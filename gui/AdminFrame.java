package gui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import products.*;
import users.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cntpAdmin;
	private JTable tblStock;
	private DefaultTableModel dtmStock;
	private StockManager stock;

	/**
	 * Create the frame.
	 */
	public AdminFrame(String adminName) throws IOException {
		setTitle("Admin - " + adminName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1133, 594);
		cntpAdmin = new JPanel();
		cntpAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cntpAdmin);
		cntpAdmin.setLayout(null);
		
		JTabbedPane tabpAdmin = new JTabbedPane(JTabbedPane.TOP);
		tabpAdmin.setBounds(0, 0, 1117, 555);
		cntpAdmin.add(tabpAdmin);
		
		JPanel panelStock = new JPanel();
		tabpAdmin.addTab("View Stock", null, panelStock, null);
		panelStock.setLayout(null);
		
		JScrollPane spnStock = new JScrollPane();
		spnStock.setBounds(10, 11, 1092, 505);
		panelStock.add(spnStock);
		
		dtmStock = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		dtmStock.setColumnIdentifiers(new Object[] {"Barcode","Category","Type","Brand","Color",
				"Connectivity","Stock","Original Cost","Retail Price","Additional Info"});
		
		// Read the stock file and populate stock table on window creation
		stock = new StockManager();
		try {
			stock.readStockFile();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "An error occurred when reading file Stock.txt","Error",JOptionPane.ERROR_MESSAGE);
		}
		for (Product p : stock.sortedProducts()) {
			dtmStock.addRow(GuiUtil.getStockEntry(p));
		}
		
		tblStock = new JTable();
		tblStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spnStock.setViewportView(tblStock);
		tblStock.setModel(dtmStock);
		
		JPanel panelAddProduct = new JPanel();
		tabpAdmin.addTab("Add Product", null, panelAddProduct, null);
		panelAddProduct.setLayout(null);
		
		JComboBox cbCategory = new JComboBox();
		cbCategory.setBounds(184, 77, 115, 22);
		
		cbCategory.addItem(ProductCategory.KEYBOARD);
		cbCategory.addItem(ProductCategory.MOUSE);
		
		panelAddProduct.add(cbCategory);
		
		JLabel lblSelectCategory = new JLabel("Product Category");
		lblSelectCategory.setBounds(67, 81, 107, 14);
		panelAddProduct.add(lblSelectCategory);
		
		JButton btnSelectCategory = new JButton("Select");
		btnSelectCategory.setBounds(210, 110, 89, 23);
		panelAddProduct.add(btnSelectCategory);
		
		
		// EVENT HANDLERS
		
		// Create the appropriate product window for the selected product type to add
		btnSelectCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCategory selectedCategory = (ProductCategory)cbCategory.getSelectedItem();
				if (selectedCategory == ProductCategory.KEYBOARD) {
					AddKeyboardFrame kbf = new AddKeyboardFrame(stock);
					kbf.setVisible(true);
				}
				else {
					AddMouseFrame amf = new AddMouseFrame(stock);
					amf.setVisible(true);
				}
			}
		});
		
		// Refresh the stock list when the tab is selected
		tabpAdmin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabpAdmin.getSelectedIndex() == 0) {
					try {
						stock.readStockFile();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "An error occurred when reading file Stock.txt","Error",JOptionPane.ERROR_MESSAGE);
					}
					dtmStock.setRowCount(0);
					for (Product p : stock.sortedProducts()) {
						dtmStock.addRow(GuiUtil.getStockEntry(p));
					}
				}
			}
		});
	}
}
