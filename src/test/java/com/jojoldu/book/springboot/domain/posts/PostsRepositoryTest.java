package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //H2 DB를 자동 으로 실행 해줌(별다른 설정이 없다면)
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //테스트 하기 전에 데이터 침범을 미리 방지 하기 위해 단위 테스트 가 끝날때 마다 수행 되도록 메서드 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts 에 insert/update 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sukyu.lim@purpleworks.co.kr")
                .build());

    //테이블 posts 의 모든 데이터 조회
    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
    }
}
