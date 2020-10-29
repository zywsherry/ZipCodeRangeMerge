package main;

/*
 * Created By: Sherry Wang
 * 10/28/2020
 * 
 * Data Model for zip code range
 */
public class ZipCodeRange {
	private String lower;
	private String upper;
	
	public ZipCodeRange(String lower, String upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
	}

	@Override
	public String toString() {
		return "Range [" + lower + ", " + upper + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.parseInt(lower);
		result = prime * result + Integer.parseInt(upper);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZipCodeRange other = (ZipCodeRange) obj;
		if (lower != other.lower)
			return false;
		if (upper != other.upper)
			return false;
		return true;
	}
	
}
