package hangman;
 
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
 
public class loginform extends JFrame implements ActionListener, MouseListener {
 
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernametext;
    private JLabel lblaccount;
    static connect_db db;
    static Connection con;
    private PreparedStatement pst;
    private JButton startbutton;
    private JLabel lblValidationMessage;
    int pid;
    private static loginform frame;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new loginform();
                    frame.setVisible(true);
 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public loginform() 
    {
    	db = new connect_db();
        con = connect_db.getConnection();
        pack();
        setBounds(100, 100, 649, 369); 
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        setBackground(new Color(250, 235, 215));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 235, 215));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 
        setContentPane(contentPane);
        contentPane.setLayout(null);
 
        JLabel heading = new JLabel("Hangman Game");
        heading.setForeground(new Color(72, 209, 204));
        heading.setFont(new Font("Chiller", Font.BOLD, 41));
        heading.setBounds(169, 11, 208, 53);
        contentPane.add(heading);
 
        JLabel blbusername = new JLabel("Enter Your Name");
        blbusername.setFont(new Font("Chaparral Pro Light", Font.BOLD, 16));
        blbusername.setBounds(37, 82, 134, 28);
        contentPane.add(blbusername);
 
        usernametext = new JTextField();
        usernametext.setBackground(new Color(253, 245, 230));
        usernametext.setForeground(SystemColor.activeCaptionText);
        usernametext.setFont(new Font("Chaparral Pro Light", Font.BOLD, 16));
        usernametext.setBounds(190, 82, 174, 28);
        contentPane.add(usernametext);
        usernametext.setColumns(10);
 
        lblValidationMessage = new JLabel("");
        lblValidationMessage.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        lblValidationMessage.setBounds(190, 120, 200, 25);
        contentPane.add(lblValidationMessage);
 
        lblaccount = new JLabel("Don't you have account?");
        lblaccount.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        lblaccount.setBounds(122, 150, 185, 25);
        lblaccount.addMouseListener(this);
        contentPane.add(lblaccount);
 
        startbutton = new JButton("Start");
        startbutton.setFont(new Font("Tekton Pro", Font.BOLD, 17));
        startbutton.setBackground(new Color(244, 164, 96));
        startbutton.setBounds(148, 180, 113, 36);
        startbutton.addActionListener(this);
        contentPane.add(startbutton);
 
        JLabel pic = new JLabel("");
        ImageIcon icon = new ImageIcon(loginform.class.getResource("./logo.png"));
        Image iconImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaleImage = new ImageIcon(iconImage);
        pic.setIcon(scaleImage);
        pic.setBounds(387, 11, 236, 256);
        contentPane.add(pic);
 
        // Add DocumentListener to usernametext
        usernametext.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkUsername();
            }
 
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkUsername();
            }
 
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkUsername();
            }
        });
    }
 
    private void checkUsername() {
        String username = usernametext.getText().trim();
 
        if (username.isEmpty()) {
            lblValidationMessage.setText("Username cannot be empty");
            return;
        }
 
        try {
            pst = con.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
 
            if (count == 0) {
                lblValidationMessage.setText("Username not found");
                lblValidationMessage.setForeground(Color.RED);
            } else {
                lblValidationMessage.setText("Username found");
                lblValidationMessage.setForeground(Color.GREEN);
            }
 
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	String loggedInUsername = getLoggedInUsername(); 
        if (e.getSource() == startbutton) {
            String username = usernametext.getText().trim();
 
            if (username.isEmpty()) {
                lblValidationMessage.setText("Username cannot be empty");
                lblValidationMessage.setForeground(Color.RED);
                return;
            }
 
            if (lblValidationMessage.getText().equals("Username not found")) {
                JOptionPane.showMessageDialog(contentPane, "Username do not exist!", "Cannot Login", JOptionPane.ERROR_MESSAGE);
                return;
            }
 
            try {
                pst = con.prepareStatement("insert into account(username) values(?)");
                pst.setString(1, username);
                pst.execute();
                //pst.close();
                JOptionPane.showMessageDialog(contentPane, "Login successful");
                frame.setVisible(false);
                pst = con.prepareStatement("select pid from account order by pid desc limit 1");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    pid = rs.getInt(1);
                }
                this.dispose();
                Choose_Category_Frame f = new Choose_Category_Frame(pid);
                f.setVisible(true);
//                Hangman H=new Hangman(loggedInUsername);
//                H.setVisible(true);
 
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(contentPane, "Username do not exist!", "Cannot Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
    private String getLoggedInUsername() {
        return usernametext.getText().trim();
    }

	@Override
    public void mouseClicked(MouseEvent e) {}
 
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == lblaccount) {
            frame.setVisible(false);
            Register_Form f = new Register_Form();
            f.setVisible(true);
        }
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {}
 
    @Override
    public void mouseEntered(MouseEvent e) {}
 
    @Override
    public void mouseExited(MouseEvent e) {}
}