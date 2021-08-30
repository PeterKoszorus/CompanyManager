package GUI;

import CellDesignes.ProjectNameCell;
import CellDesignes.UsernameCellDesign;
import Projects.Project;
import Users.Employee;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * Trieda ktora ma na starosti vykreslenie AdminWindow tento stage sa vykresli ak sa spravne prihlasil zamestnavatel
 */

public class AdminWindow extends Stage implements eh, ph {

    /**
     * Ked je zavolany konstruktor zobrazi sa okno
     */
    public AdminWindow(Employee act_user){

        TabPane root = new TabPane();
        BorderPane grid = new BorderPane();
        VBox action_group = new VBox();

        ListView<Employee> names = new ListView<>(eh.GetEmployees());

        // Setting the celldesign by our customly made class
        names.setCellFactory(param -> new UsernameCellDesign());

        HBox list = new HBox(names);

        Button register = new Button("Register new employee");
        Button logout = new Button("Logout");

        // Adding buttons to action_group
        action_group.getChildren().add(register);
        action_group.getChildren().add(logout);

        action_group.setSpacing(10);

        // list HBox
        list.getChildren().add(action_group);

        HBox.setMargin(names, new Insets(10));
        HBox.setMargin(action_group, new Insets(10, 0, 0, 0));

        // Employees tab design
        Tab employees_tab = new Tab("Employees");



        // Adding object to BorderPane
        grid.setLeft(list);

        // Event handler when selecting new user from the listWiev
        names.setOnMouseClicked(e -> {

            try {
                // Creating stage class to handle all user stuff
                new SelectedUser(names.getSelectionModel().getSelectedItem(), names.getSelectionModel().getSelectedIndex());
            }
            catch (NullPointerException err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("There is no employee registered");
                a.setContentText("There is no employee registered");
                a.showAndWait();
            }

        });

        // Register button eventhandler
        register.setOnAction(e -> new Registration());

        // When logging out the data will get serialized
        logout.setOnAction(e ->{

            eh.Serialization();
            ph.Serialization();

            close();
        });

        // Event handler for closing the window so it will get serialized
        setOnHiding(event -> {

            eh.Serialization();
            ph.Serialization();

            close();
        });

        // Adding root border pane of employees_tab to employees tab
        employees_tab.setContent(grid);


        // Project Tab setup and design

        // Creating Project tab
        Tab project_tab = new Tab("Projects");

        // ListView for project side
        ListView<Project> projects = new ListView<>(ph.GetProjects());

        // Setting the cell design for projects listview
        projects.setCellFactory(param -> new ProjectNameCell());

        // Project tab panes
        BorderPane root_prj = new BorderPane();
        VBox project_actions = new VBox();
        HBox list_prj = new HBox(projects);

        // Buttons for project tab
        Button create_project = new Button("Create a new project");

        // project_actions Vbox
        project_actions.getChildren().add(create_project);

        project_actions.setSpacing(10);

        // list_prj Hbox
        list_prj.getChildren().add(project_actions);

        HBox.setMargin(projects, new Insets(10));
        HBox.setMargin(project_actions, new Insets(10, 0, 0, 0));

        // Event handler for create_project button
        create_project.setOnAction(event -> new CreateProject());

        // Event handler for when clicked on the listview with projects
        projects.setOnMouseClicked(event -> {
            try {
                    new SelectedProject(projects.getSelectionModel().getSelectedItem());

            }catch (NullPointerException err){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("There is no project created");
                a.setContentText("There is no project created");
                a.showAndWait();
            }
        });

        // Adding panes to the root pane
        root_prj.setLeft(list_prj);

        // Adding root border pane of project_tab to project tab
        project_tab.setContent(root_prj);


        // Creating tabs for the root
        root.getTabs().add(employees_tab);
        root.getTabs().add(project_tab);

        setTitle("Your are logged in as: " + act_user.GetUsername());
        setScene(new Scene(root, 500, 500));
        setResizable(false);

        show();
    }
}

