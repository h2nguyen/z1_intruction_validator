package core;

import core.helpers.BinaryHelper;
import core.numbers.ZuseBinaryFloatingPoint24Bit;

public class Operation implements IArithmeticOperations {
	
	public ZuseBinaryFloatingPoint24Bit adder(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		
		// determine the difference D of the exponents
		int aExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(A.getExp().getBoolArr()));
		int bExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(B.getExp().getBoolArr()));
		int d = bExp-aExp;
		
		// select larges exponent
		boolean[] expLargest = (d > 0) ? B.getExp().getBoolArr() : A.getExp().getBoolArr(); 
		
		// Shift mantissa of the smaller number d times to the right; increase the exponent
		boolean[] manBig = null;
		boolean[] manSmall = null;

		if(d > 0) {
			// add the hidden bit
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getBoolArr());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getBoolArr()), Math.abs(d));
		} else {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getBoolArr());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getBoolArr()), Math.abs(d));
		}
		
		// Add the mantissas
		boolean[] manSum = BinaryHelper.addBinaryBoolArray(manBig, manSmall);
		
		// normalize the mantissa and exponent
		if(manSum[0] == false) {
			manSum = BinaryHelper.removeBitAtPos(manSum, 0);
		}
		
		if(manSum.length > manBig.length) {
			expLargest = BinaryHelper.addBinaryBoolArray(expLargest, new boolean[]{true});
			while(expLargest.length > A.getExp().getBits()) {
				expLargest = BinaryHelper.removeBitAtPos(expLargest, 0);
				if(manSum.length > A.getMan().getBits()) {
					manSum = BinaryHelper.removeBitAtPos(manSum, manSum.length - 1);
				}
			}
		}
		while(manSum.length > A.getMan().getBits()) {
			manSum = BinaryHelper.removeBitAtPos(manSum, 0);			
		}
		
		
		return new ZuseBinaryFloatingPoint24Bit(new Exponent(expLargest), new Mantissa(manSum));
	}
	
	public ZuseBinaryFloatingPoint24Bit adder2(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		
		boolean[] Af = A.getExp().getCopiedBoolArr();
		boolean[] Ag = B.getExp().getCopiedBoolArr();
		boolean[] Bf = A.getMan().getCopiedBoolArr();
		boolean[] Bg = B.getMan().getCopiedBoolArr();
		
		
		boolean[] Aa = new boolean[ZuseBinaryFloatingPoint24Bit.EXPONENT];
		boolean[] Ab = new boolean[ZuseBinaryFloatingPoint24Bit.EXPONENT];
		boolean[] Ba = new boolean[ZuseBinaryFloatingPoint24Bit.MANTISSE];
		boolean[] Bb = new boolean[ZuseBinaryFloatingPoint24Bit.MANTISSE];
		
		
		boolean[] Ae = null;
		boolean[] Be = null;
		
		boolean s0 = false;
		boolean s1 = false;
		boolean s3 = false;
		
		boolean ph = false;
		boolean lz = false;
		
		// determine the difference d of the exponents
		while (!ph) {
			Aa = copy(Af);			
			Ab = neg(Ag);
			
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE + 1);
			reset(Aa,Ab,Ba,Bb);
		} // PH 0
		
		ph = false;
		
		while (!ph) {
			if(Ae[0])
				s1 = true;
			else 
				s1 = false;
			Aa = copy(Ae);

			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE + 1);
			reset(Aa,Ab,Ba,Bb);
		} // PH 1
		
		ph = false;
		
		// check if exponent is greater equal zero
		while (!ph) {
			if(s1) {
				Aa = copy(Ae);
				Bb = copy(Bg);
				ph = true;
			} else {
				Aa = neg(Ae);
				Bb = copy(Bf);
				ph = true;
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE + 1);
			reset(Aa,Ab,Ba,Bb);
		} // PH 2

		ph = false;
		
		while (!ph) {
			int ae = bin2dec(Ae);
			if(ae == 0) {
				Aa = copy(Ae);
				Ba = copy(Be);
				//Ae = BinaryHelper.shiftRight(Ae, 1);
				ph = true;
			} else {
				Aa = copy(Ae);
				Ab = _minus1();
				Bb = BinaryHelper.shiftRight(Be,1);				
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE + 1);
			reset(Aa,Ab,Ba,Bb);
		} // PH 3
		
		ph = false;
		
		while (!ph) {
			if(s0) {
				Ba = neg(Be);
			} else {
				Ba = copy(Be);
			}
			
			if(s1) {
				Aa = copy(Af);
				Bb = copy(Bf);
				ph = true;
			} else {
				Ab = copy(Ag);
				Bb = copy(Bg);
				ph = true;
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE + 1);
			reset(Aa,Ab,Ba,Bb);
		} // PH 4
		
		ph = false;

		while (!ph) {
			// Be[0] ^= Be_1; Be[1] ^= Be_0; Be[2] ^= Be_-1 ...
			if(!Be[0]) {
				Aa = copy(Ae);
				Ba = copy(Be);
				//GOTO ende;
				lz = true;
				break;
			} else {
				Aa = copy(Ae);
				Ab = _plus1();
				Bb = BinaryHelper.shiftRight(Be, 1);
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE + 1);
			reset(Aa,Ab,Ba,Bb);
		} // PH 5
		
		if(lz) {
			return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));
		}
		
		ph = false;
