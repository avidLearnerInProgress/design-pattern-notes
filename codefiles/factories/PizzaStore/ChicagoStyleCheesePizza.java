package PizzaStore;

public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Plum Tomato Sauce";
        
        toppings.add("Shredded Mozzarella Cheese");
        toppings.add("Cherry Tomatoes");
        toppings.add("Crispy Capsicum");
    }
    
    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
