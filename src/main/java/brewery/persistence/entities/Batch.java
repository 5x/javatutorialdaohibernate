package brewery.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "batch")
public class Batch implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "batch_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "trader_id")
    private Trader trader;

    @Column(name = "created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "shipment")
    private Date shipmentDate;

    @Column(name = "note")
    private String note;

    public Batch() {
    }

    public Batch(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Batch(Integer id, Warehouse warehouse, Trader trader, Date createDate, Date shipmentDate, String note) {
        this.id = id;
        this.warehouse = warehouse;
        this.trader = trader;
        this.createDate = createDate;
        this.shipmentDate = shipmentDate;
        this.note = note;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("Batch(id: %d, warehouse: %s)",
                this.getId(), this.getWarehouse());
    }
}
