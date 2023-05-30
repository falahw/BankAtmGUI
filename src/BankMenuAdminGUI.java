import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankMenuAdminGUI extends JFrame implements ActionListener {

	private Dimension dimensi;
	private JPanel panelAdm; //, panelAdmShow, panelAdmCreate, panelAdmEdit;
	private JLabel labelAdm;
	private JRadioButton tombolShowData, tombolCreateData, tombolEditData, tombolDelData, tombolLogOut;
	private JButton tombolShowMenu;
	private ButtonGroup tombolAdmGroup;
	private String menuAdmChosen = null;
	
	BankKuDatGUI bkdGui;
	
	private JPanel panelAdmShow = new JPanel();
	private JPanel panelAdmCreate = new JPanel();
	private JPanel panelAdmEdit = new JPanel();
	private JPanel panelAdmDel = new JPanel();

	//EDIT PROPERTIES
	private int iTry = 0;
	private JLabel labelAdmEdit, labelAdmEditInputUserId, labelAdmEditInputAccNo;
	private String rbEditValue = "";
	private String userDataCur, userDataAlt, editViolMsgPass, editViolMsgName, editViolMsgAddr, editViolMsgMom, editViolMsgJob;
	private String checkEditPass, checkEditName, checkEditBdayDate, checkEditBdayMonth, checkEditBdayYear, checkEditAddr, checkEditMom, checkEditJob;
	private boolean reqEditViol = true, reqEditViolPass, reqEditViolName, reqEditViolAddr, reqEditViolMom, reqEditViolJob;
	private boolean passedCheckSpace, passedCheckNameJob, passedCheckAddr, passedCheckMom, passedCheckJob;
	private int editBdayMonthOddEven, oddEven;
	
	public BankMenuAdminGUI() throws FileNotFoundException {
		
		// TODO Auto-generated constructor stub
		bkdGui = new BankKuDatGUI();
		
		setTitle("Bank Menu Admin GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//setLocationRelativeTo(null);
		
		dimensi = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1250, 750); //(dimensi.width, dimensi.height);
		
		tombolShowData = new JRadioButton("Show data");
		tombolCreateData = new JRadioButton("Create data");
		tombolEditData = new JRadioButton("Edit data");
		tombolDelData = new JRadioButton("Delete data");
		tombolLogOut = new JRadioButton("Logot");
		
		tombolShowData.setBounds(0, 0, 125, 30);
		tombolCreateData.setBounds(0, 33, 125, 30);
		tombolEditData.setBounds(0, 65, 125, 30);
		tombolDelData.setBounds(0, 98, 125, 30);
		
		tombolShowMenu = new JButton();
		tombolShowMenu.setText("Show Menu");
		tombolShowMenu.setBounds(0, 131, 125, 30);
		
		tombolAdmGroup = new ButtonGroup();
		
		tombolAdmGroup.add(tombolShowData);
		tombolAdmGroup.add(tombolCreateData);
		tombolAdmGroup.add(tombolEditData);
		tombolAdmGroup.add(tombolDelData);
		
		panelAdm = new JPanel();
//		panelAdm.setBackground(Color.LIGHT_GRAY);
		panelAdm.setBounds(135, 0, 1120, 720);
		panelAdm.setLayout(null);
		
		tombolShowData.addActionListener(this);
		tombolCreateData.addActionListener(this);
		tombolEditData.addActionListener(this);
		tombolDelData.addActionListener(this);
		tombolShowMenu.addActionListener(this);
		
		labelAdm = new JLabel();
		labelAdm.setText("Welcome admin " + bkdGui.getUserName() + " ;)");
		labelAdm.setBackground(Color.LIGHT_GRAY);
		labelAdm.setBounds(0, 10, 200, 30);
		
		panelAdm.add(labelAdm);
		
		add(tombolShowData);
		add(tombolCreateData);
		add(tombolEditData);
		add(tombolDelData);
		add(tombolShowMenu);
		add(panelAdm);
		
		setVisible(true);
	}
	
	private void menuAdminGUIShow() {
		// TODO Auto-generated method stub
		if (panelAdm.isShowing() == true) {
			panelAdm.remove(labelAdm);
			remove(panelAdm);
		} else if (panelAdmCreate.isShowing() == true) {
			remove(panelAdmCreate);
		} else if (panelAdmEdit.isShowing() == true) {
			remove(panelAdmEdit);
		} else if (panelAdmDel.isShowing() == true) {
			remove(panelAdmDel);
		}
		
		panelAdmShow.setBackground(Color.red);
		panelAdmShow.setToolTipText("Hover");
		panelAdmShow.setBounds(135, 0, 1120, 720);
		panelAdmShow.setLayout(new BorderLayout());
		
		JTextArea jta = new JTextArea();
//		jta.setPreferredSize(new Dimension(200, 300));
//		jta.setBounds(10, 10, 1000, 700);
		jta.setFont(new Font("Courier New", Font.PLAIN, 14)); //("Liberation Mono", Font.PLAIN, 14)
		jta.append(bkdGui.showBankCust());
		jta.setEditable(false);

		add(panelAdmShow);
		panelAdmShow.add(new JScrollPane(jta), BorderLayout.CENTER);
		repaint();
	}
	
	private void menuAdminGUICreate() {
		// TODO Auto-generated method stub
		if (panelAdm.isShowing() == true) {
			panelAdm.remove(labelAdm);
			remove(panelAdm);
		} else if (panelAdmShow.isShowing() == true) {
			remove(panelAdmShow);
		} else if (panelAdmEdit.isShowing() == true) {
			remove(panelAdmEdit);
		} else if (panelAdmDel.isShowing() == true) {
			remove(panelAdmDel);
		}
		
		panelAdmCreate.setBackground(Color.yellow);
		panelAdmCreate.setBounds(135, 0, 1120, 720);
		add(panelAdmCreate);
		repaint();
	}
	
	private void menuAdminGUIEdit() {
		// TODO Auto-generated method stub
		if (panelAdm.isShowing() == true) {
			panelAdm.remove(labelAdm);
			remove(panelAdm);
		} else if (panelAdmShow.isShowing() == true) {
			remove(panelAdmShow);
		} else if (panelAdmCreate.isShowing() == true) {
			remove(panelAdmCreate);
		} else if (panelAdmDel.isShowing() == true) {
			remove(panelAdmDel);
		}
		
		panelAdmEdit.removeAll();
		
		panelAdmEdit.setBounds(135, 0, 1120, 720);
		panelAdmEdit.setLayout(null);

		labelAdmEdit = new JLabel();
		labelAdmEdit.setText("Enter User-ID & Bank Account Number of user that you want to edit");
		labelAdmEdit.setBounds(5, 5, 500, 30);
		
		labelAdmEditInputUserId = new JLabel();
		labelAdmEditInputUserId.setText("User ID");
		labelAdmEditInputUserId.setBounds(5, 40, 100, 30);
		
		labelAdmEditInputAccNo = new JLabel();
		labelAdmEditInputAccNo.setText("User Account No");
		labelAdmEditInputAccNo.setBounds(5, 78, 150, 30);
		
		JTextField jtfAdmEditUserId, jtfAdmEditAccNo;
		jtfAdmEditUserId = new JTextField();
		jtfAdmEditUserId.setBounds(160, 40, 150, 30);
		
		jtfAdmEditAccNo = new JTextField();
		jtfAdmEditAccNo.setBounds(160, 78, 150, 30);
		
		JButton tombolAdmEdit = new JButton("Edit Data");
		tombolAdmEdit.setBounds(5, 120, 125, 30);
		
		panelAdmEdit.add(labelAdmEdit);
		panelAdmEdit.add(labelAdmEditInputUserId);
		panelAdmEdit.add(jtfAdmEditUserId);
		panelAdmEdit.add(labelAdmEditInputAccNo);
		panelAdmEdit.add(jtfAdmEditAccNo);
		panelAdmEdit.add(tombolAdmEdit);
		
		add(panelAdmEdit);
		
		try {
			tombolAdmEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource() == tombolAdmEdit) {
						System.out.println("\nUser to be edited is ID: " + jtfAdmEditUserId.getText() + "; AccNo:" + jtfAdmEditAccNo.getText());
						
						try {
							System.out.println("Checking data match....");
							
							editUserGUI(jtfAdmEditUserId.getText(), jtfAdmEditAccNo.getText());
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		} catch (Exception exc) {
			// TODO: handle exception
			exc.printStackTrace();
		}
		
		repaint();
	}
	
	private void menuAdminGUIDel() {
		// TODO Auto-generated method stub
		if (panelAdm.isShowing() == true) {
			panelAdm.remove(labelAdm);
			remove(panelAdm);
		} else if (panelAdmShow.isShowing() == true) {
			remove(panelAdmShow);
		} else if (panelAdmCreate.isShowing() == true) {
			remove(panelAdmCreate);
		} else if (panelAdmEdit.isShowing() == true) {
			remove(panelAdmEdit);
		}
		
		panelAdmDel.setBackground(Color.BLUE);
		panelAdmDel.setBounds(135, 0, 1120, 720);
		
		JButton tombolAdmDel = new JButton("Tombol Del");
		tombolAdmDel.setBounds(10, 10, 125, 30);
		
		panelAdmDel.add(tombolAdmDel);
		add(panelAdmDel);
		repaint();
	}
	
	private void menuAdminGUIEditChoose() {
		// TODO Auto-generated method stub
		panelAdmEdit.removeAll();

		userDataAlt = userDataCur = bkdGui.getBankUser().toString();
		
		panelAdmEdit.setBounds(135, 0, 1120, 720);
		panelAdmEdit.setLayout(null);
		
		labelAdmEdit.setText("CHOOSE DATA YOU WANT TO ALTER");
		
		JLabel lblEditRole, lblEditAccNo, lblEditUserId, lblEditPass, lblEditName, lblEditBday, lblEditAddr, lblEditMom, lblEditJob, lblEditRSal, lbleditBal, lblEditStat;
		lblEditAccNo = null;
		
		JLabel lblEditNowRole, lblEditNowPass, lblEditNowName, lblEditNowBday, lblEditNowAddr, lblEditNowMom, lblEditNowJob;
		JLabel lblEditToRole, lblEditToPass, lblEditToName, lblEditToBday, lblEditToBdayDate, lblEditToBdayMonth, lblEditToBdayYear, lblEditToAddr, lblEditToMom, lblEditToJob;
		JLabel lblEditReqPass, lblEditReqName, lblEditReqBday, lblEditReqAddr, lblEditReqMom, lblEditReqJob;
		JComboBox<String> cbEditBdayDate, cbEditBdayMonth, cbEditBdayYear;
		
		JRadioButton rbEditRoleAdm, rbEditRoleCust, rbEditMom, rbEditJob, rbEditRSal, rbEditBal;
		JTextField txtEditRole, txtEditAccNo, txtEditUserID, txtEditNowPass, txtEditNowName, txtEditNowBday, txtEditNowAddr, txtEditNowMom, txtEditNowJob;
		JTextField txtEditToPass, txtEditToName, txtEditToAddr, txtEditToMom, txtEditToJob;
		ButtonGroup btnUserData;
		
		//FIELD EDIT USER ROLE
		lblEditRole = new JLabel("USER ROLE");
		lblEditRole.setBounds(5, 35, 125, 25);

		lblEditNowRole = new JLabel("Current");
		lblEditNowRole.setBounds(135, 35, 75, 25);
		
		txtEditRole = new JTextField(bkdGui.getUserRole());
		txtEditRole.setBounds(210, 35, 125, 25);
		txtEditRole.setEditable(false);
		
		lblEditToRole = new JLabel("..edit to..");
		lblEditToRole.setBounds(355, 35, 125, 25);
		
		rbEditRoleAdm = new JRadioButton("Admin");
		rbEditRoleAdm.setBounds(450, 35, 75, 25);
		rbEditRoleAdm.setEnabled(false);

		rbEditRoleCust = new JRadioButton("Customer");
		rbEditRoleCust.setBounds(535, 35, 125, 25);
		rbEditRoleCust.setEnabled(false);
		
		if (bkdGui.getUserRole().equals("admin")) {
			rbEditRoleAdm.setSelected(true);
		}
		else if (bkdGui.getUserRole().equals("cust")) {
			rbEditRoleCust.setSelected(true);
		}
		
		ButtonGroup rbEditRole = new ButtonGroup();
		rbEditRole.add(rbEditRoleAdm);
		rbEditRole.add(rbEditRoleCust);
		
		//FIELD EDIT USER ACC-NO
		lblEditAccNo = new JLabel("ACCOUNT NO");
		lblEditAccNo.setBounds(5, 70, 200, 25);
		
		txtEditAccNo = new JTextField(bkdGui.getUserAccNo());
		txtEditAccNo.setBounds(210, 70, 125, 25);
		txtEditAccNo.setEditable(false);
		
		//FIELD EDIT USER-ID
		lblEditUserId = new JLabel("USER-ID");
		lblEditUserId.setBounds(5, 105, 125, 25);
		
		txtEditUserID = new JTextField(bkdGui.getUserId());
		txtEditUserID.setBounds(210, 105, 125, 25);
		txtEditUserID.setEditable(false);
		
		//FIELD EDIT USER PASS
		lblEditPass = new JLabel("PASSWORD");
		lblEditPass.setBounds(5, 140, 125, 25);
		
		lblEditNowPass = new JLabel("Current");
		lblEditNowPass.setBounds(135, 140, 75, 25);
		
		txtEditNowPass = new JTextField(bkdGui.getUserPass());
		txtEditNowPass.setBounds(210, 140, 125, 25);
		txtEditNowPass.setEditable(false);
		
		lblEditToPass = new JLabel("..edit to..");
		lblEditToPass.setBounds(355, 140, 125, 25);
		
		txtEditToPass = new JTextField();
		txtEditToPass.setBounds(450, 140, 125, 25);
		
		if (reqEditViol == false) {
			txtEditToPass.setText(checkEditPass);
			txtEditToPass.setEditable(true);
		} else if (reqEditViol == true) {
			txtEditToPass.setText(bkdGui.getUserPass());
			txtEditToPass.setEditable(false);
		}
		
		lblEditReqPass = new JLabel("*) no space char");
		lblEditReqPass.setBounds(585, 140, 200, 25);
		
		//FIELD EDIT USER NAME
		lblEditName = new JLabel("USER NAME");
		lblEditName.setBounds(5, 175, 125, 25);
		
		lblEditNowName = new JLabel("Current");
		lblEditNowName.setBounds(135, 175, 75, 25);
		
		txtEditNowName = new JTextField(bkdGui.getUserName());
		txtEditNowName.setBounds(210, 175, 125, 25);
		txtEditNowName.setEditable(false);
		
		lblEditToName = new JLabel("..edit to..");
		lblEditToName.setBounds(355, 175, 125, 25);
		
		txtEditToName = new JTextField();
		txtEditToName.setBounds(450, 175, 125, 25);
		
		if (reqEditViol == false) {
			txtEditToName.setText(checkEditName);
			txtEditToName.setEditable(true);
		} else if (reqEditViol == true) {
			txtEditToName.setText(bkdGui.getUserName());
			txtEditToName.setEditable(false);
		}
		
		lblEditReqName = new JLabel("*) only letters & spacing");
		lblEditReqName.setBounds(585, 175, 200, 25);
		
		//FIELD EDIT BIRTHDAY
		lblEditBday = new JLabel("BIRTHDAY");
		lblEditBday.setBounds(5, 210, 125, 25);
		
		lblEditNowBday = new JLabel("Current");
		lblEditNowBday.setBounds(135, 210, 125, 25);
		
		txtEditNowBday = new JTextField(bkdGui.getUserDob());
		txtEditNowBday.setBounds(210, 210, 125, 25);
		txtEditNowBday.setEditable(false);
		
		lblEditToBday = new JLabel("..edit to..");
		lblEditToBday.setBounds(355, 210, 125, 25);
		
		//SETUP for EDIY BDAY DATE INPUT
		String[] splitEditGUIBday = bkdGui.getUserDob().split("/");
		
		String[] cbBdayDate = new String[31];
		for (int i = 0; i < cbBdayDate.length; i++) {
			cbBdayDate[i] = Integer.toString(i+1);
		}
		cbEditBdayDate = new JComboBox<String>(cbBdayDate);
		
		lblEditToBdayDate = new JLabel("Date");
		lblEditToBdayDate.setBounds(617, 210, 62, 25);
		
		cbEditBdayDate.setBounds(657, 210, 45, 25);
		cbEditBdayDate.setSelectedItem(splitEditGUIBday[0]);
		cbEditBdayDate.setEnabled(false);
		cbEditBdayDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("edit day date: " + cbEditBdayDate.getSelectedItem());
			}
		});
		
		//SETUP for EDIT BDAY YEAR INPUT
		lblEditToBdayYear = new JLabel("Year");
		lblEditToBdayYear.setBounds(715, 210, 45, 25);
		
		String cbBdayYearLastToList = new SimpleDateFormat("yyyy").format(new java.util.Date());
		int cbBdayYearLast = Integer.parseInt(cbBdayYearLastToList);
		String[] cbBdayYear = new String[120];
		for (int i = 0; i < cbBdayYear.length; i++) {
			cbBdayYear[i] = Integer.toString(cbBdayYearLast-i);
		}
		
		cbEditBdayYear = new JComboBox<String>(cbBdayYear);
		cbEditBdayYear.setBounds(753, 210, 59, 25);
		cbEditBdayYear.setSelectedItem(splitEditGUIBday[2]);
		cbEditBdayYear.setEnabled(false);
		cbEditBdayYear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("edit bday year: " + cbEditBdayYear.getSelectedItem());
			}
		});
		
		//SETUP for EDIT BDAY MONTH INPUT		
		lblEditToBdayMonth = new JLabel("Month");
		lblEditToBdayMonth.setBounds(450, 210, 62, 25);
		
		String[] cbBdayMonth = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		cbEditBdayMonth = new JComboBox<String>(cbBdayMonth);
		cbEditBdayMonth.setBounds(500, 210, 103, 25);
		cbEditBdayMonth.setSelectedIndex(Integer.parseInt(splitEditGUIBday[1]) -1);
		cbEditBdayMonth.setEnabled(false);
		cbEditBdayMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("edit bday month: " + cbEditBdayMonth.getSelectedItem());
				editBdayMonthOddEven = cbEditBdayMonth.getSelectedIndex() +1;
				
				if (editBdayMonthOddEven == 2 || editBdayMonthOddEven == 4 || editBdayMonthOddEven == 6 || editBdayMonthOddEven == 9 || editBdayMonthOddEven == 11) {
					oddEven = 30;
					System.out.println(oddEven + " days month");
					
					if (cbEditBdayDate.getItemCount() < 29) {
						cbEditBdayDate.addItem("29");
					}
					
					if (cbEditBdayDate.getItemCount() < 30) {
						cbEditBdayDate.addItem("30");
					}
					
					cbEditBdayDate.removeItem("31");
				} else {
					oddEven = 31;
					System.out.println(oddEven + " days month");
					
					if (cbEditBdayDate.getItemCount() < 29) {
						cbEditBdayDate.addItem("29");
					}
					
					if (cbEditBdayDate.getItemCount() < 30) {
						cbEditBdayDate.addItem("30");
					}
					
					if (cbEditBdayDate.getItemCount() < 31) {
						cbEditBdayDate.addItem("31");
					}
				}
				
				if (isLeapYear(Integer.parseInt((String) cbEditBdayYear.getSelectedItem())) == false && cbEditBdayMonth.getSelectedItem().equals("February")) {
					cbEditBdayDate.removeItem("30");
					cbEditBdayDate.removeItem("29");
				}
				else if (isLeapYear(Integer.parseInt((String) cbEditBdayYear.getSelectedItem())) == true && cbEditBdayMonth.getSelectedItem().equals("February")) {
					cbEditBdayDate.removeItem("30");
				}
			}
		});
		
		//FIELD EDIT ADDRESS
		lblEditAddr = new JLabel("ADDRESS");
		lblEditAddr.setBounds(5, 245, 125, 25);
		
		lblEditNowAddr = new JLabel("Current");
		lblEditNowAddr.setBounds(135, 245, 125, 25);
		
		txtEditNowAddr = new JTextField(bkdGui.getUserAddr());
		txtEditNowAddr.setBounds(210, 245, 125, 25);
		txtEditNowAddr.setEditable(false);
		
		lblEditToAddr = new JLabel("..edit to..");
		lblEditToAddr.setBounds(355, 245, 125, 25);
		
		txtEditToAddr = new JTextField(bkdGui.getUserAddr());
		txtEditToAddr.setBounds(450, 245, 125, 25);
		
		if (reqEditViol == false) {
			txtEditToAddr.setText(checkEditAddr);
			txtEditToAddr.setEditable(true);
		} else if (reqEditViol == true) {
			txtEditToAddr.setText(bkdGui.getUserAddr());
			txtEditToAddr.setEditable(false);
		}
		
		lblEditReqAddr = new JLabel("*) empty is not allowed");
		lblEditReqAddr.setBounds(585, 245, 200, 25);
		
		
		//FIELD EDIT MOTHER's NAME
		rbEditMom = new JRadioButton("Mother's Name: " + bkdGui.getUserMom());
		rbEditMom.setBounds(5, 188, 300, 25);
		rbEditMom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rbEditValue = "editGUIMom";
			}
		});
		
		lblEditMom = new JLabel("MOTHER's NAME");
		lblEditMom.setBounds(5, 280, 125, 25);
		
		lblEditNowMom = new JLabel("Current");
		lblEditNowMom.setBounds(135, 280, 125, 25);
		
		txtEditNowMom = new JTextField(bkdGui.getUserMom());
		txtEditNowMom.setEditable(false);
		txtEditNowMom.setBounds(210, 280, 125, 25);
		
		lblEditToMom = new JLabel("..edit to..");
		lblEditToMom.setBounds(355, 280, 125, 25);
		
		txtEditToMom = new JTextField(bkdGui.getUserMom());
		txtEditToMom.setBounds(450, 280, 125, 25);
		
		if (reqEditViol == false) {
			txtEditToMom.setText(checkEditMom);
			txtEditToMom.setEditable(true);
		} else if (reqEditViol == true) {
			txtEditToMom.setText(bkdGui.getUserMom());
			txtEditToMom.setEditable(false);
		}
		
		lblEditReqMom = new JLabel("*) only letters & spacing");
		lblEditReqMom.setBounds(585, 280, 200, 25);
		
		//FIELD EDIT JOB
		rbEditJob = new JRadioButton("Occupation: " + bkdGui.getUserJob());
		rbEditJob.setBounds(5, 213, 300, 25);
		rbEditJob.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rbEditValue = "editGUIJob";
			}
		});
		
		//FIELD EDIT SALARY RANGE
		rbEditRSal = new JRadioButton("Salary Range: " + bkdGui.getUserSalaryRange());
		rbEditRSal.setBounds(5, 238, 300, 25);
		rbEditRSal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rbEditValue = "editGUIRSal";
			}
		});
		
		//FIELD EDIT ACC BALANCE
		rbEditBal = new JRadioButton("Account Balance; " + bkdGui.getUserBalance());
		rbEditBal.setBounds(5, 263, 300, 25);
		rbEditBal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rbEditValue = "editGUIBal";
			}
		});
		
		//FIELD EDIT ACC STATUS
		lblEditStat = new JLabel("Membership Status: " + bkdGui.getUserStatus());
		lblEditStat.setBounds(27, 288, 300, 25);
		
		btnUserData = new ButtonGroup();
		btnUserData.add(rbEditMom);
		btnUserData.add(rbEditJob);
		btnUserData.add(rbEditRSal);
		btnUserData.add(rbEditBal);
		
		JButton btnAdmEdit = new JButton("Edit Data");
		btnAdmEdit.setBounds(5, 415, 125, 30);
		btnAdmEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rbEditRoleAdm.setEnabled(true);
				rbEditRoleCust.setEnabled(true);
				
				txtEditToPass.setEditable(true);
				txtEditToName.setEditable(true);
				
				cbEditBdayDate.setEnabled(true);
				cbEditBdayMonth.setEnabled(true);
				cbEditBdayYear.setEnabled(true);
				
				txtEditToAddr.setEditable(true);
				txtEditToMom.setEditable(true);
				
