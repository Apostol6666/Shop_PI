package com.company;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class Main {

    Main(){}

    public static void WriteResult(TreeMap<String, List> myBD) {
        //Печатаем результат
        for (Map.Entry<String, List> item : myBD.entrySet()) {
            System.out.print(item.getKey());
            System.out.println(':');
            for (Map.Entry<String, Integer> it : item.getValue().bye.entrySet()) {
                System.out.print(it.getKey());
                System.out.print(' ');
                System.out.println(it.getValue());
            }
            System.out.println(' ');
        }
    }

    public static String[] getBuy(String line) {
        //возвраает массив из трех элементов (покупатель - продукт - количество)
        String[] customer= new String[3];
        int count=0;

        for (String l : line.split(" ")) {
            customer[count] = l;
            count++;
        }
        return customer;
    }

    public static TreeMap<String, List> BuyesToMap(ArrayList<String> buyes) {
        TreeMap<String, List> bd = new TreeMap<>();

        for (String line : buyes) {
            String[] customer= new String[3];
            customer = getBuy(line);
            //customer - массив из трех элементов (покупатель - продукт - количество)

            List buy = new List();
            buy.bye = new TreeMap<>();

            if (bd.get(customer[0])==null) { //если такого покупателя у нас еще не было, создаем такой ключ
                buy.bye.put(customer[1], Integer.parseInt(customer[2]));
                bd.put(customer[0], buy);  //создаем объект из одной покупки
            } else { //если такой покупатель был, добавляем ключ
                bd.get(customer[0]).bye.put(customer[1],Integer.parseInt(customer[2])); //создаем объект из одной покупки
            }
        }
        return bd;
    }

    public static ArrayList<String> getBuyes(String filename) {
        //считать покупки из файла
        ArrayList<String> buyes = new ArrayList<String>();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                buyes.add(line);
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return buyes;
    }

    public static ArrayList<String> getBuyesConsole() {
        ArrayList<String> buyes = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(System.in);

            String line = sc.nextLine();
            while (!line.isEmpty()) {
                    buyes.add(line);
                    line = sc.nextLine();
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return buyes;
    }

    public static void main(String[] args) {
        //ArrayList<String> buyes = getBuyes("list.txt");
        ArrayList<String> buyes = getBuyesConsole();
        TreeMap<String, List> myBD = BuyesToMap(buyes); // myBD - массив из покупок

        WriteResult(myBD); //печатаем результат
    }
}
