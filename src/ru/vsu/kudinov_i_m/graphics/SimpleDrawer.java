package ru.vsu.kudinov_i_m.graphics;

import ru.vsu.kudinov_i_m.screen.ScreenConverter;
import ru.vsu.kudinov_i_m.screen.ScreenCoordinates;
import ru.vsu.kudinov_i_m.screen.ScreenPoint;
import ru.vsu.kudinov_i_m.math.Vector2;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SimpleDrawer implements IDrawer {

    private Graphics2D graphics;
    private ScreenConverter screenConverter;

    public SimpleDrawer(Graphics2D graphics, ScreenConverter screenConverter) {
        this.graphics = graphics;
        this.screenConverter = screenConverter;
    }

    @Override
    public Graphics2D getGraphics() {
        return graphics;
    }

    public ScreenConverter getScreenConverter() {
        return screenConverter;
    }

    @Override
    public void draw(List<Vector2> points) {
        List<ScreenPoint> screenPoints = new LinkedList<>();
        getGraphics().setColor(Color.black);
        for (Vector2 vector2 : points) {
            screenPoints.add(getScreenConverter().realToScreen(vector2));
        }
        if(screenPoints.size() == 1) {
            getGraphics().fillRect(screenPoints.get(0).getScreenX(), screenPoints.get(0).getScreenY(), 1, 1);
        } else if(screenPoints.size() == 2){
            getGraphics().drawLine(screenPoints.get(0).getScreenX(), screenPoints.get(0).getScreenY(), screenPoints.get(1).getScreenX(), screenPoints.get(1).getScreenY());
        } else if(screenPoints.size() > 2) {
            ScreenCoordinates screenCoordinates = new ScreenCoordinates(screenPoints);
            getGraphics().drawPolygon(screenCoordinates.getXCoordinates(), screenCoordinates.getYCoordinates(), screenCoordinates.getLength());
        }
    }

    @Override
    public void clear(Color color) {
        Color oldColor = getGraphics().getColor();
        getGraphics().setColor(color);
        getGraphics().fillRect(0, 0, getScreenConverter().getScreenWidth(), getScreenConverter().getScreenHeight());
        getGraphics().setColor(oldColor);
    }
}
