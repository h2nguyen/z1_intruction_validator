package core.numbers;

public class BinaryFloating32Bit extends BinaryFloating {
	private static final int EXPONENT = 8;
	private static final int MANTISSE = 23;
	
	public BinaryFloating32Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloating32Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}
}
