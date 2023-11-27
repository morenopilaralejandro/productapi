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
import com.product.api.prod.Prod;
import com.product.api.prod.ProdRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ProdRepositoryTest.Initializer.class, classes = ApiApplication.class)
public class ProdRepositoryTest {

	@ClassRule
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest").withInitScript("db/script.sql");
	@Autowired
	private ProdRepository prodRepository;
	
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
	public void shouldGetAllProd() {
		Cat cat1 = new Cat();
		cat1.setCatId(1L);
		Cat cat2 = new Cat();
		cat2.setCatId(2L);
		Cat cat3 = new Cat();
		cat3.setCatId(3L);
		
		Prod prod1 = new Prod(1L, "en1", "es1", "des1", "des1", 0.0f, cat1);
		Prod prod2 = new Prod(2L, "en2", "es2", "des2", "des2", 0.0f, cat1);
		Prod prod3 = new Prod(3L, "en3", "es3", "des3", "des3", 0.0f, cat2);
		Prod prod4 = new Prod(4L, "en4", "es4", "des4", "des4", 0.0f, cat3);
		List<Prod> prodList = new ArrayList<>();
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		prodList.add(prod4);
		
		List<Prod> found = prodRepository.findAll();
		
		Assert.assertEquals(found, prodList);

	}

	@Test
	public void shouldGetOneProd() {
		Cat cat1 = new Cat();
		cat1.setCatId(1L);
		Prod prod1 = new Prod(1L, "en1", "es1", "des1", "des1", 0.0f, cat1);
		Prod prodFound = prodRepository.findById(1L).get();

		Assert.assertEquals(prod1, prodFound);
		
	}
	
	@Test
	public void shouldCreateNewProd() {
		Cat cat1 = new Cat();
		cat1.setCatId(1L);
		Prod prod1 = new Prod(null, "en5", "es5", "des5", "des5", 0.0f, cat1);
		Prod prodExpected = new Prod(5L, "en5", "es5", "des5", "des5", 0.0f, cat1);

		prodRepository.save(prod1);
		Prod prodFound = prodRepository.findById(5L).get();

		Assert.assertEquals(prodExpected, prodFound);

	}
	
	@Test
	public void shouldReplaceProd() {
		Cat cat2 = new Cat();
		cat2.setCatId(2L);
		Prod prod1 = new Prod(1L, "update", "update", "update", "update", 0.0f, cat2);
		
		prodRepository.save(prod1);
		Prod prodFound = prodRepository.findById(1L).get();
		
		Assert.assertEquals(prod1, prodFound);

	}
	
	@Test
	public void shouldDeleteProd() {
		Optional<Prod> prodFound = prodRepository.findById(5L);
		prodRepository.deleteById(prodFound.get().getProdId());
		Optional<Prod> prodFound2 = prodRepository.findById(5L);
		
		Assert.assertTrue(prodFound2.isEmpty());
	}
}
