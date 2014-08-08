/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.dao.generic;

import YogaStudio.domain.ProductEntity;
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
         String query = "from ProductEntity  where p.fieldname =:fieldvalue ".replace("fieldname", fieldName);
         Query result= sf.getCurrentSession().createQuery(query);
         result.setParameter("fieldvalue", value);
         list = result.list();
       }
       catch(Exception e){
         System.out.print("Error finding product : "+e.getMessage());
       }
       return list;
   }
}
