import java.util.Scanner;

public class Test {
	private final Scanner sc;
	
	public Test() {
		sc = new Scanner(System.in);
	}
	
	public void closeScanner() {
		this.sc.close();
	}
	public static void main(String [] args ) {
		
		Test test = new Test();
		System.out.println(test);
		test.closeScanner();
	}
}
