## Design patterns notes 

1. Intro to design patterns 

* Localised update to code causes non-local side effects. (Adding fly feature to duck superclass causes all subclasses to inherit the fly method from baseclass and perform the fly action)
* One way to avoid these non-local side effects is to create interfaces for actions that are not going to be used in every sub-class(example Fly[all ducks are not supposed to fly]). And only the classes that need those interfaces can implement 
