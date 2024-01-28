package com.thc.sprapi.service;

import com.thc.sprapi.dto.TbboardDto;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

public interface TbboardService {
    public TbboardDto.TbboardAfterCreateDto create(TbboardDto.TbboardCreateDto params);
    public TbboardDto.TbboardAfterUpdateDto update(TbboardDto.TbboardUpdateDto params);
    public TbboardDto.TbboardSelectDto get(String id);
}
