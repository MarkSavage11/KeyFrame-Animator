package cs3500.animator.provider.view;

import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.ImmutableModel;

import java.io.IOException;
import java.util.ArrayList;


/**
 * SVG view represents a view with SVG script rendering. The output will be a SVG representation of
 * the model. The immutable model passed to this class will Draw the compoenent of the SVG view and
 * create a full output.
 */
public class SVGView implements IView {
  private int speed;

  /**
   * Constructor for an SVG VIEW. Takes in an immutable model to render the SVG text version of the
   * model.
   *
   * @param speed the speed at which to run the animation.
   */
  public SVGView(int speed) {
    this.speed = speed;
  }

  @Override
  public void displayAnimation(ImmutableModel immutableModel, Appendable output) {

    if (immutableModel == null || output == null) {
      throw new IllegalArgumentException("Parameters specified cannot be null.");
    }

    helperAppend(output, "<svg ");

    svgRender(immutableModel, output);

    //add the svg ending tag
    helperAppend(output, "</svg>");


  }


  /**
   * Decodes a single line of the input text.
   *
   * @param model  the model to base the rendering off of.
   * @param output appendable to send output to.
   */
  private void svgRender(ImmutableModel model, Appendable output) {

    ArrayList<Integer> bounds = model.getCanvasBounds();

    helperAppend(output, "width= \"" + bounds.get(2) + "\" height=\"" + bounds.get(3) + "\" " +
            "version=\"1.1\"\n" + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    StringBuilder sBuilder = new StringBuilder("");

    int xOffSet = bounds.get(0);
    int yOffSet = bounds.get(1);

    for (IPicture p : model.getPictures()) {
      sBuilder.append(p.svgRender(xOffSet, yOffSet, speed));
    }

    helperAppend(output, sBuilder.toString());

  }

  /**
   * Helper to avoid duplicated try/catch in output appending. Throws IllegalArgumentException if
   * append fails.
   *
   * @param output the appendable of the view.
   * @param s      the string to be appended.
   */
  private void helperAppend(Appendable output, String s) {
    try {
      output.append(s);
    } catch (IOException e) {
      throw new IllegalArgumentException("Append Failed");
    }
  }
}
