package GUI;

import CustomExceptions.AlreadyRegistered;
import Users.Employee;
import CustomExceptions.IncorrectFormatOfPassword;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * Trieda Registration ktora ma na starosti vykreslenie okna registracie kotra je zavolane ked dojde k sltaceniu tlacika
 * register new user v okne AdminWindow
 */

public class Registration extends Stage implements eh{

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */
    public Registration(){
        setTitle("Registration of new employee");

        FlowPane pane = new FlowPane(Orientation.VERTICAL, 5, 5);

        Button register = new Button("Register");
        TextField username = new TextField();
        TextField password = new TextField();

        Text username_text = new Text("Username: ");
        Text password_text = new Text(" Password: ");

        HBox hbox_username = new HBox(username_text);
        HBox hbox_password = new HBox(password_text);

        hbox_username.getChildren().add(username);
        hbox_password.getChildren().add(password);

        pane.getChildren().add(hbox_username);
        pane.getChildren().add(hbox_password);
        pane.getChildren().add(register);

        register.setOnAction(e -> {

            try{
                Employee curent = eh.Register(username.getText(), password.getText());

                Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Succesful registration");
                a.setHeaderText("You successfuly registered a new user called: " + curent.GetUsername());
                a.setContentText("");
                a.showAndWait();

            }
            catch (AlreadyRegistered AR){

                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Username already taken");
                a.setContentText("This username is already in use");
                a.showAndWait();
                // Maybe add the Modality parameter
            }
            catch (IncorrectFormatOfPassword IFP){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Incorret format of password");
                a.setContentText("The password has to contain at least one number, lower case letter and"
                                + "cant contain any whitspaces");
                a.showAndWait();
            }

        });

        pane.setAlignment(Pos.CENTER);

        setScene(new Scene(pane, 250, 100));
        setResizable(false);
        show();


    }


}
