package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class StretGUI extends Application {
	private Button dalsieOknoStretuTlacidlo = new Button("Dalsie okno stretu");
		
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Obri a rytieri");
		
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(dalsieOknoStretuTlacidlo);
		
 		dalsieOknoStretuTlacidlo.setOnAction(e -> new StretOkno());

		hlavneOkno.setScene(new Scene(pane, 400, 250));
		hlavneOkno.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}