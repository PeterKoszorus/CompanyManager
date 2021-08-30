package CellDesignes;

import Users.Employee;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * Trieda kotra ma nastarosti dizajn jednotlivych celov v listview typu Employee
 */
public class UsernameCellDesign extends ListCell<Employee> {

    private final Label username;

    // Constructor if we want to change the design of the cell we have to make it here
    public UsernameCellDesign() {

        username = new Label();

    }

    @Override
    protected void updateItem(Employee user, boolean empty){
        super.updateItem(user, empty);

        if(empty){
            username.textProperty().unbind();
            username.setText("");
        }else {
            username.textProperty().setValue(user.GetUsername());
        }
        setGraphic(username);
    }
}
