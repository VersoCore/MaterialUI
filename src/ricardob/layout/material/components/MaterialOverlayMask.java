package ricardob.layout.material.components;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MaterialOverlayMask extends StackPane {
    private final Timeline fadeIn;
    private final Timeline fadeOut;

    private SimpleBooleanProperty show = new SimpleBooleanProperty();

    public MaterialOverlayMask(BooleanBinding show) {
        this.getStyleClass().addAll("material", "overlay-mask");
        this.setOpacity(0);
        this.setMouseTransparent(true);

        final KeyValue overlayKeyValueI = new KeyValue(opacityProperty(), 0.6, Interpolator.EASE_BOTH);
        KeyFrame overlayKeyFrameI = new KeyFrame(Duration.millis(500), overlayKeyValueI);
        fadeIn = new Timeline(overlayKeyFrameI);

        final KeyValue overlayKeyValueO = new KeyValue(opacityProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame overlayKeyFrameO = new KeyFrame(Duration.millis(500), overlayKeyValueO);
        fadeOut = new Timeline(overlayKeyFrameO);

        this.show.bind(show);

        this.show.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                fadeIn.play();
            } else {
                fadeOut.play();
            }
        });
    }
}
