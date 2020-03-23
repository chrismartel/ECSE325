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
		
		// CONVERT INPUTS --> (1,15) FIXED-POINT
		FileReader input = new FileReader("src/lab3-In.txt");
		FileWriter converted_input = new FileWriter("src/lab3-In-converted.txt",false);
		ArrayList<Number> numberInputs = Converting.convertNumbersToFixedPointSingleLine(input, converted_input,inputW,intputF);
		double[] inputs = new double[numberInputs.size()];
		for(int i = 0;i<numberInputs.size();i++) {
			inputs[i] = numberInputs.get(i).getExtendedNumberDecimalDouble();
		}
		
		// CONVERT COEFFICIENTS --> (1,15) FIXED-POINT
		FileReader coeff = new FileReader("src/lab3-coef.txt");
		FileWriter converted_coeff = new FileWriter("src/lab3-coef-converted.txt",false);
		ArrayList<Number> numberCoeffs = Converting.convertNumbersToFixedPointMultipleLines(coeff, converted_coeff,coeffW,coeffF);
		double[] coeffs = new double[numberCoeffs.size()];
		for(int i = 0;i<numberCoeffs.size();i++) {
			coeffs[i] = numberCoeffs.get(i).getExtendedNumberDecimalDouble();
		}
		
		
		// CREATE EXPECTED OUTPUTS --> (2,15) FIXED-POINT
		double[] outputs = FIR.firNthOrderFilter(inputs, coeffs, 25);
		FileWriter outputText = new FileWriter("src/lab3-out.txt",false);
		BufferedWriter bw = new BufferedWriter(outputText);
		for(double output: outputs) {
			Number n = new Number();
			n.setFullNumberDecimalDouble(output);
			n.wholeBuild(17, 15);
			bw.write(n.getExtendedFixedPointNotation());
			bw.newLine();
		}
		bw.close();
		
		
		// ASSERT EXPECTED OUTPUTS AND RESULTS
		boolean filesAreAsserted = Converting.assertEqualFiles(new FileReader("src/lab3-out.txt"), new FileReader("src/lab3-results.txt"));
		if(filesAreAsserted) {
			System.out.println("Expected outputs and results outputs are the same!");
		}
		else {
			System.out.println("Expected outputs and results outputs are not the same...");
		}
		
		
		// RMSE
		//Convert actual outputs
		FileReader actual = new FileReader("src/out.txt_scaled.txt");
		FileWriter parsed_actual = new FileWriter("src/actualValues.txt",false);
		ArrayList<Number> actualValues = Converting.convertNumbersToFixedPointSingleLine(actual, parsed_actual,17,15);
		
		ArrayList<Number> estimatedValues1 = Number.buildArrayCopy(16, 14, actualValues);
		ArrayList<Number> estimatedValues2 = Number.buildArrayCopy(15, 13, actualValues);
		ArrayList<Number> estimatedValues3 = Number.buildArrayCopy(14, 12, actualValues);
		ArrayList<Number> estimatedValues4 = Number.buildArrayCopy(13, 11, actualValues);
		ArrayList<Number> estimatedValues5 = Number.buildArrayCopy(12, 10, actualValues);
		ArrayList<Number> estimatedValues6 = Number.buildArrayCopy(11, 9, actualValues);
		ArrayList<Number> estimatedValues7 = Number.buildArrayCopy(10, 8, actualValues);
		ArrayList<Number> estimatedValues8 = Number.buildArrayCopy(9, 7, actualValues);
		ArrayList<Number> estimatedValues9 = Number.buildArrayCopy(8, 6, actualValues);
		ArrayList<Number> estimatedValues10 = Number.buildArrayCopy(7, 5, actualValues);
		ArrayList<Number> estimatedValues11 = Number.buildArrayCopy(6, 4, actualValues);
		ArrayList<Number> estimatedValues12 = Number.buildArrayCopy(5, 3, actualValues);
		ArrayList<Number> estimatedValues13 = Number.buildArrayCopy(4, 2, actualValues);
		ArrayList<Number> estimatedValues14 = Number.buildArrayCopy(3, 1, actualValues);


		double rmse1 = FIR.RMSE(estimatedValues1, actualValues);
		//System.out.println(rmse1);
		double rmse2 = FIR.RMSE(estimatedValues2, actualValues);
		//System.out.println(rmse2);
		double rmse3 = FIR.RMSE(estimatedValues3, actualValues);
		//System.out.println(rmse3);
		double rmse4 = FIR.RMSE(estimatedValues4, actualValues);
		//System.out.println(rmse4);
		double rmse5 = FIR.RMSE(estimatedValues5, actualValues);
		//System.out.println(rmse5);
		double rmse6 = FIR.RMSE(estimatedValues6, actualValues);
		//System.out.println(rmse6);
		double rmse7 = FIR.RMSE(estimatedValues7, actualValues);
		//System.out.println(rmse7);
		double rmse8 = FIR.RMSE(estimatedValues8, actualValues);
		//System.out.println(rmse8);
		double rmse9 = FIR.RMSE(estimatedValues9, actualValues);
		//System.out.println(rmse9);
		double rmse10 = FIR.RMSE(estimatedValues10, actualValues);
		//System.out.println(rmse10);
		double rmse11 = FIR.RMSE(estimatedValues11, actualValues);
		//System.out.println(rmse11);
		double rmse12 = FIR.RMSE(estimatedValues12, actualValues);
		//System.out.println(rmse12);
		double rmse13 = FIR.RMSE(estimatedValues13, actualValues);
		//System.out.println(rmse13);
		double rmse14 = FIR.RMSE(estimatedValues14, actualValues);
		//System.out.println(rmse14);
		
		// RCLL SIMULATION
		
		// CONVERT INPUTS --> (1,2) FIXED-POINT
		FileReader inputRCLL = new FileReader("src/lab3-In.txt");
		FileWriter converted_inputRCLL = new FileWriter("src/lab3-In-converted-RCLL.txt",false);
		ArrayList<Number> numberInputsRCLL = Converting.convertNumbersToFixedPointSingleLine(inputRCLL, converted_inputRCLL,3,2);
		double[] inputsRCLL = new double[numberInputsRCLL.size()];
		for(int i = 0;i<numberInputsRCLL.size();i++) {
			numberInputsRCLL.get(i).assembleExtendedDouble();
			inputsRCLL[i] = numberInputsRCLL.get(i).getExtendedNumberDecimalDouble();
		}

		// CONVERT COEFFICIENTS --> (1,2) FIXED-POINT
		FileReader coeffRCLL = new FileReader("src/lab3-coef.txt");
		FileWriter converted_coeffRCLL = new FileWriter("src/lab3-coef-converted-RCLL.txt",false);
		ArrayList<Number> numberCoeffsRCLL = Converting.convertNumbersToFixedPointMultipleLines(coeffRCLL, converted_coeffRCLL,3,2);
		double[] coeffsRCLL = new double[numberCoeffsRCLL.size()];
		for(int i = 0;i<numberCoeffsRCLL.size();i++) {
			numberCoeffsRCLL.get(i).assembleExtendedDouble();
			coeffsRCLL[i] = numberCoeffsRCLL.get(i).getExtendedNumberDecimalDouble();
		}

		// CREATE EXPECTED OUTPUTS --> (2,2) FIXED-POINT
		double[] outputsRCLL = FIR.firNthOrderFilter(inputsRCLL, coeffsRCLL, 25);
		FileWriter outputTextRCLL = new FileWriter("src/lab3-out-RCLL.txt",false);
		BufferedWriter bwRCLL = new BufferedWriter(outputTextRCLL);
		for(double output: outputsRCLL) {
			Number n = new Number();
			n.setFullNumberDecimalDouble(output);
			n.wholeBuild(4, 2);
			bwRCLL.write(n.getExtendedFixedPointNotation());
			bwRCLL.newLine();
		}
		bwRCLL.close();
		
		// ASSERT EXPECTED OUTPUTS AND RESULTS
		boolean filesAreAssertedRCLL = Converting.assertEqualFiles(new FileReader("src/lab3-out-RCLL.txt"), new FileReader("src/lab3-RCLL-results.txt"));
		if(filesAreAsserted) {
			System.out.println("Expected outputs and results outputs are the same!");
		}
		else {
			System.out.println("Expected outputs and results outputs are not the same...");
		}


	}



}