package core.numbers;

import core.helpers.BinaryHelper;

public class BinaryFloating {
	protected int expBits;
	protected int manBits;
	
	protected boolean sign;
	protected boolean[] exp; 
	protected boolean[] man;
	
	public BinaryFloating(int expBits, int manBits) {
		this.expBits = expBits;
		this.manBits = manBits;
		
		this.sign = false;
		this.exp = new boolean[this.expBits];
		this.man = new boolean[this.manBits];
	}
	
	public BinaryFloating(boolean sign, boolean[] exp, boolean[] man) {
		this.expBits = exp.length;
		this.manBits = man.length;

		this.sign = sign;
		this.exp = exp;
		this.man = man;
	}	
	
	public BinaryFloating(int exp, int man, String bin) {
		this(exp,man);
		this.setNumber(bin);
	}
	
	public BinaryFloating(int exp, int man, int bin) {
		this(exp,man);
		this.setNumber(String.valueOf(bin));
	}

	private void setNumber(String bin) {
		for(int i = 0; i < bin.length(); i++) {
			if(i == 0) {
				if(bin.charAt(i) == '1') {
					this.sign = true;
				}
			} else if(i < (1 + expBits)) {
				if(bin.charAt(i) == '1') {
					this.exp[i-1] = true;
				}
			} else if(i < (1 + expBits + manBits)) {
				if(bin.charAt(i) == '1') {
					this.man[i-1-expBits] = true;
				}
			}
		}
	}
	
	public String toString() {
		String binFloat = "";
		binFloat += this.sign ? "1" : "0";
		
		for (int i = 0; i < exp.length; i++) {
			binFloat += this.exp[i] ? "1" : "0";
		}
		
		for (int i = 0; i < man.length; i++) {
			binFloat += this.man[i] ? "1" : "0";
		}
		
		return binFloat;
	}
	
	public int intValue() {
		int value = 0;
		
		String binExp = BinaryHelper.binBoolArrayToString(this.exp);
		String binMan = BinaryHelper.binBoolArrayToString(this.man);
		
		int expDec = BinaryHelper.convBinStringToDecInteger(binExp);
		int expExp = (int) Math.pow(2, this.expBits);
		
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
	
	public float floatValue() {
		float value = 0;
		
		if(this.sign)
			value *= (float)-1.0;
		return value;
	}
	
	public double doubleValue() {
		double value = 0;
		
		if(this.sign)
			value *= (double)-1.0;
		return (double) value;
	}
	
	public String dec2BinFloat(int decAsString) {
		return dec2BinFloat(String.valueOf(decAsString));
	}
	
	public String dec2BinFloat(double decAsString) {
		return dec2BinFloat(String.valueOf(decAsString));
	}
	
	public String dec2BinFloat(String decAsString) {
		return BinaryFloating.dec2BinFloat(this.expBits, this.manBits, decAsString);
	}
	
	public static String dec2BinFloat(int exp, int man, String decAsString) {
		String sign = decAsString.startsWith("-") ? "1" : "0";
		decAsString = decAsString.replace("-", "");
		
		String[] decAsStrings = decAsString.split("[.]");
		
		String binExp = BinaryHelper.convDecStringToBinString(decAsStrings[0]);
		
		int expNum = BinaryHelper.getExponentFloatingPointBinary(binExp);
		int expDec = (int) (Math.pow(2,exp-1) - 1) + expNum;

		String expBin = BinaryHelper.convDecIntegerToBinString(expDec);
		String manBin = "";
		
		if(decAsStrings.length < 2) {
			manBin = BinaryHelper.normalizeBinary(binExp);
		} else {
			String binMan = BinaryHelper.convDecFloatToBinString("0."+decAsStrings[1]);
			manBin = BinaryHelper.normalizeBinary(binExp+binMan);
		}		
		manBin = manBin.replace("1.", "");
		return new BinaryFloating(exp,man,sign + expBin + manBin).toString();
	}
}
