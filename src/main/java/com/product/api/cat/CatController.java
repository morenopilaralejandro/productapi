package com.product.api.cat;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.exceptions.CatNotFoundException;
import com.product.api.prod.Prod;

@RestController
public class CatController {
	@Autowired
	private final CatRepository catRepository;

	CatController(CatRepository repository) {
		this.catRepository = repository;
	}

	@GetMapping("/cat")
	List<Cat> all() {
		return catRepository.findAll();
	}

	@PostMapping("/cat")
	Cat newProd(@RequestBody Cat newProd) {
		return catRepository.save(newProd);
	}

	@GetMapping("/cat/{id}")
	Cat one(@PathVariable Long id) {
		return catRepository.findById(id).orElseThrow(() -> new CatNotFoundException(id));
	}

	@GetMapping("/cat/{id}/products")
	List<Prod> products(@PathVariable Long id) {
		return catRepository.findById(id).map(cat -> {
			return cat.getProducts();
		}).orElseThrow(() -> 
			new CatNotFoundException(id)
		);
	}

	@PutMapping("/cat/{id}")
	Cat replaceCat(@RequestBody Cat newCat, @PathVariable Long id) {
		return catRepository.findById(id).map(cat -> {
			cat.setCatNameEn(newCat.getCatNameEn());
			cat.setCatNameEs(newCat.getCatNameEs());
			return catRepository.save(cat);
		}).orElseGet(() -> {
			newCat.setCatId(id);
			return catRepository.save(newCat);
		});
	}

	@DeleteMapping("/cat/{id}")
	void deleteCat(@PathVariable Long id) {
		catRepository.deleteById(id);
	}
}
