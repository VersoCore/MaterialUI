package ricardob.layout.material.components;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MaterialOverlayPane extends StackPane
{
  private final KeyFrame fadeIn;
  private final KeyFrame fadeOut;

  public MaterialOverlayPane()
  {
    this.getStyleClass().addAll("material", "overlay-pane");
    this.setOpacity(0);
    this.setMouseTransparent(true);

    final KeyValue overlayKeyValueI = new KeyValue(opacityProperty(), 0.6, Interpolator.EASE_BOTH);
    fadeIn = new KeyFrame(Duration.millis(500), overlayKeyValueI);

    final KeyValue overlayKeyValueO = new KeyValue(opacityProperty(), 0, Interpolator.EASE_BOTH);
    fadeOut = new KeyFrame(Duration.millis(500), overlayKeyValueO);
  }

  public KeyFrame getFadeIn() {
    return fadeIn;
  }

  public KeyFrame getFadeOut() {
    return fadeOut;
  }
}
