package salary;

import java.util.ArrayList;
import java.util.List;

public class TotalSalaryOfWorker {
    private double[] sourceSalary;
    private double sourceTotal;
    private double salaryFund;
    private Worker worker;

    public double[] getSourceSalary() {
        return sourceSalary;
    }

    public void setSourceSalary(double[] sourceSalary) {
        this.sourceSalary = sourceSalary;
    }

    public double getSourceTotal() {
        return sourceTotal;
    }

    public void setSourceTotal(double[] sourceSalary) {
        double sum = 0;
        for (int i = 0; i < sourceSalary.length; i++){
            sum = sum + sourceSalary[i];
        }
        this.sourceTotal = sum;
    }

    public double getSalaryFund() {
        return salaryFund;
    }

    public void setSalaryFund(double salaryFund) {
        this.salaryFund = salaryFund;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public double percentageOfTheSumOfAllSource(Worker worker, double sourceTotal){
        return (100* worker.getSalary())/sourceTotal;
    }

    public ArrayList<Double> salaryFromTheSource(Worker worker, double[] sourceTotal){
        ArrayList<Double> salary = new ArrayList<>();
        for (double v : sourceTotal) {
            salary.add((v * worker.getSalary()) / 100);
        }
        return salary;
    }

    public double totalSalaryOfWorker(List<Double> salaryFromTheSource){
        return salaryFromTheSource.stream().mapToDouble(Double::doubleValue).sum();
    }
}
