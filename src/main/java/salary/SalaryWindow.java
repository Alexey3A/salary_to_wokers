package salary;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalaryWindow extends Application {
    Worker worker;
    SalarySource salarySource;
    ArrayList<Worker> workers;
    ArrayList<SalarySource> salarySourcesList;

    @Override
    public void start(Stage stage) throws Exception {

        workers = getAllWorkers();
        salarySourcesList = getAllSalarySources();

        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox workersContainer = new VBox();

        VBox root = FXMLLoader.load(getClass().getResource("../gui_swing.fxml"));

        ScrollPane sP = (ScrollPane) root.lookup("#salaryField");
        VBox sourcesContainer = new VBox();

        for (Node node : root.getChildrenUnmodifiable()) {
            if (node instanceof AnchorPane) {
                anchorPane = (AnchorPane) node;
            }
        }

        for (Node node : anchorPane.getChildrenUnmodifiable()) {
            if (node instanceof ScrollPane) {
                scrollPane = (ScrollPane) node;
                break;
            }
        }

        workers = getAllWorkers();

        for (Worker w : workers) {
            worker = w;
            HBox panel = new HBox();
            Label label = new Label(w.getName() + " " + w.getSurname() + " " + w.getSalary());
            Button button1 = new Button("Удалить");
            Button button2 = new Button("Изменить");

            panel.getChildren().add(label);
            panel.getChildren().add(button1);
            panel.getChildren().add(button2);
            workersContainer.getChildren().add(panel);
        }

        scrollPane.setContent(workersContainer);

        for (SalarySource w : salarySourcesList) {
            salarySource = w;
            HBox panel = new HBox();
            Label label = new Label(w.getSalarySourceName() + " : "
                    + salarySource.getSalarySourceSum());
            Button button1 = new Button("Удалить");
            Button button2 = new Button("Изменить");

            panel.getChildren().add(label);
            panel.getChildren().add(button1);
            panel.getChildren().add(button2);
            sourcesContainer.getChildren().add(panel);
        }
        sP.setContent(sourcesContainer);

        Scene scene = new Scene(root, 650, 400);
        stage.setTitle("H W");
        stage.setScene(scene);
        stage.show();
    }

    public static ArrayList<Worker> getAllWorkers() throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Worker> workers = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream("workers.txt"));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            try {
                Worker worker = objectMapper.readValue(s, Worker.class);
                workers.add(worker);
            } catch (Exception e) {
                System.out.println("Exception!!!");
            }
        }
        scanner.close();
        return workers;
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
