package storetest;


import com.microsoft.playwright.Locator;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.CustomListener.class)
public class StoreDetails extends BaseClass{
	
	

	@Test(priority=1)
	public void validTest() throws InterruptedException {
		st.uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		page.waitForTimeout(2000);
		st.enterStorename("Mathes Store");
		st.selectCategory("F&B");
		st.selectState("ANDHRA PRADESH");
		page.waitForTimeout(3000);
		st.selectCity("GUNTUR");
		page.waitForTimeout(3000);
		st.selectPincode("522001");
		page.waitForTimeout(5000);
		st.enterStoreDoorNo("123");
	    st.enterStreetName("Main Street");
	    st.enterLocality("Chennai");
	    st.enterEmail("mathesh@example.com");
	    st.enterPhoneNumber("9876543210");
	    st.enterGSTNumber("27ABCDE1234F1Z5");
	    st.enterWebsite("https://matheshstore.com");
	    st.enterFSSAI("12346789211111");
	    st.enterUdhayamID("UDHAYAM98765");

	    page.waitForTimeout(3000);
	    st.clickNext();
	}
	
	@Test(priority=2)
	public void testUploadLogo() {
	    String logoPath = "C:\\Users\\VickySekar\\Downloads\\logo.jpg";
	    
	    st.uploadStoreLogo(logoPath);
	    page.waitForTimeout(2000);
	    
	    Locator uploadedLogo = page.locator("//*[name()='path' and contains(@d,'M942.2 486')]");	    
	    uploadedLogo.click();
	    
	    page.waitForTimeout(2000);
	    
	    page.mouse().click(10, 10);
	    page.waitForTimeout(1000);
	    
	    assertTrue(uploadedLogo.isVisible(), "Uploaded logo is not visible after closing the view modal!");
	}
	

	@Test(priority=3)
	    public void verifyUnsupportedFileUpload() {
		 
		    st.uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\sample.txt");
	        page.waitForTimeout(2000);
	        st.clickNext();
	        
	        String expectedErrorMessage = "Unsupporte format, must be JPEG, PNG";
	        String errorMessage = page.locator("//div[contains(@class, 'fnIpjs') and contains(text(), 'Unsupported format')]").innerText();
	        Assert.assertEquals(errorMessage, expectedErrorMessage, "Incorrect error message displayed!");

	    }
	
	@Test(priority=4)
    public void verifyDeleteLogo() {
        String logoPath = "C:\\Users\\VickySekar\\Downloads\\logo.jpg";
        
       
        st.uploadStoreLogo(logoPath);
        
        Locator deleteElement = page.locator("//span[@aria-label='delete']//*[name()='svg']");
        assertTrue(deleteElement.isVisible(), "Delete icon is not displayed!");
        deleteElement.click();
    
        page.waitForTimeout(2000);
        assertFalse(deleteElement.isVisible(),"Logo is not deleted");
    }
	
	@Test(priority=5)
    public void verifymaxsize() {
        String logoPath = "C:\\Users\\VickySekar\\Downloads\\max.jpg";
       
        st.uploadStoreLogo(logoPath);
        st.clickNext();
        page.waitForTimeout(2000);
        
        String expectedErrorMessage = "File size too large, maximum is 5MB";
        String errorMessage = page.locator("//div[contains(@class, 'fnIpjs')]").innerText();

        Assert.assertEquals(errorMessage, expectedErrorMessage, "Incorrect error message displayed!");

    }
	

	@Test(priority=6)
    public void validstorename() {
		
		st.enterStorename("mathesh Store");
		st.clickNext();
        page.waitForTimeout(2000);
        
        Locator errorLocator = page.locator("//div[normalize-space()='Store Name is required']");
        assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid store name!");
        
	}
	
