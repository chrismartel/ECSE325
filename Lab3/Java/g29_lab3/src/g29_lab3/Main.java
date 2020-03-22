package g29_lab3;
import java.io.BufferedWriter;
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
			inputs[i] = numberInputs.get(i).getExtendedNumberDecimalDouble();
		}
		
		FileReader coeff = new FileReader("src/lab3-coef.txt");
		FileWriter converted_coeff = new FileWriter("src/lab3-coef-converted.txt",false);
		ArrayList<Number> numberCoeffs = Converting.convertNumbersToFixedPointMultipleLines(coeff, converted_coeff,coeffW,coeffF);
		double[] coeffs = new double[numberCoeffs.size()];
		for(int i = 0;i<numberCoeffs.size();i++) {
			coeffs[i] = numberCoeffs.get(i).getExtendedNumberDecimalDouble();
		}
		
		double[] outputs = FIR.firNthOrderFilter(inputs, coeffs, 25);
		FileWriter outputText = new FileWriter("src/lab3-out.txt",false);
		BufferedWriter bw = new BufferedWriter(outputText);
		for(double output: outputs) {
			bw.write(Double.toString(output));
			bw.newLine();
			System.out.println(output);
		}
		bw.close();
	}



}