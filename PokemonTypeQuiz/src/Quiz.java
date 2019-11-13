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
	
	public void runQuiz() {
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
}
