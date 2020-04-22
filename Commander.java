package com.company;

import java.util.*;

/**
 * Class that read commands
 */
public class Commander {
    /**
     * Method outputting possible commands
     * @param historyList
     */
    public void Help(HistoryList historyList){
        System.out.println("help: вывести справку по доступным командам");
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("insert key {element}: добавить новый элемент с заданным ключом");
        System.out.println("update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_key key: удалить элемент из коллекции по его ключу");
        System.out.println("clear: очистить коллекцию");
        System.out.println("save: сохранить коллекцию в файл");
        System.out.println("execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit: завершить программу (без сохранения в файл)");
        System.out.println("remove_greater {element}: удалить из коллекции все элементы, превышающие заданный");
        System.out.println("history: вывести последние 6 команд(без их аргументов)");
        System.out.println("replace_if_greater key {element}: заменить значение по ключу, если новое значение больше старого");
        System.out.println("remove_any_by_number_of_wheels numberOfWheels:удалить из коллекции один элемент, значение поля numberOfWheels которого эквивалентно заданному");
        System.out.println("count_less_than_engine_power: вывести количество элементов, значение поля enginePower которых меньше заданного");
        System.out.println("print_field_ascending_type: вывести значения поля type в порядке возрастания");
        historyList.insert("help");
    }

    /**
     * Command read method
     * @param scanner
     * @param historyList
     * @param map
     * @param collection
     * @return command execution result
     */
    public boolean go(Scanner scanner, HistoryList historyList, LinkedHashMap<Integer,Vehicle> map, Collection collection) {

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] input_ar = command.split(" ");
            switch (input_ar[0]) {

                case  ("help"):
                    this.Help(historyList);
                    break;

                case ("info"):
                    System.out.println(collection.toString());
                    historyList.insert("info");
                    break;

                case ("show"):
                    collection.Show(historyList);
                    break;

                case ("insert"):
                    collection.Insert(input_ar[1], scanner, historyList);
                    break;

                case ("update"):
                    collection.Update_id(input_ar[2], scanner, historyList);
                    break;

                case ("remove_key"):
                    collection.Remove_key(input_ar[1],scanner, historyList);
                    break;

                case ("clear"):
                    map.clear();
                    historyList.insert("clear");
                    break;

                case ("save"):
                    FileWork.FileWriter(map, historyList);
                    break;


                case ("execute_script"):
                    FileWork.ScriptReader(input_ar[1], historyList, map, collection);
                    break;

                case ("exit"):
                    return false;
                case ("remove_greater"):
                    collection.Remove_greater(scanner, historyList);
                    break;

                case ("history"):
                    historyList.show();
                    historyList.insert("history");
                    break;

                case ("replace_if_greater"):
                    collection.Replace_if_greater(scanner, input_ar[1], historyList);
                    break;

                case ("remove_any_by_number_of_wheels"):
                    collection.Remove_any_by_number_of_wheels(input_ar[1],scanner, historyList);
                    break;

                case ("count_less_than_engine_power"):
                    collection.Count_less_than_engine_power(input_ar[1],scanner, historyList);
                    break;

                case ("print_field_ascending_type"):
                    List<VehicleType> VehT = Arrays.asList(VehicleType.values());
                    Collections.sort(VehT);
                    System.out.println(VehT);
                    historyList.insert(input_ar[0]);
                    break;

                default:
                    System.out.println(input_ar[0]);
                    System.out.println("Incorrect command. Try again");
                    this.Help(historyList);
                    break;

            }
        }

        return false;
    }

    }


