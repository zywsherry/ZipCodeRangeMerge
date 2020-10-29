# ZipCodeRangeMerge

## Problem Statement
BACKGROUND
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

PROBLEM
Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

NOTES
- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

EXAMPLES:
If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399] 
Then the output should be = [94133,94133] [94200,94399]

Evaluation Guidelines:
Your work will be evaluated against the following criteria:
- Successful implementation
- Efficiency of the implementation
- Design choices and overall code organization
- Code quality and best practices


## Assumption
Input: Assumed to be a string like "[94133,94133] [94200,94299] [94600,94699]", should not be null or empty.
Output: A string represents the merged range.
Additional Assumption:
1. Zip Code should be 5 digits and can start with "0"
2. Zip Code Range should be valid, lower bounds must be smaller or equal to upper bounds
3. Zip Code Range should only consists of 2 value, otherwise an exception will be thrown.


## DataStructure Used
Used ArrayList to implement List Interface


## Approach
1. First split the input string to a list of ZipCodeRange (self-defined) Object and validating each range. Throw an IllegalArgumentException if assumption not satisfied.
2. Sort the ranges according to the lower bound value in ascending order.
3. Traverse the sorted list and compare each range's lower bound with the previous one's upper bound. Merge two ranges if an overlapping is found.
4. Otherwise, add the previous range into the result list.
5. Converting the list into a string using toString() method.


## File Description
1. App.java: Main Driver to take the input and print out the merged zip code range.
2. ZipCodeRange.java: Data model for zip code range consisting of the lower bound and upper bound. Both of type String considering zipcode starting with 0
3. ZipCodePreprocessor.java: Process the string input and convert it into a list of ZipCodeRange objects if all the constraints meeted.
4. ZipCodeRangeComparator.java: Set up the rules to compare the ranges by lower bound and return positive number if first element is larger.
5. ZipCodeRangeMerger.java: Main logic to merge the overlapping ranges.


## Test Cases:
With the help of JUnit5 using annotation including @Test, @BeforeEach and @AfterEach.
Testing three general cases: 
  1. No overlapping case (input:"[94133,94133] [94200,94299] [94600,94699]")
  2. Overlapping case (input: "[94133,94133] [94200,94299] [94226,94399]")
  3. ZipCode starting with "0" case. (input: "[00102,00210] [01201,01221] [01198,01220]")
Also test the exception throwing cases including: 
  1. input string is null or empty
  2. range has more/less than 2 bounds
  3. zip code is not consist of 5 digits
  4. the upper bound is smaller than the lower bound
  
  

## Time & Space Complexity
1. Time Complexity: O(NlogN) for sorting the list and 0(N) for traversing the list. Hence the result is 0(NlogN), where N is the number of ranges from input String.
2. Space Complexity: O(N) to store the list of ranges as well as the output list.
