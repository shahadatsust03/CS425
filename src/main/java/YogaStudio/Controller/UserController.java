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
import YogaStudio.service.ProductService;
import YogaStudio.service.UserService;
import YogaStudio.service.WaiverService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
        List<ProductEntity> products = productService.getAll();
        List<ClassEntity> classes = classService.getClassList();

        ModelAndView view = new ModelAndView("/user/customer");
        view.addObject("products", products.isEmpty() ? null : products);
        view.addObject("classes", classes.isEmpty() ? null : classes);
        return view;
    }

    @RequestMapping(value = "/user/administrator", method = RequestMethod.GET)
    public ModelAndView getAdministratorPage(HttpServletRequest request) {
        List<ProductEntity> products = productService.getAll();
        List<ClassEntity> classes = classService.getClassList();

        ModelAndView view = new ModelAndView("/user/administrator");
        view.addObject("products", products.isEmpty() ? null : products);
        view.addObject("classes", classes.isEmpty() ? null : classes);

        return view;
    }

    @RequestMapping(value = "/user/faculty", method = RequestMethod.GET)
    public ModelAndView getFacultyPage(HttpServletRequest request) {
        return new ModelAndView("/user/faculty", "message", "nothing");
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
    public String delete(int userId) {
        userService.delete(Long.valueOf(userId));
        return "redirect:/users";
    }

    //controller action for user registeration
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RedirectView register(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String message = addUser(request);
        //RedirectView view = new RedirectView("redirect:/");
        //ModelAndView view = new ModelAndView(new RedirectView("/", true));
        //view.addObject("message", message);
        redirectAttributes.addFlashAttribute("message", message);
        return new RedirectView("/", true);//"redirect:/";
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
            user.setJoinDate(sdf.parse(request.getParameter("joinDate")));
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
        UserEntity user = userService.get(Long.valueOf(1));
        List<ClassEntity> classes = classService.getClassList();
        ModelAndView view = new ModelAndView("/waiver/requestWaiver");
        view.addObject("classes", classes);
        view.addObject("user", user);
        view.addObject("pageTitle", "Waivers");
        String message = "Waiver Request:";//updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
//         if (user != null) {                
//                return new ModelAndView("/user/requestWaiver", "user", user);
//            }
//        return new ModelAndView("/user/requestWaiver", "user", "not found");  
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

    private String addUser(HttpServletRequest request) {
        List message = new ArrayList();
        String firstName = request.getParameter("firstName"),
                lastName = request.getParameter("lastName"),
                email = request.getParameter("email"),
                username = request.getParameter("username");

        if (firstName.isEmpty()) {
            message.add("First name is required");
        }
        if (lastName.isEmpty()) {
            message.add("Last name is required");
        }
        if (email.isEmpty()) {
            message.add("Email is required");
        }
        if (username.isEmpty()) {
            message.add("Username is required");
        }

        if (message.isEmpty()) {
            String fullname = firstName.concat(" " + lastName);
            String authority = request.getParameter("authority");
            //set the role customer by default
            authority = (authority == null) ? "ROLE_USER" : authority;
            boolean saved = userService.add(fullname, email, username, authority);

            if (saved) {
                message.add("Registration successful saved");
            } else {
                message.add("Registration unsuccessful");
            }
        }
        return message.toString();
    }

    //get current context user
    private UserDetails currentUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private String submitWaiver(HttpServletRequest request, RedirectView view, int id) {
        CustomerEntity customer = userService.getCustomer(Long.valueOf(id));
        ClassEntity yogaClass = classService.getClass(request.getParameter("class"));
        String message = "Failed to submit waiver request.Class Not Found.";
        if (yogaClass != null && customer != null) {

            waiverService.getWaiversByCustAndClass(customer.getId(), yogaClass.getId());

            WaiverEntity waiver = new WaiverEntity(customer, request.getParameter("reason"), yogaClass);

            boolean submitStatus = waiverService.submitWaiverRequest(waiver, id);
            //UserEntity updatedUser = userService.update(Long.valueOf(id), user);
            view.setUrl(request.getContextPath() + "/waiver/viewWaivers");
            message = "Waiver Request Submitted Successfully.";
            // view.setUrl("add");
            if (submitStatus == false) {
                message = "Failed to submit waiver request.";
            }
        }
        return message;
    }
    
    @RequestMapping(value = "/waiver/viewWaivers/{id}", method = RequestMethod.GET)
    public ModelAndView viewWaivers(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
            List<WaiverEntity> waivers=waiverService.getWaiversByCust(id);
        
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

}
