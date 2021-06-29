# ser316-SUM2021-C-aaaltive-DP-
Assignment 5a/b - Design Patterns

**Plans for design Patterns:**

- Singlton: There should be one world.  I will make the world a singlton so that there cannot be multiple worlds at 
  a time.  
  Requirements met:
  - A world with at least 2 trainers who each have at least 1 code-a-mon
  - Trainers can acquire more code-a-mons up to 6
  - Simulations are run on cycles with 2 parts day and night 
  - each part will have its own weather event 

- Factory: I will use a factory pattern to create Code-a-mons.  Since all code-a-mons are of the same family, they can
  be built using a factory, and they can perform their functions based on an interface.
  Requirements met:
  - Weather events should benefit some and disadvantage other code-a-mons
  - Code-a-mons should have different types and have advantages/disadvantages over each other
  - Code-a-mons gain XP from winning battles and can level up after earning enough
  
- Mediator: The interactions during a battle would be complex if everything was allowed to communicate directly, so 
  instead I will use a mediator pattern that will handle all actions as the single point to reduce complexity.
  Requirements met:
  - Code-a-mons compete in 1v1 battles
  - trainers can battle with wild code-a-mons during day for XP
  - Trainers can battle with other trainers at night for XP and money
  - Only 1 battle at a time
  - Purchases can be made from the store
  - Trainers can attempt to get new code-a-mon
    
