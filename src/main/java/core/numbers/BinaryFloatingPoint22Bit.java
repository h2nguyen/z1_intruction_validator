package core.numbers;

public class BinaryFloatingPoint22Bit extends BinaryFloatingPoint {
	private static final int EXPONENT = 7;
	private static final int MANTISSE = 14;

	public BinaryFloatingPoint22Bit() {
		super(EXPONENT,MANTISSE);
	}
	
	public BinaryFloatingPoint22Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}
	
	public BinaryFloatingPoint22Bit(float num) {
		this(DF.format(num).toString().replace(",", "."));
	}
}
