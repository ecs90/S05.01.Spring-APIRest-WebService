package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sucursal")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk_branchID;
    @Column(name = "name")
    private String branchName;
    @Column(name = "country")
    private String branchCountry;

    public Branch() {
    }

    public Branch(String branchName, String branchCountry) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
    }

    public Branch(Integer pk_branchID, String branchName, String branchCountry) {
        this.pk_branchID = pk_branchID;
        this.branchName = branchName;
        this.branchCountry = branchCountry;
    }

    public Integer getPk_branchID() {
        return pk_branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }
}
