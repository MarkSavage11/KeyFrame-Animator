package animation.model;

public class BasicAnimation implements Animation {

  @Override
  public int startTick() {
    return 0;
  }

  @Override
  public int endTick() {
    return 0;
  }

  @Override
  public boolean alignsWith(Animation other) {
    return false;
  }
}
