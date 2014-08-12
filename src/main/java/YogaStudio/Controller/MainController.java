/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.ProductService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class MainController {   
    
    @Autowired
    private ProductService productService; 
    @Autowired
    private ClassService classService; 
    
    
    
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/index");
        
        List<ProductEntity> products = productService.getAll();
        List<ClassEntity> classes = classService.getClassList();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try{
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                view = new ModelAndView("/user/administrator");
            }
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                view = new ModelAndView("/user/customer");
            }            
            if (grantedAuthority.getAuthority().equals("ROLE_FACULTY")) {
                view = new ModelAndView("/user/faculty");
            }
        }
        }catch(Exception ex){}
        view.addObject("products", products.isEmpty()? null: products);
        view.addObject("classes", classes.isEmpty()? null: classes);
        view.addObject("pageTitle", "Welcome");
        return  view;
    }
}

