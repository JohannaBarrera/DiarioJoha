package com.company.spring_boot_crud_app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/entradas/editar").setViewName("formulario");
		registry.addViewController("/entradas").setViewName("lista");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("login");
	}

}