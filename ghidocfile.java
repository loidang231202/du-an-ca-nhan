import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ghidocfile {
    private JFrame frame;
    private JTextField InputField;
    private JTextField filePathField;
    private JTextArea TenFileArea;
    private String filePath;

    public ghidocfile() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Number Array App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);

        InputField = new JTextField(20);
        JButton addButton = new JButton("Add Number");
        filePathField = new JTextField(20);
        // filePathField.setBounds(120,80,60,20);
        JButton browseButton = new JButton("Browse");
        JButton saveButton = new JButton("Ghi File");
        JButton loadButton = new JButton("Đọc File");
        TenFileArea = new JTextArea(5, 40);
        TenFileArea.setEditable(false);

        // JPanel panel = new JPanel();
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TenFileArea.append(InputField.getText() + "\n");
                InputField.setText("");
            }
        });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                    filePathField.setText(filePath);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(TenFileArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    TenFileArea.setText("");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        TenFileArea.append(line + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(InputField);
        panel.add(addButton);
        panel.add(filePathField);
        panel.add(browseButton);
        panel.add(saveButton);
        panel.add(loadButton);
        frame.getContentPane().add(panel, "North");
        frame.getContentPane().add(new JScrollPane(TenFileArea), "Center");

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ghidocfile();
            }
        });
    }
}
