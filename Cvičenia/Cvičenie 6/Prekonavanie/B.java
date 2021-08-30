package Prekonavanie;

public class B extends A {

	void m() throws Exception { // prekladac toto nepovoli
		/* ... */				// klientsky kod (ktory je v triede C) by zavedenim dalsej vynimky, ktora nie je odvodena od uz deklarovanej, bol zneplatneny
	}
}
