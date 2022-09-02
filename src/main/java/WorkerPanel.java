import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WorkerPanel extends JPanel implements ActionListener {

    Worker worker;
    //    ArrayList<Worker> wL;
//    JFrame jF;
    JPanel p = new JPanel();

    public WorkerPanel() {
    }

    public WorkerPanel(ArrayList<Worker> workersList, JFrame frame) {
//        wL = workersList;
//        jF = frame;
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        for (Worker w : workersList) {
            worker = w;
            JPanel panel = new JPanel();
            JLabel label = new JLabel(w.getName() + " " + w.getSurname() + " " + w.getSalary());
            JButton button1 = new JButton("Удалить");
            JButton button2 = new JButton("Изменить");
            button1.addActionListener(this);
            button2.addActionListener(this);

            panel.add(label);
            panel.add(button1);
            panel.add(button2);
            p.add(panel);

            button1.addActionListener(this);
//            frame.add(label);
//            frame.add(button1);
//            frame.add(button2);
            frame.add(p);
            frame.validate();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Удалить")) {
            try {
                Worker.deleteAnWorker(worker, new File("workers.txt"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        p.revalidate();
    }
}
