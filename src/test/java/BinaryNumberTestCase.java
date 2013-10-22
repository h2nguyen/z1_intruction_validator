import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.helpers.BinaryHelper;

/**
 * 
 */

/**
 * @author hnguyen
 *
 */
public class BinaryNumberTestCase extends BinaryHelper {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String numStringDec = "-15";
		String numStringBin = "111";
		int numIntDec = 15;
		int numIntBin2 = 1111;
		int numIntBin1 = 111;
//		BinaryFloatingPointNumber binf = new BinaryFloatingPointNumber("111.5");
//		
//		System.out.println(binf.stringValue());
		
		
//		System.out.println(BinaryHelper.convDecFloatToBinStringWith0AndPoint((float).5));
//		String fb = "111000111.001";
//		System.out.println(BinaryHelper.normalizeFloatingPointBinary(fb));
//		System.out.println(BinaryHelper.getExponentFloatingPointBinary(fb));
//		System.out.println(fb);
//		String binString = BinaryHelper.conv
		
		System.out.println(BinaryHelper.getExponentFloatingPointBinary("10010",64));
		System.out.println(BinaryHelper.getExponentFloatingPointBinary("11",64));
		
//		System.out.println(bin1.shiftRight(exp).stringValue());
//		System.out.println(BinaryHelper.convBinIntegerToBinString(numIntBin2));
//		System.out.println(BinaryHelper.convBinIntegerToDecString(numIntBin2));
//		System.out.println(BinaryHelper.convBinStringToDecInteger(numStringBin));
//		System.out.println(BinaryHelper.convBinStringToDecString(numStringBin));
//		System.out.println(BinaryHelper.convDecIntegerToBinInteger(numIntDec));
//		System.out.println(BinaryHelper.convDecIntegerToBinString(numIntDec));
//		System.out.println(BinaryHelper.convDecStringToBinInteger(numStringDec));
//		System.out.println(BinaryHelper.convDecStringToBinString(numStringBin));
//		System.out.println(BinaryHelper.addBinariesInt(numIntBin1, numIntBin1));
//		System.out.println(BinaryHelper.subBinariesInt(numIntBin2, numIntBin1));
//		System.out.println(BinaryHelper.mulBinariesInt(numIntBin2, numIntBin1));
//		System.out.println(BinaryHelper.divBinariesInt(numIntBin2, numIntBin1));
//		System.out.println(bin1.add(bin2).stringValue());
//		System.out.println(BinaryHelper.shiftRightBinString(numStringBin,1));
	}

}
