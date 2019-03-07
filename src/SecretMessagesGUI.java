import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;

public class SecretMessagesGUI extends JFrame {
    private JTextField txtKey;
    private JTextArea txtIn;
    private JTextArea txtOut;
    private JSlider slider;
    private JScrollPane scrollPane;

    public String encode(String message, int keyVal) {
        String output = "";
        char key = (char) keyVal;
        for (int x = 0; x < message.length(); x++) {
            char input = message.charAt(x);
            if (input >= 'A' && input <= 'Z') {
                input += key;
                if (input > 'Z')
                    input -= 26;
                if (input < 'A')
                    input += 26;
            } else if (input >= 'a' && input <= 'z') {
                input += key;
                if (input > 'z')
                    input -= 26;
                if (input < 'a')
                    input += 26;
            } else if (input >= '0' && input <= '9') {
                input += (keyVal % 10);
                if (input > '9')
                    input -= 10;
                if (input < '0')
                    input += 10;
            }
            output += input;
        }

        return output;
    }


    public SecretMessagesGUI() {
        getContentPane().setBackground(new Color(0, 96, 208));
        setTitle("Sekretna wiadomosc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 13, 558, 115);
        getContentPane().add(scrollPane);

        txtIn = new JTextArea();
        scrollPane.setViewportView(txtIn);
        txtIn.setWrapStyleWord(true);
        txtIn.setLineWrap(true);
        txtIn.setBackground(Color.WHITE);
        txtIn.setForeground(Color.BLACK);
        txtIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));

        txtOut = new JTextArea();
        txtOut.setWrapStyleWord(true);
        txtOut.setLineWrap(true);
        txtOut.setBackground(SystemColor.info);
        txtOut.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtOut.setBounds(12, 225, 558, 115);
        getContentPane().add(txtOut);

        txtKey = new JTextField();
        txtKey.setHorizontalAlignment(SwingConstants.CENTER);
        txtKey.setBounds(300, 158, 74, 35);
        getContentPane().add(txtKey);
        txtKey.setColumns(10);

        JLabel lblKey = new JLabel("Klucz:");
        lblKey.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
        lblKey.setBounds(200, 166, 82, 16);
        getContentPane().add(lblKey);

        JButton btnNewButton = new JButton("Szyfruj/Deszyfruj");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBackground(Color.ORANGE);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String message = txtIn.getText();
                    int key = Integer.parseInt(txtKey.getText());
                    String output = encode(message, key);
                    txtOut.setText(output);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Prosze podac liczbe w oknie klucz.");
                    txtKey.requestFocus();
                    txtKey.selectAll();
                }
            }
        });
        btnNewButton.setBounds(400, 158, 131, 35);
        getContentPane().add(btnNewButton);

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                txtKey.setText("" + slider.getValue());
                String message = txtIn.getText();
                int key = slider.getValue();
                String output = encode(message, key);
                txtOut.setText(output);
            }
        });
        slider.setFont(new Font("Tahoma", Font.BOLD, 13));
        slider.setValue(13);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(13);
        slider.setMinorTickSpacing(1);
        slider.setMinimum(-26);
        slider.setMaximum(26);
        slider.setBackground(new Color(255, 250, 205));
        slider.setBounds(50, 145, 160, 60);
        getContentPane().add(slider);
    }

    public static void main(String[] args) {
        SecretMessagesGUI theApp = new SecretMessagesGUI();
        theApp.setSize(new java.awt.Dimension(600, 400));
        theApp.setVisible(true);
    }
}
