package test;


/*
 * Created By: Sherry Wang
 * 10/28/2020
 * 
 * Unit Tests
 */


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.ZipCodePreprocessor;
import main.ZipCodeRange;
import main.ZipCodeRangeMerger;

class ZipCodeRangeMergeTests {

	List<ZipCodeRange> zipRanges;  // input
	List<ZipCodeRange> actual;     // actual output
	ZipCodePreprocessor zipCodePreprocessor;
	ZipCodeRangeMerger merger;
	
	@BeforeEach
	public void setup(){
		System.out.println("Setting Up");
		zipRanges = new ArrayList<>();
		actual = new ArrayList<>();
		zipCodePreprocessor = new ZipCodePreprocessor();
		merger = new ZipCodeRangeMerger();
	}
	
	
	@Test
	public void testCase1() {
		/*
		 * General Test Case 1:
		 * input: [94133,94133] [94200,94299] [94600,94699]
		 * output: [94133,94133] [94200,94299] [94600,94699]
		 */
		zipRanges.add(new ZipCodeRange("94133","94133"));
		zipRanges.add(new ZipCodeRange("94200","94299"));
		zipRanges.add(new ZipCodeRange("94600","94699"));
		actual = zipRanges;   // output is the same as input
		List<ZipCodeRange> obtained = merger.merge(zipRanges);
		Assertions.assertEquals(obtained, actual);
	}
	
	
	
	@Test
	public void testCase2() {
		/*
		 * General Test Case 2:
		 * input: [94133,94133] [94200,94299] [94226,94399] 
		 * output: [94133,94133] [94200,94399]	
		 */
	
		zipRanges.add(new ZipCodeRange("94133","94133"));
		zipRanges.add(new ZipCodeRange("94200","94299"));
		zipRanges.add(new ZipCodeRange("94226","94399"));
		actual.add(new ZipCodeRange("94133","94133"));
		actual.add(new ZipCodeRange("94200","94399"));

		List<ZipCodeRange> obtained = merger.merge(zipRanges);
		Assertions.assertEquals(obtained, actual);
	}
	
	
	@Test
	public void testCase3() {
		/*
		 * General Test Case 3: Considering Zipcode starting with 0
		 * input: [00102,00210] [01201,01221] [01198,01220] 
		 * output: [00102,00210] [01198,01221]	
		 */
	
		zipRanges.add(new ZipCodeRange("00102","00210"));
		zipRanges.add(new ZipCodeRange("01201","01221"));
		zipRanges.add(new ZipCodeRange("01198","01220"));
		actual.add(new ZipCodeRange("00102","00210"));
		actual.add(new ZipCodeRange("01198","01221"));

		List<ZipCodeRange> obtained = merger.merge(zipRanges);
		Assertions.assertEquals(obtained, actual);
	}


	
	
	@Test
	public void testNullInput() {
		/*
		 * Test Case when input string is null
		 * should throw illegalArgumentException
		 */
		System.out.println(
				"should throw illegalArgumentException if input is null");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			zipCodePreprocessor.setZipCodeRanges(null).getZipCodeRangeList();
		});
	}
	
	
	
	@Test
	public void testInputOfLength0() {
		/*
		 * Test Case when input is empty string
		 * should throw illegalArgumentException
		 */
		System.out.println(
				"should throw illegalArgumentException if input is of length 0");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			zipCodePreprocessor.setZipCodeRanges("").getZipCodeRangeList();
		});
	}
	
	
	
	@Test
	public void testIllegalRangeBoundNumber1() {
		/*
		 * Test Case when input contains range with three zipCode
		 * input: [94133,94133,94299] [94226,94399] 
		 * should throw illegalArgumentException
		 */
		System.out.println(
				"should throw illegalArgumentException if the bound number is not 2.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			zipCodePreprocessor.setZipCodeRanges("[94133,94133,94299] [94226,94399]").getZipCodeRangeList();
		});
	}
	
	
	
	@Test
	public void testIllegalRangeBoundNumber2() {
		/*
		 * Test Case when input contains range with one zipCode
		 * input: [94133] [94200,94299] [94226,94399] 
		 * should throw illegalArgumentException
		 */
		System.out.println(
				"should throw illegalArgumentException if the bound number is not 2.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			zipCodePreprocessor.setZipCodeRanges("[94133] [94200,94299] [94226,94399]").getZipCodeRangeList();
		});
	}
	
	
	
	@Test
	public void testIllegalZipCodeDigit() {
		/*
		 * Test Case when input contains zipCode with digits number != 5
		 * input: [942010,94299] [94226,94399] 
		 * should throw illegalArgumentException
		 */
		System.out.println(
				"should throw illegalArgumentException if the zipcode is not 5-digits.");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			zipCodePreprocessor.setZipCodeRanges("[942010,94299] [94226,94399]").getZipCodeRangeList();
		});
	}
	
	
	
	@Test
	public void testIllegalZipCodeRange() {
		/*
		 * Test Case when input contains ranges which upperLimit < lowerLimit
		 * input: [94299,94200] [94226,94399] 
		 * should throw illegalArgumentException
		 */
		System.out.println(
				"should throw illegalArgumentException if upperBound is smaller than lowerBound");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			zipCodePreprocessor.setZipCodeRanges("[94299,94200] [94226,94399]").getZipCodeRangeList();
		});
	}
	

	
	@AfterEach
	public void teardown(){
		System.out.println("Tearing Down");
		zipRanges.clear();
		actual.clear();
		zipCodePreprocessor = new ZipCodePreprocessor();
		merger = new ZipCodeRangeMerger();
	}

}
