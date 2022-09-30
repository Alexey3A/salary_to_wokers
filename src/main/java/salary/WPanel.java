package salary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class WPanel {
    Worker worker;
    Pane pane = new Pane();

    public WPanel() {
    }

    public WPanel(ArrayList<Worker> workersList, ScrollPane scrollPane) {

        //jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        for (Worker w : workersList) {
            worker = w;
            Pane panel = new Pane();
            Label label = new Label(w.getName() + " " + w.getSurname() + " " + w.getSalary());
            Button button1 = new Button("Удалить");
            Button button2 = new Button("Изменить");

            panel.getChildren().add(label);
            panel.getChildren().add(button1);
            panel.getChildren().add(button2);
            pane.getChildren().add(panel);

            scrollPane.setContent(pane);
        }
    }
}
