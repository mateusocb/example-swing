package org.example;

import javax.swing.SwingUtilities;
import org.example.view.MainView;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}