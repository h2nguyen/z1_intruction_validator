package core;

import core.helpers.BinaryHelper;
import core.numbers.ZuseBinaryFloatingPoint24Bit;

public class Operation implements IArithmeticOperations {
	
	public ZuseBinaryFloatingPoint24Bit adder(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B, boolean[] s) {
		
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
		
		
		boolean lz = false;
		boolean ph = false;
		
		// determine the difference d of the exponents
		while (!ph) {
			Aa = copy(Af);			
			Ab = neg(Ag);
			
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 0
		
		ph = false;
		// select largest exponent
		while (!ph) {
			if(Ae[0])
				s[1] = true;
			else 
				s[1] = false;
			if(Ae.length > Aa.length)
				Ae = BinaryHelper.removeBitAtPos(Ae, 0);
				
			Aa = copy(Ae);

			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 1
		
		ph = false;
		// check if exponent is greater equal zero
		while (!ph) {
			if(s[1]) {
				Aa = copy(Ae);
				Bb = copy(Bg);
				ph = true;
			} else {
				Aa = neg(Ae);
				Bb = copy(Bf);
				ph = true;
			}
			
			if(s[1])
				Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);
			else 
				Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 2

		ph = false;
		
		while (!ph) {
			int ae = bin2dec(Ae);
			
			Aa = copy(Ae);
			
			if(ae == 0) {
				Ba = copy(Be);
				ph = true;
			} else {
				Ab = _minus1();
				Bb = BinaryHelper.shiftRight(Be,1);				
			}

			if(ae == 0)
				Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);
			else {
				Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);
			}
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 3
		
		ph = false;
		
		while (!ph) {
			if(s[0]) {
				Ba = copy(Be);
			} else {
				Ba = neg(Be);
			}
			
			if(s[1]) {
				Aa = copy(Af);
				Bb = copy(Bf);
				ph = true;
			} else {
				Ab = copy(Ag);
				Bb = copy(Bg);
				ph = true;
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);			
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 4
		
		ph = false;

		while (!ph) {
			// Be[0] ^= Be_1; Be[1] ^= Be_0; Be[2] ^= Be_-1 ...
			if(s[0] == true) {
				if(Be[0] == false) {
					Aa = copy(Ae);
					Ba = copy(Be);
					lz = true;
					break;
				} else {
					Aa = copy(Ae);
					Ab = _plus1();
					Bb = BinaryHelper.shiftRight(Be, 1);
				}
			} else {
				
				if(Be[0]) {
					Aa = copy(Ae);
					Ba = neg(Be);
					s[3] = true;
					ph = true;
				} else {
					Aa = copy(Ae);
					Bb = copy(Be);
					ph = true;
				}
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);		
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 5
		
		if(lz) {
			return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));
		}
		
		ph = false;

		while (!ph) {
			if(s[0] == false) {
				if(Be[1] == false) {
					Aa = copy(Ae);
					Ab = _minus1();
					Ba = BinaryHelper.shiftLeft(Be, 1);
				} else {
					lz = true;
					Bb = copy(Be);
					break;
				}
			}
			
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT + 1);			
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 6
		
		if(Ae.length > ZuseBinaryFloatingPoint24Bit.EXPONENT) {
			Ae = BinaryHelper.removeBitAtPos(Ae, 0);
		}
		
		if(lz) {
			return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));
		}
		
