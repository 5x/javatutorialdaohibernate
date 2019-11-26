package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "city")
    private Set<BusinessFactory> businessFactories;

    public City() {
    }

    public City(String name) {
        this(null, name);
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<BusinessFactory> getBusinessFactories() {
        return businessFactories;
    }

    public void setBusinessFactories(Set<BusinessFactory> businessFactories) {
        this.businessFactories = businessFactories;
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
        return String.format("City(id: %d, name: %s)", this.getId(),
                this.getName());
    }
}
