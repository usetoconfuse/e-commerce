package gui;

import payments.PayPal;
import users.Customer;
import products.StockManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class PayPalCheckoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;

	/**
	 * Create the frame.
	 */
	public PayPalCheckoutFrame(StockManager stock, Customer user) {
		setTitle("Checkout");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(215, 87, 86, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email Address");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(83, 90, 122, 14);
		contentPane.add(lblEmail);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setBounds(225, 118, 76, 23);
		contentPane.add(btnPay);
		
		
		// EVENT HANDLERS
		
		// Process the payment with the entered email address
		// Only criteria for a valid email is it must contain '@'
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String email = GuiUtil.getTextSafe(txtEmail);
					if (!email.contains("@")) {
						throw new IllegalArgumentException("Email must contain '@'!");
					}
					PayPal paypal = new PayPal(email);
					JOptionPane.showMessageDialog(null, user.checkout(stock, paypal)); // Show the receipt window before closing
					dispose();
				}
				catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "An error occurred","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
