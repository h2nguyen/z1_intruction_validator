package core.numbers;

public class BinaryFloating64Bit extends BinaryFloating {
	private static final int EXPONENT = 8;
	private static final int MANTISSE = 52;
	
	public BinaryFloating64Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloating64Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}
}
