package PizzaStore;
import java.util.ArrayList;

public abstract class Pizza {
    // common attributes for all the pizzas
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();
    
    void prepare() {
        // the abstract class serves the purpose for following preparation steps in a particular sequence.

        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for(int i=0; i<toppings.size(); i++) {
            System.out.println("---" + toppings.get(i));
        }
    }
    
    void bake() {
        System.out.println("Bake for 30 minutes at 450 degrees");
    }
    
    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }
    
    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }
    
    public String getName() {
        return name;
    }
    
}
