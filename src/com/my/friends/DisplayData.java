package com.my.friends;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DisplayData extends JPanel{
	
	public boolean buy_lbl_chk, sell_lbl_chk, rental_lbl_chk;
	
	public JTable table;
	public static JScrollPane scrollpane;
	public MyTableModel model;
	public String[] Headers;
	
	public String[][] Data;
	private int rowSelected;
	
	public static int CurrentRowPostId;
	public static boolean someRowSelectedChk = false;
	
	public DisplayData() {
		
	}
	
	//This constructor is required to pass the flags of main labels(tabs) from FreindsMain class
	public DisplayData(boolean buy_flag, boolean sell_flag, boolean rental_flag) {
		this.buy_lbl_chk = buy_flag;
		this.sell_lbl_chk = sell_flag;
		this.rental_lbl_chk = rental_flag;	
		
		if (buy_lbl_chk){
		
			Headers = new String[]{"Post ID", "Category", "Product Title", "Price Expected", "Need By"};
		}
		else if (sell_lbl_chk){
			Headers  = new String[]{"Post ID", "Category", "Product Title", "Price", "Condition", "Make/Manufacturer", "Model", "Size/Dimensions"};
		}
		else if (rental_lbl_chk){
			Headers  = new String[]{"Post ID", "Category", "Property Type", "Price", "No of Bed", "No of Bath", "City", "Zipcode"};
		}
	}
	
	
	//this method mainly received the resultset of the clicked label's table and passes them as arraylists to jtable
	public void processTableData(ResultSet rslSt){
		try{
			
			ResultSetMetaData metaData = rslSt.getMetaData();
			int columns = metaData.getColumnCount();
			ArrayList<String[]> rows = new ArrayList<>();
			while(rslSt.next()){
				String[] currentRow = new String[columns];
				for(int i = 0; i < columns; i++){
					currentRow[i] = rslSt.getString(i+1);
				}
				rows.add(currentRow);
			}
			
			Data = new String[rows.size()][columns];
			
			Data = rows.toArray(Data);
			reloadTableData();
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	//main logic for creating the jtable (myTableModel) using the above data and headers and adding the jtable to scrollpane
	public void reloadTableData(){
		model = new MyTableModel(Data, Headers);
		table = new JTable(model);
		table.setOpaque(false);
		table.setPreferredScrollableViewportSize(new Dimension(700, 70));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBackground(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(true);
		jTableMouseClick();
		
		scrollpane = new JScrollPane();
		scrollpane.setBounds(getX(), getY(), 700, 70);
		scrollpane.getViewport().add(table);
		scrollpane.getViewport().setBackground(Color.LIGHT_GRAY);
	}
	
	//Main logic to return the current selected row id and hence the post id of the post
	public void jTableMouseClick(){
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    
			public void valueChanged(ListSelectionEvent e) {
		    	someRowSelectedChk = true;
		    	rowSelected = table.getSelectedRow();
		    	
		    	if ((rowSelected >= -1)) {
		    		table.changeSelection(rowSelected,0,false, false);
		        }
		    	
		    	CurrentRowPostId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
		        DataProcessing dp = new DataProcessing();
				dp.processData(false, false, false, false, false, buy_lbl_chk, sell_lbl_chk, rental_lbl_chk, DisplayData.someRowSelectedChk, false);
		    }
		});
		
	}
	
	
	
}
