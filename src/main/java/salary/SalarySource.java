package salary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

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
//        Pane pane = new Pane();
        double salaryTotal = 0;
        VBox sourcesContainer = new VBox();
        for (SalarySource salarySource : SalaryWindow.getAllSalarySources()) {
            salaryTotal += salarySource.getSalarySourceSum();
            HBox panel = new HBox();
            Label label = new Label(salarySource.getSalarySourceName() + " : "
                    + salarySource.getSalarySourceSum());
            Button button1 = new Button("Удалить");
            Button button2 = new Button("Изменить");

            panel.getChildren().add(label);
            panel.getChildren().add(button1);
            panel.getChildren().add(button2);
            sourcesContainer.getChildren().add(panel);
        }
        SalaryWindow.sP.setContent(sourcesContainer);

        SalaryWindow.salaryTotalLabel.setText(Double.toString(salaryTotal));
//        pane.getChildren().addAll(sourcesContainer, )
    }
}
