package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "warehouse_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private FactoryUnit factoryUnit;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "title", length = 60, nullable = false)
    private String title;

    @Column(name = "description", length = 160)
    private String description;

    @OneToMany(mappedBy = "warehouse")
    private Set<Batch> batches;

    public Warehouse() {
    }

    public Warehouse(FactoryUnit factoryUnit, String title) {
        this.factoryUnit = factoryUnit;
        this.title = title;
    }

    public Warehouse(Integer id, FactoryUnit factoryUnit, Integer capacity, String title, String description) {
        this.id = id;
        this.factoryUnit = factoryUnit;
        this.capacity = capacity;
        this.title = title;
        this.description = description;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FactoryUnit getFactoryUnit() {
        return factoryUnit;
    }

    public void setFactoryUnit(FactoryUnit factoryUnit) {
        this.factoryUnit = factoryUnit;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Warehouse(id: %d, title: %s)", this.getId(),
                this.getTitle());
    }
}
