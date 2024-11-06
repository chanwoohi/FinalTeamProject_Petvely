package kr.kh.petvely.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.kh.petvely.component.CustomLoginFailureHandler;
import kr.kh.petvely.model.user.UserRole;
import kr.kh.petvely.service.member.MemberDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private MemberDetailService memberDetailService;
	
	@Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//csrf : 

        //위 URL을 권한이 "USER"인 회원만 접근하도록 설정
        //.hasAuthority(UserRole.USER.name())
        //위 URL 권한이 "ROLE_USER"인 회원만 접근하도록 설정
        //.hasRole(UserRole.USER.name())
		
        http.csrf(csrf ->csrf.disable())
        	//URL에 접근 권한을 설정. MemberInterceptor, AdminInterceptor를 합친거라고 생각하면 됨
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/post/insert/*", "/post/update/*",  "/post/detail/*","/post/delete/*")
                    .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
	                .requestMatchers("/walkmatepost/insert/*", "/walkmatepost/update/*","/walkmatepost/detail/*", "/walkmatepost/delete/*")
	                .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
	                .requestMatchers("/gatpost/update/*", "/gatpost/delete/*", "/member/mypage")
	                .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
	                .requestMatchers("/member/login/button")
	                .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
	                .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
	                .anyRequest().permitAll()  // 그 외 요청은 인증 필요
            )
            .formLogin((form) -> form
                    .loginPage("/member/login")
                    .defaultSuccessUrl("/")
                    .loginProcessingUrl("/member/login")
                    //.successHandler(customLoginSuccessHandler)
                    .failureForwardUrl("/member/failed")
                    .failureHandler(customLoginFailureHandler)
                    .permitAll()
            )
            .rememberMe((rm)-> rm
            		.userDetailsService(memberDetailService)
            		.key("시크릿 코드")
            		.rememberMeCookieName("Auto")
            		.tokenValiditySeconds(2109600))
            .logout((logout) -> logout
            		.logoutUrl("/member/logout") //이 URL로 post방식으로 전송하면 로그아웃이 자동으로 실행
            		.logoutSuccessUrl("/")
            		.clearAuthentication(true)
            		.invalidateHttpSession(true)
            		.permitAll());  // 로그아웃도 모두 접근 가능
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

