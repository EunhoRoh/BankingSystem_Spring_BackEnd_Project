package com.eunho.bankingsystem_spring_v2.dto;

import com.eunho.bankingsystem_spring_v2.domain.Bkboard;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class BkboardDto {


    @Schema
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
//    BkboardCreateDto는 title, content, pics[], types[] 값을 전달해 주는거지
    public static class BkboardCreateDto {
        @Schema(description = "title", example="title")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String title;

        @Schema(description = "content", example="content")
        @NotNull
        @NotEmpty
        @Size(max=200)
        private String content;

        @Schema(description = "pics", example="pics")
        private String[] pics;
        @Schema(description = "types", example="types")
        private String[] types;

        public Bkboard toEntity() {
            return Bkboard.of(title, content);
        }
    }
    
    //BkboardAfterCreateDto 는 createDto를 만들고나서 id 값을 전달해 주는거지
    @Schema
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardAfterCreateDto {
        @Schema(description = "id", example="length32textnumber")
        private String id;
    }

    //BkboardUpdateDto 는 update할대 id, title, deleted, content 정보를 준다.
    @Schema
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardUpdateDto {
        @Schema(description = "id", example="length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max=32)
        private String id;

        @Schema(description = "title", example="title")
        @Size(max=100)
        private String title;

        @Schema(description = "deleted", example="Y")
        @Size(max=1)
        private String deleted;

        @Schema(description = "content", example="content")
        @Size(max=200)
        private String content;
    }

    //BkboardAfterUpdateDto 는 update하고나서 id, title, deleted, content 정보를 준다.
    @Schema
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardAfterUpdateDto {
        @Schema(description = "id", example="length32textnumber")
        private String id;
        @Schema(description = "deleted", example="Y")
        private String deleted;
        @Schema(description = "title", example="title")
        @Size(max=100)
        private String title;
        @Schema(description = "content", example="content")
        @Size(max=200)
        private String content;
    }


    //BkboardSelectDto 는 Bkboard의 모든 정보를 준다.
    // id, title, content, deleted, created_at, modified_at, TbpicSelectDto의 list인 pics, TbpicSelectDto의 liist인 files
    @Schema
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardSelectDto {

        @Schema(description = "id", example="id")
        private String id;
        @Schema(description = "title", example="title")
        private String title;
        @Schema(description = "content", example="content")
        private String content;
        @Schema(description = "deleted", example="N")
        private String deleted;
        @Schema(description = "created_at", example="2024-01-01 00:00:00.000000")
        private String created_at;
        @Schema(description = "modified_at", example="2024-01-01 00:00:00.000000")
        private String modified_at;

        @Schema(description = "pics", example="pics")
        private List<BkpicDto.BkpicSelectDto> pics;
        @Schema(description = "files", example="files")
        private List<BkpicDto.BkpicSelectDto> files;

    }


    //BkboardListDto 는 Bkboard를 검색할때 어떤 정보를 줄건지 정보 포함
    // title, deleted
    @Schema
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardListDto {
        @Schema(description = "title", example="title")
        private String title;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }
    
    
    //BkboardPagedListDto는 페이지로 보여줄때
    @Schema
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardPagedListDto extends CommonPagedListDto {
        @Schema(description = "title", example="title")
        private String title;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }

    //BkboardMoreListDto는 스크롤로 보여줄때
    @Schema
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BkboardMoreListDto extends CommonMoreListDto {
        @Schema(description = "title", example="title")
        private String title;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }
}