//				else if (rbEditValue.equals("editGUIPass")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIName")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIBday")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIAddr")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIMom")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIJob")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIRSal")) {
//					System.out.println(rbEditValue);
//				}
//				else if (rbEditValue.equals("editGUIBal")) {
//					System.out.println(rbEditValue);
//				}
//				else {
//					System.out.println("Not defined!");
//				}
			}
		});
		
		JButton btnAdmEditSave = new JButton("Save Data");
		btnAdmEditSave.setBounds(135, 415, 125, 30);
		btnAdmEditSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				//MAKE CHANGE to USER ROLE
				if (rbEditRoleAdm.isSelected()) {
					bkdGui.setUserRole("admin");
				}
				else if (rbEditRoleCust.isSelected()) {
					bkdGui.setUserRole("cust");
				}
				
				//MAKE CHANGE to USER PASS and VALIDATE
				checkEditPass = txtEditToPass.getText();
				if (!checkEditPass.equals(userDataCur.split(";")[3])) { //compare ALTERED PASS with CURRENT ONE
					validatePassword(checkEditPass);
					bkdGui.setUserPass(checkEditPass);
					
					if (passedCheckSpace == false) { //do IF VALIDATION of PASS is NOT PASSED
						reqEditViol = false;
						reqEditViolPass = false;
						
						editViolMsgPass = "\n- Password input is '" + bkdGui.getUserPass() + "'. Space char is not allowed. Do not leave this empty";
					} else if (passedCheckSpace == true) { //do IF VALIDATION of PASS is PASSED
//						reqEditViol = true;
						reqEditViolPass = true;
						
						bkdGui.setUserPass(checkEditPass);
						System.out.println("requested new password: " + checkEditPass);
					}
				} else {
					reqEditViolPass = true;
					editViolMsgPass = "";
				}
				
				//MAKE CHANGE to USER NAME and VALIDATE
				checkEditName = txtEditToName.getText();
				if (!checkEditName.equals(userDataCur.split(";")[4])) {//compare ALTERED NAME with CURRENT ONE
					validateNameJob(checkEditName);
					bkdGui.setUserName(checkEditName);
					
					if (passedCheckNameJob == false) { //do IF VALIDATION of NAME is NOT PASSED
						reqEditViol = false;
						reqEditViolName = false;
						
						editViolMsgName = "\n- User Name input is '" + bkdGui.getUserName() + "'. Only letter & spacing are allowed. Do not leave this empty";
					} else if (passedCheckNameJob == true) { //do IF VALIDATION of NAME is PASSED
//						reqEditViol = true;
						reqEditViolName = true;

						bkdGui.setUserName(checkEditName);
						System.out.println("requested new name: " + checkEditName);
					}
				} else {
					reqEditViolName = true;
					editViolMsgName = "";
				}
				
				//MAKE CHANGE to USER BDAY
				checkEditBdayDate = (String) cbEditBdayDate.getSelectedItem();
				checkEditBdayMonth = Integer.toString(cbEditBdayMonth.getSelectedIndex()+1);
				if (Integer.parseInt(checkEditBdayMonth) < 10) {
					checkEditBdayMonth = "0" + checkEditBdayMonth;
				}
				checkEditBdayYear = (String) cbEditBdayYear.getSelectedItem();
				
				bkdGui.setUserDob(checkEditBdayDate + "/" + checkEditBdayMonth + "/" + checkEditBdayYear);

				//MAKE CHANGE to USER ADDR and VALIDATE
				checkEditAddr = txtEditToAddr.getText();
				if (!checkEditAddr.equals(userDataCur.split(";")[6])) {
					validateAddr(checkEditAddr);
					bkdGui.setUserAddr(checkEditAddr);
					
					if (passedCheckAddr == false) { //do IF VALIDATION of ADDRESS in NOT PASSED
						reqEditViol = false;
						reqEditViolAddr = false;
						
						editViolMsgAddr = "\n- User Address input is '" + bkdGui.getUserAddr() + "'. Do not leave this empty";
					} else if (passedCheckAddr == true) {
//						reqEditViol = true;
						reqEditViolAddr = true;
						
						bkdGui.setUserAddr(checkEditAddr);
						System.out.println("requested new address: " + checkEditAddr);
					}
				} else {
					reqEditViolAddr = true;
					editViolMsgAddr = "";
				}
				
				//MAKE CHANGE to USER MOM's NAME and VALIDATE
				checkEditMom = txtEditToMom.getText();
				if (!checkEditMom.equals(userDataCur.split(";")[7])) {
					validateNameJob(checkEditMom);
					bkdGui.setUserMom(checkEditMom);
					
					if (passedCheckNameJob == false) {
						reqEditViol = false;
						reqEditViolMom = false;
						
						editViolMsgMom = "\n- User Mother's Name input is '" + bkdGui.getUserMom() + "'. Only letters & spacing are allowed. Do not leave this empty"; 
					} else if (passedCheckNameJob == true) {
						reqEditViolMom = true;
						
						bkdGui.setUserMom(checkEditMom);
						System.out.println("requested new mom: " + checkEditMom);
					}
				} else {
					reqEditViolMom = true;
					editViolMsgMom = "";
				}
							
				userDataAlt = bkdGui.getBankUser().toString();
				
