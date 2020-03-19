package com.bitbox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Date;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@Entity
@Table(name = "PRICE_REDUCTIONS")
public class Price_Reduction implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull    
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic(optional = false)
    @NotNull    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date start_date;
    
    @Basic(optional = false)
    @NotNull    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date end_date;
    
    @ManyToMany(mappedBy = "price_reductions", fetch = FetchType.LAZY)
    private List<Item> items;
    
    // Constructors    
    public Price_Reduction() {}

    public Price_Reduction(Long id) {
        this.id = id;
    }

    public Price_Reduction(Long id, Date start_date, Date end_date) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Price_Reduction other = (Price_Reduction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Price_Reduction{" + "id=" + id + ", start_date=" + start_date + ", end_date=" + end_date + ", items=" + items + '}';
    }

}
