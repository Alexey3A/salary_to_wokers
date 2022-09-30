package swing;

import com.fasterxml.jackson.databind.ObjectMapper;
import salary.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class WindowSwing implements ActionListener {

//    JTextField nameField, surnameField, salaryField;
    JButton buttonAdd;
//    JButton buttonRemove;
    JLabel label, labelContents;
    ArrayList<Worker> workers;
    JList<Worker> workerJList;
    WorkerPanel workerPanel;
    JPanel jPanel;
    ArrayList<JPanel> workerPanelList = new ArrayList<>();
    JFrame frame = new JFrame("Hello");

    WindowSwing() {
        JTextArea textArea = new JTextArea("Tru La La");
        try {
            workers = getAllWorkers();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        frame.setLayout(new FlowLayout());
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        nameField = new JTextField(15);
//        nameField.setActionCommand("name");
//        surnameField = new JTextField(15);
//        surnameField.setActionCommand("surname");
//        salaryField = new JTextField(15);
//        salaryField.setActionCommand("salary");
        buttonAdd = new JButton("Добавить");
//        buttonRemove = new JButton("Удалить");

//        nameField.addActionListener(this);
//        surnameField.addActionListener(this);
//        salaryField.addActionListener(this);
        buttonAdd.addActionListener(this);

   /*     try {
            workers = getAllWorkers();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        for(Worker www : workers){
            JLabel label = new JLabel(www.getName()
                    + " " + www.getSurname()
                    + " " + www.getSalary());
            JButton button1 = new JButton("Удалить");
            JButton button2 = new JButton("Изменить");
            button1.addActionListener(this);
            button2.addActionListener(this);
            JPanel jPanel = new JPanel();
            jPanel.add(label);
            jPanel.add(button1);
            jPanel.add(button2);
            workerPanelList.add(jPanel);
        }

//        frame.add(nameField);
//        frame.add(surnameField);
//        frame.add(salaryField);
        frame.add(buttonAdd);
//        frame.add(buttonRemove);
        frame.add(textArea);
        for (JPanel jP : workerPanelList) {
            frame.add(jP);
        }
//        frame.add(labelContents);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Добавить")) {
            AddWorkerFrame addWorkerFrame = new AddWorkerFrame();
//            salary.Worker worker = new salary.Worker();
//            worker.setName(nameField.getText());
//            worker.setSurname(surnameField.getText());
//            worker.setSalary(Double.parseDouble(salaryField.getText()));
//            File file = new File("workers.txt");
//            try {
//                worker.addAnWorker(worker, file);
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//            JLabel label = new JLabel(worker.getName()
//                    + " " + worker.getSurname()
//                    + " " + worker.getSalary());
//            JButton button1 = new JButton("Удалить");
//            JButton button2 = new JButton("Изменить");
//            button1.addActionListener(this);
//            button2.addActionListener(this);
//            JPanel jPanel = new JPanel();
//            jPanel.add(label);
//            jPanel.add(button1);
//            jPanel.add(button2);
//            workerPanelList.add(jPanel);
//            for (JPanel ww : workerPanelList)
//                frame.add(ww);
            frame.revalidate();
        }
        if (e.getActionCommand().equals("Удалить")) {
            JLabel jLabel = new JLabel("TTTTT");
            JTextArea textArea = new JTextArea("Tru La La");
            frame.add(textArea);
            frame.add(jLabel);
            frame.validate();
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowSwing();
            }
        });
    }
}
