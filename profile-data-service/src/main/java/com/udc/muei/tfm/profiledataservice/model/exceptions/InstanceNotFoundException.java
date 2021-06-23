package com.udc.muei.tfm.profiledataservice.model.exceptions;

/*
 * 
 * The Class InstanceNotFoundException.
 * 
 * @author a.oteroc
 * 
 */
@SuppressWarnings("serial")
public class InstanceNotFoundException extends InternalErrorException {

	/**
	 * Instantiates a new instance not found exception.
	 *
	 * @param message the message
	 */
	public InstanceNotFoundException(String message) {
		super(message);
	}

}