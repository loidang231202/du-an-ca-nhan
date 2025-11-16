
//ĐẶNG VĂN LỢI 2121050659
// Import các thư viện cần thiết
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Định nghĩa lớp Book đại diện cho một cuốn sách trong thư viện
class Book {
    private String title; // Tiêu đề của sách
    private String author; // Tác giả của sách
    private int quantity; // Số lượng sách
    private double price; // Giá của sách

    // Constructor của lớp Book để khởi tạo một cuốn sách với các thông tin đã cho
    public Book(String title, String author, int quantity, double price) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    // Các phương thức getter để truy xuất thông tin của cuốn sách
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

// Lớp chính của chương trình, kế thừa từ lớp JFrame và implement ActionListener
// để xử lý sự kiện
public class QuanLyThuVien extends JFrame implements ActionListener {
    private ArrayList<Book> books = new ArrayList<>(); // Danh sách các cuốn sách
    private JTextField titleField, authorField, quantityField, priceField; // Các ô nhập dữ liệu
    private JTextArea displayArea; // Ô hiển thị danh sách sách

    // Constructor của lớp QuanLyThuVien, khởi tạo giao diện người dùng
    public QuanLyThuVien() {
        setTitle("Quản Lý Thư Viện");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tạo panel chứa các ô nhập dữ liệu và nút "THÊM SÁCH"
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("TÊN :"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("TÁC GIẢ:"));
        authorField = new JTextField();
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("SỐ LƯỢNG:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("GIÁ TIỀN:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        JLabel infoLabel = new JLabel("Đặng Văn Lợi 2121050659");
        inputPanel.add(infoLabel);
        JButton addButton = new JButton("THÊM SÁCH");
        addButton.addActionListener(this); // Đăng ký sự kiện ActionListener cho nút "THÊM SÁCH"
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH); // Thêm panel chứa các ô nhập vào vị trí phía trên của giao diện

        // Tạo JTextArea để hiển thị danh sách sách và đặt nó vào trong JScrollPane
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        // Thêm JScrollPane chứa displayArea vào vị trí giữa của giao diện

    }

    // Phương thức xử lý sự kiện khi người dùng nhấn nút "THÊM SÁCH"
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy thông tin từ các ô nhập dữ liệu
        String title = titleField.getText();
        String author = authorField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());

        // Tạo một đối tượng Book mới và thêm vào danh sách books
        Book newBook = new Book(title, author, quantity, price);
        books.add(newBook);

        // Hiển thị lại danh sách sách sau khi thêm mới
        displayBooks();
    }

    // Phương thức để hiển thị danh sách sách trong displayArea
    private void displayBooks() {
        // Xóa nội dung hiện tại của displayArea
        displayArea.setText("");

        // Duyệt qua danh sách sách và hiển thị thông tin của từng cuốn sách trong
        // displayArea
        for (Book book : books) {
            displayArea.append("TÊN: " + book.getTitle() + ", TÁC GIẢ: " + book.getAuthor() +
                    ", SỐ LƯỢNG: " + book.getQuantity() + ", GIÁ:" + book.getPrice() + "vnd\n");
        }
    }

    // Phương thức main để chạy chương trình
    public static void main(String[] args) {
        // Tạo một đối tượng QuanLyThuVien và hiển thị giao diện
        SwingUtilities.invokeLater(() -> {
            QuanLyThuVien quanLyThuVien = new QuanLyThuVien();
            quanLyThuVien.setVisible(true);
        });
    }
}
