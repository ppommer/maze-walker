# Maze Walker

Programming exercise in the course of the practical lecture _Fundamentals of Programming_ (IN0002) at the Technical University of Munich.

This algorithm's aim is to help as many poor penguins as possible. The penguins are trapped in a maze from which there is no exit. The algorithm looks for the penguins and feeds them. In order to carry as many penguins as possible, the food stays at the starting point, so you have to find your way back there. To avoid getting lost, fields that do not have a wall on any of the eight surrounding fields are avoided. Furthermore, there is a maximum distance to the starting point, which is set by the user.

At the beginning, the program asks the user for the desired size of the labyrinth and the desired maximum distance from the starting point. After execution of the program, the number of penguins that could be rescued is displayed.

## Example
_width = 48 | length = 27 | max. distance = 100_
![alt text](https://raw.githubusercontent.com/ppommer/maze-walker/master/maze.jpg?token=AL6HJ7QSJ6XEFOVQIWRQMKC6MO4BQ)
