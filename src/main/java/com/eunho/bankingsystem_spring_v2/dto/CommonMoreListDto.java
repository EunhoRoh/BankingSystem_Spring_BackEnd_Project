package com.eunho.bankingsystem_spring_v2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//scroll 페이지를 보기위해 제공해야할 정보
//sdate, fdate ,permore, cdatetime(검색 기준일시-> 계속 검색 기준일시를 바꿔야한다.
//cway -> 조회방향
@Schema
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonMoreListDto {
	@Schema(description = "검색 기준일(시작)", example="1970-01-01")
	private String sdate;
	@Schema(description = "검색 기준일(종료)", example="1970-01-01")
	private String fdate;

	@NotNull
	@NotEmpty
	@Schema(description = "한번에 볼 갯수", example="10")
	private int permore;
	@Schema(description = "검색 기준일시", example="1970-01-01 00:00:00.000000")
	private String cdatetime;
	@Schema(description = "조회 방향", example="recent,before")
	private String cway;

	//cdatetime이 null일땐 끝페이지로 해서 다 보여준다.?
	// afterBuild는 혹시 모를 값들을 조정해주는구나
	public void afterBuild(){
		if(permore <= 0){
			permore = 10;
		}
		if(cdatetime == null || "".equals(cdatetime)){
			cdatetime = "9999-12-31 23:59:59.999999";
			cway = "before";
		}
	}
}