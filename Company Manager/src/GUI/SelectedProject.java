package GUI;

import CellDesignes.UsernameCellDesign;
import CustomExceptions.InsufficientCapacity;
import Projects.Project;
import Users.Employee;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Trieda SelectedProject ktora ma na starosti vykreslenie okna SlectedProject ktore je otvorene ked klikneme v listview
 * na nejaky projekt
 */

public class SelectedProject extends Stage implements eh,ph{

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */

    public SelectedProject(Project project){

        setTitle("Selected project: " + project.GetName());

        // Creation of panes
        BorderPane root = new BorderPane();
        HBox left_side = new HBox();
        VBox list1 = new VBox();
        VBox list2 = new VBox();

        // Creation of objects of gui
        Text assigned_emp = new Text("Assigned Employees");
        Button assign_emp = new Button("Assign employee");
        Button delete_project = new Button("Delete project");
        Button unassign_emp = new Button("Unassign employee");

        // listview with assigned employees and setting design for the cells
        ObservableList<Employee> project_info = ph.ProjectInfo(project);
        ListView<Employee> list_of_assigned_emp = new ListView<>(project_info);
        list_of_assigned_emp.setCellFactory(param -> new UsernameCellDesign());

        // Combobox with unassigned employees
        ObservableList<String> un_emp = eh.GetUnassignedEmp();
        ComboBox<String> unassigned_emp = new ComboBox<>(un_emp);

        // Vbox list1 settings
        list1.getChildren().add(assigned_emp);
        list1.getChildren().add(list_of_assigned_emp);
        list1.getChildren().add(unassigned_emp);
        list1.getChildren().add(assign_emp);

        VBox.setMargin(assigned_emp, new Insets(0, 0, 0, 70));
        VBox.setMargin(unassigned_emp, new Insets(0, 0, 0,80));
        VBox.setMargin(assign_emp, new Insets(0, 0, 0, 70));

        list1.setSpacing(10);

        // Event handler for the assign_emp button
        assign_emp.setOnAction(event -> {

            try {

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Succesfully assigned employee");
                a.setHeaderText(ph.AssignEmployee(eh.GetUserByUsername(unassigned_emp.getValue()), project));
                a.setContentText("");
                a.showAndWait();

                // Refresh part
                list_of_assigned_emp.getItems().removeAll(project_info);
                unassigned_emp.getItems().removeAll(un_emp);
                list_of_assigned_emp.getItems().addAll(ph.ProjectInfo(project));
                unassigned_emp.getItems().addAll(eh.GetUnassignedEmp());

            }catch (InsufficientCapacity err){

                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Unsuccesful assignment!");
                a.setContentText(err.getMessage());
                a.showAndWait();

            }catch (NullPointerException err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Unsuccesful assignment!");
                a.setContentText("You haven't selected any employee to assign");
                a.showAndWait();
            }

        });

        // Vbox list2 settings
        list2.getChildren().add(unassign_emp);
        list2.getChildren().add(delete_project);

        VBox.setMargin(unassign_emp, new Insets(25, 0, 0, 10));
        VBox.setMargin(delete_project, new Insets(0, 0, 0, 10));

        list2.setSpacing(10);

        // Event handler for the unassign_emp button
        unassign_emp.setOnAction(e -> {
            try {

                eh.UnassignEmployee(list_of_assigned_emp.getSelectionModel().getSelectedItem().GetUsername());

                // Refresh part
                list_of_assigned_emp.getItems().removeAll(project_info);
                unassigned_emp.getItems().removeAll(un_emp);
                list_of_assigned_emp.getItems().addAll(ph.ProjectInfo(project));
                unassigned_emp.getItems().addAll(eh.GetUnassignedEmp());

            }catch (NullPointerException err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Unsuccesful unassignment!");
                a.setContentText("You haven't selected any employee to unassign");
                a.showAndWait();
            }

        });

        // Event handler for the delete project button
        delete_project.setOnAction(event -> {

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Deletion of project");
            a.setHeaderText("Project: " + project.GetName() + ", will be deleted");
            a.setContentText("Do you wanna procced ?");

            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK){

                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Project successfuly removed");
                b.setHeaderText(ph.DeleteProject(project));
                b.setContentText("");
                b.showAndWait();

                close();

            } else {

                a.close();
            }

        });

        // Adding panes to the Hbox left_side
        left_side.getChildren().add(list1);
        left_side.getChildren().add(list2);

        // Adding panes to the root pane
        root.setLeft(left_side);

        setScene(new Scene(root, 500, 500));
        setResizable(false);
        show();
    }

}
