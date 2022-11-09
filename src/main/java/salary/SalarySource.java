package salary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
}
