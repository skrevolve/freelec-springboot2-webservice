package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter//클래스 내 모든 필드의 Getter 메소드 를 자동 생성 <--lombok annotation
@NoArgsConstructor//기본 생성자 자동 추가 <--lombok annotation
@Entity//테이블 과 링크될 클래스 임을 나타 낸다(Entity 클래스). CamelCase 로 테이블 이름음 매칭 한다. <--JPA annotation
public class Posts {

    @Id//해당 테이블 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    private Long id; //PK

    @Column(length = 500, nullable = false)
    private String title; //length500 notNULL

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //text 로 타입 변환 하기 위해 @Column 이용, notNULL

    private String author;

    @Builder//생성자 에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
//Entity 클래스 에서는 절대 Setter 메소드 를 만들지 않는다.
//만약 값 변경이 필요 하면 명확히 목적과 의도를 나타 내도록 한다.
//  public class Order {
//      public void cancelOrder() {
//          this.status = false;
//      }
//  }
//  public void 주문취소서비스의_취소이벤트() {
//      order.cancelOrder();
//  }