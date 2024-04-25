/*
 * Represents the horses in the race
 * @author Rajpreet Lisa Kaur Athwal 
 * @version 30/3/24
 */
import java.util.*;

public class Horse
{
    //Fields of class Horse
    private String horseName;
    private char horseSymbol;
    private double horseConfidence;
    private int distanceTravelled;
    private boolean hasFallen;
    private int racesWon;
    private int totalRaces;
    
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
    }

    public int getRacesWon()
    {
        return this.racesWon;
    }

    public void setRacesWon(int racesWon)
    {
        this.racesWon = racesWon;
        return;
    }

    public int getTotalRaces()
    {
        return this.totalRaces;
    }   

    public void setTotalRaces(int total)
    {
        this.totalRaces = total;
        return;
    }   
    
    //Other methods of class Horse
    public void fall()
    {
        this.hasFallen = true;
        return;
    }
    
    public double getConfidence()
    {
        return this.horseConfidence;
        
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
        
    }
    
    public String getName()
    {
        return this.horseName;
        
    }
    
    public char getSymbol()
    {
        return this.horseSymbol;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        return;
        
    }
    
    public boolean hasFallen()
    {
        if(this.hasFallen)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

    public void setHasFallen(boolean hasFallen)
    {
        this.hasFallen = hasFallen;
        return;
    }

    public void moveForward()
    {
        this.distanceTravelled++;
        return;
    }

    public void setConfidence(double newConfidence)
    {
        
        while(newConfidence<=0 || newConfidence>=1)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("The confidence rating of the horse should be between 0 and 1.");
            System.out.println("Please enter a new confidence rating for the horse: ");
            newConfidence = Double.parseDouble(scanner.nextLine());
    
        }

        this.horseConfidence = newConfidence;
    
        return;
        
    }
    
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
        return;
    }

}
