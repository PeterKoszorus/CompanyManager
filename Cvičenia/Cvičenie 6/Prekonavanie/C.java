package Prekonavanie;

// Klientsky kod
public class C {
	public static void main(String[] args) {
		A a = D.getMyObject(); // klientsky kod pracuje cez rozhranie triedy A, ale aj objekt triedy D sa tam moze ocitnut
		
		try {
			a.m();
		} catch (MyException e) { // podla rozhrania triedy A, pri metode m() staci osetrit iba vynimku MyException
			/*...*/
		}						// klientsky kod nema ako zistit, ze su aj odvodene triedy, akou je D
	}
}
