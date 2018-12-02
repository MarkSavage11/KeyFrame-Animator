package cs3500.animator.provider.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.ImmutableModel;


/**
 * Class to represent a text animation. The type of view will simply read in the input and output
 * the animation in the text form.
 */
public class TextView implements IView {

  @Override
  public void displayAnimation(ImmutableModel model, Appendable output)
          throws IllegalArgumentException {

    if (model == null || output == null) {
      throw new IllegalArgumentException("Parameters specified cannot be null.");
    }

    StringBuilder sBuilder = new StringBuilder("");

    ArrayList<Integer> array = model.getCanvasBounds();

    sBuilder.append("canvas " + array.get(0) + " " + array.get(1) + " " + array.get(2) +
            " " + array.get(3) + "\n");
    for (IPicture p : model.getPictures()) {
      sBuilder.append(p.asString());

    }
    try {
      output.append(sBuilder);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}
