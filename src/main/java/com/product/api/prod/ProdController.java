package com.product.api.prod;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.product.api.exceptions.ProdNotFoundException;

@RestController
public class ProdController {
	@Autowired
	private final ProdRepository prodRepository;

	ProdController(ProdRepository repository) {
		this.prodRepository = repository;
	}

	@GetMapping("/prod")
	List<Prod> all() {
		return prodRepository.findAll();
	}

	@PostMapping("/prod")
	Prod newProd(@RequestBody Prod newProd) {
		return prodRepository.save(newProd);
	}

	@GetMapping("/prod/{id}")
	Prod one(@PathVariable Long id) {
		return prodRepository.findById(id).orElseThrow(() -> new ProdNotFoundException(id));
	}

	@PutMapping("/prod/{id}")
	Prod replaceProd(@RequestBody Prod newProd, @PathVariable Long id) {
		return prodRepository.findById(id).map(prod -> {
			prod.setProdNameEn(newProd.getProdNameEn());
			prod.setProdNameEs(newProd.getProdNameEs());
			prod.setProdDescEn(newProd.getProdDescEn());
			prod.setProdDescEs(newProd.getProdDescEs());
			prod.setProdPrice(newProd.getProdPrice());
			prod.setCat(newProd.getCat());
			return prodRepository.save(prod);
		}).orElseGet(() -> {
			newProd.setProdId(id);
			return prodRepository.save(newProd);
		});
	}

	@DeleteMapping("/prod/{id}")
	void deleteProd(@PathVariable Long id) {
		prodRepository.deleteById(id);
	}
}
