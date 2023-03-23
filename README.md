# CalorieTracker

App developed to display the implementation of a mutimodular arquitecture, following the 3 modularization principles: 
* Cohesion 
* Coupling
* Granularity

## Advantages of Modularizacion:
1. **Faster Gradle building time:**
    1. Modules can build in parallel
    2. Only the modules in which there's code that changes are going to be rebuilt, and also the modules with depependencies.
2. **Better team work delegation:** Each team can work on a feature module and a team is not going to change code from other teams or be affected by a change from other teams.
3. **Better separation of concerns:** Modules don’t now each other, one module will not be able to access code from another module unless specified.
4. **Reusability:** Each module is an independent library that can be used in other projects.

## Modularization strategy used:
* By layer ❌
* By feature ❌
* Hybrid  ✅

## Screenshots:
<img width="337" alt="image" src="https://user-images.githubusercontent.com/46971682/227291177-dec2c50d-f9b8-47e4-9f37-02924d1fdd28.png">
<img width="335" alt="image" src="https://user-images.githubusercontent.com/46971682/227291306-31832cc3-8194-4b1e-8180-2801273f2c44.png">
