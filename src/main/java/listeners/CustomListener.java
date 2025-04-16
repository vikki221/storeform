package listeners;

import com.microsoft.playwright.Page;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Paths;

public class CustomListener implements ITestListener {

    private ExtentReports extentReports;
    private ExtentTest test;
    private Page page;

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./test-output/ExtentReports/CustomReport_" + timestamp + ".html");
        sparkReporter.config().setDocumentTitle("Custom Test Report");
        sparkReporter.config().setReportName("Playwright Test Report");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Store the listener instance in the context
        context.setAttribute("customListener", this);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test case failed");

        if (page != null) {
            // Capture screenshot
            String screenshotPath = "./test-output/screenshots/" + result.getMethod().getMethodName() + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
            test.addScreenCaptureFromPath(screenshotPath);
        } else {
            test.fail("Page object is null, cannot capture screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.fail("Test failed but within success percentage: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
