package com.eunho.bankingsystem_spring_v2.service;

import com.eunho.bankingsystem_spring_v2.dto.BkboardDto;
import com.eunho.bankingsystem_spring_v2.dto.BkcmtDto;
import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;

import java.util.List;

public interface BkboardService {
    public BkboardDto.BkboardAfterCreateDto create(BkboardDto.BkboardCreateDto params);
    public BkboardDto.BkboardAfterUpdateDto update(BkboardDto.BkboardUpdateDto params);
//    public BkboardDto.BkboardSelectDto get(String id);


    public BkboardDto.BkboardSelectDto detail(String id);
    public List<BkboardDto.BkboardSelectDto> list(BkboardDto.BkboardSelectDto params);
    public List<BkboardDto.BkboardSelectDto> moreList(BkboardDto.BkboardSelectDto params);
    public CommonAfterPagedListDto<BkboardDto.BkboardSelectDto> pagedList(BkboardDto.BkboardSelectDto params);
}
