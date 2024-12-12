import java.util.ArrayList;

import processing.core.*;
import java.util.Scanner;
import java.nio.file.Paths;

public class App extends PApplet {
    ArrayList<Block> blocks;
    int moves = 0;
    int FileMoves = 0;
    int time = 0; // need milis in here
    private int[] colors;
    private int[] ballColors;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(143, 155, 176);
        blocks = new ArrayList<>();
        blockMaker();
        // readMoves();

    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        for (Block B : blocks) {
            B.display();
        }
    }

    public void gameSet() {
        background(214, 139, 167);
    }

    public void blockMaker() { /// draw the grid
        colors = new int[] { color(130, 103, 191), // light purple
                color(34, 107, 201), // blue
                color(237, 201, 40), // yellow
                color(37, 217, 76), // light green
                color(36, 201, 163), // teal
                color(237, 111, 43), // orange
                color(201, 26, 196), // pink
                color(112, 28, 201), // purple
                color(138, 30, 70) // maroon
        };
        ballColors = new int[] { color(130, 103, 191), // light purple
                color(34, 107, 201), // blue
                color(237, 201, 40), // yellow
                color(37, 217, 76), // light green
                color(36, 201, 163), // teal
                color(237, 111, 43), // orange
                color(201, 26, 196), // pink
                color(112, 28, 201), // purple
                color(138, 30, 70) // maroon
        };

        strokeWeight(5);
        int y = 100;
        int colorPos = 0;
        for (int rows = 0; rows < 3; rows++) {
            int x = 100;
            for (int elements = 0; elements < 3; elements++) {
                Block block = new Block(x, y, 200, 200, this, colors[colorPos], ballColors[colorPos]);
                blocks.add(block);
                x += 200;
                colorPos++;
            }
            y += 200;

        }
        blocks.get(8).changeVisible(); // hide the last ball
    }

    public void mousePressed() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.isMouseInside(mouseX, mouseY)) {
                System.out.println("mouse pressed");
                // check right
                if (i % 3 != 2) {
                    Block rightBlock = blocks.get(i + 1);
                    if (rightBlock.ballVisible() == false) {
                        rightBlock.changeVisible();
                        // b.getColor();
                        // b.setColor();
                        b.changeVisible();
                        moves++;
                    }
                }
                // check left
                if (i % 3 != 0) {
                    Block leftBlock = blocks.get(i - 1);
                    if (leftBlock.ballVisible() == false) {
                        leftBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
                // check up
                if (i / 3 != 0) {
                    Block upBlock = blocks.get(i - 3);
                    if (upBlock.ballVisible() == false) {
                        upBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
                // check down
                if (i / 3 != 2) {
                    Block downBlock = blocks.get(i + 3);
                    if (downBlock.ballVisible() == false) {
                        downBlock.changeVisible();
                        b.changeVisible();
                        moves++;
                    }
                }
            }
        }
    }
    public void readMoves(){
        //  // we create a scanner for reading the file
        //  try (Scanner scanner = new Scanner(Paths.get("Moves.txt"))) {

        //     // we read the file until all lines have been read
        //     while (scanner.hasNextLine()) {
        //         // we read one line
        //         String row = scanner.nextLine();
        //         // we print the line that we read
        //         FileMoves = Integer.ValueOf(row);
        //     }
        // } catch (Exception e) {
        //     System.out.println("Error: " + e.getMessage());
        // }
    }
}
