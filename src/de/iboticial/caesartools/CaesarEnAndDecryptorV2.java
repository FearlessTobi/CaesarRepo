package de.iboticial.caesartools;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CaesarEnAndDecryptorV2 extends JFrame {

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

	TreeMap<Double, String> shiftedStringsByAverageDifference = new TreeMap<>();
	int elementIndex;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CaesarEnAndDecryptorV2 frame = new CaesarEnAndDecryptorV2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CaesarEnAndDecryptorV2() {

		setResizable(false);
		setTitle("Caesar Tools 2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taDecryptedTextOutput.setText(goForward(-1));
			}
		});

		btnForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taDecryptedTextOutput.setText(goForward(1));
			}
		});

		btnDecrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTime = System.currentTimeMillis();
				String encryptedText = taEncryptedTextInput.getText();
				taDecryptedTextOutput.setText(decryptText(encryptedText));
				long currentTimeAfter = System.currentTimeMillis();
				System.out.println(currentTimeAfter - currentTime + "ms");
			}
		});

		btnToDecryptorSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showDecryptor();
			}
		});

		btnToDecryptor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDecryptor();
			}
		});

		btnToEncryptorSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEncryptor();
			}
		});

		btnToEncryptor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEncryptor();
			}
		});

		btnEncrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String decryptedText = taDecryptedTextInput.getText();
				String shiftByString = tfShiftBy.getText();
				int shiftBy = -1;

				try {
					shiftBy = Integer.valueOf(shiftByString);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "\"" + shiftByString + "\" is no valid number!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				taEncryptedTextOutput.setText(encryptText(decryptedText, shiftBy));
			}
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

	public String decryptText(String encryptedText) {

		lblAverageDifference.setVisible(true);
		tfAverageDifference.setVisible(true);

		shiftedStringsByAverageDifference.clear();
		elementIndex = 0;

		HashMap<Integer, String> shiftedStrings = new HashMap<>();
		double[] characterFrequencyWeights = getCharacterFrequencyWeights();

		for (int i = 0; i < 26; i++) {

			// Shift text by "i"
			shiftedStrings.put(i, encryptText(encryptedText, i));

			int[] characterFrequency = new int[26];
			int total = 0;
			ArrayList<Double> differences = new ArrayList<>();

			// Count all alphabetic letters in the String
			for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
				int countedLetter = (countLetter(shiftedStrings.get(i), alphabet));
				characterFrequency[alphabet - 65] = countedLetter;

				total += countedLetter;
			}

			// Check if there is no alphabetical letter in the String
			if (total == 0) {
				tfAverageDifference.setText("0.0");
				btnBack.setVisible(false);
				btnForward.setVisible(false);
				return encryptedText;
			} else {
				btnBack.setVisible(true);
				btnForward.setVisible(true);
			}

			for (int f = 0; f < 26; f++) {

				if (characterFrequency[f] != 0) {

					double characterFrequencyPercent = characterFrequency[f] / (double) total * 100;
					double difference;

					// Calculate difference from character frequency weights for
					// each character which isnt't 0
					if (characterFrequencyWeights[f] > characterFrequencyPercent) {
						difference = characterFrequencyWeights[f] - characterFrequencyPercent;
					} else {
						difference = characterFrequencyPercent - characterFrequencyWeights[f];
					}

					differences.add(difference);

				}

			}

			double totalDifference = 0;

			// Add all differences to a totalDifference
			for (double dif : differences) {
				totalDifference += dif;
			}

			double averageDifference = totalDifference / differences.size();

			// If object key already exists in TreeMap
			while (shiftedStringsByAverageDifference.containsKey(averageDifference))
				averageDifference += 0.00001;

			shiftedStringsByAverageDifference.put(averageDifference, shiftedStrings.get(i));

		}

		// Get String with least average difference
		double leastAverageDifference = (double) shiftedStringsByAverageDifference.keySet().toArray()[elementIndex];
		double leastAverageDifferenceRounded = Math.round(leastAverageDifference * 100) / 100.0;

		String shiftedStringWithLeastAverageDifference = shiftedStringsByAverageDifference.get(leastAverageDifference);

		tfAverageDifference.setText(String.valueOf(leastAverageDifferenceRounded));

		return shiftedStringWithLeastAverageDifference;

	}

	public String encryptText(String decryptedText, int shiftBy) {

		String encryptedText = "";

		shiftBy = normalizeShiftBy(shiftBy);

		for (int i = 0; i < decryptedText.length(); i++) {

			char charToShift = decryptedText.charAt(i);

			if ((charToShift >= 'a' && charToShift <= 'z') || (charToShift >= 'A' && charToShift <= 'Z')) {

				charToShift += shiftBy;

				if (Character.isUpperCase(decryptedText.charAt(i)) && charToShift > 90)
					charToShift -= 26;
				else if (Character.isLowerCase(decryptedText.charAt(i)) && charToShift > 122)
					charToShift -= 26;

			}

			encryptedText += charToShift;

		}

		return encryptedText;

	}

	public String goForward(int amount) {

		elementIndex += amount;

		if (shiftedStringsByAverageDifference == null || shiftedStringsByAverageDifference.size() == 0)
			return taEncryptedTextInput.getText();

		if (elementIndex > 25)
			elementIndex -= 26;

		if (elementIndex < 0)
			elementIndex += 26;

		double leastAverageDifference = (double) shiftedStringsByAverageDifference.keySet().toArray()[elementIndex];
		double leastAverageDifferenceRounded = Math.round(leastAverageDifference * 100) / 100.0;

		String shiftedStringWithLeastAverageDifference = shiftedStringsByAverageDifference.get(leastAverageDifference);

		tfAverageDifference.setText(String.valueOf(leastAverageDifferenceRounded));

		return shiftedStringWithLeastAverageDifference;

	}

	public int normalizeShiftBy(int shiftBy) {

		while (shiftBy > 26) {
			shiftBy -= 26;
		}

		while (shiftBy < 0) {
			shiftBy += 26;
		}

		return shiftBy;

	}

	public static int countLetter(String str, char letter) {
		str = str.toLowerCase();
		letter = Character.toLowerCase(letter);
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			char currentLetter = str.charAt(i);
			if (currentLetter == letter)
				count++;
		}

		return count;
	}

	public double[] getCharacterFrequencyWeights() {

		double[] characterFrequencyWeights = new double[26];

		characterFrequencyWeights[0] = 6.51;
		characterFrequencyWeights[1] = 1.89;
		characterFrequencyWeights[2] = 3.06;
		characterFrequencyWeights[3] = 5.08;
		characterFrequencyWeights[4] = 17.40;
		characterFrequencyWeights[5] = 1.66;
		characterFrequencyWeights[6] = 3.01;
		characterFrequencyWeights[7] = 4.76;
		characterFrequencyWeights[8] = 7.55;
		characterFrequencyWeights[9] = 0.27;
		characterFrequencyWeights[10] = 1.21;
		characterFrequencyWeights[11] = 3.44;
		characterFrequencyWeights[12] = 2.53;
		characterFrequencyWeights[13] = 9.78;
		characterFrequencyWeights[14] = 2.51;
		characterFrequencyWeights[15] = 0.79;
		characterFrequencyWeights[16] = 0.02;
		characterFrequencyWeights[17] = 7.00;
		characterFrequencyWeights[18] = 7.27;
		characterFrequencyWeights[19] = 6.15;
		characterFrequencyWeights[20] = 4.35;
		characterFrequencyWeights[21] = 0.67;
		characterFrequencyWeights[22] = 1.89;
		characterFrequencyWeights[23] = 0.03;
		characterFrequencyWeights[24] = 0.04;
		characterFrequencyWeights[25] = 1.13;

		return characterFrequencyWeights;

	}
}