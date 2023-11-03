package com.product.api.exceptions;

public class CatNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CatNotFoundException(Long id) {
		super("Could not find cat " + id);
	}
}
