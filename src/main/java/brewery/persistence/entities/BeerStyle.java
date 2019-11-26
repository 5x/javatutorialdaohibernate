package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "beer_styles")
public class BeerStyle implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "beer_id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 160)
    private String name;

    public BeerStyle() {
    }

    public BeerStyle(String name) {
        this.name = name;
    }

    public BeerStyle(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("BeerStyle(id: %d, name: %s)", this.getId(),
                this.getName());
    }
}