		return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));

	}
	
	private ZuseBinaryFloatingPoint24Bit multiplicator(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B, boolean[] s) {
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
		
		
		boolean lz = false;
		boolean ph = false;
		
		// determine the difference d of the exponents
		while (!ph) {
			Aa = copy(Af);			
			Ab = copy(Ag);
			
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 0
		
		ph = false;
		
		for (int phase = 1; phase <= 17; phase++) {
			
			while (!ph) {
				
				Aa = copy(Ae);
				int pos = Bf.length - phase;
				boolean mm = Bf[pos]; 
				Ba = BinaryHelper.shiftRight(Be,1);
				
				
				
				if(mm)
					Bb = copy(Bg);

				ph = true;
				Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);		
				Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
				reset(Aa,Ab,Ba,Bb);
			} // PH 1...17
			
			ph = false;
		}
		
		ph = false;
		
		while (!ph) {
			Aa = copy(Ae);
			if(Be[0] == false) {
				Ba = copy(Be);
				ph = true;
				
			} else {
				Ab = _plus1();
				Bb = BinaryHelper.shiftRight(Be,1);
				
			}

			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 18
		
		ph = false;
		
		while (!ph) {
			Aa = copy(Ae);
			if(Be[0] == true) {
				Ab = _plus1();
				Bb = BinaryHelper.shiftRight(Be,1);
			} else {
				Ba = copy(Be);
			}
			
			
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 19
		
		if(lz) {
			return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));
		}
		
		ph = false;

		
		return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));
	}
	
	private ZuseBinaryFloatingPoint24Bit divisor(ZuseBinaryFloatingPoint24Bit A,	ZuseBinaryFloatingPoint24Bit B, boolean[] s) {
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
		
		
		boolean lz = false;
		boolean ph = false;
		
		// determine the difference d of the exponents
		while (!ph) {
			Aa = copy(Af);			
			Ab = neg(Ag);
			Bb = copy(Bf);
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 0
		
		ph = false;
		
		while (!ph) {
			Aa = copy(Ae);
			Ba = copy(Be);			
			Bb = neg(Bg);
			
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 1
		
		ph = false;
		
		for (int phase = 2; phase <= 17; phase++) {
			
			while (!ph) {
				
				Aa = copy(Ae);
								
				boolean uPlus2 = false;
				if(uPlus2  == false)
					Bb = copy(Bg);
				else
					Bb = neg(Bg);
				Ba = BinaryHelper.shiftLeft(Be, 1);

				ph = true;
				Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);		
				Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
				reset(Aa,Ab,Ba,Bb);
			} // PH 2...17
			
			ph = false;
		}
		
		ph = false;
		
		while (!ph) {
			Aa = copy(Ae);
			Bb = copy(Bf);
			
			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 19
		
		ph = false;
		
		while (!ph) {
			
			if(Be[1] == false) {
				Aa = copy(Ae);
				Ab = _minus1();
				
				Ba = BinaryHelper.shiftLeft(Be, 1);
			} else {
				Aa = copy(Ae);
				Bb = copy(Be);
			}

			ph = true;
			Ae = BinaryHelper.normAddBinaryBoolArray(Aa, Ab, ZuseBinaryFloatingPoint24Bit.EXPONENT);	
			Be = BinaryHelper.normAddBinaryBoolArray(Ba, Bb, ZuseBinaryFloatingPoint24Bit.MANTISSE);
			reset(Aa,Ab,Ba,Bb);
		} // PH 20
		
		ph = false;

		
		return new ZuseBinaryFloatingPoint24Bit(new Exponent(Ae), new Mantissa(Be));
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
		
		boolean[] s = new boolean[]{true, true, false, false, false, false, true, false};
		
		ZuseBinaryFloatingPoint24Bit bfp = adder(ATmp,BTmp,s);
		
		if(bfp.isSign()) {
			bfp = _2sComplement(bfp);
		}
		
		bfp.setSign(sign);
		
		return bfp;
	}
	
	public ZuseBinaryFloatingPoint24Bit sub(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		
		ZuseBinaryFloatingPoint24Bit ATmp = new ZuseBinaryFloatingPoint24Bit(A);
		ZuseBinaryFloatingPoint24Bit BTmp = new ZuseBinaryFloatingPoint24Bit(B);
		
		if(A.isSign())
			ATmp = _2sComplement(ATmp);
		if(B.isSign())
			BTmp = _2sComplement(BTmp);
		
		// check the sign
		boolean sign = BinaryHelper.zuseCheckDifSign(A, B);
		
		boolean[] s = new boolean[]{false, true, false, false, false, false, true, false};
		
		ZuseBinaryFloatingPoint24Bit bfp = adder(ATmp,BTmp,s);
		
		if(bfp.isSign()) {
			bfp = _2sComplement(bfp);
		}
		
		bfp.setSign(sign);
		
		return bfp;
	}

	public ZuseBinaryFloatingPoint24Bit mul(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		ZuseBinaryFloatingPoint24Bit ATmp = new ZuseBinaryFloatingPoint24Bit(A);
		ZuseBinaryFloatingPoint24Bit BTmp = new ZuseBinaryFloatingPoint24Bit(B);
		// check the sign
		boolean sign = BinaryHelper.zuseCheckMulSign(A, B);
		
		boolean[] s = new boolean[]{false, false, true, false, false, false, true, false};
		
		ZuseBinaryFloatingPoint24Bit bfp = multiplicator(ATmp,BTmp,s);
		
		if(bfp.isSign()) {
			bfp = _2sComplement(bfp);
		}
		
		bfp.setSign(sign);
		
		return bfp;
	}
	
	public ZuseBinaryFloatingPoint24Bit div(ZuseBinaryFloatingPoint24Bit A, ZuseBinaryFloatingPoint24Bit B) {
		ZuseBinaryFloatingPoint24Bit ATmp = new ZuseBinaryFloatingPoint24Bit(A);
		ZuseBinaryFloatingPoint24Bit BTmp = new ZuseBinaryFloatingPoint24Bit(B);
		// check the sign
		boolean sign = BinaryHelper.zuseCheckMulSign(A, B);
		
		boolean[] s = new boolean[]{true, false, true, false, false, false, true, false};
		
		ZuseBinaryFloatingPoint24Bit bfp = divisor(ATmp,BTmp,s);
		
		if(bfp.isSign()) {
			bfp = _2sComplement(bfp);
		}
		
		bfp.setSign(sign);
		
		return bfp;
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

}
