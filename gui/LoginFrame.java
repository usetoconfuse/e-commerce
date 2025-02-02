package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import users.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Scanner fileReader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame(){
		setTitle("Welcome!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cbUsername = new JComboBox();
		cbUsername.setBounds(187, 91, 101, 22);
		
		// Get the user list from the user accounts file and populate the user selection
		try {
			fileReader = new Scanner(new File("UserAccounts.txt"));
			fileReader.useDelimiter(",|\n");
			while (fileReader.hasNext()) {
				String userId = fileReader.next().trim();
				String username = fileReader.next().trim();
				String name = fileReader.next().trim();
				String houseNum = fileReader.next().trim();
				String postcode = fileReader.next().trim();
				String city = fileReader.next().trim();
				UserRole role = UserRole.valueOf(fileReader.next().trim().toUpperCase());
				
				if (role == UserRole.CUSTOMER) {
					Customer user = new Customer(userId,username,name,new Address(houseNum,postcode,city));
					cbUsername.addItem(user);
				}
				else {
					User user = new User(userId,username,name,new Address(houseNum,postcode,city), UserRole.ADMIN);
					cbUsername.addItem(user);
				}
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error reading file UserAccounts.txt","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		contentPane.add(cbUsername);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(114, 95, 63, 14);
		contentPane.add(lblUsername);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(197, 124, 91, 23);
		contentPane.add(btnLogin);
		
		
		// EVENT HANDLERS
		
		// Create a window for the customer or admin view depending on selected user
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User selectedUser = (User)cbUsername.getSelectedItem();
				if (selectedUser.getRole() == UserRole.CUSTOMER) {
					Customer customer = (Customer) selectedUser;
					CustomerFrame cstf;
					try {
						cstf = new CustomerFrame(customer);
						cstf.setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "An exception has occurred","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					AdminFrame admf;
					try {
						admf = new AdminFrame(selectedUser.getName()); // Admin frame doesn't use any user details except name
						admf.setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "An exception has occurred","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
