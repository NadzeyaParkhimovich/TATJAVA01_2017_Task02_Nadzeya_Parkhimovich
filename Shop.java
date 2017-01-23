

import java.util.HashMap;
import java.util.Map;
import java.io.*;


public class Shop {

    private Map<SportEquipment, Integer> goods = new HashMap<SportEquipment, Integer>();
    private Map<String, RentUnit> rents = new HashMap<String, RentUnit>();

    public Shop(String shopFileName, String rentFileName) {

        try {
            BufferedReader input = new BufferedReader(new FileReader(shopFileName));
            String line = null;
            String[] good;
            while (( line = input.readLine()) != null)
            {
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

    public int findEquipmentInShop(SportEquipment equipment) {
        return goods.get(equipment);
    }

    public void arrivalOfEquipment(SportEquipment equipment, int amount) {
        int number = goods.get(equipment);
        goods.put(equipment, number + amount);
    }

    public void rentEquipment(SportEquipment equipment, String name) {
        int amount  = findEquipmentInShop(equipment);
    	if (amount > 0) {
        	RentUnit unit = rents.get(name);
            if(unit != null) {
                if(unit.addEquipment(equipment)) {
                    rents.put(name, unit);
                    goods.put(equipment, amount - 1);
                }
            } else {
                unit = new RentUnit();
                if(unit.addEquipment(equipment)) {
                    rents.put(name, unit);
                    goods.put(equipment, amount - 1);
                }
            }
        }
    	

    }
    
    public void returnEquipment(SportEquipment equipment, String name) {
    	RentUnit unit = rents.get(name);
    	if(unit != null) {
    		if(unit.removeEquipment(equipment)) {
    			if(unit.getUnits().size() != 0 ) {
    				rents.put(name, unit);
    			} else {
    				rents.remove(name);
    			}
    			arrivalOfEquipment(equipment, 1);
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
                output.write(equipment.getCategory() + " " + equipment.getTitle() + " " + equipment.getPrice() + " " + entry.getValue() + "\n");
            }
            output.close();
        } catch (IOException e) {
        	System.out.println("Error in writing Goodsfile");
        }

    }
    
    public void saveRentsInFile(String fileName) {
        try {
            Writer output = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry entry : rents.entrySet()) {

                String name = (String)entry.getKey();
                RentUnit rentUnit = (RentUnit)entry.getValue();
                int count = rentUnit.getUnits().size();
                String line = count + " " + name + " ";
                for(SportEquipment equipment : rentUnit.getUnits())
                {
                	line += equipment.getCategory() + " " + equipment.getTitle() + " " + equipment.getPrice() + " ";
                }
                line += "\n";
                output.write(line);
                
            }
            output.close();
        } catch (IOException e) {
        	System.out.println("Error in writing  Rentsfile");
        }

    }
    

}
