package com.product.api.exceptions;

public class AlleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AlleNotFoundException(Long id) {
		super("Could not find alle " + id);
	}
}
