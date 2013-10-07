/**
 * 
 */
package core.helpers;

/**
 * @author hnguyen
 *
 */
public class BinaryHelper {

	private static int kPrecision = 8;
	
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
	
	public static String shiftLeftBinFloatingPointStringAsStringWithoutSignBit(String bin, int pos) {
		String exp = bin.split("[.]")[0];
		String man = bin.split("[.]")[1];
		while(pos-- > 0) {
			int expLen = exp.length();			
			man = exp.charAt(expLen-1) + man;
			exp = "0" + exp.substring(0, expLen-1);
		}
		
		return removeLeadingZerosString(exp)+"."+man;
	}
	
	public static String normalizeFloatingPointBinary(String floatingPointBin) {
		if(floatingPointBin.startsWith("1.") || floatingPointBin.startsWith("0.") || floatingPointBin.startsWith(".")) {
			return floatingPointBin;
		}
		
		while(!floatingPointBin.startsWith("1.")) {
			floatingPointBin = shiftLeftBinFloatingPointStringAsStringWithoutSignBit(floatingPointBin,1);
		}
		
		return floatingPointBin;
	}
	
	public static int getExponentFloatingPointBinary(String floatingPointBin) {
		if(floatingPointBin.startsWith("1.") || floatingPointBin.startsWith("0.") || floatingPointBin.startsWith(".")) {
			return 0;
		}
		
		int pos = 0;
		while(!floatingPointBin.startsWith("1.")) {
			floatingPointBin = shiftLeftBinFloatingPointStringAsStringWithoutSignBit(floatingPointBin,1);
			pos++;
		}
		
		return pos;
	}
	
	public static String removeLeadingZerosString(String numW0) {
		
		String num = new String(numW0);
		
		while(num.length() > 0) {
			
			if(num.length() == 1)
				break;
			
			if(num.charAt(0) == '0') {
				num = num.substring(1); 
			} else {
				break;
			}
		}
		
		return num;
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
	
	public static String convDecFloatToBinStringWith0AndPoint(float fnum) {
		return convDecFloatToBinStringWith0AndPoint(String.valueOf(fnum));
	}

	public static String convDecFloatToBinStringWith0AndPoint(String decAsString) {
		return "0."+ convDecFloatToBinString(decAsString);
	}
	
	public static String convDecFloatToBinString(float fnum) {
		return convDecFloatToBinString(String.valueOf(fnum));
	}
	
	public static String convDecFloatToBinString(String decAsString) {
		
//		if(!decAsString.startsWith("0.") && !decAsString.startsWith(".") )
//			return null;
		
		float df = Float.parseFloat(decAsString);
		if(df <= 0)
			return "0"; 
		String bin = "";
		
		while(df > 0) {
			df *= 2;
			bin += df >= 1 ? "1" : "0";
			df -= df >= 1.0 ? 1.0 : 0.0 ;
		}
		
		return bin;
	}
	
	public static String convDecFloatingPointToBinString(String decAsString) {
		
		if(!decAsString.contains(".")) {
			return convDecStringToBinString(decAsString);
		}
		
		if(decAsString.startsWith("0.") || decAsString.startsWith(".") )
			return convDecFloatToBinString(decAsString);
		
		String sigPart = decAsString.contains("-") ? "1" : "0";
		decAsString = decAsString.replace("-", "");
		
		String expPart = decAsString.split("[.]")[0];
		String manPart = decAsString.split("[.]")[1];
		
		expPart = BinaryHelper.convDecStringToBinString(expPart);
		manPart = BinaryHelper.convDecFloatToBinString(manPart);
		String floatingPointBinary = expPart+"."+manPart;
		
		int exp = getExponentFloatingPointBinary(floatingPointBinary);
		exp += (int) (Math.pow(2, kPrecision - 1) - 1 );
		
		expPart = BinaryHelper.convDecIntegerToBinString(exp);
		
		floatingPointBinary = normalizeFloatingPointBinary(floatingPointBinary);
		manPart = floatingPointBinary.substring(floatingPointBinary.indexOf('.')+1);		
		
		return sigPart+expPart+manPart;
	}
	
	
	
	public static float convBinIntegerToDecFloat(int binAsInteger) {
		return convBinStringToDecFloat(String.valueOf(binAsInteger));
	}
	
	public static float convBinStringToDecFloat(String binAsString) {
		
		String bS = new String(binAsString);
		
		if(bS.contains(".")) {
			bS = bS.split("[.]")[1]; 
		}
		
		float result = 0;
		for(int i = 1; i <= bS.length(); i++) {
			result += (float) (bS.charAt(i-1) == '1' ? (float) 1.0 / Math.pow(2,i) : 0.0);
		}
				
		return result;
	}
}
