package ricardob.layout.material.components;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MaterialActionBar extends HBox
{
  private MaterialRoot root;

  private MaterialActionBarButton navigationMenu;
  private Label title;

  private ChangeListener<String> titleListener;

//  Timeline show;
//  Timeline hide;

  public MaterialActionBar(MaterialRoot root)
  {
    this.root = root;
    this.getStyleClass().addAll("actionbar");

//    show = new Timeline();
//    final KeyValue positionKeyValueS = new KeyValue(this.translateYProperty(), 0, PhysicsSimulator.instance.getLayoutHelper().SWIFT_INTERPOLATOR_IN_OUT);
//    final KeyFrame positionKeyFrameS = new KeyFrame(Duration.millis(500), positionKeyValueS);
//    show.getKeyFrames().add(positionKeyFrameS);
//
//    hide = new Timeline();
//    final KeyValue positionKeyValueH = new KeyValue(this.translateYProperty(), -64, PhysicsSimulator.instance.getLayoutHelper().SWIFT_INTERPOLATOR_IN_OUT);
//    final KeyFrame positionKeyFrameH = new KeyFrame(Duration.millis(500), positionKeyValueH);
//    hide.getKeyFrames().add(positionKeyFrameH);

    navigationMenu = new MaterialActionBarButton("ricardob/layout/material/icons/hamburger.png");
    this.setAlignment(Pos.CENTER_LEFT);
    this.setMargin(navigationMenu, new Insets(0, 16d, 0, 16d));
    navigationMenu.setOnAction(event -> this.root.getNavigation().slideInOut());
    this.getChildren().add(navigationMenu);

    title = new Label();
    title.getStyleClass().addAll("title");

    titleListener = (observable, oldValue, newValue) -> title.setText(newValue);

    root.getCurrentActivityProperty().addListener((observable, oldValue, newValue) -> {
      if(oldValue != null)
      {
        oldValue.titleProperty().removeListener(titleListener);
        title.setText("");
      }

      if(newValue != null)
      {
        newValue.titleProperty().addListener(titleListener);
        title.setText(newValue.titleProperty().get());
      }
    });

    this.setAlignment(Pos.CENTER_LEFT);
    this.getChildren().add(title);
  }
}
