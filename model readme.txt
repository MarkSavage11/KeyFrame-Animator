--------------------------/README/--------------------------

Summary:
	This model design is structured to hold a HashMap that links a name/ID to a Shape object
	The Shape object itself has a name and type (shape types include Rectangles and Ellipses)
	and holds a list of Animation objects, which describe the starting state and the ending 
	state of the shape. The model can add shapes to the model, and add animations to shapes. 
	It can also display a String representation, which displays the name and type of each shape,
	as well as its animations, displayed by showing each value for the starting state
	and the ending state.


//Interfaces//

AnimationModel:
	This interface can add shapes to the model through the method addShape(), and add animations
	to shapes with the method addAnimation(String shapeName, Animation anim)
	This model is implemented with the concrete class: AnimationModelImpl, which also can show 
	a String representation of the model with its toString method.

Shape:
	This interface represents a shape, which holds a name, a ShapeType, and a list of animations. 
	A shape can add an Animation to its list, but ensures that no animation overlaps with eachother.
	This is implemented in ShapeImpl

Animation:
	This interface represents an animation. It holds all the information of the start state and end state.
	There is a startTick, startPosition, startSize, startColor, endTick, endPosition, endSize, endColor.
	It can also determine if it conflicts with another Animation.

//Enum//

ShapeType:
	This enum represents a type of shape, such as rectangle or ellipse. It holds a value for a string 
	representation of the type, which it can display with the toString method.