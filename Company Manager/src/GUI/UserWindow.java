package GUI;

import Users.Employee;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * Trieda ktora ma na starosti vykreslenie okna UserWindow kotre je vykreslovane ked sa prihlasi nejaky zamestnanec
 */

public class UserWindow extends Stage implements eh {

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */

    public UserWindow(Employee act_user){

        setTitle("You are logged in as: " + act_user.GetUsername());

        // Starting to measure time logged in
        long start = System.currentTimeMillis();

        // Panes
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();

        // Observable list with all the user info
        ObservableList<String> user_info = eh.GetUserInfo(act_user.GetUsername());

        // Objects to show
        Text hourly_pay = new Text(user_info.get(0));
        Text worked_time = new Text(user_info.get(1));
        Text total_sallary = new Text(user_info.get(2));
        Text assigned_project = new Text(user_info.get(3));

        // Vbox settings
        vbox.getChildren().add(hourly_pay);
        vbox.getChildren().add(worked_time);
        vbox.getChildren().add(total_sallary);
        vbox.getChildren().add(assigned_project);

        vbox.setStyle("-fx-font-weight: bold; -fx-font-size: 25 ");

        vbox.setSpacing(5);

        // Adding the panes to the root pane
        root.setCenter(vbox);
        BorderPane.setMargin(vbox, new Insets(150, 0, 0, 250));

        // Event handler for when the window closes
        setOnHiding(event -> {

            long finish = System.currentTimeMillis();
            act_user.SetTimeLoggedIn(((finish - start) / 1000));

            System.out.println("Time logged in: " + act_user.GetTime());

            eh.Serialization();

            close();
        });

        setScene(new Scene(root, 900, 500));
        setResizable(false);
        show();
    }
}