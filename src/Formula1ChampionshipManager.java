import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class  Formula1ChampionshipManager implements ChampionshipManager {
    private int numberOfDrivers;
    private ArrayList<Formula1Driver> driver;
    private ArrayList<Integer> race=new ArrayList<>();

    Formula1ChampionshipManager(int numberOfDrivers,ArrayList<Formula1Driver>driver,ArrayList<Integer>race) {
        this.numberOfDrivers = numberOfDrivers;
        this.driver=driver;
        this.race=race;
    }

    Formula1ChampionshipManager(){}

    public void menu() throws IOException {
        System.out.println("");
        System.out.println("Welcome to F1 Championship");
        System.out.println("");
        setNumberOfDrivers();
        System.out.println("");

        Scanner scan = new Scanner(System.in);

        //Menu
        do {
            System.out.println("         MENU                   ");
            System.out.println(" ");
            System.out.println("Enter ‘1’ to create a new driver");
            System.out.println("Enter ‘2’ to delete a driver");
            System.out.println("Enter '3' to change the team");
            System.out.println("Enter ‘4’ to display the statics");
            System.out.println("Enter ‘5’ to display all");
            System.out.println("Enter ‘6’ to add a race");
            System.out.println("Enter ‘7’ to save to file");
            System.out.println("Enter ‘8’ to read the file");
            System.out.println("Enter ‘0’ to end the programme");
            System.out.print("Enter your choice: ");
            int menu = scan.nextInt();

            System.out.println("");

            if (menu == 1) {
                addDriver();
            }

            if (menu == 2) {
                deleteDriver();
            }

            if (menu == 3) {
                changeTeam();
            }

            if (menu == 4) {
                displayStatics();
            }

            if (menu == 5) {
                displayAll();
            }

            if (menu == 6) {
                addRace();
            }

            if (menu == 7) {
                saveAll();
            }

            if (menu == 8) {
                readFromFile();
            }

            if (menu == 0) {
                System.out.println("Thank You!");
                break;
            }

        } while (true);
    }

    public int getNumberOfDrivers() {
        return numberOfDrivers;
    }


    public void setNumberOfDrivers() {
        //asking the user how many players you are going to add
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of drivers: ");
        this.numberOfDrivers = scan.nextInt();
        System.out.println("");
        //all player details adding to an list
        setDriver();
    }

    //adding a new driver to the list
    public void addDriver() {
        //checking whether user exceeding the adding limit
        if (getDriver().size() < getNumberOfDrivers()) {
            //adding a new driver to the list
            getDriver().add(new Formula1Driver());
            System.out.println("");

            //checking the team name duplication
            Set<String> s = new HashSet<>();
            for(int i=0;i<getDriver().size();i++) {
                if (!s.add(getDriver().get(i).getTeamName())) {
                    System.out.println("Sorry this team already in the system");
                    getDriver().remove(getDriver().get(i));
                }else{
                    continue;
                }
            }
        } else {
            System.out.println("Can't add any more drivers");
        }
        System.out.println("");
    }

    //removing the driver from the list
    public void deleteDriver() {
        Scanner scan = new Scanner(System.in);
        //asking user to enter the drivers team name that's want to delete
        System.out.print("Enter driver's team name: ");
        String deleteTeam = scan.next();

        for (int i = 0; i < getDriver().size(); i++) {
            //looking whether user entered name and team name equal with name and team name stored arraylist
            if (getDriver().get(i).getTeamName().equals(deleteTeam)) {
                //if equal removing from the arraylist
                getDriver().remove(getDriver().get(i));
                System.out.println("Delete Completed");

            } else {
                //if not equal giving a message to check the name and team name
                System.out.println("Please check your team name");
            }
            System.out.println("");
        }
    }

    // changing the drivers current team
    public void changeTeam() {
        Scanner scan = new Scanner(System.in);
        //user input for the teamName
        System.out.print("Enter driver's team name: ");
        String changeTeam = scan.next();
        System.out.println("");

        for (int i = 0; i < getDriver().size(); i++) {
            //looking whether user input name and teamName are equal with the Arraylist
            if (getDriver().get(i).getTeamName().equals(changeTeam)) {
                //removing the driver from the list
               getDriver().remove(getDriver().get(i));
               //adding as a new driver to the system
               addDriver();
               //giving a message to user team change was successful
               System.out.println(getDriver().get(i).getName()+" you are added to team "+getDriver().get(i).getTeamName());
            } else {
                //if user entered name or teamName not matching with list giving and error message
                System.out.println("Please check your name and current team name");
            }
            System.out.println("");
        }
    }

    //displaying the statics of the player
    public void displayStatics() {
        for (int i = 0; i < getDriver().size(); i++) {
            System.out.println(getDriver().get(i).getName() + " participated in " + getDriver().get(i).getNumberOfRaces() + " racers and got " + getDriver().get(i).getTotalPoints() + " points.");
        }
        System.out.println("");
    }

    //displaying full details of the driver in a table
    public void displayAll() {
        //combined exiting array with new array
        ArrayList<Formula1Driver> comp = new ArrayList<>(driver);

        class PointComparator implements Comparator<Formula1Driver> {
            //Comparing total points using two objects
            public int compare(Formula1Driver o1, Formula1Driver o2) {
                //looking whether o1 and o2 total are equal
                if (o1.getTotalPoints() == o2.getTotalPoints()) {
                    //if equal comparing the 1st places
                    if (o1.getStatics().get(0) < o2.getStatics().get(0)) {
                        return 1;//returns highest 1 position achiever
                    } else if (o1.getStatics().get(0) == o2.getStatics().get(0)) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {

                    if (o1.getTotalPoints() < o2.getTotalPoints()) {
                        return 1;//returns the highest points achiever
                    } else if (o1.getTotalPoints() == o2.getTotalPoints()) {
                        return 0;
                    } else {
                        return -1;

                    }
                }
            }
        }

        //displaying driver details in a table
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\n| %20s | %25s | %15s | %8s | %8s | %10s |\n", "Name", "Location", "TeamName", "NumberOfRacers", "TotalPoints", "1st places");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        //sorting the points
        comp.sort(new PointComparator());
        for (Formula1Driver f1 : comp) {
            System.out.printf("| %20s | %25s | %15s | %14s | %11s | %10s |\n", f1.getName(), f1.getLocation(), f1.getTeamName(), f1.getNumberOfRaces(), f1.getTotalPoints(), f1.getStatics().get(0));
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    //generating a new race
    public void addRace() {
        //getting the present time and date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //creating a array to store shuffle element
        ArrayList<String> newArrayList=new ArrayList<>();
        //shuffling the existing arraylist to generate random positions
        Collections.shuffle(driver);
        System.out.println("Race Summary"+" ( "+dtf.format(now)+" ) ");


        //Updating the statics of the drivers
        for (int i=0;i<getDriver().size();i++) {
            ArrayList<Integer> stats = new ArrayList<Integer>();
            stats.add(i+1);
            getDriver().get(i).setTotalPoints(stats);
            getDriver().get(i).setNumberOfRaces(getDriver().get(i).getNumberOfRaces() + 1);
            System.out.println(getDriver().get(i).getName()+" is "+(i+1));
            newArrayList.add(getDriver().get(i).getName());
        }

        //Saving the race details in to file
        try {
            FileWriter myWriter = new FileWriter("RaceSummary.txt");
            myWriter.write(dtf.format(now)+",");
            for (int i = 0; i < newArrayList.size() ; i++) {
                myWriter.write(getDriver().get(i).getName()+ ",");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.println(" ");
    }

    //Saving all driver details into a txt file
    public void saveAll() {
        //adding all the driver details into text file(for GUI use)
        try {
            FileWriter myWriter = new FileWriter("F1Table_GUI.txt");
            for (int i = 0; i < getDriver().size(); i++) {
                myWriter.write(getDriver().get(i).getName() + ",");
                myWriter.write(getDriver().get(i).getLocation() + ",");
                myWriter.write(getDriver().get(i).getTeamName() + ",");
                myWriter.write(getDriver().get(i).getNumberOfRaces() + ",");
                myWriter.write(getDriver().get(i).getTotalPoints() + ",");
                myWriter.write(getDriver().get(i).getStatics().get(0) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" ");

        //saving data into file(purpose of edit and resume)
        String driverDataPath = new File("F1.txt").getAbsolutePath();
        FileHandling save = new FileHandling();
        //Saves driver related data to the driver save file
        save.writeObjectToFile(driverDataPath, driver);
    }

    //reading the file
    public void readFromFile(){
        //reading the saved text file
        String driverDataPath = new File("F1.txt").getAbsolutePath();
        FileHandling read = new FileHandling();
        //Reads driver related data from the driver save file
        driver = (read.readObjectFile(driverDataPath));
    }



    public ArrayList<Formula1Driver> getDriver() {
        return driver;
    }
    public void setDriver() {this.driver =  new ArrayList<Formula1Driver>();} // created the arraylist
    public ArrayList<Integer> getRace() {return race;}
    public void setRace(ArrayList<Integer> race) {this.race = new ArrayList<Integer>();}// created the arraylist



    public static void main(String[] args)  {
        Formula1ChampionshipManager champ = new Formula1ChampionshipManager() {};
        GUI gui=new GUI(){};
        try {
            champ.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

