package com.eunho.bankingsystem_spring_v2.domain;

import com.eunho.bankingsystem_spring_v2.dto.BkpicDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "bkboardId")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Bkpic extends AuditingFields {

    @Setter @Column(nullable = false) private String bkboardId; // 게시판의 pk
    @Setter @Column(nullable = false, length = 10000) private String content;
    @Setter @Column(nullable = false) private String type;

    protected Bkpic(){}
    private Bkpic(String bkboardId, String content, String type) {
        this.bkboardId = bkboardId;
        this.content = content;
        this.type = type;
    }
    public static Bkpic of(String bkboardId, String content, String type) {
        return new Bkpic(bkboardId, content, type);
    }
    /*
    public static Bkpic of(String bkboardId) {
        return new Bkpic(bkboardId, "", "");
    }
     */

    public BkpicDto.BkpicAfterCreateDto toAfterCreateDto() {
        return BkpicDto.BkpicAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public BkpicDto.BkpicAfterUpdateDto toAfterUpdateDto() {
        return BkpicDto.BkpicAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .bkboardId(getBkboardId())
                .content(getContent())
                .type(getType())
                .build();
    }

}
