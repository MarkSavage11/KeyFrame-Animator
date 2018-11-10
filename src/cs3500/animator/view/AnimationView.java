package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimationModel;

public interface AnimationView {

    void display(ReadOnlyAnimationModel model) throws IllegalStateException;

}
