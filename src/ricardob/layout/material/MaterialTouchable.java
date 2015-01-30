package ricardob.layout.material;

import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;

public interface MaterialTouchable
{
  public Timeline touchTimeline = null;
  public Timeline hoverStartTimeline = null;
  SimpleDoubleProperty touchAnimationRadius = null;
  SimpleDoubleProperty hoverAnimationRadius = null;
  SimpleDoubleProperty mouseX = null;
  SimpleDoubleProperty mouseY = null;
  boolean pressed = false;
  boolean hover = false;
}
