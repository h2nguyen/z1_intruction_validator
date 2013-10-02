package core;

public class Mantissa {

	private BinaryNumber Ba;
	private BinaryNumber Bb;
	private BinaryNumber Be;
	
	public Mantissa() {
		// TODO Auto-generated constructor stub
	}
	
	private void addBaBb() {
		Be = Ba.add(Bb);
	}

	/**
	 * @return the ba
	 */
	public BinaryNumber getBa() {
		return Ba;
	}

	/**
	 * @param ba the ba to set
	 */
	public void setBa(BinaryNumber ba) {
		Ba = ba;
	}

	/**
	 * @return the bb
	 */
	public BinaryNumber getBb() {
		return Bb;
	}

	/**
	 * @param bb the bb to set
	 */
	public void setBb(BinaryNumber bb) {
		Bb = bb;
	}

	/**
	 * @return the be
	 */
	public BinaryNumber getBe() {
		addBaBb();
		return Be;
	}

	/**
	 * @param be the be to set
	 */
	public void setBe(BinaryNumber be) {
		Be = be;
	}
	
	public BinaryNumber getBeNegative() {
		return Be.getNegativeAs2sComplement();
	}
	
	public BinaryNumber getBeHalf() {
		return Be.shiftRight1();
	}
	
	public BinaryNumber getBeForth() {
		return Be.shiftRight2();
	}
	
	public BinaryNumber getBeDouble() {
		return Be.shiftLeft1();
	}
	
	public BinaryNumber getBeEightTimes() {
		return Be.shiftLeft3();
	}

}
