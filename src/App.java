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
    int scene = 0;
    int boxHeight = 100;
    int boxWidth = 200;
    int buttonX = 300;
    int instructionsY = 400;
    int gamePlayY = 600;
    boolean correct = true;
    double highTime = 10000;
    double time = 0.0;
    boolean timeCounting = false;
    int startTime;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() { // make the lists and check list and make blocks
        blocks = new ArrayList<>();
        blockMaker();
        readHighScore();
        readHighTime();
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
        background(255, 215, 212);// light pink
        textSize(100);
        fill(214, 39, 97);// pink
        text("Slider", 275, 100);
        textSize(35);
        fill(245, 95, 110);// red
        rect(buttonX, instructionsY, boxWidth, boxHeight);// for instructions
        rect(buttonX, gamePlayY, boxWidth, boxHeight); // for gameplay
        fill(0);
        text("Instructions", 320, 450);
        textSize(60);
        text("Play", 340, 670);
    }

    public void instrucions() { // instrucions of how to play
        background(250, 245, 182);// light green
        textSize(100);
        fill(0);
        text("Slider", 275, 100);
        strokeWeight(2);
        fill(251, 255, 41); // yellow back box
        rect(50, 710, 125, 75);// back box
        fill(0);
        textSize(45);
        text("Back", 60, 760);
        textSize(30);
        text("-The goal of this game is to", 20, 140);
        text("match the ball color to the block color", 20, 190);
        text("-Press on a ball to move it", 20, 250);
        text("-The lower the score, the better", 20, 300);
        fill(255, 0, 0);
        text("-SOMETIMES THIS IS NOT SOLVABLE", 20, 400);
        text("-Two of the balls and corresponding box's will be inverted", 20, 450);
        text("-It may look like:", 20, 500);
        fill(255, 114, 92);
        rect(250, 475, 50,50);
        fill(89, 175, 255);
        rect(300, 475, 50,50);
        circle(275, 500, 40);
        fill(255, 114, 92);
        circle(325, 500, 40);
        fill(255, 0, 0);
        text("after the rest of circles", 360, 500);
        text("are in the correct spot",20, 550);
        text("-if so press space to see your score",20, 600);
    }

    public void gamePlay() { // showing balls and gamescreen
        background(173, 173, 173);// gray
        startTime();
        for (Block B : blocks) {
            strokeWeight(5);
            B.display();
        }
        fill(0);
        text("Time:" + time, 25, 45);
        strokeWeight(2);
        fill(82, 82, 82); // orange back box
        rect(50, 710, 125, 75);// back box
        fill(0);
        textSize(45);
        text("Back", 60, 760);
    }

    public void notGamePlay() { // moves and time screen
        background(255, 251, 128);// yellow
        fill(251, 255, 56); // yellow back box
        rect(50, 710, 125, 75);// back box
        fill(0);
        text("Back", 60, 760);
        readHighScore();
        readHighTime();
        if (highScore > moves) {
            highScore = moves;
            saveMoves();
        }
        if (highTime > time) {
            highTime = time;
            saveHighTime();
        }
        textSize(50);
        fill(0);
        text("Moves:", 50, 50);
        text(moves, 200, 50);
        text("High score:", 400, 55);
        text(highScore, 650, 55);
        fill(208, 212, 0);// yellow
        rect(325, 600, 200, 100);// play again box
        fill(0);
        textSize(40);
        text("Play again", 340, 650);
        textSize(50);
        text("Time " + time, 50, 300);
        text("Fastest time " + highTime, 400, 300);
    }

    public void startTime() { // staring time counter
        if (scene == 2 && timeCounting == true) {
            time = ((millis() - startTime) / 100) / 10.0;
        }
    }

    public void keyPressed() { // maybe a keypressed for highscore
        // if (key == '2') {
        //     scene = 2;
        //     startTime = millis();
        // } 
        if (key == ' ') { // may need if puzzle isn't able to be solved
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

    public void saveHighTime() {
        try (PrintWriter writer = new PrintWriter("Time.txt")) {
            writer.println(highTime); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void readHighTime() {
        // we create a scanner for reading the file
        try (Scanner scanner = new Scanner(Paths.get("Time.txt"))) {
            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                highTime = Double.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void checkBlockPos(Block b, Block Block) { // checking and switching ball colors
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

    public void mousePressed() {// checking if mouse pressed then moving ball and checking if its all correct
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
            time = 0.0;
            timeCounting = true;
            startTime = millis();
            scene = 2;

        }
        if (mouseX > 50 && mouseY > 710 && mouseX < 50 + 125 && mouseY < 710 + 75) {// back
            scene = 0;
        }
        if (mouseX > 325 && mouseY > 600 && mouseX < 325 + 200 && mouseY < 600 + 100
                && scene == 3) {// play again
            time = 0.0;
            timeCounting = true;
            startTime = millis();
            scene = 2;
            blocks.clear();
            blockMaker();
            moves = 0;
        }
    }

    public ArrayList<Integer> getRandomColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Integer> randomizedColor = new ArrayList<>();
        colors.add(color(255, 114, 92)); // red
        colors.add(color(255, 168, 92)); // orange
        colors.add(color(255, 252, 143)); // yellow
        colors.add(color(197, 255, 143)); // light green
        colors.add(color(83, 194, 131)); // dark green
        colors.add(color(114, 250, 252)); // light blue
        colors.add(color(89, 175, 255)); // blue
        colors.add(color(184, 102, 255)); // light purple
        colors.add(color(255, 99, 206)); // pink
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