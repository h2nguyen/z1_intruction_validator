/**
 * 
 */
package core;

import java.util.BitSet;

import core.helpers.BinaryHelper;

/**
 * @author hnguyen
 *
 */
public class BinaryNumber {

	private BitSet bs;
	
	/**
	 * 
	 * @param binAsInt
	 */
	public BinaryNumber(int binAsInt) {
		
		if(!this.checkIfBin(binAsInt)) {
			binAsInt = BinaryHelper.convDecIntegerToBinInteger(binAsInt);
		}
			
		
		String binAsString = String.valueOf(binAsInt);
		this.bs = new BitSet(binAsString.length());
		for (int bitIndex = 0; bitIndex <binAsString.length(); bitIndex++) {
			if(binAsString.charAt(bitIndex) == '1')
				this.bs.set(bitIndex);
			else
				if(binAsString.charAt(bitIndex) == '+')
					this.bs.set(bitIndex,false);
				else if(binAsString.charAt(bitIndex) == '-')
					this.bs.set(bitIndex,true);
				else
					this.bs.set(bitIndex,false);
		}
	}
	
	/**
	 * 
	 * @param binAsString
	 */
	public BinaryNumber(String binAsString) {
		
		if(!this.checkIfBin(binAsString)) {
			binAsString = BinaryHelper.convDecStringToBinString(binAsString);
		}

		this.bs = new BitSet(binAsString.length());
		for (int bitIndex = 0; bitIndex <binAsString.length(); bitIndex++) {
			if(binAsString.charAt(bitIndex) == '1')
				this.bs.set(bitIndex,true);
			else
				if(binAsString.charAt(bitIndex) == '+')
					this.bs.set(bitIndex,false);
				else if(binAsString.charAt(bitIndex) == '-')
					this.bs.set(bitIndex,true);
				else
					this.bs.set(bitIndex,false);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String stringValue() {
		String binString = "";
		for (int bitIndex = 0; bitIndex < this.bs.length(); bitIndex++) {
			if(this.bs.get(bitIndex))
				binString += "1";
			else
				binString += "0";
		}
		return binString;
	}
	
	/**
	 * 
	 * @return
	 */
	public int intValue() {
		String binString = "";
		for (int bitIndex = 0; bitIndex < this.bs.length(); bitIndex++) {
			if(this.bs.get(bitIndex))
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
			System.out.println(num.charAt(i));
			if(num.charAt(i) != '0' && num.charAt(i) != '1' && num.charAt(i) != '-' && num.charAt(i) != '+') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param num
	 * @return
	 */
	private boolean checkIfBin(int num) {
		return checkIfBin(String.valueOf(num));
	}
	
}
