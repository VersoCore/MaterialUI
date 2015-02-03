package ricardob.layout.material.components;

import javafx.geometry.HorizontalDirection;
import javafx.scene.layout.VBox;

public class MaterialNavigationPane extends MaterialOverlayPane {
    private VBox root = new VBox();

    public MaterialNavigationPane() {
        super(HorizontalDirection.LEFT, true);

        this.getStyleClass().addAll("navigation");

        this.getChildren().add(root);
    }

    public VBox getRoot() {
        return root;
    }
}
