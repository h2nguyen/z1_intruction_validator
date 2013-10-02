/**
 * 
 */
package core;

import core.helpers.BinaryHelper;

/**
 * @author hnguyen
 * Only use for binary number and string. This class will not work error-less with decimal numbers. 
 */
public class BinaryNumber {

	protected boolean eBits[];
	protected boolean negative;
	
	public BinaryNumber() {		
	}
	
	public BinaryNumber(boolean bits[], boolean negative) {
		this.eBits = copyBoolArray(bits); 
		this.negative = negative;
	}
	
	/**
	 * 
	 * @param binAsInt
	 */
	public BinaryNumber(int binAsInt) {
		this(String.valueOf(binAsInt));
	}
	
	/**
	 * 
	 * @param binAsString
	 */
	public BinaryNumber(String binAsString) {
		this.setEBits(binAsString);
	}
	
	protected void setEBits(String binAsString) {
		if(!checkIfBin(binAsString)) {
			binAsString = BinaryHelper.convDecStringToBinString(binAsString);
		}
		
		if(binAsString.charAt(0) == '-') {
			negative = true;
			binAsString = binAsString.replace("-", "");
		}
		
		eBits = new boolean[binAsString.length()];
		for (int bitIndex = 0; bitIndex <binAsString.length(); bitIndex++) {
			if(binAsString.charAt(bitIndex) == '1')
				eBits[bitIndex] = true;
		}
	}
	
	private boolean[] copyBoolArray(boolean bits[]) {
		boolean newBits[] = new boolean[bits.length];
		
		for (int i = 0; i < newBits.length; i++) {
			newBits[i] = bits[i];
		}
		return newBits;
	}
	
	/**
	 * 
	 * @return
	 */
	public String stringValue() {
		String binString = "";
		for (int bitIndex = 0; bitIndex < eBits.length; bitIndex++) {
			if(eBits[bitIndex])
				binString += "1";
			else
				binString += "0";
		}
		if(negative)
			return "-" + binString;
		return binString;
	}
	
	/**
	 * 
	 * @return
	 */
	public int intValue() {
		return Integer.parseInt(stringValue());
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	protected boolean checkIfBin(String num) {
		for (int i = 0; i < num.length(); i++) {
			if(num.charAt(i) != '0' && num.charAt(i) != '1' && num.charAt(i) != '-' && num.charAt(i) != '+') {
				return false;
			}
		}
		return true;
	}

	public BinaryNumber getExponent() {
		int decExp = this.eBits.length - 1;
		int binExp = BinaryHelper.convDecIntegerToBinInteger(decExp);
				
		
		return new BinaryNumber(binExp);
	}
	
	public BinaryNumber getNegative() {
		return new BinaryNumber(eBits, negative & false);
	}
	
	public BinaryNumber getNegativeAs1sComplement() {
		BinaryNumber neg = new BinaryNumber(eBits, negative);

		for (int i = 0; i < neg.eBits.length; i++) {
			neg.eBits[i] = !neg.eBits[i];
		}
		return new BinaryNumber(neg.intValue());
	}
	
	public BinaryNumber getNegativeAs2sComplement() {
		BinaryNumber neg = new BinaryNumber(eBits, negative);

		for (int i = 0; i < neg.eBits.length; i++) {
			neg.eBits[i] = !neg.eBits[i];
		}
		return new BinaryNumber(BinaryHelper.addBinariesInt(neg.intValue(),1));
	}

	public BinaryNumber add(BinaryNumber ab) {
		return new BinaryNumber(BinaryHelper.addBinariesInt(this.intValue(),ab.intValue()));
	}

	public BinaryNumber shiftRight1() {
		return new BinaryNumber(BinaryHelper.shiftRightBinInteger(this.intValue(),1));
	}

	public BinaryNumber shiftRight2() {
		return new BinaryNumber(BinaryHelper.shiftRightBinInteger(this.intValue(),2));
	}

	public BinaryNumber shiftLeft1() {
		return new BinaryNumber(BinaryHelper.shiftLeftBinInteger(this.intValue(),1));
	}

	public BinaryNumber shiftLeft3() {
		return new BinaryNumber(BinaryHelper.shiftLeftBinInteger(this.intValue(),3));
	}
	
	public BinaryNumber shiftLeft(BinaryNumber times) {
		return new BinaryNumber(BinaryHelper.shiftLeftBinInteger(this.intValue(),BinaryHelper.convBinIntegerToDecInteger(times.intValue())));
	}
	
	public BinaryNumber shiftRight(BinaryNumber times) {
		return new BinaryNumber(BinaryHelper.shiftRightBinInteger(this.intValue(),BinaryHelper.convBinIntegerToDecInteger(times.intValue())));
	}
	
}
