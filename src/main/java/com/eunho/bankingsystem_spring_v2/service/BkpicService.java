package com.eunho.bankingsystem_spring_v2.service;

import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkpicDto;

import java.util.List;

public interface BkpicService {
    public BkpicDto.BkpicAfterCreateDto create(BkpicDto.BkpicCreateDto params);
    public BkpicDto.BkpicAfterUpdateDto update(BkpicDto.BkpicUpdateDto params);
    public BkpicDto.BkpicAfterUpdateDto delete(BkpicDto.BkpicUpdateDto params);
    public BkpicDto.BkpicSelectDto detail(String id);
    public List<BkpicDto.BkpicSelectDto> list(BkpicDto.BkpicListDto params);
    public List<BkpicDto.BkpicSelectDto> moreList(BkpicDto.BkpicMoreListDto params);
    public CommonAfterPagedListDto<BkpicDto.BkpicSelectDto> pagedList(BkpicDto.BkpicPagedListDto params);
}
