package core;

import core.helpers.BinaryHelper;
import core.numbers.BinaryFloatingPoint;

public class Operation implements IArithmeticOperations {
	
	@Override
	public BinaryFloatingPoint add(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		// check the sign
		boolean sign = BinaryHelper.checkSumSign(A, B);
		
		// determine the difference D of the exponents
		int bfpExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(B.getExp().getExpBoolArr()));
		int thisExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(A.getExp().getExpBoolArr()));
		int d = bfpExp-thisExp;
		
		// select larges exponent
		boolean[] expLargest = (d > 0) ? B.getExp().getExpBoolArr() : A.getExp().getExpBoolArr(); 
		
		// Shift mantissa of the smaller number d times to the right;
		boolean[] manBig = null;
		boolean[] manSmall = null;
		
		if(d > 0) {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getManBoolArr());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getManBoolArr()), Math.abs(d));
		} else {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getManBoolArr());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getManBoolArr()), Math.abs(d));
		}
		
		// Add the mantissas
		boolean[] manSum = BinaryHelper.addBinaryBoolArray(manBig, manSmall);
		
		// re-normalize the mantissa and exponent
		
		if(manSum[0] == false) {
			boolean[] newManSum = new boolean[manSum.length-1]; 
			for (int i = 1; i < manSum.length; i++) {
				newManSum[i-1] = manSum[i];
			}
			manSum = newManSum;
		}
		
		if(manSum.length > manBig.length) {
			expLargest = BinaryHelper.addBinaryBoolArray(expLargest, new boolean[]{true});
			
			if(expLargest.length > A.getExp().getExpBits()) {
				boolean[] newExp = new boolean[expLargest.length-1];
				for (int i = 1; i < expLargest.length; i++) {
					newExp[i-1] = expLargest[i];
				}
				expLargest = newExp;
			}
		}
		
		boolean[] newManSum = new boolean[manSum.length-1];
		for (int i = 1; i < manSum.length; i++) {
			newManSum[i-1] = manSum[i];
		}
		manSum = newManSum;
		
		// set the sign of the result
		BinaryFloatingPoint result = new BinaryFloatingPoint(sign, new Exponent(expLargest), new Mantissa(manSum));
		
		return result;
	}
	
	@Override
	public BinaryFloatingPoint sub(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		// check the sign
		boolean sign = BinaryHelper.checkDifSign(A, B);
		
		// determine the difference D of the exponents
		int bfpExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(B.getExp().getExpBoolArr()));
		int thisExp = BinaryHelper.convBinStringToDecInteger(BinaryHelper.binBoolArrayToString(A.getExp().getExpBoolArr()));
		int d = bfpExp-thisExp;
		
		// select larges exponent
		boolean[] expLargest = (d >= 0) ? B.getExp().getExpBoolArr() : A.getExp().getExpBoolArr(); 
		
		// Shift mantissa of the smaller number d times to the left (here it is meant the decimal point, however the number is shifted to the right)
		boolean[] manBig = null;
		boolean[] manSmall = null;
		
		if(d > 0) {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getManBoolArr());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getManBoolArr()), Math.abs(d));
		} else {
			manBig = BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, A.getMan().getManBoolArr());
			manSmall = BinaryHelper.shiftRight(BinaryHelper.mergeBinaryBoolArray(new boolean[]{true}, B.getMan().getManBoolArr()), Math.abs(d));
		}
		
		// Add the mantissas
		boolean[] manDiff = BinaryHelper.subBinaryBoolArray(manBig, manSmall);
		
		// re-normalize the mantissa and exponent
		
		if(manDiff[0] == false && manDiff.length > manBig.length) {
			manDiff = BinaryHelper.removeBitAtPos(manDiff, 0);
		}

		while(manDiff.length > manBig.length) {
			
//			expLargest = BinaryHelper.addBinaryBoolArray(expLargest, new boolean[]{true});
//			
//			if(expLargest.length > A.getExp().getExpBits()) {
//				boolean[] newExp = new boolean[expLargest.length-1];
//				for (int i = 1; i < expLargest.length; i++) {
//					newExp[i-1] = expLargest[i];
//				}
//				expLargest = newExp;
//			}
			
			manDiff = BinaryHelper.removeBitAtPos(manDiff, 0);			
		}
		
		// set the sign of the result
		BinaryFloatingPoint result = new BinaryFloatingPoint(sign, new Exponent(expLargest), new Mantissa(manDiff));
		
		return result;
	}

	@Override
	public BinaryFloatingPoint mul(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		return A.mul(B);
	}

	@Override
	public BinaryFloatingPoint div(BinaryFloatingPoint A, BinaryFloatingPoint B) {
		return A.div(B);
	}

}
