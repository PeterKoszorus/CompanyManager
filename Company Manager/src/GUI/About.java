package GUI;


import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * Trieda ktora ma na starosti vykreslenie stagu about ked sa v GUI stalci tlacitko about
 */

public class About extends Stage{

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */
    public About(){

        FlowPane pane = new FlowPane();

        Text info = new Text("Projekt na OPP\n" +
                "LS 2020/2021\n" +
                "AIS ID: 110826\n" +
                "Email: xkoszorus@stuba.sk\n" +
                "Github: https://github.com/PeterKoszorus");

        pane.getChildren().add(info);
        pane.setAlignment(Pos.CENTER);

        pane.setStyle(" -fx-border-color: #000099; -fx-border-width: 5; -fx-background-color: #0099ff; " +
                "-fx-font-weight: bold");

        setScene(new Scene(pane, 275,125));
        setResizable(false);
        show();
    }

}
