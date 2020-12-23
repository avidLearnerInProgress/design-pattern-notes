## Design patterns notes 

1. Intro to design patterns 

* Localised update to code causes non-local side effects. (Adding fly feature to duck superclass causes all subclasses to inherit the fly method from baseclass and perform the fly action)
* One way to avoid these non-local side effects is to create interfaces for actions that are not going to be used in every sub-class(example Fly[all ducks are not supposed to fly]). And only the classes that need those interfaces can implement them.
* However, having interfaces solves the problem but it does introduce lots of code maintainence issues. Assume you have a single interface that needs a restructure and if this interface is being implemented by more than 100 classes; everyone needs a restructure.
* Finding a way to build software so that when there are incremental changes we can do the change without impacting existing codebase. No matter how well you design an application, over time an application must grow and change or it will die. 
* To prevent using interfaces and impacting an existing codebase; we can use the design principle - **Identify aspects of application that vary and separate them from what stays the same**. In other words - **Take what varies and “encapsulate” it so it won’t affect the rest of your code**
* Here’s another way to think about this principle: take the parts that vary and encapsulate them, so that later you can alter or extend the parts that vary without affecting those that don’t.
* In the duck example we can figure out that the duck class works well and it doesnt change frequently. The parts that change are **duck** and **quack**. So, we create two separate classes for each respectively.
* Each set of classes will hold all implementations of respective behavior.



  
