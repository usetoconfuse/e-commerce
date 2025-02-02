package gui;

import payments.CreditCard;
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

public class CreditCardCheckoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSecurityNumber;
	private JTextField txtCardNumber;

	/**
	 * Create the frame.
	 */
	public CreditCardCheckoutFrame(StockManager stock, Customer user) {
		setTitle("Checkout");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSecurityNumber = new JTextField();
		txtSecurityNumber.setBounds(217, 114, 86, 20);
		contentPane.add(txtSecurityNumber);
		txtSecurityNumber.setColumns(10);
		
		JLabel lblSecurityNumber = new JLabel("Security Number");
		lblSecurityNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecurityNumber.setBounds(92, 117, 115, 14);
		contentPane.add(lblSecurityNumber);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(217, 83, 86, 20);
		contentPane.add(txtCardNumber);
		txtCardNumber.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCardNumber.setBounds(109, 86, 98, 14);
		contentPane.add(lblCardNumber);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setBounds(214, 145, 89, 23);
		contentPane.add(btnPay);
		
		
		// EVENT HANDLERS
		
		// Process the payment with the entered credit card details
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cnString = GuiUtil.getTextSafe(txtCardNumber);
					if (cnString.length() != 6) {
						throw new IllegalArgumentException("Card number must be 6 digits long!");
					}
					int cardNumber = Integer.parseInt(cnString);
					
					String snString = GuiUtil.getTextSafe(txtSecurityNumber);
					if (snString.length() != 3) {
						throw new IllegalArgumentException("Security number must be 3 digits long!");
					}
					int securityNumber = Integer.parseInt(snString);
					
					CreditCard card = new CreditCard(cardNumber, securityNumber);
					JOptionPane.showMessageDialog(null, user.checkout(stock, card)); // Display the receipt window before closing
					dispose();
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Card details must be numberical!","Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (IllegalArgumentException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "An error occurred","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
