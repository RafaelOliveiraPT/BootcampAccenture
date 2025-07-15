package pt.upacademy.examples;

import java.util.Scanner;

public class Util {

	public static Scanner reader = new Scanner(System.in);
	
	public static String getLineFromKeyboard() {
		String auxStr;
		
		while(true) {
			auxStr = reader.nextLine();
			if(auxStr.length() != 0)
				break;
			System.out.print("Tem que inserir um valor...");
		}	
		return auxStr;
	}
	
	public static int getIntFromKeyboard() {
		while(!reader.hasNextInt()) {
			System.out.println("Inseriu um valor inválido, pff insira um número.");
			reader.nextLine();
		}
		return reader.nextInt();
	}
}
