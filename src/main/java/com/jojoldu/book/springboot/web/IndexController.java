package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

//import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    //IndexController의 코드에서 반복되는 부분들 모두 @LoginIUser로 어노테이션 기반으로 개선한다
    //기존 (user)httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선되었다.
    //이제는 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올수 있게 된다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {//Model로 서버템플릿 엔진에서 사용할수 있는 객체를 저장
        //findAllDesc()결과를 posts로 index.mustache에 전달ㅁ
        model.addAttribute("posts", postsService.findAllDesc());

        //index.mustache에서 userName을 사용할수 있도록 model에 userName을 저장한다
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; //posts-save.mustache 호출
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
