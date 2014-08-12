/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.UserEntity;
import YogaStudio.domain.WaiverEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.CustomerService;
import YogaStudio.service.ProductService;
import YogaStudio.service.UserService;
import YogaStudio.service.WaiverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Shahadat
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassService classService;
    @Autowired
    private WaiverService waiverService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/user/results", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) {
        String message = "Invalid credentials";
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            if (userService.authenticateUser(name, password)) {
                message = "LoginSuccessFull";
                return new ModelAndView("results", "message", message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("results", "message", message);
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginFailed(HttpServletRequest request) {
        return new ModelAndView("index", "message", "Invalid username/password. Please try again.");
    }

    @RequestMapping(value = "/user/customer", method = RequestMethod.GET)
    public ModelAndView getCustomerPage(HttpServletRequest request) {
        List<ProductEntity> products = productService.getAll(0,4);
        List<ClassEntity> classes = classService.getClassList();

        ModelAndView view = new ModelAndView("/user/customer");
        view.addObject("products", products.isEmpty() ? null : products);
        view.addObject("classes", classes.isEmpty() ? null : classes);
        return view;
    }

    @RequestMapping(value = "/user/administrator", method = RequestMethod.GET)
    public ModelAndView getAdministratorPage(HttpServletRequest request) {
        List<ProductEntity> products = productService.getAll(0,4);
        List<ClassEntity> classes = classService.getClassList();

        ModelAndView view = new ModelAndView("/user/administrator");
        view.addObject("products", products.isEmpty() ? null : products);
        view.addObject("classes", classes.isEmpty() ? null : classes);

        return view;
    }

    @RequestMapping(value = "/user/faculty", method = RequestMethod.GET)
    public ModelAndView getFacultyPage(HttpServletRequest request) {
        List<ProductEntity> products = productService.getAll();
        List<ClassEntity> classes = classService.getClassList();

        ModelAndView view = new ModelAndView("/user/faculty");
        view.addObject("products", products.isEmpty() ? null : products);
        view.addObject("classes", classes.isEmpty() ? null : classes);

        return view;
    }

    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.GET)
    public String addUser(@ModelAttribute("user") UserEntity userentity) {

        return "addUser";
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    public String add(@Valid UserEntity userentity, BindingResult result, RedirectAttributes re) {
        String view = "redirect:/users";
        if (!result.hasErrors()) {
            //  userService.add(userentity);

        } else {
            view = "addUser";
        }
        return view;
    }

    @RequestMapping(value = "/user/users/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.get(Long.valueOf(id)));
        return "userDetail";
    }

    @RequestMapping(value = "/user/users/{id}", method = RequestMethod.POST)
    public String update(@Valid UserEntity user, BindingResult result,
            @PathVariable int id) {
        if (!result.hasErrors()) {
            userService.update(Long.valueOf(id), user); // car.id already set by binding
            return "redirect:/users";
        } else {
            return "userDetail";
        }
    }

    @RequestMapping(value = "/user/users/delete", method = RequestMethod.POST)
    public String delete(Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

    //controller action for user registeration
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="text/plain")
    @ResponseBody
    public String register(HttpServletRequest request) {
           ObjectMapper mapper = new ObjectMapper();
           HashMap response = registerUser(request);
           String json = ""; 
           try {
                //convert map to JSON string
                json = mapper.writeValueAsString( response);
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         return json;
    }

    @RequestMapping(value = "/user/myaccount", method = RequestMethod.GET)
    public ModelAndView viewProfile(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            Object object = auth.getPrincipal();

            String password = ((UserDetails) object).getPassword();
            System.out.println("User :" + name + "  Password:" + password);
            UserEntity user = userService.findUser(name, password);
            //UserEntity user = userService.findUser("devika", "devika");
            System.out.println("User Found:" + user);
            if (user != null) {
                // redirectAttributes.addAttribute("Profile", user);
                //return "user/myaccount";
                return new ModelAndView("/user/myaccount", "Profile", user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //redirectAttributes.addFlashAttribute("message", "Profile not found.");
        return new ModelAndView("/user/myaccount", "Profile", "not found");
        //return "redirect:/";
    }

    @RequestMapping(value = "/user/editProfile/{id}", method = RequestMethod.GET)
    public ModelAndView editProfile(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        UserEntity user = userService.get(Long.valueOf(id));
        String message = "Update Profile";//updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
        if (user != null) {
            return new ModelAndView("/user/editProfile", "Profile", user);
        }
        return new ModelAndView("/user/editProfile", "Profile", "not found");
    }

    @RequestMapping(value = "/user/editProfile/{id}", method = RequestMethod.POST)
    public RedirectView saveProfile(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {

        RedirectView view = new RedirectView();
        String message = updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
        return view;//"redirect:/";       
        //redirectAttributes.addFlashAttribute("message", "Profile not found.");
        //return new ModelAndView("/user/editProfile", "Profile", "not found");
        //return "redirect:/";
    }

    private String updateProfile(HttpServletRequest request, RedirectView view, int id) {
        UserEntity user = userService.get(Long.valueOf(id));

        user.setFullname(request.getParameter("fullname"));
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        try {
            user.setDateOfBirth(sdf.parse(request.getParameter("dateOfBirth")));
            //user.setJoinDate(sdf.parse(request.getParameter("joinDate")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setContactNum(Long.valueOf(request.getParameter("contactNum")));
        user.setStreet(request.getParameter("street"));
        user.setCity(request.getParameter("city"));
        user.setState(request.getParameter("state"));
        user.setCountry(request.getParameter("country"));
        user.setZipcode(Long.valueOf(request.getParameter("zipcode")));

        UserEntity updatedUser = userService.update(Long.valueOf(id), user);
        view.setUrl(request.getContextPath() + "/user/myaccount");
        String message = "Profile Updated Successfully.";
        // view.setUrl("add");
        if (updatedUser == null) {
            message = "Failed to Update the Profile";
        }
        return message;
    }

    @RequestMapping(value = "/waiver/requestWaiver/{id}", method = RequestMethod.GET)
    public ModelAndView requestWaiver(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        CustomerEntity user = customerService.get(Long.valueOf(id));
        List<ClassEntity> classes = classService.getClassList();
        ModelAndView view = new ModelAndView("/waiver/requestWaiver");
        view.addObject("classes", classes);
        view.addObject("user", user);
        // view.addObject("pageTitle", "Waivers");
        String message = "Waiver Request:";
        redirectAttributes.addFlashAttribute("message", message);
        return view;
    }

    @RequestMapping(value = "/waiver/submitWaiver/{id}", method = RequestMethod.POST)
    public RedirectView submitRequest(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {

        RedirectView view = new RedirectView();
        String message = submitWaiver(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
        return view;//"redirect:/";       
        //redirectAttributes.addFlashAttribute("message", "Profile not found.");
        //return new ModelAndView("/user/editProfile", "Profile", "not found");
        //return "redirect:/";
    }

    private HashMap registerUser(HttpServletRequest request) {
        HashMap response = new HashMap();
        List message = new ArrayList();
        boolean saved  = false;
        try{
                String firstname = request.getParameter("firstName"),
                        lastname = request.getParameter("lastName"),
                        email = request.getParameter("email"),
                        username = request.getParameter("username"),
                        street =  request.getParameter("street"),
                        city =  request.getParameter("city"),
                        country =  request.getParameter("country"),
                        state =  request.getParameter("state"),
                        zipcode =  request.getParameter("zipcode"),
                        contactnumber =  request.getParameter("contactNum");

                Date dateOfBirth = new Date();

                if (firstname.isEmpty()) {
                    message.add("First name is required");
                }
                if (lastname.isEmpty()) {
                    message.add("Last name is required");
                }
                if (email.isEmpty()) {
                    message.add("Email is required");
                }
                if (username.isEmpty()) {
                    message.add("Username is required");
                }
                //if(dateOfBirth.)
             // SimpleDateFormat formatter = new SimpleDateFormat("MMM/yyyy");
                    //                         Date expDate = formatter.parse(expirydate);
                if (message.isEmpty()) {
                    String authority = request.getParameter("authority");
                    //set the role customer by default
                    authority = (authority == null) ? "ROLE_USER" : authority;
                    saved = userService.add(username, 
                                                    email, 
                                                    username,
                                                    dateOfBirth, 
                                                    street, 
                                                    city, 
                                                    country,
                                                    state, 
                                                    Long.valueOf(zipcode),
                                                    Long.valueOf(contactnumber),
                                                    authority);

                    if (saved) {
                        message.add("Registration successful saved");
                    } else {
                        message.add("Registration unsuccessful");
                    }
                }
        }
        catch(Exception e){
            message.add("Registration unsuccessful");
        }
        
        response.put("success",saved);
        response.put("message",message.toString());
        return response;
    }

    //get current context user
    private UserDetails currentUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private String submitWaiver(HttpServletRequest request, RedirectView view, int id) {
        System.out.println("Id:" + Long.valueOf(id) + "  Class_Id:" + request.getParameter("class_id"));
        CustomerEntity customer = customerService.get(Long.valueOf(id));
        ClassEntity yogaClass = classService.getClass(Long.valueOf(request.getParameter("class_id")));
        String message = "Failed to submit waiver request.";
        if (yogaClass != null && customer != null) {

            List<WaiverEntity> waiverList = waiverService.getWaiversByCustAndClass(customer.getId(), yogaClass.getId());
            if (waiverList == null || waiverList.isEmpty()) {

                WaiverEntity waiver = new WaiverEntity(customer, request.getParameter("reason"), yogaClass);

                boolean submitStatus = waiverService.submitWaiverRequest(waiver, Long.valueOf(id));
                //UserEntity updatedUser = userService.update(Long.valueOf(id), user);                
                message = "Waiver Request Submitted Successfully.";
                // view.setUrl("add");
                if (submitStatus == false) {
                    message = "Failed to submit waiver request.";
                }
            } else {
                message = message + "Request for waiver is already submitted.";
            }
            view.setUrl(request.getContextPath() + "/waiver/viewWaivers/"+id);
        }
        return message;
    }

    @RequestMapping(value = "/waiver/viewWaivers/{id}", method = RequestMethod.GET)
    public ModelAndView viewWaivers(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        System.out.println("Id:" + Long.valueOf(id));
        CustomerEntity cust = customerService.get(Long.valueOf(id));
        List<WaiverEntity> waivers = waiverService.getWaiversByCust(cust);
        //List<WaiverEntity> waivers = new ArrayList<WaiverEntity>();
       // if(cust!=null)
       // waivers = cust.getWaivers();
        ModelAndView view = new ModelAndView("/waiver/viewWaivers");
        view.addObject("waivers", waivers);
        view.addObject("pageTitle", "Waivers");
        String message = "Waiver Request:";//updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
//         if (user != null) {                
//                return new ModelAndView("/user/requestWaiver", "user", user);
//            }
//        return new ModelAndView("/user/requestWaiver", "user", "not found");  
        return view;
    }
    
    //view customer orders     
   @RequestMapping(value = "/user/orders", method = RequestMethod.GET)
    public String viewUserOrders(Model model) {
       // model.addAttribute("product", productService.get(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();          
        Object object = auth.getPrincipal();   
        UserDetails userDetails = (UserDetails)object; //
        //TODO user by username and password
        UserEntity user = userService.findCustomerBy("username",username);
        if(user !=null)
        {
         model.addAttribute("orders", productService.getUserOrders((CustomerEntity) user));
        }
        return "product/customerOrders";
    }
    
    @RequestMapping(value="/requestpassword", method=RequestMethod.POST, produces="text/plain")
    @ResponseBody
    public String requestPassword(HttpServletRequest request) {
                    ObjectMapper mapper = new ObjectMapper();
                    String json = "";
                    HashMap response = new HashMap();
                           try{
                              String email  = request.getParameter("email");
                              UserEntity user = userService.findUserBy("email", email);
                              if(user != null){
                                  boolean updated = userService.updatePassword(user);
                                  response.put("success",updated);
                                  response.put("message",
                                              (updated)? "Password successfully reset,please check your inbox": "Sorry,we are unable to reset your password");
                              }
                              else{
                               response.put("success", false);
                               response.put("message", "Sorry ,no user with such with that email");
                              }
            }
            catch(Exception e){
              response.put("success", false);
            }
     
            try {
                //convert map to JSON string
                json = mapper.writeValueAsString( response);
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return json;//"[{\"success\": false, \"message\": \"your request failed\"}]";
    }

    @RequestMapping(value="/user/savecreditcard", method=RequestMethod.POST, produces="text/plain")
    @ResponseBody
    public String addCreditCard(HttpServletRequest request) {
                    ObjectMapper mapper = new ObjectMapper();
                    String json = "";
                    HashMap response = new HashMap();
                           try{
                              Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                              String username = auth.getName();          
                              Object object = auth.getPrincipal();   
                              UserDetails userDetails = (UserDetails)object; //
                              //TODO user by username and password
                              UserEntity user = userService.findCustomerBy("username",username);
                              if(user != null){
                                  boolean valid = true;
                                  String cardnumber  = request.getParameter("cardnumber");
                                  String expirydate =  request.getParameter("expirydate");
                                  //validate card number
                                  if(!valiateParameter(cardnumber)){
                                      valid = false;
                                   }
                                  //validate expiry date
                                  if(!valiateParameter(expirydate)){
                                      valid = false;
                                  }
                                  //validation failed
                                  if(valid){
                                     //date format 
                                      DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                      Date expDate = DateUtils.parseDate(expirydate,"MM/yyyy"); 
                                     //formattedDate = formatter.format(today); 

                                     boolean updated = userService.addCreditCard(user,Long.parseLong(cardnumber), expDate);
                                     response.put("success",updated);
                                     response.put("message", (updated)? "Credit card successfully added": "Sorry,unable to add credit card");
                                  }
                                  else{
                                     response.put("success",false);
                                     response.put("message","Entries are not valid!");
                                  }
                              }
                              else{
                               response.put("success", false);
                               response.put("message", "Sorry , you dont have an account");
                              }
            }
            catch(Exception e){
              response.put("success", false);
            }
     
            try {
                //convert map to JSON string
                json = mapper.writeValueAsString( response);
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return json;//"[{\"success\": false, \"message\": \"your request failed\"}]";
    }
    
    private boolean valiateParameter(String param){
         
       return true;
    }
}
