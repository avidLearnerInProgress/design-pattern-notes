## Design patterns notes 

### 1. Intro to design patterns (Strategy Pattern)

* Localised update to code causes non-local side effects. (Adding fly feature to duck superclass causes all subclasses to inherit the fly method from baseclass and perform the fly action)
* One way to avoid these non-local side effects is to create interfaces for actions that are not going to be used in every sub-class(example Fly[all ducks are not supposed to fly]). And only the classes that need those interfaces can implement them.
* However, having interfaces solves the problem but it does introduce lots of code maintainence issues. Assume you have a single interface that needs a restructure and if this interface is being implemented by more than 100 classes; everyone needs a restructure.
* Finding a way to build software so that when there are incremental changes we can do the change without impacting existing codebase. No matter how well you design an application, over time an application must grow and change or it will die. 
* To prevent using interfaces and impacting an existing codebase; we can use the design principle - **Identify aspects of application that vary and separate them from what stays the same**. In other words - **Take what varies and “encapsulate” it so it won’t affect the rest of your code**
* Here’s another way to think about this principle: take the parts that vary and encapsulate them, so that later you can alter or extend the parts that vary without affecting those that don’t.
* In the duck example we can figure out that the duck class works well and it doesnt change frequently. The parts that change are **duck** and **quack**. So, we create two separate classes for each respectively. Each set of classes will hold all implementations of respective behavior.
* To separate out the changing flying and quacking behaviors we use the second design principle - **Program to an interface, not an implementation**
* Program to implementation - 
  * `Dog d = new Dog(); d.bark();` - where `Dog` and `Cat` are subclasses of superclass `Animal`
* Program to interface - 
  * `Animal a = new Dog(); a.makeSound();` -  where `Dog` and `Cat` are subclasses of superclass `Animal` 
* In the above examples, when we program to interface, we know its a dog but we can use the animal reference polymorphically. However, in case of program to implementation, declaring the reference as Dog of type D forces us to code a concrete implementation.
* We’ll use an interface to represent each behavior—for instance, FlyBehavior and QuackBehavior—and each implementation of a behavior will implement one of those interfaces.  Duck classes that will implement the flying and quacking interfaces. Instead, we’ll make a set of classes whose entire reason for living is to represent a behavior (for example, “squeaking”), and it’s the behavior class,rather than the Duck class, that will implement the behavior interface. 
* **“Program to an interface” really means “Program to a supertype.”** The word interface is overloaded. This sentence simply tells to exploit polymorphism by programming to supertype so that the actual runtime object isn't locked during execution.
* Program to supertype can be repharsed as - **the declared type of the variables should be a supertype, usually an abstract class or interface, so that the objects assigned to those variables can be of any concrete implementation of the supertype, which means the class declaring them doesn’t have to know about the actual object types!**
* Using abstract class as supertype is not a good practice as it will lead to problems in inheritance chain. If two child classes have to talk to each other, we require multiple inheritance which is bad. And inheritance in general is bad. 
* Delegating the flying and quacking behavior from Duck to the respective Behavior interfaces.
  1. Add instance variables of respective behavior interfaces in the Duck class
  2. Add functions that allow the Duck to `performFly()` and `performQuack()`
* We can set the dynamic duck behavior type by using a setter method in the Duck class rather than instantiating it in duck constructor.
* The HAS-A relationship is an interesting one: each duck has a FlyBehavior and a QuackBehavior to which it delegates flying and quacking.  When two classes are used together like this it indicates that we are using composition. Instead of inheriting their behavior, the ducks get their behavior by being composed with the right behavior object. This is an important technique; in fact, it is the basis of design principle: **favor composition over inheritance**.
* Not only does it encapsulate a family of algorithms into their own set of classes, but it also changes behavior at runtime as long as the object that are being composed with implements the correct behavior interface. 
* **The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that**
* A very simple strategy pattern example can be like you have a family of sorting and searching algorithms. Different data structures like - lists, sets, dictionaries will use these families of sorting and searching behavior on the basis of which suits them the best.
* **Summary:**
  1. Identify aspects of application that vary and separate them from what stays the same. 
  2. Program to interface, not an implementation.
  3. Favor composition over inheritance.
  4. Strategy pattern - family of algorithms, encapsulating each one and making them interchangeable. 

* **Code:**
  * [Duck simulator](codefiles/strategy/SimuDuck)

* **Class Diagram** - 
  * ![Strategy Pattern](./assets/StrategyPattern.png)

----
### 2. Keeping your objects in the know (Observer Pattern)

* The task is to build a Internet Based Weather Monitoring Station (Weather-O-Rama). Three key components in the System are `Weather Station`, `WeatherData Object` and `Display Device`.
* WeatherData class has getters(getTemperature(), getHumidity(), getPressure()) for 3 measurement values respectively. When we have a new measurement data, measurementsChanged() gets executed. 
* **Publishers(Subjects) + Subscribers(Observers) = Observer Pattern**
* Subject object manages some bit of data. If the data in the subject changes, the observers or subscribers to the data get notified.
* Newspaper subscription service is a good exaple of Observer Pattern
* **The observer pattern defines a one-to-many dependency(relationship) between objects so that when and if one object changes state, all its dependents are notified and updated automatically.**
* **Loose Coupling** - If two objects are loosely coupled they can intersect but they have very little knowledge about each other.
* Observer pattern adheres to the concept of loose coupling. How?
  1. The only thing subject knows about observer is that it implemnents certain interface.
  2. We can add new observers anytime.
  3. No need to modify subject while adding new observers.
  4. We can reuse subjects / observers independently of each other.
  5. Changes to either of subject / observer will not affect each other. 
