import java.util.*;
import java.io.*;

public class Quiz {
	Scanner in;
	ArrayList<Type> types = new ArrayList<Type> ();
	
	public Quiz(String fileName) {
		try {
			in = new Scanner(new File(fileName));
			while(in.hasNext()) {
				String type;
				String buffer;
				ArrayList<String> sEffective = new ArrayList<String> ();
				ArrayList<String> nEffective = new ArrayList<String> ();
				ArrayList<String> noEffect = new ArrayList<String> ();
				
				//grab type name
				type = in.nextLine();
				in.nextLine();
				
				//grabs all super effective types
				buffer = in.nextLine();
				while(!buffer.equals("N")) {
					sEffective.add(buffer);
					buffer = in.nextLine();
				}
				
				//grabs all not very effective types
				buffer = in.nextLine();
				while(!buffer.equals("NO")) {
					nEffective.add(buffer);
					buffer = in.nextLine();
				}
				
				//grabs all not very effective types
				buffer = in.nextLine();
				while(!buffer.equals("")) {
					noEffect.add(buffer);
					buffer = in.nextLine();
				}
				
				//add type into array list
				types.add(new Type(type, sEffective, nEffective, noEffect));
			}
			
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void printTypes() {
		for(int i = 0; i < types.size(); i++) {
			System.out.println(types.get(i).getType());
			types.get(i).printSuperEffective();
			types.get(i).printNotEffective();
			types.get(i).printNoEffect();
			System.out.println();
		}
	}
	
	public void runQuiz1() {
		Random rand = new Random();
		int runQuiz = types.size();
		int type = rand.nextInt(runQuiz);
		int numCorrect = 0;
		int count = 0;
		
		System.out.println("Rules:");
		System.out.println("For each answer, write out each type with one blank space after each one");
		System.out.println("If there is no type that is effective, just leave it blank");
		System.out.println();
		
		while(runQuiz != 0) {
			int effect = count % 3;
			String answer = askQuestion(type, effect);
			if(checkAnswer(answer, type, effect)) {
				++numCorrect;
				System.out.println("Correct!");
			}
			else {
				System.out.println("Incorrect.");
				if(effect == 0) {
					types.get(type).printSuperEffective();
				}
				else if(effect == 1) {
					types.get(type).printNotEffective();
				}
				else {
					types.get(type).printNoEffect();
				}
			}
			if(++count % 3 == 0) {
				--runQuiz;
				types.add(types.get(type));
				types.remove(type);
				if(runQuiz != 0) {
					type = rand.nextInt(runQuiz);
				}
				System.out.println();
			}
		}
		System.out.println("You got " + numCorrect + " out of " + count + " correct.");
	}
	
	public String askQuestion(int type, int effect) {
		String answer = "";
		in = new Scanner(System.in);
		
		switch (effect) {
		case(0):
			System.out.println("What is " + types.get(type).getType() + " super effective against?");
			System.out.print("Enter answer here: ");
			answer = in.nextLine();
			break;
		case(1):
			System.out.println("What is " + types.get(type).getType() + " not very effective against?");
			System.out.print("Enter answer here: ");
			answer = in.nextLine();
			break;
		
		case(2):
			System.out.println("What does " + types.get(type).getType() + " not affect?");
			System.out.print("Enter answer here: ");
			answer = in.nextLine();
			break;
		}
		return answer;
	}
	
	public boolean checkAnswer(String answer, int type, int effect) {
		String clone = new String(answer);
		int count = 0;
		switch (effect) {
		case(0):
			while(clone.indexOf(' ') != -1) {
				String s = clone.substring(0, clone.indexOf(' '));
				if(!types.get(type).isSuperEffective(s)) {
					return false;
				}
				clone = clone.substring(clone.indexOf(' ') + 1);
				count++;
			}
			if(!types.get(type).isSuperEffective(clone) || (++count != types.get(type).sEffectSize() && !clone.equals(""))) {
				return false;
			}
			break;
		case(1):
			while(clone.indexOf(' ') != -1) {
				String s = clone.substring(0, clone.indexOf(' '));
				if(!types.get(type).isNotEffective(s)) {
					return false;
				}
				clone = clone.substring(clone.indexOf(' ') + 1);
				count++;
			}
			if(!types.get(type).isNotEffective(clone) || (++count != types.get(type).nEffectSize() && !clone.equals(""))) {
				return false;
			}
			break;
		
		case(2):
			while(clone.indexOf(' ') != -1) {
				String s = clone.substring(0, clone.indexOf(' '));
				if(!types.get(type).isNoEffect(s)) {
					return false;
				}
				clone = clone.substring(clone.indexOf(' ') + 1);
				count++;
			}
			if(!types.get(type).isNoEffect(clone) || (++count != types.get(type).noEffectSize() && !clone.equals(""))) {
				return false;
			}
			break;
		}
		return true;
	}
	
	public void runQuiz2(int quizSize) {
		String answer = "";
		Random rand = new Random();
		int runQuiz = quizSize;
		int numCorrect = 0;
		in = new Scanner(System.in);
		
		while(runQuiz != 0) {
			Type type1 = types.get(rand.nextInt(types.size()));
			Type type2 = types.get(rand.nextInt(types.size()));
			System.out.println("Is " + type1.getType() + " super effective (2), not very effective (0.5)" + 
								", no effect (0), or normal (1) against " + type2.getType() + "?");
			
			System.out.print("Enter answer here: ");
			answer = in.next();
			
			String correct = "";
			String err = "";
			if(type1.isSuperEffective(type2.getType())) {
				correct = "2";
				err = "Incorrect. " + type1.getType() + " is actually super effective against " + type2.getType();
			}
			else if(type1.isNotEffective(type2.getType())) {
				correct = "0.5";
				err = "Incorrect. " + type1.getType() + " is actually not very effective against " + type2.getType();
			}
			else if(type1.isNoEffect(type2.getType())) {
				correct = "0";
				err = "Incorrect. " + type1.getType() + " actually does not affect " + type2.getType();
			}
			else {
				correct = "1";
				err = "Incorrect. " + type1.getType() + " is actually normal against " + type2.getType();
			}
			
			if(answer.equals(correct)){
				++numCorrect;
				System.out.println("Correct");
			}
			else {
				System.out.println(err);
			}
			--runQuiz;
		}
		
		System.out.println("You got " + numCorrect + " out of " + quizSize + " correct.");
	}
}
