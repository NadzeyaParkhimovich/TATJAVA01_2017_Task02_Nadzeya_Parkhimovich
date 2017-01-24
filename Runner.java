import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        String shopFileName = "D:/Eclipse_Projects/epam/Task2/shopFile.txt";
        String rentFileName = "D:/Eclipse_Projects/epam/Task2/rentFile.txt";
        
        String outShopFileName = "D:/Eclipse_Projects/epam/Task2/outShopFile.txt";
        String outRentFileName = "D:/Eclipse_Projects/epam/Task2/outRentFile.txt";
        
        Shop shop = new Shop(shopFileName, rentFileName);
        
        int command;

        do {
        	System.out.println("Shop menu:");
            System.out.println("1 - Report - All goods in shop;");
            System.out.println("2 - Report - Rent goods;");
            System.out.println("3 - To find a good;");
            System.out.println("4 - To rent a good;");
            System.out.println("5 - To return a good;");
            System.out.println("6 - The arrival of a good;");
            System.out.println("7 - Save goods in file;");
            System.out.println("8 - Save rents in file;");
            System.out.println("9 - Exit.");
            
            Scanner sc = new Scanner(System.in);
            command = 0;
            try {
            	 command = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
            	 System.out.println("Please write appropriate number of menu.");
            }
            
            switch (command)
            {
            	case 1:
            		shop.printShopReport();
            		break;
            	case 2:
            		shop.printRentReport();
            		break;
            	case 3:
            		System.out.print("Category:");
            		String category = sc.next();
            		System.out.print("Title:");
            		String title = sc.next();
            		System.out.print("Price:");
            		int price;
            		try {
            			price = sc.nextInt();
            		} catch (java.util.InputMismatchException e) {
                   	 	System.out.println("Please write price as a whole number.");
                   	 	break;
                    }
            		SportEquipment equipment = new SportEquipment(category, title, price);
            		System.out.println("The amount of equipment is: " + shop.findEquipmentInShop(equipment));
            		break;
            	case 4:
            		System.out.print("Name of the buyer:");
            		String name = sc.next();
            		System.out.print("Category:");
            		category = sc.next();
            		System.out.print("Title:");
            		title = sc.next();
            		System.out.print("Price:");
            		try {
            			price = sc.nextInt();
            		} catch (java.util.InputMismatchException e) {
                   	 	System.out.println("Please write price as a whole number.");
                   	 	break;
                    }
            		equipment = new SportEquipment(category, title, price);
            		System.out.println("The rent is " + (shop.rentEquipment(equipment, name) ? "ok" : "failed"));
            		break;
            	case 5:
            		System.out.print("Name of the buyer:");
            		name = sc.next();
            		System.out.print("Category:");
            		category = sc.next();
            		System.out.print("Title:");
            		title = sc.next();
            		System.out.print("Price:");
            		try {
            			price = sc.nextInt();
            		} catch (java.util.InputMismatchException e) {
                   	 	System.out.println("Please write price as a whole number.");
                   	 	break;
                    }
            		equipment = new SportEquipment(category, title, price);
            		System.out.println("The return is " + (shop.returnEquipment(equipment, name) ? "ok" : "failed"));
            		break;
            	case 6:
            		System.out.print("Category:");
            		category = sc.next();
            		System.out.print("Title:");
            		title = sc.next();
            		System.out.print("Price:");
            		try {
            			price = sc.nextInt();
            		} catch (java.util.InputMismatchException e) {
                   	 	System.out.println("Please write price as a whole number.");
                   	 	break;
                    }
            		System.out.print("Amount:");
            		int amount;
            		try {
            			amount = sc.nextInt();
            		} catch (java.util.InputMismatchException e) {
                   	 	System.out.println("Please write amount as a whole number.");
                   	 	break;
                    }
            		equipment = new SportEquipment(category, title, price);
            		shop.arrivalOfEquipment(equipment, amount);
            		System.out.println("The arrival of equipment is done");
            		break;
            	case 7:
            		shop.saveGoodsInFile(outShopFileName);
            		break;
            	case 8:
            		shop.saveRentsInFile(outRentFileName);
            		
            }
        } while (command != 9);
        
    }


}
