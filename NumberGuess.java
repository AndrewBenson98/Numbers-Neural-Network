public class NumberGuess {

	public static void main(String[] args) {

		int[][] trainingSetInput = new int[][] { 
				{ 0, 0, 0 },
				// {0,0,1},
				{ 0, 1, 0 }, 
				{ 0, 1, 1 }, 
				{ 1, 0, 0 }, 
				{ 1, 0, 1 },
				// {1,1,0},
				{ 1, 1, 1 }, };

		int[] trainingSetOutput = new int[] { 0, 0, 0, 1, 1, 1 };

		double[] weights = new double[3];

		// assign random weights
		for (int i = 0; i < 3; i++) {
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

		System.out.println("Weight #1: " + weights[0]);
		System.out.println("Weight #2: " + weights[1]);
		System.out.println("Weight #3: " + weights[2]);

		// test 1 1 0 or 0 0 1
		int[] test = new int[] { 0, 0, 1 };
		System.out.println("Output for [0 0 1] : " + sum(test, weights));

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