//		if(Ae.length == Ab.length + 1) { // check sign of difference
//			boolean sign = Ae[0];
//			while(Ae.length > Ab.length) 
//				Ae = BinaryHelper.removeBitAtPos(Ae, 0);
//			int s2 = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(Ae));
//			
//			if(sign && s2 >= 0) { // exponent difference is positive
//				Aa = Ae;
//				Bb = B.getMan().getBoolArr();
//			} else {
//				Aa = BinaryHelper.twosComplement(Ae);
//				Bb = A.getMan().getBoolArr();
//			}
//		}
		
		return null;
//		// select larges exponent
//		boolean[] expLargest = (d > 0) ? B.getExp().getBoolArr() : A.getExp().getBoolArr(); 
//		
//		// Shift mantissa of the smaller number d times to the right; increase the exponent
//		boolean[] manBig = null;
//		boolean[] manSmall = null;
//
//		if(d > 0) {
//			// add the hidden bit
//			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getBoolArr());
//			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getBoolArr()), Math.abs(d));
//		} else {
//			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getBoolArr());
//			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getBoolArr()), Math.abs(d));
//		}
//		
//		// Add the mantissas
//		boolean[] manSum = BinaryHelper.addBinaryBoolArray(manBig, manSmall);
//		
//		// normalize the mantissa and exponent
//		if(manSum[0] == false) {
//			manSum = BinaryHelper.removeBitAtPos(manSum, 0);
//		}
//		
//		if(manSum.length > manBig.length) {
//			expLargest = BinaryHelper.addBinaryBoolArray(expLargest, new boolean[]{true});
//			while(expLargest.length > A.getExp().getBits()) {
//				expLargest = BinaryHelper.removeBitAtPos(expLargest, 0);
//				if(manSum.length > A.getMan().getBits()) {
//					manSum = BinaryHelper.removeBitAtPos(manSum, manSum.length - 1);
//				}
//			}
//		}
//		while(manSum.length > A.getMan().getBits()) {
//			manSum = BinaryHelper.removeBitAtPos(manSum, 0);			
//		}
//		
//		
//		return new ZuseBinaryFloatingPoint24Bit(new Exponent(expLargest), new Mantissa(manSum));
	}
	


	private boolean[] copy(boolean[] arr) {
		boolean[] copied = new boolean[arr.length];
		
		for (int i = 0; i < copied.length; i++) {
			copied[i] = arr[i];
		}
		return copied;
	}

	private int bin2dec(boolean[] bin) {
		boolean sign = bin[0];
		
		boolean[] newBin = BinaryHelper.removeBitAtPos(bin, 0);
		
		int dec = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(newBin));
		
		if(!sign)
			dec *= -1.0;
			
		return dec;
	}
	
	private boolean[] _plus1() {
		return new boolean[]{false, false, false, false, false, false, true};
	}

	private boolean[] _minus1() {
		return BinaryHelper.twosComplement(_plus1());
	}

	private boolean[] neg(boolean[] ar) {
		return BinaryHelper.twosComplement(ar);
	}

	private void reset(boolean[] aa, boolean[] ab, boolean[] ba, boolean[] bb) {
		resetArray(aa);
		resetArray(ab);
		resetArray(ba);
		resetArray(bb);
	}
	
	private void resetArray(boolean[] array) {
		for (int a = 0; a < array.length; a++) {
			array[a] = false;
		}
	}

	public ZuseBinaryFloatingPoint24Bit add(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		
		ZuseBinaryFloatingPoint24Bit ATmp = new ZuseBinaryFloatingPoint24Bit(A);
		ZuseBinaryFloatingPoint24Bit BTmp = new ZuseBinaryFloatingPoint24Bit(B);
		
		if(A.isSign())
			ATmp = _2sComplement(ATmp);
		if(B.isSign())
			BTmp = _2sComplement(BTmp);
		
		// check the sign
		boolean sign = BinaryHelper.zuseCheckSumSign(A, B);
		
		ZuseBinaryFloatingPoint24Bit bfp = adder(ATmp,BTmp);
		
		if(bfp.isSign()) {
			bfp = _2sComplement(bfp);
		}
		
		bfp.setSign(sign);
		
		return bfp;
	}
	
	public ZuseBinaryFloatingPoint24Bit sub(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		if(A.isSign())
			A = _2sComplement(A);
		
		B = _2sComplement(B);
		
		boolean sign = BinaryHelper.zuseCheckDifSign(A, B);
		
		ZuseBinaryFloatingPoint24Bit bfp = add(A,B);
		bfp.setSign(sign);
		return bfp;
		
	}
	
	
