package salary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.*;

public class SalarySource {
    private String salarySourceName;
    private double salarySourceSum;
    public SalarySource() {
    }

    public SalarySource(String salarySourceName, double salarySourceSum) {
        this.salarySourceName = salarySourceName;
        this.salarySourceSum = salarySourceSum;
    }

    public String getSalarySourceName() {
        return salarySourceName;
    }

    public void setSalarySourceName(String salarySourceName) {
        this.salarySourceName = salarySourceName;
    }

    public double getSalarySourceSum() {
        return salarySourceSum;
    }

    public void setSalarySourceSum(double salarySourceSum) {
        this.salarySourceSum = salarySourceSum;
    }

    public void addAnSalarySource(SalarySource salarySource, File file) throws FileNotFoundException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true));
        String s = objectMapper.writeValueAsString(salarySource);
        printWriter.println(s);
        printWriter.close();
    }

    public static void updateSourceAndTotal() throws FileNotFoundException {

        SalaryWindow.salaryTotal = 0;
        VBox sourcesContainer = new VBox();
        for (SalarySource salarySource : SalaryWindow.getAllSalarySources()) {
            SalaryWindow.salaryTotal += salarySource.getSalarySourceSum();
            HBox panel = new HBox();
            Label label = new Label(salarySource.getSalarySourceName() + " : "
                    + salarySource.getSalarySourceSum());
            Button removeAnSalarySource = new Button("Удалить");

            removeAnSalarySource.setOnAction(actionEvent -> {
                System.out.println("privet");
                try {
                    deleteAnSalarySource(salarySource);
                } catch (FileNotFoundException | JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                try {
                    updateSourceAndTotal();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            Button button2 = new Button("Изменить");

            panel.getChildren().add(label);
            panel.getChildren().add(removeAnSalarySource);
            panel.getChildren().add(button2);
            sourcesContainer.getChildren().add(panel);
        }
        SalaryWindow.sourceField.setContent(sourcesContainer);

        SalaryWindow.salaryTotal = SalaryOfWorker.rounding(SalaryWindow.salaryTotal);
        SalaryWindow.salaryTotalLabel.setText(Double.toString(SalaryWindow.salaryTotal));

        Worker.updateWorkerAndSalary();
    }

    public static void deleteAnSalarySource (SalarySource salarySource) throws FileNotFoundException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("salarySource.txt");
        List<SalarySource> salarySourceList = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            try {
                SalarySource source = objectMapper.readValue(s, SalarySource.class);
                System.out.println(salarySource + " --- " + source);
                if (!salarySource.equals(source)) {
                    salarySourceList.add(source);
                }
            } catch (Exception e) {
                System.out.println("Exception!!!");
            }
        }
        scanner.close();

        salarySourceList.forEach(System.out::println);

        PrintWriter printWriter = new PrintWriter(file);
        for (SalarySource source : salarySourceList) {
            printWriter.println(objectMapper.writeValueAsString(source));
        }
        printWriter.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalarySource source = (SalarySource) o;
        return Double.compare(source.salarySourceSum, salarySourceSum) == 0 && Objects.equals(salarySourceName, source.salarySourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salarySourceName, salarySourceSum);
    }
}
