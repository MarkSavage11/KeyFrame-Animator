-----------README--------------

Changes from last assignment:
	-An animation now stores its information of position, size, and color in a separate class called State, just for cleaner implementation.
	-Added read only interfaces for model and state in order to give view interfaces something safe to work with.
	-Added a few methods so that information could be easier gathered from the model, such as the shape and animations having a getStateAtTick method and interpolation stuff.

Summary:
	The views are structured very simply. Each view has a display method that is handed the model to display. Each view implementation has different constructor arguments to serve their separate purposes, such as appendables for the views that read to files, and frame rates for views that care about that. 

View Interface:
	Just has a display method that takes in a readonly animation model as an argument.

View Classes:
	TextAnimationView: 
		Takes in an appendable to write to during construction. Writes to that appendable in accordance to the textual formatting in the design specifications.
	VisualAnimationView:
		Takes in a framerate to dispaly with during construction. Displays an AnimationPanel that uses a Timer to display the state of all of the shapes at each tick, creating an animation.
	AnimationPanel:
		A Panel that extends JPanel. Iterates through a tick counter on a Timer that delays the appropriate amount for the given framerate. Displays the state of each shape on the given tick onto the panel.
	SVGAnimationView:
		Takes in an appendable to write to and a framerate to calculate times with during construction. Appends an SVG formatting of the model's animation information to the given appendable. 
