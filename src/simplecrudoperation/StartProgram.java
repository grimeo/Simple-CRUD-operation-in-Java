package simplecrudoperation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class StartProgram extends Head{
    
    void displayOptions(){
            System.out.println("\nWelcome to Insert Points Corp!\n");
        }
    
    public StartProgram() throws IOException{
        
        displayOptions();
        System.out.println(""
                    + "Type one of the following to proceed."
                    + "\n\"add\" to add user, "
                    + "\n\"update\" to update points,"
                    + "\n\"display\" to display participants and points,"
                    + "\n\"delete\" to delete participant."
                    + "\n\n\"exit\" to close the console"
                    + "\n");
        
        Scanner sc = new Scanner(System.in);
        
        String input = sc.next();
        System.out.println("");
        
        File file = new File(dbfolder);
        if(file.exists()){
            //do nothing
        } else{
            file.createNewFile();
        }
        
        if(input.equalsIgnoreCase("add")){
            new GatherData();
            new StartProgram();
        } else if (input.equalsIgnoreCase("update")){
            new UpdatePoints();
            new StartProgram();
        } else if (input.equalsIgnoreCase("display")){
            new DisplayData();
            new StartProgram();
        } else if (input.equalsIgnoreCase("delete")){
             new DeleteParticipants();
             new StartProgram();
        } else if(input.equalsIgnoreCase("exit")){
            System.exit(0);
        } else {
            new StartProgram();
            
        }
    }
}

class GatherData extends Head{
    
    public GatherData() throws IOException{
        
        System.out.print("Type name: ");
        setName(sc.next());
        
        System.out.print("Type points: ");
        setPoints(sc.nextInt());
        
        File file = new File(dbfolder);
        if(file.exists()){
            setWriteMethod = new InputDataToDb();
            writeNow();
            System.out.println("\nParticipant "+getName()+ " has been added.");
            
            
        } else {
            setCreateMethod = new CreateDb();
            createNow();
            writeNow();
        }
        
    }
}

class DisplayData extends Head{
    public DisplayData(){
        File file = new File(dbfolder);
        if(file.exists()){
            setReadMethod = new ReadDbAndShow();
            readNow();
        } else {
            System.out.println("Database does not exists.");
        }
    }
}

class UpdatePoints extends Head {
    public UpdatePoints(){
        
        System.out.print("Find Name: ");
        setName(sc.next());
        
        System.out.print("Set Points to update: ");
        setPoints(sc.nextInt());
        
        File file = new File(dbfolder);
        
        if (file.exists()){
            setWriteMethod = new UpdateCell();
            writeNow();
            System.out.println("\nPoints is now updated.\n");
            
        } else {
            System.out.println("Database does not exists.");
        }
        
    }
}

class DeleteParticipants extends Head {
    public DeleteParticipants(){
        System.out.print("Name of participants to delete: ");
        setName(sc.next());
        
        File file = new File(dbfolder);
        
        if (file.exists()){
            setWriteMethod = new DeleteRow();
            writeNow();
            System.out.println("Participant is gone");
        } else {
            System.out.println("Database does not exists.");
        }
    }
}