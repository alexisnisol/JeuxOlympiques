# Manuel Utilisateur pour l'Application de Simulation des Jeux Olympiques


## Introduction

Cette application permet de simuler des Jeux Olympiques, de gérer les épreuves et les participants, et de visualiser les résultats et classements. Vous pouvez enregistrer les compétitions et les participants manuellement ou via un fichier, simuler les épreuves et obtenir divers classements.


## Prérequis
 - Java installé sur votre machine.


## Lancement de l'Application
1. Compilez les fichiers sources Java en utilisant votre IDE préféré ou en ligne de commande :

```
javac -cp ./src/ -encoding UTF8 -d bin src/*.java
```
2. Exécutez l'application en ligne de commande :
```
java -cp ./bin Executable
````


# Menu Principal

Une fois l'application lancée, vous verrez le menu principal :

````
Menu:
1. Rentrer les épreuves, les participants manuellement ou par lecture de fichier
2. Simuler les épreuves
3. Obtenir les résultats et le classement pour une épreuve donnée
4. Obtenir le palmarès des médailles par pays après chaque journée
5. Obtenir un classement par nombre total de médailles et par nombre total de médailles d’Or
0. Quitter
Veuillez entrer votre choix :
````

## Options du Menu
### 1. Rentrer les épreuves et les participants manuellement ou par lecture de fichier

Vous avez deux options pour enregistrer les épreuves et les participants :

 - ### Manuellement :

   -  Saisissez l'année des Jeux Olympiques.
   -  Indiquez le nombre de participants.
   - Pour chaque participant, entrez le nom, prénom, sexe, pays, épreuve, force, endurance et agilité. 

 - ### Par lecture de fichier :
 
   - Entrez le chemin du fichier CSV contenant les informations des participants.
   - L'application lira le fichier et enregistrera les participants et les épreuves.


### 2. Simuler les épreuves

L'application simule toutes les épreuves enregistrées et affiche les résultats.


### 3. Obtenir les résultats et le classement pour une épreuve donnée

Entrez le nom de l'épreuve pour obtenir les résultats et le classement des participants.

### 4. Obtenir le palmarès des médailles par pays après chaque journée

Affiche le palmarès des médailles pour chaque pays à la fin de chaque journée des compétitions.

### 5. Obtenir un classement par nombre total de médailles et par nombre total de médailles d’Or
Affiche le classement des pays par nombre total de médailles et par nombre de médailles d'or.

### 0. Quitter
Termine l'exécution de l'application.


#  Utilisation Détailée

## Enregistrement des Participants et des Épreuves

### Manuellement :

1. Choisissez "1" dans le menu principal.

2. Saisissez "manuel".

3. Entrez l'année des Jeux Olympiques.

4. Entrez le nombre de participants.

5. Pour chaque participant, entrez :
   - Nom
   - Prénom
   - Sexe (HOMME ou FEMME)
   - Pays
   - Épreuve (précisez si l'épreuve est de l'escrime)
   - Force
   - Endurance
   - Agilité

6. Répétez l'étape 5 pour chaque participant.

### Par lecture de fichier :

1. Choisissez "1" dans le menu principal.

2. Saisissez "fichier".

3. Entrez le chemin du fichier CSV contenant les informations des participants.

4. L'application lira et enregistrera les données du fichier.

### Simulation des Épreuves

1. Choisissez "2" dans le menu principal.

2. L'application simulera toutes les épreuves enregistrées et affichera les résultats.

### Obtenir les Résultats pour une Épreuve Donnée

1. Choisissez "3" dans le menu principal.

2. Entrez le nom de l'épreuve.

3. L'application affichera les résultats et le classement des participants pour cette épreuve.

### Palmarès des Médailles par Pays

1. Choisissez "4" dans le menu principal.

2. L'application affichera le palmarès des médailles pour chaque pays après chaque journée de compétitions.

### Classement par Nombre de Médailles

1. Choisissez "5" dans le menu principal.

2. L'application affichera le classement des pays par nombre total de médailles et par nombre de médailles d'or.

# Conclusion

Cette application offre une interface simple pour gérer et simuler des Jeux Olympiques. En suivant ce manuel, vous pouvez facilement enregistrer des participants, simuler des épreuves et obtenir des résultats et classements détaillés.


