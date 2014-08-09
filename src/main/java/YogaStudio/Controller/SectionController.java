/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.ScheduleEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.SemesterEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.SectionService;
import YogaStudio.service.SemesterService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class SectionController {
    @Autowired
    private SectionService sectionService; 
    
    @Autowired
    private ClassService classService; 
    
    @Autowired
    private SemesterService semesterService; 
    
    @RequestMapping(value = {"/sections","/user/sections"}, method = RequestMethod.GET)
    public ModelAndView getAllSections(HttpServletRequest request) {
        List<SectionEntity> sections = sectionService.getAllSections();
        ModelAndView view = new ModelAndView("/section/section");
        view.addObject("sections", sections);
        view.addObject("pageTitle", "Sections");
        return  view;
    }
    
    @RequestMapping(value = "/section/add", method = RequestMethod.GET)
    public ModelAndView addSectionPage(HttpServletRequest request) {        
        ModelAndView view = new ModelAndView("/section/addSection");        
        view.addObject("pageTitle", "Sections");
        return  view;
    }
    
     @RequestMapping(value = {"section/section/sectionPopup"}, method = RequestMethod.GET)
    public ModelAndView getClassesForPopup(HttpServletRequest request) {
        List<ClassEntity> classes = classService.getClassList();
        ModelAndView view = new ModelAndView("/section/sectionPop");
        view.addObject("classes", classes);
        view.addObject("pageTitle", "Classes");
        return  view;
    }
        
    @RequestMapping(value = {"section/section/semesterPopup"}, method = RequestMethod.GET)
    public ModelAndView getSemestersForPopup(HttpServletRequest request) {
        List<SemesterEntity> semesters = semesterService.getAllSemester();
        ModelAndView view = new ModelAndView("/section/semesterPop");
        view.addObject("semesters", semesters);
        view.addObject("pageTitle", "Semesters");
        return  view;
    }
    
    @RequestMapping(value = {"/section/save","/user/section/save"}, method = RequestMethod.POST)
    public RedirectView saveSection(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
        try{
         String  message =  addUpdateSection(request);    
         redirectAttributes.addFlashAttribute("message", message);
        }catch(Exception ex){}
         return new RedirectView("/section/add", true);
    }
    
    @RequestMapping(value = {"/section/{id}", "/user/section/{id}"}, method = RequestMethod.GET)
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionService.getSection(id));
        return "section/sectionDetail";
    }    
    @RequestMapping(value = {"../section/remove_section/{id}","/section/remove_section/{id}","/user/section/remove_section/{id}"}, method = RequestMethod.GET)
    public RedirectView removeSection(HttpServletRequest request,@PathVariable Long Id, final RedirectAttributes redirectAttributes) {
        try{
         Boolean  message =  sectionService.deleteSection(Id);
         if(message)             
            redirectAttributes.addFlashAttribute("Remove successfull", message);
        }catch(Exception ex){}
         return new RedirectView("/section/", true);
    }
    
    @RequestMapping(value = {"/section/edit_section/{id}","/user/section/edit_section/{id}"}, method = RequestMethod.GET)
    public String editSection(HttpServletRequest request,@PathVariable Long Id, Model model) {
        model.addAttribute("section", sectionService.getSection(Id));
        return "section/editSection";
    }
    
       //private method to add add and update users
    private String addUpdateSection(HttpServletRequest request) throws ParseException{
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("descripton"),
                      classToAssign = request.getParameter("classToAssign"),
                      semesterToAssign = request.getParameter("semesterToAssign"),
                      location = request.getParameter("location"),
                      classLimit = request.getParameter("classLimit"),
                      DayOfWeek = request.getParameter("DayOfWeek"),
                      startTime = request.getParameter("startTime"),
                      endTime = request.getParameter("endTime");                    
                     
                 if(name.isEmpty())
                     message.add("Product name is required");
                 if(classToAssign.isEmpty())
                     message.add("Class is required");
                
                 if(location.isEmpty())
                     message.add("Location is required");
                
                  if(location.isEmpty())
                     message.add("Location is required");
                
                   
                 if(message.isEmpty()){
                     SectionEntity sectionEntity = new SectionEntity(name,description,location, Integer.parseInt(classLimit));
                     SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                     Date ds = df.parse(startTime); 
                     Date de = df.parse(endTime); 
                     
                     SemesterEntity semesterEntity = semesterService.getSemester(Long.parseLong(semesterToAssign));
                     ScheduleEntity scheduleEntity = new ScheduleEntity(Integer.parseInt(DayOfWeek),ds,de);
                     sectionEntity.addSchedule(scheduleEntity);
                     ClassEntity classEnttiy = classService.getClass(Long.parseLong(classToAssign));
                     sectionEntity.setClassEntity(classEnttiy);
                     sectionEntity.setSemester(semesterEntity);
                     boolean saved = sectionService.saveSection(sectionEntity);
                     classEnttiy.addSection(sectionEntity);
                     saved = classService.saveClass(classEnttiy);
                     if(saved)
                       message.add("Successfully saved");
                     else
                       message.add("Section saving unsuccessful");
                   }
             return message.toString();
    }   
    
}
