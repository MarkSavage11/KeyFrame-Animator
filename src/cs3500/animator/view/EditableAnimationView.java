package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyShape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;
import cs3500.animator.model.StateImpl;


public class EditableAnimationView extends JFrame implements AnimationView {

  private JList<String> shapeList;
  private JButton deleteShapeButton;
  private JTextField addShapeNameBox;
  private JComboBox<String> addShapeTypeBox;

  private JList<String> keyFrameList;
  private JButton deleteKeyFrameButton;
  private JTextField editX = new JTextField(2);
  private JTextField editY = new JTextField(2);
  private JTextField editW = new JTextField(2);
  private JTextField editH = new JTextField(2);
  private JTextField editR = new JTextField(2);
  private JTextField editG = new JTextField(2);
  private JTextField editB = new JTextField(2);
  private JButton editKeyFrameButton;
  private JTextField addT = new JTextField(2);
  private JTextField addX = new JTextField(2);
  private JTextField addY = new JTextField(2);
  private JTextField addW = new JTextField(2);
  private JTextField addH = new JTextField(2);
  private JTextField addR = new JTextField(2);
  private JTextField addG = new JTextField(2);
  private JTextField addB = new JTextField(2);
  private JButton addKeyFrameButton;

  private AnimationPanel displayPanel;
  private JTextField speedTextField;

  private ReadOnlyAnimationModel model;
  private final IAnimationController controller;
  private int speed; //Speed in ticks per second

  private boolean isLooping = false;
  private Timer timer;
  private int firstTick = 1;
  private int lastTick = 1;

  /**
   * Constructs a visual animation view that shows an animation of the given model.
   */
  public EditableAnimationView(IAnimationController controller, int speed) {
    super();
    this.controller = controller;
    this.speed = speed;
    this.setTitle("Excellent");
    this.setPreferredSize(new Dimension(1150, 600));
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.timer = new Timer(1000 / speed, (ActionEvent e) -> {
      if (isLooping && displayPanel.getTick() >= lastTick) {
        displayPanel.setTick(firstTick);
      } else {
        displayPanel.setTick(displayPanel.getTick() + 1);
      }
      displayPanel.repaint();
    });
    this.timer.start();
  }

