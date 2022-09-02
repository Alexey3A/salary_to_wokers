import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryWindow implements ActionListener {

    @FXML
    VBox box;
    @FXML
    JButton buttonAdd;

   SalaryWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SalaryWindow.class.getResource("/gui_swing.fxml"));
        SalaryWindow salaryWindow = loader.getController();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SalaryWindow();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
