# SAE JeuxOlympiques

### groupe 1B
DANTEC Malo (Non participation)

FAMILIAR Enzo  

MULIKIHAAMEA Leone (Non participation)

NISOL Alexis  

### Compilation
javac -d ./bin ./src/*.java

### Exécution
java -cp ./bin Executable

### Faire les tests
javac -cp lib/junit-4.13.2.jar:. Tests.java
java -cp lib/junit-4.13.2.jar:lib/hamcrest-2.2.jar:. org.junit.runner.JUnitCore Tests


(note : Diagramme de classes : parfait ; diagramme de séquence : correct mais genererResultat ne provient pas d'athlete mais plutôt de Competition -> fermer la flèche après athlete, et faire la suite en partant de compétition)
