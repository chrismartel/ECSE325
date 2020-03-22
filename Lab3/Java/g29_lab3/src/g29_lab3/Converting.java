package g29_lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import g29_lab3.Number;

public class Converting {
	public static ArrayList<Number> convertNumbersToFixedPointSingleLine(FileReader input, FileWriter output, int w,
			int f) throws Exception {
		BufferedReader reader = new BufferedReader(input);
		BufferedWriter writer = new BufferedWriter(output);

		ArrayList<Number> array = new ArrayList<Number>();
		boolean fractionalCount = false;
		Number number = new Number();
		String integer = "";
		String fraction = "";
		String fullNumberDecimalString = "";
		int read = 0;

		// loop until end of file
		while ((read = reader.read()) != -1) {
			if (read == 32) { // space " "
				fractionalCount = false;
				if (fraction.length() > 10) {
					fraction = fraction.substring(0, 15);
				}
				number.setFraction(fraction);
				fullNumberDecimalString = fullNumberDecimalString + fraction;
				number.setFullNumberDecimalString(fullNumberDecimalString);
				number.wholeBuild(w, f);
				System.out.println(
						number.getExtendedFixedPointNotation() + " vs " + number.getFullNumberFixedPointNotation());
				writer.write(number.getExtendedFixedPointNotation());
				writer.newLine();
				Number newNumber = number;
				array.add(newNumber);
				number = new Number();
				fraction = "";
				integer = "";
				fullNumberDecimalString = "";
			}

			else {
				if (read == 46) {// dot "."
					fractionalCount = true;
					if (integer.length() > 20) {
						integer = integer.substring(0, 20);
					}
					fullNumberDecimalString = fullNumberDecimalString + integer + ".";
					number.setInteger(integer);

				} else {
					if (fractionalCount) {
						fraction = fraction + ((char) read);
					} else {
						if (read == 45) {// dash "-"
							number.setNegative(true);
							fullNumberDecimalString = "-";
						} else {
							integer = integer + ((char) read);

						}
					}
				}

			}
		}
		reader.close();
		writer.close();
		return array;
	}

	public static ArrayList<Number> convertNumbersToFixedPointMultipleLines(FileReader input, FileWriter output, int w,
			int f) throws Exception {
		BufferedReader reader = new BufferedReader(input);
		BufferedWriter writer = new BufferedWriter(output);

		ArrayList<Number> array = new ArrayList<Number>();
		boolean fractionalCount = false;
		Number number = new Number();
		String integer = "";
		String fraction = "";
		String fullNumberDecimalString = "";
		String line = "";

		// loop until end of file
		while ((line = reader.readLine()) != null) {
			number = new Number();
			integer = "";
			fraction = "";
			fullNumberDecimalString = "";
			fractionalCount = false;
			// for each character in line
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) != 32)// not a space " "
				{
					if (line.charAt(i) == 46)// dot "."
					{
						fractionalCount = true;
						if (integer.length() > 20) {
							integer = integer.substring(0, 20);
						}
						fullNumberDecimalString = fullNumberDecimalString + integer + ".";
						number.setInteger(integer);

					} else {
						if (fractionalCount) {
							fraction = fraction + ((char) line.charAt(i));
						} else {
							if (line.charAt(i) == 45) // dash
							{
								number.setNegative(true);
								fullNumberDecimalString = "-";
							} else {
								integer = integer + ((char) line.charAt(i));
							}
						}
					}
				}
			}
			// all characters of line checked
			if (fraction.length() > 10) {
				fraction = fraction.substring(0, 15);
			}
			fullNumberDecimalString = fullNumberDecimalString + fraction;
			number.setFraction(fraction);
			number.setFullNumberDecimalString(fullNumberDecimalString);
			number.wholeBuild(w, f);
			System.out.println(
					number.getExtendedFixedPointNotation() + " vs " + number.getFullNumberFixedPointNotation());
			System.out.println(number.getFullNumberDecimalDouble());
			writer.write(number.getExtendedFixedPointNotation());
			writer.newLine();
			Number newNumber = number;
			array.add(newNumber);

		}
		reader.close();
		writer.close();
		return array;
	}
}
