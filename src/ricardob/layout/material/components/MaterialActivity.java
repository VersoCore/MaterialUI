package ricardob.layout.material.components;

import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.layout.BorderPane;

/**
 * Created by Ricardo on 29/01/2015.
 */
public abstract class MaterialActivity extends BorderPane
{
    protected ReadOnlyStringWrapper title = new ReadOnlyStringWrapper();

    private Timeline entering, exiting; //TODO

    public boolean requestActivitySwitch() {
        return true; //TODO
    }

    public ReadOnlyStringProperty titleProperty()
    {
        return title.getReadOnlyProperty();
    }
}
