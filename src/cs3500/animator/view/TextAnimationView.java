package cs3500.animator.view;

import java.io.IOException;
import java.util.Map;

import cs3500.animator.model.Animation;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyShape;

/**
 * An animation view that displays a textual representation of the given animation model.
 */
public class TextAnimationView implements AnimationView{

  private Appendable output;

  /**
   * Constructs a TextAnimationView with an appendable to write to.
   *
   * @param output the appendable this view writes the models information to
   */
  public TextAnimationView(Appendable output) {
    this.output = output;
  }

  /**
   * Displays a textual representation of the given models information.
   *
   * @param model the animation model to display.
   * @throws IllegalStateException if the appendable cannot be written to for any reason.
   */
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException {
    try {
      output.append(String.format("canvas %d %d %d %d\n", model.canvasPosition().x,
              model.canvasPosition().y, model.canvasSize().width, model.canvasSize().height));
      for (Map.Entry<String, ReadOnlyShape> shape : model.getShapes().entrySet()) {
        output.append(String.format("shape %s %s\n", shape.getKey(),
                shape.getValue().getType().toString()));

        for (Animation anim : shape.getValue().getAnimations()) {
          output.append(
                  String.format("motion %S %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d\n",
                          shape.getKey(), anim.startTick(),
                          anim.startState().getPosition().x, anim.startState().getPosition().y,
                          anim.startState().getSize().width, anim.startState().getSize().height,
                          anim.startState().getColor().getRed(),
                          anim.startState().getColor().getGreen(),
                          anim.startState().getColor().getBlue(),
                          anim.endTick(),
                          anim.endState().getPosition().x, anim.endState().getPosition().y,
                          anim.endState().getSize().width, anim.endState().getSize().height,
                          anim.endState().getColor().getRed(),
                          anim.endState().getColor().getGreen(),
                          anim.endState().getColor().getBlue()));
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write to output.");
    }
  }
}