	@Test(priority=7)
    public void emptystorename() {
		
		st.enterStorename("");
		st.clickNext();
        page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Store Name is required']").innerText();
        String ee = "Store Name is required";
        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority=8)
    public void specialcharactersstorename() {
		
		st.enterStorename("@Va$#lar");
		st.clickNext();
        
        String ae = page.locator("//div[normalize-space()='Store Name can only contain letters, numbers, spaces, & ,']").innerText();
        String ee = "Store Name can only contain letters, numbers, spaces, & ,";
        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority=9)
    public void minstorename() {
		
		st.enterStorename("ll");
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Store Name must be at least 3 characters']").innerText();
        String ee = "Store Name must be at least 3 characters";

        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority=10)
    public void maxstorename() {
		
		st.enterStorename("valarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Store Name must not exceed 50 characters']").innerText();
        String ee = "Store Name must not exceed 50 characters";

        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	
	@Test(priority=11)
	public void storetype() {
		
		Locator categorydd = page.locator("//div[@name='categoryId']//span[@title='Select an option...'][normalize-space()='Select an option...']");
		categorydd.click();
		page.waitForTimeout(2000);
		
	    List<Locator> dropdownOptions = page.locator("//div[contains(@class, 'ant-select-dropdown')]").all();
	    List<String> actualOptions = dropdownOptions.stream().map(Locator::textContent).map(String::trim).collect(Collectors.toList());
	    actualOptions.removeIf(option -> option.trim().equalsIgnoreCase("Select an option..."));

	    System.out.println("Dropdown options: " + actualOptions);
	    
	    assertFalse(actualOptions.isEmpty(), "Dropdown options list is empty!");
	}
	
	@Test(priority=12)
	public void storeselect() {
		st.selectCategory("F&B");
		
	    Locator selectedElement = page.locator("//*[@id=\"rc-tabs-0-panel-StoreDetails\"]/div/div/div/form/div[1]/div[2]/div/div[2]/div/div/div");
	    assertEquals("F&B", selectedElement.textContent().trim());
		
	}
	
	@Test(priority=13)
	public void storetypenotselected() {
		st.clickNext();
		page.waitForTimeout(2000);
		
		String ae = page.locator("//div[normalize-space()='Please select a store type']").innerText();
		String ee = "Please select a store type";
		assertEquals(ae,ee,"Error message not displayed");
	}
	
	
	@Test(priority=14)
	public void statedd() {
		Locator dd = page.locator("//div[@name='address.state']//span[@title='Select an option...'][normalize-space()='Select an option...']");
		dd.click();
		page.waitForTimeout(2000);
		
		 List<Locator> dropdownOptions = page.locator("//div[contains(@class, 'ant-select-dropdown')]").all();
		 List<String> actualOptions = dropdownOptions.stream().map(Locator::textContent).map(String::trim).collect(Collectors.toList());
		 actualOptions.removeIf(option -> option.trim().equalsIgnoreCase("Select an option..."));

		 System.out.println("Dropdown options: " + actualOptions);
		    
		 assertFalse(actualOptions.isEmpty(), "Dropdown options list is empty!");
		
	}
	
	@Test(priority=15)
	public void stateselect() {
		st.selectState("ASSAM");
		page.waitForTimeout(2000);
		
	    Locator selectedElement = page.locator("//*[@id=\"rc-tabs-0-panel-StoreDetails\"]/div/div/div/form/div[1]/div[3]/div/div/div/span/span[2]");
	    assertEquals("ASSAM", selectedElement.textContent().trim());
		
	}
	
	
	@Test(priority=16)
	public void statenotselected() {
		st.clickNext();
		page.waitForTimeout(2000);
		
		String ae = page.locator("//div[normalize-space()='State Name is required']").innerText();
		String ee = "State Name is required";
		assertEquals(ae,ee,"Error message not displayed");
	}
	
	@Test(priority=17)
	public void citydd() {
		st.selectState("ASSAM");
		page.waitForTimeout(2000);
		
		Locator dd = page.locator("//div[@name='address.city']//span[@title='Select an option...'][normalize-space()='Select an option...']");
		dd.click();
		page.waitForTimeout(2000);
		
		 List<Locator> dropdownOptions = page.locator("//div[contains(@class, 'ant-select-dropdown')]").all();
		 List<String> actualOptions = dropdownOptions.stream().map(Locator::textContent).map(String::trim).collect(Collectors.toList());
		 actualOptions.removeIf(option -> option.trim().equalsIgnoreCase("Select an option..."));

		 System.out.println("Dropdown options: " + actualOptions);
		    
		 assertFalse(actualOptions.isEmpty(), "Dropdown options list is empty!");
		
	}
	
	@Test(priority=18)
	public void cityselect() {
		st.selectState("ASSAM");
		page.waitForTimeout(2000);
		
	    st.selectCity("CACHAR");
	    page.waitForTimeout(2000);
	    
	    Locator city = page.locator("//div[@name='address.city']//span[contains(@class, 'ant-select-selection-item')]");
	    assertEquals("CACHAR", city.textContent().trim());
		
	}
	
	@Test(priority=19)
	public void citynotselected() {
		st.clickNext();
		page.waitForTimeout(2000);
		
		String ae = page.locator("//div[normalize-space()='City Name is required']").innerText();
		String ee = "City Name is required";
		assertEquals(ae,ee,"Error message not displayed");
	}
	
	@Test(priority=20)
	public void pincodedd() {
		st.selectState("ASSAM");
		page.waitForTimeout(2000);
		
	    st.selectCity("CACHAR");
	    page.waitForTimeout(2000);

		Locator dd = page.locator("//div[@name='address.pincode']//span[@title='Select an option...'][normalize-space()='Select an option...']");
		dd.click();
		page.waitForTimeout(2000);
		
		 List<Locator> dropdownOptions = page.locator("//div[contains(@class, 'ant-select-dropdown')]").all();
		 List<String> actualOptions = dropdownOptions.stream().map(Locator::textContent).map(String::trim).collect(Collectors.toList());
		 actualOptions.removeIf(option -> option.trim().equalsIgnoreCase("Select an option..."));

		 System.out.println("Dropdown options: " + actualOptions);
		    
		 assertFalse(actualOptions.isEmpty(), "Dropdown options list is empty!");
		
	}
	
	@Test(priority=21)
	public void pincodeselect() {
		st.selectState("ANDHRA PRADESH");
		page.waitForTimeout(3000);
		st.selectCity("GUNTUR");
		page.waitForTimeout(3000);
		st.selectPincode("522001");
	    
	    Locator pincode = page.locator("//div[@name='address.pincode']//span[contains(@class, 'ant-select-selection-item')]");
	    assertEquals("522001", pincode.textContent().trim());
	}
	
	@Test(priority=22)
	public void pincodenotselected() {
		st.clickNext();
		page.waitForTimeout(2000);
		
		String ae = page.locator("//div[normalize-space()='Pincode is required']").innerText();
		String ee = "Pincode is required";
		assertEquals(ae,ee,"Error message not displayed");
	}
	
	@Test(priority=13)
    public void validdoorno() {
		
		st.enterStoreDoorNo("140B/23 Valar");
		st.clickNext();
        page.waitForTimeout(2000);
        
        Locator errorLocator = page.locator("//div[normalize-space()='Store Door No is required']");
        assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid door no!");
        
	}
	
	@Test(priority=24)
    public void emptydoorno() {
		
		st.enterStoreDoorNo("");
		st.clickNext();
        page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Store Door No is required']").innerText();
        String ee = "Store Door No is required";
        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority = 25)
	public void specialdoorno() {
	    st.enterStoreDoorNo("140@va$#l");
	    st.clickNext();

	    Locator ae = page.locator("xpath=/html/body/div[1]/main/div/div/div/div[2]/div/div/div/div/div/form/div[1]/div[6]/div/div");
	   
	    String expectedMessage = "Only letters, numbers, spaces, dot, -, /, #, and commas are allowed";
	    String actualMessage = ae.innerText();
	   
	    Assert.assertEquals(actualMessage, expectedMessage, "Error message not displayed correctly");
	}

	
	
	@Test(priority=26)
    public void maxdoorno() {
		
		st.enterStoreDoorNo("12121121221");	
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Store Door No must not exceed 10 characters']").innerText();
        String ee = "Store Door No must not exceed 10 characters";

        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	

	@Test(priority=27)
    public void validstreetname() {
		
		st.enterStreetName("NGO Colony 2nd street");
		st.clickNext();
        page.waitForTimeout(2000);
        
        Locator errorLocator = page.locator("//div[normalize-space()='Street Name is required']");
        assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid street name");
        
	}
	
	@Test(priority=28)
    public void emptystreetname() {
		
		st.enterStorename("");
		st.clickNext();
        page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Street Name is required']").innerText();
        String ee = "Street Name is required";
        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority=29)
    public void specialcharacterssteetname() {
		
		st.enterStreetName("#NGO C@L@ny");
		st.clickNext();
        
		 Locator errorLocator = page.locator("//div[normalize-space()='Street Name is required']");
	     assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid street name");
	}
	
	@Test(priority=30)
    public void minstreetname() {
		
		st.enterStoreDoorNo("ng");
		st.clickNext();
		page.waitForTimeout(2000);
        
		Locator errorLocator = page.locator("//div[normalize-space()='Street Name must be at least 3 characters']");
	     assertFalse(errorLocator.isVisible(), "Street Name must be at least 3 characters");
        
	}
	
	@Test(priority=31)
    public void maxstreetname() {
		
		st.enterStreetName(" NGO Colony valls777777rtsvggganbhhbnbbahsahvhvhvgvhvhsgshagh");
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Street Name must not exceed 50 characters']").innerText();
        String ee = "Street Name must not exceed 50 characters";
        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority = 32)
	public void validLocality() {
		st.enterLocality("Chennai");
		st.clickNext();
		page.waitForTimeout(2000);

		Locator errorLocator = page.locator("//div[normalize-space()='Locality is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid locality name!");
	}

	@Test(priority = 33)
	public void emptyLocality() {
		st.enterLocality("");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Locality is required']").innerText();
		String ee = "Locality is required";

		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 34)
	public void specialCharacterLocality() {
		st.enterLocality("Ro#uke@la");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Only letters, commas, and single spaces are allowed!']").innerText();
		String ee = "Only letters, commas, and single spaces are allowed!";

		Assert.assertEquals(ae, ee, "Only letters, commas, and single spaces are allowed!");
	}

	@Test(priority = 35)
	public void lessCharacterLocality() {
		st.enterLocality("Ro");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Locality must be at least 3 characters']").innerText();
		String ee = "Locality must be at least 3 characters";
		Assert.assertEquals(ae, ee, "Locality must be at least 3 characters");
	}

	@Test(priority = 36)
	public void moreCharacterLocality() {
		st.enterLocality("Roukelabblkelakelalooekelajroukelaroukelaroukelaroukelaaarouuukela");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Locality must not exceed 50 characters']").innerText();
		String ee = "Locality must not exceed 50 characters";
		Assert.assertEquals(ae, ee, "Locality must not exceed 50 characters");
	}

	@Test(priority = 37)
	public void validEmail() {
		st.enterEmail("mathesh@example.com");
		st.clickNext();
		page.waitForTimeout(3000);

		Locator errorLocator = page.locator("//div[normalize-space()='Email is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid email!");
	}

	@Test(priority = 38)
	public void emptyEmail() {
		st.enterEmail("");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Email is required']").innerText();
		String ee = "Email is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 39)
	public void invalidEmail() {
		st.enterEmail("mathesh@ex");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Invalid email format']").innerText();
		String ee = "Invalid email format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 40)
	public void specialCharEmail() {
		st.enterEmail("m@th@sh@example.com");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Invalid email format']").innerText();
		String ee = "Invalid email format";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority=41)
    public void minemail() {
		
		st.enterEmail("a@a");
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Email must be at least 2 characters']").innerText();
        String ee = "Email must be at least 2 characters";
      
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	@Test(priority=42)
    public void maxemail() {
		
		st.enterEmail("uwegyfuygwefgewufgwehgfuwegfuegtwfygiewugfieugfriuwdjgfwhdegfuyewfgyehwfdwugyhvwdyfhwevfuweyghfwgufywhefewfya@a.com");
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Email must not exceed 100 characters']").innerText();
        String ee = "Email must not exceed 100 characters";
        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}	
	
	@Test(priority = 43)
	public void validPhoneNumber() {
		st.enterPhoneNumber("8997637599");
		st.clickNext();
		page.waitForTimeout(3000);
		Locator errorLocator = page.locator("//div[normalize-space()='Phone Number is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid Phone number!");
	}

	@Test(priority = 44)
	public void emptyPhoneNumber() {
		st.enterPhoneNumber("");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Phone Number is required']").innerText();
		String ee = "Phone Number is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 45)
	public void invalidPhoneNumber() {
		st.enterPhoneNumber("6472a58454");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Phone Number must be a valid 10-digit number starting with 6, 7, 8, or 9']").innerText();
		String ee = "Phone Number must be a valid 10-digit number starting with 6, 7, 8, or 9";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 46)
	public void lessCharPhoneNumber() {
		st.enterPhoneNumber("64726");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Phone Number must be a valid 10-digit number starting with 6, 7, 8, or 9']").innerText();
		String ee = "Phone Number must be a valid 10-digit number starting with 6, 7, 8, or 9";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 47)
	public void moreCharPhoneNumber() {
		st.enterPhoneNumber("9472687687653");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Phone Number must be a valid 10-digit number starting with 6, 7, 8, or 9']").innerText();
		String ee = "Phone Number must be a valid 10-digit number starting with 6, 7, 8, or 9";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	
     @Test(priority = 48)
     public void duplicatephonenumber() throws InterruptedException {
    	 st.fillpage();
    	 
    	 Locator error = page.locator("//div[@class='toast can-close progress error show']");
    	 assertTrue(error.isVisible(),"Error message not found for duplicate phone number");
    	 
     }
	@Test(priority = 49)
	public void validGst() {
		st.enterGSTNumber("29AAEPC1234R1Z5");
		st.clickNext();
		page.waitForTimeout(2000);
		Locator errorLocator = page.locator("//div[normalize-space()='GST number is required']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid GST!");
	}

	@Test(priority = 50)
	public void emptyGst() {
		st.enterGSTNumber("");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='GST number is required']").innerText();
		String ee = "GST number is required";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 51)
	public void invalidGst() {
		st.enterGSTNumber("29AAEPC#234R1Z5");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Invalid GST Number']").innerText();
		String ee = "Invalid GST Number";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 52)
	public void invalidLenGst() {
		st.enterGSTNumber("29AAEPC1876234R1Z5");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Invalid GST Number']").innerText();
		String ee = "Invalid GST Number";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	
	

	@Test(priority = 53)
	public void validWebsite() {
		st.enterWebsite("http://www.valar.com");
		st.clickNext();
		page.waitForTimeout(2000);
		Locator errorLocator = page.locator("//div[normalize-space()='Enter a valid website URL']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid website");
	}
	
	@Test(priority = 54)
	public void emptyWebsite() {
		st.enterWebsite("");
		st.clickNext();
		page.waitForTimeout(2000);
		Locator errorLocator = page.locator("//div[normalize-space()='Enter a valid website URL']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid website");
	}

	@Test(priority = 55)
	public void invalidWebsite() {
		st.enterWebsite("http://valar");
		st.clickNext();
		page.waitForTimeout(2000);

		String ae = page.locator("//div[normalize-space()='Enter a valid website URL']").innerText();
		String ee = "Enter a valid website URL";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 56)
	public void specialCharWebsite() {
		st.enterWebsite("http://www.v@l@r.com");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Enter a valid website URL']").innerText();
		String ee = "Enter a valid website URL";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 57)
	public void validFssai() {
		st.selectCategory("F&B");
		st.enterFSSAI("33001234592028");
		st.clickNext();
		page.waitForTimeout(2000);
		Locator errorLocator = page.locator("//div[normalize-space()='FSSAI number is required for F&B Store Type']");
		assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid FSSAI!");
	}

	@Test(priority = 58)
	public void emptyFssaiforFb() {
		 st.selectCategory("F&B");
		 st.enterFSSAI("");
		 st.clickNext();
		 page.waitForTimeout(2000);

	        String ae = page.locator("//div[normalize-space()='FSSAI number is required for F&B Store Type']").innerText();
	        String ee = "FSSAI number is required for F&B Store Type";
	        Assert.assertEquals(ae, ee, "Error message not displayed for F&B Store");
			
		}
	

	@Test(priority = 59)
	public void emptyFssaiforother() {
		 st.selectCategory("Fashion");
		 st.enterFSSAI("");
		 st.clickNext();
		 page.waitForTimeout(2000);

		 Locator errorLocator = page.locator("//div[normalize-space()='FSSAI number is required for F&B Store Type']");
		 assertFalse(errorLocator.isVisible(), "Error message is displayed even for a valid FSSAI!");
		}
		

	@Test(priority = 60)
	public void invalidFssai() {
		st.enterFSSAI("123ABC34567890");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='FSSAI must be a 14-digit number']").innerText();
		String ee = "FSSAI must be a 14-digit number";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 61)
	public void invalidLenFssai() {
		st.enterFSSAI("12345678945612354");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='FSSAI must be a 14-digit number']").innerText();
		String ee = "FSSAI must be a 14-digit number";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 62)
	public void specialCharFssai() {
		st.enterFSSAI("@@3456789012345");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='FSSAI must be a 14-digit number']").innerText();
		String ee = "FSSAI must be a 14-digit number";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 63)
	public void validUdhayamID() {
		st.enterUdhayamID("UDHAYAM98765");
		st.clickNext();
		page.waitForTimeout(2000);
		Locator errorLocator = page.locator("//div[normalize-space()='Udhayam ID must be 10-15 alphanumeric characters']");
		assertFalse(errorLocator.isVisible(), "Udhayam ID must be 10-15 alphanumeric characters");
	}

	
	@Test(priority = 64)
	public void emptyUdhayamID() {
		st.enterUdhayamID("");
		st.clickNext();
		page.waitForTimeout(2000);
		Locator errorLocator = page.locator("//div[normalize-space()='Udhayam ID must be 10-15 alphanumeric characters']");
		assertFalse(errorLocator.isVisible(), "Udhayam ID must be 10-15 alphanumeric characters");
	}

	@Test(priority = 65)
	public void invalidUdhayamID() {
		st.enterUdhayamID("123ABC");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Udhayam ID must be 10-15 alphanumeric characters']").innerText();
		String ee = "Udhayam ID must be 10-15 alphanumeric characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 66)
	public void invalidLenUdhayamID() {
		st.enterUdhayamID("UD123456987822458745");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Udhayam ID must be 10-15 alphanumeric characters']").innerText();
		String ee = "Udhayam ID must be 10-15 alphanumeric characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}
	@Test(priority = 67)
	public void specialCharUdhayamID() {
		st.enterUdhayamID("UD123456987822@@@");
		st.clickNext();
		page.waitForTimeout(2000);
		String ae = page.locator("//div[normalize-space()='Udhayam ID must be 10-15 alphanumeric characters']").innerText();
		String ee = "Udhayam ID must be 10-15 alphanumeric characters";
		Assert.assertEquals(ae, ee, "Error message not displayed");
	}

	@Test(priority = 68) // testcase 68
		public void nextButtonBehaviour() {
			st.uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
			page.waitForTimeout(2000);
	 
			st.enterStorename("Mathes Store");
			st.selectCategory("F&B");
			st.selectState("ANDHRA PRADESH");
			st.selectCity("GUNTUR");
			st.selectPincode("522001");
			st.enterStoreDoorNo("123");
			st.enterStreetName("Main Street");
			st.enterLocality("Chennai");
			st.enterEmail("mathesh@example.com");
			st.enterPhoneNumber("9876543210");
			st.enterGSTNumber("27ABCDE1234F1Z5");
			st.enterWebsite("https://matheshstore.com");
			st.enterFSSAI("33001234592028");
			st.enterUdhayamID("UDHAYAM98765");
			st.clickNext();
			page.waitForTimeout(2000);
	 
			page.waitForSelector("#rc-tabs-0-panel-StoreDetails div div form");
	 
			boolean isFormVisible = page.isVisible("#rc-tabs-0-panel-StoreDetails div div form");
			Assert.assertTrue(isFormVisible, "The form should be visible.");
	 
			page.waitForTimeout(2000);
		}
		
	 
		@Test(priority = 69) //Testcase 69
		public void invalidNextButtonBehaviour() {
		    st.uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		    page.waitForTimeout(2000);
		    st.clickNext();
		    page.waitForTimeout(2000);
	 
		    
			String ae = page.locator("//div[normalize-space()='Email is required']").innerText();
			String ee = "Email is required";
	 
			Assert.assertEquals(ae, ee, "Error message not displayed");
		}


}
