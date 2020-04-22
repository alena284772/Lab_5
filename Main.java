package com.company;

import java.util.LinkedHashMap;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileWork.setFilewithcollection(scanner);
        Collection collection=new Collection();
        LinkedHashMap<Integer,Vehicle> map=collection.Create();
        FileWork.FileReader(map);
        HistoryList historyList = new HistoryList(6);

        Commander commander=new Commander();
        commander.go(scanner,historyList,map,collection);
        }
    }


