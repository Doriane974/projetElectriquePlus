package org.m1.electriquePlus;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * La classe ListesNumeriques permet d'ajouter 2 entiers (positifs ou négatifs) représentés en
 * utilisant des listes de chiffres. On ne passera pas par un type <code>Long</code> ou 
 * <code>BigInteger</code>.
 *
 */
public class Client {

	/**
	 * Removes leading zeros from a list of digits.
	 *
	 * @param number List representing the number.
	 */
	private void removeLeadingZeros(List<Integer> number) {

		while (number != null && !number.isEmpty() && number.get(0) == 0){
			number.remove(0);
		}

	}

	/**
	 * Validates the digits in the list.
	 *
	 * @param number List representing the number.
	 * @throws IllegalArgumentException if any digit is not within the range [-9, 9].
	 */
	private void validateDigits(List<Integer> number) {
		// Check the sign digit
		if (number.get(0) > 9 || number.get(0) < -9) {
			throw new IllegalArgumentException("Invalid digit content");
		}

		// Check the remaining digits
		for (int i = 1; i < number.size(); i++) {
			int digit = number.get(i);
			if (digit < 0 || digit > 9) {
				throw new IllegalArgumentException("Invalid digit content");
			}
		}
	}


	/**
	 * Do the subtract of two lists, one negative and the other positive
	 *
	 * @param nb1 List representing the first number.
	 * @param nb2 List representing the second number.
	 * @return a list composed of number between 0 and 9 (except the first one that can be negative)
	 */
	public List<Integer> soustraction(List<Integer> nb1, List<Integer> nb2) {
		List<Integer> resultat = new ArrayList<>(); // le resultat de la soustraction sera stocké ici

		//on place la plus grande liste dans le premier tableau
		Integer signe;// = 1;//stocke le signe du resultat

		if (nb2.size() > nb1.size()) {//si nb2 represente un nombre plus grand que ce que represente nb1, on échange les deux listes
			List<Integer> temp;// = new ArrayList<>();
			//on swapp : nb2 est plus grand
			temp = nb2;
			nb2 = nb1;
			nb1 = temp;
		} else if (nb2.size() == nb1.size()) {
			int i = 0;
			while (abs(nb2.get(i)) == abs(nb1.get(i))) { //on parcours les deux listes jusqu'a trouver un nombre différent,
				//Dans le but de savoir qui est le plus grand
				i++;
				if (i == nb2.size()) { //si il n'y a aucun chiffre différents
					resultat.add(0);
					return resultat; //on return directement 0
				}
			}
			if (abs(nb2.get(i)) > abs(nb1.get(i))) { //on determine qui est le plus grand, pour connaitre le signe final
				//on swapp -> nb2 est plus grand
				List<Integer> temp;
				temp = nb2;
				nb2 = nb1;
				nb1 = temp;
			}
		}
		signe = nb1.get(0) / abs(nb1.get(0));//la le signe ne prend pas la valeur -1 pour -821 et 111

		//Le nombre qui l'emporte est le plus éloigné de 0.
		//Il donnera son signe au resultat
		//on a échanger de place les nb, mais on ne met pas à jour les entierNb 1 et 2, donc forcément ça prend me mauvais signe !

		//maintenant qu'on connaît le signe du résultat,
		//on met les 2 tableaux en positif, pour simplifier les calculs

		nb1.set(0, abs(nb1.get(0)));
		nb2.set(0, abs(nb2.get(0)));


		Integer indice1 = nb1.size();//on parcourera les listes par la fin
		Integer indice2 = nb2.size();

		int retenue = 0;

		while (indice1 > 0 && indice2 > 0) { //tant qu'aucun des deux tableaux n'ont été parcourus en entier
			indice1--;
			indice2--;
			//si il y a une retenue dut à un précédent calcul,
			//on l'ajoute a la case que l'on regarde dans nb2
			Integer val = nb2.get(indice2) - nb1.get(indice1) + retenue;
			retenue = 0 < val ? 1 : 0;
			resultat.add(0, 10 * retenue - val);
		}
		//on a parcouru en entier nb2, il reste des cases dans nb1
		while (indice1 > 0) {
			//il faut gerer la retenue ici aussi.
			indice1--;
			if (retenue == 0) resultat.add(0, nb1.get(indice1));
			else if (nb1.get(indice1) == 0) resultat.add(0, 9);
			else {
				resultat.add(0, nb1.get(indice1) - 1);
				retenue = 0;
			}
		}

		//la seule chose qu'il reste a faire est d'eventuellement gerer la retenue qu'il reste
		if (retenue == 1) resultat.add(0, 1);

		//il faut enlever les 0 ici
		removeLeadingZeros(resultat);
		resultat.set(0, resultat.get(0) * signe);
		return resultat;

	}



