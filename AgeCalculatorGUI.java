import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AgeCalculatorGUI extends JFrame{
    private JTextField inputField;
    private JLabel resultLabel;

        public AgeCalculatorGUI() {

        setTitle("Age Calculator (GUI)");
        setSize(300, 300);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputField = new JTextField();
        inputField.setBorder(BorderFactory.createTitledBorder("Masukkan tanggal lahir (dd-mm-yyyy)"));

        JButton calculateBtn = new JButton("Hitung Umur");
        resultLabel = new JLabel("Umur kamu: -", SwingConstants.CENTER);

        calculateBtn.addActionListener(e -> {
            String tanggal = inputField.getText();
            String[] parts = tanggal.split("-");

            if (parts.length != 3) {
                JOptionPane.showMessageDialog(this, "Format salah! Contoh: 25-12-2000");
                return;
            }

            try {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if (!AgeCalculator.isValidDay(day, month, year)) {
                    JOptionPane.showMessageDialog(this, "Tanggal tidak valid!");
                    return;
                }

                BirthDate bd = new BirthDate(day, month, year);
                int age = AgeCalculator.calculateAge(bd);

                resultLabel.setText("Umur kamu: " + age + " tahun");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input harus angka semua!");
            }
        });

        add(inputField);
        add(calculateBtn);
        add(resultLabel);

        setVisible(true);
    }

}
