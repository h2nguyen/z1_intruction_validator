package core;

import core.helpers.BinaryHelper;

public class Addition extends Processor {
	
	public Addition() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
		phase0(Af(),negAg());
		Ae = phase1(Aa,Ab);
		
		if(BinaryHelper.convBinStringToDecInteger(Ae) >= 0) {
			phase2(true);
		} else {
			phase2(false);
		}
		
		String largeExp = phase3(Aa,Ab);
		phase4(Aa,Ab);
	}

	/**
	 * Load the registers.
	 */
	private void phase0(String a, String b) {
		Aa = a;
		Ab = b;
	}

	/**
	 * Determine difference D of exponents in the exponent unit.
	 */
	private String phase1(String Aa, String Ab) {
		
		
		int expAa = BinaryHelper.getExponentFloatingPointBinary(Aa);
		int expAb = BinaryHelper.getExponentFloatingPointBinary(Ab);
		
//		int D = (expAa > expAb) ? expAa - expAb : expAb - expAa;
		
		int D = Math.abs(expAa - expAb);
		String expAsBin = BinaryHelper.convDecIntegerToBinString(D);
		
		return expAsBin;		
	}
	
	private void phase2(boolean s1) {
		if(!s1) {
			Aa = negAe();
			Bb = Bf();
		} else {
			Aa = Ae;
			Bb = Bg();
		}
		
		
	}

	/**
	 * Select the largest exponent.
	 */
	private String phase3(String Aa, String Ab) {
		int expAa = BinaryHelper.getExponentFloatingPointBinary(Aa);
		int expAb = BinaryHelper.getExponentFloatingPointBinary(Ab);
		
		int lE = (expAa > expAb) ? expAa : expAb; 
		
		String largestExponent = BinaryHelper.convDecIntegerToBinString(lE);
		
		return largestExponent; 
		
	}

	private void phase4(String aa2, String ab2) {
		// TODO Auto-generated method stub
		
	}
}
