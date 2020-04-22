package com.company;

import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * A class whose collection of instances the program manages.
 */
public class Vehicle implements Comparable<Vehicle>{
    /**
     * Значение поля id должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     */
    private Long id;
    /**
     * Поле name не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Поле coordinates не может быть null
     */
    private Coordinates coordinates;
    /**
     * Поле creationDate не может быть null, Значение этого поля должно генерироваться автоматически
     */
    private java.time.ZonedDateTime creationDate;
    /**
     * Поле enginePower не может быть null, Значение поля должно быть больше 0
     */
    private Float enginePower;
    /**
     * Поле numberOfWheels не может быть null, Значение поля должно быть больше 0
     */
    private Long numberOfWheels;
    /**
     * Поле type может быть null
     */
    private VehicleType type;
    /**
     * Поле fuelType может быть null
     */
    private FuelType fuelType;


    /**
     * Overridden toString () Method
     * @return returns all information about Vehicle
     */
    @Override
    public String toString() {
        return " Vehicle Name: "+getName()+" "+" Vehicle coordinates x y: "+getCoordinates(this.c)+" "+" Vehicle id: "+getId()+" "+" Vehicle Creation Date: "+getCreationDate()+" Engine Power: "+getEnginePower()+" Number Of Wheels: "+getNumberOfWheels()
                +" Vehicle type: "+getType()+" FuelType: "+getFuelType();
    }

    /**
     * Regular constructor - Vehicle
     * @param id
     * @param name
     * @param x
     * @param y
     * @param creationdate
     * @param enginePower
     * @param numberOfWheels
     * @param type
     * @param fuelType
     * @see Id#makeID()
     * @throws VehicleTypeException
     * @throws FuelTypeException
     */
    public Vehicle(Long id,String name,double x,long y,String creationdate ,float enginePower,long numberOfWheels, String type, String fuelType) throws VehicleTypeException, FuelTypeException {
        this.setId(id);
        this.name=name;
        this.setCoordinates(x,y);
        this.creationDate= ZonedDateTime.parse(creationdate);
        try {
            this.setEnginePower(enginePower);
        } catch (EnginePowerException e) {
            System.err.println(e);
        }
        try {
            this.setNumberOfWheels(numberOfWheels);
        } catch (NumberOfWheelsException e) {
            System.err.println(e);
        }
        this.setType(type);
        this.setFuelType(fuelType);
    }

    /**
     * Vehicle constructor using Scanner
     * @param scanner
     * @see Id#makeID()
     */
    public Vehicle(Scanner scanner){

        this.setId(Id.makeID());
        System.out.println("Name:");
        String name = scanner.next();
        double x = -10000;
        long y=-10000;
        this.setName(name);
        while (x <=-815||y<=-774) {
            System.out.println("Coordinate x:");
            while (scanner.hasNextDouble()==false){
                System.out.println("X is a double variable. Try again");
                scanner.nextLine();
            }
            x = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Coordinate y:");
            while (scanner.hasNextLong()==false){
                System.out.println("Y is a long variable. Try again");
                scanner.nextLine();
            }
             y = scanner.nextLong();
            this.setCoordinates(x, y);
        }
        this.setCreationDate();
        while (this.enginePower==null) {
            System.out.println("Engine Power:");
            while (scanner.hasNextFloat()==false){
                System.out.println("Engine Power is a float variable. Try again");
                scanner.nextLine();
            }
            Float Engine = scanner.nextFloat();
            try {this.setEnginePower(Engine);} catch (EnginePowerException E){System.err.println(E);}
        }
        while (this.numberOfWheels==null) {
            System.out.println("Number of wheels:");
            while (scanner.hasNextLong()==false){
                System.out.println("Number of wheels is a long variable. Try again");
                scanner.nextLine();
            }
            long number = scanner.nextLong();
            try {
                this.setNumberOfWheels(number);
            } catch (NumberOfWheelsException e) {
                System.err.println(e);
            }
        }
        while (this.getType()==null) {
            System.out.println("Vehicle types:" +
                    " CAR,\n" +
                    "    PLANE,\n" +
                    "    BICYCLE,\n" +
                    "    MOTORCYCLE,\n" +
                    "    HOVERBOARD");
            String Type = scanner.next();
            try {
                this.setType(Type);
            } catch (VehicleTypeException e) {
                System.out.println("Try again");
            }
        }
        while (this.getFuelType()==null) {
            System.out.println("Fuel types:" +
                    "KEROSENE,\n" +
                    "    ELECTRICITY,\n" +
                    "    NUCLEAR,\n" +
                    "    ANTIMATTER");
            String type = scanner.next();
            try {
                this.setFuelType(type);
            } catch (FuelTypeException e) {
                System.out.println("Try again");
            }
        }
        scanner.nextLine();
    }


    public void setId(long id1) {
       this.id=id1;

    }
    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    Coordinates c=new  Coordinates();

    /**
     *
     * @param x - X coordinate of Vehicle
     * @param y - Y coordinate of Vehicle
     * @see Coordinates#setX(double)
     * @see Coordinates#setY(long)
     */
    public void setCoordinates (double x, long y) {

        try {
            this.c.setX(x);
        } catch (CoordinateException e) {
            System.err.println(e);
        }
        try {
            this.c.setY(y);
        } catch (CoordinateException e) {
            System.err.println(e);
        }
    }
    public String getCoordinates(Coordinates c) {
        return c.getX()+" "+c.getY();
    }

    /**
     * @see ZonedDateTime#now()
     */
    public void setCreationDate() {
        this.creationDate =ZonedDateTime.now();
    }
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param enginePower
     * @throws EnginePowerException
     */
    public void setEnginePower(Float enginePower) throws EnginePowerException {
        if (enginePower<0){
            throw new EnginePowerException("Field value must be greater than 0");
        } else
        {this.enginePower=enginePower;
        }
    }

    public Float getEnginePower() {
        return enginePower;
    }

    /**
     *
     * @param fueltype
     * @throws FuelTypeException
     */
    public void setFuelType (String fueltype) throws FuelTypeException {
        boolean exist = true;
        try {
            FuelType.valueOf(fueltype);
        } catch (Exception E){
            exist=false;
        }
        if(exist==false){
            throw new FuelTypeException("Fuel type is false");

        } else {this.fuelType = FuelType.valueOf(fueltype);}
    }
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     *
     * @param numberOfWheels
     * @throws NumberOfWheelsException
     */
    public void setNumberOfWheels(Long numberOfWheels) throws NumberOfWheelsException {
        if (numberOfWheels<0){
            throw new NumberOfWheelsException ("Field value must be greater than 0");
        } else
        {this.numberOfWheels=numberOfWheels;
        }
    }
    public Long getNumberOfWheels() {
        return numberOfWheels;
    }

    /**
     *
     * @param type
     * @throws VehicleTypeException
     */
    public void setType (String type) throws VehicleTypeException {
       boolean exist = true;
       try {
       VehicleType.valueOf(type);
       } catch (Exception E){
           exist=false;
       }
       if(exist==false){
           throw new VehicleTypeException("Type is false");
       } else {this.type = VehicleType.valueOf(type);}
    }
    public VehicleType getType() {
        return type;
    }

    /**
     *CompareTo method, which takes another Vehicle as an argument and compares it with the given name length and enginePower value
     * @param o
     * @return
     */
    @Override
    public int compareTo(Vehicle o) {
        int result=this.name.length()-o.name.length();
        if(result==0){
            result=this.enginePower.compareTo(o.enginePower);
        }
        return  result;
    }
}