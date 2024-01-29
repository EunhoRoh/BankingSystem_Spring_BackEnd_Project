package com.eunho.bankingsystem_spring_v2.service.impl;

import com.eunho.bankingsystem_spring_v2.domain.Bkuser;
import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkuserDto;
import com.eunho.bankingsystem_spring_v2.exception.NoMatchingDataException;
import com.eunho.bankingsystem_spring_v2.mapper.BkuserMapper;
import com.eunho.bankingsystem_spring_v2.repository.BkuserRepository;
import com.eunho.bankingsystem_spring_v2.service.BkuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BkuserServiceImpl implements BkuserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BkuserRepository bkuserRepository;
    private final BkuserMapper bkuserMapper;
    public BkuserServiceImpl(
            BkuserRepository bkuserRepository
            ,BkuserMapper bkuserMapper
    ) {
        this.bkuserRepository = bkuserRepository;
        this.bkuserMapper = bkuserMapper;
    }

    /**/

    public BkuserDto.BkuserAfterCreateDto create(BkuserDto.BkuserCreateDto params){
        return bkuserRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public BkuserDto.BkuserAfterUpdateDto update(BkuserDto.BkuserUpdateDto params){
        Bkuser bkuser = bkuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getPassword() != null){
            bkuser.setPassword(params.getPassword());
        }
        if(params.getNick() != null){
            bkuser.setNick(params.getNick());
        }
        if(params.getSfrom() != null){
            bkuser.setSfrom(params.getSfrom());
        }
        if(params.getDeleted() != null){
            bkuser.setDeleted(params.getDeleted());
        }
        if(params.getProcess() != null){
            bkuser.setProcess(params.getProcess());
        }
        if(params.getName() != null){
            bkuser.setName(params.getName());
        }
        if(params.getPhone() != null){
            bkuser.setPhone(params.getPhone());
        }
        if(params.getMpic() != null){
            bkuser.setMpic(params.getMpic());
        }
        bkuserRepository.save(bkuser);
        return bkuser.toAfterUpdateDto();
    }

    public BkuserDto.BkuserSelectDto detail(String id){
        return bkuserMapper.detail(id);
    }
    public List<BkuserDto.BkuserSelectDto> list(BkuserDto.BkuserListDto params){
        return bkuserMapper.list(params);
    }
    public List<BkuserDto.BkuserSelectDto> moreList(BkuserDto.BkuserMoreListDto params){
        params.afterBuild();
        return bkuserMapper.moreList(params);
    }
    public CommonAfterPagedListDto<BkuserDto.BkuserSelectDto> pagedList(BkuserDto.BkuserPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(bkuserMapper.pagedListCount(params)), bkuserMapper.pagedList(params));
    }

}
