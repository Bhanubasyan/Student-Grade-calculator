import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AverageGradeCalculator extends JFrame {
    private JTextField numberOfGradesField;
    private JTextArea gradesArea;
    private JButton calculateButton;
    private JLabel resultLabel;

    public AverageGradeCalculator() {
        // Set up the frame
        setTitle("Average Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        numberOfGradesField = new JTextField(10);
        gradesArea = new JTextArea(10, 30);
        calculateButton = new JButton("Calculate Average");
        resultLabel = new JLabel("Result: ");

        // Set up the layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter number of grades:"));
        panel.add(numberOfGradesField);
        panel.add(new JLabel("Enter each grade (one per line):"));
        panel.add(new JScrollPane(gradesArea));
        panel.add(calculateButton);
        panel.add(resultLabel);
        
        add(panel);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverage();
            }
        });
    }

    private void calculateAverage() {
        try {
            int numberOfGrades = Integer.parseInt(numberOfGradesField.getText());
            String[] gradesInput = gradesArea.getText().split("\\n");
            
            if (gradesInput.length != numberOfGrades) {
                JOptionPane.showMessageDialog(this, "Number of grades entered does not match the specified amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = 0;
            for (String gradeStr : gradesInput) {
                double grade = Double.parseDouble(gradeStr.trim());
                total += grade;
            }

            double average = total / numberOfGrades;
            String emojiExperience = getEmojiExperience(average);

            resultLabel.setText(String.format("Average: %.2f %s", average, emojiExperience));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid grades.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getEmojiExperience(double average) {
        if (average >= 90) {
            return "🎉 Excellent!";
        } else if (average >= 75) {
            return "😊 Good!";
        } else if (average >= 60) {
            return "😐 Average!";
        } else {
            return "😞 Needs Improvement!";
        }
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            AverageGradeCalculator calculator = new AverageGradeCalculator();
            calculator.setVisible(true);
        });
    }
}
