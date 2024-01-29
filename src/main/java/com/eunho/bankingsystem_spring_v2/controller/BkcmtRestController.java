package com.eunho.bankingsystem_spring_v2.controller;

import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkcmtDto;
import com.eunho.bankingsystem_spring_v2.service.BkcmtService;
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

@Tag(name = "1. 댓글 API 안내",
        description = "댓글 관련 기능 정의한 RestController.")
@RequestMapping("/api/bkcmt")
@RestController
public class BkcmtRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BkcmtService bkcmtService;
    public BkcmtRestController(BkcmtService bkcmtService) {
        this.bkcmtService = bkcmtService;
    }

    @Operation(summary = "댓글 글 등록",
            description = "댓글 신규 글 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkcmtCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<BkcmtAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    public ResponseEntity<BkcmtDto.BkcmtAfterCreateDto> save(@Valid @RequestBody BkcmtDto.BkcmtCreateDto params, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bkcmtService.create(params));
    }
    @Operation(summary = "댓글 글 수정",
            description = "댓글 기존 글 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkcmtUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkcmtAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    public ResponseEntity<BkcmtDto.BkcmtAfterUpdateDto> update(@Valid @RequestBody BkcmtDto.BkcmtUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkcmtService.update(params));
    }

    @Operation(summary = "댓글 글 정보 조회",
            description = "댓글 글 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkcmtSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BkcmtDto.BkcmtSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bkcmtService.detail(id));
    }
    @Operation(summary = "댓글 글 정보 목록 조회(검색 기능 포함)",
            description = "댓글 글 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkcmtSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    public ResponseEntity<List<BkcmtDto.BkcmtSelectDto>> list(@Valid @RequestBody BkcmtDto.BkcmtListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkcmtService.list(params));
    }
    @Operation(summary = "댓글 글 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "댓글 추가 조회한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkcmtSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    public ResponseEntity<List<BkcmtDto.BkcmtSelectDto>> moreList(@Valid @RequestBody BkcmtDto.BkcmtMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkcmtService.moreList(params));
    }

    @Operation(summary = "댓글 글 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "댓글 페이징 처리 한 글 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkcmtSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<BkcmtDto.BkcmtSelectDto>> pagedList(@Valid @RequestBody BkcmtDto.BkcmtPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkcmtService.pagedList(params));
    }

}
