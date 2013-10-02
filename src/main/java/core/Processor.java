package core;

import core.helpers.BinaryHelper;

public class Processor {

	private Register F;
	private Register G;
	private Exponent exp;
	private Mantissa man;
	
	public Processor() {
		this.F = new Register(new BinaryNumber(0), new BinaryNumber(0));
		this.G = new Register(new BinaryNumber(0), new BinaryNumber(0));
		this.man = new Mantissa();
		this.exp = new Exponent();
	}
	
	private BinaryNumber getAgNegative() {
		return this.G.getA().getNegativeAs2sComplement();
	}

	private BinaryNumber getExponent(BinaryNumber bin) {
		int decExp = 10;
		
		return new BinaryNumber(BinaryHelper.convDecIntegerToBinInteger(decExp));
	}
}
