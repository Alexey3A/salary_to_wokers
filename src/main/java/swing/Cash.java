package swing;

import salary.SalaryOfWorker;
import salary.SalarySource;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Cash {
    public static double totalCash;
    public static Container cash(Container container) throws FileNotFoundException {

        totalCash = 0;
        for (SalarySource salarySource : SalarySource.getAllSalarySources()) {
            Container c = new Container();
            BoxLayout boxH = new BoxLayout(c, BoxLayout.X_AXIS);
            c.setLayout(boxH);

            c.add( new Label(salarySource.getSalarySourceName() + " : "
                                                            + salarySource.getSalarySourceSum()));
            JButton updateCash = new JButton("...");
            c.add(updateCash);

            totalCash += salarySource.getSalarySourceSum();
            totalCash = SalaryOfWorker.rounding(totalCash);

            container.add(c);
            container.add(new Label());
        }


        return container;
    }
}
