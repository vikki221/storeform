package storetest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;

public class BankDetails extends BaseClass {
	@Test(priority = 70) // testcase 70
	public void validAccountHolderName() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountHolderName("valar");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='Account Holder Name is required']");
		assertFalse(errorLocator.isVisible(), "Account Holder Name is required");
	}
 
	@Test(priority = 71) // testcase 71
	public void emptyAccountHolderName() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountHolderName("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Holder Name is required']").innerText();
		String ee = "Account Holder Name is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 72) // testcase 72
	public void multiSpaceAccountHolderName() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountHolderName("val  a  r");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Multiple consecutive spaces are not allowed']")
				.innerText();
		String ee = "Multiple consecutive spaces are not allowed";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	
 
	@Test(priority = 73) // testcase 73
	public void lessCharAccountHolderName() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountHolderName("va");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Holder Name must be at least 3 characters']")
				.innerText();
		String ee = "Account Holder Name must be at least 3 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 74) // testcase 74
	public void moreCharAccountHolderName() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountHolderName("valarabaccounyholdernameholderbankdetaildetailbranchdetailbranchadress");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Holder Name must not exceed 50 characters']")
				.innerText();
		String ee = "Account Holder Name must not exceed 50 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
 
	@Test(priority = 75) // testcase 75
	public void validAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("164000000014568");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='Account Number is required']");
		assertFalse(errorLocator.isVisible(), "Account Number is required");
	}
 
	@Test(priority = 76) // testcase 76
	public void emptyAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Number is required']").innerText();
		String ee = "Account Number is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 77) // testcase 77
	public void invalidAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("1640abch@8978");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Number must be between 9 and 18 digits']")
				.innerText();
		String ee = "Account Number must be between 9 and 18 digits";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 78) // testcase 78
	public void lessCharAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("1640");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Number must be between 9 and 18 digits']")
				.innerText();
		String ee = "Account Number must be between 9 and 18 digits";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 79) // testcase 79
	public void moreCharAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("1640675666767677778878787854");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Number must be between 9 and 18 digits']")
				.innerText();
		String ee = "Account Number must be between 9 and 18 digits";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 80) // testcase 80
	public void specialCharAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("@#16486$5887854");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Number must be between 9 and 18 digits']")
				.innerText();
		String ee = "Account Number must be between 9 and 18 digits";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 81) // testcase 81
	public void multiSpaceAccountNumber() throws InterruptedException {
		st.fillfirstpage();
		st.enterAccountNumber("345 6789 012345");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Account Number must be between 9 and 18 digits']")
				.innerText();
		String ee = "Account Number must be between 9 and 18 digits";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
 
	@Test(priority = 82) // testcase 82
	public void validIfscCode() throws InterruptedException {
		st.fillfirstpage();
		st.enterIfsc("SBIN0007587");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
		
		Locator errorLocator = page.locator("//div[normalize-space()='IFSC Code is required']");
		assertFalse(errorLocator.isVisible(), "IFSC Code is required");
		
	}
	
	@Test(priority = 83) // testcase 83
	public void emptyIfscCode() throws InterruptedException {
		st.fillfirstpage();
		st.enterIfsc("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='IFSC Code is required']").innerText();
		String ee = "IFSC Code is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 84) // testcase 84
	public void spaceIfscCode() throws InterruptedException {
		st.fillfirstpage();
		st.enterIfsc("SBIN  7587");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid IFSC Code']")
				.innerText();
		String ee = "Invalid IFSC Code";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
 
	
 
	@Test(priority = 85) // testcase 85
	public void specialCharIfscCode() throws InterruptedException {
		st.fillfirstpage();
		st.enterIfsc("sbi@@@7587");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid IFSC Code']")
				.innerText();
		String ee = "Invalid IFSC Code";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 86) // testcase 86
	public void lessCharIfscCode() throws InterruptedException {
		st.fillfirstpage();
		st.enterIfsc("sbi587");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid IFSC Code']")
				.innerText();
		String ee = "Invalid IFSC Code";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 87) // testcase 87
	public void moreCharIfscCode() throws InterruptedException {
		st.fillfirstpage();
		st.enterIfsc("sbi587sbi00074522222");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid IFSC Code']")
				.innerText();
		String ee = "Invalid IFSC Code";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 88)//testcase 88
	public void validBankName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBankName("icici");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='Bank Name is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid bank name");
	}
	
	@Test(priority = 89) // testcase 89
	public void emptyBankName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBankName("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Bank Name is required']").innerText();
		String ee = "Bank Name is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 90) // testcase 90
	public void multiSpaceBankName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBankName("icc   ici");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Multiple consecutive spaces are not allowed']").innerText();
		String ee = "Multiple consecutive spaces are not allowed";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 91) // testcase 91
	public void lessCharBankName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBankName("ic");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Bank Name must be at least 3 characters']").innerText();
		String ee = "Bank Name must be at least 3 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 92) // testcase 92
	public void moreCharBankName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBankName("iciciciiciciciisiicicsicisiiciscisiiiiiiiiicisciiiiiciiicicscscsciscisicisici");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Bank Name must not exceed 50 characters']").innerText();
		String ee = "Bank Name must not exceed 50 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
 
	@Test(priority = 93)//testcase 93
	public void validBranchName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchName("icici,salem");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='Branch Name is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid branch name");
	}
	
	@Test(priority = 94) // testcase 94
	public void emptyBranchName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchName("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Branch Name is required']").innerText();
		String ee = "Branch Name is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 95) // testcase 95
	public void multiSpaceBranchName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchName("icc   ici");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Multiple consecutive spaces are not allowed']").innerText();
		String ee = "Multiple consecutive spaces are not allowed";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 96) // testcase 96
	public void lessCharBranchName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchName("ic");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Branch Name must be at least 3 characters']").innerText();
		String ee = "Branch Name must be at least 3 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 97) // testcase 97
	public void moreCharBranchName() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchName("iciciciiciciciisiicicsicisiiciscisiiiiiiiiicisciiiiiciiicicscscsciscisicisici");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Branch Name must not exceed 50 characters']").innerText();
		String ee = "Branch Name must not exceed 50 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 98)//testcase 98
	public void validBranchAddress() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchAddress("icici main road,salem");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='Branch Address is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid branch address");
	}
	
	@Test(priority = 99) // testcase 99
	public void emptyBranchAddress() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchAddress("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Branch Address is required']").innerText();
		String ee = "Branch Address is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 100) // testcase 100
	public void multiSpaceBranchAddress() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchAddress("icici main     address");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Multiple consecutive spaces are not allowed']").innerText();
		String ee = "Multiple consecutive spaces are not allowed";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 101) // testcase 101
	public void lessCharBranchAddress() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchAddress("icic");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Branch Address must be at least 5 characters']").innerText();
		String ee = "Branch Address must be at least 5 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
	@Test(priority = 102) // testcase 102
	public void moreCharBranchAddress() throws InterruptedException {
		st.fillfirstpage();
		st.enterBranchAddress("icic main road icncni main road ndjnj ic icnibdb main road icci main road icccii manin road salem  iccisbn mainnd roadd");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Branch Address must not exceed 100 characters']").innerText();
		String ee = "Branch Address must not exceed 100 characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
 
	@Test(priority = 103)//testcase 103
	public void validPanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("ABCDE1234F");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='PAN Card is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid Pan Card");
	}
	
	@Test(priority = 104) // testcase 104
	public void emptyPanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='PAN Card is required']").innerText();
		String ee = "PAN Card is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 105) // testcase 105
	public void invalidPanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("ABC123EEFGS");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid PAN Card format']").innerText();
		String ee = "Invalid PAN Card format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
 
	@Test(priority = 106) // testcase 106
	public void lessCharPanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("ABC");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid PAN Card format']").innerText();
		String ee = "Invalid PAN Card format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 107) // testcase 107
	public void moreCharPanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("ABC123EEFGS4566665");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid PAN Card format']").innerText();
		String ee = "Invalid PAN Card format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 108) // testcase 108
	public void specialCharPanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("ABC123@EEFGS#456666&*5");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid PAN Card format']").innerText();
		String ee = "Invalid PAN Card format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 109) // testcase 109
	public void multiSpacePanCard() throws InterruptedException {
		st.fillfirstpage();
		st.enterPanCard("ABC  123 EDG");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid PAN Card format']").innerText();
		String ee = "Invalid PAN Card format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
 
	@Test(priority = 110)//testcase 110
	public void validUpiId() throws InterruptedException {
		st.fillfirstpage();
		st.enterupiId("valar@upi");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		Locator errorLocator = page.locator("//div[normalize-space()='Invalid UPI ID format']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid Upi Id");
	}
	
 
	
 
	@Test(priority = 111) // testcase 111
	public void invalidUpiId() throws InterruptedException {
		st.fillfirstpage();
		st.enterupiId("valarupi");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid UPI ID format']").innerText();
		String ee = "Invalid UPI ID format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
 
	@Test(priority = 112) // testcase 112
	public void spaceUiId() throws InterruptedException {
		st.fillfirstpage();
		st.enterupiId("val ar@upi");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid UPI ID format']").innerText();
		String ee = "Invalid UPI ID format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
 
	@Test(priority = 113) // testcase 113
	public void specialCharUpiId() throws InterruptedException {
		st.fillfirstpage();
		st.enterupiId("va!la!!r@upi");
		st.clickRegisterButton();
		page.waitForTimeout(2000);
 
		String ae = page.locator("//div[normalize-space()='Invalid UPI ID format']").innerText();
		String ee = "Invalid UPI ID format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	@Test(priority = 114) // testcase 114
	public void backButtonBehaviour() throws InterruptedException {
		st.fillbothpage();
		page.waitForTimeout(3000);
	 
		page.locator("//*[@id=\"rc-tabs-0-panel-StoreDetails\"]/div/div/form/div[2]/button[1]").click();
	 
		page.waitForSelector("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-1mt2vgf'][1]");
	 
		boolean isFormVisible = page
				.isVisible("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-1mt2vgf'][1]");
		Assert.assertTrue(isFormVisible, "The form should be visible.");
	 
		page.waitForTimeout(2000);
	 
	}
	
	@Test(priority = 115) //testcase 115
	public void registerStoresuccessfull() throws InterruptedException {
		st.fillbothpage();
		st.clickRegisterButton();
		
		page.waitForTimeout(5000);
		
		Locator message = page.locator("//div[@class='toast can-close progress success show']");
		assertTrue(message.isVisible());
	 
		page.waitForSelector("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-191zoks']//div[1]//div[1]//span[1]//div[1]//div[1]//span[1]//button[1]");
	 
		boolean isFormVisible = page.isVisible("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-191zoks']//div[1]//div[1]//span[1]//div[1]//div[1]//span[1]//button[1]");
		Assert.assertTrue(isFormVisible, "The form should be visible.");
	 
		page.waitForTimeout(2000);
	 
	}

}
