package salary;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalaryWindow extends Application {
    Worker worker;
    SalarySource salarySource;
    public static ArrayList<Worker> workers;
    public static ArrayList<SalarySource> salarySourcesList;
    public static VBox root;
    public static ScrollPane workerField;
    public static ScrollPane sourceField;
    public static Label salaryTotalLabel;
    public static Label balance;
    public static Pane sourceAndTotal;
    public static double salaryTotal;
    public static double balanceForLabel;

    @Override
    public void start(Stage stage) throws Exception {

        workers = Worker.getAllWorkers();
        salarySourcesList = getAllSalarySources();

        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../gui_swing.fxml"));

        root = fxmlLoader.load();

        workerField = (ScrollPane) root.lookup("#workerField");
        sourceField = (ScrollPane) root.lookup("#sourceField");
        salaryTotalLabel = (Label) root.lookup("#salaryTotal");
        balance = (Label) root.lookup("#balance");

        workers = Worker.getAllWorkers();

        SalarySource.updateSourceAndTotal();

        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("H W");
        stage.setScene(scene);
        stage.show();
    }

    public static ArrayList<SalarySource> getAllSalarySources() throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<SalarySource> sources = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream("salarySource.txt"));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            try {
                SalarySource source = objectMapper.readValue(s, SalarySource.class);
                sources.add(source);
            } catch (Exception e) {
                System.out.println("Exception!!!");
            }
        }
        scanner.close();
        return sources;
    }

    public static void main(String[] args) {
        launch();
    }
}
