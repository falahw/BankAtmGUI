import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BankKuDatGUI {

	private String kuDatLoc = "dbbank/dbBankUser.txt";
	private File kuDatFile = new File(kuDatLoc);
	private File kuDatdel;
	private String readLines, desName, newBalance;
	private StringBuilder bankUser;
	//String[] potong = readLines.split
	private static String userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus;
	private boolean userIdExist, accExist;
	private File dbFile = new File(kuDatLoc);
	LinkedList<String> kuDatTemp = new LinkedList<String>();
	LinkedList<String> dbTempLog = new LinkedList<String>();
	private String[] split;
	private boolean matchLogin = false;
	private boolean matchRole = false;
	private boolean matchEdit = false;
	private String timeStamp = new SimpleDateFormat("yyyy/MM/dd;HH:mm:ss").format(new java.util.Date());
	private String sequenceHeadGen = new SimpleDateFormat("yyyy").format(new java.util.Date());
	private String sequenceHead = sequenceHeadGen;
	private int lastAccNo, sequenceId, matchUserIdAccNoIndex;
	
	private int spRole, spAccNo, spId, spPass, spName, spDob, spAddr, spMom, spJob, spSalRan, spBal, spStat;
	private int spacRole, spacAccNo, spacId, spacPass, spacName, spacDob, spacAddr, spacMom, spacJob, spacSalRan, spacBal, spacStat;
	
	/// BankKudDat() CONSTRUCTOR ///
	public BankKuDatGUI() throws FileNotFoundException {
		/*
		try (BufferedReader br = new BufferedReader(new FileReader(dbFile))) {
			while ((readLines = br.readLine()) != null) {
				System.out.println("rl = " + readLines);
				kuDatTemp.add(readLines);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("dbTemp = " + dbTemp + " --> GAK ADA ISINYA COYY!!");
		}
		*/
		Scanner fileScanner = new Scanner(kuDatFile);
		while (fileScanner.hasNext()) {
			readLines = fileScanner.nextLine();
			this.kuDatTemp.add(readLines);
		}
		fileScanner.close();
	}
	
	public String getKuDatLoc() {
		return kuDatLoc;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserAccNo(String userAccNo) {
		this.userAccNo = userAccNo;
	}
	
	public String getUserAccNo() {
		return userAccNo;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}
	
	public String getUserDob() {
		return userDob;
	}
	
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	
	public String getUserAddr() {
		return userAddr;
	}
	
	public void setUserMom(String userMom) {
		this.userMom = userMom;
	}
	
	public String getUserMom() {
		return userMom;
	}
	
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	
	public String getUserJob() {
		return userJob;
	}
	
	public void setUserSalaryRange(String userSalaryRange) {
		this.userSalaryRange = userSalaryRange;
	}
	
	public String getUserSalaryRange() {
		return userSalaryRange;
	}
	
	public void setUserBalance(String userBalance) {
		this.userBalance = userBalance;
	}
	
	public String getUserBalance() {
		return userBalance;
	}
	
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	
	//SERVING BankLogin.java in checkUser()
	public void checkLogin() {
		//CHECKING whether there is any MATCHING USER-ID & PASSWORD or not
		for (Iterator iterator = kuDatTemp.iterator(); iterator.hasNext() && matchLogin == false;) {
			String string = (String) iterator.next();
			split = string.split(";");
			//SETTING-UP all USER DATA
			if (userId.equals(split[2]) && userPass.equals(split[3])) {
				this.matchLogin = true;
				
				this.userRole = split[0];
				this.userAccNo = split[1];
				this.userName = split[4];
				this.userDob = split[5];
				this.userAddr = split[6];
				this.userMom = split[7];
				this.userJob = split[8];
				this.userSalaryRange = split[9];
				this.userBalance = split[10];
				this.userStatus = split[11];
				
				break;
			} else {
				this.matchLogin = false;
			}
		}
	}
	
	public boolean getCheckLogin() {
		return matchLogin;
	}
	
	//SERVING BankMenuAdmin.java in menuAdmin2()
	public void addBankUser(StringBuilder newBankUser) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(kuDatFile, true));
		
		bw.append(newBankUser);
		bw.newLine();
		bw.close();
		
		split = newBankUser.toString().split(";");
		try (BufferedWriter bw2 = new BufferedWriter(new FileWriter("dbbank\\dbbank_userlog\\userlog_intertrans_" + split[1] + "-" + split[2] + ".txt"))) {
			bw2.write(timeStamp + ";" + split[1] + ";" + split[4] + ";" + split[10] + ";cr;transfer credit;initial balance;immediate;" + sequenceHeadGen + "00000;success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (BufferedWriter bw2 = new BufferedWriter(new FileWriter("dbbank\\dbbank_userlog\\userlog_domestic_" + split[1] + "-" + split[2] + ".txt"))) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//SERVING BankMenuAdmin.java in validateAccNo() in menuAdmin2()
	public void setLastAccNo(int firstDigitAccNo) {
		//LOOP until LAST ACC-NO found
		for (String string : kuDatTemp) {
			split = string.split(";");
			
			if (Integer.parseInt(split[1].substring(0, 1)) == firstDigitAccNo) {
				this.lastAccNo = Integer.parseInt(split[1]);
			}
		}
	}
	
	public int getLastAccNo() {
		return lastAccNo;
	}
	
	//SERVING BankMenuAdmin.java in validateUserId()
	public void checkUserIdExist(String userId) {
		this.userIdExist = false;

		for (String string : kuDatTemp) {
			split = string.split(";");
			
			if (split[2].equals(userId)) {
				this.userIdExist = true;
			}
		}
	}
	
	public boolean getCheckUserIdExist() {
		return userIdExist;
	}
	
	//SERVING BankMenuAdmin.java in editUser()
	public void matchUserIdAccNo(String userId, String userAccNo) {
		this.userId = userId;
		this.userAccNo = userAccNo;
		
		for (int i = 0; i < kuDatTemp.size(); i++) {
			split = kuDatTemp.get(i).split(";");
			
			if (userId.equals(split[2]) && userAccNo.equals(split[1])) {
				matchEdit = true;
				this.matchUserIdAccNoIndex = i;
				
				this.userRole = split[0];
				this.userPass = split[3];
				this.userName = split[4];
				this.userDob = split[5];
				this.userAddr = split[6];
				this.userMom = split[7];
				this.userJob = split[8];
				this.userSalaryRange = split[9];
				this.userBalance = split[10];
				this.userStatus = split[11];
				
				break;
			}
		}
	}
	
	public boolean getMatchUserIdAccNo() {
		return matchEdit;
	}
	
	public int getMatchUserIdAccNoIndex() {
		return matchUserIdAccNoIndex;
	}
	
	//SERVING BankMenuAdmin.java in editUser()
	private void setBankUser() {
		this.bankUser = new StringBuilder("");
		bankUser.append(userRole).append(";" + userAccNo).append(";" + userId).append(";" + userPass).append(";" + userName).append(";" + userDob).append(";" + userAddr).append(";" + userMom).append(";" + userJob).append(";" + userSalaryRange).append(";" + userBalance).append(";" + userStatus);
	}
	
	public StringBuilder getBankUser() {
		setBankUser();
		return bankUser;
	}
	
	//SERVING BankMenuAdmin.java in editUser()
	public void editBankUser(String bankUser) {
		for (int i = 0; i < kuDatTemp.size(); i++) {
			if (kuDatTemp.get(i).split(";")[1].equals(bankUser.split(";")[1])) {
				kuDatTemp.set(i, bankUser);
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(kuDatFile))) {
					for (String string : kuDatTemp) {
						bw.write(string);
						bw.newLine();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
	}
	
	//SERVING BankMenuAdmin.java in deleteUser()
	public void deleteBankUser(String bankUser) {
		for (int i = 0; i < kuDatTemp.size(); i++) {
			if (kuDatTemp.get(i).equals(bankUser)) {
				//System.out.println(kuDatTemp.get(i));
				kuDatTemp.remove(i);
				kuDatdel = new File("dbbank\\dbbank_userlog\\userlog_domestic_" + userAccNo + "-" + userId + ".txt");
				if (kuDatdel.delete()) {
					System.out.println("Successfully delete 'userlog_domestic_" + userAccNo + "-" + userId + ".txt'");
				} else {
					System.err.println("Fail delete 'userlog_domestic_" + userAccNo + "-" + userId + ".txt");
				}
				
				kuDatdel = new File("dbbank\\dbbank_userlog\\userlog_intertrans_" + userAccNo + "-" + userId + ".txt");
				if (kuDatdel.delete()) {
					System.out.println("Successfully delete 'userlog_intertrans_" + userAccNo + "-" + userId + ".txt'");
				} else {
					System.err.println("Fail delete 'userlog_intertrans_" + userAccNo + "-" + userId + ".txt'");

				}
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(kuDatFile))){
					for (String string : kuDatTemp) {
						bw.write(string);
						bw.newLine();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			}
		}
	}
	
	//SERVING BankMenuCust.java in transferSameBank()
	public void recordTransSameBank(String userAccNo, String desAccNo, String amount, String remark) throws IOException {
		String desUserId = null;
		String dbLogLoc = null;
		File dbFileLog;
		String transLoc;
		Scanner fileLogScanner;

		sequenceHead = sequenceHead + "00000";
		AtomicInteger seqAtom = new AtomicInteger(Integer.parseInt(sequenceHead)); 

		System.out.println("Recording transfer .....");
		
		for (String string : kuDatTemp) {
			split = string.split(";");
			
			if (split[1].equals(userAccNo)) {
				//System.out.println("Id of " + userAccNo + " is '" + split[2] + "'");
				this.userId = split[2];
				this.userName = split[4];
			}
			
			if (split[1].equals(desAccNo)) {
				desName = split[4];
			}
		}

		System.out.println("userId: " + userId + "; userName: " + userName + "; desName: " + desName + "; userAccNo: " + userAccNo);
		transLoc = "dbbank\\dbbank_userlog\\userlog_intertrans_" + userAccNo + "-" + userId + ".txt";

		try (BufferedReader br = new BufferedReader(new FileReader(transLoc))) {
			while ((readLines = br.readLine()) != null) {
				sequenceId = seqAtom.incrementAndGet();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//RECORDING SENDER-LOG transaction
		BufferedWriter bw = new BufferedWriter(new FileWriter(transLoc, true));
		bw.append("\n" + timeStamp + ";" + desAccNo + ";" + desName + ";" + amount + ";db;transfer debit;" + remark + ";immediate;" + sequenceId + ";success");
		bw.close();
		
		//RECORDING BENEFICIARY-LOG transaction
		for (String string : kuDatTemp) {
			split = string.split(";");
			if (desAccNo.equals(split[1])) {
				desUserId = split[2];
			}
		}
		
		dbLogLoc = "dbbank\\dbbank_userlog\\userlog_intertrans_" + desAccNo + "-" + desUserId + ".txt";
		dbFileLog = new File(dbLogLoc);
		fileLogScanner = new Scanner(dbFileLog);
		while (fileLogScanner.hasNext()) {
			readLines = fileLogScanner.nextLine();
			//System.out.println("see: " + readLines);
			split = readLines.split(";");
			sequenceId = Integer.parseInt(split[9]);
		}
		sequenceId++;
		//System.exit(1); --> USED for TESTING
		
		bw = new BufferedWriter(new FileWriter("dbbank\\dbbank_userlog\\userlog_intertrans_" + desAccNo + "-" + desUserId + ".txt", true));
		bw.append("\n" + timeStamp + ";" + userAccNo + ";" + userName + ";" + amount + ";cr;transfer credit;" + remark + ";immediate;" + sequenceId + ";success");
		bw.close();

		for (int i = 0; i < kuDatTemp.size(); i++) {
			String string = kuDatTemp.get(i);
			split = string.split(";");
			
			//CALCULATE & SET new BALANCE for SENDER in USER DATABASE
			if (split[1].equals(userAccNo)) {
				newBalance = Integer.toString(Integer.parseInt(split[10]) - Integer.parseInt(amount));
				string = string.replace(";" + split[10] + ";", ";" + newBalance + ";");
				System.out.println("newBalance = " + newBalance + "; sender: " + string);
				kuDatTemp.set(i, string);
			}
			
			//CALCULATE & SET new BALANCE for BENEFICIARY in USER DATABASE
			if (split[1].equals(desAccNo)) {
				newBalance = Integer.toString(Integer.parseInt(split[10]) + Integer.parseInt(amount));
				System.out.println("CEK-2) AFTER CALCULATING new BALANCE: " + string);
				string = string.replace(";" + split[10] + ";", ";" + newBalance + ";");
				System.out.println("CEK-3) AFTER INPUTTING new BALANCE: " + string);
				System.out.println("newBalance = " + newBalance + "; benefiary: " + string);
				kuDatTemp.set(i, string);
			}
		}
		
		//RECORD TRANSACTION to 'kuDatFile'
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(kuDatFile))) {
			for (String string : kuDatTemp) {
				wr.write(string);
				wr.newLine();
			}
			System.out.println("\nTransaction complete! End result:");
			for (String string : kuDatTemp) {
				System.out.println(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fileLogScanner.close();
	}
	
	//SERVING BankMenuCust.java in validateBankAcc()
	public void checkAccExist(String userAccNo) {
		this.accExist = false;
		
		for (String string : kuDatTemp) {
			if (string.split(";")[1].equals(userAccNo)) {
				this.accExist = true;
			}
		}
	}
	
	public boolean getAccExist() {
		return accExist;
	}
	
	//SERVING BankMenuCust.java in menuCust4()
	public void userTrxRecord(String userId, String userAccNo) throws FileNotFoundException {
		kuDatFile = new File("dbbank/dbbank_userlog/userlog_intertrans_" + userAccNo + "-" + userId + ".txt");
		LinkedList<String> userTrxLog = new LinkedList<String>();
		Scanner userLogscan = new Scanner(kuDatFile);
		
		int spTrxDate, spTrxValue, spTrxAcc, spTrxRemark, spTrxType, spTrxSeq, spTrxStat;
		spName = spTrxDate = spTrxValue = spTrxAcc = spTrxRemark = spTrxType = spTrxSeq = spTrxStat = 0;
		int spacTrxDate, spacTrxValue, spacTrxAcc, spacTrxRemark, spacTrxType, spacTrxSeq, spacTrxStat;
		spacName = spacTrxValue = spacTrxAcc = spacTrxRemark = spacTrxType = spacTrxSeq = spacTrxStat = 0;
		spTrxDate = spacTrxDate = 12;
		
		while (userLogscan.hasNext()) {
			String string = (String) userLogscan.nextLine();
			userTrxLog.add(string);
		}
		
		for (int i = 0; i < userTrxLog.size(); i++) {
			split = userTrxLog.get(i).split(";");
			/*
			System.out.println(split[0] + "\t| " + split[3] + "\t|" + split[4] + "\t|" + split[5] + "\t|" + split[7] + "\t|" + split[8] + "\t|" + split[9] + "\t|" + split[10] + "\t|");
			*/
			//COUNT RECORD SPACE
			spacName = split[3].length();
			if (spacName > spName) {
				spName = spacName;
				
				if ("TRX From/To".length() > spName) {
					spName = "TRX From/To".length();
				}
			}
			
			spacTrxValue = split[4].length();
			if (spacTrxValue > spTrxValue) {
				spTrxValue = spacTrxValue;
				
				if ("TRX Value".length() > spTrxValue) {
					spTrxValue = "TRX Value".length();
				}
			}
			
			spacTrxAcc = split[5].length();
			if (spacTrxAcc > spTrxAcc) {
				spTrxAcc = spacTrxAcc;
				
				if ("CR / DB".length() > spTrxAcc) {
					spTrxAcc = "CR / DB".length();
				}
			}
			
			spacTrxRemark = split[7].length();
			if (spacTrxRemark > spTrxRemark) {
				spTrxRemark = spacTrxRemark;
				
				if ("TRX Remark".length() > spTrxRemark) {
					spTrxRemark = "TRX Remark".length();
				}
			}
			
			spacTrxType = split[8].length();
			if (spacTrxType > spTrxType) {
				spTrxType = spacTrxType;
				
				if ("TRX Type".length() > spTrxType) {
					spTrxType = "TRX Type".length();
				}
			}
			
			spacTrxSeq = split[9].length();
			if (spacTrxSeq > spTrxSeq) {
				spTrxSeq = spacTrxSeq;
				
				if ("TRX Sequence".length() > spTrxSeq) {
					spTrxSeq = "TRX Sequence".length();
				}
			}
			
			spacTrxStat = split[10].length();
			if (spacTrxStat > spTrxStat) {
				spTrxStat = spacTrxStat;
				
				if ("TRX Status".length() > spTrxStat) {
					spTrxStat = "TRX Status".length();
				}
			}
		}
		
		//PRINT HEAD UPPERLINE
		for (int i = 0; i < 10; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spName; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxValue; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxAcc; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxRemark; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxType; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxSeq; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxStat; i++) {
			System.out.print("-");
		}
		System.out.println("--+");
		
		//PRINT RECORD HEAD
		System.out.print("TRX Date    |");
		
		System.out.print("TRX From/To");
		for (int i = 0; i < spName - "TRX From/To".length() ; i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("TRX Value");
		for (int i = 0; i < spTrxValue - "TRX Value".length() ; i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("CR / DB");
		for (int i = 0; i < spTrxAcc - "CR / DB".length() ; i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("TRX Notes");
		for (int i = 0; i < spTrxRemark - "TRX Notes".length() ; i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("TRX Type");
		for (int i = 0; i < spTrxType - "TRX Type".length() ; i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("TRX Sequence");
		for (int i = 0; i < spTrxSeq - "TRX Sequence".length() ; i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("TRX Status");
		for (int i = 0; i < spTrxStat - "TRX Status".length() ; i++) {
			System.out.print(" ");
		}
		System.out.println("  |");
		
		//PRINT HEAD UNDERLINE
		for (int i = 0; i < 10; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spName; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxValue; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxAcc; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxRemark; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxType; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxSeq; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		for (int i = 0; i < spTrxStat; i++) {
			System.out.print("-");
		}
		System.out.println("--+");
		
		//PRINT RECORD ROWS
		for (int i = 0; i < userTrxLog.size(); i++) {
			split = userTrxLog.get(i).split(";");
			
			System.out.print(split[0] + "  |");
			
			spacName = spName - split[3].length();
			System.out.print(split[3]);
			for (int j = 0; j < spacName; j++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			spacTrxValue = spTrxValue - split[4].length();
			System.out.print(split[4]);
			for (int j = 0; j < spacTrxValue; j++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			spacTrxAcc = spTrxAcc - split[5].length();
			System.out.print(split[5]);
			for (int j = 0; j < spacTrxAcc; j++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			spacTrxRemark = spTrxRemark - split[7].length();
			System.out.print(split[7]);
			for (int j = 0; j < spacTrxRemark; j++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			spacTrxType = spTrxType - split[8].length();
			System.out.print(split[8]);
			for (int j = 0; j < spacTrxType; j++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			spacTrxSeq = spTrxSeq - split[9].length();
			System.out.print(split[9]);
			for (int j = 0; j < spacTrxSeq; j++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			spacTrxStat = spTrxStat - split[10].length();
			System.out.print(split[10]);
			for (int j = 0; j < spacTrxStat; j++) {
				System.out.print(" ");
			}
			System.out.println("  |");
		}
		
		for (String string : kuDatTemp) {
			split = string.split(";");
			
			if (split[1].equals(userAccNo) && split[2].equals(userId)) {
				this.userBalance = split[10];
				break;
			}
		}
		System.out.println("\nEnding balance: Rp. " + userBalance + ",-");
		
		userLogscan.close();
	}
	
	//SERVING BankMenuAdmin.java in menuAdmin1()
	public void tampilBankKuDat() {
		spRole = spAccNo = spId = spPass = spName = spDob = spAddr = spMom = spJob = spSalRan = spBal = spStat = 0;
		spacRole = spacAccNo = spacId = spacPass = spacName = spacDob = spacAddr = spacMom = spacJob = spacSalRan = spacBal = spacStat = 0;
		
		//COUNT SPACING
		for (int i = 0; i < kuDatTemp.size(); i++) {
			split = kuDatTemp.get(i).split(";");
			
			//Spacing ROLE
			spacRole = split[0].length();
			if (spacRole > spRole) {
				spRole = spacRole;
			}

			//Spacing ACC-NO
			spacAccNo = split[1].length();
			if (spacAccNo > spAccNo) {
				spAccNo = spacAccNo;
			}
			
			//Spacing USER-ID
			spacId = split[2].length();
			if (spacId > spId) {
				spId = spacId;
			}

			//Spacing PASS
			spacPass = split[3].length();
			if (spacPass > spPass) {
				spPass = spacPass;
			}
			
			//Spacing NAME
			spacName = split[4].length();			
			if (spacName > spName) {
				spName = spacName;
			}
			
			//Spacing DOB
			spacDob = split[5].length();
			if (spacDob > spDob) {
				spDob = spacDob;
			}
			
			//Spacing ADDR
			spacAddr = split[6].length();
			if (spacAddr > spAddr) {
				spAddr = spacAddr;
			}
			
			//Spacing MOM
			spacMom = split[7].length();
			if (spacMom > spMom) {
				spMom = spacMom;
			}
			
			//Spacing JOB
			spacJob = split[8].length();
			if (spacJob > spJob) {
				spJob = spacJob;
			}
			
			//Spacing SALARY RANGE
			spacSalRan = split[9].length();
			if (spacSalRan > spSalRan) {
				spSalRan = spacSalRan;
			}
			
			//Spacing BALANCE
			spacBal = split[10].length();
			if (spacBal > spBal) {
				spBal = spacBal;
			}
			
			//Spacing STATUS
			spacStat = split[11].length();
			if (spacStat > spStat) {
				spStat = spacStat;
			}
		}
		
		//PRINTING HEAD TABLE UPPERLINE
		System.out.print("---+");

		//sp-Head ROLE
		for (int i = 0; i < spRole; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ACC-NO
		for (int i = 0; i < spAccNo; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head USER-ID
		for (int i = 0; i < spId; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head PASS
		for (int i = 0; i < spPass; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head NAME
		for (int i = 0; i < spName; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//Head DOB
		for (int i = 0; i < spDob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ADDR
		for (int i = 0; i < spAddr; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head MOM
		for (int i = 0; i < spMom; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head JOB
		for (int i = 0; i < spJob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head SALARY RANGE
		for (int i = 0; i < spSalRan; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head BALANCE
		for (int i = 0; i < spBal; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head STATUS
		for (int i = 0; i < spStat; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//PRINTING HEAD TABLE
		System.out.print("\nNO |");

		System.out.print("ROLE");
		for (int i = 0; i < spRole - "ROLE".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("ACCOUNT NO");
		for (int i = 0; i < spAccNo - "AACOUNT NO".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("USER-ID");
		for (int i = 0; i < spId - "USER-ID".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("PASSWORD");
		for (int i = 0; i < spPass - "PASSWORD".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("NAME");
		for (int i = 0; i < spName - "NAME".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("BIRTH DATE");
		for (int i = 0; i < spDob - "BIRTH DATE".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("ADDRESS");
		for (int i = 0; i < spAddr - "ADDRESS".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("MOM's NAME");
		for (int i = 0; i < spMom - "MOM's NAME".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("OCCUPATION");
		for (int i = 0; i < spJob - "OCCUPATION".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("SALARY");
		for (int i = 0; i < spSalRan - "SALARY".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("BALANCE");
		for (int i = 0; i < spBal - "BALANCE".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("ACC-STAT");
		for (int i = 0; i < spStat - "ACC-STAT".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		//PRINTING HEAD TABLE UNDERLINE
		System.out.print("\n---+");

		//sp-Head ROLE
		for (int i = 0; i < spRole; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ACC-NO
		for (int i = 0; i < spAccNo; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head USER-ID
		for (int i = 0; i < spId; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head PASS
		for (int i = 0; i < spPass; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head NAME
		for (int i = 0; i < spName; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//Head DOB
		for (int i = 0; i < spDob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ADDR
		for (int i = 0; i < spAddr; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head MOM
		for (int i = 0; i < spMom; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head JOB
		for (int i = 0; i < spJob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head SALARY RANGE
		for (int i = 0; i < spSalRan; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head BALANCE
		for (int i = 0; i < spBal; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head STATUS
		for (int i = 0; i < spStat; i++) {
			System.out.print("-");
		}
		System.out.print("--+");

		//PRINTING ROWS
		System.out.println();
		int z = 0;
		for (String string : kuDatTemp) {
			split = string.split(";");
			z++;
			
			System.out.print(z + "  |");
			
			//Print Row ROLE
			spacRole = spRole - split[0].length();
			System.out.print(split[0]);
			for (int i = 0; i < spacRole; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row ACC-NO
			spacAccNo = spAccNo - split[1].length();
			System.out.print(split[1]);
			for (int i = 0; i < spacAccNo; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row USER-ID
			spacId = spId - split[2].length();
			System.out.print(split[2]);
			for (int i = 0; i < spacId; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");

			//Print Row PASS
			spacPass = spPass - split[3].length();
			System.out.print(split[3]);
			for (int i = 0; i < spacPass; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row NAME
			spacName = spName - split[4].length();
			System.out.print(split[4]);
			for (int i = 0; i < spacName; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row DOB
			spacDob = spDob - split[5].length();
			System.out.print(split[5]);
			for (int i = 0; i < spacDob; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row ADDR
			spacAddr = spAddr - split[6].length();
			System.out.print(split[6]);
			for (int i = 0; i < spacAddr; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row MOM
			spacMom = spMom - split[7].length();
			System.out.print(split[7]);
			for (int i = 0; i < spacMom; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row JOB
			spacJob = spJob - split[8].length();
			System.out.print(split[8]);
			for (int i = 0; i < spacJob; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row SALARY RANGE
			spacSalRan = spSalRan - split[9].length();
			System.out.print(split[9]);
			for (int i = 0; i < spacSalRan; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row BALANCE
			spacBal = spBal - split[10].length();
			System.out.print(split[10]);
			for (int i = 0; i < spacBal; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");

			//Print Row STATUS
			spacStat = spStat - split[11].length();
			System.out.print(split[11]);
			for (int i = 0; i < spacStat; i++) {
				System.out.print(" ");
			}
			System.out.println("  |");
		}
	}
	
	public String showBankCust() {
		// TODO Auto-generated method stub
		StringBuilder strToReturn = new StringBuilder("");
		
		spRole = spAccNo = spId = spPass = spName = spDob = spAddr = spMom = spJob = spSalRan = spBal = spStat = 0;
		spacRole = spacAccNo = spacId = spacPass = spacName = spacDob = spacAddr = spacMom = spacJob = spacSalRan = spacBal = spacStat = 0;
		
		//COUNT SPACING
		for (int i = 0; i < kuDatTemp.size(); i++) {
			split = kuDatTemp.get(i).split(";");
			
			//Spacing ROLE
			spacRole = split[0].length();
			if (spacRole > spRole) {
				spRole = spacRole;
			}

			//Spacing ACC-NO
			spacAccNo = split[1].length();
			if (spacAccNo > spAccNo) {
				spAccNo = spacAccNo;
			}
			
			//Spacing USER-ID
			spacId = split[2].length();
			if (spacId > spId) {
				spId = spacId;
			}

			//Spacing PASS
			spacPass = split[3].length();
			if (spacPass > spPass) {
				spPass = spacPass;
			}
			
			//Spacing NAME
			spacName = split[4].length();			
			if (spacName > spName) {
				spName = spacName;
			}
			
			//Spacing DOB
			spacDob = split[5].length();
			if (spacDob > spDob) {
				spDob = spacDob;
			}
			
			//Spacing ADDR
			spacAddr = split[6].length();
			if (spacAddr > spAddr) {
				spAddr = spacAddr;
			}
			
			//Spacing MOM
			spacMom = split[7].length();
			if (spacMom > spMom) {
				spMom = spacMom;
			}
			
			//Spacing JOB
			spacJob = split[8].length();
			if (spacJob > spJob) {
				spJob = spacJob;
			}
			
			//Spacing SALARY RANGE
			spacSalRan = split[9].length();
			if (spacSalRan > spSalRan) {
				spSalRan = spacSalRan;
			}
			
			//Spacing BALANCE
			spacBal = split[10].length();
			if (spacBal > spBal) {
				spBal = spacBal;
			}
			
			//Spacing STATUS
			spacStat = split[11].length();
			if (spacStat > spStat) {
				spStat = spacStat;
			}
		}
		
		//PRINTING HEAD TABLE UPPERLINE
		strToReturn.append("---+");

		//sp-Head ROLE
		for (int i = 0; i < spRole; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head ACC-NO
		for (int i = 0; i < spAccNo; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head USER-ID
		for (int i = 0; i < spId; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head PASS
		for (int i = 0; i < spPass; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head NAME
		for (int i = 0; i < spName; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//Head DOB
		for (int i = 0; i < spDob; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head ADDR
		for (int i = 0; i < spAddr; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head MOM
		for (int i = 0; i < spMom; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head JOB
		for (int i = 0; i < spJob; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head SALARY RANGE
		for (int i = 0; i < spSalRan; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head BALANCE
		for (int i = 0; i < spBal; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head STATUS
		for (int i = 0; i < spStat; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//PRINTING HEAD TABLE
		strToReturn.append("\nNO |");

		strToReturn.append("ROLE");
		for (int i = 0; i < spRole - "ROLE".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("ACCOUNT NO");
		for (int i = 0; i < spAccNo - "AACOUNT NO".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("USER-ID");
		for (int i = 0; i < spId - "USER-ID".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("PASSWORD");
		for (int i = 0; i < spPass - "PASSWORD".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("NAME");
		for (int i = 0; i < spName - "NAME".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("BIRTH DATE");
		for (int i = 0; i < spDob - "BIRTH DATE".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("ADDRESS");
		for (int i = 0; i < spAddr - "ADDRESS".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("MOM's NAME");
		for (int i = 0; i < spMom - "MOM's NAME".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("OCCUPATION");
		for (int i = 0; i < spJob - "OCCUPATION".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("SALARY");
		for (int i = 0; i < spSalRan - "SALARY".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("BALANCE");
		for (int i = 0; i < spBal - "BALANCE".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		strToReturn.append("ACC-STAT");
		for (int i = 0; i < spStat - "ACC-STAT".length(); i++) {
			strToReturn.append(" ");
		}
		strToReturn.append("  |");
		
		//PRINTING HEAD TABLE UNDERLINE
		strToReturn.append("\n---+");

		//sp-Head ROLE
		for (int i = 0; i < spRole; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head ACC-NO
		for (int i = 0; i < spAccNo; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head USER-ID
		for (int i = 0; i < spId; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head PASS
		for (int i = 0; i < spPass; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head NAME
		for (int i = 0; i < spName; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//Head DOB
		for (int i = 0; i < spDob; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head ADDR
		for (int i = 0; i < spAddr; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head MOM
		for (int i = 0; i < spMom; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head JOB
		for (int i = 0; i < spJob; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head SALARY RANGE
		for (int i = 0; i < spSalRan; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head BALANCE
		for (int i = 0; i < spBal; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");
		
		//sp-Head STATUS
		for (int i = 0; i < spStat; i++) {
			strToReturn.append("-");
		}
		strToReturn.append("--+");

		//PRINTING ROWS
		strToReturn.append("\n");
		int z = 0;
		for (String string : kuDatTemp) {
			split = string.split(";");
			z++;
			
			strToReturn.append(z + "  |");
			
			//Print Row ROLE
			spacRole = spRole - split[0].length();
			strToReturn.append(split[0]);
			for (int i = 0; i < spacRole; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row ACC-NO
			spacAccNo = spAccNo - split[1].length();
			strToReturn.append(split[1]);
			for (int i = 0; i < spacAccNo; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row USER-ID
			spacId = spId - split[2].length();
			strToReturn.append(split[2]);
			for (int i = 0; i < spacId; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");

			//Print Row PASS
			spacPass = spPass - split[3].length();
			strToReturn.append(split[3]);
			for (int i = 0; i < spacPass; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row NAME
			spacName = spName - split[4].length();
			strToReturn.append(split[4]);
			for (int i = 0; i < spacName; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row DOB
			spacDob = spDob - split[5].length();
			strToReturn.append(split[5]);
			for (int i = 0; i < spacDob; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row ADDR
			spacAddr = spAddr - split[6].length();
			strToReturn.append(split[6]);
			for (int i = 0; i < spacAddr; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row MOM
			spacMom = spMom - split[7].length();
			strToReturn.append(split[7]);
			for (int i = 0; i < spacMom; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row JOB
			spacJob = spJob - split[8].length();
			strToReturn.append(split[8]);
			for (int i = 0; i < spacJob; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row SALARY RANGE
			spacSalRan = spSalRan - split[9].length();
			strToReturn.append(split[9]);
			for (int i = 0; i < spacSalRan; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");
			
			//Print Row BALANCE
			spacBal = spBal - split[10].length();
			strToReturn.append(split[10]);
			for (int i = 0; i < spacBal; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |");

			//Print Row STATUS
			spacStat = spStat - split[11].length();
			strToReturn.append(split[11]);
			for (int i = 0; i < spacStat; i++) {
				strToReturn.append(" ");
			}
			strToReturn.append("  |\n");
		}
		return strToReturn.toString();
	}
}
