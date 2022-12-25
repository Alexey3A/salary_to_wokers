package swing;

import javafx.scene.control.Label;
import salary.SalaryOfWorker;
import salary.SalarySource;
import salary.SalaryWindow;
import salary.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

public class Employees {
    private String name;
    private String surname;
    //    private String patronymic;
    private double salary;
    private double totalSalary;

    public static double balance;

    public Employees(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public String getName() {
        return name;
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

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public static Container updateEmployeesAndCash(Container container) throws FileNotFoundException {
        double employeeCashSum = 0;
        for (Worker w : Worker.getAllWorkers()) {
            Container c = new Container();
            BoxLayout boxH = new BoxLayout(c, BoxLayout.X_AXIS);
            c.setLayout(boxH);
            JLabel label1 = new JLabel(w.getSurname() + " " + w.getName());
            label1.setPreferredSize(new Dimension(250, 30));
            label1.setMaximumSize(new Dimension(250, 30));
            JLabel label2 = new JLabel(Double.toString(w.getSalary()));
            label2.setPreferredSize(new Dimension(70, 30));
            double totalSalaryOfWorker = SalaryOfWorker.totalSalaryOfWorker(w);
            JLabel label3 = new JLabel(Double.toString(SalaryOfWorker.rounding(totalSalaryOfWorker)));
            label3.setPreferredSize(new Dimension(130, 30));
            employeeCashSum += totalSalaryOfWorker;
//          updateWorkerInFile(w, label4);

            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(70, 30));
            textField.setMaximumSize(new Dimension(70, 30));
            textField.addActionListener(e -> System.out.println("Hello"));

            c.add(label1);
            c.add(label2);
            c.add(textField);
            c.add(label3);

            container.add(c);

        }

        balance = Cash.totalCash - employeeCashSum;
        balance = SalaryOfWorker.rounding(balance);

        return container;
    }
}
