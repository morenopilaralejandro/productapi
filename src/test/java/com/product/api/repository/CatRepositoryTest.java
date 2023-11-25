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
import com.product.api.cat.Cat;
import com.product.api.cat.CatRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = CatRepositoryTest.Initializer.class, classes = ApiApplication.class)
public class CatRepositoryTest {

	@ClassRule
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest").withInitScript("db/script.sql");
	@Autowired
	private CatRepository catRepository;
	
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
	public void shouldGetAllCat() {
		Cat cat1 = new Cat(1L, "en1", "es1");
		Cat cat2 = new Cat(2L, "en2", "es2");
		Cat cat3 = new Cat(3L, "en3", "es3");
		List<Cat> catList = new ArrayList<>();
		catList.add(cat1);
		catList.add(cat2);
		catList.add(cat3);
		
		List<Cat> found = catRepository.findAll();
		
		Assert.assertEquals(found, catList);

	}

	@Test
	public void shouldGetOneCat() {
		Cat cat1 = new Cat(1L, "en1", "es1");
		Cat catFound = catRepository.findById(1L).get();

		Assert.assertEquals(cat1, catFound);

	}
	
	@Test
	public void shouldCreateNewCat() {
		Cat cat1 = new Cat(null, "new", "new");
		Cat alleExpected = new Cat(4L, "new", "new");
		catRepository.save(cat1);
		Cat catFound = catRepository.findById(4L).get();
		
		Assert.assertEquals(alleExpected, catFound);

	}
	
	@Test
	public void shouldReplaceCat() {
		Cat cat1 = new Cat(1L, "update", "update");
		catRepository.save(cat1);
		Cat catFound = catRepository.findById(1L).get();
		
		Assert.assertEquals(cat1, catFound);

	}
	
	@Test
	public void shouldDeleteCat() {
		Optional<Cat> catFound = catRepository.findById(4L);
		catRepository.deleteById(catFound.get().getCatId());
		Optional<Cat> catFound2 = catRepository.findById(4L);
		
		Assert.assertTrue(catFound2.isEmpty());
	}
}