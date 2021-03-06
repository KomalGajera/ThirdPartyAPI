package com.demo.usefulldemo.config;

import java.io.IOException;
import java.util.Base64;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setFieldMatchingEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

		Converter<byte[], String> base64Converter = new Converter<byte[], String>() {
			@Override
			public String convert(MappingContext<byte[], String> context) {
				return context.getSource() == null ? null : Base64.getEncoder().encodeToString(context.getSource());
			}
		};

		Converter<MultipartFile, byte[]> bytesConverter = new Converter<MultipartFile, byte[]>() {
			@Override
			public byte[] convert(MappingContext<MultipartFile, byte[]> context) {
				try {
					return context.getSource() == null || context.getSource().isEmpty() ? null
							: context.getSource().getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		mapper.addConverter(bytesConverter);
		mapper.addConverter(base64Converter);

		return mapper;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
