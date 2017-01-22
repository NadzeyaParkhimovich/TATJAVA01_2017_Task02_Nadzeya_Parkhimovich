

public class SportEquipment {

    private String category;
    private String title;
    private int price;

    public SportEquipment(String category, String title, int price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }


}
