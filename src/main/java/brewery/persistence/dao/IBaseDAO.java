package brewery.persistence.dao;

import java.io.Serializable;
import java.util.List;


public interface IBaseDAO<E extends Serializable, PK> {
    List<E> findAll();

    E read(PK id);

    void update(E entity);

    void delete(PK id);

    PK create(E entity);

}
