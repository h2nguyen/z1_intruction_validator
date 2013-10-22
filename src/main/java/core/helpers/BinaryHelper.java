/**
 * 
 */
package core.helpers;

import core.logicgates.GateLogic;
import core.logicgates.GateLogic.MultiGate;

/**
 * @author hnguyen
 * 
 */
public class BinaryHelper {

	public static String convDecStringToBinString(String decString) {
		if (decString.charAt(0) == '-') {
			decString = decString.replace("-", "");
			return "-" + Integer.toBinaryString(Integer.parseInt(decString));
		}
		return Integer.toBinaryString(Integer.parseInt(decString));
	}

	public static int convDecStringToBinInteger(String decString) {
		return Integer.parseInt(Integer.toBinaryString(Integer
				.parseInt(decString)));
	}

	public static String convDecIntegerToBinString(int decInteger) {
		if (decInteger < 0) {
			return "-" + Integer.toBinaryString(-decInteger);
		}
		return Integer.toBinaryString(decInteger);
	}
	
	public static String convDecIntegerToBinStringWithLeadingZeros(int decInteger, int leadingZerosExponent) {
		
		String binString = convDecIntegerToBinString(decInteger);
		int d = leadingZerosExponent - binString.length();
		if(d > 0) {
			for (int i = 0; i < d; i++) {
				binString = "0" + binString; 
			}
		}
		return binString;
	}

	public static int convDecIntegerToBinInteger(int decInteger) {
		if (decInteger < 0) {
			return -Integer.parseInt(Integer.toBinaryString(-decInteger));
		}
		return Integer.parseInt(Integer.toBinaryString(decInteger));
	}

	public static String convBinStringToDecString(String binString) {
		return Integer.toString(Integer.parseInt(binString, 2));
	}

	public static int convBinStringToDecInteger(String binString) {
		return Integer.parseInt(binString, 2);
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
		return BinaryHelper.convDecIntegerToBinInteger(bin1 + bin2);
	}

	public static int subBinariesInteger(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);

		if (bin2 > bin1) {
			return -BinaryHelper.convDecIntegerToBinInteger(bin2 - bin1);
		}