//	@Override
//	public ZuseBinaryFloatingPoint24Bit sub(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
//		// check the sign
//		boolean sign = BinaryHelper.checkDifSign(A, B);
//		
//		// determine the difference D of the exponents
//		int bfpExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(B.getExp().getExpBoolArr()));
//		int thisExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(A.getExp().getExpBoolArr()));
//		int d = thisExp - bfpExp;
//		
//		// select larges exponent
//		boolean[] expLargest = (d >= 0) ? A.getExp().getExpBoolArr() : B.getExp().getExpBoolArr(); 
//		
//		// Shift mantissa of the smaller number d times to the left (here it is meant the decimal point, however the number is shifted to the right)
//		boolean[] manBig = null;
//		boolean[] manSmall = null;
//		
//		if(d > 0) {
//			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getManBoolArr());
//			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getManBoolArr()), Math.abs(d));
////			manBig = A.getMan().getManBoolArr();
////			manSmall = BinaryHelper.shiftRight(B.getMan().getManBoolArr(), Math.abs(d));
//		} else {
//			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getManBoolArr());
//			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getManBoolArr()), Math.abs(d));
////			manBig = B.getMan().getManBoolArr();
////			manSmall = BinaryHelper.shiftRight(A.getMan().getManBoolArr(), Math.abs(d));
//		}
//		
//		// Add the mantissas
//		boolean[] manDiff = BinaryHelper.subBinaryBoolArray(manBig, manSmall);
//		
//		// re-normalize the mantissa and exponent
//		
//		if(manDiff[0] == false && manDiff.length > manBig.length) {
//			manDiff = BinaryHelper.removeBitAtPos(manDiff, 0);
//		}
//
//		while(manDiff.length > manBig.length) {
//			
//			manDiff = BinaryHelper.removeBitAtPos(manDiff, 0);			
//		}
//		
//		// set the sign of the result
//		ZuseBinaryFloatingPoint24Bit result = new ZuseBinaryFloatingPoint24Bit(sign, new Exponent(expLargest), new Mantissa(manDiff));
//		
//		return result;
//	}

	public ZuseBinaryFloatingPoint24Bit mul(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		// get sign
		boolean sign = BinaryHelper.zuseCheckMulSign(A, B);
		
		// adding the exponents
		boolean[] expLargest = BinaryHelper.addBinaryBoolArray(A.getExp().getBoolArr(), B.getExp().getBoolArr());
		
	
		boolean[] tmpB = B.getMan().getCopiedBoolArr();
		
		boolean[] partialResult = new boolean[B.getMan().getBoolArr().length];
		
		for (int i = 0; i < tmpB.length; i++) {
					
			boolean shiftOutBit = tmpB[tmpB.length - 1];
			
			boolean[] a = BinaryHelper.shiftRight(A.getMan().getBoolArr(), i);
			
			if(shiftOutBit) {
				partialResult = BinaryHelper.addBinaryBoolArray(partialResult, a);
			}
			
			tmpB = BinaryHelper.shiftRight(tmpB, i);
		}		
		
		return new ZuseBinaryFloatingPoint24Bit(sign, new Exponent(expLargest), new Mantissa(partialResult));
	}

	public ZuseBinaryFloatingPoint24Bit multip(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		// adding the exponents
		boolean[] expLargest = BinaryHelper.addBinaryBoolArray(A.getExp().getBoolArr(), B.getExp().getBoolArr());
				
		for (int i = 0; i < A.getMan().getBits() + 2; i++) {
			
		}
		
		return null;
	}
	
	public ZuseBinaryFloatingPoint24Bit div(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		return null;
	}
	
	private ZuseBinaryFloatingPoint24Bit _2sComplement(ZuseBinaryFloatingPoint24Bit num) {
		boolean sign = num.isSign();
		
		boolean exp[] = num.getExp().getCopiedBoolArr();
		boolean man[] = BinaryHelper.twosComplement(num.getMan().getCopiedBoolArr());
		
		while(man.length > num.getMan().getBoolArr().length) {
			man = BinaryHelper.removeBitAtPos(man, man.length - 1);
			exp = BinaryHelper.addBinaryBoolArray(exp, new boolean[]{true});
			while(exp.length > num.getExp().getBoolArr().length) {
				exp = BinaryHelper.removeBitAtPos(exp, exp.length - 1);
			}
		}
		
		if(man[0])
			sign = !num.isSign();
		
		return new ZuseBinaryFloatingPoint24Bit(sign, new Exponent(exp), new Mantissa(man));
	}

}
