package simplecrudoperation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//Create db
interface Create{
    public void create();
}

class CreateDb extends Head implements Create {

    @Override
    public void create() {
        File file = new File(dbfolder);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(CreateDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

interface ReadDb {
    public void readDb();
}

class ReadDbAndShow extends Head implements ReadDb{

    @Override
    public void readDb() {
        File file = new File(dbfolder);
        
        if(file.exists()){
            String line = "";
        
            try {
                BufferedReader br = new BufferedReader(new FileReader(dbfolder));

                while ((line=br.readLine())!=null){
                    String[] dblines = line.split(",");
                    System.out.println("Participant name: "+dblines[0].replace("\"", "") + "\t\t" +"Points: "+ dblines[1].replace("\"", ""));
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadDbAndShow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadDbAndShow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Database does not exists");
        }
        
    }
    
}

interface WriteDb {
    public void writeDb();
}

class InputDataToDb extends Head implements WriteDb{

    @Override
    public void writeDb() {
        
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(dbfolder, true));
            
            String[] data = {getName(), getPoints()};
            
            writer.writeNext(data);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(InputDataToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class UpdateCell extends Head implements WriteDb{

    @Override
    public void writeDb() {
        
        try {
            CSVReader reader = new CSVReader(new FileReader(dbfolder), ',');
            List<String[]> Body = reader.readAll();
            
               for(int i = 0; i <Body.size(); i++){
                   String[] row = Body.get(i);
                   for(int j = 0; j<row.length; j++){
                       if(Body.get(i)[0].equalsIgnoreCase(getName())){
                           Body.get(i)[1] = getPoints();
                       }
                   }
               }
               
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(dbfolder), ',');
            writer.writeAll(Body);
            writer.flush();
            writer.close();
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UpdateCell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UpdateCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

class DeleteRow extends Head implements WriteDb{

    @Override
    public void writeDb() {
        
        try {
            CSVReader reader = new CSVReader(new FileReader(dbfolder), ',');
            List<String[]> Body = reader.readAll();
               for(int i = 0; i <Body.size(); i++){
                   String[] row = Body.get(i);
                   for(int j = 0; j<row.length; j++){
                       if(Body.get(i)[0].equalsIgnoreCase(getName())){
                           Body.remove(i);
                           ;
                       }
                   }
               }
               
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(dbfolder), ',');
            writer.writeAll(Body);
            writer.flush();
            writer.close();
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UpdateCell.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UpdateCell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

