AreaRectangleUnion
==================
The input is a set of rectangles in the plane, all of whose edges are parallel to the axis. This program computes the total area they occupy. Overlapping areas count only once. In other words, if you think of these rectangles as colored red, the question is to find the total red area.

The input is given as n lines (from the standard input), one line per rectangle, each line consisting of four integers (in this order): X-left, Y-bottom, X-right, Y-top. The first two are the coordinates of the lower left corner and the last two are the coordinates of the upper right corner.

The output is just one number, printed in the standard output: the area.

For example, the input

10 5 20 15
15 10 25 20
0 0 12 17

should have the output: 359
