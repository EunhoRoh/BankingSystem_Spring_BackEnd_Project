package com.eunho.bankingsystem_spring_v2.controller;

import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkuserDto;
import com.eunho.bankingsystem_spring_v2.service.BkuserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. 회원 API 안내",
        description = "회원 관련 기능 정의한 RestController.")
@RequestMapping("/api/bkuser")
@RestController
public class BkuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BkuserService bkuserService;
    public BkuserRestController(BkuserService bkuserService) {
        this.bkuserService = bkuserService;
    }

    @Operation(summary = "회원 정보 등록",
            description = "회원 신규 정보 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<BkuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />"
    )
    @PostMapping("")
    public ResponseEntity<BkuserDto.BkuserAfterCreateDto> save(@Valid @RequestBody BkuserDto.BkuserCreateDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bkuserService.create(params));
    }
    @Operation(summary = "회원 정보 수정",
            description = "회원 기존 정보 수정을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkuserUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkuserAfterUpdateDto\\> <br />"
                    + "@exception 해당 자료 없음 <br />"
    )
    @PutMapping("")
    public ResponseEntity<BkuserDto.BkuserAfterUpdateDto> update(@Valid @RequestBody BkuserDto.BkuserUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkuserService.update(params));
    }

    @Operation(summary = "회원 정보 조회",
            description = "회원 정보 1개 정보 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkuserSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BkuserDto.BkuserSelectDto> detail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bkuserService.detail(id));
    }
    @Operation(summary = "회원 정보 목록 조회(검색 기능 포함)",
            description = "회원 정보 전체 목록 조회를 위한 컨트롤러 (모두 접근 가능) <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<BkuserSelectDto\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/list")
    public ResponseEntity<List<BkuserDto.BkuserSelectDto>> list(@Valid @RequestBody BkuserDto.BkuserListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkuserService.list(params));
    }
    @Operation(summary = "회원 정보 추가조회 목록 조회(검색 기능 포함)",
            description = "회원 추가 조회한 정보 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkuserSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/moreList")
    public ResponseEntity<List<BkuserDto.BkuserSelectDto>> moreList(@Valid @RequestBody BkuserDto.BkuserMoreListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkuserService.moreList(params));
    }

    @Operation(summary = "회원 정보 페이징 처리 한 목록 조회(검색 기능 포함)",
            description = "회원 페이징 처리 한 정보 검색을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param BkuserSearchDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<Map<String, Object>\\> <br />"
                    + "@exception (no Exception) <br />"
    )
    @PostMapping("/pagedList")
    public ResponseEntity<CommonAfterPagedListDto<BkuserDto.BkuserSelectDto>> pagedList(@Valid @RequestBody BkuserDto.BkuserPagedListDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(bkuserService.pagedList(params));
    }

}
