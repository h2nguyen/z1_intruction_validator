package core;

import core.helpers.BinaryHelper;

public class Mantissa {

	private int manBits;
	
	private boolean[] man;	

	public Mantissa(int manBits) {
		this.manBits = manBits;		
		this.setMan(new boolean[this.manBits]);
	}
	
	public Mantissa(boolean[] man) {
		this.manBits = man.length;		
		this.setMan(man);
	}

	/**
	 * @return the manBits
	 */
	public int getManBits() {
		return manBits;
	}

	/**
	 * @param manBits the manBits to set
	 */
	public void setManBits(int manBits) {
		this.manBits = manBits;
	}

	/**
	 * @return the man
	 */
	public boolean[] getMan() {
		return man;
	}

	/**
	 * @param man the man to set
	 */
	public void setMan(boolean[] man) {
		this.man = man;
	}
	
}
