package salary;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class SalaryOfWorker {

    public static double salaryFund() throws FileNotFoundException {
        double salaryFund = 0;
        ArrayList<Worker> workers = Worker.getAllWorkers();
        for (Worker w : workers) {
            salaryFund += w.getSalary();
        }
        return salaryFund;
    }

    public static double percentageOffTheSumOffAllSources(Worker worker) throws FileNotFoundException {
        return (100* worker.getSalary())/salaryFund();
    }
/**/
    public static ArrayList<Double> salaryOfWorkerFromTheSource(Worker worker) throws FileNotFoundException {
        ArrayList<Double> salaryOfWorkerFromTheSource = new ArrayList<>();
        for (SalarySource salarySource : SalaryWindow.getAllSalarySources()){
            salaryOfWorkerFromTheSource.add(
                    (salarySource.getSalarySourceSum()*percentageOffTheSumOffAllSources(worker))/100
            );
        }
        return salaryOfWorkerFromTheSource;
    }

    public static double totalSalaryOfWorker(Worker worker) throws FileNotFoundException {
        double totalSalaryOfWorker = 0;
        for(Double salary : salaryOfWorkerFromTheSource(worker)){
            totalSalaryOfWorker += salary;
        }
        return totalSalaryOfWorker;
    }

    public static double rounding(double d){
        BigDecimal balanceForLabelBigDecimal = new BigDecimal(d);
        balanceForLabelBigDecimal = balanceForLabelBigDecimal.setScale(2, RoundingMode.HALF_EVEN);
        return balanceForLabelBigDecimal.doubleValue();
    }
}
