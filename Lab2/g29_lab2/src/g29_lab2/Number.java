package g29_lab2;

public class Number {
	private String integer;
	private String fraction;
	private boolean isNegative;
	private String fullNumberDecimalString;
	private double fullNumberDecimalDouble;
	private String fullNumberSignedBinary;
	private String fullNumberFixedPointNotation;

	private String fullNumberUnsignedBinary;

	private String integerBinary;
	private String fractionBinary;
	private int fractionBinaryLength; // f
	private int integerBinaryLength;
	private int binaryLength; // w

	public Number() {
		this.fullNumberSignedBinary = "";
		this.fullNumberUnsignedBinary = "";

		this.fullNumberDecimalString = "";
		this.integerBinary = "";
		this.fractionBinary = "";

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

	public int getBinaryLength() {
		return binaryLength;
	}

	public int getFractionBinaryLength() {
		return fractionBinaryLength;
	}

	public void setBinaryLength(int binaryLength) {
		this.binaryLength = binaryLength;
	}

	public void setFractionBinaryLength(int fractionBinaryLength) {
		this.fractionBinaryLength = fractionBinaryLength;
	}

	public void setIntegerBinaryLength(int integerBinaryLength) {
		this.integerBinaryLength = integerBinaryLength;
	}

	public int getIntegerBinaryLength() {
		return integerBinaryLength;
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
}
