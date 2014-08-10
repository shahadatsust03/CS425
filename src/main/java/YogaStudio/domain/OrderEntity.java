/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dateOfOrder;
    private int totalAmount;
    
    @ManyToMany(cascade={CascadeType.PERSIST})
    List<ProductEntity> products = new ArrayList<ProductEntity>();         //////////////Have to throughly tested
    
    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn
    CustomerEntity customer;

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    @OneToMany(mappedBy="order",cascade={CascadeType.ALL})
    private Set<PaymentEntity> payments = new HashSet<PaymentEntity>();

    public void addPayments(PaymentEntity payment){
        payment.setOrder(this);
        payments.add(payment);
    }
    
    public void removePayments(PaymentEntity payment){
        payment.setOrder(null);
        payments.remove(payment);
    }
    
    
    public void addProducts(ProductEntity product){                                                
        products.add(product);
    }
    public void removeProducts(ProductEntity product){
                                             
        products.remove(product);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

  
}
