package com.wipro.sales.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;


import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.service.Administrative;

public class SalesApplication {

	public static void main(String[] args)
	{
		

		JFrame f=new JFrame("Inventory and Sales System");
		Font addFontforLabels=new Font("Arial", Font.BOLD, 20);
		Font addFontfortext=new Font("Arial", Font.BOLD, 15);
		Font addFontforRadioButtons=new Font("Arial", Font.BOLD, 14);
		//label for choices
		JLabel choiceLabel=new JLabel("Enter the operations you want to perform:-");
		choiceLabel.setBounds(20,50,500,22);
	    choiceLabel.setFont(addFontforLabels);
	    
	    //radio buttons for choices
	    JRadioButton rInsert=new JRadioButton("Insert Stock");
	    rInsert.setBounds(20,90,200,20);
	    rInsert.setFont(addFontforRadioButtons);
	    
	    JRadioButton rDelete=new JRadioButton("Delete Stock");
	    rDelete.setBounds(20,120,200,20);
	    rDelete.setFont(addFontforRadioButtons);
	    
	    JRadioButton rInsertSales=new JRadioButton("Insert Sales");
	    rInsertSales.setBounds(20,150,200,20);
	    rInsertSales.setFont(addFontforRadioButtons);
	    
	    JRadioButton rView=new JRadioButton("View Sales Report");
	    rView.setBounds(20,180,200,20);
	    rView.setFont(addFontforRadioButtons);
	    
	    //button group for radio buttons
	    ButtonGroup bg=new ButtonGroup();
		bg.add(rInsert);
		bg.add(rDelete);
		bg.add(rInsertSales);
		bg.add(rView);
		
		JButton perform=new JButton("Perform operation");
		perform.setBounds(30,220,200,22);
		perform.setFont(addFontfortext);
		
		//textfield labels for accepting required data from user
		JLabel lpName=new JLabel("Enter product name");
		lpName.setBounds(30, 260, 200, 30);
		lpName.setFont(addFontfortext);
		lpName.setVisible(false);
		
		JLabel lQty=new JLabel("Enter Quantity On Hand");
		lQty.setBounds(30, 300, 200, 22);
		lQty.setFont(addFontfortext);
		lQty.setVisible(false);
		
		JLabel lPrice=new JLabel("Enter Product Unit Price");
		lPrice.setBounds(30, 340, 200, 22);
		lPrice.setFont(addFontfortext);
		lPrice.setVisible(false);
		
		JLabel lReoLevel=new JLabel("Enter Reorder Level");
		lReoLevel.setBounds(30, 380, 200, 22);
		lReoLevel.setFont(addFontfortext);
		lReoLevel.setVisible(false);
		
		JLabel lDeleteStock=new JLabel("Enter Product Id");
		lDeleteStock.setBounds(30, 260, 300, 22);
		lDeleteStock.setFont(addFontfortext);
		lDeleteStock.setVisible(false);
		
		JLabel lqtySold=new JLabel("Enter Quantity Sold");
		lqtySold.setBounds(30, 300, 200, 22);
		lqtySold.setFont(addFontfortext);
		lqtySold.setVisible(false);
		
		JLabel lSalesPrice=new JLabel("Enter Sales Price Per Unit");
		lSalesPrice.setBounds(30, 340, 200, 22);
		lSalesPrice.setFont(addFontfortext);
		lSalesPrice.setVisible(false);
		
		JLabel lDate=new JLabel("Enter Sales Date(MM/dd/yyyy)");
		lDate.setBounds(30, 380, 220, 22);
		lDate.setFont(addFontfortext);
		lDate.setVisible(false);
		
		//textfield for accepting required data for stock from user
		JTextField productName=new JTextField();
		productName.setBounds(250,260,150,22);
		productName.setFont(addFontfortext);
		productName.setVisible(false);
		
		JTextField quantityOnHand=new JTextField();
		quantityOnHand.setBounds(250,300,150,22);
		quantityOnHand.setFont(addFontfortext);
		quantityOnHand.setVisible(false);
		
		JTextField productUnitPrice=new JTextField();
		productUnitPrice.setBounds(250,340,150,22);
		productUnitPrice.setFont(addFontfortext);
		productUnitPrice.setVisible(false);
		
		JTextField reorderLevel=new JTextField();
		reorderLevel.setBounds(250,380,150,22);
		reorderLevel.setFont(addFontfortext);
		reorderLevel.setVisible(false);
		
		//textfield for accepting required data from user to delete a particular stock
		JTextField productId=new JTextField();
		productId.setBounds(280,260,150,22);
		productId.setFont(addFontfortext);
		productId.setVisible(false);
		
		JTextField quanSold=new JTextField();
		quanSold.setBounds(280,300,150,22);
		quanSold.setFont(addFontfortext);
		quanSold.setVisible(false);
		
		JTextField salesPrice=new JTextField();
		salesPrice.setBounds(280,340,150,22);
		salesPrice.setFont(addFontfortext);
		salesPrice.setVisible(false);
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		JFormattedTextField txtDate = new JFormattedTextField(df);
		txtDate.setBounds(280,380,150,22);
		txtDate.setFont(addFontfortext);
		txtDate.setVisible(false);
		
		JButton insert=new JButton("Insert Stock");
		insert.setBounds(230,410,200,22);
		insert.setFont(addFontfortext);
		insert.setVisible(false);
		
		JButton delete=new JButton("Delete Stock");
		delete.setBounds(230,320,200,22);
		delete.setFont(addFontfortext);
		delete.setVisible(false);
		
		JButton insSales=new JButton("Insert Sales");
		insSales.setBounds(230,410,200,22);
		insSales.setFont(addFontfortext);
		insSales.setVisible(false);
		
		JTextArea disp=new JTextArea();
		disp.setBounds(20,340,1400,800);
		disp.setFont(new Font("Arial", Font.PLAIN, 15));
		disp.setVisible(false);
		disp.setEditable(false);
		
		perform.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Administrative obj=new Administrative();
				if(rInsert.isSelected())
				{
					lpName.setVisible(true);
					lQty.setVisible(true);
					lPrice.setVisible(true);
					lReoLevel.setVisible(true);
					productName.setVisible(true);
					quantityOnHand.setVisible(true);
					productUnitPrice.setVisible(true);
					reorderLevel.setVisible(true);
					insert.setVisible(true);
					lDeleteStock.setVisible(false);
					lqtySold.setVisible(false);
					lSalesPrice.setVisible(false);
					productId.setVisible(false);
					quanSold.setVisible(false);
					salesPrice.setVisible(false);
					insSales.setVisible(false);
					delete.setVisible(false);
				    disp.setVisible(false);
				    lDate.setVisible(false);
				    txtDate.setVisible(false);
					
				}
				else if(rDelete.isSelected())
				{
					lDeleteStock.setVisible(true);
					productId.setVisible(true);
					delete.setVisible(true);
					lpName.setVisible(false);
					lQty.setVisible(false);
					lPrice.setVisible(false);
					lReoLevel.setVisible(false);
					productName.setVisible(false);
					quantityOnHand.setVisible(false);
					productUnitPrice.setVisible(false);
					reorderLevel.setVisible(false);
					lqtySold.setVisible(false);
					lSalesPrice.setVisible(false);
					quanSold.setVisible(false);
					salesPrice.setVisible(false);
					insSales.setVisible(false);
					insert.setVisible(false);
					 disp.setVisible(false);
					lDate.setVisible(false);
					txtDate.setVisible(false);
				}
				
				else if(rInsertSales.isSelected())
				{
					lDeleteStock.setVisible(true);
					productId.setVisible(true);
					lpName.setVisible(false);
					lQty.setVisible(false);
					lPrice.setVisible(false);
					lReoLevel.setVisible(false);
					productName.setVisible(false);
					quantityOnHand.setVisible(false);
					productUnitPrice.setVisible(false);
					reorderLevel.setVisible(false);
					insert.setVisible(false);
					delete.setVisible(false);
					lqtySold.setVisible(true);
					lSalesPrice.setVisible(true);
					quanSold.setVisible(true);
					salesPrice.setVisible(true);
					insSales.setVisible(true);
					disp.setVisible(false);
					lDate.setVisible(true);
				    txtDate.setVisible(true);
				}
				
				else if(rView.isSelected())
				{
					lDeleteStock.setVisible(false);
					productId.setVisible(false);
					delete.setVisible(false);
					lpName.setVisible(false);
					lQty.setVisible(false);
					lPrice.setVisible(false);
					lReoLevel.setVisible(false);
					productName.setVisible(false);
					quantityOnHand.setVisible(false);
					productUnitPrice.setVisible(false);
					reorderLevel.setVisible(false);
					lqtySold.setVisible(false);
					lSalesPrice.setVisible(false);
					quanSold.setVisible(false);
					salesPrice.setVisible(false);
					insSales.setVisible(false);
					insert.setVisible(false);
					lDate.setVisible(false);
					txtDate.setVisible(false);
				
					ArrayList<SalesReport> list;
					list=obj.getSalesReport();
					String res="Sales_Id\tSales_Date\tProduct_Id\tProduct_Name\t    Quantity_Sold      Product_Unit_Price      Sales_Price_Per_Unit      Profit_Amount\n";
					for(SalesReport item:list)
						res+=item.getSalesID()+"\t"+item.getSalesDate()+"\t"+item.getProductID()+"\t"+item.getProductName()+"\t        "+item.getQuantitySold()+"\t         "+item.getProductUnitPrice()+"\t\t"+item.getSalesPricePerUnit()+"\t          "+item.getProfitAmount()+"\n";
					disp.setText(res);
					disp.setVisible(true);
				}
			}
		});
		insert.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!(productName.getText().isEmpty() && quantityOnHand.getText().isEmpty() && productUnitPrice.getText().isEmpty() && reorderLevel.getText().isEmpty()))
				{
				Administrative obj=new Administrative();
				Product stockobj=new Product();
				stockobj.setProductName(productName.getText());
				stockobj.setQuantityOnHand(Integer.parseInt(quantityOnHand.getText()));
				stockobj.setProductUnitPrice(Double.parseDouble(productUnitPrice.getText()));
				stockobj.setReorderLevel(Integer.parseInt(reorderLevel.getText()));
				try
				{
				String msg=obj.insertStock(stockobj);
				 JOptionPane.showMessageDialog(f,msg);
				}
				catch(Exception f)
				{
					System.out.print("exception");
				}
				productName.setText("");
				quantityOnHand.setText("");
				productUnitPrice.setText("");
				reorderLevel.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Enter valid Data");
				}
			}
		});
		
		delete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!(productId.getText().isEmpty()))
				{
				
				Administrative obj=new Administrative();
				String pId=String.valueOf(productId.getText());
				String msg;
				msg=obj.deleteStock(pId);
				JOptionPane.showMessageDialog(f,msg);
				productId.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Please enter Product Id");
				}
			}
		});
		
		insSales.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!(txtDate.getText().isEmpty() && productId.getText().isEmpty() && quanSold.getText().isEmpty() && salesPrice.getText().isEmpty()))
				{
				Administrative obj=new Administrative();
				Sales salesobj=new Sales();
				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		        java.util.Date parsed=new java.util.Date();
				try 
				{
					parsed = format.parse(txtDate.getText());
				}
				catch (ParseException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        java.sql.Date sql = new java.sql.Date(parsed.getTime());
				
				salesobj.setSalesDate(sql);
				salesobj.setProductID(productId.getText());
				salesobj.setQuantitySold(Integer.valueOf(quanSold.getText()));
				salesobj.setSalesPricePerUnit(Double.valueOf(salesPrice.getText()));
				try 
				{
					String msg=obj.insertSales(salesobj);
					 JOptionPane.showMessageDialog(f,msg);
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				productId.setText("");
				txtDate.setText("");
				quanSold.setText("");
				salesPrice.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Enter valid Data");
				}
			}
		});
		
		f.add(choiceLabel);
		f.add(perform);
		f.add(insert);
		f.add(delete);
		f.add(insSales);
		
		f.add(rInsert);
		f.add(rDelete);
		f.add(rInsertSales);
		f.add(rView);
		
		f.add(lpName);
		f.add(lQty);
		f.add(lPrice);
		f.add(lReoLevel);
		f.add(lDeleteStock);
		f.add(lSalesPrice);
		f.add(lqtySold);
		f.add(lDate);
		
		f.add(productName);
		f.add(quantityOnHand);
		f.add(productUnitPrice);
		f.add(reorderLevel);
		f.add(productId);
		f.add(quanSold);
		f.add(salesPrice);
		f.add(disp);
		f.add(txtDate);
		
		f.setSize(1500,1000);
		f.setLayout(null);
		f.setVisible(true);
	}
}

		