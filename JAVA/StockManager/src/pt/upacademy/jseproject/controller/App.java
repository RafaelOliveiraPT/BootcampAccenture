package pt.upacademy.jseproject.controller;

import pt.upacademy.jseproject.test.SampleData;
import pt.upacademy.jseproject.textinterface.TextInterface;

public class App {

	public static void main(String args[]) {
		new SampleData().loadSampleData();
		TextInterface ti = new TextInterface();
		ti.showMenu();
	}
}
