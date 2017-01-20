package com.epam.task2;

import java.util.ArrayList;

public class RentUnit {

    private ArrayList<SportEquipment> units;

    public boolean addEquipment (SportEquipment equipment) {
        if (units.size() < 3)
        {
            units.add(equipment);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<SportEquipment> getUnits() {
        return units;
    }

}
