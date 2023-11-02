package com.product.api.alle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.exceptions.AlleNotFoundException;

@RestController
public class AlleController {
	@Autowired
	private final AlleRepository alleRepository;

	AlleController(AlleRepository repository) {
		this.alleRepository = repository;
	}

	@GetMapping("/alle")
	List<Alle> all() {
		return alleRepository.findAll();
	}

	@PostMapping("/alle")
	Alle newAlle(@RequestBody Alle newAlle) {
		return alleRepository.save(newAlle);
	}

	@GetMapping("/alle/{id}")
	Alle one(@PathVariable Long id) {
		return alleRepository.findById(id).orElseThrow(() -> new AlleNotFoundException(id));
	}

	@PutMapping("/alle/{id}")
	Alle replaceAlle(@RequestBody Alle newAlle, @PathVariable Long id) {
		return alleRepository.findById(id).map(alle -> {
			alle.setAlleNameEn(newAlle.getAlleNameEn());
			alle.setAlleNameEs(newAlle.getAlleNameEs());
			return alleRepository.save(alle);
		}).orElseGet(() -> {
			newAlle.setAlleId(id);
			return alleRepository.save(newAlle);
		});
	}

	@DeleteMapping("/alle/{id}")
	void deleteAlle(@PathVariable Long id) {
		alleRepository.deleteById(id);
	}
}
