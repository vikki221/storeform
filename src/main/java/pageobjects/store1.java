package pageobjects;

import java.nio.file.Path;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.nio.file.Paths;


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
	    public void selectstate(String stateName) {
			page.locator(stateDropdown).click();
			
			Locator option = page.locator( " option:has-text('" + stateName + "')");
			
			System.out.println("slect stae fun"+stateName);
			option.scrollIntoViewIfNeeded();
			option.click();
			//page.getByText(stateName).click();
		}
		

	    
	    public void selectCity(String cityName) {
	        page.locator(cityDropdown).click(); 
	        page.getByText(cityName).click(); 
	    }

        
	    public void selectPincode(String pincode) {
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

	}


