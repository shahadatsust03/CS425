/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;

import YogaStudio.dao.generic.ClassDAO;
import YogaStudio.dao.generic.CustomerDAO;
import YogaStudio.dao.generic.EnrollmentDAO;
import YogaStudio.dao.generic.PaymentDAO;
import YogaStudio.dao.generic.SectionDAO;
import YogaStudio.dao.generic.TranscriptDAO;
import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.EnrollmentEntity;
import YogaStudio.domain.PaymentEntity;
import YogaStudio.domain.SectionEntity;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sMazumder
 */
public class PaymentService {
     
    @Autowired
    private PaymentDAO paymentdao;
     
    public void savePayment(PaymentEntity paymentEntity) {
       paymentdao.savePayment(paymentEntity);
    }

    public void setPaymentdao(PaymentDAO paymentdao) {
        this.paymentdao = paymentdao;
    }

    public PaymentDAO getPaymentdao() {
        return paymentdao;
    }
    
      
    
   
}
