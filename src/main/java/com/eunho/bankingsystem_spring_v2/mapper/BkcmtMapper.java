package com.eunho.bankingsystem_spring_v2.mapper;

import com.eunho.bankingsystem_spring_v2.dto.BkcmtDto;

import java.util.List;

public interface BkcmtMapper {
	BkcmtDto.BkcmtSelectDto detail(String id);
	List<BkcmtDto.BkcmtSelectDto> list(BkcmtDto.BkcmtListDto params);
	List<BkcmtDto.BkcmtSelectDto> moreList(BkcmtDto.BkcmtMoreListDto params);
	List<BkcmtDto.BkcmtSelectDto> pagedList(BkcmtDto.BkcmtPagedListDto params);
	int pagedListCount(BkcmtDto.BkcmtPagedListDto params);
}