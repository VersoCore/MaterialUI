package ricardob.layout.material.components;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ricardob.layout.material.MaterialLayoutHelper;
import ricardob.layout.material.MaterialNavigationPane;

import java.util.logging.Logger;

public class MaterialRoot extends StackPane
{
  private static final Logger logger = Logger.getLogger(MaterialActivity.class.getName());
  private final BorderPane rootPane;

  private MaterialOverlayPane overlayPane;
  private MaterialActionBar actionBar;
  private MaterialNavigationPane navigation;

  private ReadOnlyObjectWrapper<MaterialActivity> currentActivityProperty = new ReadOnlyObjectWrapper<>();

  public MaterialRoot()
  {
      this.sceneProperty().addListener((observable, oldValue, newValue) -> {
          if(oldValue != null)
          {
              newValue.getStylesheets().remove(MaterialLayoutHelper.getResourcePath("ricardob/layout/material/css/material.css"));
          }
          if(newValue != null)
          {
              newValue.getStylesheets().add(MaterialLayoutHelper.getResourcePath("ricardob/layout/material/css/material.css"));
          }
      });


    this.getStyleClass().addAll("material", "_root");

    rootPane = new BorderPane();
    setMargin(rootPane, new Insets(64d, 0d, 0d, 0d));
    setAlignment(rootPane, Pos.BOTTOM_CENTER);
    getChildren().add(rootPane);

    overlayPane = new MaterialOverlayPane();
    getChildren().add(overlayPane);

    actionBar = new MaterialActionBar(this);
    setAlignment(actionBar, Pos.TOP_CENTER);
    getChildren().add(actionBar);

    navigation = new MaterialNavigationPane(overlayPane);
    setMargin(navigation, new Insets(64d, 0d, 0d, 0d));
    setAlignment(navigation, Pos.CENTER_LEFT);
    getChildren().add(navigation);


  }

  public MaterialOverlayPane getOverlayPane() {
    return overlayPane;
  }

  public MaterialActionBar getActionBar() {
    return actionBar;
  }

  public MaterialNavigationPane getNavigation() {
    return navigation;
  }

  public ReadOnlyObjectProperty<MaterialActivity> getCurrentActivityProperty()
  {
    return currentActivityProperty.getReadOnlyProperty();
  }

  public boolean requestActivitySwitch(MaterialActivity newActivity)
  {
    if(currentActivityProperty.get() == null)
    {
      logger.info("No activity currently enabled. Setting current activity to " + newActivity.titleProperty().get());
      setActivity(newActivity);
      return true;
    } else
    {
      logger.info("Activity switch requested by " + newActivity.titleProperty().get());

      boolean canBeSwitched = currentActivityProperty.get().requestActivitySwitch();

      if (canBeSwitched)
      {
        logger.info("Request granted. Switching " + currentActivityProperty.get().titleProperty().get() + " by " + newActivity.titleProperty().get() + "...");

        setActivity(newActivity);
      } else
      {
        logger.info("Current activity (" + currentActivityProperty.get().titleProperty().get() + " denied the request.");
      }

      return canBeSwitched;
    }
  }

  private void setActivity(MaterialActivity activity)
  {
    currentActivityProperty.set(activity);
    rootPane.setCenter(activity);
  }
}
