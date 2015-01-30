package ricardob.layout.material.components;

import javafx.scene.control.Button;


public class MaterialButton extends Button
{

  public MaterialButton()
  {
    this.getStyleClass().addAll("material");

    this.textProperty().addListener((observable, oldText, text) -> textProperty().setValue(text.toUpperCase()));
  }
}