* **Strive for loosely coupled designs between objects that interact.**
* *Loosely coupled designs allow us to build flexible systems that can handle change because they minimize interdependency between subjects.*
* We can also implement the above pattern using Java's in-built Observer pattern API. In this new implementation, the Subject(WeatherData) extends the Observable Class(instead of implementing an interface) and inherits the add, delete and notify Observer methods.
* To incorporate using java's builtin API changes - 
  * For object to become observer, call `addObserver()` on any observable object. Similarly to remove observer, call `removeObserver()`.
  * For observable to send notifications, extend java's Observable superclass which makes you observable. Call `setChanged()` to signify that the state has changed in the object. Call either `notifyObservers()` or `notifyObservers(Object obj)`
  * For an observer to recieve notifications, implement the update method with args as Observable Subject(the subject that has changed) and Object(the object that has to receive changed measurements).
  * The changes in measurement have to be propagated to respective observers either by using a pull based or a push based mechanism. In eit her mechanisms, we need to figure out a way if the state of the measurements have changed or not. Thus we use,`setChanged()`, `hasChanged()` and `clearChanged()` methods to support the change functionality.
  * Refer the [class diagram](./assets/ObserverPatternJavaBuiltIn.png) for better understanding on the Observable API design. 

* **Summary: (pending)**
  1. When to use Observer Pattern? => When many objects receive an update if there is a change in one of the subjects.
  2. Strive for loosely coupled designs between objects that interact.
  3. Publishers(Subjects) + Subscribers(Observers) = Observer Pattern.

* **Code:**
  * [Weather application simulator](codefiles/observer/WeatherApplicationSimulator/)
  * [Weather application simulator v2](codefiles/observer/WeatherApplicationSimulatorV2/)

* **Class Diagram** - 
  * ![Observer Pattern](./assets/ObserverPattern.png)
* Weather Station Simulator Class Diagram - 
  * ![Weather Station Simulator](./assets/ObserverPatternScenario.png)
* Weather Station Simulator Class Diagram with Java Observable API -
  * ![Weather Station Simulator API](./assets/ObserverPatternJavaBuiltIn.png)

----
### 3. Decorating Objects (Decorator Pattern)

* Inhertiance relies on the idea of subclassing. But creating too many subclasses leads to class explosion.
* The Starbuzz Coffee example has `Beverage` as the base class with different variants of beverages like `Houseblend`, `Darkroast`, `Decaf` and `Espresso`. These variants are inherited from the base class as child classes. These inherited subclasses implement the `cost` method to return the cost of beverage.
* Lets say if the Starbuzz coffee shop needs to expand their menu items and add several condiments. So now along with different flavours we have different condiments that inherit the base class Beverage for their respective cost and description.
* Class explosion leads to maintenance nightmare. One approach to prevent this is using instance variables and inheritance in superclass to keep track of condiments. But by doing this we are modifying the behavior and the responsibility of the abstract base class `Beverage`.
* Inheritance doesn't always lead to flexible or maintainable design. Inheritance can be achieved at runtime using composition and delegation.
* Subclassing a class is a behavior achieved statically during compile time. Also by doing this, all subclasses must inherit the same behavior. However, we can extend the object's behavior during runtime using composition.
* **Effect of composition on code maintainence - By dynamically composing objects we can add new functionality by writing new code rather than altering exisiting code.**
* **Open Closed Principle: Classes must be must be open for extension, closed for modification.**
* Decorator pattern is based on open closed principle. To implement decorator pattern in the Starbuzz usecase, we take a base-class object like `DarkRoast`. Now we decorate it with additional condiments like `Mocha` and `Whip`. We call the cost method and rely on delegation to add condiment costs over the base `DarkRoast` cost.
* Decorator pattern basically wraps base-class objects with additional features. Decorators have same supertype as the objects they decorate. We can pass around a decorated object in place of original wrapped object.
* Decorators add their own behavior either before or after delegating to the object it decorates to do the rest of job.
* **The Decorator Pattern attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.**
* From the class diagram, we create a subclass `Decorator` of abstract class `Component`. This `Decorator` class acts as HAS-A and IS-A relation to `Component` at the same time. We initialize the Concrete Component subclass and pass it to Concrete Decorator.  We wrap components within decorators.
* When we compose a decorator with a component, we are adding new behavior. We are acquiring new behavior not by inheriting it from a superclass, but by composing objects together. We subclass the base class to get the correct type but not to inherit its behavior. Behavior comes from composition of decorators with the base components and other decorators.
* If we have code that relies on concrete component's type then decorators will break that piece of code. As long as we write code against abstract component type the use of decorators will reamin transparent to the code.
* Real world decorators : Java IO. 
To read a text file we use `FileInputStream` which is the base component to be decorated. We wrap this with `BufferedInputStream` which is a concrete decorator. It adds behavior in 2 ways - It improves performance and also augments interface with a method `readLine()` for reading character based inputs. Now we wrap this with `LineNumberInputStream` as it adds the ability to count line numbers as it reads data.


* **Class Diagram** -
  * ![Decorator Pattern](./assets/DecoratorPattern.png)
  * ![Decorator Pattern Scenario](./assets/DecoratorPatternScenario.png)