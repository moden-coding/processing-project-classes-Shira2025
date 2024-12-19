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
    double time; // need milis in here
    int scene = 0;
    int boxHeight = 100;
    int boxWidth = 200;
    int buttonX = 300;
    int instructionsY = 400;
    int gamePlayY = 600;
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
            if (checkCorrect() == true) {
                scene = 3;
            }
        } else if (scene == 3) {
            notGamePlay();
        }

    }

    public void gameSet() { // the start screen
        background(143, 155, 176);// blue gray
        textSize(100);
        fill(174, 52, 235);
        text("Solve It", 250, 100);
        textSize(35);
        fill(128, 237, 230);
        rect(buttonX, instructionsY, boxWidth, boxHeight);// for instructions
        rect(buttonX, gamePlayY, boxWidth, boxHeight); // for gameplay
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
        fill(0);
        text("Time:" + time, 25, 45);
        strokeWeight(2);
        fill(34, 107, 201); // pink box
        rect(50, 710, 125, 75);// back box
        fill(0);
        textSize(45);
        text("Back", 60, 760);
    }

    public void startTime() { // staring time counter
        if (scene == 2) {
            time = (millis() / 100) / 10.0;
        }
    }

    public void instrucions() {
        background(66, 245, 179);// light green
        strokeWeight(2);
        fill(34, 107, 201); // pink back box
        rect(50, 710, 125, 75);// back box
        fill(0);
        textSize(45);
        text("Back", 60, 760);
    }

    public void notGamePlay() { // moves screen
        startTime();
        background(143, 155, 176);// blue gray
        fill(34, 107, 201); // blue back box
        rect(50, 710, 125, 75);// back box
        fill(0);
        text("Back", 60, 760);
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

    public void blockMaker() { // draw the grid
        ArrayList<Integer> blockColors = getRandomColors();
        ArrayList<Integer> circleColors = getRandomColors();
        strokeWeight(5);
        int y = 100;
        int colorPos = 0;
        for (int rows = 0; rows < 3; rows++) {
            int x = 100;
            for (int elements = 0; elements < 3; elements++) {
                Block block = new Block(x, y, 200, 200, this, blockColors.get(colorPos), circleColors.get(colorPos));
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
                    checkCorrect();
                }
                // check left
                if (i % 3 != 0) {
                    Block leftBlock = blocks.get(i - 1);
                    checkBlockPos(b, leftBlock);
                    checkCorrect();
                }
                // check up
                if (i / 3 != 0) {
                    Block upBlock = blocks.get(i - 3);
                    checkBlockPos(b, upBlock);
                    checkCorrect();
                }
                // check down
                if (i / 3 != 2) {
                    Block downBlock = blocks.get(i + 3);
                    checkBlockPos(b, downBlock);
                    checkCorrect();
                }
            }
        }
        if (mouseX > buttonX && mouseY > instructionsY && mouseX < buttonX + boxWidth
                && mouseY < boxHeight + instructionsY && scene == 0) {// instructions
            scene = 1;
        }
        if (mouseX > buttonX && mouseY > gamePlayY && mouseX < buttonX + boxWidth
                && mouseY < gamePlayY + boxWidth && scene == 0) {// gameplay
            scene = 2;
        }
        if (mouseX > 50 && mouseY > 710 && mouseX < 50 + 125 && mouseY < 710 + 75) {// back
            scene = 0;
        }
    }

    public ArrayList<Integer> getRandomColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Integer> randomizedColor = new ArrayList<>();
        colors.add(color(138, 30, 70)); // maroon
        colors.add(color(112, 28, 201)); // purple
        colors.add(color(201, 26, 196)); // pink
        colors.add(color(237, 111, 43)); // orange
        colors.add(color(37, 217, 76)); // light green
        colors.add(color(237, 201, 40)); // yellow
        colors.add(color(34, 107, 201)); // blue
        colors.add(color(130, 103, 191)); // light purple
        colors.add(color(36, 201, 163)); // teal
        while (colors.size() > 0) {
            int index = (int) random(colors.size());
            randomizedColor.add(colors.get(index));
            colors.remove(index);
        }
        return randomizedColor;
    }

    public boolean checkCorrect() {
        for (Block b : blocks) {
            if (b.ballVisible() == true && b.getBallColor() != b.getBlockColor()) {
                return false;
            }
        }
        return true;
    }
}

// = delcare
// == Compare

// timer
