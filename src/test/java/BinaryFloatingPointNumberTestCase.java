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
		
		float a = (float) 3;
		float b = (float) 2;
		
		ZuseBinaryFloatingPoint24Bit bfp24bit1 = new ZuseBinaryFloatingPoint24Bit(a);
		ZuseBinaryFloatingPoint24Bit bfp24bit2 = new ZuseBinaryFloatingPoint24Bit(b);
				
		
//		System.out.println("Addition: "+a+"+"+b);
//		ZuseBinaryFloatingPoint24Bit addres = new Operation().add(bfp24bit1, bfp24bit2);
//		System.out.println("adder: " + addres.floatValue(true));
//		System.out.println("\nSubstraction: "+a+"-"+b);
//		ZuseBinaryFloatingPoint24Bit subres = new Operation().sub(bfp24bit1, bfp24bit2);
//		System.out.println("substracter: " + subres.floatValue(true));
//		System.out.println("\nMuliplication: "+a+"*"+b);
//		ZuseBinaryFloatingPoint24Bit mulres = new Operation().mul(bfp24bit1, bfp24bit2);
//		System.out.println("multiplier: " + mulres.floatValue(true));
		System.out.println("\nDivision: "+a+"/"+b);
		ZuseBinaryFloatingPoint24Bit divres = new Operation().div(bfp24bit1, bfp24bit2);
		System.out.println("divisor: " + divres.floatValue(true));
		
		
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
