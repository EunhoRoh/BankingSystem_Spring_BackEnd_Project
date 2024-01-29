package com.eunho.bankingsystem_spring_v2.mapper;

import com.eunho.bankingsystem_spring_v2.dto.BkuserDto;

import java.util.List;

public interface BkuserMapper {
	BkuserDto.BkuserSelectDto detail(String id);
	List<BkuserDto.BkuserSelectDto> list(BkuserDto.BkuserListDto params);
	List<BkuserDto.BkuserSelectDto> moreList(BkuserDto.BkuserMoreListDto params);
	List<BkuserDto.BkuserSelectDto> pagedList(BkuserDto.BkuserPagedListDto params);
	int pagedListCount(BkuserDto.BkuserPagedListDto params);
}