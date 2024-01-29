package com.eunho.bankingsystem_spring_v2.service;

import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkcmtDto;

import java.util.List;

public interface BkcmtService {
    public BkcmtDto.BkcmtAfterCreateDto create(BkcmtDto.BkcmtCreateDto params);
    public BkcmtDto.BkcmtAfterUpdateDto update(BkcmtDto.BkcmtUpdateDto params);
    public BkcmtDto.BkcmtSelectDto detail(String id);
    public List<BkcmtDto.BkcmtSelectDto> list(BkcmtDto.BkcmtListDto params);
    public List<BkcmtDto.BkcmtSelectDto> moreList(BkcmtDto.BkcmtMoreListDto params);
    public CommonAfterPagedListDto<BkcmtDto.BkcmtSelectDto> pagedList(BkcmtDto.BkcmtPagedListDto params);
}
