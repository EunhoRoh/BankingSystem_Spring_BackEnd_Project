package com.eunho.bankingsystem_spring_v2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//페이지로 보여줄때 전달할 정보값
// sdate(검색기준일_시작), fdate(검색기준일_종료)
// 요청페이지, 한번에 볼 갯수, 정렬기준, 정렬 방향
public class CommonPagedListDto {
	@Schema(description = "검색 기준일(시작)", example="1970-01-01")
	private String sdate;
	@Schema(description = "검색 기준일(종료)", example="1970-01-01")
	private String fdate;

	@NotNull
	@NotEmpty
	@Schema(description = "요청 페이지", example="1")
	private int callpage;
	@NotNull
	@NotEmpty
	@Schema(description = "한번에 볼 갯수", example="10")
	private int perpage;
	@NotNull
	@NotEmpty
	@Schema(description = "정렬 기준", example="1")
	private String orderby;
	@NotNull
	@NotEmpty
	@Schema(description = "정렬 방향", example="1")
	private String orderway;

	//dto에 afterBuild를 두었다.
	// listsize라고 전체 dto 갯수를 받는다.
	// result에 callpage, pagesize, perpage, listsize를 넣고
	// int 배열을 return
	public int[] afterBuild(int listsize){
		if(perpage <= 0){
			perpage = 10;
		}
		int pagesize_remain = listsize % perpage;
		int pagesize = listsize / perpage;
		if(pagesize_remain > 0){
			pagesize++;
		}
		if(pagesize == 0){
			pagesize++;
		}

		if(callpage < 1){
			callpage = 1;
		} else if(callpage > pagesize){
			callpage = pagesize;
		}
		int offset = (callpage - 1) * perpage;
		int[] result = {callpage, pagesize, perpage, listsize};
		callpage = offset;
		return result;
	}
}