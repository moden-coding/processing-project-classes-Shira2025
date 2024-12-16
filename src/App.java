import java.util.ArrayList;

import processing.core.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class App extends PApplet {
    ArrayList<Block> blocks;
    int moves = 0;
    int highScore = 10000;
    int time = 0; // need milis in here
    int[] colors;
    int[] ballColors;
    int scene = 0;
    boolean correct = true;
    int rightPlace = 0;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() { // make the lists and check list and make blocks
        blocks = new ArrayList<>();
        blockMaker();
        readHighScore();
    }

    public void settings() {// Set the size of the window
        size(800, 800);
    }

    public void draw() {
        if (scene == 0) {
            gameSet();
        } else if (scene == 1) {
            instrucions();
        } else if (scene == 2) {
            gamePlay();
        } else if (scene == 3) {
            notGamePlay();
        }
    }

    public void gameSet() { // the start screen
        background(143, 155, 176);// blue gray
        textSize(100);
        fill(174, 52, 235);
        text("Solve It", 250, 100);
        fill(128, 237, 230);
        textSize(35);
        rect(300, 400, 200, 100);// for instructions
        rect(300, 600, 200, 100); // for gameplay
        fill(0);
        text("Instructions", 320, 450);
        textSize(60);
        text("Play", 340, 670);
    }

    public void gamePlay() { // showing balls and gamescreen
        background(92, 109, 138);// dark blue gray
        for (Block B : blocks) {
            strokeWeight(5);
            B.display();
        }
        strokeWeight(2);
        fill(34, 107, 201); //pink box
        rect(50, 710, 125, 75);// back box
        fill(0);
        textSize(45);
        text("Back", 60, 760);
    }

    public void instrucions() {
        background(66, 245, 179);// light green
        strokeWeight(2);
        fill(34, 107, 201); //pink box
        rect(50, 710, 125, 75);// back box
        fill(0);
        textSize(45);
        text("Back", 60, 760);
    }

    public void notGamePlay() { // moves screen
        background(143, 155, 176);// blue gray
        readHighScore();
        if (highScore > moves) {
            highScore = moves;
            saveMoves();
        }
        textSize(50);
        fill(0);
        text("Moves:", 300, 300);
        text(moves, 450, 300);
        text("High score:", 300, 500);
        text(highScore, 550, 500);
    }

    public void keyPressed() { // maybe a keypressed for highscore
        if (key == ' ') {
            scene = 2;
        } else if (key == 'v') {
            scene = 3;
        }
    }

    public void blockMaker() { /// draw the grid
        colors = new int[] {
                color(130, 103, 191), // light purple
                color(34, 107, 201), // blue
                color(237, 201, 40), // yellow
                color(37, 217, 76), // light green
                color(36, 201, 163), // teal
                color(237, 111, 43), // orange
                color(201, 26, 196), // pink
                color(112, 28, 201), // purple
                color(138, 30, 70) // maroon
        };
        ballColors = new int[] {
                color(138, 30, 70), // maroon
                color(112, 28, 201), // purple
                color(201, 26, 196), // pink
                color(237, 111, 43), // orange
                color(37, 217, 76), // light green
                color(237, 201, 40), // yellow
                color(34, 107, 201), // blue
                color(130, 103, 191), // light purple
                color(36, 201, 163) // teal
        };

        strokeWeight(5);
        int y = 100;
        int colorPos = 0;
        for (int rows = 0; rows < 3; rows++) {
            int x = 100;
            for (int elements = 0; elements < 3; elements++) {
                Block block = new Block(x, y, 200, 200, this, colors[colorPos], ballColors[colorPos]);
                // ball and block should be random
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
                // check right
                if (i % 3 != 2) {
                    Block rightBlock = blocks.get(i + 1);
                    checkBlockPos(b, rightBlock);
                }
                // check left
                if (i % 3 != 0) {
                    Block leftBlock = blocks.get(i - 1);
                    checkBlockPos(b, leftBlock);
                }
                // check up
                if (i / 3 != 0) {
                    Block upBlock = blocks.get(i - 3);
                    checkBlockPos(b, upBlock);
                }
                // check down
                if (i / 3 != 2) {
                    Block downBlock = blocks.get(i + 3);
                    checkBlockPos(b, downBlock);
                }
            }
        }
        if (mouseX > 300 && mouseY > 400 && mouseX < 300 + 200 && mouseY < 400 + 100) {// instructions
            scene = 1;
        }
        if (mouseX > 300 && mouseY > 600 && mouseX < 300 + 200 && mouseY < 600 + 100) {// gameplay
            scene = 2;
        }
        if (mouseX > 50 && mouseY > 710 && mouseX < 50 + 125 && mouseY < 710 + 75) {// gameplay
            scene = 0;
        }
    }

    public void saveMoves() {
        try (PrintWriter writer = new PrintWriter("Highscore.txt")) {
            writer.println(highScore); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void readHighScore() {
        // we create a scanner for reading the file
        try (Scanner scanner = new Scanner(Paths.get("Highscore.txt"))) {
            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                highScore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void checkBlockPos(Block b, Block Block) {
        if (Block.ballVisible() == false) { // checking if the ball is not visible
            Block.setBallColor(b.getBallColor()); // setting the ball to the other color
            Block.changeVisible();
            b.changeVisible();
            moves++;
        }
    }
    // public void checkCorrect(Block b, Block Block){
    // if (b.getBallColor() == getBlockColor()){
    // correct = true;
    // rightPlace ++ ;
    // }
    // else {
    // correct = false;
    // int rightPlace = 0;
    // }
    // }
}
// = delcare
// == Compare

// 4x4
// timer
// final screen with score and moves