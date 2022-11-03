package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import salary.WPanel;
import salary.Worker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalaryWindowController {
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonAdd1;
    @FXML
    private Button sourceSave;
    @FXML
    private TextArea sourceName;
    @FXML
    private TextField sourceSum;
    @FXML
    private static VBox box;

    @FXML
    private static ScrollPane scrollPane;

    public SalaryWindowController() {
    }

    public void onClickButtonAdd() {
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../new_worker.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 300, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Сведения о работнике");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addSalarySource(Stage stage) {
        buttonAdd1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../new_a_salary_source.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 200, 200);
                    Stage stage = new Stage();
                    stage.setTitle("Источник зарплаты");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sourceSave.setOnAction(event -> {

                });
            }
        });
    }

//TODO
public void onClickSourceSave(){
        sourceSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
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

    public static void showAllWorkers(ArrayList<Worker> workersList) {
        new WPanel(workersList, new ScrollPane());
    }
}
