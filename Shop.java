package com.epam.task2;

import java.util.Map;
import java.io.*;


public class Shop {

    private Map<SportEquipment, Integer> goods;
    private Map<String, RentUnit> rents;

    public Shop(String shopFileName, String rentFileName) {

        try {
            BufferedReader input = new BufferedReader(new FileReader(shopFileName));
            String line = null;
            String[] good;
            while (( line = input.readLine()) != null)
            {
                System.out.println(line);
                good = line.split(" ");
                if (good.length == 4) {
                    SportEquipment equipment = new SportEquipment(good[0], good[1], Integer.parseInt(good[2]));
                    goods.put(equipment, Integer.parseInt(good[3]));
                }

            }
            input.close();


        } catch (IOException | NumberFormatException e) {
            System.out.println("Inappropriate shopFile!");

        }

        try {
            BufferedReader input = new BufferedReader(new FileReader(rentFileName));
            String line = null;
            String[] rent;
            while (( line = input.readLine()) != null)
            {
                System.out.println(line);
                rent = line.split(" ");
                int numberOfRentsPerPerson = Integer.parseInt(rent[0]);
                String name = rent[1];
                RentUnit rentUnit = new RentUnit();
                for (int i = 0; i < numberOfRentsPerPerson; i++) {
                    SportEquipment equipment = new SportEquipment(rent[3*i+2], rent[3*i+3], Integer.parseInt(rent[3*i+4]));
                    rentUnit.addEquipment(equipment);
                }
                rents.put(name, rentUnit);

            }
            input.close();


        } catch (IOException | NumberFormatException e) {
            System.out.println("Inappropriate rentFile!");

        }

    }

    public int findEquipment(SportEquipment equipment) {
        return goods.get(equipment);
    }

    public void arrivalOfEquipment(SportEquipment equipment, int amount) {
        int number = goods.get(equipment);
        goods.put(equipment, number + amount);
    }

    public void rentEquipment(SportEquipment equipment, String name) {
        RentUnit unit = rents.get(name);
        if(unit != null) {
            if(unit.addEquipment(equipment)) {
                rents.put(name, unit);
            }
        } else {
            unit = new RentUnit();
            if(unit.addEquipment(equipment)) {
                rents.put(name, unit);
            }
        }

    }

    public RentUnit findRents(String name) {
        return rents.get(name);
    }

    public void printShopReport() {
        System.out.println("ShopReport");
        System.out.println("Category Title Price Amount");
        for (Map.Entry entry : goods.entrySet()) {

            SportEquipment equipment = (SportEquipment)entry.getKey();
            System.out.print(equipment.getCategory() + " " + equipment.getTitle() + " " + equipment.getPrice() + " ");
            System.out.println(entry.getValue());
        }
        System.out.println("------------------------------------------");
    }

    public void printRentReport() {
        System.out.println("RentReport");
        System.out.println("Name");
        System.out.println("Category Title Price");
        for (Map.Entry entry : rents.entrySet()) {

            System.out.println(entry.getKey());
            RentUnit rentUnit = (RentUnit)entry.getValue();
            for(SportEquipment equipment : rentUnit.getUnits())
            {
                System.out.println(equipment.getCategory() + " " + equipment.getTitle() + " " + equipment.getPrice());
            }
            System.out.println("------------------------------------------");
        }
        System.out.println("------------------------------------------");
    }

    public void saveGoodsInFile(String fileName) {
        try {
            Writer output = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry entry : goods.entrySet()) {

                SportEquipment equipment = (SportEquipment)entry.getKey();
                output.write(equipment.getCategory() + " " + equipment.getTitle() + " " + equipment.getPrice() + " " + entry.getValue());
            }
            output.close();
        } catch (IOException e) {

        }

    }
    

}
