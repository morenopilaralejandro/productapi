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
import com.product.api.cntcstate.Cntcstate;
import com.product.api.cntcstate.CntcstateRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = CntcstateRepositoryTest.Initializer.class, classes = ApiApplication.class)
public class CntcstateRepositoryTest {

	@ClassRule
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest").withInitScript("db/script.sql");
	@Autowired
	private CntcstateRepository cntcstateRepository;
	
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
	public void shouldGetAllCntcstate() {
		Cntcstate cntcstate1 = new Cntcstate(1L, "delivered");
		Cntcstate cntcstate2 = new Cntcstate(2L, "opened");
		Cntcstate cntcstate3 = new Cntcstate(3L, "clicked");
		Cntcstate cntcstate4 = new Cntcstate(4L, "unsubscribed");
		Cntcstate cntcstate5 = new Cntcstate(5L, "spam");
		Cntcstate cntcstate6 = new Cntcstate(6L, "bounced");
		Cntcstate cntcstate7 = new Cntcstate(7L, "blocked");
		Cntcstate cntcstate8 = new Cntcstate(8L, "retrying");
		List<Cntcstate> cntcstateList = new ArrayList<>();
		cntcstateList.add(cntcstate1);
		cntcstateList.add(cntcstate2);
		cntcstateList.add(cntcstate3);
		cntcstateList.add(cntcstate4);
		cntcstateList.add(cntcstate5);
		cntcstateList.add(cntcstate6);
		cntcstateList.add(cntcstate7);
		cntcstateList.add(cntcstate8);
		
		List<Cntcstate> found = cntcstateRepository.findAll();
		
		Assert.assertEquals(found, cntcstateList);

	}

	@Test
	public void shouldGetOneCntcstate() {
		Cntcstate cntcstate1 = new Cntcstate(1L, "delivered");
		Cntcstate cntcstateFound = cntcstateRepository.findById(1L).get();

		Assert.assertEquals(cntcstate1, cntcstateFound);

	}
	
	@Test
	public void shouldCreateNewCntcstate() {
		Cntcstate cntcstate1 = new Cntcstate(null, "new");
		Cntcstate cntcstateExpected = new Cntcstate(9L, "new");
		cntcstateRepository.save(cntcstate1);
		Cntcstate cntcstateFound = cntcstateRepository.findById(9L).get();
		
		Assert.assertEquals(cntcstateExpected, cntcstateFound);

	}
	
	@Test
	public void shouldReplaceCntcstate() {
		Cntcstate cntcstate1 = new Cntcstate(1L, "update");
		cntcstateRepository.save(cntcstate1);
		Cntcstate cntcstateFound = cntcstateRepository.findById(1L).get();
		
		Assert.assertEquals(cntcstate1, cntcstateFound);

	}
	
	@Test
	public void shouldDeleteCntcstate() {
		Optional<Cntcstate> cntcstateFound = cntcstateRepository.findById(9L);
		cntcstateRepository.deleteById(cntcstateFound.get().getCntcstateId());
		Optional<Cntcstate> cntcstateFound2 = cntcstateRepository.findById(9L);

		Assert.assertTrue(cntcstateFound2.isEmpty());
	}
}
