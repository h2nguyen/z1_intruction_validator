import static org.junit.Assert.*;

import org.junit.Test;

import core.Operation;
import core.helpers.BinaryHelper;
import core.logicgates.GateLogic;
import core.numbers.BinaryFloatingPoint;
import core.numbers.BinaryFloatingPoint24Bit;
import core.numbers.BinaryFloatingPoint32Bit;
import core.numbers.BinaryFloatingPoint64Bit;
import core.numbers.ZuseBinaryFloatingPoint24Bit;


public class BinaryFloatingPointNumberTestCase {


	//@Test
	public void testToString() {
		String bin = "1111111";
		System.out.println(BinaryHelper.binBoolArrayToString(BinaryHelper.shiftLeft(BinaryHelper.binStringToBoolArray(bin), 3)));
	}
	
	@Test
	public void testAdder() {
//		boolean[] ip1 = BinaryHelper.binStringToBoolArray("1110");
//		boolean[] ip2 = BinaryHelper.binStringToBoolArray("1000");
//
//		
//		boolean[] inputs = BinaryHelper.addBinaryBoolArray(ip1,ip2);
//		
//		System.out.println(BinaryHelper.binBoolArrayToString(inputs));
		
		
//		BinaryFloatingPoint32Bit bfp32bit1 = new BinaryFloatingPoint32Bit((float)18.0);
//		BinaryFloatingPoint32Bit bfp32bit2 = new BinaryFloatingPoint32Bit((float)3.0);
		ZuseBinaryFloatingPoint24Bit bfp24bit1 = new ZuseBinaryFloatingPoint24Bit((float)2.0);
		ZuseBinaryFloatingPoint24Bit bfp24bit2 = new ZuseBinaryFloatingPoint24Bit((float)2.0);
		
		float result = (float) new Operation().adder2(bfp24bit1, bfp24bit2).floatValue(true);
		float result2 = (float) new Operation().mul2(bfp24bit1, bfp24bit2).floatValue(true);
//		float result = (float) new Operation().mul(bfp32bit1, bfp32bit2).floatValue(true);
		System.out.println("adder:" + result);
		System.out.println("mul: " + result2);
//		System.out.println(BinaryFloatingPoint.DF.format(result).toString().replace(",", "."));
		
		
	}
	
	//@Test
	public void otherStringTests() {
		String a = "3.5";
		String b = "4.7554654654654";
		
		System.out.println(b);
		
		BinaryFloatingPoint32Bit b32 = new BinaryFloatingPoint32Bit(b);
		System.out.println(b32.toString());		
		System.out.println(b32.floatValue(false));
		
		BinaryFloatingPoint64Bit b64 = new BinaryFloatingPoint64Bit(b);
		System.out.println(b64.toString());		
		System.out.println(b64.floatValue(false));

	}
	
	//@Test
	public void regexCheckTest() {
		float bin = (float) -3.5;
		
		System.out.println(BinaryHelper.isBinary(bin));
	}

}
