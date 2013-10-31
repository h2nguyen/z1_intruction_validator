package core;

import core.numbers.ZuseBinaryFloatingPoint24Bit;

public interface IArithmeticOperations {
	public ZuseBinaryFloatingPoint24Bit add(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B);
	public ZuseBinaryFloatingPoint24Bit sub(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B);
	public ZuseBinaryFloatingPoint24Bit mul(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B);
	public ZuseBinaryFloatingPoint24Bit div(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B);
}
