package GUI;

import CustomExceptions.NoWorkHours;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Users.Employee;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Objects;
import java.util.Optional;
import CustomExceptions.LowestSallary;

/**
 * Trieda SelectedUser ktora ma na starosti vykreslenie okna SlectedUser ktore je otvorene ked klikneme v listview
 * na nejakeho zamestnanca
 */

// This class will represent selected user in AdminWindow
public class SelectedUser extends Stage implements eh{

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */

    public SelectedUser(Employee selected_user, int index){

        // Title is set to name of selected user
        setTitle("Selected User: " + selected_user.GetUsername());

        // Image reference
        final Image refresh_img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("refresh.png")));

        // Storing the observable list to var
        ObservableList<String> sallary_info = eh.GetUserInfo(selected_user.GetUsername());

        // ListView for the user info
        ListView<String> user_info = new ListView<>(sallary_info);

        // Panes
        BorderPane root = new BorderPane();
        VBox user_actions = new VBox();
        HBox list = new HBox(user_info);

        // Buttons
        Button delete = new Button("Delete user");
        Button raise = new Button(" Give a raise");
        Button paycut = new Button("Give a paycut");
        Button pay_sallary = new Button("Pay sallary");
        Button refresh = new Button("", new ImageView(refresh_img));

        // user_actions Vbox
        user_actions.getChildren().add(refresh);
        user_actions.getChildren().add(raise);
        user_actions.getChildren().add(paycut);
        user_actions.getChildren().add(pay_sallary);
        user_actions.getChildren().add(delete);

        user_actions.setSpacing(10);

        // list HBox
        list.getChildren().add(user_actions);

        HBox.setMargin(user_info, new Insets(10));
        HBox.setMargin(user_actions, new Insets(10,0,0,0));

        // Event handler for the delete button press
        delete.setOnAction(e ->{

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Deletion of user");
            a.setHeaderText("Employee with username: " + selected_user.GetUsername() + ", will be deleted");
            a.setContentText("Do you wanna procced ?");

            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK){

                eh.DeleteUser(index);
                eh.Serialization();

                close();

            } else {

                a.close();
            }

        });

        // Event handler for the raise button
        raise.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succesfully raised hourly sallary!");
            a.setHeaderText(selected_user.GetRaise());
            a.setContentText("");
            a.showAndWait();
        });

        // Event handler for the paycut button
        paycut.setOnAction(e -> {
            try {

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Succesfully decreased hourly sallary!");
                a.setHeaderText(selected_user.GetPaycut());
                a.setContentText("");
                a.showAndWait();

            }catch (LowestSallary err){

                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Unsuccesful paycut!");
                a.setContentText(err.getMessage());
                a.showAndWait();

            }

        });

        // Event handler for the Pay Sallary button
        pay_sallary.setOnAction(e -> {
            try {

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Succesfully paid sallary!");
                a.setHeaderText(selected_user.PaySallary());
                a.setContentText("");
                a.showAndWait();

            }catch (NoWorkHours err){

                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Unsuccesful payment!");
                a.setContentText(err.getMessage());
                a.showAndWait();

            }
        });

        // Event handler for the refresh button
        refresh.setOnAction(e ->{

            user_info.getItems().removeAll(sallary_info);
            user_info.getItems().addAll(eh.GetUserInfo(selected_user.GetUsername()));

        });

        // Added the panes to the root pane
        root.setLeft(list);

        setScene(new Scene(root, 500, 500));
        setResizable(false);
        show();



    }

}
