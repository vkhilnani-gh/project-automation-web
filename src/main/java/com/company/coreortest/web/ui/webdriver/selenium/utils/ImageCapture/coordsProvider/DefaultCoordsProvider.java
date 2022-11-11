package com.company.coreortest.web.ui.webdriver.selenium.utils.ImageCapture.coordsProvider;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;

/**
 * Default Co-ordinate provider for (@link ImageCaptureUtils)
 * Mainly used with Windows OS and Browserstack
 */
public class DefaultCoordsProvider extends CoordsProvider {
    @Override
    public Coords ofElement(WebDriver driver, WebElement element) {
        Point point = element.getLocation();
        Dimension dimension = element.getSize();
        return new Coords(
                point.getX(),
                point.getY(),
                dimension.getWidth(),
                dimension.getHeight());
    }
}
