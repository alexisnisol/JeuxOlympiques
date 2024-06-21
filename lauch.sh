#!/bin/bash




JAVAFX_LIB="/usr/share/openjfx/lib"
DESKTOP_FILE="./JeuxIUTOlympiques.desktop"
ICON_PATH="./assets/jo_paris.png"
CLASS_PATH=".:bin:assets:/usr/share/java/mariadb-java-client.jar" # Inclure le dossier bin et le jar JSON dans le CLASS_PATH
MAIN_CLASS="vue.accueil.Main" # Assurez-vous que ceci correspond à votre classe principale

# Compiler le code source

javac -cp lib/json-20240303.jar:./src/ --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml -d bin src/*.java

# Exécuter l'application
java --module-path $JAVAFX_LIB --add-modules javafx.controls,javafx.fxml -cp $CLASS_PATH $MAIN_CLASS