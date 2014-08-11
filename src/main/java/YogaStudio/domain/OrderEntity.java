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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfOrder;
    private int totalQuantity;
    private double totalAmount;

    public OrderEntity() {
        this.totalQuantity = 0;
        this.totalAmount = 0;
    }

    public OrderEntity( int totalQuantity, double totalAmount) {
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
    }
    
    @OneToMany(mappedBy="product",cascade={CascadeType.ALL})
    List<ProductOrderEntity> productOrders = new ArrayList<ProductOrderEntity>();         //////////////Have to throughly tested
    
    @ManyToOne(cascade={CascadeType.ALL})
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
    
    public void addProductOrder(ProductOrderEntity product){  
        product.setOrder(this);
        productOrders.add(product);
    }
    public void removeProducts(ProductOrderEntity product){  
       product.setOrder(null);
       productOrders.remove(product);
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    } 

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<ProductOrderEntity> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrderEntity> productOrders) {
        this.productOrders = productOrders;
    }  
}
