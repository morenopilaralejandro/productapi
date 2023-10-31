package com.product.api.exceptions;

public class ProdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProdNotFoundException(Long id) {
		super("Could not find prod " + id);
	}
}
