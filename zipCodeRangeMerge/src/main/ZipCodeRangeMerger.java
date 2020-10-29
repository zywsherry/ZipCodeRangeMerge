package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Created By: Sherry Wang
 * 10/28/2020
 * 
 * Merge the overlapping zip code ranges
 */

public class ZipCodeRangeMerger {
	
	public List<ZipCodeRange> merge(List<ZipCodeRange> zipRangeLists){
		List<ZipCodeRange> merged = new ArrayList<>();
		
		if (zipRangeLists == null || zipRangeLists.size() == 0) {
			return merged;
		}
		
		// sort in ascending order according to the lower bound 
		Collections.sort(zipRangeLists, new ZipCodeRangeComparator());
		
		String lower = zipRangeLists.get(0).getLower();
		String upper = zipRangeLists.get(0).getUpper();
		
		for (ZipCodeRange zipRange : zipRangeLists){
			// overlap found  
			if (zipRange.getLower().compareTo(upper) <= 0) {
				// update the upper limit with higher value
				upper = (zipRange.getUpper().compareTo(upper)>0)? zipRange.getUpper():upper;
			}
			// gap between intervals, add previous one to the result
			else {
				merged.add(new ZipCodeRange(lower, upper));
				lower = zipRange.getLower();
				upper = zipRange.getUpper();
			}
		}
		merged.add(new ZipCodeRange(lower, upper));
		
		return merged;
	}

}
