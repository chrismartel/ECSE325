package g29_lab3;

public class Number {
	private String integer;
	private String fraction;
	private boolean isNegative;
	private String fullNumberDecimalString;
	private double fullNumberDecimalDouble;
	private String fullNumberSignedBinary;
	private String fullNumberFixedPointNotation;
	private String extendedFixedPointNotation;
	private String fullNumberUnsignedBinary;
	private String integerBinary;
	private String fractionBinary;

	public Number() {
		fullNumberDecimalDouble = -999999;
	}

	public String getFraction() {
		return fraction;
	}

	public void setInteger(String integer) {
		this.integer = integer;
	}

	public void setFraction(String fraction) {
		this.fraction = fraction;
	}

	public String getInteger() {
		return integer;
	}

	public boolean isNegative() {
		return isNegative;
	}

	public void setNegative(boolean isNegative) {
		this.isNegative = isNegative;
	}

	public String getFullNumberDecimalString() {
		return fullNumberDecimalString;
	}

	public void setFullNumberDecimalString(String fullNumberDecimalString) {
		this.fullNumberDecimalString = fullNumberDecimalString;
	}

	public void setIntegerBinary(String integerBinary) {
		this.integerBinary = integerBinary;
	}

	public void setFractionBinary(String fractionBinary) {
		this.fractionBinary = fractionBinary;
	}

	public String getIntegerBinary() {
		return integerBinary;
	}

	public String getFractionBinary() {
		return fractionBinary;
	}

	public String getFullNumberSignedBinary() {
		return fullNumberSignedBinary;
	}

	public String getFullNumberUnsignedBinary() {
		return fullNumberUnsignedBinary;
	}

	public void setFullNumberSignedBinary(String fullNumberSignedBinary) {
		this.fullNumberSignedBinary = fullNumberSignedBinary;
	}

	public void setFullNumberUnsignedBinary(String fullNumberUnsignedBinary) {
		this.fullNumberUnsignedBinary = fullNumberUnsignedBinary;
	}

	public String getFullNumberFixedPointNotation() {
		return fullNumberFixedPointNotation;
	}

	public void setFullNumberFixedPointNotation(String fullNumberFixedPointNotation) {
		this.fullNumberFixedPointNotation = fullNumberFixedPointNotation;
	}

	public double getFullNumberDecimalDouble() {
		return fullNumberDecimalDouble;
	}

	public void setFullNumberDecimalDouble(double fullNumberDecimalDouble) {
		this.fullNumberDecimalDouble = fullNumberDecimalDouble;
	}

	public String getExtendedFixedPointNotation() {
		return extendedFixedPointNotation;
	}

	public void setExtendedFixedPointNotation(String extendedFixedPointNotation) {
		this.extendedFixedPointNotation = extendedFixedPointNotation;
	}
	
	// compute integer binary representation from integer decimal binary representation
	public String convertDecimalIntegerToBinary() throws Exception{
		String binary = "";
		String decimalIntegerString = this.getInteger();
		if(decimalIntegerString==null) {
			throw new Exception("Integer decimal string must be computed first!");
		}
		int decimalIntegerInt = Integer.parseInt(decimalIntegerString);
		// get unsigned value in bits
		binary = Integer.toBinaryString(decimalIntegerInt);
		if (binary.charAt(0) == 49) {
			binary = "0" + binary;
		}
		this.setIntegerBinary(binary);
		return binary;

	}

	// compute fraction binary representation from fraction decimal binary representation
	public String convertDecimalFractionToBinary() throws Exception{
		String binary = "";
		int decimalFractionLength = this.getFraction().length();
		String decimalFractionString = this.getFraction();
		if(decimalFractionString==null) {
			throw new Exception("Fraction decimal string must be computed first!");
		}
		double decimalFraction = (Double.parseDouble(decimalFractionString)) / (Math.pow(10, decimalFractionLength));

		while (decimalFraction != 0 && binary.length()<=20) {
			decimalFraction = decimalFraction * 2;
			if (decimalFraction >= 1) {
				decimalFraction = decimalFraction - 1;
				binary = binary + "1";
			} else {
				binary = binary + "0";
			}

		}
		this.setFractionBinary(binary);
		return binary;

	}
	
	//compute unsigned binary representation
	public String assembleUnsignedBinaryNumber() throws Exception{
		String unsignedBinaryNumber = "";
		String integerBinary = this.getIntegerBinary();
		String fractionBinary = this.getFractionBinary();
		if(integerBinary==null || fractionBinary==null) {
			throw new Exception("Integer binary representation and fraction binary representation must be computed first!");
		}
		unsignedBinaryNumber = integerBinary + fractionBinary;
		this.setFullNumberUnsignedBinary(unsignedBinaryNumber);
		return unsignedBinaryNumber;
	}
	
	// compute signed binary representation from unsigned representation
	public String assembleSignedBinaryNumber() throws Exception{
		String signedBinaryNumber = "";
		if(this.getFullNumberUnsignedBinary()==null) {
			throw new Exception("Unsigned binary representation must be computed first!");
		}
		if (this.isNegative()) {
			signedBinaryNumber = convertToSigned(this.getFullNumberUnsignedBinary());
		} else {
			signedBinaryNumber = this.getFullNumberUnsignedBinary();
		}
		this.setFullNumberSignedBinary(signedBinaryNumber);
		return signedBinaryNumber;
	}
	
