package com.eunho.bankingsystem_spring_v2.domain;



import com.eunho.bankingsystem_spring_v2.dto.BkboardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Bkboard extends AuditingFields{
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문
    //@Setter @Column(nullable = false) private String tbuserId;

    protected Bkboard(){}
    private Bkboard(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public static Bkboard of(String title, String content) {
        return new Bkboard(title, content);
    }
    public static Bkboard of(String title) {
        return new Bkboard(title, "");
    }


    //id값으로 BkboardAfterCreateDto에 값을 준다?
    public BkboardDto.BkboardAfterCreateDto toAfterCreateDto() {
        return BkboardDto.BkboardAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }

    //id, deleted, title, content 값으로 BkboardAfterUpdateDto에 값을 준다?*
    //이 toAfterUpdateDto가 필요한 이유는?

    public BkboardDto.BkboardAfterUpdateDto toAfterUpdateDto() {
        return BkboardDto.BkboardAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(getTitle())
                .content(getContent())
                .build();
    }
    
}
