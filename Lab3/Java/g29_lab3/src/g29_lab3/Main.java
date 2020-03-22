package g29_lab3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import g29_lab3.Converting;
import g29_lab3.Number;
public class Main {
	public static void main(String[] args) throws Exception {
		FileReader input = new FileReader("src/lab3-In.txt");
		FileWriter converted_input = new FileWriter("src/lab3-In-converted.txt",false);
		ArrayList<Number> inputs = Converting.convertTextInputsToFixedPoint(input, converted_input,16,15);
	}



}
