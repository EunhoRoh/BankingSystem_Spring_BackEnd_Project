package com.thc.sprapi.domain;

import com.thc.sprapi.dto.TbuserDto;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(
        indexes = {
        @Index(name = "IDX_tbuser_createdAt", columnList = "createdAt")
        ,@Index(name = "IDX_tbuser_modifiedAt", columnList = "modifiedAt")
        ,@Index(name = "IDX_tbuser_process", columnList = "process")
        }
        , uniqueConstraints= {
            @UniqueConstraint(name = "UQ_tbuser_nick", columnNames = {"nick"})
        }
        )
@Entity
public class Tbuser extends AuditingFields {

    @Setter @Column(nullable = false) private String uid;
    @Setter @Column(nullable = false) private String pw;
    @Setter @Column(nullable = false) private String nick;
    @Setter @Column(nullable = false) private String sfrom;
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = true) private String name;
    @Setter @Column(nullable = true) private String phone;
    @Setter @Column(nullable = true) private String mpic;
    @Setter @Column(nullable = true, length = 10000) private String content; // 본문

    protected Tbuser(){}
    private Tbuser(String uid, String pw, String nick, String sfrom, String process) {
        this.uid = uid;
        this.pw = pw;
        this.nick = nick;
        this.sfrom = sfrom;
        this.process = process;
    }
    public static Tbuser of(String uid, String pw, String nick, String sfrom, String process) {
        return new Tbuser(uid, pw, nick, sfrom, process);
    }
    public static Tbuser of(String uid, String pw) {
        return new Tbuser(uid, pw, "", "", "0");
    }

    public TbuserDto.TbuserAfterCreateDto toAfterCreateDto() {
        return TbuserDto.TbuserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbuserDto.TbuserAfterUpdateDto toAfterUpdateDto() {
        return TbuserDto.TbuserAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .build();
    }

}
