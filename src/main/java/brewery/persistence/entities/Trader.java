package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "trader")
public class Trader implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "trader_id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @OneToMany(mappedBy = "trader")
    private Set<Batch> batches;

    public Trader() {
    }

    public Trader(String name) {
        this.name = name;
    }

    public Trader(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Trader(id: %d, name: %s)", this.getId(),
                this.getName());
    }
}
