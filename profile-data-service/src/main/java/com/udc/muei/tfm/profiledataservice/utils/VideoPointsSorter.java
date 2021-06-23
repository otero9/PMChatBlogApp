package com.udc.muei.tfm.profiledataservice.utils;

import java.util.Comparator;

import com.udc.muei.tfm.profiledataservice.controller.video.VideoDTO;

/*
 * 
 * The Class VideoPointsSorter.
 * 
 * @author a.oteroc
 * 
 */
public class VideoPointsSorter implements Comparator<VideoDTO> {

	@Override
	public int compare(VideoDTO v1, VideoDTO v2) {
		int value = 0;
		if (v2.getPoints() < v1.getPoints())
			value = -1;
		if (v2.getPoints() > v1.getPoints())
			value = 1;
		if (v2.getPoints() == v1.getPoints())
			value = 0;
		return value;
	}

}
