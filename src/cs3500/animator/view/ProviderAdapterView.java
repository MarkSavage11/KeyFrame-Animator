package cs3500.animator.view;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.ModelAdapter;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.provider.model.ImmutableModel;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.IActionListener;
import cs3500.animator.provider.view.IAnimateView;
import cs3500.animator.provider.view.VisualAnimationView;

public class ProviderAdapterView implements AnimationView {

  private int speed;
  private IAnimationController controller;

  public ProviderAdapterView (IAnimationController controller, int speed) {
    this.speed = speed;
    this.controller = controller;
  }

  @Override
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException {

    ImmutableModel adaptedModel = new ModelAdapter(model);
    IAnimateView providerView = new EditorView(new VisualAnimationView(adaptedModel, speed));
    IActionListener listener = new AdaptedAnimationListener(controller, providerView, model);

    providerView.setListener(listener, new KeyListenerAdapter(listener));

    providerView.displayAnimationVisual();
  }

}
