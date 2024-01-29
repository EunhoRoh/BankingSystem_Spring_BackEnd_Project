package com.eunho.bankingsystem_spring_v2.domain;

import com.eunho.bankingsystem_spring_v2.dto.BkuserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(
        indexes = {
        @Index(name = "IDX_bkuser_createdAt", columnList = "createdAt")
        ,@Index(name = "IDX_bkuser_modifiedAt", columnList = "modifiedAt")
        ,@Index(name = "IDX_bkuser_process", columnList = "process")
        }
        , uniqueConstraints= {
            @UniqueConstraint(name = "UQ_bkuser_nick", columnNames = {"nick"})
        }
        )
@Entity
public class Bkuser extends AuditingFields {

    @Setter @Column(nullable = false) private String uid;
    @Setter @Column(nullable = false) private String password;
    @Setter @Column(nullable = false) private String nick;
    @Setter @Column(nullable = false) private String sfrom;
    @Setter @Column(nullable = false) private String process;
    @Setter @Column(nullable = true) private String name;
    @Setter @Column(nullable = true) private String phone;
    @Setter @Column(nullable = true) private String mpic;
    @Setter @Column(nullable = true, length = 10000) private String content; // 본문

    protected Bkuser(){}
    private Bkuser(String uid, String pw, String nick, String sfrom, String process) {
        this.uid = uid;
        this.password = pw;
        this.nick = nick;
        this.sfrom = sfrom;
        this.process = process;
    }
    public static Bkuser of(String uid, String pw, String nick, String sfrom, String process) {
        return new Bkuser(uid, pw, nick, sfrom, process);
    }
    public static Bkuser of(String uid, String pw) {
        return new Bkuser(uid, pw, "", "", "0");
    }

    public BkuserDto.BkuserAfterCreateDto toAfterCreateDto() {
        return BkuserDto.BkuserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public BkuserDto.BkuserAfterUpdateDto toAfterUpdateDto() {
        return BkuserDto.BkuserAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .build();
    }

}
