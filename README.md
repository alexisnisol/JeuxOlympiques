# SAE JeuxOlympiques

### groupe 1B
DANTEC Malo  
FAMILIAR Enzo  
MULIKIHAAMEA Leone  
NISOL Alexis  

### Compilation
javac -d ./bin ./src/*.java

### Exécution
java -cp ./bin Executable

### Faire les tests
javac -cp lib/junit-4.13.2.jar:. Tests.java
java -cp lib/junit-4.13.2.jar:lib/hamcrest-2.2.jar:. org.junit.runner.JUnitCore Tests
