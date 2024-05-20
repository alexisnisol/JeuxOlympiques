package modele;

import java.util.Comparator;

public class ComparePerformance implements Comparator<Participant> {
    
    @Override
    public int compare(Participant p1, Participant p2) {
        return Float.compare(p2.getPerformance(), p1.getPerformance());
    }
}
