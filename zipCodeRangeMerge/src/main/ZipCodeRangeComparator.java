package main;

/*
 * Created By: Sherry Wang
 * 10/28/2020
 * 
 * Compare the ranges by lower bound and sort in ascending order
 */

import java.util.Comparator;

public class ZipCodeRangeComparator implements Comparator<ZipCodeRange>{
	
	public int compare(ZipCodeRange z1, ZipCodeRange z2) {
		return z1.getLower().compareTo(z2.getLower());
	}

}
