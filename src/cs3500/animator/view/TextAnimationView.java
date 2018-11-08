package cs3500.animator.view;

import java.util.Arrays;

import cs3500.animator.model.ReadOnlyAnimationModel;

public class TextAnimationView implements AnimationView{

  private Appendable output;

  public TextAnimationView(Appendable output) {
    this.output = output;
  }

  @Override
  public void display(ReadOnlyAnimationModel model) {
    output.append(String.format("canvas %d %d %d %d\n", model.canvasPosition().x,
            model.canvasPosition().y, model.canvasSize().width, model.canvasSize().height);

  }
}
