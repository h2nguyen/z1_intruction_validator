import static org.junit.Assert.*;

import org.junit.Test;

import core.helpers.BinaryHelper;
import core.logicgates.GateLogic;
import core.numbers.BinaryFloatingPoint;
import core.numbers.BinaryFloatingPoint32Bit;
import core.numbers.BinaryFloatingPoint64Bit;


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
		
		
		BinaryFloatingPoint32Bit bfp32bit1 = new BinaryFloatingPoint32Bit((float)35.0);
		BinaryFloatingPoint32Bit bfp32bit2 = new BinaryFloatingPoint32Bit((float)15.5);
		BinaryFloatingPoint result = bfp32bit1.add(bfp32bit2);
		System.out.println(result.floatValue());
		
	}
	
	//@Test
	public void otherStringTests() {
		String a = "3.5";
		String b = "4.7554654654654";
		
		System.out.println(b);
		
		BinaryFloatingPoint32Bit b32 = new BinaryFloatingPoint32Bit(b);
		System.out.println(b32.toString());		
		System.out.println(b32.floatValue());
		
		BinaryFloatingPoint64Bit b64 = new BinaryFloatingPoint64Bit(b);
		System.out.println(b64.toString());		
		System.out.println(b64.floatValue());

	}
	
	//@Test
	public void regexCheckTest() {
		float bin = (float) -3.5;
		
		System.out.println(BinaryHelper.isBinary(bin));
	}

}