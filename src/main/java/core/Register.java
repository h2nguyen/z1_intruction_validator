/**
 * 
 */
package core;

/**
 * @author hnguyen
 * 
 */
public class Register {

	protected BinaryNumber A;
	protected BinaryNumber B;

	public Register(BinaryNumber A, BinaryNumber B) {
		this.A = A;
		this.B = B;
	}

	/**
	 * @return the a
	 */
	public BinaryNumber getA() {
		return A;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(BinaryNumber a) {
		A = a;
	}

	/**
	 * @return the b
	 */
	public BinaryNumber getB() {
		return B;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(BinaryNumber b) {
		B = b;
	}

}
