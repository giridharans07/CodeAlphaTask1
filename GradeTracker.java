import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class StudentInfo {
    String studentName;
    int score;
    char grade;

    StudentInfo(String n, int s, char g) {
        studentName = n;
        score = s;
        grade = g;
    }
}

public class GradeTracker extends JFrame implements ActionListener {

    JTextField nameTxt, marksTxt;
    JTextArea output;
    JButton addButton, summaryButton;

    ArrayList<StudentInfo> list = new ArrayList<>();

    GradeTracker() {
        setTitle("Grade Tracker");
        setSize(520, 420);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Name"));
        nameTxt = new JTextField(15);
        add(nameTxt);

        add(new JLabel("Marks"));
        marksTxt = new JTextField(5);
        add(marksTxt);

        addButton = new JButton("Add");
        summaryButton = new JButton("Summary");

        add(addButton);
        add(summaryButton);

        output = new JTextArea(13, 42);
        output.setEditable(false);
        add(new JScrollPane(output));

        addButton.addActionListener(this);
        summaryButton.addActionListener(this);

        setVisible(true);
    }

    char calculateGrade(int m) {
        if (m >= 90) return 'A';
        if (m >= 75) return 'B';
        if (m >= 60) return 'C';
        if (m >= 50) return 'D';
        return 'F';
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            try {
                String name = nameTxt.getText().trim();
                int marks = Integer.parseInt(marksTxt.getText());

                if (name.length() == 0 || marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Invalid input");
                    return;
                }

                char g = calculateGrade(marks);
                list.add(new StudentInfo(name, marks, g));

                output.append(name + " | " + marks + " | Grade " + g + "\n");

                nameTxt.setText("");
                marksTxt.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter numbers only");
            }
        }

        if (e.getSource() == summaryButton) {

            if (list.size() == 0) {
                JOptionPane.showMessageDialog(this, "No data available");
                return;
            }

            int sum = 0;
            int max = list.get(0).score;
            int min = list.get(0).score;

            for (StudentInfo s : list) {
                sum += s.score;
                if (s.score > max) max = s.score;
                if (s.score < min) min = s.score;
            }

            double avg = (double) sum / list.size();

            output.append("\n--- REPORT ---\n");
            output.append("Students : " + list.size() + "\n");
            output.append("Average  : " + avg + "\n");
            output.append("Highest  : " + max + "\n");
            output.append("Lowest   : " + min + "\n");
        }
    }

    public static void main(String[] args) {
        new GradeTracker();
    }
}

 