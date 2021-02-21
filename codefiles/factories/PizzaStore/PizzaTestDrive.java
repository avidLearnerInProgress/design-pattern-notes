package PizzaStore;

public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Bob ordered a " + pizza.getName() + "\n");
        
        pizza = chicagoStore.orderPizza("pepperoni");
        System.out.println("Alice ordered a " + pizza.getName() + "\n");
    }
}
