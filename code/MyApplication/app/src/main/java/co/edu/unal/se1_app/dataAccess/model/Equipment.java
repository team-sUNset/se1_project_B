package co.edu.unal.se1_app.dataAccess.model;

public class Equipment {
    private Long id;

    private String name;

    private Long officeID;

    private int stock;

    public Equipment(String name, Long officeID, int stock) {
        this.name = name;
        this.officeID = officeID;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOfficeID() {
        return officeID;
    }

    public void setOfficeID(Long officeID) {
        this.officeID = officeID;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}