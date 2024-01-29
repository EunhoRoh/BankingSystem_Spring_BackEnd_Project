package com.eunho.bankingsystem_spring_v2.service.impl;

import com.eunho.bankingsystem_spring_v2.domain.Bkboard;
import com.eunho.bankingsystem_spring_v2.dto.CommonAfterPagedListDto;
import com.eunho.bankingsystem_spring_v2.dto.BkboardDto;
import com.eunho.bankingsystem_spring_v2.dto.BkpicDto;
import com.eunho.bankingsystem_spring_v2.exception.NoMatchingDataException;
import com.eunho.bankingsystem_spring_v2.mapper.BkboardMapper;
import com.eunho.bankingsystem_spring_v2.repository.BkboardRepository;
import com.eunho.bankingsystem_spring_v2.service.BkboardService;
import com.eunho.bankingsystem_spring_v2.service.BkpicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

//Service로 기능 담당
@Service
public class BkboardServiceImpl implements BkboardService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //repository, mapper, service 주입
    private final BkboardRepository bkboardRepository;
    private final BkboardMapper bkboardMapper;
    private final BkpicService bkpicService;
    public BkboardServiceImpl(
            BkboardRepository bkboardRepository
            ,BkboardMapper bkboardMapper
            ,BkpicService bkpicService
    ) {
        this.bkboardRepository = bkboardRepository;
        this.bkboardMapper = bkboardMapper;
        this.bkpicService = bkpicService;
    }



    //create로 BkboardCreateDto를 받아서 BkboardAfterCreateDto 뿌려주기
    public BkboardDto.BkboardAfterCreateDto create(BkboardDto.BkboardCreateDto params){
        //repository에 createDto 저장하고 Dto로 만들고 BkboardAfterCreateDto로 만들기
        BkboardDto.BkboardAfterCreateDto result = bkboardRepository.save(params.toEntity()).toAfterCreateDto();
        String[] pics = params.getPics();
        String[] types = params.getTypes();
        //bkpicService의 create를 통해 BkpicCreateDto의 정보 담아서 하나씩 repository에 저장?
        for(int i=0;i<pics.length;i++){
            bkpicService.create(new BkpicDto.BkpicCreateDto(result.getId(), pics[i], types[i]));
        }
        /*
        for (String pic : params.getPics()) {
            BkpicService.create(new BkpicDto.BkpicCreateDto(result.getId(), pic));
        }
        */
        //result에는 만들어진 BkboardAfterCreateDto에 정보가 있다.
        return result;
    }


    public BkboardDto.BkboardAfterUpdateDto update(BkboardDto.BkboardUpdateDto params){
        //bkboardRepository로 BkboardUpdateDto의 id를 통해 bkboard 객체를 만든다.
        Bkboard bkboard = bkboardRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));

        if(params.getTitle() != null){
            //title 수정
            bkboard.setTitle(params.getTitle());
        }
        if(params.getContent() != null){
            //content 수정
            bkboard.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            //deleted 수정
            bkboard.setDeleted(params.getDeleted());
        }
        //
        //이렇게 수정된 bkboard를 저장한다. (알아서 id에 맞는 bkboard 찾아서 수정해주나?
        bkboardRepository.save(bkboard);
        //그리고 수정된 bkboard의 afterupdateDto 정보를 return
        return bkboard.toAfterUpdateDto();
    }


    // bkboard id값으로
    public BkboardDto.BkboardSelectDto detail(String id){
        //id 값으로 BkboardSelectDto에 정보를 넣어줄 값들을 불러온다.
        BkboardDto.BkboardSelectDto result = bkboardMapper.detail(id);
        //불러온 dto의 pics 값들을 setting 해줘야 한다.
        // bkpicService의 list 기능을 통해 BkpicListDto를 넣어주는데 file type은 image이고 나머진 null값
        result.setPics(bkpicService.list(new BkpicDto.BkpicListDto(id,null,"image",null)));

        // 마찬가지로 bkpicService의 list 기능을 통해 BkpicListDto를 넣어주는데 file type은 file 이고 나머진 null값
        result.setFiles(bkpicService.list(new BkpicDto.BkpicListDto(id,null,"file",null)));

        //result에는 SelectDto인데 pics와 files 배열에 값들이 설정되어 있다.
        return result;
    }

    //BkboardListDto를 받아서 title과 deleted에 해당하는 bkboardSeletDto를 리스트로 가져온다.
    public List<BkboardDto.BkboardSelectDto> list(BkboardDto.BkboardListDto params){
        /*
        // 상세 정보 조회 하는 것을 디테일에만 맡길때
        List<BkboardDto.BkboardSelectDto> a_list = BkboardMapper.list(params);
        List<BkboardDto.BkboardSelectDto> result_list = new ArrayList<>();
        for(BkboardDto.BkboardSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
         */
        return bkboardMapper.list(params);
    }


    //BkboardMoreListDto를 받아서 일단 afterBuild로 값 설정해주고(permore, cdatetime, cway)
    public List<BkboardDto.BkboardSelectDto> moreList(BkboardDto.BkboardMoreListDto params){
        params.afterBuild();
        //BkboardMoreListDto를 받아서 BkboardSelectDto의 리스트를 가져온다.
        return bkboardMapper.moreList(params);
    }

    //BkboardPagedListDto를 받아서 perpage, callpage 정보들을 받은 다음에 afterBuild로 값 설정해주고 pagedListcount로 몇개를 가져올지 정하고 (listsize와 그에따른 int 배열), 그리고 pagedList로 BkboardSelectDto의 리스트를 가져온다.
    public CommonAfterPagedListDto<BkboardDto.BkboardSelectDto> pagedList(BkboardDto.BkboardPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(bkboardMapper.pagedListCount(params)), bkboardMapper.pagedList(params));
    }

}
