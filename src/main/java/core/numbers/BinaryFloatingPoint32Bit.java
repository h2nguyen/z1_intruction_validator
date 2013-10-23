package core.numbers;

public class BinaryFloatingPoint32Bit extends BinaryFloatingPoint {
	private static final int EXPONENT = 8;
	private static final int MANTISSE = 23;	
	
	public BinaryFloatingPoint32Bit(String bin) {
		super(EXPONENT,MANTISSE,bin);		
	}
	
	public BinaryFloatingPoint32Bit(float num) {
		this(DF.format(num).toString().replace(",", "."));
	}
}
