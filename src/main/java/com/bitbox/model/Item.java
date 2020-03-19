package com.bitbox.model;

import com.bitbox.enums.Item_State;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@Entity
@Table(name = "ITEMS")
public class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull    
    @Column(name = "code")
    private Long item_code;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = true)
    @Column(name = "price")
    private Double price;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_state", columnDefinition = "VARCHAR(12) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Item_State item_state;
    
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "reason_deactivation")
    private String reason;
    
    @JoinTable(name = "REL_ITEM_SUPPLIER",
            joinColumns = @JoinColumn(name = "FK_item_code", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_supplier", nullable = false))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Supplier> suppliers;
    
    @JoinTable(name = "REL_ITEM_PRICE_REDUCTION",
            joinColumns = @JoinColumn(name = "FK_item_code", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_price_reduction", nullable = false))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Price_Reduction> price_reductions;
    
    @Basic(optional = true)    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date creation_date;
    
    @JoinColumn(name = "creator", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;
    
    // Constructors
    public Item() {}
    
    public Item(Long item_code) {
        this.item_code = item_code;
    }

    public Item(Long item_code, String description, Double price, Item_State item_state, User creator) {
        this.item_code = item_code;
        this.description = description;
        this.price = price;
        this.item_state = Item_State.ACTIVE;        
        this.creator = creator;
    }
    
    // Getters and setters
    public Long getItem_code() {
        return item_code;
    }

    public void setItem_code(Long item_code) {
        this.item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Item_State getItem_state() {
        return item_state;
    }

    public void setItem_state(Item_State item_state) {
        this.item_state = item_state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    // Setter of suppliers
    public void addSupplier(Supplier supplier) {
        if(this.suppliers == null) {
            this.suppliers = new ArrayList<>();
        }
        this.suppliers.add(supplier);
    }
    
    public List<Price_Reduction> getPrice_reduction() {
        return price_reductions;
    }
    
    // Setters of Price reduction
    public void addPriceReduction(Price_Reduction price_Reduction) {
        if(this.price_reductions == null) {
            this.price_reductions = new ArrayList<>();
        }
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.item_code);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.item_code, other.item_code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "item_code=" + item_code + ", description=" + description + ", price=" + price + ", item_state=" + item_state + ", reason=" + reason + ", suppliers=" + suppliers + ", price_reductions=" + price_reductions + ", creation_date=" + creation_date + ", creator=" + creator + '}';
    }

}
