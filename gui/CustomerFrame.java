package gui;

import users.*;
import products.*;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cntpCustomer;
	private JTable tblProducts;
	private DefaultTableModel dtmProducts;
	private JTable tblBasket;
	private DefaultTableModel dtmBasket;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private StockManager stock;
	private JTextField txtSearchBarcode;
	private JTextField txtFilterButtons;

	/**
	 * Create the frame.
	 */
	public CustomerFrame(Customer customer) throws FileNotFoundException, IOException {
		setTitle("Hello, " + customer.getName() + "!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 572);
		cntpCustomer = new JPanel();
		cntpCustomer.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cntpCustomer);
		cntpCustomer.setLayout(null);
		
		JTabbedPane tabpCustomer = new JTabbedPane(JTabbedPane.TOP);
		tabpCustomer.setBounds(0, 0, 1084, 533);
		cntpCustomer.add(tabpCustomer);
		
		JPanel panelProducts = new JPanel();
		tabpCustomer.addTab("Products", null, panelProducts, null);
		panelProducts.setLayout(null);
		
		JScrollPane spnProducts = new JScrollPane();
		spnProducts.setBounds(10, 40, 1059, 421);
		panelProducts.add(spnProducts);
		
		dtmProducts = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		dtmProducts.setColumnIdentifiers(new Object[] {"Barcode","Category","Type","Brand","Color",
				"Connectivity","Stock","Retail Price","Additional Info"});
		
		// Read the stock file and populate stock table on window creation
		stock = new StockManager();
		try {
			stock.readStockFile();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "An error occurred when reading file Stock.txt","Error",JOptionPane.ERROR_MESSAGE);
		}
		for (Product p : stock.sortedProducts()) {
			dtmProducts.addRow(GuiUtil.getProductsEntry(p));
		}
		
		tblProducts = new JTable();
		tblProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spnProducts.setViewportView(tblProducts);
		tblProducts.setModel(dtmProducts);
		
		JButton btnAddToBasket = new JButton("Add to Basket");
		btnAddToBasket.setBounds(949, 471, 120, 23);
		panelProducts.add(btnAddToBasket);
		
		JLabel lblSearchBarcode = new JLabel("Search for Barcode");
		lblSearchBarcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchBarcode.setBounds(-49, 15, 176, 14);
		panelProducts.add(lblSearchBarcode);
		
		txtSearchBarcode = new JTextField();
		txtSearchBarcode.setColumns(10);
		txtSearchBarcode.setBounds(149, 12, 103, 20);
		panelProducts.add(txtSearchBarcode);
		
		JButton btnSearchBarcode = new JButton("Search");
		btnSearchBarcode.setBounds(262, 11, 89, 23);
		panelProducts.add(btnSearchBarcode);
		
		JLabel lblFilterButtons = new JLabel("Filter Mouse Buttons");
		lblFilterButtons.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFilterButtons.setBounds(360, 15, 189, 14);
		panelProducts.add(lblFilterButtons);
		
		txtFilterButtons = new JTextField();
		txtFilterButtons.setColumns(10);
		txtFilterButtons.setBounds(566, 12, 86, 20);
		panelProducts.add(txtFilterButtons);
		
		JButton btnFilterButtons = new JButton("Filter");
		btnFilterButtons.setBounds(662, 11, 89, 23);
		panelProducts.add(btnFilterButtons);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(863, 11, 89, 23);
		panelProducts.add(btnReset);
		
		JPanel panelBasket = new JPanel();
		tabpCustomer.addTab("Basket", null, panelBasket, null);
		panelBasket.setLayout(null);
		
		JScrollPane spnBasket = new JScrollPane();
		spnBasket.setBounds(10, 11, 1059, 450);
		panelBasket.add(spnBasket);
		
		dtmBasket = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		dtmBasket.setColumnIdentifiers(new Object[] {"Barcode","Category","Type","Brand","Color",
				"Connectivity","Retail Price","Additional Info","Quantity"});
		
		tblBasket = new JTable();
		tblBasket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spnBasket.setViewportView(tblBasket);
		tblBasket.setModel(dtmBasket);
		
		JButton btnClearBasket = new JButton("Clear Basket");
		btnClearBasket.setBounds(10, 471, 164, 23);
		panelBasket.add(btnClearBasket);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(945, 471, 124, 23);
		panelBasket.add(btnCheckout);
		
		JRadioButton rdbtnCreditCard = new JRadioButton("Credit Card");
		buttonGroup.add(rdbtnCreditCard);
		rdbtnCreditCard.setBounds(843, 471, 96, 23);
		panelBasket.add(rdbtnCreditCard);
		
		JRadioButton rdbtnPayPal = new JRadioButton("PayPal");
		buttonGroup.add(rdbtnPayPal);
		rdbtnPayPal.setBounds(772, 471, 69, 23);
		panelBasket.add(rdbtnPayPal);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(633, 475, 133, 14);
		panelBasket.add(lblTotal);
		
		
		// EVENT HANDLERS
		
		// Get the barcode of the selected product in the products table and add it to the basket.
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int barcode = Integer.parseInt((String)tblProducts.getValueAt(tblProducts.getSelectedRow(), 0));
					customer.addToBasket(stock.searchBarcode(barcode));
				}
				catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Please select an item to add to basket.");
				}
			}
		});
		
		// Filter the products table by the entered barcode.
		btnSearchBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String bcString = GuiUtil.getTextSafe(txtSearchBarcode);
					if (bcString.length() != 6) {
						throw new IllegalArgumentException("Barcode must be 6 digits long!");
					}
					int barcode = Integer.parseInt(bcString);
					Product p = stock.searchBarcode(barcode);
					dtmProducts.setRowCount(0);
					if (p == null) return; // Show a blank table if the barcode has no match
					dtmProducts.addRow(GuiUtil.getProductsEntry(p));
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Barcode must be a number!","Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (IllegalArgumentException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Filter the products table by the entered number of mouse buttons.
		btnFilterButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int numberOfButtons = Integer.parseInt(GuiUtil.getTextSafe(txtFilterButtons));
					dtmProducts.setRowCount(0);
					for (Product p : stock.filterMouseButtons(numberOfButtons)) {
						dtmProducts.addRow(GuiUtil.getProductsEntry(p));
					}
				}
				catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "Invalid filter number","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Clear the basket and reset the total
		btnClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmBasket.setRowCount(0);
				customer.clearBasket();
				lblTotal.setText("Total: 0.00");
			}
		});
		
		// Reset any applied filters on the products table
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmProducts.setRowCount(0);
				txtSearchBarcode.setText("");
				txtFilterButtons.setText("");
				for (Product p : stock.sortedProducts()) {
					dtmProducts.addRow(GuiUtil.getProductsEntry(p));
				}
			}
		});
		
		// Create the appropriate checkout window when a payment method has been selected
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getBasketContents().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Cannot checkout with empty basket!");
				}
				else if (rdbtnPayPal.isSelected()) {
					PayPalCheckoutFrame ppchf = new PayPalCheckoutFrame(stock, customer);
					ppchf.setVisible(true);
				}
				else if (rdbtnCreditCard.isSelected()) {
					CreditCardCheckoutFrame ccchf = new CreditCardCheckoutFrame(stock, customer);
					ccchf.setVisible(true);
				} // Do nothing if a payment method has not been selected
			}
		});

		// Refresh the appropriate table when its tab is selected
		tabpCustomer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabpCustomer.getSelectedIndex() == 0) {
					try {
						stock.readStockFile();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "An error occurred when reading file Stock.txt","Error",JOptionPane.ERROR_MESSAGE);
					}
					dtmProducts.setRowCount(0);
					for (Product p : stock.sortedProducts()) {
						dtmProducts.addRow(GuiUtil.getProductsEntry(p));
					}
				}
				if(tabpCustomer.getSelectedIndex() == 1) {
					dtmBasket.setRowCount(0);
					for (int barcode : customer.getBasketContents().keySet()) {
						Product p = stock.searchBarcode(barcode);
						int quantityInBasket = customer.getBasketContents().get(barcode);
						dtmBasket.addRow(GuiUtil.getBasketEntry(p, quantityInBasket));
					}
					lblTotal.setText(String.format("Total: %.2f", customer.getBasketTotal()));
				}
			}
		});
	}
}
