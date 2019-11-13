import  java.util.*;

public class Type {
	private String type;
	private ArrayList<String> sEffective;
	private ArrayList<String> nEffective;
	private ArrayList<String> noEffect;
	
	public Type(String t, ArrayList<String> s, ArrayList<String> n, ArrayList<String> no) {
		type =  t;
		sEffective = s;
		nEffective = n;
		noEffect = no;
	}
	
	public String getType() {
		return type;
	}
	
	public void printSuperEffective() {
		if(sEffective.size() != 0) {
			System.out.print(type + " is super effective against ");
			for(int i = 0; i < sEffective.size() - 1; i++) {
				if(i != sEffective.size() - 2) {
					System.out.print(sEffective.get(i) + ", ");
				}
				else {
					System.out.print(sEffective.get(i) + " ");
				}
			}
			if(sEffective.size() != 1) {
				System.out.println("and " + sEffective.get(sEffective.size() - 1));
			}
			else {
				System.out.println(sEffective.get(sEffective.size() - 1));
			}
		}
		else {
			System.out.println(type + " is not super effective against anything");
		}
	}
	
	public void printNotEffective() {
		System.out.print(type + " is not very effective against ");
		for(int i = 0; i < nEffective.size() - 1; i++) {
			if(i != nEffective.size() - 2) {
				System.out.print(nEffective.get(i) + ", ");
			}
			else {
				System.out.print(nEffective.get(i) + " ");
			}
		}
		if(nEffective.size() != 1) {
			System.out.println("and " + nEffective.get(nEffective.size() - 1));
		}
		else {
			System.out.println(nEffective.get(nEffective.size() - 1));
		}
	}
	
	public void printNoEffect() {
		if(noEffect.size() != 0) {
			System.out.print(type + " does not affect ");
			for(int i = 0; i < noEffect.size() - 1; i++) {
				System.out.print(noEffect.get(i) + ", ");
			}
			if(noEffect.size() != 1) {
				System.out.println("and " + noEffect.get(noEffect.size() - 1));
			}
			else {
				System.out.println(noEffect.get(noEffect.size() - 1));
			}
		}
		else {
			System.out.println(type + " affects everything");
		}
	}
	
	public boolean isSuperEffective(String t) {
		for(int i = 0; i < sEffective.size(); i++) {
			if(t.equalsIgnoreCase(sEffective.get(i))) {
				return true;
			}
		}
		if(sEffective.size() == 0 && t.equals("")) {
			return true;
		}
		return false;
	}
	
	public boolean isNotEffective(String t) {
		for(int i = 0; i < nEffective.size(); i++) {
			if(t.equalsIgnoreCase(nEffective.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNoEffect(String t) {
		for(int i = 0; i < noEffect.size(); i++) {
			if(t.equalsIgnoreCase(noEffect.get(i))) {
				return true;
			}
		}
		if(noEffect.size() == 0 && t.equals("")) {
			return true;
		}
		return false;
	}
	
	public int sEffectSize() {
		return sEffective.size();
	}
	
	public int nEffectSize() {
		return nEffective.size();
	}
	
	public int noEffectSize() {
		return noEffect.size();
	}
}
