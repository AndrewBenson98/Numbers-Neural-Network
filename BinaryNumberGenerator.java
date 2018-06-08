
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BinaryNumberGenerator {

	public void NumGen(int size,String file) throws FileNotFoundException {
		
		PrintWriter writer= new PrintWriter(file);
		
		//int size =7;
		int [] array = new int[size];
		String line= "";
		//Initialize the array
		for(int i =0;i<size;i++) {
			array[i]=0;
		}

		for(int i =(int)Math.pow(2,size)-1; i>=0;i--) {
			if(array[size-1]==0) {
				array[size-1]=1;
			}else if(array[size-1]==1) {
				
				//Flip all the 1's and the next 0
				for(int j =size-1;j>=0;j--) {
					if(array[j]==1) {
						array[j]=0;
					}else if(array[j]==0) {
						array[j]=1;
						break;
					}
				}
				
			}
			for(Integer a:array) {
				System.out.printf("%d ",a);
				line += a+" ";
				
			}
			
			
			//------------------------------------------------------------------------------------------
			
			//Sets what the output is (Remember that the numbers are separated by spaces!)
			//The output for a string is 1 if the second number is 1 and 0 if the second number is 0
//			if(line.charAt(2)=='1') {
//				writer.println(line+"     1");
//			}else {
//				writer.println(line+"     0");
//			}
//			line="";
//			System.out.println();
			
			//------------------------------------------------------------------------------------------
			
			//The output is 1 if the sum of the numbers in a line is even
			//calculate sum
//			int sum=0;
//			for(int k =0;k<size*2;k+=2) {
//				sum+= Character.getNumericValue(line.charAt(k));
//			}
//			if(sum %2==0) {
//				writer.println(line+"     1");
//				//System.out.printf("     %d\n",sum);
//			}else {
//				writer.println(line+"     0");
//				//System.out.printf("     %d\n",sum);
//			}
//			line="";
//			System.out.println();
			//------------------------------------------------------------------------------------------
			
			//Output is 1 if first 2 numbers are the same
			if(line.charAt(0)==line.charAt(2)) {
				writer.println(line+"     1");
			}else {
				writer.println(line+"     0");
			}
			line="";
			System.out.println();
//			
			
			
		}
		writer.close();
		
	}

}
