package ricardob.layout.material;

import javafx.animation.Interpolator;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MaterialLayoutHelper
{
  final static public Interpolator SWIFT_INTERPOLATOR = Interpolator.SPLINE(0.4, 0, 0.2, 1);
  final static public Interpolator SWIFT_INTERPOLATOR_IN_OUT = Interpolator.SPLINE(0, 0.5, 0.2, 1);

  private ColorScheme colorScheme;
  private MaterialColorPalette.Colors baseColor;
  private MaterialColorPalette.Colors accentColor;

  public MaterialLayoutHelper(ColorScheme colorScheme, MaterialColorPalette.Colors baseColor, MaterialColorPalette.Colors accentColor)
  {
    this.colorScheme = colorScheme;
    this.baseColor = baseColor;
    this.accentColor = accentColor;

    initializeFonts();
  }

  private void initializeFonts()
  {
    Font.loadFont(MaterialLayoutHelper.class.getClassLoader().getResourceAsStream("ricardob/layout/material/fonts/glyphicons-halflings-regular.ttf"), 10);
  }

  public Color getLightBaseColor()
  {
    return MaterialColorPalette.getShade(baseColor, "100");
  }

  public Color getBaseColor()
  {
    return MaterialColorPalette.getShade(baseColor, "500");
  }

  public Color getDarkBaseColor()
  {
    return MaterialColorPalette.getShade(baseColor, "700");
  }

  public Color getBaseColor(String customShade)
  {
    return MaterialColorPalette.getShade(baseColor, customShade);
  }

  public Color getLightAccentColor()
  {
    return MaterialColorPalette.getShade(accentColor, "A100");
  }

  public Color getAccentColor()
  {
    return MaterialColorPalette.getShade(accentColor, "A200");
  }

  public Color getAccentBaseColor()
  {
    return MaterialColorPalette.getShade(accentColor, "A400");
  }

  public Color getAccentColor(String customShade)
  {
    return MaterialColorPalette.getShade(accentColor, customShade);
  }

  public String getResourcePath(String internalPath) {
    return MaterialLayoutHelper.class.getClassLoader().getResource(internalPath).toExternalForm();
  }

  public enum ColorScheme
  {
    LIGHT, DARK
  }

}
