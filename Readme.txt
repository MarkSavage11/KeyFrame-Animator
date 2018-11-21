-----------README--------------

Changes from last last assignment:
	-An animation now stores its information of position, size, and color in a separate class
	called State, just for cleaner implementation.
	-Added read only interfaces for model and state in order to give view interfaces
	something safe to work with.
	-Added a few methods so that information could be easier gathered from the model,
	such as the shape and animations having a getStateAtTick method and interpolation stuff.

Changes from last assignment
    -Added keyframes to the model, so that it can convert keyframes to the animations that it stores
    it can also delete shapes and animations. A model can also give its earliest and latest tick
    -Added a Controller interface and class, which just interfaces between an EditableAnimationView
    and a model
    -Implemented an EditableAnimationView that allows the user to view and modify an animation

Summary:
	Hw06 The views are structured very simply. Each view has a display method that is handed the model
	to display. Each view implementation has different constructor arguments to serve their separate
	purposes, such as appendables for the views that read to files,
	and frame rates for views that care about that.

	Hw07 The EditableAnimationView is an interactable view that has a visualization of the animation,
	as well as options for changing the playback for that animation (Play, pause, restart, loop, change
	framerate) and editing the animation by adding shapes and keyframes. The player is very self
	explanatory. The animation editor can add and delete shapes, and the shapes can be selected to
	reveal their keyframes, which can also be added, edited, and deleted. If the user tries something
	illegal, they get a dialogue pop up that describes how what they did was wrong.

View Interface:
	Just has a display method that takes in a readonly animation model as an argument.

View Classes:
	TextAnimationView: 
		Takes in an appendable to write to during construction. Writes to that appendable in
		accordance to the textual formatting in the design specifications.
	VisualAnimationView:
		Takes in a framerate to display with during construction. Displays an AnimationPanel that
		uses a Timer to display the state of all of the shapes at each tick, creating an animation.
	AnimationPanel:
		A Panel that extends JPanel. Iterates through a tick counter on a Timer that delays the
		appropriate amount for the given framerate. Displays the state of each shape on the
		given tick onto the panel.
	SVGAnimationView:
		Takes in an appendable to write to and a framerate to calculate times with during
		construction. Appends an SVG formatting of the model's animation information to the
		given appendable.
	EditableAnimationView:
	    Takes in a controller and a speed. The controller holds a model. The view also calls display
	    to initialize its view with the given model. Each swing component uses anonymous functions
	    to act on its view, and to send the controller requests to modify the model, such as add shape
	    or edit keyframe.

Controller Interface:
    Has methods that the view can call to edit the model, including addShape, deleteShape, etc. It
    also has a method run, which runs the given view.

Controller Class:
    A basic implementation of the controller interface. Gets told by the view to edit the model. Also
    is responsible for displaying any model errors to the user in a dialogue box.
