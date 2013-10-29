package core;

import core.helpers.BinaryHelper;
import core.numbers.BinaryFloatingPoint;

public class Operation implements IArithmeticOperations {

	public BinaryFloatingPoint adder(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		
		// determine the difference D of the exponents
		int bfpExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(B.getExp().getBoolArr()));
		int thisExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(A.getExp().getBoolArr()));
		int d = bfpExp-thisExp;
		
		// select larges exponent
		boolean[] expLargest = (d > 0) ? B.getExp().getBoolArr() : A.getExp().getBoolArr(); 
		
		// Shift mantissa of the smaller number d times to the right; increase the exponent
		boolean[] manBig = null;
		boolean[] manSmall = null;

		if(d > 0) {
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
		
		
		return new BinaryFloatingPoint(new Exponent(expLargest), new Mantissa(manSum));
	}
	
	@Override
	public BinaryFloatingPoint add(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		
		BinaryFloatingPoint ATmp = new BinaryFloatingPoint(A);
		BinaryFloatingPoint BTmp = new BinaryFloatingPoint(B);
		
		if(A.isSign())
			ATmp = _2sComplement(ATmp);
		if(B.isSign())
			BTmp = _2sComplement(BTmp);
		
		// check the sign
		boolean sign = BinaryHelper.checkSumSign(A, B);
		
		BinaryFloatingPoint bfp = adder(ATmp,BTmp);
		
		if(A.isSign() || B.isSign()) {
			bfp = _2sComplement(bfp);
		}
		
		bfp.setSign(sign);
		
		return bfp;
	}
	
	@Override
	public BinaryFloatingPoint sub(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		if(A.isSign())
			A = _2sComplement(A);
		
		B = _2sComplement(B);
		
		boolean sign = BinaryHelper.checkDifSign(A, B);
		
		BinaryFloatingPoint bfp = add(A,B);
		bfp.setSign(sign);
		return bfp;
		
	}
	
	
//	@Override
//	public BinaryFloatingPoint sub(BinaryFloatingPoint A, BinaryFloatingPoint B) {
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
//		BinaryFloatingPoint result = new BinaryFloatingPoint(sign, new Exponent(expLargest), new Mantissa(manDiff));
//		
//		return result;
//	}

	@Override
	public BinaryFloatingPoint mul(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		return A.mul(B);
	}

	@Override
	public BinaryFloatingPoint div(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		return A.div(B);
	}
	
	private BinaryFloatingPoint _2sComplement(BinaryFloatingPoint num) {
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
		
		return new BinaryFloatingPoint(sign, new Exponent(exp), new Mantissa(man));
	}

}
