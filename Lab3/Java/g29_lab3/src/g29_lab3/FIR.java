package g29_lab3;

import java.util.ArrayList;

public class FIR {
	public static double[] firNthOrderFilter(double[] inputs, double[] coeffs, int N) {
		double[] outputs = new double[inputs.length];
		// for all remaining inputs
		for (int i = 0; i < inputs.length; i++) {
			// partial products
			double[] products = new double[N];
			double sum = 0;
			// for each coefficients
			for (int j = 0; j < N; j++) {
				if ((i - j) < 0) {
					products[j] = 0;
				} else {
					products[j] = coeffs[j] * inputs[i - j];
				}
			}
			for (int j = 0; j < N; j++) {
				sum = sum + products[j];
			}
			outputs[i] = sum;
		}
		return outputs;
	}
	
	public static double RMSE(ArrayList<Number> estimatedValues, ArrayList<Number> actualValues) {
		double sum = 0;
		int N = actualValues.size();
		for(int i = 0;i<N;i++) {
			sum = sum + Math.pow((estimatedValues.get(i).getExtendedNumberDecimalDouble()-actualValues.get(i).getExtendedNumberDecimalDouble()), 2);
		}
		double rmse = Math.sqrt((sum/N));
		return rmse;
	}
}
