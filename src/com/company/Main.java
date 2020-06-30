package com.company;

import java.awt.EventQueue;

import java.util.Locale;
import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        EventQueue.invokeLater(() -> {
            try {

                //region Приветственное сообщение
                JOptionPane.showMessageDialog(new JFrame(), "Хныкин, таск 8.23", "ИнфоПриветствие :)", JOptionPane.INFORMATION_MESSAGE);

                JFrame Win = new Window();
                Win.setVisible(true);
                Win.setSize(910, 520);
            } catch (Exception ex) {
                //showErrorMessageBox(ex);
            }
        });
    }
}
