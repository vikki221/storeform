package pageobjects;

import java.nio.file.Path;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;
import java.util.Random;


public class store1 {
	
	 private Page page;

	 private final String uploadInput = "//input[@type='file']";
	 private final String uploadedFileNameLocator = "//div[@class='ant-upload-list-item ant-upload-list-item-undefined']";
	 private final String storeName = "input#name";
	 private final String categoryDropdown = "//div[@name='categoryId']//span[@title='Select an option...'][normalize-space()='Select an option...']";
	 private final String stateDropdown = "//div[@name='address.state']//span[@title='Select an option...'][normalize-space()='Select an option...']";
	 private final String cityDropdown = "//div[@name='address.city']//span[@title='Select an option...'][normalize-space()='Select an option...']";
	 private final String pincodeDropdown = "//div[@name='address.pincode']//span[@title='Select an option...'][normalize-space()='Select an option...']";
	 private final String storeDoorNo = "input[name='address.address1']";
	 private final String streetName =  "input[name='address.address2']";
	 private final String locality = "input[name='address.address3']";
	 private final String email = "input[name='address.email']";
	 private final String phoneNumber = "input[name='address.phone_number']";
	 private final String gstNumber = "input[name='gst_no']";
	 private final String website = "input[name='website']";
	 private final String fssai = "input[name='fssai_no']";
	 private final String udhayamId =  "input[name='udhayam_id']";
	 private final String nextButton = "//button[@type='submit']";
	 
	 private final String name = "//input[@id='bankDetail.account_holder_name']";
	 private final String accno = "//input[@id='bankDetail.account_number']";
	 private final String ifsc = "//input[@id='bankDetail.ifsc_code']";
	 private final String bankname = "//input[@id='bankDetail.bank_name']";
	 private final String branchname = "//input[@id='bankDetail.branch_name']";
	 private final String address = "//input[@id='bankDetail.address']";
	 private final String pan = "//input[@id='bankDetail.pan_number']";
	 private final String upi = "//input[@id='bankDetail.upi_id']";
	 private final String register = "//button[@type='submit']";
	 
	 private final String uploadPanCard = "(//span[@class='ant-upload'])[2]//input[@type='file']";
	 private final String uploadAadhar = "(//span[@class='ant-upload'])[3]//input[@type='file']";
	 private final String uploadCancelCheque = "(//span[@class='ant-upload'])[4]//input[@type='file']";
	 private final String uploadAddressProof = "(//span[@class='ant-upload'])[5]//input[@type='file']";
	 private final String uploadOwnPan = "(//span[@class='ant-upload'])[6]//input[@type='file']";

	    public store1(Page page) {
	        if (page == null) {
	            throw new IllegalArgumentException("Page object cannot be null");
	        }
	        this.page = page;
	    }
	   
	    public void uploadStoreLogo(String filePath) {
	        page.locator(uploadInput).setInputFiles(Paths.get(filePath));
	    }
	    
	    public boolean isLogoUploadVisible() {
	        return page.locator(uploadInput).isVisible();
	    }

	    public String getUploadedFileName() {
	        return page.locator(uploadedFileNameLocator).innerText();
	    }
	    
	    public void enterStorename(String storeInput) {
	        page.locator(storeName).fill(storeInput);
	    }
	    
	    public void uploadStoreLogo(Path string) {
	        page.locator(uploadInput).setInputFiles(string);
	    }

	    
	    public void selectCategory(String category) {
	        page.locator(categoryDropdown).click(); 
	        page.getByText(category).click();
	    }

	    public void selectState(String stateName) {
	        page.locator(stateDropdown).click(); 
	        page.getByText(stateName).click(); 
	    }
	    
	    public void selectCity(String cityName) {
	        page.locator(cityDropdown).click(); 
	        page.getByText(cityName).click(); 
	    }

        
	    public void selectPincode(String pincode)  {
	        page.locator(pincodeDropdown).click();
	        page.getByText(pincode).click(); 
	    }


	    
	    public void enterStoreDoorNo(String doorNo) {
	        page.locator(storeDoorNo).fill(doorNo);
	    }
	    
	    public void enterStreetName(String street) {
	        page.locator(streetName).fill(street);
	    }

	    public void enterLocality(String loc) {
	        page.locator(locality).fill(loc);
	    }

	    public void enterEmail(String emailInput) {
	        page.locator(email).fill(emailInput);
	    }

	    public void enterPhoneNumber(String phone) {
	        page.locator(phoneNumber).fill(phone);
	    }

	    public void enterGSTNumber(String gst) {
	        page.locator(gstNumber).fill(gst);
	    }

	    public void enterWebsite(String site) {
	        page.locator(website).fill(site);
	    }

	    public void enterFSSAI(String fssaiNumber) {
	        page.locator(fssai).fill(fssaiNumber);
	    }

	    public void enterUdhayamID(String udhayam) {
	        page.locator(udhayamId).fill(udhayam);
	    }

	    public void clickNext() {
	        page.locator(nextButton).click();
	    }
	    
	    public void enterAccountHolderName(String accname) {
	    	page.locator(name).fill(accname);
	    }
	    
