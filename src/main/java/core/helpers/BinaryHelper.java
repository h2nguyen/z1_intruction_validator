/**
 * 
 */
package core.helpers;

/**
 * @author hnguyen
 *
 */
public class BinaryHelper {

	public static String convDecStringToBinString(String decString) {
		return Integer.toBinaryString(Integer.parseInt(decString));
	}
	
	public static int convDecStringToBinInteger(String decString) {
		return Integer.parseInt(Integer.toBinaryString(Integer.parseInt(decString)));
	}
	
	public static String convDecIntegerToBinString(int decInteger) {
		if(decInteger < 0) {
			return "-"+Integer.toBinaryString(-decInteger);
		}
		return Integer.toBinaryString(decInteger);
	}
	
	public static int convDecIntegerToBinInteger(int decInteger) {
		if(decInteger < 0) {
			return -Integer.parseInt(Integer.toBinaryString(-decInteger));
		}
		return Integer.parseInt(Integer.toBinaryString(decInteger));
	}

	public static String convBinStringToDecString(String binString) {		
		return Integer.toString(Integer.parseInt(binString,2));
	}
	
	public static int convBinStringToDecInteger(String binString) {
		return Integer.parseInt(binString,2);
	}

	public static String convBinIntegerToBinString(int binInteger) {
		return String.valueOf(binInteger);
	}
	
	public static String convBinIntegerToDecString(int binInteger) {		
		return String.valueOf(Integer.parseInt(String.valueOf(binInteger), 2));
	}
	
	public static int convBinIntegerToBinInteger(int binInteger) {		
		return Integer.parseInt(String.valueOf(binInteger), 2);
	}
	
	public static int addBinariesInt(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToBinInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToBinInteger(bin2);
		return BinaryHelper.convDecIntegerToBinInteger(bin1+bin2);
	}
	
	public static int subBinariesInt(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToBinInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToBinInteger(bin2);
		
		if(bin2 > bin1) {
			return -BinaryHelper.convDecIntegerToBinInteger(bin2-bin1);
		}
		
		return BinaryHelper.convDecIntegerToBinInteger(bin1-bin2);
	}
	
	/**
	 * Multiply two binary numbers
	 * @param bin1
	 * @param bin2
	 * @return
	 */
	public static int mulBinariesInt(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToBinInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToBinInteger(bin2);
		
		return BinaryHelper.convDecIntegerToBinInteger(bin1*bin2);
	}
	
	/**
	 * TODO: needs to be implemented
	 * @param bin1
	 * @param bin2
	 * @return
	 */
	public static int divBinariesInt(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToBinInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToBinInteger(bin2);
		
		return BinaryHelper.convDecIntegerToBinInteger(bin1/bin2);
	}
}
