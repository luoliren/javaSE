package Enter;

import Dao.FlightDAO;
import MainFrame.MainFrame;
import User.users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Enter extends JFrame implements ActionListener{

	JLabel userLa,passLa;
	JTextField userTxt;
	JPasswordField passTxt;
//	JLabel PhotoLabel = new JLabel(new ImageIcon("Flower.jpg"));

	JButton loginbt;

	JLabel InformationJLabel;
	public Enter(){
		this.setLayout(null);

		

		userLa=new JLabel("用户名");
		userLa.setSize(60, 30);
		userLa.setLocation(90, 200);
		userLa.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));

		this.add(userLa);
		
		userTxt=new JTextField();
		userTxt.setSize(150, 30);
		userTxt.setLocation(160, 200);
		this.add(userTxt);

		passLa=new JLabel("密码");
		passLa.setSize(60, 30);
		passLa.setLocation(90, 260);
		passLa.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 10));
		this.add(passLa);

		passTxt=new JPasswordField();
		passTxt.setSize(150, 30);
		passTxt.setLocation(160, 260);
		this.add(passTxt);
		this.setTitle("航班管理系统");
		this.setLocation(700,200);
		this.setSize(500, 500);


		String Background = "33.jpg";

		ImageIcon background = new ImageIcon(Background);

		JLabel label = new JLabel(background);

		label.setBounds(0, 0,this.getWidth() , this.getHeight());

		JPanel imagePanel = (JPanel) this.getContentPane();

		imagePanel.setOpaque(false);

		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.init();


		this.validate();
		this.setVisible(true);

	}

	private void init(){
		loginbt=new JButton("登录");

		loginbt.setSize(100,50);

		loginbt.setLocation(180, 330);

		loginbt.addActionListener(this);

		this.add(loginbt);

		InformationJLabel = new JLabel("欢迎使用!");
		
		InformationJLabel.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 50));

		InformationJLabel.setSize(300, 50);

		InformationJLabel.setLocation(120,100);

		this.add(InformationJLabel);

	}


	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();
		if (bt.getText().equals("登录")) {
			String name = userTxt.getText().trim();

			@SuppressWarnings("deprecation")
			String num = passTxt.getText().trim();
			//System.out.println(name);
			//System.out.println(num);
			//List<users> list= FlightDAO.LoginIn(name,num);

			if (num != null && name != null) {

				users user = FlightDAO.LoginIn(name,num);

					this.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

				if ((num != null && name != null && user !=null)) {

					MainFrame Flight = new MainFrame();



				} else {

					JOptionPane.showMessageDialog(this, "用户名或密码错误!");
				}
			}
		}
	}

}
