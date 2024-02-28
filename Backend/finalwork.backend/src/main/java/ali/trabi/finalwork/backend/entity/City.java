package ali.trabi.finalwork.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Double x_value;
    Double y_value;

    public City() {
    }

    public City(Integer id, String name, Double x_value, Double y_value) {
        this.id = id;
        this.name = name;
        this.x_value = x_value;
        this.y_value = y_value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX_value() {
        return x_value;
    }

    public void setX_value(Double x_value) {
        this.x_value = x_value;
    }

    public Double getY_value() {
        return y_value;
    }

    public void setY_value(Double y_value) {
        this.y_value = y_value;
    }
}
