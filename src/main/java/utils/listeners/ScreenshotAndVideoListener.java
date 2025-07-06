package utils.listeners;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static utils.PropertiesLoader.getProp;


public class ScreenshotAndVideoListener implements IInvokedMethodListener {

    private static final String fileSeparator = System.getProperty("file.separator");
    private static final String homeDir = System.getProperty("user.home") + fileSeparator + "failedTestResult";


    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult tr) {
        if (WebDriverRunner.hasWebDriverStarted() ) {
            if (!tr.isSuccess()) {
                System.out.println("***** Error. Test failed. " + tr.getName());

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

                String testClassName = getTestClassName(tr.getInstanceName());
                String methodName = tr.getName() + df.format(calendar.getTime());
                String screenShotName = methodName + ".png";

                String imgPath = homeDir + fileSeparator + testClassName + fileSeparator
                        + screenShotName;
                System.out.println("Screenshot is here : " + imgPath);
                System.out.println("file:///" + imgPath.replace("\\", "//"));

                File scrFile = Selenide.screenshot(OutputType.FILE);

                File screenShotFile = new File(imgPath);
                try {
                    FileUtils.copyFile(scrFile, screenShotFile);
                    try {
                        getScreenshot(imgPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("An exception occurred while taking screenshot " + e.getCause());
                }
                String URL_TO_FAILED_VIDEO = getProp("remoteServerURL") + ":4444/video/" + Selenide.sessionId() + ".mp4";
                Allure.addAttachment("Link to video of failed tests running", "text/uri-list", URL_TO_FAILED_VIDEO);
            }
        }
    }

    @Attachment
    public static byte[] getScreenshot(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("", resourceName));
    }

    private String getTestClassName(String testName) {
        String[] testClassName = testName.split("\\.");
        int i = testClassName.length - 1;
        String n = testClassName[i];

        System.out.println("Class name " + n);
        return n;
    }
}
