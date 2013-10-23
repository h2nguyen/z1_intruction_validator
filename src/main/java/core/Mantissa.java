package core;

public class Mantissa {

	private int manBits;
	
	private boolean[] manBoolArr;	

	public Mantissa(int manBits) {
		this.manBits = manBits;		
		this.setManBoolArr(new boolean[this.manBits]);
	}
	
	public Mantissa(boolean[] man) {
		this.manBits = man.length;		
		this.setManBoolArr(man);
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
	 * @return the manBoolArr
	 */
	public boolean[] getManBoolArr() {
		return manBoolArr;
	}

	/**
	 * @param manBoolArr the manBoolArr to set
	 */
	public void setManBoolArr(boolean[] manBoolArr) {
		this.manBoolArr = manBoolArr;
	}


	
}
