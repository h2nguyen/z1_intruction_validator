package core.numbers;

public class BinaryFloatingPoint64Bit extends BinaryFloatingPoint {
	private static final int EXPONENT = 11;
	private static final int MANTISSE = 52;
	
	public BinaryFloatingPoint64Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloatingPoint64Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}
}
