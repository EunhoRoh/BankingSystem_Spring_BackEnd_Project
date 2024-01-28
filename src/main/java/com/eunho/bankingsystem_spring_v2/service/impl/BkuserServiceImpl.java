package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbuser;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbuserMapper;
import com.thc.sprapi.repository.TbuserRepository;
import com.thc.sprapi.service.TbuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbuserRepository tbuserRepository;
    private final TbuserMapper tbuserMapper;
    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
            ,TbuserMapper tbuserMapper
    ) {
        this.tbuserRepository = tbuserRepository;
        this.tbuserMapper = tbuserMapper;
    }

    /**/

    public TbuserDto.TbuserAfterCreateDto create(TbuserDto.TbuserCreateDto params){
        return tbuserRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbuserDto.TbuserAfterUpdateDto update(TbuserDto.TbuserUpdateDto params){
        Tbuser tbuser = tbuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getPassword() != null){
            tbuser.setPassword(params.getPassword());
        }
        if(params.getNick() != null){
            tbuser.setNick(params.getNick());
        }
        if(params.getSfrom() != null){
            tbuser.setSfrom(params.getSfrom());
        }
        if(params.getDeleted() != null){
            tbuser.setDeleted(params.getDeleted());
        }
        if(params.getProcess() != null){
            tbuser.setProcess(params.getProcess());
        }
        if(params.getName() != null){
            tbuser.setName(params.getName());
        }
        if(params.getPhone() != null){
            tbuser.setPhone(params.getPhone());
        }
        if(params.getMpic() != null){
            tbuser.setMpic(params.getMpic());
        }
        tbuserRepository.save(tbuser);
        return tbuser.toAfterUpdateDto();
    }

    public TbuserDto.TbuserSelectDto detail(String id){
        return tbuserMapper.detail(id);
    }
    public List<TbuserDto.TbuserSelectDto> list(TbuserDto.TbuserListDto params){
        return tbuserMapper.list(params);
    }
    public List<TbuserDto.TbuserSelectDto> moreList(TbuserDto.TbuserMoreListDto params){
        params.afterBuild();
        return tbuserMapper.moreList(params);
    }
    public CommonAfterPagedListDto<TbuserDto.TbuserSelectDto> pagedList(TbuserDto.TbuserPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbuserMapper.pagedListCount(params)), tbuserMapper.pagedList(params));
    }

}