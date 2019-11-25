import java.util.Scanner;
public class QuizDriver {

	public static void main(String[] args) {
		Quiz q = new Quiz("type.txt");
		Scanner in = new Scanner(System.in);
		boolean run = true;
		
		System.out.println("Welcome to the Pokemon Type Quiz");
		while(run) {
			System.out.println("Quiz Options:");
			System.out.println("1) Easy Quiz");
			System.out.println("2) Hard Quiz");
			System.out.println("3) Quit");
			System.out.print("Enter option here: ");
			String answer = in.next();
			System.out.println();
			
			switch(answer) {
			case("2"):
				q.runQuiz1();
			break;
			case("1"):
				System.out.print("How long do you want the quiz to be: ");
				int length = in.nextInt();
				while(length <= 0) {
					System.out.println();
					System.out.print("Invalid quiz length. Please enter a positive integer:");
					length = in.nextInt();
				}
				q.runQuiz2(length);
			break;
			case("3"):
				run = false;
			System.out.println("Thanks for running the quiz.");
			break;
			default:
				System.out.println("Please enter a valid choice.");
			}
		}
	}

}
