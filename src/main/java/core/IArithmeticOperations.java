package core;

import core.numbers.BinaryFloatingPoint;

public interface IArithmeticOperations {
	public BinaryFloatingPoint add(BinaryFloatingPoint A, BinaryFloatingPoint B);
	public BinaryFloatingPoint sub(BinaryFloatingPoint A, BinaryFloatingPoint B);
	public BinaryFloatingPoint mul(BinaryFloatingPoint A, BinaryFloatingPoint B);
	public BinaryFloatingPoint div(BinaryFloatingPoint A, BinaryFloatingPoint B);
}
