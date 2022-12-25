package swing;

import salary.SalaryWindow;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class FirstWindow extends JFrame {
    FirstWindow() throws FileNotFoundException {
        super("First window");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();

        BoxLayout boxV = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(boxV);

        FlowLayout workerAndSalaryButtons = new FlowLayout(FlowLayout.LEFT);
        Container container1 = new Container();
        container1.setLayout(workerAndSalaryButtons);
        JButton addAnWorkerButton = new JButton("Добавить работника");
        JButton addSalarySourceButton = new JButton("Добавить источник зп");
        addSalarySourceButton.setHorizontalAlignment(SwingConstants.RIGHT);
        container1.add(addAnWorkerButton);
        container1.add(addSalarySourceButton);

        FlowLayout workerAndSalary = new FlowLayout(FlowLayout.LEFT);
        Container container2 = new Container();
        container2.setLayout(workerAndSalary);

        Container container4 = new Container();
        BoxLayout boxV2 = new BoxLayout(container4, BoxLayout.Y_AXIS);
        container4.setLayout(boxV2);
        Cash.cash(container4);
        JScrollPane sourceSalaryScrollPane = new JScrollPane(container4
                ,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sourceSalaryScrollPane.setPreferredSize(new Dimension(300, 500));
        container2.add(sourceSalaryScrollPane);

        Container container3 = new Container();
        BoxLayout boxV1 = new BoxLayout(container3, BoxLayout.Y_AXIS);
        container3.setLayout(boxV1);
        Employees.updateEmployeesAndCash(container3);
        JScrollPane workersScrollPane = new JScrollPane(container3, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        workersScrollPane.setPreferredSize(new Dimension(500, 500));
        container2.add(workersScrollPane);



        FlowLayout bottom = new FlowLayout(FlowLayout.LEFT);
        Container container5 = new Container();
        container5.setLayout(workerAndSalary);
        container5.add(new Label("Итого : " + Double.toString(Cash.totalCash)));
        container5.add(new Label("Остаток : " + Double.toString(Employees.balance)));

        container.add(container1);
        container.add(container2);
        container.add(container5);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new FirstWindow();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
