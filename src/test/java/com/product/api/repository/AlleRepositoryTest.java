package com.product.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
		Alle alle1 = new Alle(1L, "celery", "apio");
		Alle alle2 = new Alle(2L, "crustaceans", "crustaceos");
		Alle alle3 = new Alle(3L, "eggs", "huevos");
		Alle alle4 = new Alle(4L, "fish", "pescado");
		Alle alle5 = new Alle(5L, "gluten", "gluten");
		Alle alle6 = new Alle(6L, "lupin", "altramuz");
		Alle alle7 = new Alle(7L, "milk", "lácteos");
		Alle alle8 = new Alle(8L, "moluscs", "moluscos");
		Alle alle9 = new Alle(9L, "mustard", "mostaza");
		Alle alle10 = new Alle(10L, "nuts", "frutos secos");
		Alle alle11 = new Alle(11L, "peanuts", "cacahuetes");
		Alle alle12 = new Alle(12L, "sesame", "sésamo");
		Alle alle13 = new Alle(13L, "soya", "soja");
		Alle alle14 = new Alle(14L, "sulphites", "sulfitos");
		List<Alle> alleList = new ArrayList<>();
		alleList.add(alle1);
		alleList.add(alle2);
		alleList.add(alle3);
		alleList.add(alle4);
		alleList.add(alle5);
		alleList.add(alle6);
		alleList.add(alle7);
		alleList.add(alle8);
		alleList.add(alle9);
		alleList.add(alle10);
		alleList.add(alle11);
		alleList.add(alle12);
		alleList.add(alle13);
		alleList.add(alle14);
	
		List<Alle> found = alleRepository.findAll();
		
		Assert.assertEquals(found, alleList);

	}

	@Test
	public void shouldGetOneAlle() {
		Alle alle1 = new Alle(1L, "celery", "apio");
		Alle alleFound = alleRepository.findById(1L).get();

		Assert.assertEquals(alle1, alleFound);
		
	}
	
	@Test
	public void shouldCreateNewAlle() {
		Alle alle1 = new Alle(null, "new", "new");
		Alle alleExpected = new Alle(15L, "new", "new");
		alleRepository.save(alle1);
		Optional<Alle> alleFound = alleRepository.findById(15L);

		Assert.assertEquals(alleExpected, alleFound.get());

	}
	
	@Test
	public void shouldReplaceAlle() {
		Alle alle1 = new Alle(1L, "update", "update");
		alleRepository.save(alle1);
		Optional<Alle> alleFound = alleRepository.findById(1L);
		
		Assert.assertEquals(alle1, alleFound.get());

	}
	
	@Test
	public void shouldDeleteAlle() {
		Optional<Alle> alleFound = alleRepository.findById(15L);
		alleRepository.deleteById(alleFound.get().getAlleId());
		Optional<Alle> alleFound2 = alleRepository.findById(15L);
		
		Assert.assertTrue(alleFound2.isEmpty());
	}
}
