package core.numbers;

public class BinaryFloating24Bit extends BinaryFloating {
	private static final int EXPONENT = 7;
	private static final int MANTISSE = 16;

	public BinaryFloating24Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloating24Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}

}
