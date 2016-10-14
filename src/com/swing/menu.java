package com.swing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Arrays;
public class menu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numclicks=0;
	JTextField text=new JTextField();
	database d=new database();
	readunl r=new readunl();
	private void InitUI()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(4, 4));
		firstPanel.setMaximumSize(new Dimension(400, 400));
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(5, 13));
		secondPanel.setMaximumSize(new Dimension(520, 200));
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		this.setContentPane(mainPanel);
		
		JButton but=new JButton("Click to display file content");
		JMenuBar menubar=new JMenuBar();
		JMenu file=new JMenu("file");
		JMenuItem new1=new JMenuItem("new");
		JMenuItem exit=new JMenuItem("Exit");
		JMenuItem read=new JMenuItem("Read");
		new1.addActionListener(this);
		read.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				r.read();
			}
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		file.add(new1);
		file.add(read);
		file.add(exit);
		menubar.add(file);
		setJMenuBar(menubar);
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				displayfile();
			}
		});
		but.setPreferredSize(new Dimension(10, 10));
		but.setVerticalTextPosition(AbstractButton.CENTER);
		but.setHorizontalTextPosition(AbstractButton.LEADING);
		but.setMnemonic(KeyEvent.VK_M);
		firstPanel.add(but);
		secondPanel.add(text);
		setMinimumSize(new Dimension(520,600));
		setSize(550, 450);
		setTitle("SK");
		setVisible(true);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	public void actionPerformed(ActionEvent e)
	{
		numclicks++;
		text.setText("Number of clicks" + numclicks);
		JOptionPane.showMessageDialog(null,"Number of clicks" + numclicks);
		//dispose();
		JDialog frame1=new JDialog();
		final Class[] columnClass = new Class[] {
	            Integer.class, String.class, String.class, String.class
	        };
	 
	        //create table model with data
	        DefaultTableModel model = new DefaultTableModel(d.vals, d.columnNames) {
	 
	            @Override
	            public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	 
	            @Override
	            public Class<?> getColumnClass(int columnIndex)
	            {
	                return columnClass[columnIndex];
	            }
	        };
		JTable table=new JTable(model);
		table.setBackground(Color.GREEN);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		frame1.add(scrollPane);
		frame1.setSize(400, 500);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		/*JButton b=new JButton();
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				menu frame1=new menu();
				frame1.setTitle("New");
			}
		});
		frame1.add(b);
		//button1.setBackground(getForeground());
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//JOptionPane.showMessageDialog(button1, new JButton("Click!!!"));*/
	}
	private void displayfile(){
		String[] age = Arrays.copyOf(r.getage(), r.getage().length);
		String[] name = Arrays.copyOf(r.getname(), r.getname().length);
		String[][] vals=new String[1000][2];
		String[] colnames={"Name","Age"};
		int i=0;
		while(name[i]!=null){
			vals[i][0]=name[i];
			vals[i][1]=age[i];
			i++;
		}
		JDialog filedialog=new JDialog();
		JTable t=new JTable(vals,colnames);
		JScrollPane scroll=new JScrollPane(t);
		filedialog.add(scroll);
		filedialog.setVisible(true);
		filedialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		filedialog.setSize(400, 400);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new menu().InitUI();
	}

}
