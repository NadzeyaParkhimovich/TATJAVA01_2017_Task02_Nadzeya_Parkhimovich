
public class Runner {

    public static void main(String[] args) {

        String shopFileName = "D:/Eclipse_Projects/epam/Task2/shopFile.txt";
        String rentFileName = "D:/Eclipse_Projects/epam/Task2/rentFile.txt";
        
        Shop shop = new Shop(shopFileName, rentFileName);

        shop.printShopReport();
        shop.printRentReport();


    }


}
