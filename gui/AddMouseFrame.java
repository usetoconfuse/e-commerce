package gui;

import products.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddMouseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtColor;
	private JTextField txtBrand;
	private JComboBox cbType;
	private JComboBox cbConnectivity;
	private JTextField txtStock;
	private JTextField txtOriginalCost;
	private JTextField txtRetailPrice;
	private JButton btnAddMouse;
	private JLabel lblBarcode;
	private JLabel lblBrand;
	private JLabel lblColor;
	private JLabel lblStock;
	private JLabel lblOriginalCost;
	private JLabel lblRetailPrice;
	private JLabel lblType;
	private JLabel lblConnectivity;
	private JLabel lblButtons;
	private JTextField txtButtons;

	/**
	 * Create the frame.
	 */
	public AddMouseFrame(StockManager stock) {
		setTitle("Add Mouse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(122, 28, 86, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(122, 90, 86, 20);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(122, 59, 86, 20);
		contentPane.add(txtBrand);
		txtBrand.setColumns(10);
		
		cbType = new JComboBox();
		cbType.setBounds(306, 27, 98, 22);
		
		cbType.addItem(MouseType.STANDARD);
		cbType.addItem(MouseType.GAMING);
		
		contentPane.add(cbType);
		
		cbConnectivity = new JComboBox();
		cbConnectivity.setBounds(306, 58, 98, 22);
		
		cbConnectivity.addItem(ConnectivityType.WIRED);
		cbConnectivity.addItem(ConnectivityType.WIRELESS);
		
		contentPane.add(cbConnectivity);
		
		txtStock = new JTextField();
		txtStock.setBounds(122, 142, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtOriginalCost = new JTextField();
		txtOriginalCost.setBounds(122, 173, 86, 20);
		contentPane.add(txtOriginalCost);
		txtOriginalCost.setColumns(10);
		
		txtRetailPrice = new JTextField();
		txtRetailPrice.setBounds(122, 204, 86, 20);
		contentPane.add(txtRetailPrice);
		txtRetailPrice.setColumns(10);
		
		btnAddMouse = new JButton("Add");
		btnAddMouse.setBounds(306, 203, 89, 23);
		
		contentPane.add(btnAddMouse);
		
		lblBarcode = new JLabel("Barcode");
		lblBarcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBarcode.setBounds(51, 31, 61, 14);
		contentPane.add(lblBarcode);
		
		lblBrand = new JLabel("Brand");
		lblBrand.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrand.setBounds(73, 62, 39, 14);
		contentPane.add(lblBrand);
		
		lblColor = new JLabel("Color");
		lblColor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblColor.setBounds(73, 93, 39, 14);
		contentPane.add(lblColor);
		
		lblStock = new JLabel("Stock");
		lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStock.setBounds(73, 145, 39, 14);
		contentPane.add(lblStock);
		
		lblOriginalCost = new JLabel("Original Cost");
		lblOriginalCost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOriginalCost.setBounds(33, 176, 79, 14);
		contentPane.add(lblOriginalCost);
		
		lblRetailPrice = new JLabel("Retail Price");
		lblRetailPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetailPrice.setBounds(41, 207, 71, 14);
		contentPane.add(lblRetailPrice);
		
		lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setBounds(257, 31, 39, 14);
		contentPane.add(lblType);
		
		lblConnectivity = new JLabel("Connectivity");
		lblConnectivity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConnectivity.setBounds(221, 62, 75, 14);
		contentPane.add(lblConnectivity);
		
		lblButtons = new JLabel("Buttons");
		lblButtons.setHorizontalAlignment(SwingConstants.RIGHT);
		lblButtons.setBounds(243, 93, 53, 14);
		contentPane.add(lblButtons);
		
		txtButtons = new JTextField();
		txtButtons.setBounds(306, 90, 98, 20);
		contentPane.add(txtButtons);
		txtButtons.setColumns(10);
		
		
		// EVENT HANDLERS
		
		// Create a mouse instance from entered information and add it to the stock
		btnAddMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String bcString = GuiUtil.getTextSafe(txtBarcode);
					if (bcString.length() != 6) {
						throw new IllegalArgumentException("Barcode must be 6 digits long!");
					}
					int barcode = Integer.parseInt(bcString);
					String brand = GuiUtil.getTextSafe(txtBrand);
					String color = GuiUtil.getTextSafe(txtColor).toLowerCase();
					ConnectivityType connectivity = (ConnectivityType) cbConnectivity.getSelectedItem();
					int quantityInStock = Integer.parseInt(GuiUtil.getTextSafe(txtStock));
					double originalCost = Double.parseDouble(GuiUtil.getTextSafe(txtOriginalCost));
					double retailPrice = Double.parseDouble(GuiUtil.getTextSafe(txtRetailPrice));
					MouseType type = (MouseType) cbType.getSelectedItem();
					int numberOfButtons = Integer.parseInt(txtButtons.getText());
					
					Mouse mouse = new Mouse(barcode,brand,color,connectivity,
							quantityInStock,originalCost,retailPrice,type,numberOfButtons);
					stock.addProduct(mouse);
					dispose();
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Barcode, Stock, Original Cost, Retail Price and Buttons must be numbers!","Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (IllegalArgumentException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "An error occurred.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
