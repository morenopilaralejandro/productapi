package com.product.api.exceptions;

public class ContactNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ContactNotFoundException(Long id) {
		super("Could not find contact " + id);
	}
}
