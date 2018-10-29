package animation.model;

import java.util.List;
import java.util.function.Predicate;

public enum AnimateableProperty {
  POSITION (2, (list) -> true),
  SIZE (2, (list) -> true),
  COLOR (3, (list) -> list.stream().allMatch((i) -> i >= 0 && i <= 255));

  private Predicate<List<Integer>> test;
  private int length;

  AnimateableProperty(int length, Predicate<List<Integer>> test) {
    this.length = length;
    this.test = test;
  }

  public boolean validState(List<Integer> state) {
    return state.size() == this.length && test.test(state);
  }

  public int getLength() {
    return this.length;
  }
}
