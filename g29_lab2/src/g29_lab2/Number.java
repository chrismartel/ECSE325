package g29_lab2;

public class Number {
	private String integer;
	private String fraction;
	private boolean isNegative;
	private String fullNumberDecimal;
	private String fullNumberSignedBinary;
	private String fullNumberUnsignedBinary;

	private String integerBinary;
	private String fractionBinary;
	private int fractionBinaryLength;
	private int integerBinaryLength;
	private int binaryLength;
	
	public Number(){
		this.fullNumberSignedBinary="";
		this.fullNumberUnsignedBinary="";

		this.fullNumberDecimal="";
		this.integerBinary="";
		this.fractionBinary="";
		
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
	public void setFullNumberDecimal(String fullNumberDecimal) {
		this.fullNumberDecimal = fullNumberDecimal;
	}

	 public String getFullNumberDecimal() {
		return fullNumberDecimal;
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
}