//				System.out.println("\nuserDataCur: " + userDataCur);
//				System.out.println("userDataAlt: " + userDataAlt);
//				System.out.println("bkdGUI.getUserId() =  " + bkdGui.getBankUser());
//				System.out.println("reqEditViolPass = " + reqEditViolPass + "; reqEditViolName = " + reqEditViolName + "\n");
				
				//CONFIRM ALTER DATA
				if (!userDataCur.equals(userDataAlt)) {
					System.out.println("Request data change");
					
					//SEND EDIT VIOLATION MESSAGE
					if (reqEditViol == false) {
						JOptionPane.showMessageDialog(null,
								"Password violation detected\n" + editViolMsgPass + editViolMsgName + editViolMsgAddr + editViolMsgMom,
								"Warning Edit Requirement Violation", JOptionPane.WARNING_MESSAGE);
						
						//RESET EVERY CHANGE to THEIR CURRENT DATA
						if (reqEditViolPass == false) {
							bkdGui.setUserPass(userDataCur.split(";")[3]);
//							checkEditPass = bkdGui.getUserPass();
						}
						
						if (reqEditViolName == false) {
							bkdGui.setUserName(userDataCur.split(";")[4]);
//							checkEditName = bkdGui.getUserName();
						}
						
						if (reqEditViolAddr == false) {
							bkdGui.setUserAddr(userDataCur.split(";")[6]);
							
						}
						
						if (reqEditViolMom == false) {
							bkdGui.setUserMom(userDataCur.split(";")[7]);
						}
					} else {
						int opsiEdit = JOptionPane.showConfirmDialog(null, "Data is changed! Proceed?", "Data Change Info", JOptionPane.YES_NO_OPTION);
						
						if (opsiEdit == 0) { //CONFIRM APPROVED
							bkdGui.editBankUser((bkdGui.getBankUser().toString()));
						} else { //CONFIRM REJECTED
							System.out.println("Change is discarded");
							
							JOptionPane.showMessageDialog(null, "Change is discarded! Data remain the same", "Data Change Info", JOptionPane.INFORMATION_MESSAGE);
							
							//RESET DATA to THEIR CURRENT since NO ALTERATION is MADE
							bkdGui.setUserRole(userDataCur.split(";")[0]);
							bkdGui.setUserPass(userDataCur.split(";")[3]);
							bkdGui.setUserName(userDataCur.split(";")[4]);
							bkdGui.setUserAddr(userDataCur.split(";")[6]);
							bkdGui.setUserMom(userDataCur.split(";")[7]);
						}
					}
				} else { //SAVE w/o ALTER ANY DATA
					reqEditViol = true;
					
					System.out.println("No change. Data remain the same");
					
					JOptionPane.showMessageDialog(null, "No change. Data remain the same", "Data Change Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
				System.out.println("Attempt " + iTry++);
				panelAdmEdit.removeAll();
				menuAdminGUIEditChoose();
			}
		});
		
		//ADDING GUI-COMPONENT if USER-ROLE
		panelAdmEdit.add(labelAdmEdit);
		panelAdmEdit.add(lblEditRole);
		panelAdmEdit.add(lblEditNowRole);
		panelAdmEdit.add(txtEditRole);
		panelAdmEdit.add(lblEditToRole);
		panelAdmEdit.add(rbEditRoleAdm);
		panelAdmEdit.add(rbEditRoleCust);
		
		//ADDING GUI-COMPONENT of ACC-NO & USER-ID
		panelAdmEdit.add(lblEditAccNo);
		panelAdmEdit.add(txtEditAccNo);
		panelAdmEdit.add(lblEditUserId);
		panelAdmEdit.add(txtEditUserID);
		
		//ADDING GUI-COMPONENT of USER PASS
		panelAdmEdit.add(lblEditPass);
		panelAdmEdit.add(txtEditNowPass);
		panelAdmEdit.add(lblEditNowPass);
		panelAdmEdit.add(lblEditToPass);
		panelAdmEdit.add(txtEditToPass);
		panelAdmEdit.add(lblEditReqPass);
		
		//ADDING GUI-COMPONENT of USER NAME
		panelAdmEdit.add(lblEditName);
		panelAdmEdit.add(lblEditNowName);
		panelAdmEdit.add(txtEditNowName);
		panelAdmEdit.add(lblEditToName);
		panelAdmEdit.add(txtEditToName);
		panelAdmEdit.add(lblEditReqName);
		
		//ADDING GUI-COMPONENT of BIRTHDAY
		panelAdmEdit.add(lblEditBday);
		panelAdmEdit.add(lblEditNowBday);
		panelAdmEdit.add(txtEditNowBday);
		panelAdmEdit.add(lblEditToBday);
		panelAdmEdit.add(lblEditToBdayDate);
		panelAdmEdit.add(cbEditBdayDate);
		panelAdmEdit.add(lblEditToBdayMonth);
		panelAdmEdit.add(cbEditBdayMonth);
		panelAdmEdit.add(lblEditToBdayYear);
		panelAdmEdit.add(cbEditBdayYear);
		
		//ADDING GUI-COMPONENT of ADDRESS
		panelAdmEdit.add(lblEditAddr);
		panelAdmEdit.add(lblEditNowAddr);
		panelAdmEdit.add(txtEditNowAddr);
		panelAdmEdit.add(lblEditToAddr);
		panelAdmEdit.add(txtEditToAddr);
		panelAdmEdit.add(lblEditReqAddr);
		
		//ADDING GUI-COMPONENT of MOM's NAME
		panelAdmEdit.add(lblEditMom);
		panelAdmEdit.add(lblEditNowMom);
		panelAdmEdit.add(txtEditNowMom);
		panelAdmEdit.add(lblEditToMom);
		panelAdmEdit.add(txtEditToMom);
		panelAdmEdit.add(lblEditReqMom);
		
