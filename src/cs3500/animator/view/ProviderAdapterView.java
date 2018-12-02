package cs3500.animator.view;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.ModelAdapter;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.provider.model.ImmutableModel;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.IAnimateView;
import cs3500.animator.provider.view.VisualAnimationView;

public class ProviderAdapterView implements AnimationView {

  private int speed;
  private IAnimationController controller;

  ProviderAdapterView (IAnimationController controller, int speed) {
    this.speed = speed;
    this.controller = controller;
  }

  @Override
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException {

    ImmutableModel adaptedModel = new ModelAdapter(model);

    IAnimateView providerView = new EditorView(new VisualAnimationView(adaptedModel, speed));
    providerView.setListener();
    //TODO Write an AnimationListener to go in these parens
    providerView.displayAnimationVisual();
  }

}
