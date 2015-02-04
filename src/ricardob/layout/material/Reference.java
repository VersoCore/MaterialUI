package ricardob.layout.material;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Ricardo on 04/02/2015.
 */
public class Reference {

    private static Properties props;

    public static final String VERSION;
    public static Date BUILD_DATE;

    static {
        props = new Properties();

        try (InputStream inputStream = Reference.class.getResourceAsStream("materialUI.build")) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        VERSION = props.getProperty("PROJECT_VERSION");
        try {
            BUILD_DATE = new SimpleDateFormat("ddMMyyyyhhmm").parse(String.valueOf(props.get("PROJECT_BUILD_DATE")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
