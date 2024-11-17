package hangman;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
 
public class Register_Form extends JFrame implements ActionListener, MouseListener {
 
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUserName;
    private JButton btnSignUp;
    private ImageIcon icon, scaleImage;
    private Image iconImage;
    static connect_db db;
    static Connection con;
    private PreparedStatement pst;
    private JLabel lblLogin;
    private JLabel lblValidationMessage;
 
    /**
     * Create the frame.
     */
    public Register_Form() {
        try {
            db = new connect_db();
            con = connect_db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Register Form");
        setBounds(100, 100, 684, 416);
        setIconImage(new ImageIcon(Register_Form.class.getResource("logo.png")).getImage());
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
 
        setContentPane(contentPane);
        contentPane.setLayout(null);
 
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 342, 384);
        contentPane.add(panel);
 
        JLabel lblHangman = new JLabel("");
        lblHangman.setBackground(Color.WHITE);
        lblHangman.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon(Register_Form.class.getResource("hangman.jpg"));
        iconImage = icon.getImage().getScaledInstance(342, 384, Image.SCALE_SMOOTH);
        scaleImage = new ImageIcon(iconImage);
        lblHangman.setIcon(scaleImage);
        panel.add(lblHangman);
 
        JLabel lblSignUp = new JLabel("Sign Up");
        lblSignUp.setForeground(new Color(165, 121, 75));
        lblSignUp.setBackground(Color.ORANGE);
        lblSignUp.setFont(new Font("Cooper Black", Font.BOLD, 32));
        lblSignUp.setBounds(456, 24, 145, 35);
        contentPane.add(lblSignUp);
 
        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setForeground(new Color(158, 66, 81));
        lblUserName.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
        lblUserName.setBounds(385, 109, 86, 29);
        contentPane.add(lblUserName);
 
        btnSignUp = new JButton("Sign Up");
        btnSignUp.setFocusable(false);
        btnSignUp.setForeground(new Color(64, 0, 64));
        btnSignUp.setBackground(new Color(130, 78, 116));
        btnSignUp.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
        btnSignUp.setBounds(441, 255, 145, 29);
        contentPane.add(btnSignUp);
        btnSignUp.addActionListener(this);
 
        lblLogin = new JLabel("Have an account? Click here!");
        lblLogin.setForeground(new Color(128, 128, 192));
        lblLogin.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
        lblLogin.setBounds(399, 303, 234, 29);
        lblLogin.addMouseListener(this);
        contentPane.add(lblLogin);
 
        txtUserName = new JTextField();
        txtUserName.setForeground(new Color(203, 153, 204));
        txtUserName.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
        txtUserName.setBounds(385, 149, 234, 35);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);
 
        lblValidationMessage = new JLabel("");
        lblValidationMessage.setForeground(Color.RED);
        lblValidationMessage.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblValidationMessage.setBounds(385, 190, 234, 50);
        contentPane.add(lblValidationMessage);
 
        // Add DocumentListener to txtUserName
        txtUserName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateUsername();
            }
 
            @Override
            public void removeUpdate(DocumentEvent e) {
                validateUsername();
            }
 
            @Override
            public void changedUpdate(DocumentEvent e) {
                validateUsername();
            }
        });
    }
 
    private void validateUsername() {
        String username = txtUserName.getText().trim();
 
        if (username.isEmpty()) {
            lblValidationMessage.setText("<html>Username cannot be empty</html>");
            lblValidationMessage.setForeground(Color.RED);
            return;
        }
 
        // Validate username length and pattern
        if (!Pattern.matches("^(?=.*[A-Z])[A-Za-z\\d]{8,16}$", username)) {
            lblValidationMessage.setText("<html>Username must be 8-16 characters long and contain at least 1 capital letter</html>");
            lblValidationMessage.setForeground(Color.RED);
            return;
        }
 
        try {
            // Check if username already exists
            pst = con.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
 
            if (count > 0) {
                lblValidationMessage.setText("Username is already taken");
                lblValidationMessage.setForeground(Color.RED);
            } else {
                lblValidationMessage.setText("Username is available");
                lblValidationMessage.setForeground(Color.GREEN); // Change color to green
            }
 
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUserName.getText().trim();
 
        if (username.isEmpty()) {
            lblValidationMessage.setText("Username cannot be empty");
            lblValidationMessage.setForeground(Color.RED);
            return;
        }
 
        if (!Pattern.matches("^(?=.*[A-Z])[A-Za-z\\d]{8,16}$", username)) {
            lblValidationMessage.setText("Username must be 8-16 characters long and contain at least 1 capital letter");
            lblValidationMessage.setForeground(Color.RED);
            return;
        }
 
        if (!lblValidationMessage.getText().equals("Username is available")) {
            JOptionPane.showMessageDialog(null, lblValidationMessage.getText());
            return;
        }
 
        try {
            // Insert new username
            pst = con.prepareStatement("insert into user(username) values (?)");
            pst.setString(1, username);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Inserted");
            lblValidationMessage.setText(""); // Clear the validation message
            this.dispose();
            loginform login=new loginform();
            login.setVisible(true);
 
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lblLogin) {
            this.dispose();
            loginform f = new loginform();
            f.setVisible(true);
        }
    }
 
    @Override
    public void mousePressed(MouseEvent e) {}
 
    @Override
    public void mouseReleased(MouseEvent e) {}
 
    @Override
    public void mouseEntered(MouseEvent e) {}
 
    @Override
    public void mouseExited(MouseEvent e) {}

	
}