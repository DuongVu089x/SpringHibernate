package com.dav.spring.hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.dav.spring.hibernate.common.logger.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class AssetConfig.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.dav.spring.hibernate.common.logger" })
public class AssetConfig {

	/**
	 * My logger.
	 *
	 * @return the my logger
	 */
	@Bean
	public MyLogger myLogger() {
		return new MyLogger();
	}
}
