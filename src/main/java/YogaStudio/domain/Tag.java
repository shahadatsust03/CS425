/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Shahadat
 */
@Entity

public class Tag implements DomainObject{
    
    @Id
    @GeneratedValue
    
    private int id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
