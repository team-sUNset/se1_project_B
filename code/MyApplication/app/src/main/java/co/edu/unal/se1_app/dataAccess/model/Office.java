package co.edu.unal.se1_app.dataAccess.model;

public class Office {
    private Long id;

    private String name;

    public Office(String name) {
        this.name = name;
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
}