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
		if(decString.charAt(0) == '-') {
			decString = decString.replace("-", "");
			return "-"+Integer.toBinaryString(Integer.parseInt(decString));
		}
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
	
	public static int convBinIntegerToDecInteger(int binInteger) {		
		return Integer.parseInt(String.valueOf(binInteger), 2);
	}
	
	public static int addBinariesInt(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);
		return BinaryHelper.convDecIntegerToBinInteger(bin1+bin2);
	}
	
	public static int subBinariesInteger(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);
		
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
	public static int mulBinariesInteger(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);
		
		return BinaryHelper.convDecIntegerToBinInteger(bin1*bin2);
	}
	
	/**
	 * TODO: needs to be implemented
	 * @param bin1
	 * @param bin2
	 * @return
	 */
	public static int divBinariesInteger(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);
		
		return BinaryHelper.convDecIntegerToBinInteger(bin1/bin2);
	}
	
	
	public static int shiftLeftBinInteger(int bin, int pos) {
		if(pos <= 0) return bin;
		
		bin = BinaryHelper.convBinIntegerToDecInteger(bin);
		
		for (int i = 0; i < pos; i++) bin *= 2;
		
		return BinaryHelper.convDecIntegerToBinInteger(bin);
	}
	
	public static int shiftRightBinInteger(int bin, int pos) {
		if(pos <= 0) return bin;
		
		bin = BinaryHelper.convBinIntegerToDecInteger(bin);
		
		for (int i = 0; i < pos; i++) bin /= 2;
		
		return BinaryHelper.convDecIntegerToBinInteger(bin);
	}
	
	public static int shiftLeftBinString(String bin, int pos) {
		return shiftLeftBinInteger(Integer.parseInt(bin), pos);
	}
	
	public static int shiftRightBinString(String bin, int pos) {
		return shiftRightBinInteger(Integer.parseInt(bin), pos);
	}
	
	public static int negateBinInteger(int numInteger) {
		return -1 * numInteger;
	}
	
	public static int negateBinString(String numString) {
		int num = Integer.valueOf(numString);
		return -1 * num;
	}
}
