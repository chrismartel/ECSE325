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
		// parse text files and create Numbers with integer and fraction part in decimal
		ArrayList<Number> parseX = parseText(bufferx, n);
		ArrayList<Number> parseY = parseText(buffery, n);
		System.out.println(convertToSigned("0000"));

		for(Number number: parseX) {
			convertDecimalFractionToBinary(number);
			convertDecimalIntegerToBinary(number);
			assembleUnsignedBinaryNumber(number);
			System.out.println(assembleUnsignedBinaryNumber(number));

		}
		for(Number number: parseY) {
			convertDecimalFractionToBinary(number);
			convertDecimalIntegerToBinary(number);
			assembleUnsignedBinaryNumber(number);

		}
		
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
					number.setFullNumberDecimal(number.getFullNumberDecimal()+".");

				} else {
					if (fractionalCount) {
						fraction = fraction + ((char) c);

					} else {
						if (c == 45) {
							number.setNegative(true);
						}
						else {
							integer = integer + ((char) c);

						}
					}
					number.setFullNumberDecimal(number.getFullNumberDecimal()+((char) c));
				}

			}
			number.setInteger(integer);
			if(fraction.equals("")){
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
		if(binary.charAt(0)==49) {
			binary = "0"+binary;
		}
		number.setIntegerBinary(binary);
		number.setIntegerBinaryLength(binary.length());
		return binary;

	}
	public static String convertDecimalFractionToBinary(Number number) {
		String binary = "";
		int decimalFractionLength = number.getFraction().length();
		double decimalFraction = (Integer.parseInt(number.getFraction()))/(Math.pow(10, decimalFractionLength));
		while(decimalFraction!=0) {
			 decimalFraction = decimalFraction*2;
			if(decimalFraction>=1) {
				decimalFraction = decimalFraction-1;
				binary = binary+"1";
			}
			else {
				binary = binary+"0";
			}
			
		}
		number.setFractionBinary(binary);
		number.setFractionBinaryLength(binary.length());
		return binary;

	}
	
	public static String assembleUnsignedBinaryNumber(Number number) {
		String unsignedBinaryNumber = "";
		unsignedBinaryNumber = number.getIntegerBinary()+number.getFractionBinary();
		number.setBinaryLength(number.getFractionBinaryLength()+number.getIntegerBinaryLength());
		return unsignedBinaryNumber;
	}
	
	public static String convertToSigned(String unsigned) {
		String signed = "";
		int length = unsigned.length();
		//flip bits
		for(int i =0;i<length;i++) {
			switch(unsigned.charAt(i)) {
			case(49)://1
				signed = signed + "0";
			break;
			case(48)://0
				signed = signed + "1";
			break;
			default:
			}
		}
		
		//add 1
		if(signed.charAt(length-1)==48) { //ends with 0
			// replace 0 by 1
			signed = signed.substring(0, (length-1))+"1";
		}
		else { // ends with one
			boolean carry = true;
			String temp = "";
			for(int i=(length-1);i>=0;i--) {
				if(carry) {
					if(signed.charAt(i)==49) {
						carry=true;
					}
					else {
						carry=false;
					}
					if(carry) {
						temp = "0"+temp;
					}
					else {
						temp = "1"+temp;

					}
				}
				else {
					temp = signed.charAt(i)+temp;
				}
			}
			signed = temp;

		}
		return signed;
	}

}
