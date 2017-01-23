import java.util.ArrayList;

public class RentUnit {

    private ArrayList<SportEquipment> units = new ArrayList<SportEquipment>();

    public boolean addEquipment (SportEquipment equipment) {
        if (units.size() < 3)
        {
            units.add(equipment);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeEquipment(SportEquipment equipment) {
        return units.remove(equipment);
    }
    
    public ArrayList<SportEquipment> getUnits() {
        return units;
    }

}