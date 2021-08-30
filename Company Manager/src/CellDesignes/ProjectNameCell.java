package CellDesignes;

import Projects.Project;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * Trieda kotra ma nastarosti dizajn jednotlivych celov v listview typu Project
 */
public class ProjectNameCell extends ListCell<Project> {

    private final Label name;

    // Constructor if we want to change the design of the cell we have to make it here
    public ProjectNameCell() {

        name = new Label();

    }

    @Override
    protected void updateItem(Project project, boolean empty){
        super.updateItem(project, empty);

        if(empty){
            name.textProperty().unbind();
            name.setText("");
        }else {
            name.textProperty().setValue(project.GetName());
        }
        setGraphic(name);
    }

}
