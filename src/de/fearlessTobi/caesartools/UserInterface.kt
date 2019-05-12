package de.fearlessTobi.caesartools

import de.fearlessTobi.caesartools.ProgramLogic.decryptText
import de.fearlessTobi.caesartools.ProgramLogic.encryptText
import de.fearlessTobi.caesartools.ProgramLogic.goForward
import java.awt.EventQueue
import javax.swing.*
import javax.swing.border.EmptyBorder

class UserInterface private constructor() : JFrame() {

    private val contentPane: JPanel

    private var btnBack: JButton = JButton("<<")
    private var btnForward: JButton = JButton(">>")

    private var lblEnterEncryptedText: JLabel = JLabel("Encrypted text")
    private var lblEnterTextToEncrypt: JLabel = JLabel("Non-Encrypted text")
    private var lblAverageDifference: JLabel = JLabel("Average difference:")
    private var lblShiftBy: JLabel = JLabel("Shift by")
    private var lblCharacters: JLabel = JLabel("character(s)")

    private var taEncryptedTextInput: JTextArea = JTextArea()
    private var taEncryptedTextOutput: JTextArea = JTextArea()
    private var taDecryptedTextInput: JTextArea = JTextArea()
    private var taDecryptedTextOutput: JTextArea = JTextArea()

    private var tfAverageDifference: JTextField = JTextField()
    private var tfShiftBy: JTextField = JTextField()

    private var lblDecryptedText: JLabel = JLabel("Decrypted text")
    private var lblEncryptedText: JLabel = JLabel("Encrypted text")

    private var btnDecrypt: JButton = JButton("Decrypt")
    private var btnToDecryptorSmall: JButton = JButton("Go to Decryptor")
    private var btnToDecryptor: JButton = JButton("Go to Decryptor")

    private var btnEncrypt: JButton = JButton("Encrypt")
    private var btnToEncryptorSmall: JButton = JButton("Go to Encryptor")
    private var btnToEncryptor: JButton = JButton("Go to Encryptor")

    init {
        isResizable = false
        title = "Caesar Tools 2.0"
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setBounds(100, 100, 450, 330)
        contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        setContentPane(contentPane)
        contentPane.layout = null

        createContentPaneObjects()
        addActionListeners()
    }

    private fun createContentPaneObjects() {
        btnBack.setBounds(10, 258, 58, 23)
        btnBack.isEnabled = true
        btnBack.isVisible = false
        contentPane.add(btnBack)

        btnForward.setBounds(368, 258, 58, 23)
        btnForward.isEnabled = true
        btnForward.isVisible = false
        contentPane.add(btnForward)

        lblEnterEncryptedText.setBounds(171, 5, 97, 14)
        lblEnterEncryptedText.isVisible = false
        contentPane.add(lblEnterEncryptedText)

        lblEnterTextToEncrypt.setBounds(171, 5, 97, 14)
        lblEnterTextToEncrypt.isVisible = false
        contentPane.add(lblEnterTextToEncrypt)

        lblAverageDifference.setBounds(98, 234, 97, 14)
        lblAverageDifference.isVisible = false
        contentPane.add(lblAverageDifference)

        lblShiftBy.setBounds(136, 234, 37, 14)
        lblShiftBy.isVisible = false
        contentPane.add(lblShiftBy)

        lblCharacters.setBounds(267, 234, 64, 14)
        lblCharacters.isVisible = false
        contentPane.add(lblCharacters)

        taEncryptedTextInput.setBounds(36, 25, 364, 78)
        taEncryptedTextInput.wrapStyleWord = true
        taEncryptedTextInput.columns = 10
        taEncryptedTextInput.lineWrap = true
        taEncryptedTextInput.isVisible = false
        contentPane.add(taEncryptedTextInput)

        taEncryptedTextOutput.setBounds(36, 146, 364, 78)
        taEncryptedTextOutput.wrapStyleWord = true
        taEncryptedTextOutput.isEnabled = true
        taEncryptedTextOutput.isEditable = false
        taEncryptedTextOutput.isVisible = false
        taEncryptedTextOutput.columns = 10
        taEncryptedTextOutput.lineWrap = true
        contentPane.add(taEncryptedTextOutput)

        taDecryptedTextInput.setBounds(36, 25, 364, 78)
        taDecryptedTextInput.wrapStyleWord = true
        taDecryptedTextInput.columns = 10
        taDecryptedTextInput.lineWrap = true
        taDecryptedTextInput.isVisible = false
        contentPane.add(taDecryptedTextInput)

        taDecryptedTextOutput.setBounds(36, 146, 364, 78)
        taDecryptedTextOutput.wrapStyleWord = true
        taDecryptedTextOutput.isEnabled = true
        taDecryptedTextOutput.isEditable = false
        taDecryptedTextOutput.isVisible = false
        taDecryptedTextOutput.columns = 10
        taDecryptedTextOutput.lineWrap = true
        contentPane.add(taDecryptedTextOutput)

        tfAverageDifference.setBounds(248, 231, 86, 20)
        tfAverageDifference.isEditable = false
        tfAverageDifference.columns = 10
        tfAverageDifference.isVisible = false
        contentPane.add(tfAverageDifference)

        tfShiftBy.setBounds(183, 231, 74, 20)
        tfShiftBy.columns = 10
        tfShiftBy.isVisible = false
        contentPane.add(tfShiftBy)

        lblDecryptedText.setBounds(171, 125, 74, 14)
        lblDecryptedText.isVisible = false
        contentPane.add(lblDecryptedText)

        lblEncryptedText.setBounds(171, 125, 74, 14)
        lblEncryptedText.isVisible = false
        contentPane.add(lblEncryptedText)

        btnToDecryptorSmall.setBounds(317, 1, 117, 23)
        btnToDecryptorSmall.isEnabled = true
        btnToDecryptorSmall.isVisible = false
        contentPane.add(btnToDecryptorSmall)

        btnToDecryptor.setBounds(251, 25, 152, 243)
        contentPane.add(btnToDecryptor)

        btnDecrypt.setBounds(171, 258, 97, 23)
        btnDecrypt.isVisible = false
        btnDecrypt.isEnabled = true
        contentPane.add(btnDecrypt)

        btnToEncryptorSmall.setBounds(317, 1, 117, 23)
        btnToEncryptorSmall.isEnabled = true
        btnToEncryptorSmall.isVisible = false
        contentPane.add(btnToEncryptorSmall)

        btnToEncryptor.setBounds(36, 25, 152, 243)
        contentPane.add(btnToEncryptor)

        btnEncrypt.setBounds(171, 258, 97, 23)
        btnEncrypt.isVisible = false
        btnEncrypt.isEnabled = true
        contentPane.add(btnEncrypt)
    }

