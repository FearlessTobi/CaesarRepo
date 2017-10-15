package de.fearlessTobi.caesartools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static de.fearlessTobi.caesartools.ProgramLogic.*;

public class UserInterface extends JFrame {

    public JPanel contentPane;

    public JButton btnBack;
    public JButton btnForward;

    public JLabel lblEnterEncryptedText;
    public JLabel lblEnterTextToEncrypt;
    public JLabel lblAverageDifference;
    public JLabel lblShiftBy;
    public JLabel lblCharacters;

    public JTextArea taEncryptedTextInput;
    public JTextArea taEncryptedTextOutput;
    public JTextArea taDecryptedTextInput;
    public JTextArea taDecryptedTextOutput;

    public JTextField tfAverageDifference;
    public JTextField tfShiftBy;

    public JLabel lblDecryptedText;
    public JLabel lblEncryptedText;

    public JButton btnDecrypt;
    public JButton btnToDecryptorSmall;
    public JButton btnToDecryptor;

    public JButton btnEncrypt;
    public JButton btnToEncryptorSmall;
    public JButton btnToEncryptor;

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        EventQueue.invokeLater(() -> {
                UserInterface frame = new UserInterface();
                frame.setVisible(true);
        });
    }

    public UserInterface() {

        setResizable(false);
        setTitle("Caesar Tools 2.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 330);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        createContentPaneObjects();
        addActionListeners();

    }

    public void createContentPaneObjects() {

        btnBack = new JButton("<<");
        btnBack.setBounds(10, 258, 58, 23);
        btnBack.setEnabled(true);
        btnBack.setVisible(false);
        contentPane.add(btnBack);

        btnForward = new JButton(">>");
        btnForward.setBounds(368, 258, 58, 23);
        btnForward.setEnabled(true);
        btnForward.setVisible(false);
        contentPane.add(btnForward);

        lblEnterEncryptedText = new JLabel("Encrypted text");
        lblEnterEncryptedText.setBounds(171, 5, 97, 14);
        lblEnterEncryptedText.setVisible(false);
        contentPane.add(lblEnterEncryptedText);

        lblEnterTextToEncrypt = new JLabel("Non-Encrypted text");
        lblEnterTextToEncrypt.setBounds(171, 5, 97, 14);
        lblEnterTextToEncrypt.setVisible(false);
        contentPane.add(lblEnterTextToEncrypt);

        lblAverageDifference = new JLabel("Average difference:");
        lblAverageDifference.setBounds(98, 234, 97, 14);
        lblAverageDifference.setVisible(false);
        contentPane.add(lblAverageDifference);

        lblShiftBy = new JLabel("Shift by");
        lblShiftBy.setBounds(136, 234, 37, 14);
        lblShiftBy.setVisible(false);
        contentPane.add(lblShiftBy);

        lblCharacters = new JLabel("character(s)");
        lblCharacters.setBounds(267, 234, 64, 14);
        lblCharacters.setVisible(false);
        contentPane.add(lblCharacters);

        taEncryptedTextInput = new JTextArea();
        taEncryptedTextInput.setBounds(36, 25, 364, 78);
        taEncryptedTextInput.setWrapStyleWord(true);
        taEncryptedTextInput.setColumns(10);
        taEncryptedTextInput.setLineWrap(true);
        taEncryptedTextInput.setVisible(false);
        contentPane.add(taEncryptedTextInput);

        taEncryptedTextOutput = new JTextArea();
        taEncryptedTextOutput.setBounds(36, 146, 364, 78);
        taEncryptedTextOutput.setWrapStyleWord(true);
        taEncryptedTextOutput.setEnabled(true);
        taEncryptedTextOutput.setEditable(false);
        taEncryptedTextOutput.setVisible(false);
        taEncryptedTextOutput.setColumns(10);
        taEncryptedTextOutput.setLineWrap(true);
        contentPane.add(taEncryptedTextOutput);

        taDecryptedTextInput = new JTextArea();
        taDecryptedTextInput.setBounds(36, 25, 364, 78);
        taDecryptedTextInput.setWrapStyleWord(true);
        taDecryptedTextInput.setColumns(10);
        taDecryptedTextInput.setLineWrap(true);
        taDecryptedTextInput.setVisible(false);
        contentPane.add(taDecryptedTextInput);

        taDecryptedTextOutput = new JTextArea();
        taDecryptedTextOutput.setBounds(36, 146, 364, 78);
        taDecryptedTextOutput.setWrapStyleWord(true);
        taDecryptedTextOutput.setEnabled(true);
        taDecryptedTextOutput.setEditable(false);
        taDecryptedTextOutput.setVisible(false);
        taDecryptedTextOutput.setColumns(10);
        taDecryptedTextOutput.setLineWrap(true);
        contentPane.add(taDecryptedTextOutput);

        tfAverageDifference = new JTextField();
        tfAverageDifference.setBounds(248, 231, 86, 20);
        tfAverageDifference.setEditable(false);
        tfAverageDifference.setColumns(10);
        tfAverageDifference.setVisible(false);
        contentPane.add(tfAverageDifference);

        tfShiftBy = new JTextField();
        tfShiftBy.setBounds(183, 231, 74, 20);
        tfShiftBy.setColumns(10);
        tfShiftBy.setVisible(false);
        contentPane.add(tfShiftBy);

        lblDecryptedText = new JLabel("Decrypted text");
        lblDecryptedText.setBounds(171, 125, 74, 14);
        lblDecryptedText.setVisible(false);
        contentPane.add(lblDecryptedText);

        lblEncryptedText = new JLabel("Encrypted text");
        lblEncryptedText.setBounds(171, 125, 74, 14);
        lblEncryptedText.setVisible(false);
        contentPane.add(lblEncryptedText);

        btnToDecryptorSmall = new JButton("Go to Decryptor");
        btnToDecryptorSmall.setBounds(317, 1, 117, 23);
        btnToDecryptorSmall.setEnabled(true);
        btnToDecryptorSmall.setVisible(false);
        contentPane.add(btnToDecryptorSmall);

        btnToDecryptor = new JButton("Go to Decryptor");
        btnToDecryptor.setBounds(251, 25, 152, 243);
        contentPane.add(btnToDecryptor);

        btnDecrypt = new JButton("Decrypt");
        btnDecrypt.setBounds(171, 258, 97, 23);
        btnDecrypt.setVisible(false);
        btnDecrypt.setEnabled(true);
        contentPane.add(btnDecrypt);

        btnToEncryptorSmall = new JButton("Go to Encryptor");
        btnToEncryptorSmall.setBounds(317, 1, 117, 23);
        btnToEncryptorSmall.setEnabled(true);
        btnToEncryptorSmall.setVisible(false);
        contentPane.add(btnToEncryptorSmall);

        btnToEncryptor = new JButton("Go to Encryptor");
        btnToEncryptor.setBounds(36, 25, 152, 243);
        contentPane.add(btnToEncryptor);

        btnEncrypt = new JButton("Encrypt");
        btnEncrypt.setBounds(171, 258, 97, 23);
        btnEncrypt.setVisible(false);
        btnEncrypt.setEnabled(true);
        contentPane.add(btnEncrypt);

    }

    public void addActionListeners() {

        btnBack.addActionListener(e -> {
            ValuePair forwardedValues = goForward(-1);

            if(forwardedValues!=null) {
                tfAverageDifference.setText(String.valueOf(forwardedValues.getLeastAverageDifferenceRounded()));
                taDecryptedTextOutput.setText(forwardedValues.getShiftedStringWithLeastAverageDifference());
            }
    });

        btnForward.addActionListener(e -> {
            ValuePair newOutputText = goForward(1);

            if(newOutputText!=null) {
                tfAverageDifference.setText(String.valueOf(newOutputText.getLeastAverageDifferenceRounded()));
                taDecryptedTextOutput.setText(newOutputText.getShiftedStringWithLeastAverageDifference());
            }
        });

        btnDecrypt.addActionListener(e -> {
            long currentTime = System.currentTimeMillis();
            String encryptedText = taEncryptedTextInput.getText();

            lblAverageDifference.setVisible(true);
            tfAverageDifference.setVisible(true);
            ValuePair decryptedTextValues = decryptText(encryptedText);
            taDecryptedTextOutput.setText(decryptedTextValues.getShiftedStringWithLeastAverageDifference());
            tfAverageDifference.setText(String.valueOf(decryptedTextValues.getLeastAverageDifferenceRounded()));

            if (decryptedTextValues.getIsTotalZero()) {
                btnBack.setVisible(false);
                btnForward.setVisible(false);
            } else {
                btnBack.setVisible(true);
                btnForward.setVisible(true);
            }

            long currentTimeAfter = System.currentTimeMillis();
            System.out.println(currentTimeAfter - currentTime + "ms");
        });

        btnToDecryptorSmall.addActionListener(arg0 -> showDecryptor());

        btnToDecryptor.addActionListener(e -> showDecryptor());

        btnToEncryptorSmall.addActionListener(e -> showEncryptor());

        btnToEncryptor.addActionListener(e -> showEncryptor());

        btnEncrypt.addActionListener(e -> {
            String decryptedText = taDecryptedTextInput.getText();
            String shiftByString = tfShiftBy.getText();
            int shiftBy;

            try {
                shiftBy = Integer.valueOf(shiftByString);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "\"" + shiftByString + "\" is no valid number!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            taEncryptedTextOutput.setText(encryptText(decryptedText, shiftBy));
        });

    }

    public void showDecryptor() {
        btnDecrypt.setVisible(true);
        btnToEncryptorSmall.setVisible(true);
        lblEnterEncryptedText.setVisible(true);
        lblDecryptedText.setVisible(true);
        taEncryptedTextInput.setVisible(true);
        taDecryptedTextOutput.setVisible(true);
        btnToDecryptor.setVisible(false);
        btnToEncryptor.setVisible(false);

        btnEncrypt.setVisible(false);
        taDecryptedTextInput.setVisible(false);
        taEncryptedTextOutput.setVisible(false);
        lblEnterTextToEncrypt.setVisible(false);
        btnToDecryptorSmall.setVisible(false);
        lblEncryptedText.setVisible(false);
        lblShiftBy.setVisible(false);
        lblCharacters.setVisible(false);
        tfShiftBy.setVisible(false);

        taDecryptedTextInput.setText(null);
        taEncryptedTextOutput.setText(null);
        tfShiftBy.setText(null);
    }

    public void showEncryptor() {
        btnEncrypt.setVisible(true);
        taDecryptedTextInput.setVisible(true);
        taEncryptedTextOutput.setVisible(true);
        lblEnterTextToEncrypt.setVisible(true);
        btnToDecryptorSmall.setVisible(true);
        lblEncryptedText.setVisible(true);
        lblShiftBy.setVisible(true);
        lblCharacters.setVisible(true);
        tfShiftBy.setVisible(true);
        btnToDecryptor.setVisible(false);
        btnToEncryptor.setVisible(false);

        btnDecrypt.setVisible(false);
        btnToEncryptorSmall.setVisible(false);
        lblEnterEncryptedText.setVisible(false);
        lblDecryptedText.setVisible(false);
        taEncryptedTextInput.setVisible(false);
        taDecryptedTextOutput.setVisible(false);
        lblAverageDifference.setVisible(false);
        tfAverageDifference.setVisible(false);
        btnBack.setVisible(false);
        btnForward.setVisible(false);

        taEncryptedTextInput.setText(null);
        taDecryptedTextOutput.setText(null);
    }


}