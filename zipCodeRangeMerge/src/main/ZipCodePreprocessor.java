package main;

import java.util.ArrayList;
import java.util.List;

/*
 * Created By: Sherry Wang
 * 10/28/2020
 * 
 * Validating the input. If validated, load the input string to a list.
 * Otherwise, throw IllegalArgumentException exception
 */

public class ZipCodePreprocessor {
	
	private String zipCodeRanges;
	
	public ZipCodePreprocessor() {	}
	
	public ZipCodePreprocessor(String zipCodeRanges) {
		this.zipCodeRanges = zipCodeRanges;
	}

	public ZipCodePreprocessor setZipCodeRanges(String zipCodeRanges) {
		this.zipCodeRanges = zipCodeRanges;
		return this;
	}

	public List<ZipCodeRange> getZipCodeRangeList(){
		if (zipCodeRanges == null || zipCodeRanges.length() == 0) {
			throw new IllegalArgumentException("Illegal Input: Input can not be Empty!");
		}
		
		String[] rangeIntervals = zipCodeRanges.trim().split(" ");
		List<ZipCodeRange> zipCodeRangeList = new ArrayList<>();
		
		for (String intervalString : rangeIntervals) {
			String[] rangeInterval = getRangeInterval(intervalString);
			zipCodeRangeList.add(rangeValidator(rangeInterval));
		}
		return zipCodeRangeList;
	}
	
	
	public String[] getRangeInterval(String intervalString) {
		return intervalString.replaceAll("\\[|\\]", "").split(",");
	}
	
	
	public ZipCodeRange rangeValidator(String[] rangeInterval) {
		if(rangeInterval.length != 2) {
			throw new IllegalArgumentException(rangeInterval + ": Illegal range bound number! Should be 2.");
		}
		
		String lowerBound = rangeInterval[0];
		String upperBound = rangeInterval[1];
		ZipCodeRange zipcodeRange = null;
		if (isDigitsValid(lowerBound) && isDigitsValid(upperBound) 
				&& isValidRange(lowerBound, upperBound)) {
			zipcodeRange = new ZipCodeRange(lowerBound, upperBound);
		}
		return zipcodeRange;
	}
	
	
	public boolean isDigitsValid(String zipCode) {
		if (zipCode.length() != 5) {
			throw new IllegalArgumentException(zipCode + ": Illegal digits number! Should be 5.");
		}
		return true;
	}
	
	
	public boolean isValidRange(String lowerBound, String upperBound) {
		// lower bound greater than upper bound
		if (lowerBound.compareTo(upperBound) > 0) {
			throw new IllegalArgumentException("["+lowerBound+","+upperBound+"]" 
					+": Illegal range! Lower bound should NOT be greater than upper bound.");
		}
		return true;
	}

}
