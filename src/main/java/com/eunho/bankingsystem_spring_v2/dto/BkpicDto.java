package com.eunho.bankingsystem_spring_v2.dto;

import com.eunho.bankingsystem_spring_v2.domain.Bkpic;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class BkpicDto {
	
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicCreateDto {
		@Schema(description = "bkboardId", example="bkboardId")
		@NotNull
		@NotEmpty
		@Size(max=100)
		private String bkboardId;

		@Schema(description = "content", example="content")
		@NotNull
		@NotEmpty
		@Size(max=200)
		private String content;

		@Schema(description = "type", example="type")
		@NotNull
		@NotEmpty
		private String type;

		public Bkpic toEntity() {
			return Bkpic.of(bkboardId, content, type);
		}
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicAfterCreateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String id;

		@Schema(description = "bkboardId", example="bkboardId")
		@Size(max=100)
		private String bkboardId;

		@Schema(description = "deleted", example="Y")
		@Size(max=1)
		private String deleted;

		@Schema(description = "content", example="content")
		@Size(max=200)
		private String content;

		@Schema(description = "type", example="type")
		private String type;
	}
	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicAfterUpdateDto {
		@Schema(description = "id", example="length32textnumber")
		private String id;
		@Schema(description = "deleted", example="Y")
		private String deleted;
		@Schema(description = "bkboardId", example="bkboardId")
		@Size(max=100)
		private String bkboardId;
		@Schema(description = "content", example="content")
		@Size(max=200)
		private String content;
		@Schema(description = "type", example="type")
		private String type;
	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicSelectDto {

		@Schema(description = "id", example="id")
		private String id;
		@Schema(description = "bkboard_id", example="bkboard_id")
		private String bkboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
		@Schema(description = "created_at", example="2024-01-01 00:00:00.000000")
		private String created_at;
		@Schema(description = "modified_at", example="2024-01-01 00:00:00.000000")
		private String modified_at;

	}

	@Schema
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicListDto {
		@Schema(description = "bkboard_id", example="bkboardId")
		private String bkboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}

	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicPagedListDto extends CommonPagedListDto {
		@Schema(description = "bkboard_id", example="bkboard_id")
		private String bkboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}
	@Schema
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BkpicMoreListDto extends CommonMoreListDto {
		@Schema(description = "bkboard_id", example="bkboard_id")
		private String bkboard_id;
		@Schema(description = "content", example="content")
		private String content;
		@Schema(description = "type", example="type")
		private String type;
		@Schema(description = "deleted", example="N")
		private String deleted;
	}
	
	
}