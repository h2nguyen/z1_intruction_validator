import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.BinaryNumber;
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
		String numStringDec = "15";
		String numStringBin = "111";
		int numIntDec = -15;
		int numIntBin2 = 1111;
		int numIntBin1 = 111;
		
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
		
		
//		System.out.println(new BinaryNumber(numStringBin).stringValue());
		
		System.out.println(BinaryHelper.shiftRightInt(numIntBin2,2));
	}

}
