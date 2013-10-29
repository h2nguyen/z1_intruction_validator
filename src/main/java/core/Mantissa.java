package core;

public class Mantissa {

	private int bits;
	
	private boolean[] boolArr;	

	public Mantissa(int bits) {
		this.bits = bits;		
		this.setBoolArr(new boolean[this.bits]);
	}
	
	public Mantissa(boolean[] man) {
		this.bits = man.length;		
		this.setBoolArr(man);
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
	 * @return the boolArr
	 */
	public boolean[] getBoolArr() {
		return boolArr;
	}

	/**
	 * @param boolArr the boolArr to set
	 */
	public void setBoolArr(boolean[] boolArr) {
		this.boolArr = boolArr;
	}

	public boolean[] getCopiedBoolArr() {
		boolean[] arr = new boolean[this.boolArr.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = this.boolArr[i];
		}
		return arr;
	}
	
}
