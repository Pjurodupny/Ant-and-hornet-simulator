import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlScreen extends JPanel implements ActionListener {

    JLabel simScreenSideLengthText;
    JTextField simScreenSideLengthTextField;

    JLabel antsNumberText;
    JTextField antsNumberTextField;

    JLabel hornetNumberText;
    JTextField hornetNumberTextField;

    JLabel flowerNumberText;
    JTextField flowerNumberTextField;

    JLabel dataFromFileText;
    JTextField dataFromFileTextField;

    JLabel saveDataText;
    JTextField saveDataTextField;

    JButton startButton;
    JButton stopButton;

    JLabel stepsText;

    JLabel stepSetText;
    JTextField stepSetTextField;

    JLabel setFpsText;
    JTextField setFpsTextField;

    SimScreen simScreen;
    Simulation simulation;

    private int labelWidth = 100;
    private  int labelHeight = 30;
    private int textFieldWidth = 50;
    private int textFieldHeight = 30;
    private int buttonWidth = 100;
    private int buttonHeight = 30;

    int xLabel = 10;
    int xTextField = 100;
    int xButton = 30;
    int y = 20;

    private int simScreenSideLength = 0;
    private int numberOfAnts = 0;
    private int numberOfHornets = 0;
    private int numberOfFlowers = 0;



    public ControlScreen(Simulation simulation, SimScreen simScreen) {
        this.simScreen = simScreen;
        this.simulation = simulation;


//        simScreenSideLengthText = new JLabel("side length: ");
//        simScreenSideLengthText.setBounds(xLabel, y, labelWidth, labelHeight);
//        this.add(simScreenSideLengthText);
//
//        simScreenSideLengthTextField = new JTextField();
//        simScreenSideLengthTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
//        simScreenSideLengthTextField.addActionListener(this);
//        this.add(simScreenSideLengthTextField);
//
//        y += 40;

        antsNumberText = new JLabel("ants number: ");
        antsNumberText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(antsNumberText);

        antsNumberTextField = new JTextField();
        antsNumberTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        antsNumberTextField.addActionListener(this);
        this.add(antsNumberTextField);

        y += 40;

        hornetNumberText = new JLabel("hornet number: ");
        hornetNumberText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(hornetNumberText);

        hornetNumberTextField = new JTextField();
        hornetNumberTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        hornetNumberTextField.addActionListener(this);
        this.add(hornetNumberTextField);

        y += 40;

        flowerNumberText = new JLabel("flower number: ");
        flowerNumberText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(flowerNumberText);

        flowerNumberTextField = new JTextField();
        flowerNumberTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        flowerNumberTextField.addActionListener(this);
        this.add(flowerNumberTextField);

        y += 40;

        dataFromFileText = new JLabel("load from file: ");
        dataFromFileText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(dataFromFileText);

        dataFromFileTextField = new JTextField(simulation.getFileNameInput());
        dataFromFileTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        dataFromFileTextField.addActionListener(this);
        this.add(dataFromFileTextField);

        y += 40;

        saveDataText = new JLabel("save to: ");
        saveDataText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(saveDataText);

        saveDataTextField = new JTextField(simulation.getFileNameOutput());
        saveDataTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        saveDataTextField.addActionListener(this);
        this.add(saveDataTextField);

        y += 40;

        startButton = new JButton("START");
        startButton.setBounds(xButton, y, buttonWidth, buttonHeight);
        startButton.addActionListener(this);
        this.add(startButton);

        y += 40;

        stopButton = new JButton("STOP");
        stopButton.setBounds(xButton, y, buttonWidth, buttonHeight);
        stopButton.addActionListener(this);
        this.add(stopButton);

        y += 40;

        stepsText = new JLabel("Steps: 0");
        stepsText.setBounds(30, y, labelWidth, labelHeight);
        this.add(stepsText);

        y += 40;

        stepSetText = new JLabel("set steps: ");
        stepSetText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(stepSetText);

        stepSetTextField = new JTextField(Integer.toString(simulation.getSetSteps()));
        stepSetTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        stepSetTextField.addActionListener(this);
        this.add(stepSetTextField);

        y += 40;

        setFpsText = new JLabel("set fps: ");
        setFpsText.setBounds(xLabel, y, labelWidth, labelHeight);
        this.add(setFpsText);

        setFpsTextField = new JTextField(Integer.toString(simulation.getFPS()));
        setFpsTextField.setBounds(xTextField, y, textFieldWidth, textFieldHeight);
        setFpsTextField.addActionListener(this);
        this.add(setFpsTextField);
    }

    public void update() {
        stepsText.setText("Steps: " + simScreen.getSteps());
    }

    public void updateParameters(ArrayList<Integer> parameters) {

//        simScreenSideLength = parameters.get(0);
//        simScreenSideLengthTextField.setText(Integer.toString(simScreenSideLength));
//        simScreen.BLOCKSIZE = (SimScreen.WIDTH / simScreenSideLength);

        numberOfAnts = parameters.get(1);
        antsNumberTextField.setText(Integer.toString(numberOfAnts));
        simScreen.setNumberOfAnts(numberOfAnts);

        numberOfHornets = parameters.get(2);
        simScreen.setNumberOfHornets(numberOfHornets);
        hornetNumberTextField.setText(Integer.toString(numberOfHornets));

        numberOfFlowers = parameters.get(3);
        simScreen.setNumberOfFlowers(numberOfFlowers);
        flowerNumberTextField.setText(Integer.toString(numberOfFlowers));



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //buttons
        if (e.getSource() == startButton && !simScreen.isRunning()) {
            simScreen.setRunning(true);
            if (simScreen.getSteps() == simulation.getSetSteps()) {
                simScreen.resetSteps();
                simScreen.init();
            }

        }
        if (e.getSource() == stopButton) {
            if (!simScreen.isRunning()) {
                simScreen.resetSteps();
                simScreen.init();
            }
            simScreen.setRunning(false);
        }

        //textFields

        //set number of ants if enter on text field is pressed
        if (e.getSource() == antsNumberTextField) {
            try {
                simScreen.setNumberOfAnts(Integer.parseInt(antsNumberTextField.getText()));
            } catch (NumberFormatException er) {
                simScreen.setNumberOfAnts(0);
            }
        }
        //set number of hornets if enter on text field is pressed
        if (e.getSource() == hornetNumberTextField) {
            try {
                simScreen.setNumberOfHornets(Integer.parseInt(hornetNumberTextField.getText()));
            } catch (NumberFormatException er) {
                simScreen.setNumberOfHornets(0);
            }
        }
        //set number of flowers if enter on text field is pressed
        if (e.getSource() == flowerNumberTextField) {
            try {
                simScreen.setNumberOfFlowers(Integer.parseInt(flowerNumberTextField.getText()));
            } catch (NumberFormatException er) {
                simScreen.setNumberOfFlowers(0);
            }
        }
        //set steps if enter on text field is pressed
        if (e.getSource() == stepSetTextField) {
            try {
                System.out.println(Integer.parseInt(stepSetTextField.getText()));
                simulation.setSetSteps(Integer.parseInt(stepSetTextField.getText()));
            } catch (NumberFormatException er) {
                simulation.setSetSteps(0);
            }
        }
        //set fps if enter on text field is pressed
        if (e.getSource() == setFpsTextField) {
            try {
                simulation.setFPS(Integer.parseInt(setFpsTextField.getText()));
            } catch (NumberFormatException er) {
                simulation.setFPS(0);
            }
        }
        //set filename of input data textfile and update parameters if enter on text field is pressed and filename is valid
        if (e.getSource() == dataFromFileTextField) {
            String fileName = dataFromFileTextField.getText();
            if (fileName.contains(".txt")) {
                Parameters.loadDataFromFile("/" + fileName);
                updateParameters(Parameters.getParameters(0));
            }
        }


    }


}

