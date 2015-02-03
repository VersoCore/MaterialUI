package ricardob.layout.material.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HorizontalDirection;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ricardob.layout.material.MaterialLayoutHelper;

/**
 * Created by Ricardo on 30/01/2015.
 */
public class MaterialOverlayPane extends StackPane {
    private Timeline slideIn;
    private Timeline slideOut;

    private SimpleObjectProperty<HorizontalDirection> attachmentProperty = new SimpleObjectProperty<>();

    private SimpleBooleanProperty isInProperty = new SimpleBooleanProperty();
    private SimpleBooleanProperty isModalProperty = new SimpleBooleanProperty();

    public MaterialOverlayPane(HorizontalDirection attachment, boolean isModal) {
        attachmentProperty.set(attachment);

        ChangeListener<Object> modalPropertyRegisterer = (observable, oldValue, newValue) -> {
            if (newValue instanceof MaterialRoot) {
                ((MaterialRoot) newValue).registerOverlayPane(this);
            }

            if (oldValue instanceof MaterialRoot) {
                ((MaterialRoot) oldValue).unregisterOverlayPane(this);
            }
        };
        modalPropertyRegisterer.changed(null, null, this.getParent());
        isModalProperty.addListener(modalPropertyRegisterer);
        parentProperty().addListener(modalPropertyRegisterer);

        isModalProperty.set(isModal);

        if (attachmentProperty.get() == HorizontalDirection.LEFT) {
            this.setTranslateX(-10000);
        } else {
            this.setTranslateX(10000);
        }

        ChangeListener<Object> animationHandler = (observable, oldValue, newValue) -> {

            switch (attachmentProperty.get()) {
                case LEFT:
                    this.setLayoutX(0);
                    break;
                case RIGHT:
                    break;
            }

            slideIn = new Timeline();

            KeyValue positionKeyValueI = null;

            switch (attachmentProperty.get()) {
                case LEFT:
                    positionKeyValueI = new KeyValue(this.translateXProperty(), 0, MaterialLayoutHelper.SWIFT_INTERPOLATOR_IN_OUT);
                    break;
                case RIGHT:
                    positionKeyValueI = new KeyValue(this.translateXProperty(), this.getScene().getWidth() - this.getLayoutX() - this.getWidth(), MaterialLayoutHelper.SWIFT_INTERPOLATOR_IN_OUT);
                    break;
            }

            KeyFrame positionKeyFrameI = new KeyFrame(Duration.millis(500), positionKeyValueI);
            slideIn.getKeyFrames().add(positionKeyFrameI);

            slideOut = new Timeline();
            KeyValue positionKeyValueO = null;

            switch (attachmentProperty.get()) {
                case LEFT:
                    positionKeyValueO = new KeyValue(this.translateXProperty(), -getWidth(), MaterialLayoutHelper.SWIFT_INTERPOLATOR_IN_OUT);
                    break;
                case RIGHT:
                    positionKeyValueO = new KeyValue(this.translateXProperty(), this.getScene().getWidth() - this.getLayoutX(), MaterialLayoutHelper.SWIFT_INTERPOLATOR_IN_OUT);
                    break;
            }

            KeyFrame positionKeyFrameO = new KeyFrame(Duration.millis(500), positionKeyValueO);
            slideOut.getKeyFrames().add(positionKeyFrameO);
        };

        this.sceneProperty().addListener(animationHandler);
        this.widthProperty().addListener(animationHandler);
        this.layoutXProperty().addListener(animationHandler);
    }

    public void slideIn() {
        slideIn.play();
        this.getStyleClass().add("in");
        isInProperty.set(true);
    }

    public void slideOut() {
        slideOut.play();
        this.getStyleClass().remove("in");
        isInProperty.set(false);
    }

    public void slideInOut() {
        if (isInProperty.get()) {
            slideOut();
        } else {
            slideIn();
        }
    }

    public HorizontalDirection getAttachment() {
        return attachmentProperty.get();
    }

    public SimpleObjectProperty<HorizontalDirection> attachmentProperty() {
        return attachmentProperty;
    }

    public void setAttachment(HorizontalDirection attachment) {
        attachmentProperty.set(attachment);
    }

    public SimpleBooleanProperty isInProperty() {
        return isInProperty;
    }

    public boolean isIn() {
        return isInProperty.get();
    }

    public SimpleBooleanProperty isModalProperty() {
        return isModalProperty;
    }

    public boolean isModal() {
        return isModalProperty.get();
    }
}
