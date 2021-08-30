package GUI;

import CustomExceptions.IncorectCapacity;
import CustomExceptions.ProjectAlreadyExists;
import Projects.Project;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Trieda ktora ma na starosti vykreslenie okna CreateProject ktore sa otvori po staleni tlacitka createproject v
 * AdminWindow
 */
public class CreateProject extends Stage implements ph{

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */
    public CreateProject(){

        setTitle("New project");

        FlowPane pane = new FlowPane(Orientation.VERTICAL, 5, 5);

        Button create = new Button("Create");
        TextField name = new TextField();
        TextField capacity = new TextField();

        Text name_text = new Text("    Name: ");
        Text capacity_text = new Text("Capacity: ");

        HBox hbox_name = new HBox(name_text);
        HBox hbox_capacity = new HBox(capacity_text);

        hbox_name.getChildren().add(name);
        hbox_capacity.getChildren().add(capacity);

        // adding diferents panes to the root pane
        pane.getChildren().add(hbox_name);
        pane.getChildren().add(hbox_capacity);
        pane.getChildren().add(create);

        create.setOnAction(e -> {

            try{
                Project curent = ph.CreateProject(name.getText(), Integer.parseInt(capacity.getText()));

                close();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Succesful creation of project");
                a.setHeaderText("You successfuly created a new project called: " + curent.GetName());
                a.setContentText("");
                a.showAndWait();

            }
            catch (ProjectAlreadyExists err){

                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Name of project is already taken");
                a.setContentText("This name is already in use");
                a.showAndWait();
            }
            catch (IncorectCapacity err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Incorret amount of capacity");
                a.setContentText("Capacity has to be larger than zero ");
                a.showAndWait();
            }

        });

        pane.setAlignment(Pos.CENTER);

        setScene(new Scene(pane, 250, 100));
        setResizable(false);
        show();

    }

}
