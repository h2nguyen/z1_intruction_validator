package core;

public class Processor {

	protected Register F;
	protected Register G;
	
	public Processor() {
		this.F = new Register("F", new Exponent("Af", new BinaryNumber(0)), new Mantissa("Bf", new BinaryFloatingPointNumber(0)));
		this.G = new Register("G", new Exponent("Ag", new BinaryNumber(0)), new Mantissa("Bg", new BinaryFloatingPointNumber(0)));
	}
}
