package gui;

import stret.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;

public class StretGUI extends Application {
	private Button vytvorBojovnikov = new Button("Vytvor bojovnikov");
	private Button spustStret = new Button("Spust stret");
	private Button resetTextu = new Button("Resetovanie textoveho pola");
	private TextField rytieri = new TextField();
	private TextField statocniRytieri = new TextField();
	private TextField zliObri = new TextField();
	private Label rytieriOzn = new Label("Rytieri");
	private Label statocniRytieriOzn = new Label("Statocni rytieri");
	private Label zliObriOzn = new Label("Zli obri");
	private TextArea vypis = new TextArea();
	private ScrollPane skrol = new ScrollPane();
	
	private EnergiaBojovnikov energiaBojovnikov;
	private SucetSerialov sucetserialov;
	
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Stret");
		
		FlowPane pane = new FlowPane();
		
		Stret stret = new Stret();
		
		pane.getChildren().add(vytvorBojovnikov);
		pane.getChildren().add(spustStret);
		pane.getChildren().add(resetTextu);
		pane.getChildren().add(rytieri);
		pane.getChildren().add(rytieriOzn);
		pane.getChildren().add(statocniRytieri);
		pane.getChildren().add(statocniRytieriOzn);
		pane.getChildren().add(zliObri);
		pane.getChildren().add(zliObriOzn);
		pane.getChildren().add(vypis);
		
		skrol.setContent(pane);		
/*
		class SpracovanieStretu implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
						Integer.parseInt(statocniRytieri.getText()),
						Integer.parseInt(zliObri.getText()));

				vypis.appendText(stret.stret());
			}
		}
			    	
		spustStret.setOnAction(new SpracovanieStretu());
*/		
		// prijimac pomocou anonymnej metody
/*		spustStret.setOnAction(new EventHandler<ActionEvent>() { // anonymna trieda odvodena od rozhrania EventHandler
	    	@Override // anotacia, ktora indikuje prekonanie; anotacie su informaciou pre cloveka, ale vyuzitelne su aj programovo
	    	public void handle(ActionEvent event) { // v anonymnej triede prekonavame metodu handle()
				stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
						Integer.parseInt(statocniRytieri.getText()),
						Integer.parseInt(zliObri.getText()));

				vypis.appendText(stret.stret());
			}
	    });
*/

		// prijimac pomocou referencie na metodu (method reference)
/*	    EventHandler<ActionEvent> a = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
						Integer.parseInt(statocniRytieri.getText()),
						Integer.parseInt(zliObri.getText()));

				vypis.appendText(stret.stret());
			}
		};

   		spustStret.setOnAction(a::handle);
*/
/*
		// prijimac pomocou lambda vyrazu (lambda expression)
		//spustStret.setOnAction((ActionEvent e) -> { // lambda vyraz s uvedenim typu
		spustStret.setOnAction(e -> { // lambda vyraz s odvodenim typu z kontextu
			stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
					Integer.parseInt(statocniRytieri.getText()),
					Integer.parseInt(zliObri.getText()));

			vypis.appendText(stret.stret());
			}
		);
*/
 		vytvorBojovnikov.setOnAction(e -> { // lambda vyraz s odvodenim typu z kontextu
			stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
					Integer.parseInt(statocniRytieri.getText()),
					Integer.parseInt(zliObri.getText()));

			vypis.appendText("Bojovnici vytvoreni.\n");
			}
		);

 		spustStret.setOnAction(e -> vypis.appendText(stret.stret()));

 		resetTextu.setOnAction(e -> vypis.clear());

		energiaBojovnikov = new EnergiaBojovnikov(stret);
		stret.pridajSledovatela(energiaBojovnikov);
		pane.getChildren().add(energiaBojovnikov);

		sucetserialov = new SucetSerialov(stret);
		stret.pridajSledovatela(sucetserialov);
		pane.getChildren().add(sucetserialov);


//		hlavneOkno.setScene(new Scene(pane, 500, 300));
		hlavneOkno.setScene(new Scene(skrol, 500, 300)); // so scrollbarmi
		hlavneOkno.show();
	}

	// metoda main() pomoze v pripade zlyhania standardneho sposobu aktivacie JavaFX aplikacie
	// ale inak nie je potrebna
	public static void main(String[] args) {
		launch(args);
	}
}
