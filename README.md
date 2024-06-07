# Système de Gestion de Parc de Rechargement Électrique

Ce projet est un système de gestion de parc de rechargement électrique pour les véhicules électriques. Il offre des fonctionnalités pour gérer les bornes de recharge, les clients, les véhicules, les réservations, etc.

## Utilisation 

Lors de l'execution de la classe Main, nous pouvons choisir si nous sommes le gestionnaire du parc ou un client.
Pour ouvrir la partie du gestionnaire nous devons nous connecter en tant que gestionnaire.
Le nom de famille a entré est Dupont et le prenom est Fred.
Le gestionnaire peux voir la liste des clients.

  ![Exemple d'utilisation de l'application](https://github.com/Doriane974/projetElectriquePlus/blob/master/UtilisationAppli.png?raw=true)

Si le gestionnaire n'est pas reconnu alors l'utilisateur retourne au menu principal.
Un visiteur peux faire un compte et inscrire ou non sa voiture lors de l'inscription.
Pour ajouter un vehicule ou faire une reservation, le client est inviter à se connecter.

## Contenu du projet

Le package **org.m1.electriquePlus** contient toutes les classes principales du système.

#### Classes principales :

- **Borne** : Représente une borne de recharge électrique. Permet de vérifier la disponibilité de la borne à un certain moment.
- **Client** : Représente un client du parc de rechargement électrique. Stocke les informations du client et son véhicule.
- **FileManagement** : Gère la lecture et l'écriture des fichiers pour stocker les données du système.
- **Parc** : Représente le parc de rechargement électrique dans son ensemble. Stocke les bornes, les clients, les adresses, les immatriculations, les véhicules et les réservations.
- **Vehicule** : Représente un véhicule électrique. Stocke les informations du véhicule telles que l'immatriculation, la marque, le modèle et l'année de fabrication.
- **Adresse** : Représente une adresse physique. Utilisée pour stocker l'adresse du client.
- **Immatriculation** : Représente une plaque d'immatriculation. Utilisée pour stocker l'immatriculation du véhicule.
- **Reservation** : Représente une réservation de borne de recharge électrique. Stocke les informations de la réservation.

<details>
  <summary>Diagramme de classes</summary> 
  
  <img src="https://github.com/Doriane974/projetElectriquePlus/blob/master/DiagrammeDeClasse.png?raw=true" alt="Diagramme de classes">
</details>


## Fonctionnalités

Le système de gestion de parc de rechargement électrique offre les fonctionnalités suivantes :

1. **Gestion des bornes** :
   - Ajouter une nouvelle borne.
   - Vérifier la disponibilité d'une borne à un certain moment.
   - Modifier le statut d'une borne.

2. **Gestion des clients** :
   - Ajouter un nouveau client.
   - Enregistrer les informations du client dans un fichier.
   - Charger les informations du client à partir d'un fichier.

3. **Gestion des véhicules** :
   - Ajouter un nouveau véhicule.
   - Enregistrer les informations du véhicule dans un fichier.
   - Charger les informations du véhicule à partir d'un fichier.
   - 
4. **Gestion des réservations** :
   - Effectuer une réservation de borne de recharge pour un client.

5. **Sauvegarde et chargement des données** :
   - Sauvegarder les informations des clients, des véhicules et des bornes dans des fichiers.
   - Charger les informations à partir des fichiers lors du démarrage du système.
  

  ## Tâches 

  ### User-Stories

  Nous avons organiser le developpement de ce projet grâce à des user storys, qui nous guident dans l'implémentations de fonctionnalité. 
Nous avons 4 catégories d'User stories, chaque catégorie est illustrée par une couleur différente sur le trello du projet. 

* Facturation (gris)
* Base de données (vert)
* Utilisation des bornes (rose)
* Gestion du parc (rouge)


 ### Sprints
 
 Nous avons pu réaliser 2 sprints pour l'instant, chacun d'une durée d'une semaine et demis environ.

  ![Burndown Chart](https://github.com/Doriane974/projetElectriquePlus/blob/master/Burndown.png?raw=true)
  

  ## Licence

  Le projet est sous license MIT, il est donc possible d'utiliser, de modifier et de distribuer ce code.
  



