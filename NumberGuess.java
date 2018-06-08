import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumberGuess {

	public static void main(String[] args) throws FileNotFoundException {
		
		int numOfElements =7;
		String fileName ="binaryNumbers.txt";
		boolean reRun= true;
		int[] test = new int[] { 1, 1, 1,0,1,1,1 };
		

		BinaryNumberGenerator bng = new BinaryNumberGenerator();
		
		if(reRun) {
		bng.NumGen(numOfElements, fileName);
		}
		
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		
		int[][] trainingSetInput = new int[(int)Math.pow(2, numOfElements)][numOfElements];
		int[] trainingSetOutput = new int[(int)Math.pow(2, numOfElements)];
		
		//Write in test data to array----------------------------------------------
		for(int i =0;i<(int)Math.pow(2, numOfElements);i++) {
			
			for(int j =0; j<numOfElements;j++) {
				trainingSetInput[i][j]= sc.nextInt();
			}
			trainingSetOutput[i]= sc.nextInt();
		}
		
			
		
		double[] weights = new double[numOfElements];

		// assign random weights
		for (int i = 0; i < numOfElements; i++) {
			weights[i] = Math.random();
			System.out.printf("Initial Weight #%d: %f\n", i, weights[i]);
		}
		double output;
		for (int i = 0; i < 1000000; i++) {

			// get output
			for (int j = 0; j < trainingSetInput.length; j++) {
				output = sum(trainingSetInput[j], weights);

				weights = adjustWeights(trainingSetInput[j], trainingSetOutput[j], output, weights);
			}

		} // end main loop


		for(int i =0; i<numOfElements;i++) {
			System.out.printf("Weight #%d: %f\n",i+1,weights[i]);
		}
		
		double result = sum(test, weights);
		
		System.out.printf("Output for %s : %f\n",test.toString(),result);
		
		if(result > 0.5)
		{
			System.out.println("The output is 1");
		}else {
			System.out.println("The output is 0");
		}
		sc.close();

	}

	// Multiple inputs and weights and return output
	public static double sum(int[] testInputs, double[] inputWeights) {

		double output = 0;
		for (int i = 0; i < testInputs.length; i++) {
			output += testInputs[i] * inputWeights[i];
		}
		return sigmoid(output);
	}

	// Normalize the output to be between 0 and 1
	public static double sigmoid(double x) {
		return (1 / (1 + Math.pow(Math.E, (-1 * x))));
	}

	// Get output gradiant
	public static double sigmoidCurveGradiant(double output) {
		return output * (1 - output);
	}

	// Adjust the weights
	public static double[] adjustWeights(int[] testInput, int testOutput, double output, double[] weights) {

		// double [] weights= new double [3];

		double error;
		// System.out.println("TEST OUTPUT: "+Arrays.toString(testOutput));
		for (int i = 0; i < testInput.length; i++) {
			error = testOutput - output;
			// System.out.printf("ERROR: %f INPUT: %d GRADIANT:
			// %f\n",error,testInput[i],sigmoidCurveGradiant(output));
			double adjustment = error * testInput[i] * sigmoidCurveGradiant(output);
			// System.out.println("ADJUSTMENT"+adjustment);
			weights[i] = weights[i] + adjustment;

		}

		return weights;

	}

}