  /**
   * Displays the given model's animation, while also allowing for interaction and editing of the
   * model.
   *
   * @param model the animation model to display.
   * @throws IllegalStateException this method does not throw this exception.
   */
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException {
    this.model = model;
    resetTickBounds();
    displayPanel = new AnimationPanel(model);
    displayPanel.setPreferredSize(new Dimension(500, 500));
    JScrollPane displayScrollPane = new JScrollPane(displayPanel);

    JPanel editorPanel = new JPanel(new GridLayout(2, 2, 15, 10));
    editorPanel.setPreferredSize(new Dimension(625, 600));

    JPanel shapeDisplayPanel = new JPanel(new BorderLayout());
    shapeDisplayPanel.add(new JLabel("Shapes"), BorderLayout.PAGE_START);
    shapeList = new JList<>(this.getShapesInfoAsArray());
    shapeList.setLayoutOrientation(JList.VERTICAL);
    shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapeList.addListSelectionListener((ListSelectionEvent e) -> {
      keyFrameList.setListData(getKeyFrameInfoAsArray());
      deleteShapeButton.setEnabled(true);
      deleteKeyFrameButton.setEnabled(false);
      editKeyFrameButton.setEnabled(false);
      addKeyFrameButton.setEnabled(true);
    });
    shapeDisplayPanel.add(new JScrollPane(shapeList), BorderLayout.CENTER);
    deleteShapeButton = new JButton("Delete");
    deleteShapeButton.setEnabled(false);
    deleteShapeButton.addActionListener((ActionEvent e) -> {
      deleteShape();
      this.shapeList.setListData(getShapesInfoAsArray());
      resetTickBounds();
      this.deleteKeyFrameButton.setEnabled(false);
      this.editKeyFrameButton.setEnabled(false);
      this.addKeyFrameButton.setEnabled(false);
    });
    shapeDisplayPanel.add(deleteShapeButton, BorderLayout.PAGE_END);

    JPanel keyFrameDisplayPanel = new JPanel(new BorderLayout());
    keyFrameDisplayPanel.add(new JLabel("Key Frames"), BorderLayout.PAGE_START);
    keyFrameList = new JList<>(this.getKeyFrameInfoAsArray());
    keyFrameList.setLayoutOrientation(JList.VERTICAL);
    keyFrameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    keyFrameList.addListSelectionListener((ListSelectionEvent e) -> {
      this.deleteKeyFrameButton.setEnabled(true);
      this.editKeyFrameButton.setEnabled(true);
    });
    keyFrameDisplayPanel.add(new JScrollPane(keyFrameList), BorderLayout.CENTER);
    deleteKeyFrameButton = new JButton("Delete");
    deleteKeyFrameButton.setEnabled(false);
    deleteKeyFrameButton.addActionListener((ActionEvent e) -> {
      deleteKeyFrame();
      this.keyFrameList.setListData(getKeyFrameInfoAsArray());
      resetTickBounds();
      this.deleteKeyFrameButton.setEnabled(false);
      this.editKeyFrameButton.setEnabled(false);
    });
    keyFrameDisplayPanel.add(deleteKeyFrameButton, BorderLayout.PAGE_END);

    JPanel addShapePanel = new JPanel();
    addShapePanel.setPreferredSize(new Dimension(150, 300));
    JLabel nameLabel = new JLabel("Name");
    addShapeNameBox = new JTextField(7);

    JLabel shapeLabel = new JLabel("Shape");
    addShapeTypeBox = new JComboBox<>(new String[]{"Rectangle", "Ellipse"});

    JButton addShapeButton = new JButton("Add Shape");
    addShapeButton.setPreferredSize(new Dimension(200, 30));
    addShapeButton.addActionListener((ActionEvent e) -> {
      addShape();
      this.shapeList.setListData(getShapesInfoAsArray());
      resetTickBounds();
    });

    addShapePanel.add(nameLabel);
    addShapePanel.add(addShapeNameBox);
    addShapePanel.add(shapeLabel);
    addShapePanel.add(addShapeTypeBox);
    addShapePanel.add(addShapeButton);

    JPanel keyFrameModifyPanel = new JPanel();
    JPanel editKeyFramePanel = new JPanel();
    editKeyFramePanel.setPreferredSize(new Dimension(190, 100));
    JPanel addKeyFramePanel = new JPanel();
    addKeyFramePanel.setPreferredSize(new Dimension(240, 100));
    editKeyFramePanel.add(new JLabel("x "));
    editKeyFramePanel.add(editX);
    editKeyFramePanel.add(new JLabel("y "));
    editKeyFramePanel.add(editY);
    editKeyFramePanel.add(new JLabel("w "));
    editKeyFramePanel.add(editW);
    editKeyFramePanel.add(new JLabel("h "));
    editKeyFramePanel.add(editH);
    editKeyFramePanel.add(new JLabel("r "));
    editKeyFramePanel.add(editR);
    editKeyFramePanel.add(new JLabel("g "));
    editKeyFramePanel.add(editG);
    editKeyFramePanel.add(new JLabel("b "));
    editKeyFramePanel.add(editB);
    editKeyFrameButton = new JButton("Edit");
    editKeyFrameButton.setPreferredSize(new Dimension(150, 30));
    editKeyFrameButton.addActionListener((ActionEvent e) -> {
      editKeyFrame();
      this.keyFrameList.setListData(getKeyFrameInfoAsArray());
      resetTickBounds();
      this.editKeyFrameButton.setEnabled(false);
      this.deleteKeyFrameButton.setEnabled(false);
    });
    editKeyFrameButton.setEnabled(false);
    editKeyFramePanel.add(editKeyFrameButton);

    addKeyFramePanel.add(new JLabel("t "));
    addKeyFramePanel.add(addT);
    addKeyFramePanel.add(new JLabel("x "));
    addKeyFramePanel.add(addX);
    addKeyFramePanel.add(new JLabel("y "));
    addKeyFramePanel.add(addY);
    addKeyFramePanel.add(new JLabel("w "));
    addKeyFramePanel.add(addW);
    addKeyFramePanel.add(new JLabel("h "));
    addKeyFramePanel.add(addH);
    addKeyFramePanel.add(new JLabel("r "));
    addKeyFramePanel.add(addR);
    addKeyFramePanel.add(new JLabel("g "));
    addKeyFramePanel.add(addG);
    addKeyFramePanel.add(new JLabel("b "));
    addKeyFramePanel.add(addB);
    addKeyFrameButton = new JButton("Add");
    addKeyFrameButton.setPreferredSize(new Dimension(150, 30));
    addKeyFrameButton.addActionListener((ActionEvent e) -> {
      addKeyFrame();
      this.keyFrameList.setListData(getKeyFrameInfoAsArray());
      resetTickBounds();
    });
    addKeyFrameButton.setEnabled(false);
    addKeyFramePanel.add(addKeyFrameButton);


    keyFrameModifyPanel.add(editKeyFramePanel);
    keyFrameModifyPanel.add(addKeyFramePanel);

    editorPanel.add(shapeDisplayPanel);
    editorPanel.add(keyFrameDisplayPanel);
    editorPanel.add(addShapePanel);
    editorPanel.add(keyFrameModifyPanel);

    JPanel viewerPanel = new JPanel(new BorderLayout());
    viewerPanel.setPreferredSize(new Dimension(500, 600));

    JPanel playerPanel = new JPanel();
    playerPanel.setPreferredSize(new Dimension(500, 100));
    JButton restartButton = new JButton("Restart");
    restartButton.addActionListener((ActionEvent e) -> {
      displayPanel.setTick(1);
      timer.restart();
    });
    JButton playButton = new JButton("Play");
    playButton.addActionListener((ActionEvent e) -> timer.start());
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener((ActionEvent e) -> timer.stop());
    this.speedTextField = new JTextField("" + this.speed, 2);
    JButton changeSpeedButton = new JButton("Change");
    changeSpeedButton.addActionListener((ActionEvent e) -> {
      try {
        this.speed = Integer.parseInt(speedTextField.getText());
        timer.setDelay(1000 / speed);
      } catch (Exception error) {
        this.showError(error.getMessage());
      }
    });
    JCheckBox isLoopingBox = new JCheckBox("Looping", false);
    isLoopingBox.addActionListener((ActionEvent e) -> this.isLooping = !isLooping);

    playerPanel.add(restartButton);
    playerPanel.add(playButton);
    playerPanel.add(pauseButton);
    playerPanel.add(new JLabel("Speed: "));
    playerPanel.add(speedTextField);
    playerPanel.add(changeSpeedButton);
    playerPanel.add(isLoopingBox);


    viewerPanel.add(displayScrollPane, BorderLayout.CENTER);
    viewerPanel.add(playerPanel, BorderLayout.PAGE_END);


    this.add(editorPanel, BorderLayout.WEST);
    this.add(viewerPanel, BorderLayout.EAST);


    this.pack();
    this.setVisible(true);
  }

