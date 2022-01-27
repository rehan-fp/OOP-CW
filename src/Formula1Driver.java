import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Formula1Driver extends Driver implements Serializable {
    private int totalPoints;
    private ArrayList<Integer>Statics=new ArrayList<Integer>(9);



    public Formula1Driver(String name,String location,String teamName,int numberOfRaces){
        super(name,location,teamName,numberOfRaces);

    }
    public Formula1Driver(){
        creatPlayer();
    }


    public ArrayList<Integer> getStatics() {

        return Statics;
    }

    public void setStatics(ArrayList<Integer> Statics) {

        this.Statics = new ArrayList<Integer>();
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    //Calculating the players total points according to their winning positions
    public void setTotalPoints(ArrayList<Integer> Statics) {
        int total=0;
        for(int i=0;i<Statics.size();i++){
            if(i==0){
                total=Statics.get(i)*25;
            }else if(i==1){
                total=Statics.get(i)*18;
            }else if(i==2){
                total=Statics.get(i)*15;
            }else if(i==3){
                total=Statics.get(i)*12;
            }else if(i==4){
                total=Statics.get(i)*10;
            }else if(i==5){
                total=Statics.get(i)*8;
            }else if(i==6){
                total=Statics.get(i)*6;
            }else if(i==7){
                total=Statics.get(i)*4;
            }else if(i==8){
                total=Statics.get(i)*2;
            }else if(i==9){
                total=Statics.get(i)*1;
            }

            totalPoints+=total;
        }

    }

    //creating a new player
    void creatPlayer() {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter driver's name: ");
        String name=scanner.next();
        setName(name);

        System.out.print("Enter driver's location: ");
        String location=scanner.next();
        setLocation(location);


        System.out.print("Enter driver's team name: ");
        String teamname=scanner.next();
        setTeamName(teamname);

        System.out.print("Enter the number of racers driver participated: ");
        int numraces=scanner.nextInt();
        setNumberOfRaces(numraces);

        for(int i=0;i<10;i++){
            System.out.print("Enter number of "+(i+1)+" places driver won: ");
            Integer statics=scanner.nextInt();
            Statics.add(i,statics);
        }
        setTotalPoints(Statics);
    }









}


