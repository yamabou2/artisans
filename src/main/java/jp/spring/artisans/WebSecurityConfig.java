package jp.spring.artisans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {
	@Bean
	SecurityFilterChain securittyFilterChain (HttpSecurity http) throws Exception {
	    http
		    .authorizeHttpRequests(authz -> authz 
	            .requestMatchers("/").permitAll() // リクエストに対する許可設定
	            .anyRequest().authenticated()  // それ以外へのアクセスは認証（ログイン）必要
	        )
		    .formLogin(login -> login// フォームログインの設定をする。
		            .loginPage("/login").permitAll()//ログインページのURLを”/login”にしアクセスは全許可。
		            .loginProcessingUrl("/login")// ログイン処理のURLを”/login”にする。
		            .failureUrl("/login?error=true")// ログイン失敗時のリダイレクトURLの設定をする
		            .defaultSuccessUrl("/", true) // ログイン成功時のURLを"/secret "にする。
		    )
		    .logout(logout -> logout
		    	    //ログアウトリクエストのURLパターンを定義
		            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		            .logoutSuccessUrl("/")// ログアウトしたら「/」にリダイレクトする
		    );

	        return http.build();
	    }
	
	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("guest").password("guest").roles("USER").build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        return manager;
    }
	
}
