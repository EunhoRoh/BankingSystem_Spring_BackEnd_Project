package com.eunho.bankingsystem_spring_v2.controller;

import com.eunho.bankingsystem_spring_v2.util.FileUpload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


// localhost /api/default로 호출 받음
@Tag(name = "0. 기본 API 안내",
        description = "기본 기능 정의한 RestController.")
@RequestMapping("/api/default")
@RestController
public class DefaultRestController {

    //파일 upload하기
    @Operation(summary = "파일업로드",
            description = "파일을 서버에 업로드(일반) \n"
                    + "@param MultipartFile multipartFile \n"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<String\\> \n"
                    + "@exception \n"
    )
    // /api/default/uploadFile로 호출
    // 서버에 파일 업로드
    @PostMapping("/uploadFile")
    // MultipartFile로 file 받아서
    public ResponseEntity<String> uploadFile(@Valid @RequestParam MultipartFile file, HttpServletRequest request) {
        String returnValue = null;
        try {
            // FileUpload의 local을 통해, 받은 file과 서버에 보낸 request로 file의 URL을 얻어온다.
            // file url은 서버에 업로드된 url 이다.
            //returnValue = FileUpload.s3(file);
            returnValue = FileUpload.local(file, request);
        } catch (IOException e) {
        }
        //그리고 returnvalue String return(file url)
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
