/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.OrderEntity;
import YogaStudio.domain.PaymentEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.ProductOrderEntity;
import YogaStudio.domain.UserEntity;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shahadat
 * User data access class -- this will interact with the database source
 */
@Transactional
public class ProductDAO {
    
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    public List<ProductEntity> getAll() {
        Query q = sf.getCurrentSession().createQuery("from ProductEntity");
        return q.list();
    }

    public boolean add(ProductEntity product) {
        try{
           sf.getCurrentSession().saveOrUpdate (product);
           return true;
        }
        catch(Exception e){
            System.out.println("Error adding new customer "+e.getMessage().toString());
            return false;
        }
    }

    public ProductEntity get(Long id) {
        return (ProductEntity) sf.getCurrentSession().load(ProductEntity.class, id);
    }

    public void update(int userId, ProductEntity product) {     

    }

    public void delete(Long productId) {
        ProductEntity product = get(productId);
        sf.getCurrentSession().delete(product);
    }
     
   
  public List<ProductEntity> findBy(String fieldName,String value){
       List list = null;
       try{
         String query = "from ProductEntity p where p.fieldname =:fieldvalue ".replace("fieldname", fieldName);
         Query result= sf.getCurrentSession().createQuery(query);
         result.setParameter("fieldvalue", value);
         list = result.list();
       }
       catch(Exception e){
         System.out.print("Error finding product : "+e.getMessage());
       }
       return list;
   }
   
   //search
   public List<ProductEntity> search(String fieldName,String value){
       List list = null;
       try{
         String query = "from ProductEntity p where p.fieldname like :fieldvalue ".replace("fieldname", fieldName);
         Query result= sf.getCurrentSession().createQuery(query);
         result.setParameter("fieldvalue", value);
         list = result.list();
       }
       catch(Exception e){
         System.out.print("Error finding product : "+e.getMessage());
       }
       return list;
   }
   
   
   public boolean addProductOrders(List<ProductOrderEntity> productOrders ,UserEntity user) {
        try{
             double totalAmount = 0;
             int totalQuantity = 0;
             /*create the order */
              OrderEntity order = new OrderEntity();
              /* map the customer to order*/
              order.setCustomer((CustomerEntity) user);
            /*add the  product list to the order*/
              for (ProductOrderEntity pOrder : productOrders){
                     /* the productOrder is detached from the product
                     it is necessary to fetch the product and set it on the product order
                    */
                     ProductEntity p = get(pOrder.getProduct().getId());
                     pOrder.setProduct(p);
                     pOrder.setTotalAmount(pOrder.getPrice() * pOrder.getQuantity());
                     totalAmount += pOrder.getPrice() * pOrder.getQuantity();
                     totalQuantity += pOrder.getQuantity();
                     order.addProductOrder(pOrder);
               }
              //set the total amount and quantity for the order
              order.setTotalAmount(totalAmount);
              order.setDateOfOrder(new Date());
              order.setTotalQuantity(totalQuantity);
              
              //Create the  payment 
              PaymentEntity payment = new PaymentEntity(totalAmount);
              order.addPayments(payment);
              
              sf.getCurrentSession().saveOrUpdate(order);
             
           //sf.getCurrentSession().persist(product);
           return true;
        }
        catch(Exception e){
            System.out.println("Error adding new customer "+e.getMessage().toString());
            return false;
        }
    }
   
   public List<OrderEntity> getUserOrders(CustomerEntity customer){
       List<OrderEntity> list = null;
       try{
         String query = "from OrderEntity O where O.customer.id =:value ORDER BY dateOfOrder ";
         Query result= sf.getCurrentSession().createQuery(query);
         result.setParameter("value", customer.getId());
         list = result.list();
         int index = 0;
         for(OrderEntity o:list){
             Query result1= sf.getCurrentSession().createQuery("from ProductOrderEntity O where O.order.id =:value");
             result1.setParameter("value",o.getId());
             List<ProductOrderEntity> list1 = result1.list();
             o.setProductOrders(list1);
             list.set(index, o);
             index++;
         }
       }
       catch(Exception e){
         System.out.print("Error finding query : "+e.getMessage());
       }
       return list;
   }
}
