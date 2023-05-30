import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author wijaya
 *
 */
class BankAtmGUIUnitTest extends JFrame{

	/**
	 * All @throws java.lang.Exception
	 *
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	*/
	JRadioButton tombolCreateData;
	JPanel panelAdmin;
	
//	private void TesFrame() {
//		// TODO Auto-generated method stub
//		setTitle("Bank Atm GUI Unit Test");
//		setSize(500, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setVisible(true);
//	}

	private boolean isLeapYear(int year) {
		return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
		// TODO Auto-generated method stub

	}
	@Test
	void test() {
		System.out.println("Helo");
		
		assertTrue(isLeapYear(2011));
		assertTrue(isLeapYear(1904));
		
		assertFalse(isLeapYear(1900));
		assertFalse(isLeapYear(1901));
//		TesFrame();
//		fail("Not yet implemented");
	}

}
