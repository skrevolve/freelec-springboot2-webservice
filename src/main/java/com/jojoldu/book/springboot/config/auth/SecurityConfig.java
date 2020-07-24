package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //스프링 시큐리티 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //h2-console 화면을 사용하기위해 해당 옵션들을 disable처리
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    //URL별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeRequests()
                    //권한 관리 대상을 지정하는 옵션
                    .antMatchers("/",
                                "/css/**",
                                "/images/**",
                                "/js/**",
                                "/h2-console/**")
                    //'/api/v1/**'주소를 가진 API는 USER권한을 가진 사람만 가능하도록 설정
                    .permitAll().antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    //설정된 값 외에 나머지 URL들은(anyRequest) 모두인증된 사용자들에게만 허용하도록 설정
                    .anyRequest().authenticated()
                .and()
                    //로그아웃 기능 설정의 진입점
                    .logout()
                    //로그아웃 성공시 / 로 이동
                    .logoutSuccessUrl("/")
                .and()
                    //OAuth2 로그인 기능 설정의 진입점
                    .oauth2Login()
                        //로그인 성공 이후 사용자 정보를 가져올때 설정들을 담당
                        .userInfoEndpoint()
                            //소셜 로그인 성공시 후속 조치를 진행할 userService 인터페이스의 구현체를 등록
                            .userService(customOAuth2UserService);

    }
}
