import static org.junit.Assert.*;

import org.junit.Test;

import core.helpers.BinaryHelper;
import core.numbers.BinaryFloatingPoint32Bit;
import core.numbers.BinaryFloatingPoint64Bit;


public class BinaryFloatingPointNumberTestCase {

	@Test
	public void testZuseBinaryFloatingPointBooleanBooleanArrayBooleanArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testZuseBinaryFloatingPointString() {
		fail("Not yet implemented");
	}

	//@Test
	public void testToString() {
		String bin = "10010010";
		System.out.println(new BinaryFloatingPoint32Bit(bin).toString());
	}
	
	@Test
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
