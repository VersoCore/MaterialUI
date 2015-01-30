package ricardob.layout.material;

import javafx.animation.Interpolator;

public class MaterialLayoutHelper
{
  final static public Interpolator SWIFT_INTERPOLATOR = Interpolator.SPLINE(0.4, 0, 0.2, 1);
  final static public Interpolator SWIFT_INTERPOLATOR_IN_OUT = Interpolator.SPLINE(0, 0.5, 0.2, 1);

  public static String getResourcePath(String internalPath) {
    return MaterialLayoutHelper.class.getClassLoader().getResource(internalPath).toExternalForm();
  }
}
