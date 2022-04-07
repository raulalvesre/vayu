package br.com.vayu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class VayuApplication  {

	@Bean
	public MessageSource messageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:validation-messages");
		messageSource.setDefaultEncoding("UTF-8");

		return  messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		var bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());

		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(VayuApplication.class, args);
	}

}
