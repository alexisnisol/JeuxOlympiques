package modele;
import java.util.Comparator;

import modele.participants.Participant;

/**
 * Cette classe implémente l'interface Comparator pour comparer les performances des participants.
 */
public class ComparePerformance implements Comparator<Participant> {
    
    /**
     * Compare deux participants en fonction de leur performance.
     * 
     * @param p1 Le premier participant à comparer.
     * @param p2 Le deuxième participant à comparer.
     * @return Une valeur négative si la performance de p1 est inférieure à celle de p2,
     *         une valeur positive si la performance de p1 est supérieure à celle de p2,
     *         ou zéro si les performances sont égales.
     */
    @Override
    public int compare(Participant p1, Participant p2) {
        return Float.compare(p2.getPerformance(), p1.getPerformance());
    }
}
