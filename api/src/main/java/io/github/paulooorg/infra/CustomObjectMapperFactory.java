package io.github.paulooorg.infra;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapperFactory {
	public static ObjectMapper create() {
		ObjectMapper customObjectMapper = new ObjectMapper();
		customObjectMapper.registerModule(createLocalDateTimeModule());
		return customObjectMapper;
	}
	
	private static SimpleModule createLocalDateTimeModule() {
		return new SimpleModule()
				.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
				.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
	}
}
