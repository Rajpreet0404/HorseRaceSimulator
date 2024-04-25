import java.util.concurrent.TimeUnit;
import java.lang.Math;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.0
 */
public class Race
{
    public static int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }

    // Main method:
    public static void main(String[] p)
    {
        // Create a main frame for the application
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Main menu");
        
        mainFrame.setSize(1500, 350);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.getContentPane().setBackground(new Color(54, 135, 73));
        mainFrame.setResizable(false);

        // Create a menu bar
        JMenuBar mainMenuBar = new JMenuBar();

        // Add the 'Menu' option to the menu bar
        JMenu menu = new JMenu("Menu");
        mainMenuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Main Menu");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(true);
            }
        });
        menu.add(menuItem);
        mainFrame.setJMenuBar(mainMenuBar);

        // Green background used to represent grass on the racetrack
        JTextField welcomeTextField = new JTextField("Welcome to the Horse Race Simulation!",30);
        welcomeTextField.setBackground(new Color(54, 135, 73));
        welcomeTextField.setForeground(Color.WHITE);
        welcomeTextField.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeTextField.setVisible(true);
        welcomeTextField.setEditable(false);
        welcomeTextField.setHorizontalAlignment(JTextField.CENTER);

        // Create a panel on the main frame 
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(54, 135, 73)); 
        textPanel.setVisible(true);

        JTextArea instructionTextArea = new JTextArea();
        instructionTextArea.setFont(new Font("monospaced", Font.PLAIN, 17));
        instructionTextArea.setRows(7);
        instructionTextArea.setBackground(new Color(54, 135, 73)); 
        instructionTextArea.setForeground(Color.WHITE);
        instructionTextArea.setEditable(false);
        instructionTextArea.setVisible(true);

        instructionTextArea.setText("To start, please configure the race by clicking the 'Edit Race' button to enter the track length and add your horses.");
        instructionTextArea.append("\n\n");
        instructionTextArea.append("Once you have successfully configured the race, you can return to this screen by clicking the 'Menu' option in the menu bar.");
        instructionTextArea.append("\n\n");
        instructionTextArea.append("Click the 'Start race' button to start the race and watch your horses compete!");
        instructionTextArea.append("\n\n");
        instructionTextArea.append("Good luck and have fun!");

        // Create a panel on the main frame 
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.setBackground(new Color(54, 135, 73)); 
        mainPanel.setVisible(true);

        // Buttons

        // Add a 'Start Race' button to the mainPanel
        JButton startRaceButton = new JButton("Start race");
        startRaceButton.setFont(new Font("Arial", Font.BOLD, 20));
        startRaceButton.setBackground(new Color(35, 158, 152));
        startRaceButton.setForeground(Color.WHITE);
        startRaceButton.setVisible(true);
        startRaceButton.setEnabled(false);
        // Set the preferred size of the button

        // Add an 'Edit Race' button to the mainPanel
        // Blue background used on buttons to represent the sky
        JButton editRaceButton = new JButton("Edit race");
        editRaceButton.setFont(new Font("Arial", Font.BOLD, 20));
        editRaceButton.setBackground(new Color(35, 158, 152));
        editRaceButton.setForeground(Color.WHITE);
        editRaceButton.setVisible(true);
        // Set the preferred size of the button

        textPanel.add(instructionTextArea);

        mainPanel.add(editRaceButton);
        mainPanel.add(startRaceButton);

        mainFrame.add(welcomeTextField);
        mainFrame.add(textPanel);
        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);

        Race race1 = new Race(raceLength);

        // Button action listners

        //Start race button action listner
        startRaceButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Executes the race on the event dispatch thread (EDT) so the GUI remains responsive
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() 
                {
                    @Override
                    protected Void doInBackground() throws Exception 
                    {
                        race1.startRaceGUI(mainFrame); // Call the startRaceGUI method on the instance
                        return null;
                    }
                };
                worker.execute();
            }
        });

        // Opens the edit race window and hides the main menu, allowing the user to configure the race
        editRaceButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                race1.editRaceWindow(mainFrame,startRaceButton);
            }
        });

        return;
    }

    public void editRaceWindow(JFrame mainFrame, JButton startRaceButton)
    {
        // Hides the main menu window
        mainFrame.setVisible(false);

        // Opens a new frame for the 'Edit Race' window
        JFrame editRaceFrame = new JFrame();
        editRaceFrame.setTitle("Edit Race");
        editRaceFrame.setSize(800, 500);
        editRaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editRaceFrame.setLayout(new FlowLayout());
        editRaceFrame.getContentPane().setBackground(new Color(54, 135, 73));
        editRaceFrame.setResizable(false);

        // Main menu bar for the 'Edit Race' window
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        mainMenuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Main Menu");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRaceFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });
        menu.add(menuItem);
        editRaceFrame.setJMenuBar(mainMenuBar);

        JPanel editPanel = new JPanel(new FlowLayout());
        editPanel.setBackground(new Color(54, 135, 73)); 

        // Allows the user to enter the track length 
        JTextField raceLenLabel = new JTextField("Enter the track length (m):",15);
        raceLenLabel.setBackground(Color.WHITE);
        raceLenLabel.setForeground(new Color(35, 158, 152));
        raceLenLabel.setFont(new Font("Arial", Font.BOLD, 15));
        raceLenLabel.setVisible(true);
        raceLenLabel.setEditable(false);
        raceLenLabel.setHorizontalAlignment(JTextField.CENTER);

        JTextField raceLenEntry = new JTextField(10);
        raceLenEntry.setBackground(Color.WHITE);
        raceLenEntry.setForeground(Color.BLACK);
        raceLenEntry.setFont(new Font("Arial", Font.BOLD, 15));
        raceLenEntry.setVisible(true);
        raceLenEntry.setHorizontalAlignment(JTextField.CENTER);

        // Buttons

        // Allows the user to submit the track length that they have entered
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 13));
        submitButton.setBackground(new Color(35, 158, 152));
        submitButton.setForeground(Color.WHITE);
        submitButton.setVisible(true);

        // Allows the user to add the first horse to the race, with the name and character that they have entered
        JButton addHorse1Button = new JButton("Add Horse 1");
        addHorse1Button.setFont(new Font("Arial", Font.BOLD, 13));
        addHorse1Button.setBackground(new Color(35, 158, 152));
        addHorse1Button.setForeground(Color.WHITE);
        addHorse1Button.setVisible(true);

        // Allows the user to add the second horse to the race, with the name and character that they have entered
        JButton addHorse2Button = new JButton("Add Horse 2");
        addHorse2Button.setFont(new Font("Arial", Font.BOLD, 13));
        addHorse2Button.setBackground(new Color(35, 158, 152));
        addHorse2Button.setForeground(Color.WHITE);
        addHorse2Button.setVisible(true);

        // Allows the user to add the third horse to the race, with the name and character that they have entered
        JButton addHorse3Button = new JButton("Add Horse 3");
        addHorse3Button.setFont(new Font("Arial", Font.BOLD, 13));
        addHorse3Button.setBackground(new Color(35, 158, 152));
        addHorse3Button.setForeground(Color.WHITE);
        addHorse3Button.setVisible(true);

        submitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = raceLenEntry.getText();
                // Try catch block used to check whther the input for the race length is a valid integer
                try {
                    int number = Integer.parseInt(input);
                    // Check if the input is a valid integer greater than 5 and less than 75
                    if(number <= 5 || number > 75)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer greater than 5 and less than 75.", "Error", JOptionPane.ERROR_MESSAGE);
                        raceLenEntry.setText("");
                    }
                    else
                    {
                        // Once the race length has been successfully entered by the user, the race can be started by clicking
                        // the start race button
                        raceLength = number;
                        JOptionPane.showMessageDialog(null, "Track length set to " + raceLength + " meters.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Start button enabled. You can now start the race from the main menu.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        raceLenEntry.setText("");
                        startRaceButton.setEnabled(true);
                    }
                    // The input is a valid integer
                } catch (NumberFormatException f) 
                {
                    // The input is not a valid integer
                    // Handle the error or display an error message to the user
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    raceLenEntry.setText("");
                }
            }
        });

        JTextField horse1NameLabel = new JTextField("Enter a name for Horse 1:",15);
        horse1NameLabel.setBackground(new Color(54, 135, 73));
        horse1NameLabel.setForeground(Color.WHITE);
        horse1NameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        horse1NameLabel.setVisible(true);
        horse1NameLabel.setEditable(false);
        horse1NameLabel.setHorizontalAlignment(JTextField.CENTER);

        // Entry field to allow the user to enter the name of the first horse
        JTextField horse1NameEntry = new JTextField(10);
        horse1NameEntry.setBackground(Color.WHITE);
        horse1NameEntry.setForeground(Color.BLACK);
        horse1NameEntry.setFont(new Font("Arial", Font.BOLD, 15));
        horse1NameEntry.setVisible(true);
        horse1NameEntry.setHorizontalAlignment(JTextField.CENTER);

        JTextField horse1CharLabel = new JTextField("Select a symbol to represent Horse 1:",27);
        horse1CharLabel.setBackground(new Color(54, 135, 73));
        horse1CharLabel.setForeground(Color.WHITE);
        horse1CharLabel.setFont(new Font("Arial", Font.BOLD, 15));
        horse1CharLabel.setVisible(true);
        horse1CharLabel.setEditable(false);
        horse1CharLabel.setHorizontalAlignment(JTextField.CENTER);

        JTextField horse2NameLabel = new JTextField("Enter a name for Horse 2:",15);
        horse2NameLabel.setBackground(new Color(54, 135, 73));
        horse2NameLabel.setForeground(Color.WHITE);
        horse2NameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        horse2NameLabel.setVisible(true);
        horse2NameLabel.setEditable(false);
        horse2NameLabel.setHorizontalAlignment(JTextField.CENTER);

        // Entry field to allow the user to enter the name of the second horse
        JTextField horse2NameEntry = new JTextField(10);
        horse2NameEntry.setBackground(Color.WHITE);
        horse2NameEntry.setForeground(Color.BLACK);
        horse2NameEntry.setFont(new Font("Arial", Font.BOLD, 15));
        horse2NameEntry.setVisible(true);
        horse2NameEntry.setHorizontalAlignment(JTextField.CENTER);

        JTextField horse2CharLabel = new JTextField("Select a symbol to represent Horse 2:",27);
        horse2CharLabel.setBackground(new Color(54, 135, 73));
        horse2CharLabel.setForeground(Color.WHITE);
        horse2CharLabel.setFont(new Font("Arial", Font.BOLD, 15));
        horse2CharLabel.setVisible(true);
        horse2CharLabel.setEditable(false);
        horse2CharLabel.setHorizontalAlignment(JTextField.CENTER);

        JTextField horse3NameLabel = new JTextField("Enter a name for Horse 3:",15);
        horse3NameLabel.setBackground(new Color(54, 135, 73));
        horse3NameLabel.setForeground(Color.WHITE);
        horse3NameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        horse3NameLabel.setVisible(true);
        horse3NameLabel.setEditable(false);
        horse3NameLabel.setHorizontalAlignment(JTextField.CENTER);

        // Entry field to allow the user to enter the name of the third horse
        JTextField horse3NameEntry = new JTextField(10);
        horse3NameEntry.setBackground(Color.WHITE);
        horse3NameEntry.setForeground(Color.BLACK);
        horse3NameEntry.setFont(new Font("Arial", Font.BOLD, 15));
        horse3NameEntry.setVisible(true);
        horse3NameEntry.setHorizontalAlignment(JTextField.CENTER);

        JTextField horse3CharLabel = new JTextField("Select a symbol to represent Horse 3:",27);
        horse3CharLabel.setBackground(new Color(54, 135, 73));
        horse3CharLabel.setForeground(Color.WHITE);
        horse3CharLabel.setFont(new Font("Arial", Font.BOLD, 15));
        horse3CharLabel.setVisible(true);
        horse3CharLabel.setEditable(false);
        horse3CharLabel.setHorizontalAlignment(JTextField.CENTER);

        // Entry field to allow the user to enter the character icon 
        JComboBox<String> symbolComboBox1 = new JComboBox<>();
        symbolComboBox1.addItem("♘");
        symbolComboBox1.addItem("♞");
        symbolComboBox1.addItem("☀");
        symbolComboBox1.addItem("☁");
        symbolComboBox1.addItem("☂");
        symbolComboBox1.addItem("☃");
        symbolComboBox1.addItem("★");
        symbolComboBox1.addItem("☆");
        symbolComboBox1.addItem("☺");
        symbolComboBox1.addItem("☻");
        symbolComboBox1.addItem("☹");
        symbolComboBox1.addItem("♕");
        symbolComboBox1.addItem("♔");
        symbolComboBox1.addItem("♥");

        //symbolComboBox1.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));

        // Entry field to allow the user to enter the character icon 
        JComboBox<String> symbolComboBox2 = new JComboBox<>();
        symbolComboBox2.addItem("♘");
        symbolComboBox2.addItem("♞");
        symbolComboBox2.addItem("☀");
        symbolComboBox2.addItem("☁");
        symbolComboBox2.addItem("☂");
        symbolComboBox2.addItem("☃");
        symbolComboBox2.addItem("★");
        symbolComboBox2.addItem("☆");
        symbolComboBox2.addItem("☺");
        symbolComboBox2.addItem("☻");
        symbolComboBox2.addItem("☹");
        symbolComboBox2.addItem("♕");
        symbolComboBox2.addItem("♔");
        symbolComboBox2.addItem("♥");

        //symbolComboBox2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));

        // Entry field to allow the user to enter the character icon 
        JComboBox<String> symbolComboBox3 = new JComboBox<>();
        symbolComboBox3.addItem("♘");
        symbolComboBox3.addItem("♞");
        symbolComboBox3.addItem("☀");
        symbolComboBox3.addItem("☁");
        symbolComboBox3.addItem("☂");
        symbolComboBox3.addItem("☃");
        symbolComboBox3.addItem("★");
        symbolComboBox3.addItem("☆");
        symbolComboBox3.addItem("☺");
        symbolComboBox3.addItem("☻");
        symbolComboBox3.addItem("☹");
        symbolComboBox3.addItem("♕");
        symbolComboBox3.addItem("♔");
        symbolComboBox3.addItem("♥");

        //symbolComboBox3.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));
        
        JPanel horsesPanel = new JPanel(new GridLayout(9,2));
        horsesPanel.setBackground(new Color(54, 135, 73));

        editPanel.add(raceLenLabel);
        editPanel.add(raceLenEntry);
        editPanel.add(submitButton);

        horsesPanel.add(horse1NameLabel);
        horsesPanel.add(horse1NameEntry);
        horsesPanel.add(horse1CharLabel);
        horsesPanel.add(symbolComboBox1);
        horsesPanel.add(new JLabel()); // Empty label
        horsesPanel.add(addHorse1Button);
        horsesPanel.add(horse2NameLabel);
        horsesPanel.add(horse2NameEntry);
        horsesPanel.add(horse2CharLabel);
        horsesPanel.add(symbolComboBox2);
        horsesPanel.add(new JLabel()); // Empty label
        horsesPanel.add(addHorse2Button);
        horsesPanel.add(horse3NameLabel);
        horsesPanel.add(horse3NameEntry);
        horsesPanel.add(horse3CharLabel);
        horsesPanel.add(symbolComboBox3);
        horsesPanel.add(new JLabel()); // Empty label
        horsesPanel.add(addHorse3Button);

        editRaceFrame.add(editPanel);
        editRaceFrame.add(horsesPanel);

        editPanel.setVisible(true);
        horsesPanel.setVisible(true);
        editRaceFrame.setVisible(true);

        // Used to add horse1 to the race
        addHorse1Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String horse1Name = horse1NameEntry.getText();
                String horse1Char = (String) symbolComboBox1.getSelectedItem();

                // Checks if the entry fields are empty
                if(horse1Name.equals("") || horse1Char.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name and a character for Horse 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    horse1NameEntry.setText("");
                }
                // Checks the length of the horse name is greater than 15 characters
                else if(horse1Name.length() > 15)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name with less than 15 characters for Horse 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    horse1NameEntry.setText("");
                }
                else
                {
                    // Generates a random number (between 0.1 and 0.9 inclusive) for the horses confidence
                    double randomNum = generateRandomNumber();
                    Horse horse1 = new Horse(horse1Char.charAt(0),horse1Name,randomNum);
                    // Adds the horse to the first lane
                    addHorse(horse1,1);
                    JOptionPane.showMessageDialog(null, "Horse 1 added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    horse1NameEntry.setText("");
                }   
            }
        });

        // Used to add horse2 to the race
        addHorse2Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String horse2Name = horse2NameEntry.getText();
                String horse2Char = (String) symbolComboBox2.getSelectedItem();

                // Checks if the entry fields are empty
                if(horse2Name.equals("") || horse2Char.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name and a character for Horse 2.", "Error", JOptionPane.ERROR_MESSAGE);
                    horse2NameEntry.setText("");
                }
                // Checks the length of the horse name is greater than 15 characters
                else if(horse2Name.length() > 15)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name with less than 15 characters for Horse 2.", "Error", JOptionPane.ERROR_MESSAGE);
                    horse2NameEntry.setText("");
                }
                else
                {
                    // Generates a random number (between 0.1 and 0.9 inclusive) for the horses confidence
                    double randomNum = generateRandomNumber();
                    Horse horse2 = new Horse(horse2Char.charAt(0),horse2Name,randomNum);
                    // Adds the horse to the second lane
                    addHorse(horse2,2);
                    JOptionPane.showMessageDialog(null, "Horse 2 added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    horse2NameEntry.setText("");
                }   
            }
        });

        // Used to add horse3 to the race
        addHorse3Button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String horse3Name = horse3NameEntry.getText();
                String horse3Char = (String) symbolComboBox3.getSelectedItem();

                // Checks if the entry fields are empty
                if(horse3Name.equals("") || horse3Char.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name and a character for Horse 3.", "Error", JOptionPane.ERROR_MESSAGE);
                    horse3NameEntry.setText("");
                }
                // Checks the length of the horse name is greater than 15 characters
                else if(horse3Name.length() > 15)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name with less than 15 characters for Horse 3.", "Error", JOptionPane.ERROR_MESSAGE);
                    horse3NameEntry.setText("");
                }
                else
                {
                    // Generates a random number (between 0.1 and 0.9 inclusive) for the horses confidence
                    double randomNum = generateRandomNumber();
                    Horse horse3 = new Horse(horse3Char.charAt(0),horse3Name,randomNum);
                    // Adds the horse to the third lane
                    addHorse(horse3,3);
                    JOptionPane.showMessageDialog(null, "Horse 3 added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    horse3NameEntry.setText("");
                }   
            }
        });

    }

    /**
     * Generates a random number between 0 and 1 (not inclusive) and rounds it to 1 decimal place.
     */
    public double generateRandomNumber() 
    {
        double randomNumber = Math.random();
        double roundedNumber = Math.round(randomNumber * 10) / 10.0;
        while(roundedNumber == 0.0 || roundedNumber == 1.0)
        {
            randomNumber = Math.random();
            roundedNumber = Math.round(randomNumber * 10) / 10.0;
        }
        return roundedNumber;
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRaceGUI(JFrame mainFrame)
    {
        // Hide the main menu window
        mainFrame.setVisible(false);

        // Create a new frame for the race to be displayed
        JFrame raceFrame = new JFrame();
        raceFrame.setTitle("Race");
        raceFrame.setSize(900, 250);
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raceFrame.setLayout(new FlowLayout());
        raceFrame.getContentPane().setBackground(new Color(54, 135, 73));

        // Create a text area to display the race
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("monospaced", Font.PLAIN, 20));
        textArea.setRows(5);
        textArea.setBackground(new Color(54, 135, 73)); 
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setVisible(true);

        raceFrame.add(textArea);

        // Create a menu bar for the Start race window
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        mainMenuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Main Menu");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });
        menu.add(menuItem);

        raceFrame.setJMenuBar(mainMenuBar);
        raceFrame.setVisible(true);
        
        // Sets the menu bar to visible once the race is over 
        mainMenuBar.setVisible(false);
        race(textArea);
        mainMenuBar.setVisible(true);
        
    }

    public void race(JTextArea textArea)
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;
                        
        //reset all the lanes (all horses not fallen and back to 0). 
        if( (lane1Horse == null && lane2Horse == null && lane3Horse == null) || (lane1Horse == null && lane2Horse == null) || 
            (lane1Horse == null && lane3Horse == null) || (lane2Horse == null && lane3Horse == null) )
        {
            textArea.setText("Not enough horses to start the race!");
            finished = true;
            return;
        }
    
        if(lane1Horse != null)
        {
            lane1Horse.goBackToStart();
            lane1Horse.setHasFallen(false);
        }

        if(lane2Horse != null)
        {
            lane2Horse.goBackToStart();
            lane2Horse.setHasFallen(false);
        }

        if(lane3Horse != null)
        {
            lane3Horse.goBackToStart();
            lane3Horse.setHasFallen(false);
        }
                    
        while (!finished)
        {
            //move each horse
            if(lane1Horse != null)
            {
                moveHorse(lane1Horse);
            }

            if(lane2Horse != null)
            {
                moveHorse(lane2Horse);
            }

            if(lane3Horse != null)
            {
                moveHorse(lane3Horse);
            }
                        
            //print the race positions
            printRace(textArea);
            
            //if any of the three horses has won the race is finished
            if ( raceWonBy(lane1Horse) ) 
            {
                double confidence = lane1Horse.getConfidence();
                if (confidence == 0.9 || confidence == 0.1)
                {
                }
                else
                {
                    confidence = confidence + 0.1;
                    confidence = Math.round(confidence * 10.0) / 10.0; // Round to 1 decimal place
                    lane1Horse.setConfidence(confidence);
                }

                //print the race positions
                printRace(textArea);

                textArea.append("And the winner is... " + lane1Horse.getName() + "!");

                finished = true;
            }
            else if( raceWonBy(lane2Horse) )
            {
                double confidence = lane2Horse.getConfidence();
                if (confidence == 0.9 || confidence == 0.1)
                {
                }
                else
                {
                    confidence = confidence + 0.1;
                    confidence = Math.round(confidence * 10.0) / 10.0; // Round to 1 decimal place
                    lane2Horse.setConfidence(confidence);
                }

                //print the race positions
                printRace(textArea);

                textArea.append("And the winner is... " + lane2Horse.getName() + "!");

                finished = true;
            }
            else if( raceWonBy(lane3Horse) )
            {
                double confidence = lane3Horse.getConfidence();
                if (confidence == 0.9 || confidence == 0.1)
                {
                }
                else
                {
                    confidence = confidence + 0.1;
                    confidence = Math.round(confidence * 10.0) / 10.0; // Round to 1 decimal place
                    lane3Horse.setConfidence(confidence);
                }

                //print the race positions
                printRace(textArea);

                textArea.append("And the winner is... " + lane3Horse.getName() + "!");

                finished = true;
            }

            if(lane1Horse == null)
            {
                if(lane2Horse.hasFallen() && lane3Horse.hasFallen())
                {
                    textArea.append("All horses have fallen! No winner this time!");
                    finished = true;
                }
            }
            else if(lane2Horse == null)
            {
                if(lane1Horse.hasFallen() && lane3Horse.hasFallen())
                {
                    textArea.append("All horses have fallen! No winner this time!");
                    finished = true;
                }
            }
            else if(lane3Horse == null)
            {
                if(lane1Horse.hasFallen() && lane2Horse.hasFallen())
                {
                    textArea.append("All horses have fallen! No winner this time!");
                    finished = true;
                }
            }
            else
            {
                if(lane1Horse.hasFallen() && lane2Horse.hasFallen() && lane3Horse.hasFallen())
                {
                    textArea.append("All horses have fallen! No winner this time!");
                    finished = true;
                }

            }
        
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception f){}
        }
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                double confidence = theHorse.getConfidence();
                confidence = confidence - 0.1;
                confidence = Math.round(confidence * 10.0) / 10.0; // Round to 1 decimal place
                theHorse.setConfidence(confidence);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if(theHorse == null)
        {
            return false;
        }
        else
        {
            if (theHorse.getDistanceTravelled() == raceLength)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace(JTextArea textArea)
    {

        textArea.setText("");
        
        multiplePrint('=',raceLength+3,textArea); //top edge of track
        textArea.append("\n");
        
        printLane(lane1Horse, textArea);
        textArea.append("\n");
        
        printLane(lane2Horse, textArea);
        textArea.append("\n");
        
        printLane(lane3Horse, textArea);
        textArea.append("\n");
        
        multiplePrint('=',raceLength+3,textArea); //bottom edge of track
        textArea.append("\n");  
    }

    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse, JTextArea textArea)
    {
        if (theHorse != null)
        {
            //calculate how many spaces are needed before
            //and after the horse
            int spacesBefore = theHorse.getDistanceTravelled();
            int spacesAfter = raceLength - theHorse.getDistanceTravelled();
            
            //print a | for the beginning of the lane
            textArea.append("|");
            
            //print the spaces before the horse
            multiplePrint(' ',spacesBefore, textArea);
            
            //if the horse has fallen then print dead
            //else print the horse's symbol
            if(theHorse.hasFallen())
            {
                textArea.append("☠");
            }
            else
            {
               textArea.append(String.valueOf(theHorse.getSymbol()));
            }

            //print the spaces after the horse
            multiplePrint(' ',spacesAfter, textArea);

            //print the | for the end of the track
            textArea.append("|");

            // Print the horse's name and current confidence
            textArea.append(" " + theHorse.getName() + " (Current confidence " + theHorse.getConfidence() + ")");
        }
        else
        {
            //calculate how many spaces are needed before
            //and after the horse
            int spacesBefore = 0;
            int spacesAfter = raceLength +1;

            //print a | for the beginning of the lane
            textArea.append("|");
            
            //print the spaces before the horse
            multiplePrint(' ',spacesBefore, textArea);

            //print the spaces after the horse
            multiplePrint(' ',spacesAfter, textArea);
            
            //print the | for the end of the track
            textArea.append("|");
        }

    }
        
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times, JTextArea textArea)
    {
        int i = 0;
        while (i < times)
        {
            textArea.append(String.valueOf(aChar));
            i = i + 1;
        }
    }
}
