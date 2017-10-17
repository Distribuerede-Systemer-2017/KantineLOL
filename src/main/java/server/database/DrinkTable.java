package server.database;

import server.models.Drink;

import java.util.*;

public class DrinkTable {

    private ArrayList<Drink> drinks;
    private static DrinkTable instance = null;

    protected DrinkTable() {
        this.drinks = new ArrayList<>();

        Drink smoothie = new Drink();
        smoothie.setId(1);
        smoothie.setPrice(19.95);
        smoothie.setName("smoothie");

        Drink juice = new Drink();
        juice.setId(2);
        juice.setPrice(14.95);
        juice.setName("Juice");

    }

    public static DrinkTable getInstance() {
        if (instance == null) {
            instance = new DrinkTable();
        }
        return instance;
    }

    public void addDrink(Drink drink) {
        this.drinks.add(drink);
    }

    public ArrayList<Drink> getDrinks() {
        return this.drinks;
    }

    public Drink findById(int id) {
        for (Drink drink : this.drinks) {
            if (drink.getId() == id) {
                return drink;
            }
        }
        return null;
    }
}
