package simplecrudoperation;

import java.util.Scanner;

public class Head {
    
    public Head(){
        
    }
    
    public String dbfolder = "Points.csv";
    
    private static String name;
    private static int points;
    
    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }
    
    public void setPoints(int newPoints){
        points = newPoints;
    }
    public String getPoints(){
        return String.valueOf(points);
    }
    
    Scanner sc = new Scanner(System.in);
    
    public Create setCreateMethod;
    public WriteDb setWriteMethod;
    public ReadDb setReadMethod;
    
    public void createNow(){
         setCreateMethod.create();
    }
    
    public void writeNow(){
        setWriteMethod.writeDb();
    }
    
    public void readNow(){
        setReadMethod.readDb();
    }
    
}
