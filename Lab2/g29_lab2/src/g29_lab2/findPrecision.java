package g29_lab2;

import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author Christian Martel
 * @author Luka Loignon
 */

public class findPrecision {

	// 'space' --> 32
	// '.' --> 46
	public static void main(String args[]) throws IOException {
		FileReader inputx = new FileReader("lab2-x.txt");
		FileReader inputy = new FileReader("lab2-y.txt");
		BufferedReader bufferx = new BufferedReader(inputx);
		BufferedReader buffery = new BufferedReader(inputy);
		int n = 1000;
		int xw;
		int xf;
		int yw;
		int yf;
		int productw;
		int productf;
		int sumw;
		int sumf;
		int outputw;
		int outputf;
		// parse text files and create Numbers with integer and fraction part in decimal
		ArrayList<Number> parseX = parseText(bufferx, n);
		ArrayList<Number> parseY = parseText(buffery, n);

		for (Number number : parseX) {
			convertDecimalFractionToBinary(number);
			convertDecimalIntegerToBinary(number);
			assembleUnsignedBinaryNumber(number);
			assembleSignedBinaryNumber(number);
			assembleFixedPointNotation(number);
			assembleFullNumberDecimalDouble(number);
		}
		xf = findMaxF(parseX);
		xw = xf + findMaxI(parseX);
		System.out.println("x:  f-->"+xf+", w-->"+xw);
		// xf=7, xw=10

		for (Number number : parseY) {
			convertDecimalFractionToBinary(number);
			convertDecimalIntegerToBinary(number);
			assembleUnsignedBinaryNumber(number);
			assembleSignedBinaryNumber(number);
			assembleFixedPointNotation(number);
			assembleFullNumberDecimalDouble(number);
		}
		yf = findMaxF(parseY);
		yw = yf + findMaxI(parseY);
		System.out.println("y:  f-->"+yf+", w-->"+yw);
		// yf=7, yw=10

		
		
		ArrayList<Number> products = computeProductsWF(parseX,parseY,n);
		ArrayList<Number> sums = computeSums(products,n);
		for(int i =0;i<n;i++) {
			assembleNumberFromDouble(products.get(i));
			convertDecimalFractionToBinary(products.get(i));
			convertDecimalIntegerToBinary(products.get(i));
			assembleUnsignedBinaryNumber(products.get(i));
			assembleSignedBinaryNumber(products.get(i));
			assembleFixedPointNotation(products.get(i));
			
			assembleNumberFromDouble(sums.get(i));
			convertDecimalFractionToBinary(sums.get(i));
			convertDecimalIntegerToBinary(sums.get(i));
			assembleUnsignedBinaryNumber(sums.get(i));
			assembleSignedBinaryNumber(sums.get(i));
			assembleFixedPointNotation(sums.get(i));
		}
		productf = findMaxF(products);
		productw = findMaxI(products)+productf;
		System.out.println("product:  f-->"+productf+", w-->"+productw);
		sumf = findMaxF(sums);
		sumw = findMaxI(sums)+sumf;
		System.out.println("sum:  f-->"+sumf+", w-->"+sumw);


	}

	public static ArrayList<Number> parseText(BufferedReader reader, int n) throws IOException {
		ArrayList<Number> parsedArray = new ArrayList<Number>();
		boolean fractionalCount = false;
		String integer = "";
		String fraction = "";
		int c = 0;

		// repeat 1000 times
		for (int i = 0; i < n; i++) {
			Number number = new Number();

			while ((c = reader.read()) != 32) {

				if (c == 46) {
					fractionalCount = true;
					number.setFullNumberDecimalString(number.getFullNumberDecimalString() + ".");

				} else {
					if (fractionalCount) {
						fraction = fraction + ((char) c);

					} else {
						if (c == 45) {
							number.setNegative(true);
						} else {
							integer = integer + ((char) c);

						}
					}
					number.setFullNumberDecimalString(number.getFullNumberDecimalString() + ((char) c));
				}

			}
			number.setInteger(integer);
			if (fraction.equals("")) {
				fraction = "0";
			}
			number.setFraction(fraction);
			parsedArray.add(i, number);
			fraction = "";
			integer = "";
			fractionalCount = false;
		}

		return parsedArray;

	}

	public static String convertDecimalIntegerToBinary(Number number) {
		String binary = "";
		String decimalIntegerString = number.getInteger();

		int decimalIntegerInt = Integer.parseInt(decimalIntegerString);
		// get unsigned value in bits
		binary = Integer.toBinaryString(decimalIntegerInt);
		if (binary.charAt(0) == 49) {
			binary = "0" + binary;
		}
		number.setIntegerBinary(binary);
		number.setIntegerBinaryLength(binary.length());
		return binary;

	}

	public static String convertDecimalFractionToBinary(Number number) {
		String binary = "";
		int decimalFractionLength = number.getFraction().length();
		double decimalFraction = (Long.parseLong(number.getFraction())) / (Math.pow(10, decimalFractionLength));
		
		while (decimalFraction != 0) {
			decimalFraction = decimalFraction * 2;
			if (decimalFraction >= 1) {
				decimalFraction = decimalFraction - 1;
				binary = binary + "1";
			} else {
				binary = binary + "0";
			}

		}
		number.setFractionBinary(binary);
		number.setFractionBinaryLength(binary.length());
		return binary;

	}

	public static String assembleUnsignedBinaryNumber(Number number) {
		String unsignedBinaryNumber = "";
		unsignedBinaryNumber = number.getIntegerBinary() + number.getFractionBinary();
		number.setFullNumberUnsignedBinary(unsignedBinaryNumber);
		number.setBinaryLength(number.getFractionBinaryLength() + number.getIntegerBinaryLength());
		return unsignedBinaryNumber;
	}
	
