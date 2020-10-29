package main;

import java.util.List;
import java.util.Scanner;

/*
 * Created By: Sherry Wang
 * 10/28/2020
 * 
 * Main Driver to read the input and return the result
 */

public class App {
	
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String zipCodeRanges = scanner.nextLine();
		
		// get valid range list
		ZipCodePreprocessor preprocessor = new ZipCodePreprocessor(zipCodeRanges);
		List<ZipCodeRange> zipcodeList = preprocessor.getZipCodeRangeList();
		
		// merging
		ZipCodeRangeMerger merger = new ZipCodeRangeMerger();
		List<ZipCodeRange> mergedRange = merger.merge(zipcodeList);
		
		// print out the result
		for (ZipCodeRange range : mergedRange) {
			System.out.println(range.toString());
		}
	}

}
