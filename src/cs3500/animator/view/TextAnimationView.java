package cs3500.animator.view;

import java.io.IOException;
import java.util.Map;

import cs3500.animator.model.Animation;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyShape;

public class TextAnimationView implements AnimationView{

  private Appendable output;

  public TextAnimationView(Appendable output) {
    this.output = output;
  }

  @Override
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException {
    try {
      output.append(String.format("canvas %d %d %d %d\n", model.canvasPosition().x,
              model.canvasPosition().y, model.canvasSize().width, model.canvasSize().height));
      for (Map.Entry<String, ReadOnlyShape> shape : model.getShapes().entrySet()) {
        output.append(String.format("shape %s %s\n", shape.getKey(),
                shape.getValue().getType().toString()));

        for (Animation anim : shape.getValue().getAnimations()) {
          output.append(
                  String.format("motion %S %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d",
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
