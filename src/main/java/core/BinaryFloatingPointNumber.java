package core;
import core.BinaryNumber;
import core.helpers.BinaryHelper;

/**
 * 
 */

/**
 * @author hnguyen
 *
 */
public class BinaryFloatingPointNumber extends BinaryNumber {
	
	private boolean mBits[];
	
	/**
	 * @param bits
	 * @param negative
	 */
	public BinaryFloatingPointNumber(boolean[] bits, boolean negative) {
		super(bits, negative);
	}
	
	public BinaryFloatingPointNumber(boolean[] eBits, boolean[] mBits, boolean negative) {
		super(eBits, negative);
		this.mBits = mBits;
	}

	/**
	 * @param binAsInt
	 */
	public BinaryFloatingPointNumber(int binAsInt) {
		super(binAsInt);		
	}
	
	/**
	 * @param binAsFloat
	 */
	public BinaryFloatingPointNumber(float binAsFloat) {
		this(String.valueOf(binAsFloat));
	}

	/**
	 * @param binAsString
	 */
	public BinaryFloatingPointNumber(String binAsString) {
		if(binAsString.contains(".")) {
			String bits[] = binAsString.split("[.]");
			super.setEBits(bits[0]);
			this.setMBits(bits[1]);
		} else {
			super.setEBits(binAsString);
			this.setMBits(new String());
		}
	}
	
	private void setMBits(String binAsString) {
		if(!checkIfBin(binAsString)) {
			//TODO need to convert to the real floating number after the full stop			
			binAsString = BinaryHelper.convDecFloatToBinString(binAsString);
		}
		
		this.mBits = new boolean[binAsString.length()];
		for (int bitIndex = 0; bitIndex <binAsString.length(); bitIndex++) {
			if(binAsString.charAt(bitIndex) == '1')
				this.mBits[bitIndex] = true;
		}
	}

	/** 
	 * @return
	 */
	public String stringValue() {
		String binString = "";
		for (int bitIndex = 0; bitIndex < eBits.length; bitIndex++) {
			binString += eBits[bitIndex] ?  "1" : "0";
		}
		
		if(mBits.length > 0) {
			binString += ".";
		}
		
		for (int bitIndex = 0; bitIndex < mBits.length; bitIndex++) {
			binString += mBits[bitIndex] ? "1" : "0";
		}
		
		if(negative)
			return "-" + binString;
		return binString;
	}
	
	/**
	 * 
	 * @return
	 */
	public float floatValue() {
		return Integer.parseInt(this.stringValue());
	}
}
