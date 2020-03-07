import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FindPingu extends Maze {
  static int[][] maze;

  public static int read(String text) {
    JFrame frame = new JFrame();
    String s = JOptionPane.showInputDialog(frame, text);
    frame.dispose();

    int x;
    if (s == null)
      System.exit(0);
    try {
      x = Integer.parseInt(s.trim());
    } catch (NumberFormatException e) {
      x = read(text);
    }
    return x;
  }

  public static void write(String output) {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame, output, "Ausgabe", JOptionPane.PLAIN_MESSAGE);
    frame.dispose();
  }

  public static void main(String[] args) {
    int width = read("Please enter the width of the maze:");
    int height = read("Please enter the height of the maze:");;
    maze = generateStandardPenguinMaze(width, height);
    int maxDistance = read("Please enter the maximum distance:");
    if(width < 3 || height < 3 || maxDistance < 1) {
      write("Wrong input!");
      return;
    }
    int penguins = walk(1, 0, maxDistance);
    System.out.println("Number of rescued penguins: " + penguins);
  }

  // test if there is a wall at the given coordinates
  public static boolean wall(int x, int y) {
    return maze[x][y] == WALL;
  }

  // test if there is no wall at the given coordinates; coordinates can be outside the maze
  public static boolean noWall(int x, int y) {
    if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length)
      return false;
    return maze[x][y] != WALL;
  }

  public static int walk(int x, int y, int maxDistance) {
    if(maxDistance < 0)
      return 0;

    // are we going to walk out of the maze?
    if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length)
      return 0;

    // are we going to walk on a field without wall?
    if (noWall(x - 1, y - 1) && noWall(x - 1, y) && noWall(x - 1, y + 1) && noWall(x, y - 1)
        && noWall(x, y) && noWall(x, y + 1) && noWall(x + 1, y - 1) && noWall(x + 1, y)
        && noWall(x + 1, y + 1))
      return 0;

    // are we going to walk into a wall or on a field we have already visited?
    if (maze[x][y] == WALL || maze[x][y] == OLD_PATH_ACTIVE || maze[x][y] == OLD_PATH_DONE) {
      return 0;
    }

    int penguins = maze[x][y] == PENGUIN ? 1 : 0;

    // we are walking on the field
    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_ACTIVE;

    penguins += walk(x + 1, y, maxDistance - 1);

    // at this point, we have returned from recursion and re-visit the field
    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_ACTIVE;

    penguins += walk(x, y + 1, maxDistance - 1);

    // at this point, we have returned from recursion and re-visit the field
    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_ACTIVE;

    penguins += walk(x - 1, y, maxDistance - 1);

    // at this point, we have returned from recursion and re-visit the field
    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_ACTIVE;

    penguins += walk(x, y - 1, maxDistance - 1);

    // at this point, we have returned from recursion and re-visit the field
    maze[x][y] = PLAYER;
    draw(maze);

    // search in all directions completed
    maze[x][y] = OLD_PATH_DONE;

    return penguins;
  }
}
