import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class BankMenuCustGUI extends JFrame {

	public BankMenuCustGUI() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bank Menu Customer GUI");
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	/*
	private String custChoice, transDesNo, transAmount, transRemark;
	private String userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus;
	boolean inputChoice = false;
	Scanner inputScan = new Scanner(System.in);
	private BankKuDatGUI bkd = new BankKuDatGUI();

	public BankMenuCustGUI(String userRole, String userAccNo, String userId, String userPass, String userName, String userDob, String userAddr, String userMom, String userJob, String userSalaryRange, String userBalance, String userStatus) throws IOException {

		this.userRole = userRole;
		this.userAccNo = userAccNo;
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userDob = userDob;
		this.userAddr = userAddr;
		this.userMom = userMom;
		this.userJob = userJob;
		this.userSalaryRange = userSalaryRange;
		this.userBalance = userBalance;
		this.userStatus = userStatus;

		System.out.println("Welcome customer " + userName + "! What do you want to do today?");
		
		while (inputChoice == false) {
			System.out.println("\n1) Check account profile"
					+ "\n2) Check balance"
					+ "\n3) Transfer"
					+ "\n4) Check transaction record"
					+ "\n5) Logout");
			System.out.print("\nChoose one from 1-4 (in numeric): ");
			
			custChoice = inputScan.nextLine();
			System.out.println("Menu " + custChoice + " is chosen\n");
			
			if (custChoice.equals("1")) {
				inputChoice = true;
				menuCust1();
			} else if (custChoice.equals("2")) {
				inputChoice = true;
				menuCust2();
			} else if (custChoice.equals("3")) {
				inputChoice = true;
				menuCust3();
			} else if (custChoice.equals("4")) {
				inputChoice = true;
				menuCust4();
			} else if (custChoice.equals("5")) {
				inputChoice = true;
				menuCust5();
			} else {
				inputChoice = false;
				System.out.println("\nWrong input '" + custChoice + "'!");
				System.out.println("Please enter correct formatted menu choice (1-4)");
			}
		}
	}
	
	private void menuCust1() throws IOException {
		System.out.println(userName + "'s Credetials:");
		System.out.println("1) Account No: " + userAccNo
				+ "\n2) User ID: " + userId
				+ "\n3) Password: " + userPass
				+ "\n4) Name: " + userName
				+ "\n5) Date of birth: " + userDob
				+ "\n6) Address: " + userAddr
				+ "\n7) Mother's name: " + userMom
				+ "\n8) Occupation: " + userJob
				+ "\n9) Salary range: " + userSalaryRange
				+ "\n10) Balance: " + userBalance
				+ "\n11) Membership status: " + userStatus);
		
		backToMainMenu();
	}
	
	private void menuCust2() throws IOException {
		System.out.println("User balance: Rp. " + userBalance);
		backToMainMenu();
	}
	
	private void menuCust3() throws IOException {
		inputChoice = false;
		
		while (inputChoice == false) {
			System.out.println("Transfer type:"
					+ "\n1) Same Bank"
					+ "\n2) Domestic");
			System.out.print("Choose one (1 or 2): ");
			custChoice = inputScan.nextLine();
			
			if (custChoice.equals("1")) {
				inputChoice = true;
				transferSameBank();
			} else if (custChoice.equals("2")) {
				inputChoice = true;
				transferDomestic();
			} else {
				inputChoice = false;
				System.out.println("\nWrong input: '" + custChoice + "'");
				System.out.println("Please enter correct input!");
			}
		}
		
		backToMainMenu();
	}
	
	private void menuCust4() throws IOException {
		System.out.println("USER TRANSACTION RECORD");
		System.out.println("=======================");
		bkd.userTrxRecord(userId, userAccNo);
		backToMainMenu();
	}
	
	private void menuCust5() {
		System.out.println("menuCust5");
		System.out.println("\nThank you for your work today! Have a nive day ;)");
		inputScan.close();
		System.exit(0);
	}
	
	private void transferSameBank() throws IOException {
		boolean correctData = false;
		
		System.out.println("\nSAME BANK TRANSFER");
		System.out.println("------------------");
		
		while (correctData == false) {
			System.out.print("Destination account number: ");
			transDesNo = inputScan.nextLine();
			validateBankAcc(transDesNo);
			
			System.out.print("Transfer amount: ");
			transAmount = inputScan.nextLine();
			validateBankAmount(transAmount);
			
			System.out.print("Transfer notes: ");
			transRemark = inputScan.nextLine();
			validateRemark(transRemark);
			
			System.out.println("\nYour transfer request are:"
					+ "\n- Destination account: " + getDesBankAcc()
					+ "\n- Transfer amount: " + getBankAmount()
					+ "\n- Transfer notes: " + getRemark());
			
			boolean transConfirm = false;
			
			while (transConfirm == false) {
				System.out.print("Is this transfer correct (y/n)? ");
				custChoice = inputScan.nextLine().toLowerCase();
				
				if (custChoice.equals("y")) {
					correctData = true;
					transConfirm = true;
					bkd.recordTransSameBank(userAccNo, getDesBankAcc(), getBankAmount(), getRemark());
					System.out.println("\nTransfer completed!");
				}
				else if (custChoice.equals("n")) {
					correctData = false;
					transConfirm = true;
					System.out.println("\nRe-input your data");
				}
				else {
					correctData = false;
					transConfirm = false;
					System.out.println("\nWrong input '" + custChoice + "'!");
					System.out.println("Input must be 'y' or 'n'\n");
				}
			}
		}
	}
	
	private void transferDomestic() {
		System.out.println("\nDomestic transfer is proceed here");
	}
	
	private void validateBankAcc(String transDesNo) {
		boolean accExist = false;
		
		while (accExist == false) {
			bkd.checkAccExist(transDesNo);
			
			if (bkd.getAccExist() == true) {
				accExist = true;
				//System.out.println("Account " + accNo + " does EXIST!");
			}
			else if (bkd.getAccExist() == false) {
				accExist = false;
				System.out.println("\nAccount '" + transDesNo + "' does NOT EXIST!\nPlease try again.");
				System.out.print("Destination account number: ");
				transDesNo = inputScan.nextLine();
				this.transDesNo = transDesNo;
			}
		}
	}
	
	private void validateBankAmount(String transAmount) {
		int amountCheck = 0;
		boolean bankAmount = false;
		
		while (bankAmount == false) {
			try {
				amountCheck = Integer.parseInt(transAmount);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			if (amountCheck == 0) {
				bankAmount = false;
				System.out.println("\nWrong input '" + transAmount + "'!");
				System.out.println("Only NUMERIC is allowed. Please try again.");
				System.out.print("Transfer amount: ");
				transAmount = inputScan.nextLine();
			}
			else if (amountCheck == Integer.parseInt(transAmount)) {
				if (amountCheck < 10000) {
					bankAmount = false;
					System.out.println("\nTransfer amount too small '" + amountCheck + "'!");
					System.out.println("Transfer amount must be bigger than Rp. 10.000,-. Please try again.");
					System.out.print("Transfer amount: ");
					transAmount = inputScan.nextLine();
				}
				else {
					bankAmount = true;
					//System.out.println("\nYour transfer amount: " + amountCheck);
					this.transAmount = Integer.toString(amountCheck);
				}
			}
		}
	}
	
	private void validateRemark(String transRemark) {
		this.transRemark = transRemark;
	}
	
	private String getDesBankAcc() {
		return transDesNo;
	}
	
	private String getBankAmount() {
		return transAmount;
	}
	
	private String getRemark() {
		return transRemark;
	}
	
	private void backToMainMenu() throws IOException {
		String input;
		boolean getMainMenu = false;
		
		while (getMainMenu == false) {
			System.out.print("\n((Press 'Enter' to get back to main menu))");
			input = inputScan.nextLine();
			
			if (input.equals("")) {
				getMainMenu = true;
			} else {
				getMainMenu = false;
				System.out.println("Your input is '" + input + "'. Try again!");
			}
		}
		new BankMenuCustGUI(userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus);
	}
	*/
}
