package salary;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.*;

public class Worker {
    private String name;
    private String surname;
    private double salary;
    //    private double percentageOfTheSumOfAllSources;
//    private double salaryFromTheSource;
    private double totalSalary;
    private int numberOfDaysMissed;

    public String getName() {
        return name;
    }

    public Worker() {
    }

    public Worker(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getNumberOfDaysMissed() {
        return numberOfDaysMissed;
    }

    public void setNumberOfDaysMissed(int numberOfDaysMissed) {
        this.numberOfDaysMissed = numberOfDaysMissed;
    }

    //    public double getPercentageOfTheSumOfAllSources() {
//        return percentageOfTheSumOfAllSources;
//    }
//
//    public void setPercentageOfTheSumOfAllSources(double percentageOfTheSumOfAllSources) {
//        this.percentageOfTheSumOfAllSources = percentageOfTheSumOfAllSources;
//    }
//
//    public double getSalaryFromTheSource() {
//        return salaryFromTheSource;
//    }
//
//    public void setSalaryFromTheSource(double salaryFromTheSource) {
//        this.salaryFromTheSource = salaryFromTheSource;
//    }
//
    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Double.compare(worker.salary, salary) == 0 && name.equals(worker.name) && surname.equals(worker.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, salary);
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

    public void addAnWorker(Worker w) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("workers.txt");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true));
        String s = objectMapper.writeValueAsString(w);
        printWriter.println(s);
        printWriter.close();

        Scanner scanner = new Scanner(new FileInputStream(file));
        Set<Worker> workerSet = new HashSet<>();
        while (scanner.hasNextLine()) {
            String s2 = scanner.nextLine();
            try {
                Worker worker = objectMapper.readValue(s2, Worker.class);
                workerSet.add(worker);
            } catch (JsonParseException | MismatchedInputException ignored) {
                System.out.println("JsonParseException or MismatchedInputException");
            }
        }

        PrintWriter printWriter2 = new PrintWriter(new FileOutputStream(file));
        for (Worker worker : workerSet) {
            String s3 = objectMapper.writeValueAsString(worker);
            printWriter2.println(s3);
        }
        printWriter2.close();
    }

    public static void deleteAnWorker(Worker w, File file) throws FileNotFoundException
            , JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Worker> workerSet = new HashSet<>();
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            try {
                Worker worker = objectMapper.readValue(s, Worker.class);
                if (!w.equals(worker)) {
                    workerSet.add(worker);
                }
            } catch (Exception e) {
                System.out.println("Exception!!!");
            }
        }
        scanner.close();
        PrintWriter printWriter = new PrintWriter(file);
        for (Worker worker : workerSet) {
            printWriter.println(objectMapper.writeValueAsString(worker));
        }
        printWriter.close();
    }

    //TODO
    public static void updateWorkerAndSalary() throws FileNotFoundException {
        VBox workersContainer = new VBox();
        for (Worker w : getAllWorkers()) {
            Label label1 = new Label(w.getSurname());
            Label label2 = new Label(w.getName());
            Label label3 = new Label(Double.toString(w.getSalary()));
            double totalSalaryOfWorker = SalaryOfWorker.totalSalaryOfWorker(w);
            Label label4 = new Label(Double.toString(SalaryOfWorker.rounding(totalSalaryOfWorker)));
            SalaryWindow.balanceForLabel += totalSalaryOfWorker;
            TextField zpNow = new TextField();

            zpNow.setOnAction(actionEvent -> {
//                label4.setText(zpNow.getText());

                //TODO

                /*double sum = 0;
                try {
                    for (Worker worker1 : Worker.getAllWorkers()) {
                        sum += worker1.getSalary();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                SalaryWindow.balance.setText(Double.toString(SalaryOfWorker.rounding(sum)));
                try {
                    updateWorkerAndSalary();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }*/
            });

            Button button = new Button("Рассчитать");
            label1.setMinWidth(130);
            label2.setMinWidth(130);
            label3.setMinWidth(60);
            label4.setMinWidth(60);
            zpNow.setMaxWidth(80);
            button.setMinWidth(80);
            HBox hBox = new HBox(15, label1, label2, label3, zpNow, label4, button);
            workersContainer.getChildren().add(hBox);
        }

        double d = Double.parseDouble(SalaryWindow.salaryTotalLabel.getText());
        SalaryWindow.balanceForLabel = d - SalaryWindow.balanceForLabel;
        SalaryWindow.balanceForLabel = SalaryOfWorker.rounding(SalaryWindow.balanceForLabel);
        SalaryWindow.balance.setText(Double.toString(SalaryWindow.balanceForLabel));

        SalaryWindow.workerField.setContent(workersContainer);
    }
}
