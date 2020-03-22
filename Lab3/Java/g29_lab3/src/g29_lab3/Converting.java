package g29_lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import g29_lab3.Number;

public class Converting {
	public static ArrayList<Number> convertTextInputsToFixedPoint(FileReader input, FileWriter output,int w,int f)
			throws Exception {
		BufferedReader reader = new BufferedReader(input);
		BufferedWriter writer = new BufferedWriter(output);
		
		ArrayList<Number> array = new ArrayList<Number>();
		boolean fractionalCount = false;
		Number number = new Number();
		String integer = "";
		String fraction = "";
		String fullNumberDecimalString="";
		int read = 0;

		// loop until end of file
		while ((read = reader.read()) != -1) {
			if (read == 32) { // space " "
				fractionalCount = false;
				fullNumberDecimalString = fullNumberDecimalString + fraction;
				number.setFraction(fraction);
				number.setFullNumberDecimalString(fullNumberDecimalString);
				number.wholeBuild(w, f);
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
}
