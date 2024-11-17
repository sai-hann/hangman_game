package hangman;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Choose_Category_Frame extends JFrame implements ActionListener,MouseListener {

	private static final long serialVersionUID = 1L;
	private int pid =0;
	private JPanel contentPane;
	private static Statement stm;
	private static ResultSet rs;
	private static connect_db cb;
	ImageIcon ico,scaled_ico;
	Image img;
	private static Connection cn;
	private static int score=0;
	private static int highest_score=0;
	private static String username;
	private JPanel panel_body_part,panel_food,panel_fruit,panel_vegetable,panel_animal,panel_sport, panel_country,panel_cloth,panel_color,panel_electronic,panel_job;
	private JButton btn_select,btn_exit;
	private String category;
	JPanel category_group_pane;
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	
	public Choose_Category_Frame(int pid) 
	{
		this.pid=pid;
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
					score = rs.getInt(2);
					username = rs.getString(1);
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
	

	
		setBounds(100, 100, 886, 494);
		setLocationRelativeTo(null);
		setTitle("Choose the Category");
		setIconImage(new ImageIcon(Choose_Category_Frame.class.getResource("logo.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_username = new JLabel("Username: "+ username);
		lbl_username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_username.setHorizontalAlignment(SwingConstants.LEADING);
		lbl_username.setBounds(80, 11, 400, 25);
		contentPane.add(lbl_username);
		
		JLabel lbl_highest_score = new JLabel("Highest Score: "+highest_score);
		lbl_highest_score.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_highest_score.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_highest_score.setBounds(632, 11, 174, 25);
		contentPane.add(lbl_highest_score);
		
		JLabel lbl_current_score = new JLabel("Current Score: "+score);
		lbl_current_score.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_current_score.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_current_score.setBounds(425, 11, 186, 25);
		contentPane.add(lbl_current_score);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 47, 726, 318);
		contentPane.add(scrollPane);
		
		category_group_pane = new JPanel();
		category_group_pane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(category_group_pane);
		category_group_pane.setLayout(null);

	
		
		panel_body_part = new JPanel();
		panel_body_part.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_body_part.setBounds(23, 11, 111, 130);
		panel_body_part.setBackground(Color.white);
		category_group_pane.add(panel_body_part);
		panel_body_part.setLayout(null);
		panel_body_part.addMouseListener(this);		
		
		JLabel img_body_part = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("body_part.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_body_part.setIcon(scaled_ico);
		img_body_part.setBounds(15, 11, 86, 90);
		panel_body_part.add(img_body_part);
		
		JLabel lbl_body_part = new JLabel("Body Part");
		lbl_body_part.setLabelFor(img_body_part);
		lbl_body_part.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_body_part.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_body_part.setBounds(0, 105, 111, 20);
		panel_body_part.add(lbl_body_part);
		
		
		panel_animal = new JPanel();
		panel_animal.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_animal.setLayout(null);
		panel_animal.setBounds(157, 11, 111, 130);
		panel_animal.setBackground(Color.white);
		panel_animal.addMouseListener(this);	
		category_group_pane.add(panel_animal);
		
		JLabel img_animal = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("animal.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_animal.setIcon(scaled_ico);
		img_animal.setBounds(15, 11, 86, 90);
		panel_animal.add(img_animal);
		
		JLabel lbl_animal = new JLabel("Animal");
		lbl_animal.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_animal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_animal.setBounds(0, 105, 111, 20);
		panel_animal.add(lbl_animal);
		
		panel_cloth = new JPanel();
		panel_cloth.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_cloth.setLayout(null);
		panel_cloth.setBounds(292, 11, 111, 130);
		panel_cloth.setBackground(Color.white);
		panel_cloth.addMouseListener(this);	
		category_group_pane.add(panel_cloth);
		
		JLabel img_cloth = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("cloth.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_cloth.setIcon(scaled_ico);
		img_cloth.setBounds(15, 11, 86, 90);
		panel_cloth.add(img_cloth);
		
		JLabel lbl_cloth = new JLabel("Cloth");
		lbl_cloth.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cloth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_cloth.setBounds(0, 105, 111, 20);
		panel_cloth.add(lbl_cloth);
		
		panel_color = new JPanel();
		panel_color.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_color.setLayout(null);
		panel_color.setBounds(438, 11, 111, 130);
		panel_color.setBackground(Color.white);
		panel_color.addMouseListener(this);
		category_group_pane.add(panel_color);
		
		JLabel img_color = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("color.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_color.setIcon(scaled_ico);
		img_color.setBounds(15, 11, 86, 90);
		panel_color.add(img_color);
		
		JLabel lbl_color = new JLabel("Color");
		lbl_color.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_color.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_color.setBounds(0, 105, 111, 20);
		panel_color.add(lbl_color);
		
		panel_country = new JPanel();
		panel_country.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_country.setLayout(null);
		panel_country.setBounds(583, 11, 111, 130);
		panel_country.setBackground(Color.white);
		panel_country.addMouseListener(this);
		category_group_pane.add(panel_country);
		
		JLabel img_country = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("country.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_country.setIcon(scaled_ico);
		img_country.setBounds(15, 11, 86, 90);
		panel_country.add(img_country);
		
		JLabel lbl_country = new JLabel("Country");
		lbl_country.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_country.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_country.setBounds(0, 105, 111, 20);
		panel_country.add(lbl_country);
		
		panel_electronic = new JPanel();
		panel_electronic.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_electronic.setLayout(null);
		panel_electronic.setBounds(23, 152, 111, 130);
		panel_electronic.setBackground(Color.white);
		panel_electronic.addMouseListener(this);
		category_group_pane.add(panel_electronic);
		
		JLabel img_electronic = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("electronic.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_electronic.setIcon(scaled_ico);
		img_electronic.setBounds(15, 11, 86, 90);
		panel_electronic.add(img_electronic);
		
		JLabel lbl_electronic = new JLabel("Electronic");
		lbl_electronic.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_electronic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_electronic.setBounds(0, 105, 111, 20);
		panel_electronic.add(lbl_electronic);
		
		panel_food = new JPanel();
		panel_food.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_food.setLayout(null);
		panel_food.setBounds(157, 152, 111, 130);
		panel_food.setBackground(Color.white);
		panel_food.addMouseListener(this);
		category_group_pane.add(panel_food);
		
		JLabel img_food = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("food.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_food.setIcon(scaled_ico);
		img_food.setBounds(15, 11, 86, 90);
		panel_food.add(img_food);
		
		JLabel lbl_food = new JLabel("Food");
		lbl_food.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_food.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_food.setBounds(0, 105, 111, 20);
		panel_food.add(lbl_food);
		
		panel_fruit = new JPanel();
		panel_fruit.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_fruit.setLayout(null);
		panel_fruit.setBounds(292, 152, 111, 130);
		panel_fruit.setBackground(Color.white);
		panel_fruit.addMouseListener(this);
		category_group_pane.add(panel_fruit);
		
		JLabel img_fruit = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("fruit.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_fruit.setIcon(scaled_ico);
		img_fruit.setBounds(15, 11, 86, 90);
		panel_fruit.add(img_fruit);
		
		JLabel lbl_fruit = new JLabel("Fruit");
		lbl_fruit.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fruit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_fruit.setBounds(0, 105, 111, 20);
		panel_fruit.add(lbl_fruit);
		
		panel_job = new JPanel();
		panel_job.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_job.setLayout(null);
		panel_job.setBounds(438, 152, 111, 130);
		panel_job.setBackground(Color.white);
		panel_job.addMouseListener(this);
		category_group_pane.add(panel_job);
		
		JLabel img_job = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("job.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_job.setIcon(scaled_ico);
		img_job.setBounds(15, 11, 86, 90);
		panel_job.add(img_job);
		
		JLabel lbl_job = new JLabel("Job");
		lbl_job.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_job.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_job.setBounds(0, 105, 111, 20);
		panel_job.add(lbl_job);
		
		panel_sport = new JPanel();
		panel_sport.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_sport.setLayout(null);
		panel_sport.setBounds(583, 152, 111, 130);
		panel_sport.addMouseListener(this);
		panel_sport.setBackground(Color.white);
		category_group_pane.add(panel_sport);
		
		JLabel img_sport = new JLabel("");
		ico = new ImageIcon(Choose_Category_Frame.class.getResource("sport.png"));
		img = ico.getImage().getScaledInstance(86, 90, Image.SCALE_SMOOTH);
		scaled_ico = new ImageIcon(img);
		img_sport.setIcon(scaled_ico);
		img_sport.setBounds(15, 11, 86, 90);
		panel_sport.add(img_sport);
		
		JLabel lbl_sport = new JLabel("Sport");
		lbl_sport.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_sport.setBounds(0, 105, 111, 20);
		panel_sport.add(lbl_sport);
		
		btn_select = new JButton("Select");
		btn_select.addActionListener(this);
		btn_select.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_select.setBounds(389, 374, 127, 34);
		btn_select.setFocusable(false);
		contentPane.add(btn_select);
		
		btn_exit = new JButton("Exit");
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_exit.setBounds(700, 374, 106, 35);
		btn_exit.addActionListener(this);
		btn_exit.setFocusable(false);
		contentPane.add(btn_exit);
		
		JLabel lbl_Categories = new JLabel("Categories: 10");
		lbl_Categories.setBounds(90, 379, 154, 25);
		contentPane.add(lbl_Categories);
		lbl_Categories.setFont(new Font("Tahoma", Font.PLAIN, 20));
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		
		// TODO Auto-generated method stub
		if(e.getSource()==btn_select)
		{
			setVisible(false);
			System.out.println(pid);
			Hangman f = new Hangman(category,pid);
			f.setVisible(true);
		}
		if(e.getSource()==btn_exit)
		{
			this.dispose();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method 
		JPanel clicked_panel = (JPanel) e.getSource();
		Component a[]= category_group_pane.getComponents();
		for(int i=0; i<a.length;i++)
		{
			if(a[i].getClass() == JPanel.class)
			{
				a[i].setBackground(Color.white);
			}
		}
		clicked_panel.setBackground(Color.PINK);
		
		if(e.getSource()==panel_body_part)
		{
			
			category = "body_part";
			
		}
		else if(e.getSource()==panel_animal)
		{
			category = "animal";
		}
		else if(e.getSource()==panel_color)
		{
			category = "color";
		}
		else if(e.getSource()==panel_fruit)
		{
			category = "fruit";
		}
		else if(e.getSource()==panel_food)
		{
			category = "food";
		}
		else if(e.getSource()==panel_country)
		{
			category = "country";
		}
		else if(e.getSource()==panel_electronic)
		{
			category = "electronic";
		}
		else if(e.getSource()==panel_sport)
		{
			category = "sport";
		}
		else if(e.getSource()==panel_job)
		{
			category = "job";
		}
		else if(e.getSource()==panel_cloth)
		{
			category = "cloth";
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
