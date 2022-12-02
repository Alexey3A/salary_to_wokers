package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import salary.SalarySource;
import salary.SalaryWindow;
import salary.Worker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SalaryWindowController {
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonAdd1;
    @FXML
    private Button sourceSave;
    @FXML
    private Button saveWorker;
    @FXML
    private TextArea sourceName;
    @FXML
    private TextField sourceSum;
    @FXML
    private TextField surnameWorker;
    @FXML
    private TextField nameWorker;
    @FXML
    private TextField patronymicWorker;
    @FXML
    private TextField salaryWorker;
    @FXML
    private static VBox box;
    @FXML
    private static ScrollPane scrollPane;
    @FXML
    private static ScrollPane sourceField;
    @FXML
    private Label salaryTotal;
    @FXML
    private Label sourceTotal;
    @FXML
    private Pane sourceAndTotal;

    public SalaryWindowController() {
    }

    public void addWorker() {
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../new_worker.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 300, 300);
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

    public void saveWorker(){
        saveWorker.setOnAction(actionEvent -> {
            Worker worker = new Worker();
            worker.setSurname(surnameWorker.getText());
            worker.setName(nameWorker.getText());
            worker.setPatronymic(patronymicWorker.getText());
            worker.setSalary(Double.parseDouble(salaryWorker.getText()));
            try {
                worker.addAnWorker(worker);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) saveWorker.getScene().getWindow();
            stage.close();

            try {
                Worker.updateWorkerAndSalary();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addSalarySource() {
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
            }
        });
    }

    public void onClickSourceSave() {
        sourceSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SalarySource salarySource = new SalarySource();
                salarySource.setSalarySourceName(sourceName.getText());
                salarySource.setSalarySourceSum(Double.parseDouble(sourceSum.getText()));
                try {
                    salarySource.addAnSalarySource(salarySource, new File("salarySource.txt"));
                } catch (FileNotFoundException | JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage) sourceSave.getScene().getWindow();
                stage.close();

                try {
                    SalarySource.updateSourceAndTotal();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

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
}
