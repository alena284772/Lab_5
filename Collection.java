package com.company;

import java.util.*;

/**
 * This class work with LinkedHashMap collection.
 */
public class Collection {
    /**
     * Field containing the type of collection
     */
    private static String Type="LinkedHashMap";
    /**
     * Field containing the date the collection was created
     */
    private static Date date;
    /**
     * Collection field
     */
    public LinkedHashMap<Integer,Vehicle> map;


    /**
     *Method creating a collection and fixing the date of it's creation
     * @return LinkedHashMap map
     */
    public LinkedHashMap<Integer,Vehicle> Create(){
        this.map = new LinkedHashMap<Integer,Vehicle>();
        Collection.date=new Date();
        return this.map;
    }

    public static Date getDate() {
        return date;
    }


    public static String getType() {
        return Type;
    }

    /**
     *Method returning the type of the collection, date of creation and it's size
     * @return Information about collection
     */
    @Override
    public String toString() {
        return "Type of collection: "+Collection.getType()+" Creation date: "+Collection.getDate()+" Map size: "+map.size();
    }

    /**
     *Method that removes from the collection all elements that are larger than entered
     * @param scanner
     * @param historyList
     */
    public void Remove_greater(Scanner scanner,HistoryList historyList){
        Vehicle v = new Vehicle(scanner);
        List<Object> listA = new ArrayList();
        Set set = map.entrySet();
        Iterator<LinkedHashMap<Integer,Vehicle>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            if (map.get(item.getKey()).compareTo(v) > 0) {
                listA.add(item.getKey());
            }
        }
        for (int i = 0; i < listA.size(); i++) {
            map.remove(listA.get(i));
            System.out.println("Object with key: " + listA.get(i) + " was delete");
        }
        historyList.insert("remove_greater");
    }

    /**
     *Method removing an item from the collection by it's key
     * @param string
     * @param scanner
     * @param historyList
     */
    public void Remove_key(String string,Scanner scanner,HistoryList historyList){
        Integer Key= Key_check(string,scanner);
        if (map.containsKey(Key) == true) {
            map.remove(Key);
            System.out.println("The element with the specified key has been deleted.");
        } else {
            System.out.println("Element with this key doesn't exist");
        }

        historyList.insert("remove_key");
    }

    /**
     *A method that changes the value by key, if the entered value is greater than the old
     * @param scanner
     * @param string
     * @param historyList
     */
    public void Replace_if_greater(Scanner scanner,String string,HistoryList historyList){
        Integer Key = Key_check(string,scanner);
        if (map.containsKey(Key) == true) {
            Vehicle v = new Vehicle(scanner);
            if (map.get(Key).compareTo(v) < 0) {
                map.put(Key, v);
                System.out.println("Element value has been changed");
            } else {
                System.out.println("Element value has NOT been changed");
            }
        } else {
            System.out.println("Element with this key doesn't exist");
        }

        historyList.insert("replace_if_greater");
    }

    /**
     *A method that removes one element from the collection, the value of the numberOfWheels field of which is equivalent to the specified
     * @param string
     * @param scanner
     * @param historyList
     */
    public void Remove_any_by_number_of_wheels(String string,Scanner scanner,HistoryList historyList){
        Long number=Long_check(string,scanner);
        Integer Key = null;
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            if (map.get(item.getKey()).getNumberOfWheels() == number) {
                Key = Integer.valueOf((Integer) item.getKey());
            }
        }
        if(Key==null){
            System.out.println("The object was not deleted, because an object with this number of wheels does not exist.");
        } else{
            map.remove(Key);
            System.out.println("Object is deleted");
        }
        historyList.insert("remove_any_by_number_of_wheels");
    }

    /**
     *A method that displays the number of elements whose enginePower field value is less than the specified
     * @param string
     * @param scanner
     * @param historyList
     */
    public void Count_less_than_engine_power(String string,Scanner scanner,HistoryList historyList){
        Float power=Float_check(string,scanner);
        int a = 0;
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            if (map.get(item.getKey()).getEnginePower() < power) {
                a = a + 1;
            }
        }
        System.out.println("Number of objects is " + a);
        historyList.insert("count_less_than_engine_power");
    }

    /**
     * A method that updates the value of a collection element whose id is equal to the specified
     * @param string
     * @param scanner
     * @param historyList
     */
    public void Update_id(String string,Scanner scanner,HistoryList historyList){
        Long ID = Long_check(string,scanner);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        Integer key=null;
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            if (map.get(item.getKey()).getId() == ID) {
                key=Integer.valueOf((Integer) item.getKey());
                Vehicle veh = new Vehicle(scanner);
                map.put(Integer.valueOf((Integer) item.getKey()), veh);
                System.out.print("Object with this id is update");
            }
        }
        if(key==null){
            System.out.println("The item was not updated because the item with this ID does not exist.");
        }

        historyList.insert("update_id");
    }

    /**
     * A method that adds a new item with a given key
     * @param string
     * @param scanner
     * @param historyList
     * @throws NumberFormatException
     */
    public void Insert(String string,Scanner scanner,HistoryList historyList) throws NumberFormatException {
        Integer Key = Key_check(string,scanner);
        Vehicle veh = new Vehicle(scanner);
        map.put(Key, veh);

        historyList.insert("insert");
    }

    /**
     *A method that outputs to the standard output stream all the elements of the collection in a string representation
     * @param historyList
     */
    public void Show(HistoryList historyList){
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
        }
        historyList.insert("show");
    }

    /**
     * A method that checks whether the value entered is Integer
     * @param string
     * @param scanner
     * @return Key value
     */
    public Integer Key_check(String string, Scanner scanner){
        Integer Key = null;
        try {
            Key = Integer.valueOf(string);
        } catch (NumberFormatException E) {
            System.out.println("Input arg of key is incorrect. Try again");
            while (Key==null) {
                System.out.println("Key:");
                try{Key=Integer.valueOf(scanner.next());} catch (NumberFormatException e) {
                    System.out.println("Input arg of key is incorrect. Try again");
                }
            }
        }
        return Key;
    }

    /**
     * A method that checks whether the entered value is Long
     * @param string
     * @param scanner
     * @return Long value
     */
    public Long Long_check(String string, Scanner scanner){
        Long aLong = null;
        try {
            aLong = Long.valueOf(string);
        } catch (NumberFormatException E) {
            System.out.println("Input arg of long value is incorrect. Try again");
            while (aLong==null) {
                System.out.println("Long value:");
                try{aLong=Long.valueOf(scanner.next());} catch (NumberFormatException e) {
                    System.out.println("Input arg of long value is incorrect. Try again");
                }
            }
        }
        return aLong;
    }

    /**
     * A method that checks whether the entered value is Float
     * @param string
     * @param scanner
     * @return Float value
     */
    public Float Float_check(String string,Scanner scanner){
        Float aFloat = null;
        try {
            aFloat = Float.valueOf(string);
        } catch (NumberFormatException E) {
            System.out.println("Input arg of float value is incorrect. Try again");
            while (aFloat==null) {
                System.out.println("Float value:");
                try{aFloat=Float.valueOf(scanner.next());} catch (NumberFormatException e) {
                    System.out.println("Input arg of float value is incorrect. Try again");
                }
            }
        }
        return aFloat;
    }
}
