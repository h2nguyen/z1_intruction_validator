/**
 * 
 */
package core;

/**
 * @author hnguyen
 * 
 */
public class Register {

	protected String name;
	protected Mantissa mantissa;
	protected Exponent exponent;

	public Register(String name, Exponent A, Mantissa B) {
		this.name = name;
		this.exponent = A;
		this.mantissa = B;
	}
}