  private String[] getShapesInfoAsArray() {
    String[] result = new String[model.getShapes().size()];
    int count = 0;
    for (Map.Entry<String, ReadOnlyShape> shape : model.getShapes().entrySet()) {
      result[count] = String.format("%s  |  %s",
              shape.getKey(), shape.getValue().getType().toString());
      count++;
    }
    return result;
  }

  private String[] getKeyFrameInfoAsArray() {
    if (shapeList.getSelectedValue() == null) {
      return new String[0];
    }
    Scanner nameParse = new Scanner(shapeList.getSelectedValue());
    ReadOnlyShape shape = this.model.getShape(nameParse.next());
    String[] result = new String[shape.getKeyframes().size()];

    int count = 0;
    for (Map.Entry<Integer, State> frame : shape.getKeyframes().entrySet()) {
      result[count] = String.format("t: %d x: %d y: %d w: %d h: %d Color: (%d, %d, %d)",
              frame.getKey(), frame.getValue().getPosition().x, frame.getValue().getPosition().y,
              frame.getValue().getSize().width, frame.getValue().getSize().height,
              frame.getValue().getColor().getRed(), frame.getValue().getColor().getGreen(),
              frame.getValue().getColor().getBlue());
      count++;
    }
    return result;
  }


  private void addShape() {
    ShapeType type = null;
    switch ((String) addShapeTypeBox.getSelectedItem()) {
      case "Ellipse":
        type = ShapeType.ELLIPSE;
        break;
      case "Rectangle":
        type = ShapeType.RECTANGLE;
        break;
      default:
        break;
    }
    this.controller.addShape(addShapeNameBox.getText(), type);
  }

  private void deleteShape() {
    Scanner nameParse = new Scanner(shapeList.getSelectedValue());
    this.controller.deleteShape(nameParse.next());

  }

  private void addKeyFrame() {
    Scanner nameParse = new Scanner(shapeList.getSelectedValue());
    try {
      controller.addKeyframe(nameParse.next(), Integer.parseInt(addT.getText()), new StateImpl(
              new Point(Integer.parseInt(addX.getText()), Integer.parseInt(addY.getText())),
              new Dimension(Integer.parseInt(addW.getText()), Integer.parseInt(addH.getText())),
              new Color(Integer.parseInt(addR.getText()), Integer.parseInt(addG.getText()),
                      Integer.parseInt(addB.getText()))));
    } catch (Exception e) {
      this.showError(e.getMessage());
    }

  }

  private void editKeyFrame() {
    Scanner nameParse = new Scanner(shapeList.getSelectedValue());
    Scanner tickParse = new Scanner(keyFrameList.getSelectedValue());
    tickParse.next();
    try {
      controller.editKeyFrame(nameParse.next(), tickParse.nextInt(), new StateImpl(
              new Point(Integer.parseInt(editX.getText()), Integer.parseInt(editY.getText())),
              new Dimension(Integer.parseInt(editW.getText()), Integer.parseInt(editH.getText())),
              new Color(Integer.parseInt(editR.getText()), Integer.parseInt(editG.getText()),
                      Integer.parseInt(editB.getText()))));
    } catch (Exception e) {
      this.showError(e.getMessage());
    }
  }

  private void deleteKeyFrame() {
    Scanner nameParse = new Scanner(shapeList.getSelectedValue());
    String shapeName = nameParse.next();
    Scanner tickParse = new Scanner(keyFrameList.getSelectedValue());
    tickParse.next();
    int tick = tickParse.nextInt();
    controller.deleteKeyFrame(shapeName, tick);
  }

  private void resetTickBounds() {
    this.firstTick = model.getFirstTick();
    this.lastTick = model.getLastTick();
  }

  private void showError(String error) {
    JOptionPane.showMessageDialog(new JFrame(), error, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
