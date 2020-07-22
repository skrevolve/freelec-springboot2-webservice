package com.jojoldu.book.springboot.domain.posts;
// Posts 클래스 로 DB를 접근 하게 해줄 JPA repository

import org.springframework.data.jpa.repository.JpaRepository;

//JqaRepository<Entity 클래스, PK 타입> 를 상속 하면 기본 적인 CRUD 메소드 가 자동 으로 생성 된다.
//Entity 클래스 와 기본 Entity repository 는 함께 위치 해야 한다.(domain.posts 와 같이 관리)
public interface PostsRepository extends JpaRepository<Posts,Long> {

}
