package hangman;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

public class Hangman extends JFrame implements MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel lblCategory;
    private JLabel lblWord;
    private JLabel lblCurrentScore;
    private JLabel lblHighestScore;
    private JTextArea textAreaHangman;
    private JButton[] letterButtons;
    private static Statement stm;
	private static ResultSet rs;
	private static connect_db cb;
	private static Connection cn;

    private String secretWord;
    private String selectedCategory;
    private int wrongGuesses;
    private int currentScore;
    private int highestScore;
    private String username; // Field to store the username
    private int score;
    private int highest_score;
    private connect_db connectDb; // Add an instance of ConnectDB
    private String category;
    private JButton btn_back,btn_exit;
    private int pid;

   

	
    public Hangman(String category, int pid)
    {
//    	this.username = username; // Store the username
    	try 
		{
			try 
			{
				cb = new connect_db();
				cn = cb.getConnection();
				stm = cn.createStatement();
				rs = stm.executeQuery("select username,score from account where pid = "+pid);
				while(rs.next())
				{
					username = rs.getString(1);
					currentScore = rs.getInt(2);
				}
				
				rs.close();
				rs = stm.executeQuery("select max(score) from account group by username having username ='"+username+"'");
				while(rs.next())
				{
					highest_score = rs.getInt(1);
				}
				rs.close();
				
				
				
				
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
			}
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
    	this.category= category;
    	this.pid = pid;
//        connectDb = new connect_db(); // Initialize the database connection
    	setTitle("Hangman Game");
    	setIconImage(new ImageIcon(Hangman.class.getResource("logo.png")).getImage());
    	setBounds(100, 100, 838, 568);
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 204, 204));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 204, 204));
        panel.setBounds(239, 80, 575, 385);
        contentPane.add(panel);
        panel.setLayout(null);

        lblCategory = new JLabel("Category:");
        lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategory.setBounds(0, 0, 552, 39);
        panel.add(lblCategory);
        lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));

        lblWord = new JLabel();
        lblWord.setHorizontalAlignment(SwingConstants.CENTER);
        lblWord.setBounds(10, 87, 552, 39);
        panel.add(lblWord);
        lblWord.setFont(new Font("Tahoma", Font.PLAIN, 25));

        lblCurrentScore = new JLabel("Current Score: 0");
        lblCurrentScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCurrentScore.setBounds(661, 10, 153, 26);
        contentPane.add(lblCurrentScore);

        lblHighestScore = new JLabel("Highest Score: 0");
        lblHighestScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHighestScore.setBounds(10, 10, 153, 26);
        contentPane.add(lblHighestScore);
        
        btn_back = new JButton("Back");
        btn_back.setFocusable(false);
        btn_back.setForeground(new Color(64, 0, 64));
        btn_back.setBackground(new Color(130, 78, 116));
        btn_back.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
        btn_back.setBounds(21, 440, 100, 30);
        btn_back.addMouseListener(this);
        contentPane.add(btn_back);
        
        btn_exit = new JButton("Exit");
        btn_exit.setFocusable(false);
        btn_exit.setForeground(new Color(64, 0, 64));
        btn_exit.setBackground(new Color(130, 78, 116));
        btn_exit.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
        btn_exit.setBounds(140, 440, 100, 30);
        btn_exit.addMouseListener(this);
        contentPane.add(btn_exit);
        
     
        textAreaHangman = new JTextArea();
        textAreaHangman.setBackground(new Color(204, 204, 204));
        textAreaHangman.setFont(new Font("Monospaced", Font.PLAIN, 36));
        textAreaHangman.setEditable(false);
        textAreaHangman.setFocusable(false);
        textAreaHangman.setBounds(21, 80, 203, 340);
        contentPane.add(textAreaHangman);

        // Initialize letter buttons for guessing
        letterButtons = new JButton[26];
        char[] letters = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        int x = 10, y = 219;
        for (int i = 0; i < letters.length; i++) {
            JButton btn = new JButton(String.valueOf(letters[i]));
            btn.setBounds(x, y, 47, 39);
            btn.setFocusable(false);
            btn.setFont(new Font("Tahoma", Font.BOLD, 12));
            btn.addActionListener(e -> handleGuess(btn.getText().charAt(0), btn));
            panel.add(btn);
            letterButtons[i] = btn;
            x += 50;
            if (i == 9 || i == 18) {
                x = (i == 9) ? 35 : 85;
                y += 50;
            }
        }

        initializeGame();
    }
	// Constructor to accept username


    private void initializeGame() {
        wrongGuesses = 0;
        currentScore = getCurrentScore();
        highestScore = getHighestScore(); // Get the highest score for the user
        lblHighestScore.setText("Highest Score: " + highestScore);
        secretWord = getRandomWord();
        selectedCategory = category;
        lblCategory.setText("Category: " + selectedCategory);
        lblWord.setText("_ ".repeat(secretWord.length()));
        lblCurrentScore.setText("Current Score: " + currentScore);
        drawHangman();

        for (JButton button : letterButtons) {
            button.setEnabled(true);
        }
    }

    private String getRandomWord() {
        String word = null;

        try (Connection conn = connect_db.getConnection()) {
            

            String query = "SELECT data FROM " + category + " ORDER BY RAND() LIMIT 1";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                word = rs.getString("data");
            }
            System.out.println(word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return word != null ? word.toUpperCase() : "";
    }

//    private String getCategory(String word) {
//        String category = null;
//
//        try (Connection conn = connect_db.getConnection()) {
//            String[] categories = {"country", "sport", "food", "fruit", "animal", "job", "electronic", "vegetable", "cloth", "color"};
//
//            for (String cat : categories) {
//                String query = "SELECT data FROM " + cat + " WHERE data = ?";
//                PreparedStatement pstmt = conn.prepareStatement(query);
//                pstmt.setString(1, word);
//                ResultSet rs = pstmt.executeQuery();
//
//                if (rs.next()) {
//                    category = cat; // Found the category
//                    break; // Exit the loop if the category is found
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return category != null ? category : "Unknown"; // Return the found category or "Unknown"
//    }

    private int getHighestScore() {
        try (Connection conn = connect_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT MAX(score) FROM account WHERE username = ?")) {
            stmt.setString(1, username);  // Use the stored username
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void handleGuess(char guessedLetter, JButton button) {
        button.setEnabled(false);
        if (secretWord.contains(String.valueOf(guessedLetter))) {
            updateDisplayedWord(guessedLetter);
            if (!lblWord.getText().contains("_")) {
                currentScore += 10;
                lblCurrentScore.setText("Current Score: " + currentScore);
                
                JOptionPane.showMessageDialog(this, "😍😍😍 Congratulations! You've guessed the word: " + secretWord);
                saveScoreToDatabase(currentScore);
                //initializeGame();
                wrongGuesses=0;
                highestScore = getHighestScore(); // Get the highest score for the user
                lblHighestScore.setText("Highest Score: " + highestScore);
                secretWord = getRandomWord();
                selectedCategory = category;
                lblCategory.setText("Category: " + selectedCategory);
                lblWord.setText("_ ".repeat(secretWord.length()));
                lblCurrentScore.setText("Current Score: " + currentScore);
                drawHangman();
                for (JButton button1 : letterButtons) {
                    button1.setEnabled(true);
                }
            }
        } else {
            wrongGuesses++;
            drawHangman();
            if (wrongGuesses >= 6) {
                JOptionPane.showMessageDialog(this, "😭😭😭 Game Over! The word was: " + secretWord);
                JOptionPane.showMessageDialog(this, "Your score is "+currentScore+". Try Again...");
                cb = new connect_db();
                cn = cb.getConnection();
                try {
					PreparedStatement pre = cn.prepareStatement("insert into account(username) values(?)");
					pre.setString(1, username);
					pre.execute();
					// select latest created account
					pre= cn.prepareStatement("select pid from account where username = ? order by pid desc limit 1;");
					pre.setString(1, username);
					ResultSet rs = pre.executeQuery();
					if(rs.next())
					{
						pid = rs.getInt(1);
					}
					this.dispose();
					Choose_Category_Frame cf = new Choose_Category_Frame(pid);
					cf.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }

    private void updateDisplayedWord(char guessedLetter) {
        StringBuilder currentDisplay = new StringBuilder(lblWord.getText());
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guessedLetter) {
                currentDisplay.setCharAt(i * 2, guessedLetter); // Update with the guessed letter
            }
        }
        lblWord.setText(currentDisplay.toString());
    }

    private void drawHangman() {
        String hangman = "";
        switch (wrongGuesses) {
            case 0: hangman = "  +---+\n  |   |\n      |\n      |\n      |\n      |\n========="; break;
            case 1: hangman = "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n========="; break;
            case 2: hangman = "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n========="; break;
            case 3: hangman = "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n========="; break;
            case 4: hangman = "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n========="; break;
            case 5: hangman = "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n========="; break;
            case 6: hangman = "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="; break;
        }
        textAreaHangman.setText(hangman);
    }
   

    private void saveScoreToDatabase(int currentScore) {

            // First, get the latest pid for the given username
            try{
                

                    // Now update the score for the latest pid
                    String updateScoreQuery = "UPDATE account SET score = ? WHERE pid = ?";
                    Connection conn = connect_db.getConnection();
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateScoreQuery)) 
                    {
                        updateStmt.setInt(1, currentScore);
                        updateStmt.setInt(2, pid);

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated == 0) {
                            JOptionPane.showMessageDialog(this, "No rows were updated. Please check the username.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Score saved successfully.");
                        }
                    }
               
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save score to the database.");
        }
        
    }

    

    private int getCurrentScore() {
        
        try (Connection conn = connect_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT score FROM account WHERE pid=?")) {
            
            stmt.setInt(1, pid);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	System.out.println(currentScore);
                currentScore = rs.getInt("score");
                System.out.println("Current score for " + username + ": " + currentScore);
            } else {
                System.out.println("Username not found: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentScore;
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_back)
		{
			if(wrongGuesses>=1)
			{
				JOptionPane.showMessageDialog(this, "<html>You already wrong one or more guesses in this round.<br>"
						+ "Try again in next round.");
				
			}
			else
			{
				this.dispose();
				System.out.println(pid);
				Choose_Category_Frame cf = new Choose_Category_Frame(pid);
				cf.setVisible(true);
			}
		}
		if(e.getSource()==btn_exit)
		{
			System.exit(0);
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
