/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Shahadat
 */
@NamedQueries({ @NamedQuery(name = "userLogin", query = "select u.id from UserEntity u where u.username=:username and u.password=:password and u.activests=:enabled" ),
@NamedQuery(name = "findUserByUsername", query = "select u from UserEntity u where u.username=:username and u.password=:password and u.activests=:enabled" )})
@Entity
@Table(name="Users")
@SecondaryTables(@SecondaryTable(name="user_roles",pkJoinColumns = {
@PrimaryKeyJoinColumn(name="USER_ID",referencedColumnName = "USER_ID")}))
@Inheritance(strategy=InheritanceType.JOINED)
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="USER_ID")
    private Long id;
     @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="FULLNAME")
    private String fullname;
    @Column(name="EMAIL")
    private String email;
   @Column(name="ENABLED")
    private Byte activests;
    
    @Column(table="user_roles")
    private String AUTHORITY;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;
    @Column(name="RATING")
    private Byte userrating;
    @Column(name = "CONTACT_NUM")
    private Long contactNum;
    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "ZIPCODE")
    private Long zipcode;
     
     @OneToOne(mappedBy="user",cascade={CascadeType.ALL},orphanRemoval = true)
     @JoinColumn
     private CreditCardEntity creditCard;
    
    public int getUserrating() {
        return userrating;
    }

    public void setUserrating(Byte userrating) {
        this.userrating = userrating;
    }        
    
//    public String getUserlabel() {
//        return userlabel;
//    }
//
//    public void setUserlabel(String userlabel) {
//        this.userlabel = userlabel;
//    }
//    
    
//    public String getUserranked() {
//        return userranked;
//    }
//
//    public void setUserranked(String userranked) {
//        this.userranked = userranked;
//    }
     

    public byte getActivests() {
        return activests;
    }

    public void setActivests(byte activests) {
        this.activests = activests;
    }

    public String getAUTHORITY() {
        return AUTHORITY;
    }

    public void setAUTHORITY(String AUTHORITY) {
        this.AUTHORITY = AUTHORITY;
    }
    
   
    
    public UserEntity(){
    
    }
    
    public UserEntity(String username, String password, String fullname, String email){
        //super();
        this.username=username;
        this.password=password;
        this.fullname=fullname;
        this.email=email;     
    }

    public UserEntity(String username, String password, String fullname, String email, Date dateOfBirth, Long contactNum, String street, String city, String state, String country, Long zipcode) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.contactNum = contactNum;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }
    
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the joinDate
     */
    public Date getJoinDate() {
        return joinDate;
    }
    
    public UserEntity(String username, String password, String fullname, String email, String AUTHORITY, Date dateOfBirth, Long contactNum, String street, String city, String state, String country, Long zipcode) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.AUTHORITY = AUTHORITY;
        this.dateOfBirth = dateOfBirth;
        this.contactNum = contactNum;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    /**
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @return the contactNum
     */
    public Long getContactNum() {
        return contactNum;
    }

    /**
     * @param contactNum the contactNum to set
     */
    public void setContactNum(long contactNum) {
        this.contactNum = contactNum;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the zipcode
     */
    public long getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the creditCard
     */
    public CreditCardEntity getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(CreditCardEntity creditCard) {
        this.creditCard = creditCard;
    }

     
}
