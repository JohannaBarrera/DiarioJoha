package com.company.spring_boot_crud_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.requestMatchers("/entradas").authenticated()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.defaultSuccessUrl("/entradas", true)
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() { 
		Usuario usuario = usuarioRepository.findByNombre("joha");
		System.out.println("este es CATE :" + usuario.getNombre());
		System.out.println("este es CATE :" + usuario.getClave());


		UserDetails user =
			 User.withDefaultPasswordEncoder()
			//	.username( cate.getNombre())
			.username( usuario.getNombre())
				.password(usuario.getClave())
				//.roles("USER")
				.build();


	//	 System.out.println("este es CATE :" + cate.getNombre());

		return new InMemoryUserDetailsManager(user);
	}





}
