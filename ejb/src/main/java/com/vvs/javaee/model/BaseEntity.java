package com.vvs.javaee.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void setId(Long id) { this.id = id;}
    public Long getId() { return this.id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity )) return false;
        return id != null && id.equals(((BaseEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
