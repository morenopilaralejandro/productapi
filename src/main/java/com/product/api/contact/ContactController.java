package com.product.api.contact;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.product.api.exceptions.ContactNotFoundException;

@RestController
public class ContactController {
	@Autowired
	private final ContactRepository contactRepository;

	ContactController(ContactRepository repository) {
		this.contactRepository = repository;
	}

	@GetMapping("/contact")
	List<Contact> all() {
		return contactRepository.findAll();
	}

	@PostMapping("/contact")
	Contact newProd(@RequestBody Contact newContact) {
		return contactRepository.save(newContact);
	}

	@GetMapping("/contact/{id}")
	Contact one(@PathVariable Long id) {
		return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
	}

	@PutMapping("/contact/{id}")
	Contact replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
		return contactRepository.findById(id).map(contact -> {
			contact.setContactName(newContact.getContactName());
			contact.setContactEmail(newContact.getContactEmail());
			contact.setContactMsg(newContact.getContactMsg());
			contact.setContactDate(newContact.getContactDate());
			contact.setCntcstate(newContact.getCntcstate());
			return contactRepository.save(contact);
		}).orElseGet(() -> {
			newContact.setContactId(id);
			return contactRepository.save(newContact);
		});
	}

	@DeleteMapping("/contact/{id}")
	void deleteContact(@PathVariable Long id) {
		contactRepository.deleteById(id);
	}
}
