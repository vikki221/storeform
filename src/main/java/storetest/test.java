package storetest;

import com.microsoft.playwright.Browser;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import pageobjects.store1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class test {
	
	private Playwright playwright;
	private Browser browser;
	private Page page;
	private store1 st;

	@BeforeMethod
	public void testOpenWebsite() throws InterruptedException {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
		page.navigate("https://registeruat.valardigital.in/");
		System.out.println("Page title: " + page.title());

		st = new store1(page);
	}

	@Test
	public void validTest() {
		st.uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\logo.jpg");
		page.waitForTimeout(2000);
		st.enterStorename("Mathes Store");
		st.selectCategory("F&B");
		st.selectState("TAMIL NADU");
		st.selectCity("MADURAI");
		page.waitForTimeout(2000);
		st.selectPincode("625002");
		st.enterStoreDoorNo("123");
	    st.enterStreetName("Main Street");
	    st.enterLocality("Chennai");
	    st.enterEmail("mathesh@example.com");
	    st.enterPhoneNumber("9876543210");
	    st.enterGSTNumber("27ABCDE1234F1Z5");
	    st.enterWebsite("https://matheshstore.com");
	    st.enterFSSAI("FSSAI123467892");
	    st.enterUdhayamID("UDHAYAM98765");

	    page.waitForTimeout(3000);
	    st.clickNext();
	}
	
	@Test(priority=1)
	public void testUploadLogo() {
	    String logoPath = "C:\\Users\\VickySekar\\Downloads\\logo.jpg";
	    
	    st.uploadStoreLogo(logoPath);
	    page.waitForSelector("//*[name()='path' and contains(@d,'M942.2 486')]", new Page.WaitForSelectorOptions().setTimeout(5000));
	    
	    Locator uploadedLogo = page.locator("//*[name()='path' and contains(@d,'M942.2 486')]");
	    assertTrue(uploadedLogo.isVisible(), "Uploaded logo is not displayed!");
	    
	    uploadedLogo.click();
	    
	    page.waitForTimeout(2000);
	    
	    page.mouse().click(10, 10);
	    page.waitForTimeout(1000);
	    
	    assertTrue(uploadedLogo.isVisible(), "Uploaded logo is not visible after closing the view modal!");
	}
	

	@Test(priority=2)
	    public void verifyUnsupportedFileUpload() {
		 
		    st.uploadStoreLogo("C:\\Users\\VickySekar\\Downloads\\sample.txt");
	        page.waitForTimeout(3000);
	        st.clickNext();
	        
	        String expectedErrorMessage = "Unsupported format, must be JPEG, PNG";
	        String errorMessage = page.locator("//div[contains(@class, 'fnIpjs') and contains(text(), 'Unsupported format')]").innerText();
	        Assert.assertEquals(errorMessage, expectedErrorMessage, "Incorrect error message displayed!");

	    }

	@Test(priority=3)
    public void verifyViewLogo() {
        String logoPath = "C:\\Users\\VickySekar\\Downloads\\logo.jpg";
        
       
        st.uploadStoreLogo(logoPath);
        page.waitForSelector("//*[name()='path' and contains(@d,'M942.2 486')]", new Page.WaitForSelectorOptions().setTimeout(5000));

        Locator viewElement = page.locator("//*[name()='path' and contains(@d,'M942.2 486')]");
        assertTrue(viewElement.isVisible(), "View icon is not displayed!");
        viewElement.click();
        
        page.waitForTimeout(2000);  
        
        page.mouse().click(10, 10);  
        page.waitForTimeout(1000);

    }
	
	@Test(priority=4)
    public void verifyDeleteLogo() {
        String logoPath = "C:\\Users\\VickySekar\\Downloads\\logo.jpg";
        
       
        st.uploadStoreLogo(logoPath);
        
        Locator deleteElement = page.locator("//span[@aria-label='delete']//*[name()='svg']");
        assertTrue(deleteElement.isVisible(), "Delete icon is not displayed!");
        deleteElement.click();
    
        page.waitForTimeout(2000);
        assertFalse(page.locator("//div[@class='ant-upload-list-item']").isVisible(), "Logo is still visible after deletion!");
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
	
	@Test(priority=9)
    public void maxstorename() {
		
		st.enterStorename("valarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		st.clickNext();
		page.waitForTimeout(2000);
        
        String ae = page.locator("//div[normalize-space()='Store Name must not exceed 50 characters']").innerText();
        String ee = "Store Name must not exceed 50 characters";

        
        Assert.assertEquals(ae,ee,"Error message not displayed");
        
	}
	
	
	@Test(priority=10)
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
	
	@Test(priority=11)
	public void storeselect() {
		st.selectCategory("F&B");
		
	    Locator selectedElement = page.locator("//*[@id=\"rc-tabs-0-panel-StoreDetails\"]/div/div/div/form/div[1]/div[2]/div/div[2]/div/div/div");
	    assertEquals("F&B", selectedElement.textContent().trim());
		
	}
	
	@Test
	public void storetypenotselected() {
		st.clickNext();
		page.waitForTimeout(2000);
		
		String ae = page.locator("//div[normalize-space()='Please select a store type']").innerText();
		String ee = "Please select a store type";
		assertEquals(ae,ee,"Error message not displayed");
	}
	
	
	@Test
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
	
	@Test(priority=11)
	public void stateselect() {
		st.selectState("ASSAM");
		page.waitForTimeout(2000);
		
	    Locator selectedElement = page.locator("//*[@id=\"rc-tabs-0-panel-StoreDetails\"]/div/div/div/form/div[1]/div[3]/div/div/div/span/span[2]");
	    assertEquals("ASSAM", selectedElement.textContent().trim());
		
	}
	
	
	
	@Test
	public void citydd() {
		Locator dd = page.locator("//div[@name='address.city']//span[@title='Select an option...'][normalize-space()='Select an option...']");
		dd.click();
		page.waitForTimeout(2000);
		
		 List<Locator> dropdownOptions = page.locator("//div[contains(@class, 'ant-select-dropdown')]").all();
		 List<String> actualOptions = dropdownOptions.stream().map(Locator::textContent).map(String::trim).collect(Collectors.toList());
		 actualOptions.removeIf(option -> option.trim().equalsIgnoreCase("Select an option..."));

		 System.out.println("Dropdown options: " + actualOptions);
		    
		 assertFalse(actualOptions.isEmpty(), "Dropdown options list is empty!");
		
		
	}

	

	@AfterMethod
	public void closeBrowser() {
		if (browser != null) {
			browser.close();
		}
		if (playwright != null) {
			playwright.close();
		}
	}
}
