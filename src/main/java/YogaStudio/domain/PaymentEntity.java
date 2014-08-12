/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="Payment")
public class PaymentEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date paymentDate;    

    @ManyToOne()
    @JoinTable(name="Class_Payment",  
    joinColumns={@JoinColumn(name="Class_Id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="Payment_Id", referencedColumnName="id")})
    ClassEntity classes;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinTable(name="Order_Payment",  
    joinColumns={@JoinColumn(name="Order_Id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="Payment_Id", referencedColumnName="id")})  
    OrderEntity order;
    
    @ManyToOne()
    @JoinColumn
    CreditCardEntity creditcard;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
    
     
        
    public ClassEntity getClasses() {
        return classes;
    }

    public void setClasses(ClassEntity classes) {
        this.classes = classes;
    }
    
    public PaymentEntity() {
    
    }
    
    public PaymentEntity(Double amount) {
        this.amount = amount;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentEntity(Long id, Date paymentDate, Double amount) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
    private Double amount;
    public Long getId() {
        return id;
    }

    public void setCreditcard(CreditCardEntity creditcard) {
        this.creditcard = creditcard;
    }


    public CreditCardEntity getCreditcard() {
        return creditcard;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    
}
