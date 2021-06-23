package com.udc.muei.tfm.profiledataservice.utils;

import java.util.Comparator;

import com.udc.muei.tfm.profiledataservice.controller.blog.BlogDTO;

/*
 * 
 * The Class BlogPointsSorter.
 * 
 * @author a.oteroc
 * 
 */
public class BlogPointsSorter implements Comparator<BlogDTO> {

	@Override
	public int compare(BlogDTO b1, BlogDTO b2) {
		int value = 0;
		if (b2.getPoints() < b1.getPoints())
			value = -1;
		if (b2.getPoints() > b1.getPoints())
			value = 1;
		if (b2.getPoints() == b1.getPoints())
			value = 0;
		return value;
	}

}
