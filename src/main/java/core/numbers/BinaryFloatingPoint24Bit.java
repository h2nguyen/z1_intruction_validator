package core.numbers;

public class BinaryFloatingPoint24Bit extends BinaryFloatingPoint {
	private static final int EXPONENT = 7;
	private static final int MANTISSE = 16;

	public BinaryFloatingPoint24Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloatingPoint24Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}

}