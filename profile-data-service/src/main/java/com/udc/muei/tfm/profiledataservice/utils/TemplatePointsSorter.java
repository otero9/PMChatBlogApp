package com.udc.muei.tfm.profiledataservice.utils;

import java.util.Comparator;

import com.udc.muei.tfm.profiledataservice.controller.template.TemplateDTO;

public class TemplatePointsSorter implements Comparator<TemplateDTO> {

	@Override
	public int compare(TemplateDTO t1, TemplateDTO t2) {
		int value = 0;
		if (t2.getPoints() < t1.getPoints())
			value = -1;
		if (t2.getPoints() > t1.getPoints())
			value = 1;
		if (t2.getPoints() == t1.getPoints())
			value = 0;
		return value;
	}

}
