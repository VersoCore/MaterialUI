package ricardob.layout.material.components.control;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.logging.Logger;

/**
 * Created by Ricardo on 28/01/2015.
 */
public class MaterialNavigationItem extends Button {
    private static final Logger logger = Logger.getLogger(MaterialNavigationItem.class.getName());

    public MaterialNavigationItem() {
        super();
        this.getStyleClass().add("item");

        setupLogging();
    }

    public MaterialNavigationItem(String text) {
        super(text);
        this.getStyleClass().add("item");

        setupLogging();
    }

    public MaterialNavigationItem(String text, Node graphic) {
        super(text, graphic);
        this.getStyleClass().add("item");

        setupLogging();
    }

    private void setupLogging() {
        pressedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                logger.info("Naigation button " + this.getText() + " was pressed.");
            }
        });
    }


}
