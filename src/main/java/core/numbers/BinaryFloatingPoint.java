package core.numbers;

import java.text.DecimalFormat;

import core.Exponent;
import core.Mantissa;
import core.helpers.BinaryHelper;

public class BinaryFloatingPoint {
	
	public static DecimalFormat DF = new DecimalFormat("############################################.########################################################################################");
	
	public static final int MAXSHIFT = 64;
//	protected int expBits;
//	protected int manBits;
	
//	protected boolean[] exp; 
//	protected boolean[] man;
	
	protected boolean sign;
	protected Exponent exp;
	protected Mantissa man;
	
	public BinaryFloatingPoint(int expBits, int manBits) {
		this.sign = false;
		this.exp = new Exponent(expBits);
		this.man = new Mantissa(manBits);
	}
	
	public BinaryFloatingPoint(boolean sign, Exponent exp, Mantissa man) {		
		this.sign = sign;
		this.exp = exp;
		this.man = man;
	}	
	
	public BinaryFloatingPoint(int exp, int man, String bin) {
		this(exp,man);
		this.setNumber(bin);
	}
	
	public BinaryFloatingPoint(int exp, int man, int bin) {
		this(exp,man);
		this.setNumber(String.valueOf(bin));
	}

	public BinaryFloatingPoint(int exp, int man, float num) {
		this(exp,man);
		this.setNumber(String.valueOf(num));
	}

	private void setNumber(String bin) {
		if(!BinaryHelper.isBinary(bin)) {
			bin = BinaryHelper.convDecFloatingPointToBinFloatingPointString(bin, this.exp.getExpBits());
		}
		for(int i = 0; i < bin.length(); i++) {
			if(i == 0) {
				if(bin.charAt(i) == '1') {
					this.sign = true;
				}
			} else if(i < (1 + this.exp.getExpBits())) {
				if(bin.charAt(i) == '1') {
					this.exp.getExp()[i-1] = true;
				}
			} else if(i < (1 + this.exp.getExpBits() + this.man.getManBits())) {
				if(bin.charAt(i) == '1') {
					this.man.getMan()[i-1-this.exp.getExpBits()] = true;
				}
			}
		}
	}
	
