package cs3500.animator.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import cs3500.animator.provider.view.IActionListener;

public class KeyListenerAdapter implements KeyListener {

  IActionListener listener;

  Map<Character, String> keyToName;

  public KeyListenerAdapter(IActionListener listener) {
    this.listener = listener;

    keyToName.put('p', "Play");
    keyToName.put('u', "Pause");
    keyToName.put('r', "Restart");
    keyToName.put('i', "Increase Speed");
    keyToName.put('d', "Decrease Speed");
    keyToName.put('l', "Looping Toggle");
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyToName.containsKey(e.getKeyChar())) {
      listener.action(keyToName.get(e.getKeyChar()));
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
