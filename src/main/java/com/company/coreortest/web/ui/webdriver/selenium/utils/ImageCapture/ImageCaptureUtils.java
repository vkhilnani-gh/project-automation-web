package com.company.coreortest.web.ui.webdriver.selenium.utils.ImageCapture;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import com.company.coreortest.web.ui.exception.AutomationException;
import com.company.coreortest.web.ui.webdriver.selenium.utils.ImageCapture.coordsProvider.DefaultCoordsProvider;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.DiffMarkupPolicy;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.comparison.ImageMarkupPolicy;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

/**
 * Image capture utility that provides various methods to capture screenshots and compare images.
 */
public class ImageCaptureUtils {
    private final WebDriver webDriver;
    private final String timeStamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
    private String filename;
    private final DiffMarkupPolicy diffMarkupPolicy = new ImageMarkupPolicy();
    private CoordsProvider coordsProvider = new DefaultCoordsProvider();
    private static final String IMAGE_OUTPUT_PATH = ".//build/reports/screenshots/";
    private final AShot aShot = new AShot();
    private int numberPixels = 0;
    private ShootingStrategy shootingStrategy = ShootingStrategies.viewportPasting(100);

    public ImageCaptureUtils(WebDriver webDriver) {
        if (webDriver == null) {
            throw new AutomationException("Web driver cannot be null");
        }
                this.webDriver = webDriver;
    }

    /**
     * Provide the right mapping for the co-ordinate of an element.
     * @param coordsProvider e.g 'new MacCoordsProvider()'. Default (if not set) is 'new DefaultCoordsProvider()'
     * @return ImageCaptureUtils instance.
     */
    public ImageCaptureUtils setCoordsProvider(CoordsProvider coordsProvider) {
        this.coordsProvider = coordsProvider;
        return this;
    }

    private File getScreenshotFile()
    {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    }

    /**
     * Method to capture screenshot in build/reports/screenshots folder.
     * @param screenshotName (String) - name of screenshot without extension.
     */
    public void captureScreenshot(String screenshotName) {
        filename = screenshotName + "_" + timeStamp + ".png";
        try {
            Files.createDirectories(Paths.get(IMAGE_OUTPUT_PATH));
            FileHandler.copy(getScreenshotFile(), new File(IMAGE_OUTPUT_PATH + filename));
        } catch (Exception e) {
            throw new AutomationException("Screenshot could not be captured" + e);
        }
    }

    /**
     *
     * @param screenshotName (String) - name of screenshot without extension.
     * @param outputFilePath (String) - user defined file path to store the captured screenshot. e.g .//screenshots
     */
    public void captureScreenshot(String screenshotName, String outputFilePath) {
        filename = "/" + screenshotName + "_" + timeStamp + ".png";
        try {
            Files.createDirectories(Paths.get(outputFilePath));
            FileHandler.copy(getScreenshotFile(), new File(outputFilePath + filename));
        } catch (Exception e) {
            throw new AutomationException("Screenshot could not be captured" + e);
        }
    }

    /**
     * Make scroll bar disappear before taking screenshot.
     * @return ImageCaptureUtils instance.
     */
    public ImageCaptureUtils removeScrollBars() {
        ((JavascriptExecutor) webDriver).executeScript("document.body.style.overflow = 'hidden';");
        return this;
    }

    /**
     * Adds element to ignore before comparing image.
     * @param element - WebElement to ignore.
     * @return
     */
    public ImageCaptureUtils addIgnoredElement(WebElement element) {
        aShot.addIgnoredArea(coordsProvider.ofElement(webDriver, element));
        return this;
    }

    /**
     * Sets the area to be ignored when taking full screenshot.
     * @param x - x-axis, 0 is on the top left of the screen.
     *          Sets the starting point of the rectangular area in horizontal perspective.
     * @param y - y-axis, 0 is on the top left of the screen.
     *          Sets the starting point of the rectangular area in vertical perspective.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     * @return ImageCaptureUtils instance.
     */
    public ImageCaptureUtils addIgnoredArea (int x, int y, int width, int height) {
        aShot.addIgnoredArea(new Coords(x,y, width, height));
        return this;
    }

    public ImageCaptureUtils compareThreshold(int numberPixels) {
        this.numberPixels = numberPixels;
        return this;
    }

    public ImageCaptureUtils hideElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.position='hidden'", element);
        return this;
    }

    public ImageCaptureUtils keepPositionElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.position='relative'", element);
        return this;
    }

    public ImageCaptureUtils setShootingStrategy(ShootingStrategy shootingStrategy) {
        this.shootingStrategy = shootingStrategy;
        return this;
    }

    /**
     * Method to compare images and store the difference, if any, in build/reports/screenshots folder
     * @param baseImageFilePath (String) - file path to capture base image e.g './/screenshots/baseimages'
     * @param baseImageName (String) - base image name with .png format
     * @param element
     * @return (boolean) - Return true if there is a difference between base Image and actual image(captured during
     * the test run)
     * @throws IOException
     */
    public boolean compareImage(String baseImageFilePath, String baseImageName, WebElement... element)
            throws IOException {
        //Process Base Image from given path. Create new image if base image does not exist.
        Screenshot expectedImage;
        try {
            Files.createDirectories(Paths.get(baseImageFilePath));
            Files.createDirectories(Paths.get(IMAGE_OUTPUT_PATH));
        } catch (Exception e) {
            throw new AutomationException(e);
        }
        String imagePath = baseImageFilePath + "/" + baseImageName;
        try {
            expectedImage = new Screenshot(ImageIO.read(new File(imagePath)));
        } catch (IOException e) {
            if (Arrays.asList(element).isEmpty()) {
                expectedImage = captureWholePageImage();
            } else {
                expectedImage = captureElementImage(element);
            }
            saveImageToPath(imagePath, expectedImage);
        }

        //Process Actual Image from the test run
        Screenshot actualImage;
        if (Arrays.asList(element).isEmpty()) {
            actualImage = captureWholePageImage();
        } else {
            actualImage = captureElementImage(element);
        }

        //Process Base Image for any ignored areas
        expectedImage.setIgnoredAreas(actualImage.getIgnoredAreas());

        //Compare Base and Actual Images
        ImageDiff imgDiff = new ImageDiffer()
                .withDiffMarkupPolicy(diffMarkupPolicy.withDiffColor(Color.MAGENTA))
                .makeDiff(expectedImage, actualImage)
                .withDiffSizeTrigger(numberPixels);
        Screenshot diffImage = new Screenshot(imgDiff.getMarkedImage());

        //Save Actual and Difference Image if not matching
        String outputImagePath = IMAGE_OUTPUT_PATH + baseImageName;
        if (imgDiff.hasDiff()) {
            String actImagepath = outputImagePath.replace(".png", "_Actual.png");
            saveImageToPath(actImagepath, actualImage);

            String diffImagePath = outputImagePath.replace(".png", "_Diff.png");
            saveImageToPath(diffImagePath, diffImage);
        }
        //Return true if there is a difference between the images
        return imgDiff.hasDiff();
    }

    private Screenshot captureElementImage(WebElement[] element) {
        return aShot.coordsProvider(coordsProvider)
                .takeScreenshot(webDriver, Arrays.asList(element));
    }

    private Screenshot captureWholePageImage() {
        return aShot.coordsProvider(coordsProvider)
                .shootingStrategy(this.shootingStrategy)
                .takeScreenshot(webDriver);
    }

    private void saveImageToPath(String imagePath, Screenshot screenshot) throws IOException {
        ImageIO.write(screenshot.getImage(), "png", new File(imagePath));
    }
}
