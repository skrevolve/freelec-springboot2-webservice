package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //모든 Entity의 상위 클래스로 지정하여 모든 테이블에 참조 되도록 한다.
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될때 마다 시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate //Entity 값이 변경될때 마다 시간 자동저장
    private LocalDateTime modifiedDate;
}
