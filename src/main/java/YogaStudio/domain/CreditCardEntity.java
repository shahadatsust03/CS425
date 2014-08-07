/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sMazumder
 */
@Entity
@Table(name="CreditCard")
public class CreditCardEntity {
   
    @Id
    @GeneratedValue
    private Long id;
    private Long cardNumber;
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @OneToOne(cascade={CascadeType.ALL})
    UserEntity user;
    
    @OneToMany(mappedBy="creditcard", cascade={CascadeType.ALL})
//    @JoinColumn
    List<PaymentEntity> payments;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }
    

    public CreditCardEntity() {
    }

    public CreditCardEntity(Long id, Long cardNumber, Date expiryDate) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
