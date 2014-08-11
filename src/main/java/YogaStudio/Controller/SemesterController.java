/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.SemesterEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.ProductService;
import YogaStudio.service.SemesterService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class SemesterController {
    @Autowired
    private SemesterService semesterService; 
    
    @RequestMapping(value = {"/semesters","/user/semesters"}, method = RequestMethod.GET)
    public ModelAndView getSemesters(HttpServletRequest request) {
        List<SemesterEntity> semesters = semesterService.getSemesterList();
        ModelAndView view = new ModelAndView("/semesters/semester");
        view.addObject("semesters", semesters);
        view.addObject("pageTitle", "Semesters");
        return  view;
    }
    
    @RequestMapping(value = {"/removesemester/semesters/add", "/semesters/add"}, method = RequestMethod.GET)
    public String addEditSemesters(HttpServletRequest request) {
        return "semesters/addSemester";
    }
    
    @RequestMapping(value = {"/semesters/save","/user/semesters/save"}, method = RequestMethod.POST)
    public ModelAndView saveSemester(HttpServletRequest request,final RedirectAttributes redirectAttributes) throws ParseException {
         //String  message =  addUpdateClass(request);    
         //redirectAttributes.addFlashAttribute("message", message);
         //return new RedirectView("/classes/add", true);
         String semesterName = request.getParameter("semesterName");
         String startDate = request.getParameter("startDate");
         String endDate = request.getParameter("endDate");
         
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         
         SemesterEntity semesterEntity = new SemesterEntity(semesterName, formatter.parse(startDate), formatter.parse(endDate));
         semesterService.saveSemester(semesterEntity);
         
         return getSemesters(null);
    }
    
     @RequestMapping(value = {"/semesters/{id}", "/user/semesters/{id}"}, method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        ModelAndView view = new ModelAndView("semesters/semesterDetail");
        SemesterEntity semesterEntity = semesterService.getSemester(id);
        view.addObject("semester", semesterEntity);
        return view;
        
    }    
    
    @RequestMapping(value = {"/editsemesterform/{id}", "/semesters/editsemesterform/{id}"}, method = RequestMethod.GET)
    public ModelAndView getEditSemester(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        ModelAndView view = new ModelAndView("semesters/editsemester");
        SemesterEntity semesterEntity = semesterService.getSemester(id);
        view.addObject("semester", semesterEntity);
        return view;
        
    }    
    
    @RequestMapping(value = {"/updatesemester","/editsemesterform/updatesemester"}, method = RequestMethod.POST)
    public RedirectView updateSemester(HttpServletRequest request,final RedirectAttributes redirectAttributes) throws ParseException {
         //String  message =  updateClassFunction(request);    
         //redirectAttributes.addFlashAttribute("message", message);
        
         String semesterName = request.getParameter("semesterName");
         String startDate = request.getParameter("startDate");
         String endDate = request.getParameter("endDate");
         String id = request.getParameter("id");
         
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         
         SemesterEntity semesterEntity = new SemesterEntity(semesterName, formatter.parse(startDate), formatter.parse(endDate));
         semesterEntity.setId(Long.parseLong(id));
         semesterService.updateSemester(semesterEntity);
         
         return new RedirectView("/semesters", true);
    }
    
    @RequestMapping(value = {"/removesemester/{id}", "/semesters/removesemester/{id}"}, method = RequestMethod.GET)
    public ModelAndView removeSemester(@PathVariable Long id, Model model) throws ParseException {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";  
        try{
        semesterService.removeSemester(id);
        return getSemesters(null);
        }catch(Exception e){
            ModelAndView view = new ModelAndView("semesters/results");
            String message = "Remove unsuccessful! You must remove sections of this semester first";
            view.addObject("message", message);
            return view;        
        }
    }
}
