package com.eunho.bankingsystem_spring_v2.controller.page;

import com.eunho.bankingsystem_spring_v2.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

//
@RequestMapping("")
@Controller
public class DefaultPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // localhost:8080/doc -> swagger를 통해 index.html로 간다.
    @GetMapping("/doc")
    public String getSwagger() {
        return "redirect:/swagger-ui/index.html";
    }


    // 공백이든 뭐든 index.html로 간다.
    @GetMapping({"", "/", "/index"})
    public String getIndex(){
        return "index";
    }


    // test.html로 간다.
    @GetMapping("/test")
    public String page(){
        return "/test";
    }


    //restcontroller로 
    // localhost/ uploadfile/filename.ext url로 보낸다.
    @ResponseBody
    @RequestMapping(value = "/uploadfile/{file_name:.+}", method = {RequestMethod.GET,RequestMethod.POST})
//    filename과 받은 request로 받은 이미지를 byte배열로 파싱
    public byte[] getImage(@PathVariable("file_name") String file_name, HttpServletRequest request) throws Exception {
        //logger.info("file_name : " + file_name);
        //서버에 request를 통한 file root path 알기
        String root_path = FileUpload.path(request);
        //logger.info("root_path : " + root_path);
        byte[] return_byte = null;
        //해당 이미지를 byte[]형태로 반환
        // file 객체 선언, file이름까지 잇다.
        File file = new File(root_path + file_name);
        InputStream in = null;
        try {
            // inputstream으로 file 객체 저장
            in = new FileInputStream(file);
            // IOUtils로 byte로 바꿔주기
            return_byte = IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            logger.info("FileNotFoundException / file_name : " + file_name);
            //e.printStackTrace();
        } catch (IOException e) {
            logger.info("IOException / file_name : " + file_name);
            //e.printStackTrace();
        } 
        //다 마치면
        finally {
            if (in != null) {
                try {
                    //input stream 닫아주기
                    in.close();
                } catch (Exception e) {
                    logger.info("final Exception / file_name : " + file_name);
                }
            }
        }
        //byte 파싱한거 return
        return return_byte;
    }

    // localhost / download/ filename.ext url로 호출
    @ResponseBody
    @RequestMapping(value = "/download/{file_name:.+}", method = RequestMethod.GET)
    // filename과 Map, 서버에 요청한 request, 받은 내용 response가 parameter다. 맞나?
    // pathvariable과 requestparam의 차이점은?
    public void download(@PathVariable("file_name") String file_name, @RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        logger.info("download 호출 성공 map : " + file_name);
        //fileupload로 서버의 파일 rootpath 알기
        String root_path = FileUpload.path(request);
        logger.info("root_path : " + root_path);
        
        //rootpath + filename으로 file 객체 만들기
        File file = new File(root_path + file_name);

        //여기는 response 에 설정해주는 부분인데, 어려우면 당분간은 패쓰!!
        String mimeType= URLConnection.guessContentTypeFromName(file_name);		//--- 파일의 mime타입을 확인합니다.
        if(mimeType==null){			//--- 마임타입이 없을 경우 application/octet-stream으로 설정합니다.
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);	//--- response에 mimetype을 설정합니다.
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(file.getName(), "utf-8") +"\"");
        //

        //inputstream에 file 객체 넣기
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));	//--- inputstream 객체를 얻습니다.

        //읽은 파일을 response에 outputstream에 복사한다.
        FileCopyUtils.copy(inputStream, response.getOutputStream());		//--- inputstream으로 파일을 읽고 outputsream으로 파일을 씁니다.
    }

}