    private fun addActionListeners() {
        btnBack.addActionListener {
            val forwardedValues = goForward(-1)
            if (forwardedValues != null) {
                tfAverageDifference.text = forwardedValues.leastAverageDifferenceRounded.toString()
                taDecryptedTextOutput.text = forwardedValues.shiftedStringWithLeastAverageDifference
            }
        }

        btnForward.addActionListener {
            val newOutputText = goForward(1)
            if (newOutputText != null) {
                tfAverageDifference.text = newOutputText.leastAverageDifferenceRounded.toString()
                taDecryptedTextOutput.text = newOutputText.shiftedStringWithLeastAverageDifference
            }
        }

        btnDecrypt.addActionListener {
            val encryptedText = taEncryptedTextInput.text

            lblAverageDifference.isVisible = true
            tfAverageDifference.isVisible = true
            val decryptedTextValues = decryptText(encryptedText)
            taDecryptedTextOutput.text = decryptedTextValues.shiftedStringWithLeastAverageDifference
            tfAverageDifference.text = decryptedTextValues.leastAverageDifferenceRounded.toString()

            btnBack.isVisible = !decryptedTextValues.isTotalZero
            btnForward.isVisible = !decryptedTextValues.isTotalZero
        }

        btnToDecryptorSmall.addActionListener { switchWorkMode(true) }
        btnToDecryptor.addActionListener { switchWorkMode(true) }
        btnToEncryptorSmall.addActionListener { switchWorkMode(false) }
        btnToEncryptor.addActionListener { switchWorkMode(false) }

        btnEncrypt.addActionListener {
            val decryptedText = taDecryptedTextInput.text
            val shiftByString = tfShiftBy.text
            val shiftBy: Int
            try {
                shiftBy = Integer.valueOf(shiftByString)
            } catch (ex: Exception) {
                JOptionPane.showMessageDialog(null, "\"$shiftByString\" is no valid number!", "Error",
                        JOptionPane.ERROR_MESSAGE)
                return@addActionListener
            }
            taEncryptedTextOutput.text = encryptText(decryptedText, shiftBy)
        }
    }

    private fun switchWorkMode(decryptor: Boolean) {
        btnDecrypt.isVisible = decryptor
        btnToEncryptorSmall.isVisible = decryptor
        lblEnterEncryptedText.isVisible = decryptor
        lblDecryptedText.isVisible = decryptor
        taEncryptedTextInput.isVisible = decryptor
        taDecryptedTextOutput.isVisible = decryptor
        btnToDecryptor.isVisible = false
        btnToEncryptor.isVisible = false
        btnBack.isVisible = false
        btnForward.isVisible = false
        lblAverageDifference.isVisible = decryptor
        tfAverageDifference.isVisible = decryptor

        btnEncrypt.isVisible = !decryptor
        taDecryptedTextInput.isVisible = !decryptor
        taEncryptedTextOutput.isVisible = !decryptor
        lblEnterTextToEncrypt.isVisible = !decryptor
        btnToDecryptorSmall.isVisible = !decryptor
        lblEncryptedText.isVisible = !decryptor
        lblShiftBy.isVisible = !decryptor
        lblCharacters.isVisible = !decryptor
        tfShiftBy.isVisible = !decryptor

        if (decryptor) {
            taDecryptedTextInput.text = null
            taEncryptedTextOutput.text = null
            tfShiftBy.text = null
        } else {
            taEncryptedTextInput.text = null
            taDecryptedTextOutput.text = null
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel")
            EventQueue.invokeLater {
                val frame = UserInterface()
                frame.isVisible = true
            }
        }
    }

}