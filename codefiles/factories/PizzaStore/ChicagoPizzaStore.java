package PizzaStore;

public class ChicagoPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } 
        else if(type.equals("pepperoni")) {
            return new ChicagoStylePepperoniPizza();
        } 
        else return null;
    }
}
