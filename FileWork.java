package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Class working with files
 */
public class FileWork  {
    private static File Filewithcollection;

    /**
     * The method reads the script from the specified file. The script contains commands in the same form in which they are entered by the user interactively.
     * @param string
     * @param historyList
     * @param map
     * @param collection
     */
    public static void ScriptReader(String string, HistoryList historyList, LinkedHashMap<Integer,Vehicle> map,Collection collection) {
        File file1=new File(string);
        try {
            Scanner scan = new Scanner(file1);
            Commander C=new Commander();
            while (scan.hasNextLine()){
                C.go(scan,historyList,map,collection);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.out.println("Try again");

        }
        historyList.insert("Execute_script");

    }

    /**
     * Method reading a collection from a file
     * @param map
     */
    public static void FileReader( LinkedHashMap<Integer,Vehicle> map){
        ArrayList<Long> list = new ArrayList<>();
        list.add(Id.getID());
        try {

            Scanner Scanner = new Scanner(FileWork.Filewithcollection);
            Scanner.nextLine();
            while (Scanner.next().equals("<Key>")) {
            String key = Scanner.next();
            Scanner.nextLine();
            Scanner.nextLine();
            Scanner.skip("   <Id> ");
            long id = Scanner.nextLong();
            list.add(id);
           Scanner.nextLine();
            Scanner.skip("   <Name> ");
            String name = Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <Coordinate_x> ");
            String x=Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <Coordinate_y> ");
            String y=Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <creationDate> ");
            String creationDate = Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <enginePower> ");
            String Engine = Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <numberOfWheels> ");
            String number = Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <type> ");
            String Type = Scanner.next();
            Scanner.nextLine();
            Scanner.skip("   <fuelType> ");
            String type = Scanner.next();
            Scanner.nextLine();
            Scanner.nextLine();

            try {
                Vehicle vehicle = new Vehicle(id,name, Double.valueOf(x), Long.valueOf(y), creationDate,Float.valueOf(Engine), Long.valueOf(number), Type, type);
                map.put(Integer.valueOf(key), vehicle);
            } catch (VehicleTypeException e) {
                e.printStackTrace();
            } catch (FuelTypeException e) {
                e.printStackTrace();
            }
        }

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        Id.setID(Collections.max(list));

    }

    /**
     *Method writing a collection to a file
     * @param map
     * @param historyList
     */
    public static void FileWriter(LinkedHashMap<Integer,Vehicle> map,HistoryList historyList ){
        try (PrintWriter printWriter = new PrintWriter(FileWork.Filewithcollection)) {
            printWriter.printf("<%s> %n","Collection");
            Set set = map.entrySet();
            Iterator<LinkedHashMap<Integer,Vehicle>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry item = (Map.Entry) iterator.next();
                printWriter.printf(" <Key> %s </Key> %n", item.getKey());
                printWriter.printf("  <%s> %n","Vehicle");
                printWriter.printf("   <Id> %s </Id> %n", map.get(item.getKey()).getId());
                printWriter.printf("   <Name> %s </Name> %n", map.get(item.getKey()).getName());
                printWriter.printf("   <Coordinate_x> %s </Coordinates_x> %n", map.get(item.getKey()).c.getX());
                printWriter.printf("   <Coordinate_y> %s </Coordinates_y> %n", map.get(item.getKey()).c.getY());
                printWriter.printf("   <creationDate> %s </creationDate> %n", map.get(item.getKey()).getCreationDate());
                printWriter.printf("   <enginePower> %s </enginePower> %n", map.get(item.getKey()).getEnginePower());
                printWriter.printf("   <numberOfWheels> %s </numberOfWheels> %n", map.get(item.getKey()).getNumberOfWheels());
                printWriter.printf("   <type> %s </type> %n", map.get(item.getKey()).getType());
                printWriter.printf("   <fuelType> %s </fuelType> %n", map.get(item.getKey()).getFuelType());
                printWriter.printf("  <%s> %n","/Vehicle");
            }
            printWriter.printf("<%s>", "/Collection");
            printWriter.close();
        } catch (Exception e){
            e.getMessage();
        }
        historyList.insert("save");
    }

    /**
     *File Check Method
     * @param scanner
     */
    public static void setFilewithcollection(Scanner scanner){
        System.out.println("Enter the file that contains the collection in xml format or exit:");
        String filename = scanner.nextLine();
        if(filename.equals("exit")){
            System.exit(0);
        } else {
            File filewithcollection = new File(filename);
            if (filewithcollection.exists() == false) {
                System.out.println("No matching file found");
                FileWork.setFilewithcollection(scanner);
            } else {
                FileWork.Filewithcollection = filewithcollection;
            }
        }
    }
}
