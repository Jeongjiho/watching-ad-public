package com.watchingad.watchingad.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
public class JasyptConfiguration {
	
	@Bean
	@ConfigurationProperties(prefix = "jasypt.encryptor")
	public SimpleStringPBEConfig encryptorConfig() {
		return new SimpleStringPBEConfig();
	}

	@Bean(name = "stringEncryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(encryptorConfig());
		return encryptor;
	}

}
