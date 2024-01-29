package com.eunho.bankingsystem_spring_v2.mapper;

import com.eunho.bankingsystem_spring_v2.dto.BkboardDto;

import java.util.List;

public interface BkboardMapper {
    //Bkboard의 id 값으로 BkboardSelectDto를 가져온다.
    BkboardDto.BkboardSelectDto get(String id);


    //일부러 업데이트 안하신 건가?
    BkboardDto.BkboardSelectDto detail(String id);
    List<BkboardDto.BkboardSelectDto> list(BkboardDto.BkboardListDto params);
    List<BkboardDto.BkboardSelectDto> moreList(BkboardDto.BkboardMoreListDto params);
    List<BkboardDto.BkboardSelectDto> pagedList(BkboardDto.BkboardPagedListDto params);
    int pagedListCount(BkboardDto.BkboardPagedListDto params);

}
