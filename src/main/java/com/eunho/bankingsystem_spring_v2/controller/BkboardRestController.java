package com.eunho.bankingsystem_spring_v2.controller;

import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkboardDto;
import com.eunho.bankingsystem_spring_v2.service.BkboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//BkboardRestController로
@Tag(name = "1. 게시판 API 안내",
        description = "게시판 관련 기능 정의한 RestController.")
// /api/bkboard 로 호출
@RequestMapping("/api/bkboard")
@RestController
public class BkboardRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //bkboardService 주입
    private final BkboardService bkboardService;
    
    public BkboardRestController(BkboardService bkboardService) {
        this.bkboardService = bkboardService;
    }

    
    // /api/bkboard PostMapping으로 호출
    @Operation(summary = "게시판 글 등록",
            description = "게시판 신규 글 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkboardCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<BkboardAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
//    save 글 등록
    // BkboardCreateDto로 params으로 받고 BkboardAfterCreateDto return
    public ResponseEntity<BkboardDto.BkboardAfterCreateDto> save(@Valid @RequestBody BkboardDto.BkboardCreateDto params, HttpServletRequest request, HttpServletResponse response) {
        /*
        String[] pics = params.getPics();
        for (String pic : pics) {
            logger.info("Ctrl- / {} : {}", i, pic);
        }
        */
        /*
        logger.info("Ctrl- / test_auth : " + request.getAttribute("test_auth"));
        logger.info("Ctrl- / test_header : " + response.getHeader("test_header"));
        request.setAttribute("test_auth_1", "1Y");
        response.setHeader("test_header_1", "1!");
         */
        
        //bkboardService로 create로 BkboardCreateDto를 보내고 만들기
        return ResponseEntity.status(HttpStatus.CREATED).body(bkboardService.create(params));
    }

    ///api/bkboard PutMapping으로 호출
    @Operation(summary = "게시판 글 수정",
            description = "게시판 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkboardUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkboardAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
//    BkboardUpdateDto를 받아서 BkboardAfterUpdateDto return
    public ResponseEntity<BkboardDto.BkboardAfterUpdateDto> update(@Valid @RequestBody BkboardDto.BkboardUpdateDto params) {
        //bkboardService의 update 호출
        return ResponseEntity.status(HttpStatus.OK).body(bkboardService.update(params));
    }


    // /api/bkboard/{id} 으로 호출
    @Operation(summary = "게시판 글 정보 조회",
            description = "게시판 글 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkboardSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    //id 값을 url로 바꾸자
    // 상세 페이지
    @GetMapping("/{id}")
    // Bkboard의 pk id를 받아서 BkboardSelectDto return
    public ResponseEntity<BkboardDto.BkboardSelectDto> detail(@PathVariable("id") String id) {

        // bkboardService의 detail 호출
        return ResponseEntity.status(HttpStatus.OK).body(bkboardService.detail(id));
    }

    // /api/bkboard/list 으로 호출
    @Operation(summary = "게시판 글 정보 목록 조회(검색 기능 포함)",
            description = "게시판 글 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkboardSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    // list로 BkboardListDto를 parameter로 받은 다음 BkboardSelectDto의 list로 return
    public ResponseEntity<List<BkboardDto.BkboardSelectDto>> list(@Valid @RequestBody BkboardDto.BkboardListDto params) {
        //bkboardService의 list로 BkboardSelectDto list return
        return ResponseEntity.status(HttpStatus.OK).body(bkboardService.list(params));
    }


    // /api/bkboard/moreList 으로 호출
    @Operation(summary = "게시판 글 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "게시판 추가 조회한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkboardSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    // BkboardMoreListDto를 parameter로 받아서 BkboardSelectDto의 list로 return
    public ResponseEntity<List<BkboardDto.BkboardSelectDto>> moreList(@Valid @RequestBody BkboardDto.BkboardMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkboardService.moreList(params));
    }


// BkboardPagedListDto를 받아서 bkboardService의 pagedList의 파라미터로 넘겨준다.
    //pageList에서는 BkboardPagedListDto를 받아서 bkboardMapper의 pagedListCount와 pagedList의 파라미터로 보내고
    // CommonAfterPagedListDto의 perpage, listsize, callpage, lastpage, list 값들을 설정해 준후 BkboardSelectDto의 id, title, content, deleted, creaated_at, modified_at, pics, files로 값들을 뿌려준다? *
    @Operation(summary = "게시판 글 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "게시판 페이징 처리 한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkboardSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<BkboardDto.BkboardSelectDto>> pagedList(@Valid @RequestBody BkboardDto.BkboardPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkboardService.pagedList(params));
    }

}
