package com.product.api.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
import com.product.api.contact.Contact;
import com.product.api.contact.ContactRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ContactRepositoryTest.Initializer.class, classes = ApiApplication.class)
public class ContactRepositoryTest {

	@ClassRule
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest").withInitScript("db/script.sql");
	@Autowired
	private ContactRepository contactRepository;
	
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
	public void shouldGetAllContact() {
		LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		now = now.truncatedTo(ChronoUnit.SECONDS);
		Cntcstate cntcstate = new Cntcstate();
		cntcstate.setCntcstateId(1L);
	
		Contact contact2 = new Contact(null, "name2", "aaa@aaa.com", "msg2", now, cntcstate);
		Contact contact3 = new Contact(null, "name3", "aaa@aaa.com", "msg3", now, cntcstate);
		Contact contact4 = new Contact(null, "name4", "aaa@aaa.com", "msg4", now, cntcstate);
		List<Contact> contactList = new ArrayList<>();
		contactList.add(contact2);
		contactList.add(contact3);
		contactList.add(contact4);
		
		contactRepository.saveAll(contactList);
		
		Contact contact5 = new Contact(2L, "name2", "aaa@aaa.com", "msg2", now, cntcstate);
		Contact contact6 = new Contact(3L, "name3", "aaa@aaa.com", "msg3", now, cntcstate);
		Contact contact7 = new Contact(4L, "name4", "aaa@aaa.com", "msg4", now, cntcstate);
		List<Contact> contactListExpected = new ArrayList<>();
		contactListExpected.add(contact5);
		contactListExpected.add(contact6);
		contactListExpected.add(contact7);
		
	
		List<Contact> found = contactRepository.findAll();
		
		Assert.assertEquals(found, contactListExpected);

	}

	@Test
	public void shouldGetOneContact() {
		LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		now = now.truncatedTo(ChronoUnit.SECONDS);
		Cntcstate cntcstate = new Cntcstate();
		cntcstate.setCntcstateId(1L);
		
		Contact contact2 = new Contact(2L, "name2", "aaa@aaa.com", "msg2", now, cntcstate);	
		Contact contactFound = contactRepository.findById(2L).get();
		
		Assert.assertEquals(contact2, contactFound);
		
	}
	
	@Test
	public void shouldCreateNewContact() {
		LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		now = now.truncatedTo(ChronoUnit.SECONDS);
		
		Cntcstate cntcstate = new Cntcstate();
		cntcstate.setCntcstateId(1L);
		
		Contact contact1 = new Contact(null, "name1", "aaa@aaa.com", "msg1", now, cntcstate);
		contactRepository.save(contact1);
		
		Contact contactExpected = new Contact(1L, "name1", "aaa@aaa.com", "msg1", now, cntcstate);
		Contact contactFound = contactRepository.findById(1L).get();

		Assert.assertEquals(contactExpected, contactFound);

	}
	
	@Test
	public void shouldReplaceContact() {
		LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		now = now.truncatedTo(ChronoUnit.SECONDS);
		
		Cntcstate cntcstate = new Cntcstate(2L, null);
		Contact contact2 = new Contact(2L, "update", "update", "update", now, cntcstate);
		
		contactRepository.save(contact2);
		Contact contactFound = contactRepository.findById(2L).get();
		
		Assert.assertEquals(contact2, contactFound);

	}
	
	@Test
	public void shouldDeleteContact() {
		Optional<Contact> contactFound = contactRepository.findById(1L);
		contactRepository.deleteById(contactFound.get().getContactId());
		Optional<Contact> contactFound2 = contactRepository.findById(1L);
		
		Assert.assertTrue(contactFound2.isEmpty());
	}
}
