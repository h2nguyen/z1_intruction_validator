package core;

public class Processor {

	private Register F;
	private Register G;
	private Exponent exp;
	private Mantissa man;
	
	public Processor() {
		this.F = new Register(new BinaryNumber(0), new BinaryNumber(0));
		this.G = new Register(new BinaryNumber(0), new BinaryNumber(0));
	}

}
