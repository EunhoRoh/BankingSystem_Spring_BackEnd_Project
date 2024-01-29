package com.eunho.bankingsystem_spring_v2.service.impl;

import com.eunho.bankingsystem_spring_v2.domain.Bkcmt;
import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkcmtDto;
import com.eunho.bankingsystem_spring_v2.exception.NoMatchingDataException;
import com.eunho.bankingsystem_spring_v2.mapper.BkcmtMapper;
import com.eunho.bankingsystem_spring_v2.repository.BkcmtRepository;
import com.eunho.bankingsystem_spring_v2.service.BkcmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BkcmtServiceImpl implements BkcmtService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BkcmtRepository bkcmtRepository;
    private final BkcmtMapper bkcmtMapper;
    public BkcmtServiceImpl(
            BkcmtRepository bkcmtRepository
            ,BkcmtMapper bkcmtMapper
    ) {
        this.bkcmtRepository = bkcmtRepository;
        this.bkcmtMapper = bkcmtMapper;
    }

    public BkcmtDto.BkcmtAfterCreateDto create(BkcmtDto.BkcmtCreateDto params){
        return bkcmtRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public BkcmtDto.BkcmtAfterUpdateDto update(BkcmtDto.BkcmtUpdateDto params){
        Bkcmt bkcmt = bkcmtRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getBkboardId() != null){
            bkcmt.setBkboardId(params.getBkboardId());
        }
        if(params.getContent() != null){
            bkcmt.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            bkcmt.setDeleted(params.getDeleted());
        }
        bkcmtRepository.save(bkcmt);
        return bkcmt.toAfterUpdateDto();
    }

    public BkcmtDto.BkcmtSelectDto detail(String id){
        return bkcmtMapper.detail(id);
    }
    public List<BkcmtDto.BkcmtSelectDto> list(BkcmtDto.BkcmtListDto params){
        /*
        // 상세 정보 조회 하는 것을 디테일에만 맡길때
        List<BkcmtDto.BkcmtSelectDto> a_list = bkcmtMapper.list(params);
        List<BkcmtDto.BkcmtSelectDto> result_list = new ArrayList<>();
        for(BkcmtDto.BkcmtSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
         */
        return bkcmtMapper.list(params);
    }
    public List<BkcmtDto.BkcmtSelectDto> moreList(BkcmtDto.BkcmtMoreListDto params){
        params.afterBuild();
        return bkcmtMapper.moreList(params);
    }
    public CommonAfterPagedListDto<BkcmtDto.BkcmtSelectDto> pagedList(BkcmtDto.BkcmtPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(bkcmtMapper.pagedListCount(params)), bkcmtMapper.pagedList(params));
    }

}
