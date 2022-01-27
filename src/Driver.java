import java.io.Serializable;

public abstract class Driver implements Serializable {
    private String name;
    private String location;
    private String teamName;
    private int numberOfRaces;



    public Driver(String name,String location,String teamName,int numberOfRaces){
        this.name=name;
        this.location=location;
        this.teamName=teamName;
        this.numberOfRaces=numberOfRaces;

    }

     Driver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getNumberOfRaces() {
        return numberOfRaces;
    }

    public void setNumberOfRaces(int numberOfRaces) {
        this.numberOfRaces = numberOfRaces;
    }

    @Override
    public String toString(){
        return "Driver Name:"+" "+name+" Location:"+" "+location+" Team Name:"+teamName;
    }
}
