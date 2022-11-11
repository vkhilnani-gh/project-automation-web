package com.company.coreortest.web.ui.webdriver.selenium.utils.ImageCapture.coordsProvider;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;

/**
 * Co-ordinate provider for Apple Mac OS (local run)
 */
public class MacCoordsProvider extends CoordsProvider {
    @Override
    public Coords ofElement(WebDriver driver, WebElement element) {
        Point point = element.getLocation();
        Dimension dimension = element.getSize();
        return new Coords(
                point.getX() *2,
                point.getY() * 2,
                (int) (dimension.getWidth() + (dimension.getWidth() * 1.1)),
                (int) (dimension.getHeight() + (dimension.getHeight() * 1.1)));
    }
}