	    public void enterAccountNumber(String accnumber) {
	    	page.locator(accno).fill(accnumber);
	    }
	    
	    public void enterIfsc(String code) {
	    	page.locator(ifsc).fill(code);
	    }
	    
	    public void enterBankName(String bank) {
	    	page.locator(bankname).fill(bank);
	    }
	    
	    public void enterBranchName(String branch) {
	    	page.locator(branchname).fill(branch);
	    }
	    
	    public void enterBranchAddress(String addresss) {
	    	page.locator(address).fill(addresss);
	    }
	    
	    public void enterPanCard(String panno) {
	    	page.locator(pan).fill(panno);
	    }
	    
	    public void enterupiId(String upiid) {
	    	page.locator(upi).fill(upiid);
	    }
	    
	    public void clickRegisterButton() {
	    	page.locator(register).click();
	    }
	    public void fillfirstpage() throws InterruptedException {
	    	uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
			enterStorename("Mathes Store");
			selectCategory("F&B");
			selectState("ANDHRA PRADESH");
			selectCity("GUNTUR");
			selectPincode("522001");
			enterStoreDoorNo("123");
		    enterStreetName("Main Street");
		    enterLocality("Chennai");
		    enterEmail("mathesh@example.com");
		    enterPhoneNumber("9876543210");
		    enterGSTNumber("27ABCDE1234F1Z5");
		    enterWebsite("https://matheshstore.com");
		    enterFSSAI("12346789211111");
		    enterUdhayamID("UDHAYAM98765");
		    clickNext();
	    }
          
	    
	    
	    public void fillpage() throws InterruptedException {
	    	uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
			page.waitForTimeout(2000);
			enterStorename("Mathes Store");
			selectCategory("F&B");
			selectState("ANDHRA PRADESH");
			selectCity("GUNTUR");
			selectPincode("522001");
			enterStoreDoorNo("123");
		    enterStreetName("Main Street");
		    enterLocality("Chennai");
		    enterEmail("mathesh@example.com");
		    enterPhoneNumber("9876543210");
		    enterGSTNumber("27ABCDE1234F1Z5");
		    enterWebsite("https://matheshstore.com");
		    enterFSSAI("12346789211111");
		    enterUdhayamID("UDHAYAM98765");

		    page.waitForTimeout(3000);
		    clickNext();
		    
		    enterAccountHolderName("Mathesh");
		    enterAccountNumber("8289138271");
		    enterIfsc("ABCD0123456");
		    enterBankName("State Bank Of India");
		    enterBranchName("Thallakulam");
		    enterBranchAddress("1st street");
		    enterPanCard("ABCDE1234F");
		    enterupiId("mathesh@upi");
		    
		    page.waitForTimeout(3000);
		    clickRegisterButton();
		    page.waitForTimeout(3000);
		    
	    }
	    public void enterRandomPhoneNumber() {
	        Random random = new Random();
	        String phoneNumber = "9" + (100000000 + random.nextInt(900000000));
	        page.locator("input[name='address.phone_number']").fill(phoneNumber);
	    }

	    
	    public void fillbothpage() throws InterruptedException {
	    	uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
			page.waitForTimeout(2000);
			enterStorename("Dominos");
			selectCategory("F&B");
			selectState("ANDHRA PRADESH");
			selectCity("GUNTUR");
			selectPincode("522001");
			enterStoreDoorNo("123");
		    enterStreetName("Main Street");
		    enterLocality("Chennai");
		    enterEmail("mathesh@example.com");
		    enterRandomPhoneNumber();
		    enterGSTNumber("27ABCDE1234F1Z5");
		    enterWebsite("https://matheshstore.com");
		    enterFSSAI("12346789211111");
		    enterUdhayamID("UDHAYAM98765");

		    page.waitForTimeout(3000);
		    clickNext();
		    
		    enterAccountHolderName("Mathesh");
		    enterAccountNumber("8289138271");
		    enterIfsc("ABCD0123456");
		    enterBankName("State Bank Of India");
		    enterBranchName("Thallakulam");
		    enterBranchAddress("1st street");
		    enterPanCard("ABCDE1234F");
		    enterupiId("mathesh@upi");		    
	    }
	    
	    public void uploadPanCard(String filepath) {
			page.locator(uploadPanCard).setInputFiles(Paths.get(filepath));
		}
	 
		public void uploadAadhar(String filePath) {
			page.locator(uploadAadhar).setInputFiles(Paths.get(filePath));
		}
	 
		public void uploadCancelCheque(String filePath) {
			page.locator(uploadCancelCheque).setInputFiles(Paths.get(filePath));
		}
	 
		public void uploadAddressProof(String filePath) {
			page.locator(uploadAddressProof).setInputFiles(Paths.get(filePath));
		}
	 
		public void uploadOwnPan(String filePath) {
			page.locator(uploadOwnPan).setInputFiles(Paths.get(filePath));
		}

		public void clickSubmitButton() {
			page.locator("//button[normalize-space()='Submit']").click();
			
		}
	 
	   
	}


