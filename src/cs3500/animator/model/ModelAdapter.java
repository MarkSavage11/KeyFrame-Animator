package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.IState;
import cs3500.animator.provider.model.ImmutableModel;

public class ModelAdapter implements ImmutableModel {

  ReadOnlyAnimationModel model;

  public ModelAdapter(ReadOnlyAnimationModel model) {
    this.model = model;
  }

  @Override
  public ArrayList<IPicture> getPictures() {
    ArrayList<IPicture> pictures = new ArrayList<>();
    for (String s: model.getShapes().keySet()) {
      pictures.add(new IPictureAdapter(s));
    }
    return pictures;
  }

  @Override
  public ArrayList<IState> getStatesAtTime(int currentTime) {
    ArrayList<IState> states = new ArrayList<>();
    for (ReadOnlyShape s : model.getShapes().values()) {
      states.add(new IStateAdapter(s.getType(), s.getStateAt(currentTime)));
    }
    return states;
  }

  @Override
  public ArrayList<Integer> getCanvasBounds() {
    ArrayList<Integer> bounds = new ArrayList<>();
    bounds.addAll(Arrays.asList(model.canvasPosition().x, model.canvasPosition().y,
            model.canvasSize().width, model.canvasSize().height));
    return bounds;
  }

  @Override
  public int lastTime() {
    return model.getLastTick();
  }

}