	public static double assembleFullNumberDecimalDouble(Number number) {
		double fullNumberDecimalDouble = Integer.parseInt(number.getInteger());
		double decimalFraction = Integer.parseInt(number.getFraction())/Math.pow(10, number.getFraction().length());
		fullNumberDecimalDouble = fullNumberDecimalDouble+decimalFraction;
		if(number.isNegative()) {
			fullNumberDecimalDouble = fullNumberDecimalDouble*(-1);
		}
		number.setFullNumberDecimalDouble(fullNumberDecimalDouble);
		return fullNumberDecimalDouble;
	}

	public static String assembleSignedBinaryNumber(Number number) {
		String signedBinaryNumber = "";
		if (number.isNegative()) {
			signedBinaryNumber = convertToSigned(number.getFullNumberUnsignedBinary());
		} else {
			signedBinaryNumber = number.getFullNumberUnsignedBinary();
		}
		number.setFullNumberSignedBinary(signedBinaryNumber);
		return signedBinaryNumber;
	}

	public static String assembleFixedPointNotation(Number number) {
		int f = number.getFractionBinaryLength();
		int w = number.getBinaryLength();
		String signed = number.getFullNumberSignedBinary();
		String fixedPoint = "";
		for (int i = 0; i < w; i++) {
			if (i == (w - f)) {
				fixedPoint = fixedPoint + "." + signed.charAt(i);
			} else {
				fixedPoint = fixedPoint + signed.charAt(i);
			}
		}
		number.setFullNumberFixedPointNotation(fixedPoint);
		return fixedPoint;
	}
	
	public static void assembleNumberFromDouble(Number number){
		double decimalDouble = number.getFullNumberDecimalDouble();
		String decimalString = Double.toString(decimalDouble);
		String integer = "";
		String fraction = "";
		int length = decimalString.length();
		if(decimalString.charAt(0)==45) {
			number.setNegative(true);
			decimalString = decimalString.substring(1);
			length--;
		}
		else {
			number.setNegative(false);
		}
		boolean fractionCount=false;
		boolean countEnable = true;
		char x;
		for(int i =0;i<length;i++) {
			countEnable = true;
			x = decimalString.charAt(i);
			if(x==46) {
				countEnable = false;
				fractionCount = true;
			}
			if(countEnable) {
				if(fractionCount) {
					fraction = fraction + x;
				}
				else {
					integer = integer + x;
				}
			}
		}
		number.setFraction(fraction);
		number.setInteger(integer);
		number.setFullNumberDecimalString(integer+"."+fraction);
		if(number.isNegative()) {
			number.setFullNumberDecimalString("-"+number.getFullNumberDecimalString());
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
	

	public static ArrayList<Number> computeProductsWF(ArrayList<Number> numbersX, ArrayList<Number> numbersY, int n) {
		ArrayList<Number> products = new ArrayList<Number>();
		for(int i=0;i<n;i++) {
			Number number = new Number(); 
			double product = numbersX.get(i).getFullNumberDecimalDouble()*numbersY.get(i).getFullNumberDecimalDouble();
			number.setFullNumberDecimalDouble(product);
			number.setBinaryLength(findMulW(numbersX.get(i),numbersY.get(i)));
			number.setFractionBinaryLength(findMulF(numbersX.get(i),numbersY.get(i)));
			number.setIntegerBinaryLength(number.getBinaryLength()-number.getFractionBinaryLength());
			products.add(number);
		}
		return products;
	}
	
	public static ArrayList<Number> computeProducts(ArrayList<Number> numbersX, ArrayList<Number> numbersY, int n) {
		ArrayList<Number> products = new ArrayList<Number>();
		for(int i=0;i<n;i++) {
			Number number = new Number(); 
			double product = numbersX.get(i).getFullNumberDecimalDouble()*numbersY.get(i).getFullNumberDecimalDouble();
			number.setFullNumberDecimalDouble(product);
			products.add(number);
		}
		return products;
	}
	
	public static ArrayList<Number> computeSums(ArrayList<Number> products, int n) {
		ArrayList<Number> sums = new ArrayList<Number>();
		double totalSum=0;
		for(int i=0;i<n;i++) {
			Number number = new Number();
			double sum = products.get(i).getFullNumberDecimalDouble()+totalSum;
			number.setFullNumberDecimalDouble(sum);
			totalSum = sum;
			sums.add(number);
		}
		return sums;
	}
	
	public static int findMaxI(ArrayList<Number> numbers) {
		int maxI = 0;
		for(Number number: numbers) {
			int w = number.getIntegerBinaryLength();
			if(w>maxI)
				maxI=w;
		}
		return maxI;
	}
	
	public static int findMaxF(ArrayList<Number> numbers) {
		int maxF = 0;
		for(Number number: numbers) {
			int f = number.getFractionBinaryLength();
			if(f>maxF)
				maxF=f;
		}
		return maxF;
	}
	
	public static int findAddW(Number n1, Number n2) {
		int w=  Math.max(n1.getBinaryLength(), n2.getBinaryLength())+1;
		return w;
	}
	
	public static int findAddF(Number n1, Number n2) {
		int f= Math.max(n1.getFractionBinaryLength(),n2.getFractionBinaryLength());
		return f;
	}
	
	
	public static int findMulW(Number n1, Number n2) {
		int w= n1.getBinaryLength()+n2.getBinaryLength();
		return w;
	}
	
	public static int findMulF(Number n1, Number n2) {
		int f=n1.getFractionBinaryLength()+n2.getFractionBinaryLength();
		return f;
	}

}
