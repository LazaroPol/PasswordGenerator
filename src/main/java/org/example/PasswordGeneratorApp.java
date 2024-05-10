package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordGeneratorApp extends JFrame {
    // Componentes de la UI
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private JButton generateButton;
    private JSpinner lengthSpinner;

    public PasswordGeneratorApp() {
        createUI();
    }

    private void createUI() {
        setTitle("Password Generator");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputTextField = new JTextField(20);
        add(inputTextField);

        lengthSpinner = new JSpinner(new SpinnerNumberModel(8, 4, 20, 1));
        add(lengthSpinner);

        generateButton = new JButton("Generar y Guardar");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAndSavePassword();
            }
        });
        add(generateButton);

        outputTextArea = new JTextArea(10, 30);
        add(new JScrollPane(outputTextArea));
    }

    private void generateAndSavePassword() {
        String baseText = inputTextField.getText();
        int length = (Integer) lengthSpinner.getValue();
        String password = generatePassword(length);
        outputTextArea.setText("Texto: " + baseText + "\nContraseña: " + password);

        saveToFile(baseText, password);
    }

    private String generatePassword(int length) {
        // Implementación simple de generación de contraseñas
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    private void saveToFile(String baseText, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
            writer.write("Texto: " + baseText + " - Contraseña: " + password + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar en archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new PasswordGeneratorApp().setVisible(true);
        });
    }
}
