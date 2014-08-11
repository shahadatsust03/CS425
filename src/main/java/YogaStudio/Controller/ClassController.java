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
    public ModelAndView getClasses(HttpServletRequest request) {
        List<ClassEntity> classes = classService.getClassList();
        ModelAndView view = new ModelAndView("/classes/class");
        view.addObject("classes", classes);
        view.addObject("pageTitle", "Classes");
        return  view;
    }
    
    @RequestMapping(value = {"classes/classes/classPopup", "classes/classPopup"}, method = RequestMethod.GET)
    public ModelAndView getClassesForPopup(HttpServletRequest request) {
        List<ClassEntity> classes = classService.getClassList();
        ModelAndView view = new ModelAndView("/classes/classPop");
        view.addObject("classes", classes);
        view.addObject("pageTitle", "Classes");
        return  view;
    }
    
    @RequestMapping(value = {"/removeclass/classes/add", "/classes/add"}, method = RequestMethod.GET)
    public String addEditClasses(HttpServletRequest request) {
        return "classes/addClass";
    }
    
    @RequestMapping(value = {"/classes/save","/user/classes/save"}, method = RequestMethod.POST)
    public ModelAndView saveClass(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
         String  message =  addUpdateClass(request);    
         //redirectAttributes.addFlashAttribute("message", message);
         //return new RedirectView("/classes/add", true);
         return getClasses(null);
    }
    
     @RequestMapping(value = {"/classes/{id}", "/user/classes/{id}"}, method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        ModelAndView view = new ModelAndView("classes/classDetail");
        ClassEntity classEntity = classService.getClass(id);
        view.addObject("classes", classEntity);
        return view;
        
    }    
    
    @RequestMapping(value = {"/editclassform/{id}", "/classes/editclassform/{id}"}, method = RequestMethod.GET)
    public ModelAndView getEditClass(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        ModelAndView view = new ModelAndView("classes/editclass");
        ClassEntity classEntity = classService.getClass(id);
        view.addObject("classes", classEntity);
        return view;
        
    }    
    
     @RequestMapping(value = {"/updateclass","/editclassform/updateclass"}, method = RequestMethod.POST)
    public RedirectView updateClass(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
         String  message =  updateClassFunction(request);    
         redirectAttributes.addFlashAttribute("message", message);
         return new RedirectView("/classes", true);
    }
    
    @RequestMapping(value = {"/removeclass/{id}", "/classes/removeclass/{id}"}, method = RequestMethod.GET)
    public ModelAndView removeClass(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";   
        try{
        classService.removeClass(id);
        return getClasses(null);
        }catch(Exception e){
            ModelAndView view = new ModelAndView("classes/results");
            String message = "Remove unsuccessful! You must remove successors of this class first";
            view.addObject("message", message);
            return view;
        }
        
    }
    
    
      //private method to add add and update users
    private String addUpdateClass(HttpServletRequest request){
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("description"),
                      prerequestie = request.getParameter("prerequestie"),
                     price = request.getParameter("price");                    
                     
                 if(name.isEmpty())
                     message.add("Product name is required");
                 if(price.isEmpty())
                     message.add("Price is required");
                
                   
                 if(message.isEmpty()){
                     ClassEntity classEntity = new ClassEntity(name,description,Double.parseDouble(price));
                     String[] split = prerequestie.split(",");
                     for(int i = 0; i < split.length; i++){
                         if(split[i].trim().length() != 0)
                         {
                            Long id = Long.parseLong(split[i]);
                            ClassEntity preReq = classService.getClass(id);
                            preReq.addPrerequisteClass(classEntity);
                            classEntity.addPrerequisteClasse(preReq);
                            
                         }
                     }
                     
                     boolean saved = classService.saveClass(classEntity);
                    
                     if(saved)
                       message.add("Successfully saved");
                     else
                       message.add("Class saving unsuccessful");
                   }
             return message.toString();
    }   
    
      //private method to add add and update users
    private String updateClassFunction(HttpServletRequest request){
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("description"),
                      prerequestie = request.getParameter("prerequestie"),
                     price = request.getParameter("price"),
                      id = request.getParameter("id");
                     
                     
                 if(name.isEmpty())
                     message.add("Product name is required");
                 if(price.isEmpty())
                     message.add("Price is required");
                
                   
                 if(message.isEmpty()){
                     ClassEntity classEntity = new ClassEntity(name,description,Double.parseDouble(price));
                     classEntity.setId(Long.parseLong(id));
                     String[] split = prerequestie.split(",");
                     for(int i = 0; i < split.length; i++){
                         if(split[i].trim().length() != 0)
                         {
                            Long id2 = Long.parseLong(split[i]);
                            ClassEntity preReq = classService.getClass(id2);
                            preReq.addPrerequisteClass(classEntity);
                            classEntity.addPrerequisteClasse(preReq);
                            
                         }
                     }
                     
                     
                     boolean updated = classService.updateClass(classEntity);
                    
                     if(updated)
                       message.add("Successfully updated");
                     else
                       message.add("Class update unsuccessful");
                   }
             return message.toString();
    }   
}
