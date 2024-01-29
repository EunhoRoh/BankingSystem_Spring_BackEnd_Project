package com.eunho.bankingsystem_spring_v2.domain;

import com.eunho.bankingsystem_spring_v2.dto.BkcmtDto;
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
public class Bkcmt extends AuditingFields {

    @Setter @Column(nullable = false) private String bkboardId; // 게시판의 pk
    @Setter @Column(nullable = false, length = 10000) private String content;

    protected Bkcmt(){}
    private Bkcmt(String bkboardId, String content) {
        this.bkboardId = bkboardId;
        this.content = content;
    }
    public static Bkcmt of(String bkboardId, String content) {
        return new Bkcmt(bkboardId, content);
    }
    public static Bkcmt of(String bkboardId) {
        return new Bkcmt(bkboardId, "");
    }

    public BkcmtDto.BkcmtAfterCreateDto toAfterCreateDto() {
        return BkcmtDto.BkcmtAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public BkcmtDto.BkcmtAfterUpdateDto toAfterUpdateDto() {
        return BkcmtDto.BkcmtAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .bkboardId(getBkboardId())
                .content(getContent())
                .build();
    }

}
