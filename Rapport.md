# MorpionDesignPatterns (GERAY & BAGLAN)

## Modélisation UML du projet
</br>

<p align="center">
  <img src="./docs/UMLProject.jpg" />
</p>

## Les Design Patterns utilisés

###  1. Le Singleton
</br>

<p align="center">
  <img src="./docs/UMLSingleton.jpg" />
</p>

Le Singleton est utilisé dans le cas du `ConfigRepository` car le fait qu'il y ait qu'une seule instance de `ConfigRepository` nous assure que c'est uniquement sa propiété `joueurs` qui est utilisée/mise à jour. Également, le fait qu'il n'y ait pas plusieurs instances nous assure que plusieurs instances n'essayeront pas d'accéder au fichier `config.json` en même temps, ce qui pourrait poser problème et amener à des pertes de données.

###  2. L'Observer
</br>

<p align="center">
  <img src="./docs/UMLObserver.jpg" />
</p>

L'Observer est utilisé dans le cas du `ConfigRepository`, pour qu'il observe ou qu'il soit tenu informé de chaque changement de chaque `Joueur`. Ainsi à chaque changement d'un `Joueur`, en l'occurence à chaque `setScoreEnPlus(int score)`, le `ConfigRepository` appelle sa méthode `sauvegarderJoueurs()` laquelle sauvegarde cette dernière modification.

###  3. ...
</br>

<p align="center">
  <img src="./docs/....jpg" />
</p>

...

++ mettre à jour le UMLProject.jpg