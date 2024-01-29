package com.eunho.bankingsystem_spring_v2.service.impl;

import com.eunho.bankingsystem_spring_v2.domain.Bkpic;
import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkpicDto;
import com.eunho.bankingsystem_spring_v2.exception.NoMatchingDataException;
import com.eunho.bankingsystem_spring_v2.mapper.BkpicMapper;
import com.eunho.bankingsystem_spring_v2.repository.BkpicRepository;
import com.eunho.bankingsystem_spring_v2.service.BkpicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BkpicServiceImpl implements BkpicService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BkpicRepository bkpicRepository;
    private final BkpicMapper bkpicMapper;
    public BkpicServiceImpl(
            BkpicRepository bkpicRepository
            ,BkpicMapper bkpicMapper
    ) {
        this.bkpicRepository = bkpicRepository;
        this.bkpicMapper = bkpicMapper;
    }

    public BkpicDto.BkpicAfterCreateDto create(BkpicDto.BkpicCreateDto params){
        return bkpicRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public BkpicDto.BkpicAfterUpdateDto update(BkpicDto.BkpicUpdateDto params){
        Bkpic bkpic = bkpicRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getBkboardId() != null){
            bkpic.setBkboardId(params.getBkboardId());
        }
        if(params.getContent() != null){
            bkpic.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            bkpic.setDeleted(params.getDeleted());
        }
        bkpicRepository.save(bkpic);
        return bkpic.toAfterUpdateDto();
    }
    public BkpicDto.BkpicAfterUpdateDto delete(BkpicDto.BkpicUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }

    public BkpicDto.BkpicSelectDto detail(String id){
        return bkpicMapper.detail(id);
    }
    public List<BkpicDto.BkpicSelectDto> list(BkpicDto.BkpicListDto params){
        return bkpicMapper.list(params);
    }
    public List<BkpicDto.BkpicSelectDto> moreList(BkpicDto.BkpicMoreListDto params){
        params.afterBuild();
        return bkpicMapper.moreList(params);
    }
    public CommonAfterPagedListDto<BkpicDto.BkpicSelectDto> pagedList(BkpicDto.BkpicPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(bkpicMapper.pagedListCount(params)), bkpicMapper.pagedList(params));
    }

}
