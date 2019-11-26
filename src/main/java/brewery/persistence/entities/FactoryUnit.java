package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "factory_unit")
public class FactoryUnit implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "unit_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "factory_id", nullable = false)
    private BusinessFactory businessFactory;

    @Column(name = "description", length = 160)
    private String description;

    @OneToMany(mappedBy = "factoryUnit")
    private Set<Warehouse> warehouses;

    public FactoryUnit() {
    }

    public FactoryUnit(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }

    public FactoryUnit(Integer id, BusinessFactory businessFactory, String description) {
        this.id = id;
        this.businessFactory = businessFactory;
        this.description = description;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BusinessFactory getBusinessFactory() {
        return businessFactory;
    }

    public void setBusinessFactory(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("FactoryUnit(id: %d, factory: %s)", this.getId(),
                this.getBusinessFactory());
    }
}
