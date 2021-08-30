package GUI;

import CustomExceptions.IncorectPassword;
import Users.*;
import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.util.Objects;

public class GUI extends Application implements eh, ph{


    private final Image info = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Info.png")));

    //private ScrollPane scroll = new ScrollPane();
    private final Button login = new Button("login");
    private final Button about = new Button("About", new ImageView(info));
    private final TextField username_field = new TextField();
    private final PasswordField password_field = new PasswordField();
    private final Label username_lb = new Label("Username: ");
    private final Label password_lb = new Label("Password: ");

    /**
     * Specialna metoda ktora zabezpecuje spustenie aplikacie
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overridnuta metoda ktora je zavolana ked sa spusti aplikacia vnutri v metode sa vykresluje LoginScreen
     */
    @Override
    public void start(Stage LoginWindow) {
        LoginWindow.setTitle("Company Manager");

        FlowPane pane = new FlowPane(Orientation.VERTICAL, 5, 5);
        BorderPane root = new BorderPane();
        HBox hbox = new HBox(about);

        // FlowPane properties

        ObservableList list = pane.getChildren();

        list.addAll(username_lb, username_field, password_lb, password_field, login);

        pane.setAlignment(Pos.CENTER);

        pane.setStyle("-fx-background-color: #fa645e;-fx-background-insets: 125 235 125 235;");

        // Hbox properties

        HBox.setMargin(about, new Insets(10, 10, 10, 10));

        about.setOnAction(e -> new About());

        // Root properties
        root.setCenter(pane);
        root.setTop(hbox);

        //Doesnt work root.setStyle("-fx-background-image: url(cardboard.png)");

        //Functional part

        login.setOnAction(e -> {

            try {

                Employee curent = eh.Login(username_field.getText(), password_field.getText());

                // Use of RTTI
                if(curent.getClass().getName().equals("Users.Employer")){
                    new AdminWindow(curent);
                }
                if(curent.getClass().getName().equals("Users.Employee")){
                    new UserWindow(curent);
                }

                // LoginWindow.close();
                // Cant reopen the login window
            }
            catch (IncorectPassword err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Login unsuccessful !");
                a.setContentText("You entered a wrong password");
                a.initModality(Modality.WINDOW_MODAL);
                a.initOwner(LoginWindow);
                a.showAndWait();
            }
            catch (NullPointerException err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Login unsuccessful !");
                a.setContentText("There is no user called " + username_field.getText());
                a.initModality(Modality.WINDOW_MODAL);
                a.initOwner(LoginWindow);
                a.showAndWait();
            }

        });

        LoginWindow.setScene(new Scene(root,700, 500));
        LoginWindow.setResizable(false);
        LoginWindow.show();

    }
}
