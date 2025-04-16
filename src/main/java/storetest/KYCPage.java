package storetest;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;

public class KYCPage extends BaseClass{
	
	@Test(priority = 116) 
	public void kycpage() throws InterruptedException {
	    st.fillbothpage();
	    st.clickRegisterButton();
	    page.waitForTimeout(5000);

	    Locator storepan = page.locator(
	        "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-191zoks']//div[1]//div[1]//span[1]//div[1]//div[1]//span[1]//button[1]");
	    assertTrue(storepan.isVisible());
	}

	
	@Test(priority = 117)//testcase 117
	public void validKYCDocument() throws InterruptedException {
 
		st.fillbothpage();
		st.clickRegisterButton();
 
		st.uploadPanCard("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
 
		page.waitForTimeout(3000);
 
		st.clickSubmitButton();
	}
 
	@Test(priority = 118) //testcase 118
	public void emptyKYCDocument() throws InterruptedException {
 
		st.fillbothpage();
		st.clickRegisterButton();
		
 
		page.waitForTimeout(3000);
 
		st.clickSubmitButton();
 
		String expectedErrorMessage = "Aadhar Card is required";
		String errorMessage = page.locator("//div[normalize-space()='Aadhar Card is required']").innerText();
 
		Assert.assertEquals(errorMessage, expectedErrorMessage, "Incorrect error message displayed!");
 
	}
 
	@Test(priority = 119) //testcase 119
	public void unsupportedKYCDocument() throws InterruptedException {
 
		st.fillbothpage();
		st.clickRegisterButton();
		
		st.uploadPanCard("C:\\Users\\VickySekar\\Downloads\\sample.txt");
 
		st.clickSubmitButton();
 
		page.waitForTimeout(3000);
 
		String expectedErrorMessage = "Unsupported format, must be JPEG, PNG, or PDF";
		String errorMessage = page.locator("//div[normalize-space()='Unsupported format, must be JPEG, PNG, or PDF']").innerText();
		Assert.assertEquals(errorMessage, expectedErrorMessage, "Incorrect error message displayed!");
 
	}
 
	@Test(priority = 120) //testcase 120
	public void maxSizeKYCDocument() throws InterruptedException {
 
		st.fillbothpage();
		st.clickRegisterButton();
		
		st.uploadPanCard("C:\\Users\\VickySekar\\Downloads\\max.jpg");
 
		st.clickSubmitButton();
 
		page.waitForTimeout(3000);
 
		String expectedErrorMessage = "File size too large, maximum is 2MB";
		String errorMessage = page.locator("//div[normalize-space()='File size too large, maximum is 2MB']").innerText();
		Assert.assertEquals(errorMessage, expectedErrorMessage, "Incorrect error message displayed!");
 
	}
	
	 
	@Test(priority = 123) // testcase 123
	public void backButtonKYCBehaviour() throws InterruptedException {
		st.fillbothpage();
		st.clickRegisterButton();
		page.waitForTimeout(3000);
		
		st.uploadPanCard("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		st.uploadAadhar("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
	 
		page.locator("//*[@id=\"rc-tabs-0-panel-UploadKYCDetails\"]/div/div/form/div[2]/button[1]").click();
	 
		page.waitForSelector("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-1mt2vgf'][1]");
	 
		boolean isFormVisible = page
				.isVisible("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-1mt2vgf'][1]");
		Assert.assertTrue(isFormVisible, "The form should be visible.");
	 
		page.waitForTimeout(5000);
	 
	}
	
	@Test(priority =124) //testcase 124
	public void submitKYC() throws InterruptedException {
		st.fillbothpage();
		st.clickRegisterButton();
		
		st.uploadPanCard("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		st.uploadAadhar("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		st.uploadAddressProof("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		st.uploadCancelCheque("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		st.uploadOwnPan("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
	 
		page.waitForTimeout(3000);
	 
		st.clickSubmitButton();
		
		page.waitForTimeout(3000);
		
		Locator message = page.locator("//div[@class='toast can-close progress success show']");
		assertTrue(message.isVisible());
		
		page.waitForSelector("(//div[@class='MuiContainer-root MuiContainer-maxWidthSm css-nzk61r'])[1]");
	 
		boolean isFormVisible = page
				.isVisible("(//div[@class='MuiContainer-root MuiContainer-maxWidthSm css-nzk61r'])[1]");
		Assert.assertTrue(isFormVisible, "The form should be visible.");
	 
		
		page.waitForTimeout(2000);
	 
	 
	}
	
}
