import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class Game {
    JFrame window;
    JPanel titlePane, buttonsPane, imagePane, level1Pane, pleb, food, scorePane;
    JLabel titleLabel, scoreLabel;
    JButton level1Button, level2Button, level3Button;
    Font titleFont = new Font("Times New Roman", Font.PLAIN,100);
    Font largeButtonFont = new Font("Times New Roman", Font.PLAIN, 60);
    changeScene sceneChanger = new changeScene();
    movement move = new movement();
    int count = 0;

    int plebX = 450;
    int plebY = 300;

    int foodX = 700;
    int foodY = 150;


    public static void main(String[] args) {
        new Game();
    }
    public Game(){
        window = new JFrame("Victor's Game");
        window.setSize(900,600);    //actual width: 875, height: 575
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.WHITE);
        window.setLayout(null);


        titlePane = new JPanel();
        titlePane.setBounds(50,50,500,150);
        titlePane.setBackground(Color.DARK_GRAY);
        window.add(titlePane);

        titleLabel = new JLabel("Title");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titlePane.add(titleLabel);

        imagePane = new JPanel();
        imagePane.setBounds(175,250,250,250);
        imagePane.setBackground(Color.DARK_GRAY);
        window.add(imagePane);

        //ImageIcon sword = new ImageIcon(Objects.requireNonNull(getClass().getResource("res/Sword.png")));

        JLabel swordLabel = new JLabel();
        //swordLabel.setIcon(sword);
        imagePane.add(swordLabel);


        buttonsPane = new JPanel();
        buttonsPane.setBounds(600, 50, 200, 450);
        buttonsPane.setBackground(Color.DARK_GRAY);
        buttonsPane.setLayout(new GridLayout (3, 1));
        window.add(buttonsPane);

        level1Button = new JButton("Level 1");
        level1Button.setFont(largeButtonFont);
        level1Button.addActionListener(sceneChanger);
        level1Button.setActionCommand("L1");

        level2Button = new JButton("Level 2");
        level2Button.setFont(largeButtonFont);
        level2Button.addActionListener(sceneChanger);
        level2Button.setActionCommand("L2");

        level3Button = new JButton("Level 3");
        level3Button.setFont(largeButtonFont);
        level3Button.addActionListener(sceneChanger);
        level3Button.setActionCommand("L3");

        buttonsPane.add(level1Button);
        buttonsPane.add(level2Button);
        buttonsPane.add(level3Button);



        window.setVisible(true);




    }
    public void createKeyboard(){
        JButton up = new JButton("W");
        up.addActionListener(move);
        up.setActionCommand("up");
        JButton down = new JButton("S");
        down.addActionListener(move);
        down.setActionCommand("down");
        JButton left = new JButton("A");
        left.addActionListener(move);
        left.setActionCommand("left");
        JButton right = new JButton("D");
        right.addActionListener(move);
        right.setActionCommand("right");

        JPanel wasdPane = new JPanel();
        wasdPane.setBounds(50,50,250,150);
        wasdPane.setBackground(Color.DARK_GRAY);
        wasdPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        wasdPane.add(up, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        wasdPane.add(left, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        wasdPane.add(down, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        wasdPane.add(right, constraints);

        window.add(wasdPane);
        window.add(level1Pane);
    }
    public void checkFood(){
        int randXMin = 0;
        int randXMax = 24;
        int randYMin = 0;
        int randYMax = 12;

        Random XRand = new Random();
        Random YRand = new Random();

        if (plebX == foodX && plebY == foodY){
            count++;
            scoreLabel.setText("SCORE: " + count);
            foodX = XRand.nextInt(randXMax-randXMin+1) + randXMin;
            foodY = YRand.nextInt(randYMax-randYMin+1) + randYMin;
            food.setBounds(100 + ( foodX * 25 ), 100 + (foodY * 25),50,50);
            foodX = food.getX();
            foodY = food.getY();
        }

    }
    public void createLevel1(){


        titlePane.setVisible(false);
        imagePane.setVisible(false);
        buttonsPane.setVisible(false);


        level1Pane = new JPanel();
        level1Pane.setBackground(Color.BLACK);
        level1Pane.setBounds(100,100,700,400);  //left edge is 100px, top edge is 100


        pleb = new JPanel();
        pleb.setBackground(Color.GREEN);
        pleb.setBounds(plebX,plebY,50,50);
        window.add(pleb);

        food = new JPanel();
        food.setBackground(Color.RED);
        food.setBounds(foodX, foodY, 50,50);
        window.add(food);

        scorePane = new JPanel();
        scorePane.setBackground(Color.BLACK);
        scorePane.setBounds(500,425,300,75);
        window.add(scorePane);

        scoreLabel = new JLabel("SCORE: " + count);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(largeButtonFont);
        scorePane.add(scoreLabel);



    }
    public void createLevel2(){
        titlePane.setVisible(false);
        imagePane.setVisible(false);
        buttonsPane.setVisible(false);

        level1Pane = new JPanel();
        level1Pane.setBackground(Color.orange);
        level1Pane.setBounds(100,100,700,400);
        window.add(level1Pane);


    }
    public void createLevel3(){
        titlePane.setVisible(false);
        imagePane.setVisible(false);
        buttonsPane.setVisible(false);

        level1Pane = new JPanel();
        level1Pane.setBackground(Color.pink);
        level1Pane.setBounds(100,100,700,400);
        window.add(level1Pane);


    }
    public class changeScene implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            switch (action){
                case "L1" ->{
                    createLevel1();
                    createKeyboard();
                }
                case "L2" ->{
                    createLevel2();
                    createKeyboard();
                }
                case "L3" ->{
                    createLevel3();
                    createKeyboard();
                }
            }
        }
    }
    public class movement implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            switch (action){
                case "up" ->{
                    if (plebY>100) {
                        plebY = plebY - 25;
                        pleb.setBounds(plebX, plebY, 50, 50);

                    }
                    checkFood();
                }
                case "down" ->{
                    if (plebY<450) {
                        plebY = plebY + 25;
                        pleb.setBounds(plebX, plebY, 50, 50);

                    }
                    checkFood();
                }
                case "left" ->{
                    if(plebX>100) {
                        plebX = plebX - 25;
                        pleb.setBounds(plebX, plebY, 50, 50);

                    }
                    checkFood();
                }
                case "right" ->{
                    if (plebX<750) {
                        plebX = plebX + 25;
                        pleb.setBounds(plebX, plebY, 50, 50);

                    }
                    checkFood();
                }

            }
        }
    }
}