package com.parfait.study.reactivemongo.config;

import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.parfait.study.reactivemongo.config.converter.PostBodyConverter;

@Configuration
public class MongoDBConfiguration {

	@Bean
	public MongoClientSettingsBuilderCustomizer mongoClientOptions() {
		return settingBuilder -> {
			settingBuilder.applyToConnectionPoolSettings(poolSettingBuilder -> {
				poolSettingBuilder.maxSize(2);
				poolSettingBuilder.minSize(2);
			});
		};
	}

	@Bean
	public PostBodyConverter postBodyConverter() {
		return new PostBodyConverter();
	}
}