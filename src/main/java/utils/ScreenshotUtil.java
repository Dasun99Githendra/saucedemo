package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots during test execution.
 * Helps track and debug test flows, especially useful for failed scenarios.
 */
public class ScreenshotUtil {

    // Format date to yyyy-MM-dd
    private static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    // Format time to HH-mm-ss for unique filenames
    private static String getCurrentTime() {
        return new SimpleDateFormat("HH-mm-ss").format(new Date());
    }

    /**
     * Captures a screenshot and stores it using test class name and timestamped filename.
     *
     * @param driver         The active WebDriver instance.
     * @param testClassName  The name of the test class.
     * @param fileName       The screenshot file name prefix.
     * @throws IOException   If the file cannot be saved.
     */
    public static void takeScreenshot(WebDriver driver, String testClassName, String fileName) throws IOException {
    	
    	// Capture screenshot as a file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        // Generate dynamic folder and filename using date and time
        String dateFolder = getCurrentDate();
        String timeSuffix = getCurrentTime();
        String folderPath = "screenshots" + File.separator + testClassName + File.separator + dateFolder;
        String fullFileName = fileName + "_" + timeSuffix + ".png";

        File destFile = new File(folderPath + File.separator + fullFileName);

        // Create directories if they don't exist
        destFile.getParentFile().mkdirs();
        
        // Copy the screenshot file to target location
        FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * Optional overload for fixed file path saving - if needs to use a full custom output path directly
     */
    public static void takeScreenshot(WebDriver driver, String outputPath) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(outputPath);
        FileUtils.copyFile(srcFile, destFile);
    }
}

