package com.eunho.bankingsystem_spring_v2.service;

import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkuserDto;

import java.util.List;

public interface BkuserService {

    public BkuserDto.BkuserAfterCreateDto create(BkuserDto.BkuserCreateDto params);
    public BkuserDto.BkuserAfterUpdateDto update(BkuserDto.BkuserUpdateDto params);
    public BkuserDto.BkuserSelectDto detail(String id);
    public List<BkuserDto.BkuserSelectDto> list(BkuserDto.BkuserListDto params);
    public List<BkuserDto.BkuserSelectDto> moreList(BkuserDto.BkuserMoreListDto params);
    public CommonAfterPagedListDto<BkuserDto.BkuserSelectDto> pagedList(BkuserDto.BkuserPagedListDto params);
}
