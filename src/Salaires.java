import java.util.ArrayList;

abstract class Employe {

    private String name;
    private String firstName;
    private int age;
    private String joinDate;

    abstract double calculerSalaire();

    @Override
    public String toString() {
        return this.firstName + " " + name;
    }

    String getNom() {
        return "L'employé " + this;
    }

    public Employe(String firstName, String name, int age, String joinDate) {
        this.firstName = firstName;
        this.name = name;
        this.age = age;
        this.joinDate = joinDate;
    }
}

class EmployePayeAuCA extends Employe {
    final private int ca;
    public EmployePayeAuCA (String firstName, String name, int age, String joinDate, int ca) {
        super(firstName, name, age, joinDate);
        this.ca = ca;
    }
    double calculerSalaire() {
        return this.ca * 0.2 + this.getSalaireSupplementaire();
    }

    protected int getSalaireSupplementaire () { return 0; }
}

class Vendeur extends EmployePayeAuCA {
    public Vendeur(String firstName, String name, int age, String joinDate, int ca) {
        super(firstName, name, age, joinDate, ca);
    }
    @Override
    String getNom() {
        return "Le vendeur " + this;
    }

    @Override
    protected int getSalaireSupplementaire () {
        return 400;
    }
}

class Representant extends EmployePayeAuCA {
    public Representant(String firstName, String name, int age, String joinDate, int ca) {
        super(firstName, name, age, joinDate, ca);
    }
    @Override
    String getNom() {
        return "Le représentant " + this;
    }
    @Override
    protected int getSalaireSupplementaire () {
        return 800;
    }
}

class Technicien extends Employe {
    private int unitCount;
    public Technicien(String firstName, String name, int age, String joinDate, int unitCount) {
        super(firstName, name, age, joinDate);
        this.unitCount = unitCount;
    }
    double calculerSalaire () {
        return this.unitCount * 5;
    }
    @Override
    String getNom() {
        return "Le technicien " + this;
    }
}

class Manutentionnaire extends Employe {
    protected int hourCount;
    public Manutentionnaire(String firstName, String name, int age, String joinDate, int hourCount) {
        super(firstName, name, age, joinDate);
        this.hourCount = hourCount;
    }
    double calculerSalaire() {
        return this.hourCount * 65;
    }
    @Override
    String getNom() {
        return "Le manutentionnaire " + this;
    }
}

interface EmployeARisque {
    default double calculerSalaire (double salaireMensuel, int primeMensuelle) {
        return salaireMensuel + primeMensuelle;
    }
}

class TechnARisque extends Technicien implements EmployeARisque {
    public TechnARisque (String firstName, String name, int age, String joinDate, int hourCount) {
        super(firstName, name, age, joinDate, hourCount);
    }
    double calculerSalaire () {
        return EmployeARisque.super.calculerSalaire(super.calculerSalaire(), 400);
    }
}

class ManutARisque extends Manutentionnaire implements EmployeARisque {
    public ManutARisque (String firstName, String name, int age, String joinDate, int hourCount) {
        super(firstName, name, age, joinDate, hourCount);
    }
    double calculerSalaire () {
        return EmployeARisque.super.calculerSalaire(super.calculerSalaire(), 300);
    }
}

class Personnel {

    private ArrayList<Employe> employes = new ArrayList<>();

    public void ajouterEmploye(Employe employe) {
        this.employes.add(employe);
    }

    public void afficherSalaires() {
        for (Employe employe : this.employes) {
            System.out.println(employe.getNom() + " gagne " + employe.calculerSalaire());
        }
    }

    public double salaireMoyen() {
        double sum = 0;
        for (Employe employe: this.employes) {
            sum += employe.calculerSalaire();
        }
        return sum/this.employes.toArray().length;
    }

}

public class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();

        p.ajouterEmploye(new Vendeur("Pierre", "Business", 45, "1995", 30000));
        p.ajouterEmploye(new Representant("Léon", "Vendtout", 25, "2001", 20000));
        p.ajouterEmploye(new Technicien("Yves", "Bosseur", 28, "1998", 1000));
        p.ajouterEmploye(new Manutentionnaire("Jeanne", "Stocketout", 32, "1998", 45));
        p.ajouterEmploye(new TechnARisque("Jean", "Flippe", 28, "2000", 1000));
        p.ajouterEmploye(new ManutARisque("Al", "Abordage", 30, "2001", 45));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");
    }
}
