package gui;

import stret.*;
import postavy.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.concurrent.*;
import java.io.*;
import java.util.*;

public class StretOkno extends Stage {
	private Button vytvorBojovnikovTlacidlo = new Button("Vytvor bojovnikov");
	private Button dalsiStretTlacidlo = new Button("Dalsi stret");
	private Button celyStretTlacidlo = new Button("Cely stret");
	private Button ulozTlacidlo = new Button("Uloz");
	private Button nacitajTlacidlo = new Button("Nacitaj");
	private Button spocitajTlacidlo = new Button("Spocitaj");
	private TextField rytieri = new TextField();
	private TextField statocniRytieri = new TextField();
	private TextField zliObri = new TextField();
	private TextField typBojovnika = new TextField();
	private Label rytieriOzn = new Label("Rytieri");
	private Label statocniRytieriOzn = new Label("Statocni rytieri");
	private Label zliObriOzn = new Label("Zli obri");
	private Label typBojovnikaOzn = new Label("Typ bojovnika");
	private TextArea vypis = new TextArea();
	private ScrollPane skrol = new ScrollPane();
	
	private EnergiaBojovnikov energiaBojovnikov;
	
	public StretOkno() {
		super();
		
		setTitle("Stret");
		
		FlowPane pane = new FlowPane();
		
		Stret stret = new Stret();
		
		pane.getChildren().add(vytvorBojovnikovTlacidlo);
		pane.getChildren().add(dalsiStretTlacidlo);
		pane.getChildren().add(celyStretTlacidlo);
		pane.getChildren().add(ulozTlacidlo);
		pane.getChildren().add(nacitajTlacidlo);
		pane.getChildren().add(spocitajTlacidlo);
		pane.getChildren().add(rytieri);
		pane.getChildren().add(rytieriOzn);
		pane.getChildren().add(statocniRytieri);
		pane.getChildren().add(statocniRytieriOzn);
		pane.getChildren().add(zliObri);
		pane.getChildren().add(zliObriOzn);
		pane.getChildren().add(vypis);
		pane.getChildren().add(typBojovnika);
		pane.getChildren().add(typBojovnikaOzn);

		skrol.setContent(pane);

 		vytvorBojovnikovTlacidlo.setOnAction(e -> {
			try {
				stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
						Integer.parseInt(statocniRytieri.getText()),
						Integer.parseInt(zliObri.getText()));
				vypis.clear();
				vypis.appendText("Bojovnici vytvoreni.\n");
			} catch (NerovnyPocetBojovnikovException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("Pocet zlych obrov nemoze byt vacsi ako celkovy pocet rytierov.");
				a.showAndWait();
			}catch (NumberFormatException ex){
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("Bol zadany string namiesto integeru");
				a.showAndWait();
			}
 		});

 		dalsiStretTlacidlo.setOnAction(e -> {
 			try {
 				vypis.appendText(stret.dalsiStret1na1());
 			} catch (NoSuchElementException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("Dalsia dvojica bojovnikov uz nie je.");
				a.showAndWait(); 					 				
 			}
 		});

 		celyStretTlacidlo.setOnAction(e -> {
// 			vypis.appendText(stret.stret()); // zle, lebo zatazujeme GUI zlozitym vypoctom
 			
 			// Dva sposoby spustenia zloziteho vypoctu mimo nite, v ktorej sa vykonava GUI
 			// 1. Priame pouzitie vlastnej nite
/*
 			Thread th = new Thread() {
				public void run() {
					String s = stret.stret();
					Platform.runLater(() -> vypis.appendText(s)); // do GUI musime zasahovat vylucne cez JavaFX aplikacnu nit
				}
		    };
*/
 			// 2. Pouzitie triedy Task<V>

		    Task<String> t = new Task<String>() {
 				protected String call() {
 					return stret.stret();
 				}
 				protected void succeeded() {
 					Platform.runLater(() -> vypis.appendText(getValue()));
 				}
 			};
 			
 			// 2a. Zachytenie vynimky cez sledovanie zlyhania ulohy
/*
 			t.setOnFailed(ee -> {
 				Exception ex = (Exception) t.getException();
 				if (t.getException() instanceof NullPointerException) {
 					Alert a = new Alert(AlertType.ERROR);
 					a.setTitle("Chyba");
 					a.setContentText("Bojovnici neboli inicializovani.");
 					a.showAndWait();
 					// ex.printStackTrace(); // ak chceme aj vypis nazvu vynimky a zasobnika volani
 				}
 			});
*/

 			// 2b. Zachytenie vynimky cez sledovanie atributu, do ktoreho sa vyhodena vynimka uklada
 			t.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
 				if(newValue != null) {
 					Exception ex = (Exception) newValue;
 	 				if (ex instanceof NullPointerException) {
 	 					Alert a = new Alert(AlertType.ERROR);
 	 					a.setTitle("Chyba");
 	 					a.setContentText("Bojovnici neboli inicializovani.");
 	 					a.showAndWait();
 	 					// ex.printStackTrace(); // ak chceme aj vypis nazvu vynimky a zasobnika volani
 	 				}
 				}
 			});

 			Thread th = new Thread(t);

 			// v oboch pripadoch nastavime nit, aby bola demon, t. j. neblokovala ukoncenie programu (virtualneho stroja) a spustime ju
 			th.setDaemon(true);
			th.start();
 		});
 		 		
  		ulozTlacidlo.setOnAction(e -> {
			try {
				stret.uloz();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
 		
 		nacitajTlacidlo.setOnAction(e -> {
			try {
				stret.nacitaj();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

 		spocitajTlacidlo.setOnAction(e -> {
 			switch (typBojovnika.getText()) {
 				case ("r") : vypis.appendText("Rytieri:" + stret.spocitajBojovnikov(Rytier.class) + "\n");
 				break;
 				case ("sr") : vypis.appendText("Statocni rytieri:" + stret.spocitajBojovnikov(StatocnyRytier.class) + "\n");
 				break;
 				case ("o") : vypis.appendText("Obri:" + stret.spocitajBojovnikov(Obor.class) + "\n");
 				break;
 				case ("zo") : vypis.appendText("Zli obri:" + stret.spocitajBojovnikov(ZlyObor.class) + "\n");
 				break; 			
 			}
		});

		energiaBojovnikov = new EnergiaBojovnikov(stret);
		stret.pridajSledovatela(energiaBojovnikov);
		pane.getChildren().add(energiaBojovnikov);

		setScene(new Scene(skrol, 500, 300));
		show();
	}
}