	/**
	 * Do the addition of two lists, only with positives numbers
	 *
	 * @param nb1 List representing the first number.
	 * @param nb2 List representing the second number.
	 * @return a list composed of number between 0 and 9 (except the first that is not 0)
	 **/
	public List<Integer> ajoutePositif(List<Integer> nb1, List<Integer> nb2) {
		List<Integer> resultat = new ArrayList<>();
		Integer indice1 = nb1.size();
		Integer indice2 = nb2.size();
		Integer retenue = 0;

		while (indice1 > 0 || indice2 > 0 || retenue > 0) { //on est dans cette boucle tant que l'on a parcouru aucun des deux tableaux en entier
			Integer num1 = indice1-- > 0 ? nb1.get(indice1) : 0; // on décrémente l'indice, et
			Integer num2 = indice2-- > 0 ? nb2.get(indice2) : 0;
			Integer sum = num1 + num2 + retenue;
			retenue = sum >= 10 ? 1 : 0;
			resultat.add(0, sum-10*retenue);
		}
		while (resultat.get(0) == 0)
			resultat.remove(0);
		return resultat;
	}

	/**
	 * <p>La méthode considère 2 entiers qui sont représentés en utilisant des listes de
	 * chiffres, respectivement <code>nb1</code> et <code>nb2</code>. Cela permet de pouvoir
	 * manipuler des très grands nombres sans utiliser un type borné tel que <code>Long</code>
	 * par exemple.
	 * La méthode ajoute ces 2 nombres et renvoie le résultat comme une liste de chiffres.
	 * </p>
	 * <p>Par exemple, si on veut ajouter les nombres 142 et 13, on doit créer une liste
	 * (nb1) avec trois éléments [1,4,2] et une liste (nb2) avec deux éléments [1,3].
	 * Comme 142+13 = 155, le programme doit donc produire une liste avec trois éléments [1,5,5]
	 * </p>
	 * [1,4,2] + [1,3] = [1,5,5]
	 * <p>
	 * Chaque élément des listes nb1 et nb2 doit être compris entre 0 et 9, excepté le premier
	 * élément de chaque liste qui peut être compris entre -9 et 0 pour pouvoir représenter un nombre 
	 * négatif.
	 * Une exception <code>IllegalArgumentException</code> est levée si cette condition préalable n'est
	 * pas remplie.
	 * </p>
	 *
	 * @param nb1  liste qui contient le premier entier. Si <code>nb1</code> est une liste vide, cela
	 * correspond à 0. Si <code>nb1</code> est <code>null</code>, le résultat est une liste vide
	 * @param nb2  liste qui contient le deuxième entier. Si <code>nb2</code> est une liste vide, cela
	 * correspond à 0. Si <code>nb2</code> est <code>null</code>, le résultat est une liste vide
	 * @return la somme de <code>nb1</code> et <code>nb2</code> représentée comme une liste de chiffres
	 */
	public List<Integer> ajoute(List<Integer> nb1, List<Integer> nb2) {
		//Tests de contenus
		if((nb1 == null) || (nb2==null)){
			return new ArrayList<Integer>();
		}

		removeLeadingZeros(nb1);
		removeLeadingZeros(nb2);

		if(nb1.isEmpty() && nb2.isEmpty()){
			nb1.add(0);
			return nb1;
		}

		if(nb1.isEmpty()) {
			return nb2;
		}
		if(nb2.isEmpty()){
			return nb1;
		}

		validateDigits(nb1);
		validateDigits(nb2);

		List<Integer> resultat;// = new ArrayList<>();

		//on s'occuper pour l'instant de l'addition de deux nombres positifs
		if(nb1.get(0)>0 && nb2.get(0)>0) {
			resultat = ajoutePositif(nb1, nb2);
		}
		else {
			if (nb1.get(0) < 0 && nb2.get(0) < 0) {//on s'occupe de l'addition de deux nombres négatifs
				nb1.set(0, abs(nb1.get(0)));
				nb2.set(0, abs(nb2.get(0)));
				resultat = ajoutePositif(nb1, nb2);
				resultat.set(0, resultat.get(0) * (-1));
			} else {//on s'occupe de l'addition d'un nombre negatif et d'un nombre positif
				resultat = soustraction(nb1, nb2);
			}
		}
		return resultat;
	}

}