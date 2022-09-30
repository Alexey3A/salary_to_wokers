package salary;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.SalaryWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalaryWindow extends Application {
    Worker worker;
    ArrayList<Worker> workers;

    @Override
    public void start(Stage stage) throws Exception {

        workers = getAllWorkers();

        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();

        VBox root = FXMLLoader.load(getClass().getResource("../gui_swing.fxml"));
        SalaryWindowController.showAllWorkers(getAllWorkers());


        for(Node node : root.getChildrenUnmodifiable()) {
            if (node instanceof AnchorPane) {
                anchorPane = (AnchorPane) node;
            }
        }
        System.out.println(anchorPane.getChildrenUnmodifiable());

        for(Node node : anchorPane.getChildrenUnmodifiable()) {
            if(node instanceof ScrollPane) {
                scrollPane = (ScrollPane) node;
                break;
            }
        }
        container.getChildren().addAll(new Button("111"));
        container.getChildren().add(new Button("222"));
        scrollPane.setContent(container);

        System.out.println(root.getChildren());
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

    public static void main(String[] args) {
        launch();
    }
}