package com.product.api.exceptions;

public class CntcstateNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CntcstateNotFoundException(Long id) {
		super("Could not find cntcstate " + id);
	}
}
