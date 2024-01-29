package com.eunho.bankingsystem_spring_v2.mapper;

import com.eunho.bankingsystem_spring_v2.dto.BkpicDto;

import java.util.List;

public interface BkpicMapper {
	BkpicDto.BkpicSelectDto detail(String id);
	List<BkpicDto.BkpicSelectDto> list(BkpicDto.BkpicListDto params);
	List<BkpicDto.BkpicSelectDto> moreList(BkpicDto.BkpicMoreListDto params);
	List<BkpicDto.BkpicSelectDto> pagedList(BkpicDto.BkpicPagedListDto params);
	int pagedListCount(BkpicDto.BkpicPagedListDto params);
}