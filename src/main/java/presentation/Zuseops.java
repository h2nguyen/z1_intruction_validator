package presentation;

import core.Operation;
import core.numbers.ZuseBinaryFloatingPoint24Bit;

public class Zuseops {

	public static void main(String[] args) {
		if(args.length < 3) {
			System.err.println("Not enough arguments!");
			System.err.println(getHelp());
			System.exit(0);
		}
		
		try {
			
		
			float a = (float) Float.valueOf(args[1]);
			float b = (float) Float.valueOf(args[2]);
			
			if(a < 0 || b < 0) {
				System.err.println("Numbers have to be positive");
				System.err.println(getHelp());
				System.exit(0);
			}
			
			
			ZuseBinaryFloatingPoint24Bit bfp24bit1 = new ZuseBinaryFloatingPoint24Bit(a);
			ZuseBinaryFloatingPoint24Bit bfp24bit2 = new ZuseBinaryFloatingPoint24Bit(b);
			
			switch (args[0])  {
				case "add":
					System.out.println("Addition: "+a+"+"+b);
					ZuseBinaryFloatingPoint24Bit addres = new Operation().add(bfp24bit1, bfp24bit2);
					System.out.println("adder: " + addres.floatValue(true));
					break;
				case "sub":
					System.out.println("\nSubstraction: "+a+"-"+b);
					ZuseBinaryFloatingPoint24Bit subres = new Operation().sub(bfp24bit1, bfp24bit2);
					System.out.println("substracter: " + subres.floatValue(true));
					break;
				case "mul":
					System.out.println("\nMuliplication: "+a+"*"+b);
					ZuseBinaryFloatingPoint24Bit mulres = new Operation().mul(bfp24bit1, bfp24bit2);
					System.out.println("multiplier: " + mulres.floatValue(true));
					break;
				case "div":
					System.out.println("\nDivision: "+a+"/"+b);
					ZuseBinaryFloatingPoint24Bit divres = new Operation().div(bfp24bit1, bfp24bit2);
					System.out.println("divisor: " + divres.floatValue(true));
					break;
				default:
					System.err.println("Wrong input operation!");
					System.err.println(getHelp());
					System.exit(0);
			}
		} catch (Exception e) {
			System.err.println("Wrong number format input!");
			System.err.println(getHelp());
			System.exit(0);
		}
		
		
	}

	private static String getHelp() {
		String help = "";
		help += "Following usage command: \n"
				+ "java -jar zuseops.jar [add|sub|mul|div] operand1(as positive number) operand2(as positive number)\n";
		help += "e.g.: java -jar zuseops.jar add 18 3\n";
		
		return help;
	}

}
