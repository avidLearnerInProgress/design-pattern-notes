## Baking with OO Goodness (Factory Pattern)

* When we use `new` keyword, we are instantiating a concrete class so this is definitely an implementation, not an interface. There is nothing wrong with the keyword `new`. The real culprit is change and how change impacts the use of `new`.
* If we have a set of concrete classes, we are forced to design the logic of our application in the following manner - 

    ```
    Duck duck;
    if (picnic) {
        duck = new MallardDuck();
    } else if (hunting) {       
        duck = new DecoyDuck();
    } else if (inBathTub) {
        duck = new RubberDuck();
    }   
    ```
* The code above is more fragile, error-prone, and not flexible. Also it clearly shows that when it comes time for extension or changes, we will have to revisit this code and examine what needs to be added / deleted.
* By coding to interface, we can insulate ourselves from changes that happen to the system. If the code is written to an interface, it will work with any new classes implementing those interfaces via polymorphism.
* When we have concrete classes, the code is not "closed for modification" which violates the open close principle.
* For the pizza shop, we have an orderPizza method that looks like this - 
    ```
    //based on the type of pizza, we instantiate correct concrete class

    Pizza orderPizza(String type) {
        Pizza pizza;
        if (type.equals(“cheese”)) {
            pizza = new CheesePizza();
        } else if (type.equals(“greek”) {
            pizza = new GreekPizza();
        } else if (type.equals(“pepperoni”) {
            pizza = new PepperoniPizza();
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    ```
* If you need to add more pizza types like a clam pizza or a veggie pizza, you need to modify the above function. Also incase if you want to delete a pizza, you have to modify it.
* Clearly, dealing with which concrete class is instantiated is really messing up our orderPizza() method and preventing it from being closed for modification. We can figure out that the object creationg code is somethign that is varying. This part needs to be encapsulated.
* **The pizza object creation code can be moved into another object that is only responsible / concerned with creating pizzas. This object is called Factory.**
* Creating a SimplePizzaFactory results in following changes -
    ```
    public class SimplePizzaFactory {
        public Pizza createPizza(String type) {
            Pizza pizza = null;
            if (type.equals(“cheese”)) {
                pizza = new CheesePizza();
            } else if (type.equals(“pepperoni”)) {
                pizza = new PepperoniPizza();
            } else if (type.equals(“clam”)) {
                pizza = new ClamPizza();
            } else if (type.equals(“veggie”)) {
                pizza = new VeggiePizza();
            }
            return pizza;
        }
    }

    //client code
    public class PizzaStore {
        
        SimplePizzaFactory factory;
        
        //using a constructor to store the reference of SimplePizzaFactory object.
        public PizzaStore(SimplePizzaFactory factory) {
            this.factory = factory;
        }
        public Pizza orderPizza(String type) {
            Pizza pizza;

            //delegating concrete object creation responsibility to another function
            pizza = factory.createPizza(type); // New is replaced with a createPizza()
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    // other methods here
    }
    ```