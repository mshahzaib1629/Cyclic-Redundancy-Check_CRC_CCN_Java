import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
	
	static CRC crc = new CRC();
	static int efficency = 0;
	
	public static void main(String args[]) throws IOException {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		String rec;
		String tempCode;
		String data = "";
		String divisor = "";
		
		System.out.println("Enter Data Size:");
		int dataSize = input.nextInt();
		for (int i = 0; i < dataSize; i++) {
			data = data + rand.nextInt(2);
		}		
		String code = data;

		System.out.println("Enter Divisor Size:");
		int divisorSize = input.nextInt();
		if(divisorSize <= 1){
			System.out.println("Divisor can't be less or equal than 1");
			return;
		}
		
		divisor = "1";
		for (int i = 1; i < divisorSize; i++) {
			divisor = divisor + rand.nextInt(2);
		}
		
		System.out.println("Current Data: " + code);
		System.out.println("Current Divisor: " + divisor);
		if(divisor.length() > data.length()){
			System.out.println("Divisor can not be greater than Dividend!");
			return;
		}
		if(divisor.charAt(0)=='0'){
			System.out.println("Divisor can't be start with 0");
			return;
		}
		

		while (code.length() < (data.length() + divisor.length() - 1))
			code = code + "0";

		code = data + crc.div(code, divisor);
		
		System.out.println("Data Transmitted: " + code);
		
		System.out.println("Enter no. of Iterations: ");
		int iterate = input.nextInt();
		
		System.out.println("No. \t Sent \t\t\t Received \t\tStatus");

		for (int i = 1; i <= iterate; i++) {

			int random;
			int randValue = rand.nextInt(2);

			random = rand.nextInt(code.length());
			
			System.out.print(i + ". \t");
			System.out.print(code);
			rec = code.substring(0, random) + randValue + code.substring(random + 1);
			System.out.print("\t\t" + rec);
			passToCRC(rec, divisor);

		}
		
		System.out.println("Efficency: " + (efficency/(float)iterate)*100 + "%");
	}
	
	public static void passToCRC(String rec, String divisor){
		try {
			if (Integer.parseInt(crc.div(rec, divisor)) == 0){
				System.out.print("\t\tNo Error Detected!");
				//return;
			}
			else{
				System.out.print("\t\tErrors Detected!");
				efficency = efficency + 1;
			}
			System.out.println();
			
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid entry, enter again...");
		}
	}
}
