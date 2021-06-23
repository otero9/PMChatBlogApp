package com.udc.muei.tfm.profiledataservice.utils;

import java.util.Comparator;

import com.udc.muei.tfm.profiledataservice.controller.user.UserDTO;

/*
 * 
 * The Class UserPointsSorter.
 * 
 * @author a.oteroc
 * 
 */
public class UserPointsSorter implements Comparator<UserDTO> {

	@Override
	public int compare(UserDTO u1, UserDTO u2) {
		int value = 0;
		if (u2.getPoints() < u1.getPoints())
			value = -1;
		if (u2.getPoints() > u1.getPoints())
			value = 1;
		if (u2.getPoints() == u1.getPoints())
			value = 0;
		return value;
	}

}
