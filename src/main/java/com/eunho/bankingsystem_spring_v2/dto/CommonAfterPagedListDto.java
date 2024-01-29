package com.eunho.bankingsystem_spring_v2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

//이 AfterPagedListDto는 어디서 쓰이는 걸까?
// <T>인거 보니 어떤 타입을 줄거 같은데.
// 요청페이지, 마지막페이지, 한번에 조회할 갯수, 전체 갯수
// 아마 TbboardSelectDto를 받을 리스트

@Schema
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonAfterPagedListDto<T> {
	@Schema(description = "요청 페이지", example="1")
	private int callpage;
	@Schema(description = "마지막 페이지", example="100")
	private int lastpage;
	@Schema(description = "한번에 조회할 갯수", example="100")
	private int perpage;
	@Schema(description = "전체 갯수", example="1")
	private int listsize;

	@Schema(description = "리스트", example="해당 리스트")
	private List<T> list;

	//calc int 배열은 CommonPagedListDto가 넘긴 callpage, lastpage, perpage, listsize를 받고
	// list는 아마 Bkboard의 SelectDto를 받을거 같아.
	public CommonAfterPagedListDto(int[] calc, List<T> list){
		this.callpage = calc[0];
		this.lastpage = calc[1];
		this.perpage = calc[2];
		this.listsize = calc[3];
		this.list = list;
	}
}