package core;

public class Exponent {

	private int expBits;	
	private boolean[] expBoolArr;
	
	public Exponent(int expBits) {
		this.expBits = expBits;
		this.expBoolArr = new boolean[this.expBits];
	}
	
	public Exponent(boolean[] exp) {
		this.expBits = exp.length;
		this.expBoolArr = exp;
	}

	/**
	 * @return the expBits
	 */		
	public int getExpBits() {
		return expBits;
	}

	/**
	 * @param expBits the expBits to set
	 */
	public void setExpBits(int expBits) {
		this.expBits = expBits;
	}

	/**
	 * @return the exp
	 */
	public boolean[] getExpBoolArr() {
		return expBoolArr;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExpBoolArr(boolean[] exp) {
		this.expBoolArr = exp;
	}
}
