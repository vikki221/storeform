package storetest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pageobjects.store1;

public class BaseClass {
	private Playwright playwright;
	private Browser browser;
	protected Page page;
	protected store1 st;


    @Parameters("browser")
    @BeforeMethod
    public void testOpenWebsite(@Optional("chromium") String browserType) throws InterruptedException {
        playwright = Playwright.create();

        if (browserType.equalsIgnoreCase("edge")) {
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setChannel("msedge")
                    .setArgs(java.util.Arrays.asList("--start-maximized"))
            );
        }else if (browserType.equalsIgnoreCase("brave")) {
            browser = playwright.chromium().launch(
            	new BrowserType.LaunchOptions()
                    .setChannel("brave")
                    .setHeadless(true)
                    .setArgs(java.util.Arrays.asList("--start-maximized"))
            );
        }
        
        else {
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setArgs(java.util.Arrays.asList("--start-maximized"))
            );
        }

        BrowserContext context = browser.newContext(
            new Browser.NewContextOptions().setViewportSize(null)
        );

        page = context.newPage();
        page.navigate("https://registeruat.valardigital.in/");
        st = new store1(page);
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
