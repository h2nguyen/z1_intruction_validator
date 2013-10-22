package core;

public class Exponent {

	private int expBits;	
	private boolean[] exp;
	
	public Exponent(int expBits) {
		this.expBits = expBits;
		this.exp = new boolean[this.expBits];
	}
	
	public Exponent(boolean[] exp) {
		this.expBits = exp.length;
		this.exp = exp;
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
	public boolean[] getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(boolean[] exp) {
		this.exp = exp;
	}
}
