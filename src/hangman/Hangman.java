package hangman;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Hangman extends JFrame {
    private JPanel contentPane;
    private JLabel lblCategory;
    private JLabel lblWord;
    private JLabel lblCurrentScore;
    private JLabel lblHighestScore;
    private JTextArea textAreaHangman;
    private JButton[] letterButtons;

    private String secretWord;
    private String selectedCategory;
    private int wrongGuesses;
    private int currentScore;
    private int highestScore;
    private String username; // Field to store the username
    private connect_db connectDb; // Add an instance of ConnectDB

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                String loggedInUsername = getLoggedInUsername(); // Implement this method to get the current username
                Hangman frame = new Hangman(loggedInUsername); // Pass the username
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static String getLoggedInUsername() {
    	String username = null;
        String query = "SELECT username FROM account WHERE pid = (SELECT MAX(pid) FROM account)";

        try (Connection conn = connect_db.getConnection();
        		PreparedStatement statement =conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                username = resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    
	}

	
    public Hangman(String category, int pid)
    {
    	
    }
	// Constructor to accept username
    public Hangman(String username) {
        this.username = username; // Store the username
        connectDb = new connect_db(); // Initialize the database connection
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 838, 568);
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

        textAreaHangman = new JTextArea();
        textAreaHangman.setBackground(new Color(204, 204, 204));
        textAreaHangman.setFont(new Font("Monospaced", Font.PLAIN, 36));
        textAreaHangman.setEditable(false);
        textAreaHangman.setBounds(21, 80, 203, 385);
        contentPane.add(textAreaHangman);

        // Initialize letter buttons for guessing
        letterButtons = new JButton[26];
        char[] letters = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        int x = 10, y = 219;
        for (int i = 0; i < letters.length; i++) {
            JButton btn = new JButton(String.valueOf(letters[i]));
            btn.setBounds(x, y, 47, 39);
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

    private void initializeGame() {
        wrongGuesses = 0;
        currentScore = 0;
        highestScore = getHighestScore(); // Get the highest score for the user
        lblHighestScore.setText("Highest Score: " + highestScore);
        secretWord = getRandomWord();
        selectedCategory = getCategory(secretWord);
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
            String[] categories = {"country", "sport", "food", "fruit", "animal", "job", "electronic", "vegetable", "cloth", "color"};
            String randomCategory = categories[(int) (Math.random() * categories.length)];

            String query = "SELECT data FROM " + randomCategory + " ORDER BY RAND() LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                word = rs.getString("data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return word != null ? word.toUpperCase() : "";
    }

    private String getCategory(String word) {
        String category = null;

        try (Connection conn = connect_db.getConnection()) {
            String[] categories = {"country", "sport", "food", "fruit", "animal", "job", "electronic", "vegetable", "cloth", "color"};

            for (String cat : categories) {
                String query = "SELECT data FROM " + cat + " WHERE data = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, word);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    category = cat; // Found the category
                    break; // Exit the loop if the category is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category != null ? category : "Unknown"; // Return the found category or "Unknown"
    }

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
                if (currentScore > highestScore) {
                    highestScore = currentScore;
                    lblHighestScore.setText("Highest Score: " + highestScore);
                }
                JOptionPane.showMessageDialog(this, "🥳🥳🥳Congratulations! You've guessed the word: " + secretWord);
                saveScoreToDatabase(currentScore);
                //initializeGame();
                wrongGuesses=0;
                highestScore = getHighestScore(); // Get the highest score for the user
                lblHighestScore.setText("Highest Score: " + highestScore);
                secretWord = getRandomWord();
                selectedCategory = getCategory(secretWord);
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
                JOptionPane.showMessageDialog(this, "😭😭😭Game Over! The word was: " + secretWord);
                initializeGame();
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
   

    private void saveScoreToDatabase(int score) {
        try (Connection conn = connect_db.getConnection()) {
            // First, get the latest pid for the given username
            String getPidQuery = "SELECT pid FROM account WHERE username = ? ORDER BY pid DESC LIMIT 1";
            try (PreparedStatement getPidStmt = conn.prepareStatement(getPidQuery)) {
                getPidStmt.setString(1, username.trim()); // Ensure the username is trimmed

                ResultSet rs = getPidStmt.executeQuery();
                if (rs.next()) {
                    int latestPid = rs.getInt("pid"); // Retrieve the latest pid

                    // Now update the score for the latest pid
                    String updateScoreQuery = "UPDATE account SET score = ? WHERE pid = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateScoreQuery)) {
                        updateStmt.setInt(1, score);
                        updateStmt.setInt(2, latestPid);

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated == 0) {
                            JOptionPane.showMessageDialog(this, "No rows were updated. Please check the username.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Score saved successfully.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No records found for the username.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save score to the database.");
        }
    }

    

    private int getCurrentScore() {
        int score = 0;
        try (Connection conn = connect_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT score FROM account WHERE username = ?")) {
            
            stmt.setString(1, username.trim());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                score = rs.getInt("score");
                System.out.println("Current score for " + username + ": " + score);
            } else {
                System.out.println("Username not found: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }


}
