package br.com.royalbet;

import br.com.royalbet.filter.LoginFilter;
import org.apache.juli.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoyalBetApplication {
	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegistrationBean(){
		FilterRegistrationBean<LoginFilter> registrationBean
				= new FilterRegistrationBean<LoginFilter>();
		LoginFilter customURLFilter = new LoginFilter();
		registrationBean.setFilter(customURLFilter);
		registrationBean.addUrlPatterns("/bet/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}



	public static void main(String[] args) {
		SpringApplication.run(RoyalBetApplication.class, args);
	}



}
