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
import YogaStudio.service.ScheduleService;
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
    
    @Autowired
    private ScheduleService scheduleService; 
    private Object message;
    
    @RequestMapping(value = {"/sections","/user/sections","sections","../sections"}, method = RequestMethod.GET)
    public ModelAndView getAllSections(HttpServletRequest request) {
        List<SectionEntity> sections = sectionService.getAllSections();
        ModelAndView view = new ModelAndView("/section/section");
        view.addObject("sections", sections);
        view.addObject("pageTitle", "Sections");
        return  view;
    }
    
    @RequestMapping(value = {"/section/add","../section/add","section/add"}, method = RequestMethod.GET)
    public ModelAndView addSectionPage(HttpServletRequest request) {        
        ModelAndView view = new ModelAndView("/section/addSection");   
        List<ClassEntity> classes = classService.getClassList();
        List<SemesterEntity> semesters = semesterService.getSemesterList();
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        view.addObject("classes", classes);
        view.addObject("semesters", semesters);
         view.addObject("schedules", schedules);
        view.addObject("pageTitle", "Sections");
        return  view;
    }    
    
    @RequestMapping(value = {"/section/add/{id}","../section/add/{id}","section/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView addSectionPageForClass(HttpServletRequest request, @PathVariable Long id) {        
        ModelAndView view = new ModelAndView("/section/addSection");   
        List<ClassEntity> classes = classService.getClassList();
        List<SemesterEntity> semesters = semesterService.getSemesterList();
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        //view.addObject("classID", id);        
        view.addObject("classID", id.toString());        
        
        view.addObject("classes", classes);
        view.addObject("semesters", semesters);
         view.addObject("schedules", schedules);
        view.addObject("pageTitle", "Sections");
        return  view;
    } 
    
    @RequestMapping(value = {"/section/add/","../section/add/","section/add/"}, method = RequestMethod.GET)
    public ModelAndView addSectionPage(HttpServletRequest request, @PathVariable Long id) {        
        ModelAndView view = new ModelAndView("/section/addSection");   
        List<ClassEntity> classes = classService.getClassList();
        List<SemesterEntity> semesters = semesterService.getSemesterList();
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        
        view.addObject("classes", classes);
        view.addObject("semesters", semesters);
         view.addObject("schedules", schedules);
        view.addObject("pageTitle", "Sections");
        return  view;
    }    
    
    
     @RequestMapping(value = {"section/add/section/addSchedule","section/addSchedule","../section/addSchedule","/section/addSchedule", "/section/section/addSchedule"}, method = RequestMethod.GET)
    public ModelAndView addSchedulePage(HttpServletRequest request) {        
        ModelAndView view = new ModelAndView("/section/addSchedule"); 
        view.addObject("pageTitle", "Schedule");
        return  view;
    }    
     
    @RequestMapping(value = {"section/add/save","section/save","../section/save","/section/save","/user/section/save"}, method = RequestMethod.POST)
    public ModelAndView saveSection(HttpServletRequest request) {
        try{
            ModelAndView view = new ModelAndView();
            
            String  message =  addUpdateSection(request);
           }catch(Exception ex){
           }
         return new ModelAndView("/section/section","message",message);
    }
    
    @RequestMapping(value = {"section/editSection/save"}, method = RequestMethod.POST)
    public ModelAndView saveEditedSection(HttpServletRequest request) {
        try{
            ModelAndView view = new ModelAndView();
            
            String  message =  addEditSection(request);
           }catch(Exception ex){
           }
         return new ModelAndView("/section/section","message",message);
    }
    
    @RequestMapping(value = {"section/add/section/saveSchedule","section/saveSchedule","../section/saveSchedule","/section/saveSchedule","/user/section/saveSchedule"}, method = RequestMethod.POST)
    public RedirectView saveSchedule(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
        try{
            String  message =  addUpdateSchedule(request);    
            redirectAttributes.addFlashAttribute("message", message);
           }catch(Exception ex){
                redirectAttributes.addFlashAttribute("message", ex.toString());
           }
         return new RedirectView("/section/addSchedule", true);
    }
    
    @RequestMapping(value = {"section/{id}","../section/{id}","/section/{id}", "/user/section/{id}"}, method = RequestMethod.GET)
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionService.getSection(id));
        return "section/sectionDetail";
    }    
   
    @RequestMapping(value = {"section/removeSection/{id}","../section/removeSection/{id}","/section/removeSection/{id}","/removeSection/{id}"}, method = RequestMethod.GET)
     public RedirectView removeSection(HttpServletRequest request,@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        Object message = null; 
         RedirectView view = new RedirectView();                
         if(sectionService.deleteSection(id))
         {
              redirectAttributes.addFlashAttribute("Remove successfull", message);
         }
         else
         {
              redirectAttributes.addFlashAttribute("Remove not successfull", message);
         }
         view.setUrl(request.getContextPath()+"/section/section");         
         return view;//"redirect:/";
    }
 
    @RequestMapping(value = {"faculty/editSection/{id}","../faculty/editSection/{id}","/faculty/editSection/{id}", "/user/faculty/editSection/{id}"}, method = RequestMethod.GET)
    public String editSection(@PathVariable Long id,Model model) {        
        model.addAttribute("faculty", sectionService.getSection(id));
        return "faculty/editFaculty";        
    } 
    
    @RequestMapping(value = {"section/editSection/{id}","../section/editSection/{id}","/section/editSection/{id}", "/user/section/editSection/{id}"}, method = RequestMethod.GET)
    public ModelAndView editSectionSection(@PathVariable Long id) {        
        ModelAndView view = new ModelAndView("section/editSection");   
        List<ClassEntity> classes = classService.getClassList();
        List<SemesterEntity> semesters = semesterService.getSemesterList();
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
       view.addObject("section", sectionService.getSection(id));
        view.addObject("classes", classes);
        view.addObject("semesters", semesters);
         view.addObject("schedules", schedules);
        return view;        
    } 
     
       //private method to add add and update users
    private String addUpdateSection(HttpServletRequest request) throws ParseException{
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("descripton"),
                      classToAssign = request.getParameter("class1"),
                      semesterToAssign = request.getParameter("semester1"),
                      location = request.getParameter("location"),
                      classLimit = request.getParameter("classLimit"),
                      schedules = request.getParameter("schedule1")
                     ;                    
                     
                 if(name.isEmpty())
                     message.add("Product name is required. ");
                 if(classToAssign.isEmpty())
                     message.add("Class is required");
                 if(classToAssign.isEmpty())
                     message.add("Semester is required. ");
                
                 if(location.isEmpty())
                     message.add("Location is required. ");               
                 
                  if(classLimit.trim().length() == 0 ){
                      message.add("Class limit is required. ");
                  }
                  try{
                      Integer.parseInt(classLimit);
                  }
                  catch(Exception ex){
                       message.add("Invalid class limit. ");
                  }
                
                   
                 if(message.isEmpty()){                     
                     
                     SectionEntity sectionEntity = new SectionEntity(name,description,location, Integer.parseInt(classLimit));
                     SimpleDateFormat df = new SimpleDateFormat("HH:mm");                     
                     SemesterEntity semesterEntity = semesterService.getSemester(Long.parseLong(semesterToAssign));
                     
                     ClassEntity classEnttiy = classService.getClass(Long.parseLong(classToAssign));
                     
                     sectionEntity.setClassEntity(classEnttiy);
                     sectionEntity.setSemester(semesterEntity);
                     
                     String[] splits = schedules.split(",");
                     for(String split: splits){
                         if(split.trim().length() != 0)
                         {
                           ScheduleEntity schedule = scheduleService.getSchedule(Long.parseLong(split));
                           schedule.addSection(sectionEntity);
                           sectionEntity.addSchedule(schedule);
                         }
                     }
                     
                     boolean saved = sectionService.saveSection(sectionEntity);
                     classEnttiy.addSection(sectionEntity);
                     saved = classService.saveClass(classEnttiy);
                     if(saved)
                       message.add("Successfully saved ");
                     else
                       message.add("Section saving unsuccessful");
                   }
             return message.toString();
    }  
    
    private String addEditSection(HttpServletRequest request) throws ParseException{
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("descripton"),
                      classToAssign = request.getParameter("class1"),
                      semesterToAssign = request.getParameter("semester1"),
                      location = request.getParameter("location"),
                      classLimit = request.getParameter("classLimit"),
                      schedules = request.getParameter("schedule1"),
                      section = request.getParameter("sectionID")
                     ;                    
                     
                 if(name.isEmpty())
                     message.add("Product name is required. ");
                 if(classToAssign.isEmpty())
                     message.add("Class is required");
                 if(classToAssign.isEmpty())
                     message.add("Semester is required. ");
                
                 if(location.isEmpty())
                     message.add("Location is required. ");               
                 
                  if(classLimit.trim().length() == 0 ){
                      message.add("Class limit is required. ");
                  }
                  try{
                      Integer.parseInt(classLimit);
                  }
                  catch(Exception ex){
                       message.add("Invalid class limit. ");
                  }
                
                   
                 if(message.isEmpty()){                     
                     
                     SectionEntity sectionEntity = sectionService.getSection(Long.parseLong(section));
                     SimpleDateFormat df = new SimpleDateFormat("HH:mm");                     
                     SemesterEntity semesterEntity = semesterService.getSemester(Long.parseLong(semesterToAssign));
                     
                     ClassEntity classEnttiy = classService.getClass(Long.parseLong(classToAssign));
                     
                     sectionEntity.setClassEntity(classEnttiy);
                     sectionEntity.setSemester(semesterEntity);
                     
                     String[] splits = schedules.split(",");
                     for(String split: splits){
                         if(split.trim().length() != 0)
                         {
                           ScheduleEntity schedule = scheduleService.getSchedule(Long.parseLong(split));
                           schedule.addSection(sectionEntity);
                           sectionEntity.addSchedule(schedule);
                         }
                     }
                     
                     boolean saved = sectionService.saveSection(sectionEntity);
                     classEnttiy.addSection(sectionEntity);
                     saved = classService.saveClass(classEnttiy);
                     if(saved)
                       message.add("Successfully saved ");
                     else
                       message.add("Section saving unsuccessful");
                   }
             return message.toString();
    }  
    
    
     private String addUpdateSchedule(HttpServletRequest request) throws ParseException{
          List message = new ArrayList();
                String DayOfWeek = request.getParameter("DayOfWeek"),
                      startTime = request.getParameter("startTime"),
                      endTime = request.getParameter("endTime");
                 if(DayOfWeek.isEmpty())
                     message.add("Day of week is required. ");
                 if(startTime.isEmpty())
                     message.add("Start time is required");
                 if(DayOfWeek.isEmpty())
                     message.add("End time is required. ");
                   
                 if(message.isEmpty()){                     
                     
                     SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                     Date ds = df.parse(startTime); 
                     Date de = df.parse(endTime); 
                    
                     ScheduleEntity scheduleEntity = new ScheduleEntity(Integer.parseInt(DayOfWeek),ds,de);
                     Boolean saved = scheduleService.saveSchedule(scheduleEntity);
                     if(saved)
                       message.add("Successfully saved ");
                     else
                       message.add("Section saving unsuccessful");
                   }
             return message.toString();
    }  
    
}
