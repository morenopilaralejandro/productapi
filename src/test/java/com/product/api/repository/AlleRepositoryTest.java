package com.product.api.repository;

import java.util.List;
import java.util.NoSuchElementException;
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
@ContextConfiguration(initializers = AlleRepositoryTest.Initializer.class, classes = ApiApplication.class)
public class AlleRepositoryTest {

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
		int inserted = 1;
		List<Alle> found = alleRepository.findAll();
		
		// Then
		Assert.assertEquals(found.size(), rows+inserted);

	}

	@Test
	public void shouldGetOneAlle() {
		// Given && When
		Alle alle1 = new Alle(1L, "celery", "apio");
		Optional<Alle> alleFound = alleRepository.findById(1L);

		// Then
		Assert.assertEquals(alle1, alleFound.get());

	}
	
	@Test
	public void shouldCreateNewAlle() {
		// Given && When
		Alle alle1 = new Alle(null, "new", "new");
		Alle alleExpected = new Alle(15L, "new", "new");
		alleRepository.save(alle1);
		Optional<Alle> alleFound = alleRepository.findById(15L);
		
		// Then
		Assert.assertEquals(alleExpected, alleFound.get());

	}
	
	@Test
	public void shouldReplaceAlle() {
		// Given && When
		Alle alle1 = new Alle(1L, "update", "update");
		alleRepository.save(alle1);
		Optional<Alle> alleFound = alleRepository.findById(1L);
		
		// Then
		Assert.assertEquals(alle1, alleFound.get());

	}
	
	@Test(expected = NoSuchElementException.class)
	public void shouldDeleteAlle() {
		// Given && When
		Optional<Alle> alleFound = alleRepository.findById(15L);
		alleRepository.deleteById(alleFound.get().getAlleId());
		Optional<Alle> alleFound2 = alleRepository.findById(15L);
		
		// Then
		alleFound2.get();
	}
}
