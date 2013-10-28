package core;

public class Exponent {

	private int bits;	
	private boolean[] boolArr;
	
	public Exponent(int bits) {
		this.bits = bits;
		this.boolArr = new boolean[this.bits];
	}
	
	public Exponent(boolean[] exp) {
		this.bits = exp.length;
		this.boolArr = exp;
	}

	/**
	 * @return the bits
	 */		
	public int getBits() {
		return bits;
	}

	/**
	 * @param bits the bits to set
	 */
	public void setBits(int bits) {
		this.bits = bits;
	}

	/**
	 * @return the exp
	 */
	public boolean[] getBoolArr() {
		return boolArr;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setBoolArr(boolean[] exp) {
		this.boolArr = exp;
	}
}
