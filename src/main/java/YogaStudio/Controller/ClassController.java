/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author sMazumder
 */
@Controller
public class ClassController {
    @Autowired
    private ClassService classService; 
    
    @RequestMapping(value = {"/classes","/user/classes"}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        List<ClassEntity> classes = classService.getClassList();
        ModelAndView view = new ModelAndView("/classes/class");
        view.addObject("classes", classes);
        view.addObject("pageTitle", "Classes");
        return  view;
    }
    
    @RequestMapping(value = {"/user/classes/add", "/classes/add"}, method = RequestMethod.GET)
    public String addEditClasses(HttpServletRequest request) {
        return "classes/addClass";
    }
    
    @RequestMapping(value = {"/classes/save","/user/classes/save"}, method = RequestMethod.POST)
    public RedirectView saveClass(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
         String  message =  addUpdateClass(request);    
         redirectAttributes.addFlashAttribute("message", message);
         return new RedirectView("/classes/add", true);
    }
    
     @RequestMapping(value = {"/classes/{id}", "/user/classes/{id}"}, method = RequestMethod.GET)
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("classes", classService.getClass(id));
        return "classes/classDetail";
    }    
      //private method to add add and update users
    private String addUpdateClass(HttpServletRequest request){
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("descritpion"),
                     price = request.getParameter("price");                    
                     
                 if(name.isEmpty())
                     message.add("Product name is required");
                 if(price.isEmpty())
                     message.add("Price is required");
                
                   
                 if(message.isEmpty()){
                     ClassEntity classEntity = new ClassEntity(name,description,Double.parseDouble(price));
                     boolean saved = classService.saveClass(classEntity);
                    
                     if(saved)
                       message.add("Successfully saved");
                     else
                       message.add("Class saving unsuccessful");
                   }
             return message.toString();
    }   
}
