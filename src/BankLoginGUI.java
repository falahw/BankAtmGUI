import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BankLoginGUI extends JFrame implements ActionListener {
	
	private JLabel labelId, labelPass;
	private JTextField txfId;
	private JPasswordField txfPass;
	private JButton tombolLogin;
	private JPanel panelLogin;
	private String inputID, inputPass;
	
	public BankLoginGUI() {
		// TODO Auto-generated constructor stub
		setTitle("BankGUI Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setBackground(Color.lightGray);
		//setLayout(new FlowLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		
		/* CREATE JLabel() OBJECT for ID & Password */
		labelId = new JLabel();
		labelId.setText("User ID");
		labelId.setBounds(150, 150, 75, 30);
		labelId.setHorizontalAlignment(JLabel.CENTER);
		//labelId.setVerticalAlignment(JLabel.CENTER);
		labelId.setOpaque(true);

		labelPass = new JLabel();
		labelPass.setText("Password");
		labelPass.setBounds(150, 190, 75, 30);
		labelPass.setHorizontalAlignment(JLabel.CENTER);
		labelPass.setOpaque(true);
		
		txfId = new JTextField();
		txfId.setPreferredSize(new Dimension(200, 30));
		txfId.setBounds(230, 150, 100, 30);
		
		txfPass = new JPasswordField();
		txfPass.setPreferredSize(new Dimension(200, 30));
		txfPass.setBounds(230, 190, 100, 30);
		//txfPass.setBackground(Color.black);

		tombolLogin = new JButton();
		tombolLogin.setBounds(150, 230, 175, 30);
		tombolLogin.setText("LOGIN");
		tombolLogin.addActionListener(this);
		
		panelLogin = new JPanel();
		//panelLogin.setBackground(Color.black);
		//panelLogin.setBounds(10, 10, 200, 50);
		panelLogin.setLayout(null);

		panelLogin.add(labelId);
		panelLogin.add(txfId);
		panelLogin.add(labelPass);
		panelLogin.add(txfPass);
		panelLogin.add(tombolLogin);
		
		add(panelLogin);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus;
		Boolean matchLogin;
		Scanner userInputScan = new Scanner(System.in);
		BankKuDatGUI bkd;
		
		try {
			bkd = new BankKuDatGUI();
			bkd.tampilBankKuDat();
			System.out.println();
			
			if (e.getSource() == tombolLogin) {
				inputID = txfId.getText();
				inputPass = txfPass.getText();
				
				System.out.println("Try to LOGIN! AS id: " + inputID + " & pass: " + inputPass);
				//System.out.println("e = " + e);
				
				bkd.setUserId(inputID);
				bkd.setUserPass(inputPass);
				bkd.checkLogin();
				
				matchLogin = bkd.getCheckLogin();
				if (matchLogin == false) {
					System.out.println("Login doesn't match any data. Try again!\n");
					JOptionPane.showMessageDialog(null, "Login does not match any data. Try again!", "Login Info", JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println("Login success!");
					
					JOptionPane.showMessageDialog(null, "Login Success!", "Login Info", JOptionPane.PLAIN_MESSAGE);
					
					if (bkd.getUserRole().equals("admin")) {
						System.out.println("Ask to choose");
						
						String[] response = {"Admin", "Customer"};
						int roleChosen = JOptionPane.showOptionDialog(null,
								"You are an admin\nWhich login role do you want to use",
								"Login Role",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								response,
								0);
						
						System.out.println("\nChosen role: " + roleChosen);
						
						if (roleChosen == 0) {
							System.out.println("Login as admin");
							this.dispose();
							new BankMenuAdminGUI();
						}
						else if (roleChosen == 1) {
							System.out.println("Login as admin-customer");
							this.dispose();
							new BankMenuCustGUI();
						}
					}
					else if (bkd.getUserRole().equals("cust")) {
						System.out.println("Login as customer");
						this.dispose();
						new BankMenuCustGUI();
					}
				}
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
//	private void checkUser(String dbUserLocation) throws FileNotFoundException, IOException {
//		String userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus;
//		BankKuDatGUI bkd = new BankKuDatGUI();
//		String userInput;
//		Scanner userInputScan = new Scanner(System.in);
//		
//		/// CHECKING VALIDITY if USER-ID and PASSWORD are VALID or NOT ///
//		boolean matchLogin = false;
//		while (matchLogin == false) {
//			/// MATCHING UP User-ID & Password ///
////			System.out.print("User ID: "); //asking User-id --> commented by GUI Addition
//			userInput = inputID; //GUI Addition
//			bkd.setUserId(userInput); //(userInputScan.nextLine());
//			/*
//			try {
//				userInput = passwordInput();
//				bkd.setUserPass(userInput);
//			} catch (Exception e) {
//				System.out.print("Password: "); //asking Password
//				bkd.setUserPass(userInputScan.nextLine());
//			}
//			--> commented by GUI Addition
//			*/
//			userInput = inputPass; //GUI Addition
//			bkd.setUserPass(userInput); //GUI Addition
//			
//			bkd.checkLogin(); //VALIDATE entered USER-ID and PASSWORD
//			matchLogin = bkd.getCheckLogin(); //get BOOLEAN VALUE of 'matchLogin'
//			
//			if (matchLogin == false) {
//				System.out.println("\nLogin doesn't match any data. Try again! ");
//				JOptionPane.showMessageDialog(null, "Login does not match any data. Try again!", "Login Failed", JOptionPane.ERROR_MESSAGE);
//			}
//		}
//		System.out.println("\nLogin success!");
//		
//		/// SETTING ALL USER's DATA ///
//		userRole = bkd.getUserRole();
//		userAccNo = bkd.getUserAccNo();
//		userId = bkd.getUserId();
//		userPass = bkd.getUserPass();
//		userName = bkd.getUserName();
//		userDob = bkd.getUserDob();
//		userAddr = bkd.getUserAddr();
//		userMom = bkd.getUserMom();
//		userJob = bkd.getUserJob();
//		userSalaryRange= bkd.getUserSalaryRange();
//		userBalance = bkd.getUserBalance();
//		userStatus = bkd.getUserStatus();
//		
//		/// CHECKING USER's ROLE ///
//		boolean correctRole = false;
//		if (bkd.getUserRole().equals("admin")) {
//			System.out.print("Which login role do you want choose: ");
//			
//			while (correctRole == false) {
//				System.out.print("\n1) Admin"
//						+ "\n2) Customer"
//						+ "\n3) Logout"
//						+ "\nChoose one (1 or 2, & 3 for logout): ");
//				userInput = userInputScan.nextLine();
//				
//				if (userInput.equals("1")) {
//					correctRole = true;
//					new BankMenuAdminGUI(userName);
//				} else if (userInput.equals("2")) {
//					correctRole = true;
//
//					new BankMenuCustGUI(userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus);
//				} else if (userInput.equals("3")) {
//					System.out.println("\nThank you for using this app! Have a nice day ;)");
//					System.exit(0);
//				} else {
//					System.out.println("\nWrong input '" + userInput + "'!");
//					System.out.println("Please enter correct formatted input choice (1 or 2)");
//				}
//			}
////			userInputScan.close(); --> commented by GUI Addition
//		} else if (bkd.getUserRole().equals("cust")) {
//			new BankMenuCustGUI(userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus);
//		}
//	}
//	
//	private String passwordInput() {
//		Console konsol = System.console();
//		
//		if (konsol == null) {
//			System.out.println("PASSWORD will be SHOWN! (Can NOT DETECT console/terminal)");
//			System.out.println("-- Try to run in OS windows --");
//		}
//		
//		char[] passArray = konsol.readPassword("Password: ");
//				
//		return new String(passArray);
//	}
}
