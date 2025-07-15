package io.upacademy.jseproject.utilities;

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
	
	//retorna -9999 quando o utilizador não introduzir texto
	public static int getIntFromKeyboard(boolean onlyPositive, boolean emptyLine) {
		int auxInt = -9999;
		String auxStr;
		while(true) {
			auxStr = reader.nextLine();
			try {
				if(emptyLine && auxStr.isEmpty()) {
					break;
				}
				auxInt = Integer.parseInt(auxStr);
				if (onlyPositive && auxInt < 0) {
					System.out.println("Introduza um valor positivo:");
				} else  {
					break;
				}
			} catch (Exception e) {
				System.out.print("Insira um valor válido:");
			}
		}
		return auxInt;
	}
	
	//retorna -9999 quando o utilizador não introduzir texto
	public static double getDoubleFromKeyboard(boolean onlyPositive, boolean emptyLine) {
		double auxDouble = -9999;
		String auxStr;
		while(true) {
			auxStr = reader.nextLine();
			try {
				if(emptyLine && auxStr.isEmpty()) {
					break;
				}
				auxDouble = Double.parseDouble(auxStr);
				if (onlyPositive && auxDouble < 0) {
					System.out.println("Introduza um valor positivo:");
				} else  {
					break;
				}
			} catch (Exception e) {
				System.out.print("Insira um valor válido:");
			}
		}
		return auxDouble;
	}
}