//		panelAdmEdit.add(rbEditName);
//		panelAdmEdit.add(rbEditBday);
//		panelAdmEdit.add(rbEditAddr);
//		panelAdmEdit.add(rbEditMom);
//		panelAdmEdit.add(rbEditJob);
//		panelAdmEdit.add(rbEditRSal);
//		panelAdmEdit.add(rbEditBal);
//		panelAdmEdit.add(lblEditStat);
		
		panelAdmEdit.add(btnAdmEdit);
		panelAdmEdit.add(btnAdmEditSave);
		
		add(panelAdmEdit);
		repaint();
	}
	
	//DETERMINING LEAP YEAR
	private boolean isLeapYear(int year) {
		// TODO Auto-generated method stub
		return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == tombolShowData) {
			menuAdmChosen = "adminShow";
		} else if (e.getSource() == tombolCreateData) {
			menuAdmChosen = "adminCreate";
		} else if (e.getSource() == tombolEditData) {
			menuAdmChosen = "adminEdit";
		} else if (e.getSource() == tombolDelData) {
			menuAdmChosen = "adminDel";
		}
		
		if (e.getSource() == tombolShowMenu && menuAdmChosen.equals("adminShow")) {
			System.out.println("Show menu admin show");
			
			menuAdminGUIShow();
		} else if (e.getSource() == tombolShowMenu && menuAdmChosen.equals("adminCreate")) {
			System.out.println("Show menu admin create");
			
			menuAdminGUICreate();
		} else if (e.getSource() == tombolShowMenu && menuAdmChosen.equals("adminEdit")) {
			System.out.println("Show menu admin edit");
			
			menuAdminGUIEdit();
		} else if (e.getSource() == tombolShowMenu && menuAdmChosen.equals("adminDel")) {
			System.out.println("Show menu admin delete");
			
			menuAdminGUIDel();
		}
	}
	
	Scanner inputScan = new Scanner(System.in);
	private String input;
	private String adminChoice;
	//private String dbUserLoc = "C://Users/Lenovo/eclipse-workspace/BankAtm/dbBankUser.txt";
	//File dbUserFile = new File(dbUserLoc);
	//Scanner dbUserScan = new Scanner(dbUserFile);
	//private String barisFile;
	//LinkedList<String> dbUserTemp = new LinkedList<String>();
	Scanner dataInputScan = new Scanner(System.in);
	private String userRole, userId, userPass, userName, userDob, userAddr, userMom, userJob, userRangeSlr, userStatus;
	private String userAccNum;
	private int userAccNo, userAccBalance;
	private AtomicInteger usrAccNo;
	boolean flagEmpty, flagSpace, flagCharCaps, flagCharSpecial, flagBothLN, flagNum;
	private String checkSpace, userNameJob;
	final String letterCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final String letterSpec = "`~!@#$%^&*()_+-=[]\\\\{}|;'\\\":,./<>?";
	final String numbers = "1234567890";
	private String[] letterCapsEach = letterCaps.split("");
	private String[] letterSpecEach = letterSpec.split("");
	private String[] numbersEach = numbers.split("");
	
	private BankKuDatGUI bkd = new BankKuDatGUI();

	public BankMenuAdminGUI(String userName) throws FileNotFoundException, IOException {
		this.userName = userName;
		
		System.out.println("\nWelcome admin " + userName + "! What do you want to do today?");
		System.out.println("\n1) Check user database"
				+ "\n2) Create user data"
				+ "\n3) Edit user data"
				+ "\n4) Delete user data"
				+ "\n5) Logout");
		System.out.print("\nChoose one (in numeric): ");
		
		Scanner adminChoiceScan = new Scanner(System.in);
		adminChoice = adminChoiceScan.nextLine();

		System.out.println("Menu " + adminChoice + " is chosen");
		menuAdminInput(adminChoice);
		adminChoiceScan.close();
	}
	
	private void menuAdminInput(String adminChoice) throws FileNotFoundException, IOException {
		this.adminChoice = adminChoice;
		
		if (adminChoice.equals("1")) {
			menuAdmin1();
		} else if (adminChoice.equals("2")) {
			menuAdmin2();
		} else if (adminChoice.equals("3")) {
			menuAdmin3();
		} else if (adminChoice.equals("4")) {
			menuAdmin4();
		} else if (adminChoice.equals("5")) {
			menuAdmin5();
		} else {
			System.out.print("\nChoose right menu number!\nTry again: ");
			input = inputScan.nextLine();
			menuAdminInput(input);
			inputScan.close();
		}
	}
	
	private void menuAdmin1() throws IOException {
		System.out.println("\nUSER DATABASE\n=============");
		bkd.tampilBankKuDat();
		
		backToMainMenu();
	}
	
	private void menuAdmin2() throws FileNotFoundException, IOException {
		StringBuilder userData = new StringBuilder("");

		System.out.println("\nCREATE NEW USER\n===============");
		/*
		while (dbUserScan.hasNext()) {
			barisFile = dbUserScan.nextLine();
			dbUserTemp.add(barisFile);
		}
		*/
		//input ROLE
		System.out.print("Role (admin/cust): ");
		userRole = dataInputScan.nextLine();
		validateUserRole(userRole);
		userData.append(getUserRole());
		
		//input ACC-NO
		validateAccNo();
		userData.append(";" + getAccNo());
		
		//input USER-ID
		System.out.print("User ID (only low letters & numbers, no spacing): ");
		userId = dataInputScan.nextLine();
		validateUserId(userId);
		userData.append(";" + getUserId());
		
		//input PASSWORD
		System.out.print("Password (no space character): ");
		userPass = dataInputScan.nextLine();
		validatePassword(userPass);
		userData.append(";" + getUserPass());
		
		//input NAME
		System.out.print("Name (letters & spacing): ");
		userName = dataInputScan.nextLine();
		validateNameJob(userName);
		userData.append(";" + getUserNameJob());

		//input DATE of BIRTH
		System.out.print("Date of birth (dd/mm/yyyy): ");
		userDob = dataInputScan.nextLine();
		validateDob(userDob);
		userData.append(";" + getUserDob());
		
		//input ADDRESS
		System.out.print("Address: ");
		userAddr = dataInputScan.nextLine();
		validateAddr(userAddr);
		userData.append(";" + getUserAddr());
		
		//input MOM's NAME
		System.out.print("Mother's name (letters & spacing): ");
		userMom = dataInputScan.nextLine();
		validateNameJob(userMom);
		userData.append(";" + getUserNameJob());
		
		//input JOB
		System.out.print("Job (letters & spacing): ");
		userJob = dataInputScan.nextLine();
		validateNameJob(userJob);
		userData.append(";" + getUserNameJob());
		
		//input RANGE SALARY
		System.out.print("Range of Salary:"
				+ "\n1) 1-3 juta"
				+ "\n2) 3-7 juta"
				+ "\n3) 7-15 juta"
				+ "\n4) 15-25 juta"
				+ "\n5) > 25 juta"
				+ "\nChoose one of the above (in number): ");
		userRangeSlr = dataInputScan.nextLine();
		validateRangeSalary(userRangeSlr);
		userData.append(";" + getUserRangeSlr());
		
		//input ACCOUNT BALANCE
		System.out.print("Initial balance (numeric only & > 99999): ");
		validateAccBalance();
		userData.append(";" + getUserAccBalance());
		
		//input MEMBER STATUS
		validateStatus(getUserAccBalance());
		userData.append(";" + getUserStatus());
		
		System.out.println("\nuserData: " + userData);
		
		//ADDING NEW BANK USER
		bkd.addBankUser(userData);
		backToMainMenu();
	}
	
	private void menuAdmin3() throws IOException {
		System.out.println("\nEDIT USER DATA");
		System.out.println("==============");
		
		System.out.println("Enter userId & bank account you want to edit:");
		System.out.print("User ID: ");
		userId = inputScan.nextLine();
		System.out.print("Bank account: ");
		userAccNum = inputScan.nextLine();
		
		editUser(userId, userAccNum);
		backToMainMenu();
	}
	
	private void menuAdmin4() throws IOException {
		System.out.println("\nDELETE USER DATA");
		System.out.println("==============");
		
		System.out.println("Enter userId & bank account you want to delete!");
		System.out.print("User ID: ");
		this.userId = inputScan.nextLine();
		System.out.print("Bank account: ");
		this.userAccNum = inputScan.nextLine();
		
		deleteUser(userId, userAccNum);
		backToMainMenu();
	}
	
	private void menuAdmin5() {
		//System.out.println("\nClear screen, thx u & greeed thank you for using the apps!");
		System.out.println("\nThank you for your work today! Have a nice day ;)");
		System.exit(0);
	}
	
	private void validateUserRole(String userRole) {
		// TODO Auto-generated method stub
		
		while (!userRole.equals("admin") && !userRole.equals("cust")) {
			System.out.println("Role input: " + userRole);
			System.out.print("Try again! Role must be filled with 'admin' or 'cust'\n\nRole (admin/cust): ");
			userRole = dataInputScan.nextLine();
		}
		this.userRole = userRole;
	}
	
	private void validateAccNo() {
		bkd.getLastAccNo();
		
		int accNoLast = 0;
		if (userRole.equals("admin")) {
			bkd.setLastAccNo(1);
			accNoLast = bkd.getLastAccNo();
			
			this.usrAccNo = new AtomicInteger(accNoLast);
			this.userAccNo = usrAccNo.incrementAndGet();
		} else if (userRole.equals("cust")) {
			bkd.setLastAccNo(2);
			accNoLast = bkd.getLastAccNo();
			
			this.usrAccNo = new AtomicInteger(accNoLast);
			this.userAccNo = usrAccNo.incrementAndGet();
		}
		System.out.println("Account Number (auto generated): " + this.userAccNo);
	}
	
	private void validateUserId(String userId) throws FileNotFoundException {
		flagEmpty = true;
		flagSpace = true;
		flagCharCaps = true;
		flagCharSpecial = true;
		flagBothLN = true;
		boolean flagSameId = true;
		while (flagEmpty == true || flagSpace == true || flagCharCaps == true || flagCharSpecial == true || flagBothLN == false || flagSameId == true) {//salah 1 salah semua
			flagEmpty = true;
			flagSpace = true;
			flagCharCaps = true;
			flagCharSpecial = true;
			flagBothLN = false;
			flagSameId = true;
			String[] userIdeach = userId.split("");
			
			//check input EMPTY
			if (userId.isEmpty()) {
				flagEmpty = true; /*
				System.out.println("\nTyped user ID is: [empty]");
				System.out.println("User ID can not be empty! Please input correct user ID!");
				System.out.print("User ID (only low letters & number): ");
				userId = dataInputScan.nextLine(); */
			} else if (!userId.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input SPACING
			checkSpace = userId;
			if (!userId.equals(checkSpace.replace(" ", ""))) {
				flagSpace = true; /*
				System.out.println("\nTyped user ID is: " + checkSpace);
				System.out.println("Space character is not allowed! Please input correct user ID!");
				System.out.print("User ID (only low letters & number): ");
				userId = dataInputScan.nextLine(); */
			} else if (userId.equals(checkSpace.replace(" ", ""))) {
				flagSpace = false;
			}
			
			//CHECK input CAPS
			int flagCharCapsChecker = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < letterCapsEach.length; j++) {
					if (userIdeach[i].equals(letterCapsEach[j])) {
						flagCharCapsChecker = 1;
					}
				}
			}
			if (flagCharCapsChecker == 1) {
				flagCharCaps = true;
			} else {
				flagCharCaps = false;
			}
			
			//CHECK input SPECIAL CHAR
			int flagCharSpecialChecker = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < letterSpecEach.length; j++) {
					if (userIdeach[i].equals(letterSpecEach[j])) {
						flagCharSpecialChecker = 1;
					}
				}
			}
			if (flagCharSpecialChecker == 1) {
				flagCharSpecial = true;
			} else {
				flagCharSpecial = false;
			}
			
			//CHECK input BOTHLN
			int checkNum = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < numbersEach.length; j++) {
					if (userIdeach[i].equals(numbersEach[j])) {
						checkNum = 1;
					}
				}
			}
			int checkChar = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < letterCapsEach.length; j++) {
					if (userIdeach[i].equals(letterCapsEach[j].toLowerCase())) {
						checkChar = 1;
					}
				}
			}
			if (checkNum == 1 && checkChar == 1) {
				flagBothLN = true;
			} else {
				flagBothLN = false;
			}
			
			//CHECK input SAME ID
			boolean flagSameIdCheck;
			bkd.checkUserIdExist(userId);
			flagSameIdCheck = bkd.getCheckUserIdExist();
			
			if (flagSameIdCheck == true) {
				flagSameId = true;
			} else if (flagSameIdCheck == false) {
				flagSameId = false;
			}
			
			if (flagEmpty == true || flagSpace == true || flagCharCaps == true || flagCharSpecial == true || flagBothLN == false || flagSameId == true) {
				System.out.println();
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagSpace == true) {
					System.out.println("Detecting spacing!");
				}
				
				if (flagCharCaps == true) {
					System.out.println("Detecting capital characters!");
				}
				
				if (flagCharSpecial == true) {
					System.out.println("Detecting special characters!");
				}
				
				if (flagBothLN == false) {
					System.out.println("Detecting no letters or no numbers! checkChar = " + checkChar + ", checkNum = " + checkNum + ", flagBothLN = " + flagBothLN);
				}
				
				if (flagSameId == true) {
					System.out.println("Detecting UNAVAILABLE user Id! Try others!");
				}
				
				System.out.println("Entered user ID is: '" + userId + "'");
				//System.out.println("flagEmpty = " + flagEmpty + ", flagSpace = " + flagSpace + ", flagCharCaps = " + flagCharCaps + ", flagCharSpecial = " + flagCharSpecial);
				System.out.println("Please enter a right formatted user ID!");
				System.out.print("User ID (only low letters & numbers): ");
				userId = dataInputScan.nextLine();
			}
		}
		this.userId = userId;
	}
	
	private boolean validatePassword(String userPass) {
		passedCheckSpace = false;
		
		checkSpace = userPass;
		
		flagEmpty = true;
		flagSpace = true;
		if (flagEmpty == true || flagSpace == true) {
			flagEmpty = true;
			flagSpace = true;
			checkSpace = userPass;
			
			//CHECK input EMPTY
			if (userPass.isEmpty()) {
				flagEmpty = true;
			} else if (!userPass.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input SPACING
			checkSpace = userPass;
			if (!userPass.equals(checkSpace.replace(" ", ""))) {
				flagSpace = true;
			} else if (userPass.equals(checkSpace.replace(" ", ""))) {
				flagSpace = false;
			}
			
			if (flagEmpty == true || flagSpace == true) {
				System.out.println();
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagSpace == true) {
					System.out.println("Detecting spacing!");
				}

				System.out.println("Entered password is: '" + userPass + "'");
				System.out.println("Please enter a right formatted password!");
//				System.out.print("Password (no space character): ");
//				userPass = dataInputScan.nextLine();
				
				System.out.println("request '" + checkEditPass + "' as new pass: REJECTED");
				passedCheckSpace = false;
			} else {
				System.out.println("request '" + checkEditPass + "' as new pass: APPROVED");
				passedCheckSpace = true;
			}
		}
		this.userPass = userPass;
		return passedCheckSpace;
	}
	
	private boolean validateNameJob(String userNameJob) {
		passedCheckNameJob = false;
		
		flagCharSpecial = true;
		flagEmpty = true;
		flagNum = true;
		if (flagCharSpecial == true || flagEmpty == true || flagNum == true) {
			flagCharSpecial = true;
			flagEmpty = true;
			flagNum = true;
			
			//CHECK input EMPTY
			if (userNameJob.isEmpty()) {
				flagEmpty = true;
			} else if (!userNameJob.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input SPECIAL CHAR
			int flagCharSpecialCheck = 0;
			String[] userNameEach = userNameJob.split("");
			for (int i = 0; i < userNameEach.length; i++) {
				for (int j = 0; j < letterSpecEach.length; j++) {
					if (userNameEach[i].equals(letterSpecEach[j])) {
						flagCharSpecialCheck = 1;
					}
				}
			}
			if (flagCharSpecialCheck == 1) {
				flagCharSpecial = true;
			} else {
				flagCharSpecial = false;
			}
			
			//CHECK input NUM
			int flagNumCheck = 0;
			for (int i = 0; i < userNameEach.length; i++) {
				for (int j = 0; j < numbersEach.length; j++) {
					if (userNameEach[i].equals(numbersEach[j])) {
						flagNumCheck = 1;
					}
				}
			}
			if (flagNumCheck == 1) {
				flagNum = true;
			} else {
				flagNum = false;
			}
			
			if (flagEmpty == true || flagCharSpecial == true || flagNum == true) {
				System.out.println();
				
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagCharSpecial == true) {
					System.out.println("Detecting special characters!");
				}
				
				if (flagNum == true) {
					System.out.println("Detecting numbers characters!");
				}
				
				System.out.println("Entered user name is: '" + userNameJob + "'");
				System.out.println("FLAGS -- flagEmpty = " + flagEmpty + ", flagCharSpecial = " + flagCharSpecialCheck + ", flagNum = " + flagNum);
				System.out.println("Please enter a right formatted user name!");
//				System.out.print("Name (only letters & spacing): ");
//				userNameJob = dataInputScan.nextLine();
				
				System.out.println("request '" + checkEditName + "' as new name: REJECTED");
				passedCheckNameJob = false;
			} else {
				System.out.println("request '" + checkEditName + "' as new name: APPROVED");
				passedCheckNameJob = true;
			}
		}
		
		this.userNameJob = userNameJob;
		return passedCheckNameJob;
	}
	
	private void validateDob(String userDob) {
		// TODO Auto-generated method stub
		
		flagEmpty = true;
		boolean flagDobDd = false;
		boolean flagDobMm = false;
		boolean flagDobYyyy = false;
		boolean flagDobCorrect = false;
		boolean flagDobLength = false;
		while (flagEmpty == true || flagDobDd == false || flagDobMm == false || flagDobYyyy == false || flagDobCorrect == false || flagDobLength == false) {
			flagEmpty = true;
			flagDobDd = false;
			flagDobMm = false;
			flagDobYyyy = false;
			flagDobCorrect = false;
			flagDobLength = false;
			
			String[] userDobEach = userDob.split("");
			/*
			System.out.println();
			System.out.println("Entered date of birth is '" + userDob + "'");
			for (int i = 0; i < userDobEach.length; i++) {
				System.out.println("userDobEach[" + i + "] = " + userDobEach[i]);
			}
			*/
			
			//CHECK input EMPTY
			if (userDob.isEmpty()) {
				flagEmpty = true;
			} else if (!userDob.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input LENGTH
			if (userDob.length() == 10) {
				flagDobLength = true;
				
				//CHECK input DATE FORMAT
				//System.out.println("userDobEach[1] = " + userDobEach[1]);
				//System.out.println("userDobEach[2] = " + userDobEach[2]);
				if (userDobEach[2].equals("/") && userDobEach[5].equals("/") ) {
					flagDobCorrect = true;
					
					//CHECK input DD FORMAT
					int flagDobDd1 = 0;
					int flagDobDd2 = 0;
					for (int i = 0; i <= 1; i++) {
						for (int j = 0; j < numbersEach.length; j++) {
							if (userDobEach[0].equals(numbersEach[j])) {
								flagDobDd1 = 1;
							}
							
							if (userDobEach[1].equals(numbersEach[j])) {
								flagDobDd2 = 1;
							}
						}
					}
					if (flagDobDd1 == 1 && flagDobDd2 == 1) {
						if (Integer.parseInt(userDob.split("/")[0]) <= 31) {
							flagDobDd = true;
						}
					} else {
						flagDobDd = false;
					}
					
					//CHECK input MM FORMAT
					int flagDobMm1 = 0;
					int flagDobMm2 = 0;
					//System.out.println("userDobEach[3] = " + userDobEach[3]);
					//System.out.println("userDobEach[4] = " + userDobEach[4]);
					for (int i = 3; i <= 4; i++) {
						for (int j = 0; j < numbersEach.length; j++) {
							if (userDobEach[3].equals(numbersEach[j])) {
								flagDobMm1 = 1;
							}
							
							if (userDobEach[4].equals(numbersEach[j])) {
								flagDobMm2 = 1;
							}
						}
					}
					if (flagDobMm1 == 1 && flagDobMm2 == 1) {
						if (Integer.parseInt(userDob.split("/")[1]) <= 12) {
							flagDobMm = true;
						}
					} else {
						flagDobMm = false;
					}
					
					//CHECK input YYYY FORMAT
					int flagDobYy1 = 0;
					int flagDobYy2 = 0;
					int flagDobYy3 = 0;
					int flagDobYy4 = 0;
					for (int i = 6; i <= 9; i++) {
						for (int j = 0; j < numbersEach.length; j++) {
							if (userDobEach[6].equals(numbersEach[j])) {
								flagDobYy1 = 1;
							}
							
							if (userDobEach[7].equals(numbersEach[j])) {
								flagDobYy2 = 1;
							}
							
							if (userDobEach[8].equals(numbersEach[j])) {
								flagDobYy3 = 1;
							}
							
							if (userDobEach[9].equals(numbersEach[j])) {
								flagDobYy4 = 1;
							}
						}
					}
					if (flagDobYy1 == 1 && flagDobYy2 == 1 && flagDobYy3 == 1 && flagDobYy4 == 1) {
						flagDobYyyy = true;
					} else {
						flagDobYyyy = false;
					}
				} else {
					flagDobCorrect = false;
				}
			} else {
				flagDobLength = false;
			}
			
			//System.out.println("/// KELUAR ///");
			if (flagEmpty == true || flagDobDd == false || flagDobMm == false || flagDobYyyy == false || flagDobCorrect == false || flagDobLength == false) {
				System.out.println();
				
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagDobDd == false || flagDobMm == false || flagDobYyyy == false || flagDobCorrect == false || flagDobLength == false) {
					System.out.println("Wrong input characters!");
				}
				
				System.out.println("Entered date of birth is '" + userDob + "'");
				//System.out.println("flagEmpty = " + flagEmpty + ", flagDobDd = " + flagDobDd + ", flagDobMm = " + flagDobMm + ", flagDobYyyy = " + flagDobYyyy + ", flagDbCorrect = " + flagDobCorrect + ", flagDobLength = " + flagDobLength);
				System.out.println("Please enter a correct formatted date of birth!");
				System.out.print("Birth of Date (in format 'dd/mm/yyyy'): ");
				userDob = dataInputScan.nextLine();
			}
		}
		
		this.userDob = userDob;
	}
	
	private boolean validateAddr(String userAddr) {
		// TODO Auto-generated method stub
		passedCheckAddr = false;
		
		flagEmpty = true;
		if (flagEmpty == true) {
			flagEmpty = true;
			
			if (userAddr.isEmpty()) {
				flagEmpty = true;
			} else if (!userAddr.isEmpty()) {
				flagEmpty = false;
			}
			
			if (flagEmpty == true) {
				System.out.println("\nDetecting empty input!");
				
				System.out.println("Entered address is: '" + userAddr + "'");
				System.out.println("Please enter a correct formatted address!");
//				System.out.print("Address: ");
//				userAddr = dataInputScan.nextLine();
				
				System.out.println("request '" + checkEditAddr + "' as new address: REJECTED");
				passedCheckAddr = false;
			} else {
				System.out.println("request '" + checkEditAddr + "' as new address: APPROVED");
				passedCheckAddr = true;
			}
		}
		
		this.userAddr = userAddr;
		return passedCheckAddr;
	}
	
	private void validateRangeSalary(String userRangeSlr) {
		// TODO Auto-generated method stub
		
		flagEmpty = true;
		boolean flagRangeSlr = false;
		while (flagRangeSlr == false || flagEmpty == true) {
			flagEmpty = true;
			flagRangeSlr = false;
			
			//CHECK input EMPTY
			if (userRangeSlr.isEmpty()) {
				flagEmpty = true;
			} else if (!userRangeSlr.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input MATCH CHOICE
			if (userRangeSlr.equals("1")) {
				userRangeSlr = "1-3 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("2")) {
				userRangeSlr = "3-7 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("3")) {
				userRangeSlr = "7-15 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("4")) {
				userRangeSlr = "15-25 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("5")) {
				userRangeSlr = "> 25 juta";
				flagRangeSlr = true;
			} else {
				flagRangeSlr = false;
			}
			
			if (flagRangeSlr == false || flagEmpty == true) {
				System.out.println();
				
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagRangeSlr == false) {
					System.out.println("Wrong choice input!");
				}
				
				//System.out.println("flagRangeSlr = " + flagRangeSlr + ", flagEmpty = " + flagEmpty);
				System.out.println("Entered menu choice  is '" + userRangeSlr + "'");
				System.out.println("Please enter correct choice of number!");
				System.out.print("Range of Salary:"
					+ "\n1) 1-3 juta"
					+ "\n2) 3-7 juta"
					+ "\n3) 7-15 juta"
					+ "\n4) 15-25 juta"
					+ "\n5) > 25 juta"
					+ "\nChoose one of the above (in number): ");
				userRangeSlr = dataInputScan.nextLine();
			}
		}
		
		this.userRangeSlr = userRangeSlr;
	}
	
	private void validateAccBalance() {
		boolean flagUserBalance = false;
		while (flagUserBalance == false) {
			flagUserBalance = false;
			
			try {
				this.userAccBalance = dataInputScan.nextInt();
				
				//CHECK input EMPTY
				if (userAccBalance < 100000) {
					flagUserBalance = false;
					
					System.out.println();
					System.out.println("flagUserBalance = " + flagUserBalance);
					System.out.println("Entered balance is '" + this.userAccBalance + "'");
					System.out.println("Input must be greater than '100000'");
					System.out.println("Please enter a correct formatted balance!");
					System.out.print("Initial balance (numeric only & > 99999): ");
				} else if (userAccBalance >= 100000) {
					flagUserBalance = true;
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("flagUserBalance = " + flagUserBalance);
				System.out.println("Entered balance is '" + this.userAccBalance + "'");
				System.out.println("Input must be greater than '100000'");
				System.out.println("Please enter a correct formatted balance!");
				System.out.print("Initial balance (numbers only): ");
				dataInputScan.next();
				continue;
			}
		}
	}
	
	private void validateStatus(int userAccBalance) {
		// TODO Auto-generated method stub
		
		if (userAccBalance <= 7000000 ) {
			userStatus = "silver";
		} else if (userAccBalance > 7000000 && userAccBalance <= 25000000) {
			userStatus = "gold";
		} else if (userAccBalance > 25000000) {
			userStatus = "platinum";
		}

		System.out.println("Member status (auto generate): " + userStatus);
	}

	public String getUserRole() {
		return userRole;
	}
	
	public int getAccNo() {

		return this.userAccNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public String getUserNameJob() {
		return userNameJob;
	}
	
	public String getUserDob() {
		return userDob;
	}
	
	public String getUserAddr() {
		return userAddr;
	}
	
	public String getUserRangeSlr() {
		return userRangeSlr;
	}
	
	public int getUserAccBalance() {
		return userAccBalance;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	
	private void editUserGUI(String userId, String userAccNum) throws FileNotFoundException {
		// TODO Auto-generated method stub
		this.userId = userId;
		this.userAccNum = userAccNum;
		
		boolean flagUserIdMatchAccNo = false;
		bkd.matchUserIdAccNo(userId, userAccNum);
		flagUserIdMatchAccNo = bkd.getMatchUserIdAccNo();
		
		boolean menuEditAvail = false;
		String[] dbUserTempLineFragment = null;
		
		if (flagUserIdMatchAccNo == false) {
			System.out.println("No match found! Your inputs are userId: '" + userId + "', bankAcc: '" + userAccNum + "'");
			System.out.println("Please enter correct credentials!");
			
			JOptionPane.showMessageDialog(null, "User ID & Account No doesn't match any data\nTry again!", "Alert Data Matching", JOptionPane.WARNING_MESSAGE);
			
			menuAdminGUIEdit();
		} else if (flagUserIdMatchAccNo == true) {
			System.out.println("User ID & Account No match!");
			
			JOptionPane.showMessageDialog(null, "User ID & Account No match!", "Info Data Matching", JOptionPane.PLAIN_MESSAGE);
			
			menuAdminGUIEditChoose();
		}
	}
	
	private void editUser(String userId, String userAccNum) throws FileNotFoundException {
		// TODO Auto-generated method stub
		this.userId = userId;
		this.userAccNum = userAccNum;

		String[] dbUserTempLineFragment = null;
		/*
		dbUserScan = new Scanner(dbUserFile);
		
		while (dbUserScan.hasNext()) {
			dbUserTemp.add(dbUserScan.nextLine());
		}
*/
		boolean flagUserMatch = false;
		boolean editUserCheck = false;
		boolean menuEditAvail = false;
		int catchIndex = 0;
		
		while (flagUserMatch == false) {
			bkd.matchUserIdAccNo(userId, userAccNum);
			editUserCheck = bkd.getMatchUserIdAccNo();
			
			if (editUserCheck == true) {
				System.out.println("\nUser match found!");
				catchIndex = bkd.getMatchUserIdAccNoIndex();
			}
			
			if (editUserCheck == true) {
				flagUserMatch = true;
			} else if (editUserCheck == false) {
				flagUserMatch = false;
			}
			
			if (flagUserMatch == true) {
				while (menuEditAvail == false) {
					dbUserTempLineFragment = bkd.getBankUser().toString().split(";"); //dbUserTemp.get(catchIndex).split(";");
					
					System.out.println("1) Role: " + bkd.getUserRole() +
							"\n2) Bank account: " + bkd.getUserAccNo() + " *---UNEDITABLE---" +
							"\n3) User ID: " + bkd.getUserId() + " *---UNEDITABLE---" +
							"\n4) Password: " + bkd.getUserPass() +
							"\n5) Name: " + bkd.getUserName() +
							"\n6) Birthday: " + bkd.getUserDob() +
							"\n7) Address: " + bkd.getUserAddr() +
							"\n8) Mother's name: " + bkd.getUserMom() +
							"\n9) Occupation: " + bkd.getUserJob() +
							"\n10) Range salary: " + bkd.getUserSalaryRange() +
							"\n11) Balance: Rp. " + bkd.getUserBalance() +
							"\n12) Member status: " + bkd.getUserStatus() + " *---UNEDITABLE---");
					
					System.out.print("\nInput number menu of data you want to alter (1-12): ");
					String menuInput3Edit = inputScan.nextLine();
					
					if (menuInput3Edit.equals("1")) {
						System.out.println("\nAlter Role");
						System.out.println("==========");
						//System.out.println("BEFORE: " + dbUserTemp.get(catchIndex));
						System.out.println("Old role: " + bkd.getUserRole());
						System.out.print("New role (admin/cust): ");
						userRole = dataInputScan.nextLine();
						
						validateUserRole(userRole);
						bkd.setUserRole(getUserRole()); //dbUserTempLineFragment[0] = getUserRole();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("4")) {
						System.out.println("\nALTER PASSWORD");
						System.out.println("==============");
						System.out.println("Old password: " + bkd.getUserPass());
						System.out.print("New password (no spacing): ");
						userPass = dataInputScan.nextLine();
						
						validatePassword(userPass);
						bkd.setUserPass(getUserPass()); //dbUserTempLineFragment[3] = getUserPass();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("5")) {
						System.out.println("\nAlter Name");
						System.out.println("==========");
						System.out.println("Old name: " + bkd.getUserName());
						System.out.print("New name (only letters & spacing): " );
						userName = dataInputScan.nextLine();
						
						validateNameJob(userName);
						bkd.setUserJob(getUserNameJob()); //dbUserTempLineFragment[4] = getUserNameJob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("6")) {
						System.out.println("\nALTER BIRTHDAY");
						System.out.println("==============");
						System.out.println("Old birthday: " + bkd.getUserDob());
						System.out.print("New birthday: ");
						userDob = dataInputScan.nextLine();
						
						validateDob(userDob);
						bkd.setUserDob(getUserDob()); //dbUserTempLineFragment[5] = getUserDob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("7")) {
						System.out.println("\nALTER ADDRESS");
						System.out.println("=============");
						System.out.println("Old address: " + bkd.getUserAddr());
						System.out.print("New address: ");
						userAddr = dataInputScan.nextLine();
						
						validateAddr(userAddr);
						bkd.setUserAddr(getUserAddr()); //dbUserTempLineFragment[6] = getUserAddr();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("8")) {
						System.out.println("\nALTER MOTHER's NAME");
						System.out.println("===================");
						System.out.println("Old mother's name: " + bkd.getUserMom());
						System.out.print("New mother's name (only letters & spacing): ");
						userMom = dataInputScan.nextLine();
						
						validateNameJob(userMom);
						bkd.setUserMom(getUserNameJob()); //dbUserTempLineFragment[7] = getUserNameJob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("9")) {
						System.out.println("\nALTER OCCUPATION");
						System.out.println("================");
						System.out.println("Old occupation: " + bkd.getUserJob());
						System.out.print("New occupation (only letters & spacing): ");
						userJob = dataInputScan.nextLine();
						
						validateNameJob(userJob);
						bkd.setUserJob(getUserNameJob()); //dbUserTempLineFragment[8] = getUserNameJob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("10")) {
						System.out.println("\nALTER SALARY RANGE");
						System.out.println("==================");
						System.out.println("Old salary range: " + bkd.getUserSalaryRange());
						System.out.print("New salary range:"
								+ "\n1) 1-3 juta"
								+ "\n2) 3-7 juta"
								+ "\n3) 7-15 juta"
								+ "\n4) 15-25 juta"
								+ "\n5) > 25 juta"
								+ "\nChoose one of the above (in number): ");
						userRangeSlr = dataInputScan.nextLine();
						
						validateRangeSalary(userRangeSlr);
						bkd.setUserSalaryRange(getUserRangeSlr()); //dbUserTempLineFragment[9] = getUserRangeSlr();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("11")) {
						System.out.println("\nALTER BALANCE VALUE");
						System.out.println("===================");
						System.out.println("Old balance: " + bkd.getUserBalance());
						System.out.print("New balance: ");
						
						validateAccBalance();
						bkd.setUserBalance(Integer.toString(getUserAccBalance())); //dbUserTempLineFragment[10] = Integer.toString(getUserAccBalance());
						validateStatus(getUserAccBalance());
						bkd.setUserStatus(getUserStatus()); //dbUserTempLineFragment[11] = getUserStatus();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("2") || menuInput3Edit.equals("3") || menuInput3Edit.equals("12")) {
						menuEditAvail = false;
						System.out.print("This choice menu '" + menuInput3Edit + "' is uneditable!\nChoose another edit choice menu: ");
					} else {
						menuEditAvail = false;
						System.out.print("This choice menu '" + menuInput3Edit + "' is not available!\nPlease input correct edit choice menu: ");
					}
				}
			}
			else if (flagUserMatch == false) {
				JOptionPane.showMessageDialog(null, "User ID & Account No doesn't match any data\nTry again!", "Alert Data Matching", JOptionPane.WARNING_MESSAGE);
				System.out.println("\nNo match found! Your inputs are userId: '" + userId + "', bankAcc: '" + userAccNum + "'");
				System.out.println("Please enter correct credentials!");
				menuAdminGUIEdit();
//				System.out.print("User ID: ");
//				userId = inputScan.nextLine();
//				System.out.print("Bank account: ");
//				userAccNum = inputScan.nextLine();
			}
		}

		System.out.print("BEFORE SET: ");
		for (int i = 0; i < dbUserTempLineFragment.length; i++) {
			System.out.print(dbUserTempLineFragment[i] + ";");
		}
		System.out.println("\nAFTER SET: " + bkd.getBankUser());
		bkd.editBankUser(bkd.getBankUser().toString());
		System.out.println("Editing file is done!");
	}
	
	private void deleteUser(String userId, String userAccNum) throws FileNotFoundException {
		System.out.println("\nInside deleteUser()");
		this.userId = userId;
		this.userAccNum = userAccNum;

		String[] dbUserTempLineFragment = null;
		/*
		dbUserScan = new Scanner(dbUserFile);
		
		while (dbUserScan.hasNext()) {
			dbUserTemp.add(dbUserScan.nextLine());
		}
*/
		boolean flagUserMatch = false;
		boolean deleteUserCheck = false;
		int catchIndex = 0;

		while (flagUserMatch == false) {
			bkd.matchUserIdAccNo(userId, userAccNum);
			deleteUserCheck = bkd.getMatchUserIdAccNo();
			
			if (deleteUserCheck == true) {
				System.out.println("\nUser match found!");
				System.out.println("Deleting user with user ID:" + userId + " & acc no:" + userAccNum);
				catchIndex = bkd.getMatchUserIdAccNoIndex();
			}
			
			if (deleteUserCheck == true) {
				flagUserMatch = true;
			} else if (deleteUserCheck == false) {
				flagUserMatch = false;
			}
			
			if (flagUserMatch == true) {
				bkd.deleteBankUser(bkd.getBankUser().toString());
			} else if (flagUserMatch == false) {
				System.out.println("\nNo match found!");
				System.out.println("Your input is: user id = '" + userId + "', user account number = '" + userAccNum + "'");
				System.out.println("Please enter correct credentials:");
				System.out.print("User ID: ");
				userId = inputScan.nextLine();
				System.out.print("Bank account: ");
				userAccNum = inputScan.nextLine();
			}
		}
		System.out.println("DELETING SUCCEED!");
	}
	
	private void backToMainMenu() throws FileNotFoundException, IOException {
		boolean getMainMenu = false;
		
		while (getMainMenu == false) {
			System.out.print("\n((nPress 'Enter' to get back to main menu))");
			input = inputScan.nextLine();
			
			if (input.equals("")) {
				getMainMenu = true;
			} else {
				getMainMenu = false;
				System.out.println("Your input is '" + input + "'. Try again!");
			}
		}
		new BankMenuAdminGUI(userName);
	}
}
