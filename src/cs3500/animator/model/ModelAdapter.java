package cs3500.animator.model;

import java.util.ArrayList;

import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.IState;
import cs3500.animator.provider.model.ImmutableModel;

public class ModelAdapter implements ImmutableModel {

  AnimationModel model;

  ModelAdapter(AnimationModel model) {
    this.model = model;
  }

  @Override
  public ArrayList<IPicture> getPictures() {
    ArrayList<IPicture> pictures = new ArrayList<>();
    for (String s: model.getShapes().keySet()) {
      pictures.add(new IPictureAdapter(s));
    }
  }

  @Override
  public ArrayList<IState> getStatesAtTime(int currentTime) {

  }

  @Override
  public ArrayList<Integer> getCanvasBounds() {
    return null;
  }

  @Override
  public int lastTime() {
    return 0;
  }

}
