package storetest;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import listeners.CustomListener;
import pageobjects.store1;

public class BaseClass {
    private Playwright playwright;
    private Browser browser;
    protected Page page;
    protected store1 st;

    @Parameters("browser")
    @BeforeMethod
    public void testOpenWebsite(@Optional("chromium") String browserType, ITestContext context) throws InterruptedException {
        playwright = Playwright.create();

        if (browserType.equalsIgnoreCase("edge")) {
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setChannel("msedge")
                    .setArgs(java.util.Arrays.asList("--start-maximized"))
            );
        } else if (browserType.equalsIgnoreCase("brave")) {
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setChannel("brave")
                    .setHeadless(true)
                    .setArgs(java.util.Arrays.asList("--start-maximized"))
            );
        } else {
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setArgs(java.util.Arrays.asList("--start-maximized"))
            );
        }

        BrowserContext browserContext = browser.newContext(
            new Browser.NewContextOptions().setViewportSize(null)
        );

        page = browserContext.newPage();
        page.navigate("https://registeruat.valardigital.in/");
        st = new store1(page);

        // Set the page object in the listener after it is initialized
        CustomListener listener = (CustomListener) context.getAttribute("customListener");
        if (listener != null) {
            listener.setPage(page);
        }
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
