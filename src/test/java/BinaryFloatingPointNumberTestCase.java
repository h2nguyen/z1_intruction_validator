import static org.junit.Assert.*;

import org.junit.Test;

import core.helpers.BinaryHelper;
import core.numbers.BinaryFloating32Bit;
import core.numbers.BinaryFloating64Bit;


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
		System.out.println(new BinaryFloating32Bit(bin).toString());
	}
	
	@Test
	public void otherStringTests() {
		String result = BinaryHelper.shiftLeftBinFloatingPointStringAsStringWithoutSignBit("0.101010", 10);
		System.out.println(result);		
	}

}
