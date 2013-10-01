package core;

import core.helpers.BinaryHelper;

public class Exponent {

	private BinaryNumber Aa;
	private BinaryNumber Ab;
	private BinaryNumber Ae;
	
	public Exponent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the aa
	 */
	public BinaryNumber getAa() {
		return Aa;
	}

	/**
	 * @param aa the aa to set
	 */
	public void setAa(BinaryNumber aa) {
		Aa = aa;
	}

	/**
	 * @return the ab
	 */
	public BinaryNumber getAb() {
		return Ab;
	}

	/**
	 * @param ab the ab to set
	 */
	public void setAb(BinaryNumber ab) {
		Ab = ab;
	}

	/**
	 * @return the ae
	 */
	public BinaryNumber getAe() {
		return Ae;
	}
	
	/**
	 * @return the negative ae
	 */
	public BinaryNumber getNegativeAe() {
		return Ae.getNegativeAs2sComplement();
	}

	/**
	 * @param ae the ae to set
	 */
	public void setAe(BinaryNumber ae) {
		Ae = ae;
	}

	private void addAaAe() {
		Ae = Aa.add(Ab);
	}
	
}
