package core.numbers;

import java.text.DecimalFormat;

import core.Exponent;
import core.Mantissa;
import core.Operation;
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
	
	public BinaryFloatingPoint(Exponent exp, Mantissa man) {		
		this.sign = false;
		this.exp = exp;
		this.man = man;
	}
	
	public BinaryFloatingPoint(boolean sign, int expBits, int manBits) {
		this.sign = sign;
		this.exp = new Exponent(expBits);
		this.man = new Mantissa(manBits);
	}
	
	public BinaryFloatingPoint(boolean sign, Exponent exp, Mantissa man) {		
		this.sign = sign;
		this.exp = exp;
		this.man = man;
	}	
	
	public BinaryFloatingPoint(int exp, int man, String bin) {
		this(bin.startsWith("-") ?  true : false, exp,man);
		this.setNumber(bin);
	}
	
	public BinaryFloatingPoint(int exp, int man, int bin) {
		this(bin < 0 ? true : false, exp,man);
		this.setNumber(String.valueOf(bin));
	}

	public BinaryFloatingPoint(int exp, int man, float num) {
		this(num < 0 ? true : false, exp, man);
		this.setNumber(String.valueOf(num));
	}

	/**
	 * @return the sign
	 */
	public boolean isSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(boolean sign) {
		this.sign = sign;
	}

	/**
	 * @return the exp
	 */
	public Exponent getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(Exponent exp) {
		this.exp = exp;
	}

	/**
	 * @return the man
	 */
	public Mantissa getMan() {
		return man;
	}

	/**
	 * @param man the man to set
	 */
	public void setMan(Mantissa man) {
		this.man = man;
	}

	private void setNumber(String bin) {
		//if(!BinaryHelper.isBinary(bin)) {
			bin = BinaryHelper.convDecFloatingPointToBinFloatingPointString(bin, this.exp.getBits());
//		}
		for(int i = 0; i < bin.length(); i++) {
			if(i == 0) {
				if(bin.charAt(i) == '1') {
					this.sign = true;
				}
			} else if(i < (1 + this.exp.getBits())) {
				if(bin.charAt(i) == '1') {
					this.exp.getBoolArr()[i-1] = true;
				}
			} else if(i < (1 + this.exp.getBits() + this.man.getBits())) {
				if(bin.charAt(i) == '1') {
					this.man.getBoolArr()[i-1-this.exp.getBits()] = true;
				}
			}
		}
	}
	
	public BinaryFloatingPoint add(BinaryFloatingPoint bfp) {
		return new Operation().add(this, bfp);
	}
	
	public BinaryFloatingPoint sub(BinaryFloatingPoint b) {
		// TODO Auto-generated method stub
		return null;
	}

	public BinaryFloatingPoint mul(BinaryFloatingPoint b) {
		// TODO Auto-generated method stub
		return null;
	}

	public BinaryFloatingPoint div(BinaryFloatingPoint b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		String binFloat = "";
		binFloat += this.sign ? "1" : "0";
		
		for (int i = 0; i < this.exp.getBits(); i++) {
			binFloat += this.exp.getBoolArr()[i] ? "1" : "0";
		}
		
		for (int i = 0; i < this.man.getBits(); i++) {
			binFloat += this.man.getBoolArr()[i] ? "1" : "0";
		}
		
		return binFloat;
	}
	
	public int intValue() {
		int value = 0;
		
//		String binExp = BinaryHelper.binBoolArrayToString(this.exp.getExp());
//		String binMan = BinaryHelper.binBoolArrayToString(this.man.getMan());
//		
//		int expDec = BinaryHelper.convBinStringToDecInteger(binExp);
//		int expExp = (int) Math.pow(2, this.exp.getExpBits());
//		
//		int exp = expDec - expExp;
//		if(exp > 0) { // rechtsshift
//			String result = BinaryHelper.shiftLeftBinFloatingPointStringAsStringWithoutSignBit(binMan, Math.abs(exp));
//		} else if(exp < 0) { // linksshift
//			
//		} else {
//			//DO NOTHING
//		}
//			
//		
//		if(this.sign)
//			value *= -1;
		return value;
	}
	
	public double floatValue(boolean asResult) {
		return BinaryHelper.convBinFloatingPointToDecFloatingPointString(this.toString(), this.exp.getBits(),asResult);		
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
		return BinaryFloatingPoint.decFloat2BinFloat(this.exp.getBits(), this.man.getBits(), decAsString);
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
