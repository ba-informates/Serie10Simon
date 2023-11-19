abstract class Employe {

    private String name;
    private String firstName;
    private int age;
    private String joinDate;

    abstract double calculerSalaire();

    String getNom() {
        return "L'employé " + firstName + " " + name;
    }

    public Employe(String name, String firstName, int age, String joinDate) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.joinDate = joinDate;
    }
}

class Vendeur extends EmployePayeAuCA {
    public Vendeur(String name, String firstName, int age, String joinDate, int ca) {
        super(name, firstName, age, joinDate, ca);
    }
}

class Representant extends EmployePayeAuCA {
    public Representant(String name, String firstName, int age, String joinDate, int ca) {
        super(name, firstName, age, joinDate, ca);
    }
}

class EmployePayeAuCA extends Employe {
    private int ca;
    public EmployePayeAuCA (String name, String firstName, int age, String joinDate, int ca) {
        super(name, firstName, age, joinDate);
        this.ca = ca;
    }
    double calculerSalaire() {
        return this.ca * 0.2 + 400;
    }
}

class Technicien extends Employe {
    private int unitCount;
    public Technicien(String name, String firstName, int age, String joinDate, int unitCount) {
        super(name, firstName, age, joinDate);
        this.unitCount = unitCount;
    }
    double calculerSalaire () {
        return this.unitCount * 5;
    }
}

class Manutentionnaire extends Employe {
    private int hourCount;
    public Manutentionnaire(String name, String firstName, int age, String joinDate, int hourCount) {
        super(name, firstName, age, joinDate);
        this.hourCount = hourCount;
    }
    double calculerSalaire() {
        return this.hourCount * 65;
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