	// compute decimal double representation from strings
	public double assembleFullNumberDecimalDouble() throws Exception{
		String integer = this.getInteger();
		String fraction = this.getFraction();
		if(integer==null || fraction==null) {
			throw new Exception("Integer decimal representation and fraction decimal representation must be computed first!");
		}
		double fullNumberDecimalDouble = Double.parseDouble(integer);
		double decimalFraction = Double.parseDouble(fraction) / (double) (Math.pow(10, fraction.length()));
		fullNumberDecimalDouble = fullNumberDecimalDouble + decimalFraction;
		if (this.isNegative()) {
			fullNumberDecimalDouble = fullNumberDecimalDouble * (-1);
		}
		this.setFullNumberDecimalDouble(fullNumberDecimalDouble);
		return fullNumberDecimalDouble;
	}

	// compute fixed point binary representation from signed representation
	public String assembleFixedPointNotation() throws Exception{
		if(this.getFractionBinary()==null || this.getIntegerBinary()==null || this.getFullNumberSignedBinary()==null) {
			throw new Exception("Integer binary representation, fraction binary representation and signed representation must be computed first!");
		}
		int f = this.getFractionBinary().length();
		int w = this.getFullNumberSignedBinary().length();
		String signed = this.getFullNumberSignedBinary();
		String fixedPoint = "";
		for (int i = 0; i < w; i++) {
			if (i == (w - f)) {
				fixedPoint = fixedPoint + signed.charAt(i);
			} else {
				fixedPoint = fixedPoint + signed.charAt(i);
			}
		}
		this.setFullNumberFixedPointNotation(fixedPoint);
		return fixedPoint;
	}
	
	// extend the fixed point binary representation
	public void extendFixedPointNotation(int w, int f) throws Exception {
		if(this.getFractionBinary()==null || this.getIntegerBinary()==null || this.getFullNumberSignedBinary()==null) {
			throw new Exception("Integer binary representation, fraction binary representation and signed representation must be computed first!");
		}
		int currentF = this.getFractionBinary().length();

		int diffF = f - currentF;
		String extendedFixedPoint = this.getFullNumberFixedPointNotation();
		
		if(diffF>=0) {
			for (int i = 0; i < diffF; i++) {
				// add 0 to fraction part
				extendedFixedPoint = extendedFixedPoint + "0";
			}
		}
		// f is smaller than the current length, need to round up
		else {
			int i = this.getIntegerBinary().length();
			String iPart = extendedFixedPoint.substring(0, i);
			extendedFixedPoint = iPart+extendedFixedPoint.substring(i,(i+15));
		}

		int currentW = extendedFixedPoint.length();
		if(w<currentW) {
			throw new Exception("w must be greater than the current full length");
		}
		int diffW = w - currentW;
		int sign = 0;
		if (extendedFixedPoint.charAt(0) == 49) {
			sign = 1;
		}
		for (int i = 0; i < diffW; i++) {
			extendedFixedPoint = sign + extendedFixedPoint;
		}
		this.setExtendedFixedPointNotation(extendedFixedPoint);
	}

	// set fraction, integer and whole string representation of number from double representation
	public void assembleNumberFromDouble() throws Exception{
		double decimalDouble = this.getFullNumberDecimalDouble();
		String decimalString = Double.toString(decimalDouble);
		String integer = "";
		String fraction = "";
		int length = decimalString.length();
		if (decimalString.charAt(0) == 45) {
			this.setNegative(true);
			decimalString = decimalString.substring(1);
			length--;
		} else {
			this.setNegative(false);
		}
		boolean fractionCount = false;
		boolean countEnable = true;
		char x;
		for (int i = 0; i < length; i++) {
			countEnable = true;
			x = decimalString.charAt(i);
			if (x == 46) {
				countEnable = false;
				fractionCount = true;
			}
			if (countEnable) {
				if (fractionCount) {
					fraction = fraction + x;
				} else {
					integer = integer + x;
				}
			}
		}
		this.setFraction(fraction);
		this.setInteger(integer);
		this.setFullNumberDecimalString(integer + "." + fraction);
		if (this.isNegative()) {
			this.setFullNumberDecimalString("-" + this.getFullNumberDecimalString());
		}
	}

	public static String convertToSigned(String unsigned) {
		String signed = "";
		int length = unsigned.length();
		// flip bits
		for (int i = 0; i < length; i++) {
			switch (unsigned.charAt(i)) {
			case (49):// 1
				signed = signed + "0";
				break;
			case (48):// 0
				signed = signed + "1";
				break;
			default:
			}
		}

		// add 1
		if (signed.charAt(length - 1) == 48) { // ends with 0
			// replace 0 by 1
			signed = signed.substring(0, (length - 1)) + "1";
		} else { // ends with one
			boolean carry = true;
			String temp = "";
			for (int i = (length - 1); i >= 0; i--) {
				if (carry) {
					if (signed.charAt(i) == 49) {
						carry = true;
					} else {
						carry = false;
					}
					if (carry) {
						temp = "0" + temp;
					} else {
						temp = "1" + temp;

					}
				} else {
					temp = signed.charAt(i) + temp;
				}
			}
			signed = temp;

		}
		return signed;
	}
	
	//build all number representations from decimal string
	public void wholeBuild(int w, int f) throws Exception {
		//impossible to build
		if(this.getFullNumberDecimalDouble()==-999999 && this.getFullNumberDecimalString()==null) {
			throw new Exception("Build impossible: need either double or string decimal representation");
		}
		else {
			if(this.getFullNumberDecimalDouble()==-999999) {
				this.assembleFullNumberDecimalDouble();
			}
			if(this.getFullNumberDecimalString()==null) {
				this.assembleNumberFromDouble();
			}
		}
		this.convertDecimalIntegerToBinary();
		this.convertDecimalFractionToBinary();
		this.assembleUnsignedBinaryNumber();
		this.assembleSignedBinaryNumber();
		this.assembleFixedPointNotation();
		this.extendFixedPointNotation(w, f);
	}
	

}