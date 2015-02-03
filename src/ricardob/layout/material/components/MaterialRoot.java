package ricardob.layout.material.components;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ricardob.layout.material.MaterialLayoutHelper;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MaterialRoot extends StackPane {
    private static final Logger logger = Logger.getLogger(MaterialActivity.class.getName());
    private final BorderPane rootPane;

    private MaterialActionBar actionBar;
    private MaterialNavigationPane navigation;
    //private MaterialActivityOverlay;

    private ReadOnlyObjectWrapper<MaterialActivity> currentActivityProperty = new ReadOnlyObjectWrapper<>();
    private ReadOnlyStringWrapper title = new ReadOnlyStringWrapper();

    private MaterialOverlayMask modalMask;
    ArrayList<MaterialOverlayPane> overlayPanes = new ArrayList<>();
    BooleanBinding modalMaskShowing;
    ChangeListener<Boolean> modalMaskListener = (observable, oldValue, newValue) -> modalMaskShowing.invalidate();

    public MaterialRoot() {
        this.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                newValue.getStylesheets().remove(MaterialLayoutHelper.getResourcePath("ricardob/layout/material/css/material.css"));
            }
            if (newValue != null) {
                newValue.getStylesheets().add(MaterialLayoutHelper.getResourcePath("ricardob/layout/material/css/material.css"));
            }
        });

        this.getStyleClass().addAll("material", "_root");

        modalMaskShowing = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                for (MaterialOverlayPane pane : overlayPanes) {
                    if (pane.isModal() && pane.isIn())
                        return true;
                }
                return false;
            }
        };

        rootPane = new BorderPane();
        setMargin(rootPane, new Insets(64d, 0d, 0d, 0d));
        setAlignment(rootPane, Pos.BOTTOM_CENTER);
        getChildren().add(rootPane);

        modalMask = new MaterialOverlayMask(modalMaskShowing);
        this.getChildren().add(modalMask);

        actionBar = new MaterialActionBar(this);
        setAlignment(actionBar, Pos.TOP_CENTER);
        getChildren().add(actionBar);

        navigation = new MaterialNavigationPane();
        setMargin(navigation, new Insets(64d, 0d, 0d, 0d));
        setAlignment(navigation, Pos.CENTER_LEFT);
        getChildren().add(navigation);
    }

    public MaterialActionBar getActionBar() {
        return actionBar;
    }

    public MaterialNavigationPane getNavigation() {
        return navigation;
    }

    public void registerOverlayPane(MaterialOverlayPane pane) {
        overlayPanes.add(pane);
        pane.isInProperty().addListener(modalMaskListener);
        pane.isModalProperty().addListener(modalMaskListener);
        modalMaskShowing.invalidate();
    }

    public void unregisterOverlayPane(MaterialOverlayPane pane) {
        overlayPanes.remove(pane);
        pane.isInProperty().removeListener(modalMaskListener);
        pane.isModalProperty().removeListener(modalMaskListener);
        modalMaskShowing.invalidate();
    }

    public ReadOnlyObjectProperty<MaterialActivity> getCurrentActivityProperty() {
        return currentActivityProperty.getReadOnlyProperty();
    }

    public boolean requestActivitySwitch(MaterialActivity newActivity) {
        if (currentActivityProperty.get() == null) {
            logger.info("No activity currently enabled. Setting current activity to " + newActivity.titleProperty().get());
            setActivity(newActivity);
            return true;
        } else {
            logger.info("Activity switch requested by " + newActivity.titleProperty().get());

            boolean canBeSwitched = currentActivityProperty.get().requestActivitySwitch();

            if (canBeSwitched) {
                logger.info("Request granted. Switching " + currentActivityProperty.get().titleProperty().get() + " by " + newActivity.titleProperty().get() + "...");

                setActivity(newActivity);
            } else {
                logger.info("Current activity (" + currentActivityProperty.get().titleProperty().get() + " denied the request.");
            }

            return canBeSwitched;
        }
    }

    private void setActivity(MaterialActivity activity) {
        currentActivityProperty.set(activity);
        rootPane.setCenter(activity);
    }
}
