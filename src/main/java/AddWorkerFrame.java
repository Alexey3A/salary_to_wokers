import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkerFrame implements ActionListener {
    JTextField nameField, surnameField, patronymicField, salaryField;
    JButton buttonAdd;
    JLabel nameLabel = new JLabel("имя");
    JLabel patronymicLabel = new JLabel("отчество");
    JLabel surnameLabel = new JLabel("фамилия");
    JLabel salaryLabel = new JLabel("зарплата");

    JFrame frame = new JFrame("New worker");
//    CardLayout cardLayout = new CardLayout();

    AddWorkerFrame(){
        GridLayout gridLayout = new GridLayout(1,2);
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        nameField = new JTextField(15);
        nameField.setActionCommand("name");
        surnameField = new JTextField(15);
        surnameField.setActionCommand("surname");
        salaryField = new JTextField(15);
        salaryField.setActionCommand("salary");
        patronymicField = new JTextField(15);
        patronymicField.setActionCommand("patronymic");
        buttonAdd = new JButton("Добавить");

        nameField.addActionListener(this);
        surnameField.addActionListener(this);
        salaryField.addActionListener(this);
        patronymicField.addActionListener(this);
        buttonAdd.addActionListener(this);

        panel1.add(nameLabel);
        panel1.add(nameField);
        panel2.add(patronymicLabel);
        panel2.add(patronymicField);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        frame.add(panel1);
        frame.add(panel2);

//        frame.add(nameField);
//        frame.add(surnameField);
//        frame.add(salaryField);
//        frame.add(buttonAdd);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
