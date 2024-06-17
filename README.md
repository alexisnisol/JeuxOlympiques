# SAE JeuxOlympiques

### groupe 1B

NISOL Alexis  
FAMILIAR Enzo


## Génération de la javadoc
javadoc -d doc -charset utf8 -noqualifier all --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml src/*.java

### Compilation
javac -cp ./src/ -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml src/*.java

### Exécution
java -cp .:bin:img --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml Executable

### Faire les tests
javac -cp lib/junit-4.13.2.jar:. Tests.java
java -cp lib/junit-4.13.2.jar:lib/hamcrest-2.2.jar:. org.junit.runner.JUnitCore Tests
