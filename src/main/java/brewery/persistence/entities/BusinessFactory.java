package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "factory")
public class BusinessFactory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "factory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @OneToMany(mappedBy = "businessFactory")
    private Set<FactoryUnit> factoryUnits;

    public BusinessFactory(City city, String title) {
        this.city = city;
        this.title = title;
    }

    public BusinessFactory() {
    }

    public BusinessFactory(Integer id, City city, String title) {
        this.id = id;
        this.city = city;
        this.title = title;
    }

    public Set<FactoryUnit> getFactoryUnits() {
        return factoryUnits;
    }

    public void setFactoryUnits(Set<FactoryUnit> factoryUnits) {
        this.factoryUnits = factoryUnits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Factory(id: %d, title: %s, city: %s)",
                this.id, this.title, this.city.toString());
    }
}
