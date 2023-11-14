package com.product.api.controller;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import com.product.api.ApiApplication;
import com.product.api.alle.Alle;
import com.product.api.alle.AlleRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AlleControllerTest.Initializer.class, classes = ApiApplication.class)
public class AlleControllerTest {

	@ClassRule
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest").withInitScript("db/script.sql");
	@Autowired
	private AlleRepository alleRepository;

	public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues values = TestPropertyValues.of("spring.datasource.url=" + mysql.getJdbcUrl(),
					"spring.datasource.username=" + mysql.getUsername(),
					"spring.datasource.password=" + mysql.getPassword());
			values.applyTo(configurableApplicationContext);
		}
	}

	@Test
	public void shouldGetAllAlle() {
		// Given && When
		int rows = 14;
		List<Alle> found = alleRepository.findAll();

		// Then
		Assert.assertEquals(found.size(), rows);

	}

	@Test
	public void shouldGetOneAlle() {
		// Given && When
		Optional<Alle> alle1 = Optional.ofNullable(new Alle(1L, "celery", "apio"));
		Optional<Alle> alleFound = alleRepository.findById(1L);

		// Then
		Assert.assertEquals(alle1, alleFound);

	}
}
