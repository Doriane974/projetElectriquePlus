# Système de Gestion de Parc de Rechargement Électrique

Ce projet est un système de gestion de parc de rechargement électrique pour les véhicules électriques. Il offre des fonctionnalités pour gérer les bornes de recharge, les clients, les véhicules, les réservations, etc.

## Installation 

Instructions détaillées sur la manière d'installer et de configurer le système sur différentes plateformes. Incluez les exigences logicielles et matérielles nécessaires.

## Utilisation 

Expliquez comment utiliser votre projet, en fournissant des exemples d'utilisation, des captures d'écran et des instructions sur l'exécution du système.

# Exemples d'utilisation

Des exemples concrets d'utilisation du système avec des captures d'écran ou des commandes. Montrez aux utilisateurs comment ils peuvent interagir avec les fonctionnalités principales de votre projet.

# Configuration avancée

Des informations sur la configuration avancée du système, si nécessaire. Expliquez comment les utilisateurs peuvent personnaliser les paramètres ou les options en fonction de leurs besoins spécifiques

# Dépannage

Des conseils pour résoudre les problèmes courants rencontrés lors de l'utilisation du système. Fournissez des solutions aux erreurs ou aux difficultés que les utilisateurs pourraient rencontrer.

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
  

  ## Contributions

  Des instructions sur la manière de contribuer au projet, y compris la façon de soumettre des bugs, des problèmes ou des demandes de fonctionnalités. Incluez également des directives pour la soumission de pull requests et les normes de codage.

  ## Licence

  Le projet est sous license MIT, il est donc possible d'utiliser, de modifier et de distribuer ce code.
  
  ## Crédits 

  Reconnaissance des personnes ou des organisations ayant contribué au projet. Attribuez également les ressources tierces utilisées dans le projet.

  ## Tâches restantes

  ![Burndown Chart](https://github.com/Doriane974/projetElectriquePlus/blob/master/Burndown.png?raw=true)

