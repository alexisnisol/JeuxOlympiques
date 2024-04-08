public class Athlètes implements Participant{

    private String nom, prenom, Sexe,medailles;
    private int force, endurance, agilite,totalMedailles;
    private Pays Pays;
    private Equipes Equipe;


    public Athlètes(String nom, String prenom, String Sexe,int force, int endurance, int agilite, String medailles, int totalMedailles, Pays Pays, Equipes Equipe){
        this.nom = nom;
        this.prenom = prenom;
        this.totalMedailles = totalMedailles;
        this.agilite = agilite;
        this.endurance = endurance; 
        this.Sexe = Sexe;
        this.force = force;
        this.medailles = medailles;
        this.Pays = Pays;
        this.Equipe = Equipe;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return Sexe;
    }

    public String getMedailles() {
        return medailles;
    }

    public int getForce() {
        return force;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getAgilite() {
        return agilite;
    }

    public int getTotalMedailles() {
        return totalMedailles;
    }

    @Override
    public void participer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'participer'");
    }

    @Override
    public String obtenirNom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenirNom'");
    }
    
}