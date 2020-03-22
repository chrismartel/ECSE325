package g29_lab3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import g29_lab3.Converting;
import g29_lab3.Number;
import g29_lab3.FIR;
public class Main {
	public static void main(String[] args) throws Exception {
		int inputW = 16;
		int intputF = 15;
		int coeffW = 16;
		int coeffF = 15;
		int outputW = 17;
		int outputF = 15;
		int filterOrder = 25;
		FileReader input = new FileReader("src/lab3-In.txt");
		FileWriter converted_input = new FileWriter("src/lab3-In-converted.txt",false);
		ArrayList<Number> numberInputs = Converting.convertNumbersToFixedPointSingleLine(input, converted_input,inputW,intputF);
		double[] inputs = new double[numberInputs.size()];
		for(int i = 0;i<numberInputs.size();i++) {
			inputs[i] = numberInputs.get(i).getFullNumberDecimalDouble();
		}
		
		FileReader coeff = new FileReader("src/lab3-coef.txt");
		FileWriter converted_coeff = new FileWriter("src/lab3-coef-converted.txt",false);
		System.out.println("WAAAA\n");
		ArrayList<Number> numberCoeffs = Converting.convertNumbersToFixedPointMultipleLines(coeff, converted_coeff,coeffW,coeffF);
		double[] coeffs = new double[numberCoeffs.size()];
		for(int i = 0;i<numberCoeffs.size();i++) {
			System.out.println(numberCoeffs.get(i).getFullNumberDecimalDouble());
			coeffs[i] = numberCoeffs.get(i).getFullNumberDecimalDouble();
		}
		
		double[] outputs = FIR.firNthOrderFilter(inputs, coeffs, 25);
		for(double output: outputs) {
			System.out.println(output);
		}
	}



}