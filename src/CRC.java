import java.io.*;

public class CRC {

	public String remainder;
	
	 public String div(String dividend, String divisor) {
		int pointer = divisor.length();
		String result = dividend.substring(0, pointer);
		remainder = "";
		xor(result, divisor);
		
		while (pointer < dividend.length()) {
			if (remainder.charAt(0) == '0') {
				remainder = remainder.substring(1, remainder.length());
				remainder = remainder + String.valueOf(dividend.charAt(pointer));
				pointer++;
			}
			result = remainder;
			remainder = "";
			xor(result, divisor);
		}
		return remainder.substring(1, remainder.length());
	}
	
	public void xor(String result, String divisor){
		
		if(result.charAt(0) == '0'){
			int divisorLength = divisor.length();
			divisor = "";
			for(int j=0; j< divisorLength; j++){
				divisor = divisor + '0';
			}
		}
		
		for (int i = 0; i < divisor.length(); i++) {
			
			if (result.charAt(i) == divisor.charAt(i))
				remainder += "0";
			else
				remainder += "1";
		}
	}
}