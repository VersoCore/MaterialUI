package ricardob.layout.material;

import javafx.animation.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ricardob.layout.material.components.MaterialOverlayPane;

public class MaterialNavigationPane extends VBox
{
  private final MaterialOverlayPane overlayPane;

  private final Timeline slideIn;
  private final Timeline slideOut;
  private boolean in = false;

  public MaterialNavigationPane(MaterialOverlayPane overlayPane)
  {
    this.overlayPane = overlayPane;
    this.translateXProperty().setValue(-300);
    this.getStyleClass().addAll("navigation");

    slideIn = new Timeline();
    final KeyValue positionKeyValueI = new KeyValue(this.translateXProperty(), 0, MaterialLayoutHelper.SWIFT_INTERPOLATOR_IN_OUT);
    final KeyFrame positionKeyFrameI = new KeyFrame(Duration.millis(500), positionKeyValueI);
    slideIn.getKeyFrames().add(positionKeyFrameI);
    slideIn.getKeyFrames().add(overlayPane.getFadeIn());

    slideOut = new Timeline();
    final KeyValue positionKeyValueO = new KeyValue(this.translateXProperty(), -300, MaterialLayoutHelper.SWIFT_INTERPOLATOR_IN_OUT);
    final KeyFrame positionKeyFrameO = new KeyFrame(Duration.millis(500), positionKeyValueO);
    slideOut.getKeyFrames().add(positionKeyFrameO);
    slideOut.getKeyFrames().add(overlayPane.getFadeOut());

    focusedProperty().addListener((observable, oldValue, newValue) -> slide(!newValue));
  }

  public void slideIn()
  {
    slideIn.play();
    this.getStyleClass().add("in");
    in = true;

  }

  public void slideOut()
  {
    slideOut.play();
    this.getStyleClass().remove("in");
    in = false;
  }

  public void slideInOut()
  {
    if(in)
    {
      slideOut();
    } else
    {
      slideIn();
    }
  }

  public void slide(boolean in)
  {
    if(in)
    {
      slideIn();
    } else
    {
      slideOut();
    }
  }
}
