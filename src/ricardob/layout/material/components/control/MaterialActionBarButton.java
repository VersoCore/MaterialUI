package ricardob.layout.material.components.control;

import javafx.scene.image.ImageView;

public class MaterialActionBarButton extends javafx.scene.control.Button {
    public MaterialActionBarButton(String imagePath) {
        this.getStyleClass().addAll("button");
        this.setGraphic(new ImageView(MaterialActionBarButton.class.getClassLoader().getResource(imagePath).toExternalForm()));
    }
}
