package com.product.api.cntcstate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.product.api.contact.Contact;
import com.product.api.exceptions.CntcstateNotFoundException;

@RestController
public class CntcstateController {
	@Autowired
	private final CntcstateRepository cntcstateRepository;

	CntcstateController(CntcstateRepository repository) {
		this.cntcstateRepository = repository;
	}

	@GetMapping("/cntcstate")
	List<Cntcstate> all() {
		return cntcstateRepository.findAll();
	}

	@PostMapping("/cntcstate")
	Cntcstate newCntcstate(@RequestBody Cntcstate newCntcstate) {
		return cntcstateRepository.save(newCntcstate);
	}

	@GetMapping("/cntcstate/{id}")
	Cntcstate one(@PathVariable Long id) {
		return cntcstateRepository.findById(id).orElseThrow(() -> new CntcstateNotFoundException(id));
	}
	
	@GetMapping("/cntcstate/{id}/contactList")
	List<Contact> contactList(@PathVariable Long id) {
		return cntcstateRepository.findById(id).map(cntcstate -> {
			return cntcstate.getContactList();
		}).orElseThrow(() -> 
			new CntcstateNotFoundException(id)
		);
	}

	@PutMapping("/cntcstate/{id}")
	Cntcstate replaceCntcstate(@RequestBody Cntcstate newCntcstate, @PathVariable Long id) {
		return cntcstateRepository.findById(id).map(cntcstate -> {
			cntcstate.setCntcstateName(newCntcstate.getCntcstateName());
			return cntcstateRepository.save(cntcstate);
		}).orElseGet(() -> {
			newCntcstate.setCntcstateId(id);
			return cntcstateRepository.save(newCntcstate);
		});
	}

	@DeleteMapping("/cntcstate/{id}")
	void deleteCntcstate(@PathVariable Long id) {
		cntcstateRepository.deleteById(id);
	}
}
