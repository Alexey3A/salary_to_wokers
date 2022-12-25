package salary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SalaryWindow extends Application {
    public static ArrayList<Worker> workers;
    public static VBox root;
    public static ScrollPane workerField;
    public static ScrollPane sourceField;
    public static Label salaryTotalLabel;
    public static Label balance;
    public static double salaryTotal;
    public static double balanceForLabel;

    @Override
    public void start(Stage stage) throws Exception {

        workers = Worker.getAllWorkers();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../gui_swing.fxml"));

        root = fxmlLoader.load();

        workerField = (ScrollPane) root.lookup("#workerField");
        sourceField = (ScrollPane) root.lookup("#sourceField");
        salaryTotalLabel = (Label) root.lookup("#salaryTotal");
        balance = (Label) root.lookup("#balance");

        SalarySource.updateSourceAndTotal();

        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("H W");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
