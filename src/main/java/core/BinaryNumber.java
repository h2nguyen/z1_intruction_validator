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

	private boolean bits[];
	private boolean negative;
	
	public BinaryNumber(boolean bits[], boolean negative) {
		this.bits = copyBoolArray(bits); 
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
		
		if(!checkIfBin(binAsString)) {
			binAsString = BinaryHelper.convDecStringToBinString(binAsString);
		}
		
		if(binAsString.charAt(0) == '-') {
			negative = true;
			binAsString = binAsString.replace("-", "");
		}
		
		bits = new boolean[binAsString.length()];
		for (int bitIndex = 0; bitIndex <binAsString.length(); bitIndex++) {
			if(binAsString.charAt(bitIndex) == '1')
				bits[bitIndex] = true;
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
		for (int bitIndex = 0; bitIndex < bits.length; bitIndex++) {
			if(bits[bitIndex])
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
		String binString = "";
		for (int bitIndex = 0; bitIndex < bits.length; bitIndex++) {
			if(bits[bitIndex])
				binString += "1";
			else
				binString += "0";
		}
		return Integer.parseInt(binString);
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	private boolean checkIfBin(String num) {
		for (int i = 0; i < num.length(); i++) {
			if(num.charAt(i) != '0' && num.charAt(i) != '1' && num.charAt(i) != '-' && num.charAt(i) != '+') {
				return false;
			}
		}
		return true;
	}

	public BinaryNumber getExponent() {
		int decExp = this.bits.length - 1;
		int binExp = BinaryHelper.convDecIntegerToBinInteger(decExp);
				
		
		return new BinaryNumber(binExp);
	}
	
	public BinaryNumber getNegative() {
		return new BinaryNumber(bits, negative & false);
	}
	
	public BinaryNumber getNegativeAs1sComplement() {
		BinaryNumber neg = new BinaryNumber(bits, negative);

		for (int i = 0; i < neg.bits.length; i++) {
			neg.bits[i] = !neg.bits[i];
		}
		return new BinaryNumber(neg.intValue());
	}
	
	public BinaryNumber getNegativeAs2sComplement() {
		BinaryNumber neg = new BinaryNumber(bits, negative);

		for (int i = 0; i < neg.bits.length; i++) {
			neg.bits[i] = !neg.bits[i];
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