		return BinaryHelper.convDecIntegerToBinInteger(bin1 - bin2);
	}

	/**
	 * Multiply two binary numbers
	 * 
	 * @param bin1
	 * @param bin2
	 * @return
	 */
	public static int mulBinariesInteger(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);

		return BinaryHelper.convDecIntegerToBinInteger(bin1 * bin2);
	}

	/**
	 * TODO: needs to be implemented
	 * 
	 * @param bin1
	 * @param bin2
	 * @return
	 */
	public static int divBinariesInteger(int bin1, int bin2) {
		bin1 = BinaryHelper.convBinIntegerToDecInteger(bin1);
		bin2 = BinaryHelper.convBinIntegerToDecInteger(bin2);

		return BinaryHelper.convDecIntegerToBinInteger(bin1 / bin2);
	}

	public static int shiftLeftBinInteger(int bin, int pos) {
		if (pos <= 0)
			return bin;

		bin = BinaryHelper.convBinIntegerToDecInteger(bin);

		for (int i = 0; i < pos; i++)
			bin *= 2;

		return BinaryHelper.convDecIntegerToBinInteger(bin);
	}

	public static int shiftRightBinInteger(int bin, int pos) {
		if (pos <= 0)
			return bin;

		bin = BinaryHelper.convBinIntegerToDecInteger(bin);

		for (int i = 0; i < pos; i++)
			bin /= 2;

		return BinaryHelper.convDecIntegerToBinInteger(bin);
	}

	public static int shiftLeftBinString(String bin, int pos) {
		return shiftLeftBinInteger(Integer.parseInt(bin), pos);
	}

	public static String shiftRightBinFloatingPointStringAsStringWithoutSignBit(
			String bin, int pos) {
		boolean hasExtension = false;
		if (!bin.contains(".")) {
			bin += ".0";
			hasExtension = true;
		}

		String exp = bin.split("[.]")[0];
		String man = bin.split("[.]")[1];
		if (hasExtension)
			man = "";
		while (pos-- > 0) {
			int expLen = exp.length();
			man = exp.charAt(expLen - 1) + man;
			exp = "0" + exp.substring(0, expLen - 1);
		}

		return removeLeadingZerosString(exp) + "."
				+ removeFollowingZerosString(man);
	}

	public static String shiftLeftBinFloatingPointStringAsStringWithoutSignBit(
			String bin, int pos) {
		if (!bin.contains(".")) {
			while (pos-- > 0) {

			}
		}

		char[] bin2 = bin.toCharArray();

		while (pos-- > 0) {
			int ic = bin.indexOf('.');
			if (ic + 2 > bin.length()) {
				bin += '0';
				bin2 = bin.toCharArray();
				ic = bin.indexOf('.');
			}
			if (ic + 1 < bin.length()) {
				bin2[ic] = bin2[ic + 1];
				bin2[ic + 1] = '.';
				bin = new String(bin2);
			}
		}

		bin = removeFollowingZerosString(removeLeadingZerosString(bin));
		bin += bin.endsWith(".") ? "0" : "";

		return bin;
	}

	public static String normalizeBinary(String floatingPointBin) {
		if (floatingPointBin.startsWith("1.")
				|| floatingPointBin.startsWith("0.")
				|| floatingPointBin.startsWith(".")) {
			return floatingPointBin;
		}

		while (!floatingPointBin.startsWith("1.")) {
			floatingPointBin = shiftRightBinFloatingPointStringAsStringWithoutSignBit(
					floatingPointBin, 1);
		}

		return floatingPointBin;
	}

	public static int getExponentFloatingPointBinary(String floatingPointBin) {
		if (floatingPointBin.startsWith("1.")
				|| floatingPointBin.startsWith("0.")
				|| floatingPointBin.startsWith(".")) {
			return 0;
		}

		int pos = 0;
		while (!floatingPointBin.startsWith("1.")) {
			floatingPointBin = shiftRightBinFloatingPointStringAsStringWithoutSignBit(
					floatingPointBin, 1);
			pos++;
		}

		return pos;
	}

	public static String removeLeadingZerosString(String numW0) {

		String num = new String(numW0);

		while (num.length() > 0) {

			if (num.length() == 1)
				break;

			if (num.charAt(0) == '0') {
				num = num.substring(1);
			} else {
				break;
			}
		}

		return num;
	}

	public static String removeFollowingZerosString(String numW0) {

		String num = new String(numW0);

		while (num.length() > 0) {

			if (num.length() == 1)
				break;

			if (num.charAt(num.length() - 1) == '0') {
				num = num.substring(0, num.length() - 1);
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
		return "0." + convDecFloatToBinString(decAsString);
	}

	public static String convDecFloatToBinString(float fnum) {
		return convDecFloatToBinString(String.valueOf(fnum));
	}

	public static String convDecFloatToBinString(String decAsString) {

		// if(!decAsString.startsWith("0.") && !decAsString.startsWith(".") )
		// return null;

		double df = Double.parseDouble(decAsString);
		if (df <= 0)
			return "0";
		String bin = "";

		while (df > 0) {
			df *= 2;
			bin += df >= 1 ? "1" : "0";
			df -= df >= 1.0 ? 1.0 : 0.0;
		}

		return bin;
	}

	public static String convDecFloatBehindThePointToBinString(
			String decAsString) {

		// if(!decAsString.startsWith("0.") && !decAsString.startsWith(".") )
		// return null;

		double df = Double.parseDouble("0." + decAsString);
		if (df <= 0)
			return "0";
		String bin = "";

		while (df > 0) {
			df *= 2;
			bin += df >= 1 ? "1" : "0";
			df -= df >= 1.0 ? 1.0 : 0.0;
		}

		return bin;
	}

	public static float convBinBehindPointStringToDecInteger(String string) {
		if (string.startsWith("0.")) {
			string = string.replace("0.", "");
		}

		if (string.startsWith(".")) {
			string = string.replace(".", "");
		}

		float value = (float) 0.0;
		for (int b = 0; b < string.length(); b++) {
			value += 1.0 / (Math.pow(2, b + 1))
					* Integer.valueOf("" + string.charAt(b));
		}
		return value;
	}

	public static String convDecFloatingPointToBinFloatingPointString(
			String decAsString, int expLength) {

		if (!decAsString.contains(".")) {
			decAsString += ".0";
		}
		String sigPart = decAsString.contains("-") ? "1" : "0";
		decAsString = decAsString.replace("-", "");

		String expPart = decAsString.split("[.]")[0];
		String manPart = decAsString.split("[.]")[1];

		expPart = BinaryHelper.convDecStringToBinString(expPart);
		manPart = BinaryHelper.convDecFloatBehindThePointToBinString(manPart);
		String floatingPointBinary = expPart + "." + manPart;

		int exp = getExponentFloatingPointBinary(floatingPointBinary);
		exp += (int) (Math.pow(2, expLength - 1) - 1);

		expPart = BinaryHelper.convDecIntegerToBinStringWithLeadingZeros(exp, expLength);

		floatingPointBinary = normalizeBinary(floatingPointBinary);
		manPart = floatingPointBinary.substring(floatingPointBinary
				.indexOf('.') + 1);

		return sigPart + expPart + manPart;
	}

	public static float convBinFloatingPointToDecFloatingPointString(
			String binAsString, int expLength) {
		int bitsLength = 1 + expLength;
		while (binAsString.length() < bitsLength) {
			binAsString += "0";
		}

		String signPart = "";
		String expPart = "";
		String manPart = "";
		for (int b = 0; b < binAsString.length(); b++) {
			if (b == 0)
				signPart += binAsString.charAt(b);
			else if (b <= expLength)
				expPart += binAsString.charAt(b);
			else
				manPart += binAsString.charAt(b);
		}

		manPart = "1." + manPart;
		int exp = BinaryHelper.convBinStringToDecInteger(expPart);
		exp -= (int) (Math.pow(2, expLength - 1) - 1);

		String binFloatingPoint = (exp < 0) ? BinaryHelper
				.shiftRightBinFloatingPointStringAsStringWithoutSignBit(
						manPart, Math.abs(exp)) : BinaryHelper
				.shiftLeftBinFloatingPointStringAsStringWithoutSignBit(manPart,
						Math.abs(exp));
		String[] binFloatingPointParts = binFloatingPoint.split("[.]");

		float result = BinaryHelper
				.convBinStringToDecInteger(binFloatingPointParts[0]);
		result += BinaryHelper
				.convBinBehindPointStringToDecInteger(binFloatingPointParts[1]);
		if (signPart.charAt(0) == '1')
			result *= -1.0;
		return result;
	}

	public static float convBinIntegerToDecFloat(int binAsInteger) {
		return convBinStringToDecFloat(String.valueOf(binAsInteger));
	}

	public static float convBinStringToDecFloat(String binAsString) {

		String bS = new String(binAsString);

		if (bS.contains(".")) {
			bS = bS.split("[.]")[1];
		}

		float result = 0;
		for (int i = 1; i <= bS.length(); i++) {
			result += (float) (bS.charAt(i - 1) == '1' ? (float) 1.0
					/ Math.pow(2, i) : 0.0);
		}

		return result;
	}

	public static String binBoolArrayToString(boolean[] exp) {
		String bin = "";
		for (int i = 0; i < exp.length; i++) {
			bin += exp[i] ? "1" : "0";
		}
		return bin;
	}

	public static boolean[] binStringToBoolArray(String bin) {
		boolean[] binBoolArray = new boolean[bin.length()];

		for (int i = 0; i < binBoolArray.length; i++) {
			binBoolArray[i] = bin.charAt(i) == '1' ? true : false;
		}

		return binBoolArray;
	}

	public static boolean isBinary(float num) {
		return isBinary(String.valueOf(num));
	}

	public static boolean isBinary(String num) {
		return num.matches("^[0-1]*[.]{0,1}[0-1]*");
	}

	public static boolean[] shiftRight(boolean[] arr, int d) {
		boolean[] arrS = new boolean[arr.length];
		
//		if(hiddenBit && d > 0)
//			arrS[d-1] = hiddenBit;
		
		for (int i = 0; i < arrS.length; i++) {
			if (i >= d) {
				arrS[i] = arr[i - d];
			}
		}

		return arrS;
	}

	public static boolean[] shiftLeft(boolean[] arr, int d) {
		boolean[] arrS = new boolean[arr.length];
		for (int i = d; i < arr.length; i++) {
			arrS[i - d] = arr[i];
		}

		return arrS;
	}

	public static boolean[] mergeBinaryBoolArray(boolean[] bin1, boolean[] bin2) {
		boolean[] mergedArray = new boolean[bin1.length + bin2.length];
		for (int i = 0; i < bin1.length; i++) {
			mergedArray[i] = bin1[i];
		}
		
		for (int i = 0; i < bin2.length; i++) {
			mergedArray[i+(bin1.length)] = bin2[i];
		}
		
		return mergedArray;
	}
	
	public static boolean[] addBinaryBoolArray(boolean[] bin1, boolean[] bin2) {	
		if (bin1.length > bin2.length) {
			boolean[] newBin2 = new boolean[bin1.length];

			for (int i = 0; i < bin2.length; i++) {
				newBin2[i] = bin2[i];
			}
			bin2 = shiftRight(newBin2, bin1.length - bin2.length);
			
			
		} else if (bin2.length > bin1.length) {
			boolean[] newBin1 = new boolean[bin2.length];

			for (int i = 0; i < bin1.length; i++) {
				newBin1[i] = bin1[i];
			}
			bin1 = shiftRight(newBin1, bin2.length - bin1.length);
		}
		
		
		MultiGate multiBitAdder = GateLogic.buildAdder(bin1.length);
	    // Convert input numbers into array of bits
	    boolean[] inputs = mergeBinaryBoolArray(inverseOrder(bin1),inverseOrder(bin2));

		return inverseOrder(multiBitAdder.eval(inputs));
	}

	private static boolean[] inverseOrder(boolean[] bin) {
		boolean[] reverse = new boolean[bin.length];
		for (int i = 0, j = bin.length - 1; i < bin.length; i++,j--) {
			reverse[j] = bin[i];
		}
		
		
		return reverse;
	}
}
