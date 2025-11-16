import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CHUONG03 {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("CHUONG03");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField stringTextField = new JTextField();
        stringTextField.setPreferredSize(new Dimension(200, 30));
        JTextField filePathTextField = new JTextField();
        filePathTextField.setPreferredSize(new Dimension(200, 30));  
        JButton writeButton = new JButton("Ghi File");
        writeButton.setPreferredSize(new Dimension(100, 30));
        writeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = stringTextField.getText();
                String filePath = filePathTextField.getText();
                String fileName = filePath + "\\TestABC.txt";

                try (FileOutputStream fo = new FileOutputStream(fileName)) {
                    fo.write(s.getBytes());
                    JOptionPane.showMessageDialog(frame, "Đã ghi dữ liệu vào file.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Lỗi khi ghi file: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton readButton = new JButton("Đọc File");
        readButton.setPreferredSize(new Dimension(100, 30));  
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathTextField.getText();
                String fileName = filePath + "\\TestABC.txt";

                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    StringBuilder data = new StringBuilder();
                    String st;
                    while ((st = br.readLine()) != null) {
                        data.append(st).append("\n");
                    }

                    JOptionPane.showMessageDialog(frame, "Dữ liệu đọc được:\n" + data.toString());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "Không tìm thấy file: " + fileName, "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Lỗi khi đọc file: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton browseButton = new JButton("Browse");
        browseButton.setPreferredSize(new Dimension(80, 30)); 
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 3, 5, 3); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nhập xâu:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(stringTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Đường dẫn file:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(filePathTextField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(browseButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(writeButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(readButton, gbc);
        frame.getContentPane().add(panel);
        frame.setSize(500, 300); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
