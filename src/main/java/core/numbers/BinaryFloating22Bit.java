package core.numbers;

public class BinaryFloating22Bit extends BinaryFloating {
	private static final int EXPONENT = 7;
	private static final int MANTISSE = 14;

	public BinaryFloating22Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloating22Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}
	
}