	public BinaryFloatingPoint add(BinaryFloatingPoint bfp) {
		// determine the difference D of the exponents
		int bfpExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(bfp.exp.getExp()));
		int thisExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(this.exp.getExp()));
		int d = bfpExp-thisExp;
		
		// select larges exponent
		boolean[] expLargest = (d > 0) ? bfp.exp.getExp() : this.exp.getExp(); 
		
		// Shift mantissa of the smaller number d times to the right;
		boolean[] manBig = null;
		boolean[] manSmall = null;
		
		if(d > 0) {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, bfp.man.getMan());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, this.man.getMan()), Math.abs(d));
		} else {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, this.man.getMan());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, bfp.man.getMan()), Math.abs(d));
		}
		
		// Add the mantissas
		boolean[] manSum = BinaryHelper.addBinaryBoolArray(manBig, manSmall);
		
		// re-normalize the mantissa and exponent
		
		if(manSum[0] == false) {
			boolean[] newManSum = new boolean[manSum.length-1]; 
			for (int i = 1; i < manSum.length; i++) {
				newManSum[i-1] = manSum[i];
			}
			manSum = newManSum;
		}
		
		if(manSum.length > manBig.length) {
			expLargest = BinaryHelper.addBinaryBoolArray(expLargest, new boolean[]{true});
			
			if(expLargest.length > this.exp.getExpBits()) {
				boolean[] newExp = new boolean[expLargest.length-1];
				for (int i = 1; i < expLargest.length; i++) {
					newExp[i-1] = expLargest[i];
				}
				expLargest = newExp;
			}
			
//			boolean[] newManSum = new boolean[manSum.length-1];
//			for (int i = 1; i < manSum.length; i++) {
//				newManSum[i-1] = manSum[i];
//			}
//			manSum = newManSum;
		}
		
		boolean[] newManSum = new boolean[manSum.length-1];
		for (int i = 1; i < manSum.length; i++) {
			newManSum[i-1] = manSum[i];
		}
		manSum = newManSum;

		
		System.out.println(BinaryHelper.binBoolArrayToString(expLargest));
		System.out.println(BinaryHelper.binBoolArrayToString(manBig));
		System.out.println(BinaryHelper.binBoolArrayToString(manSmall));
		System.out.println(BinaryHelper.binBoolArrayToString(manSum));
		
		// set the sign of the result
		BinaryFloatingPoint result = new BinaryFloatingPoint(false, new Exponent(expLargest), new Mantissa(manSum));
		System.out.println(result.toString());
		
		return result;
	}
	
	public String toString() {
		String binFloat = "";
		binFloat += this.sign ? "1" : "0";
		
		for (int i = 0; i < this.exp.getExpBits(); i++) {
			binFloat += this.exp.getExp()[i] ? "1" : "0";
		}
		
		for (int i = 0; i < this.man.getManBits(); i++) {
			binFloat += this.man.getMan()[i] ? "1" : "0";
		}
		
		return binFloat;
	}
	
	public int intValue() {
		int value = 0;
		
		String binExp = BinaryHelper.binBoolArrayToString(this.exp.getExp());
		String binMan = BinaryHelper.binBoolArrayToString(this.man.getMan());
		
		int expDec = BinaryHelper.convBinStringToDecInteger(binExp);
		int expExp = (int) Math.pow(2, this.exp.getExpBits());
		
		int exp = expDec - expExp;
		if(exp > 0) { // rechtsshift
			String result = BinaryHelper.shiftLeftBinFloatingPointStringAsStringWithoutSignBit(binMan, Math.abs(exp));
		} else if(exp < 0) { // linksshift
			
		} else {
			//DO NOTHING
		}
			
		
		if(this.sign)
			value *= -1;
		return value;
	}
	
	public double floatValue() {
		return BinaryHelper.convBinFloatingPointToDecFloatingPointString(this.toString(), this.exp.getExpBits());		
	}
	
	public double doubleValue() {
		double value = 0;
		
		if(this.sign)
			value *= (double)-1.0;
		return (double) value;
	}
	
	public String decFloat2BinFloat(int decAsString) {
		return decFloat2BinFloat(String.valueOf(decAsString));
	}
	
	public String decFloat2BinFloat(double decAsString) {
		return decFloat2BinFloat(String.valueOf(decAsString));
	}
	
	public String decFloat2BinFloat(String decAsString) {
		return BinaryFloatingPoint.decFloat2BinFloat(this.exp.getExpBits(), this.man.getManBits(), decAsString);
	}
	
	public static String decFloat2BinFloat(int exp, int man, String decAsString) {
		
		if(!decAsString.contains(".")) {
			decAsString += ".0";
		}
		
		String sign = decAsString.startsWith("-") ? "1" : "0";
		decAsString = decAsString.replace("-", "");
		
		String[] decAsStrings = decAsString.split("[.]");
		
		String binExp = BinaryHelper.convDecStringToBinString(decAsStrings[0]);
		
		int expNum = BinaryHelper.getExponentFloatingPointBinary(binExp, MAXSHIFT);
		int expDec = (int) (Math.pow(2,exp-1) - 1) + expNum;

		String expBin = BinaryHelper.convDecIntegerToBinString(expDec);
		String manBin = "";
		
		if(decAsStrings.length < 2) {
			manBin = BinaryHelper.normalizeBinary(binExp,MAXSHIFT);
		} else {
			String binMan = BinaryHelper.convDecFloatToBinString("0."+decAsStrings[1]);
			manBin = BinaryHelper.normalizeBinary(binExp+binMan,MAXSHIFT);
		}		
		manBin = manBin.replace("1.", "");
		return new BinaryFloatingPoint(exp,man,sign + expBin + manBin).toString();
	}
}
