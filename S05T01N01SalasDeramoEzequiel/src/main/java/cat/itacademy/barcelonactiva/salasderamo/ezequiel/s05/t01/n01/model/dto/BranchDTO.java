package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t01.n01.model.dto;


public class BranchDTO {
    private Integer pk_branchID;
    private String branchName;
    private String branchCountry;
    private String branchType;

    public Integer getPk_branchID() {
        return pk_branchID;
    }

    public void setPk_branchID(Integer pk_branchID) {
        this.pk_branchID = pk_branchID;
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

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }
}
