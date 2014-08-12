/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.UserEntity;
import YogaStudio.domain.WaiverEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.CustomerService;
import YogaStudio.service.FacultyService;
import YogaStudio.service.ProductService;
import YogaStudio.service.SectionService;
import YogaStudio.service.UserService;
import YogaStudio.service.WaiverService;
import YogaStudio.util.Util;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @author sMazumder
 */
@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassService classService;
    @Autowired
    private WaiverService waiverService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private SectionService sectionService;

    @RequestMapping(value = {"/faculty", "/faculty/faculty"}, method = RequestMethod.GET)
    public ModelAndView getAllFaculty(HttpServletRequest request) {
        List<FacultyEntity> facultys = facultyService.getAllFaculty();
        ModelAndView view = new ModelAndView("/faculty/faculty");
        view.addObject("facultys", facultys);
        view.addObject("pageTitle", "Faculty");
        return view;
    }

    @RequestMapping(value = {"/faculty/{id}", "/user/faculty/{id}"}, method = RequestMethod.GET)
    public String getFaculty(@PathVariable Long id, Model model) {
        model.addAttribute("faculty", facultyService.getFaculty(id));
        model.addAttribute("sections", sectionService.getAllSections());
        return "faculty/facultyDetail";
    }

    @RequestMapping(value = {"/faculty/editFaculty/{id}", "/user/faculty/editFaculty/{id}"}, method = RequestMethod.GET)
    public String editFaculty(@PathVariable Long id, Model model) {
        model.addAttribute("faculty", facultyService.getFaculty(id));
        return "faculty/editFaculty";
    }

    @RequestMapping(value = {"/faculty/assignFaculty/{id}/{value}", "/user/faculty/assignFaculty/{id}/{value}"}, method = RequestMethod.GET)
    public String assignFaculty(@PathVariable("id") String id, @PathVariable("value") String value, Model model) {
        FacultyEntity faculty = facultyService.getFaculty(Long.parseLong(id));
        SectionEntity section = sectionService.getSection(Long.parseLong(value));
        faculty.addSection(section);
        facultyService.add(faculty);
        model.addAttribute("Message", "Successfull");

        return "faculty/faculty";
    }

    @RequestMapping(value = {"/removeFaculty/{id}", "/YogaStudio/faculty/removeFaculty/{id}", "/faculty/removeFaculty/{id}"}, method = RequestMethod.GET)
    public RedirectView removeFaculty(HttpServletRequest request, @PathVariable Long id, final RedirectAttributes redirectAttributes) {
        Object message = null;
        RedirectView view = new RedirectView();
        if (facultyService.removeFaculty(id)) {
            redirectAttributes.addFlashAttribute("Remove successfull", message);
        } else {
            redirectAttributes.addFlashAttribute("Remove not successfull", message);
        }
        view.setUrl(request.getContextPath() + "/faculty");
        return view;//"redirect:/";
    }

    @RequestMapping(value = {"/faculty/add", "/user/faculty/add"}, method = RequestMethod.GET)
    public String faculty(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String message = "";
        redirectAttributes.addFlashAttribute("message", message);
        return "faculty/addFaculty";
    }

    @RequestMapping(value = {"faculty/save", "/userfaculty/save"}, method = RequestMethod.POST)
    public RedirectView register(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String message = addFaculty(request);
        redirectAttributes.addFlashAttribute("message", message);
        return new RedirectView("/faculty", true);//"redirect:/";
    }

    private String addFaculty(HttpServletRequest request) {
        List message = new ArrayList();
        String firstName = request.getParameter("name"),
                email = request.getParameter("email"),
                exp = request.getParameter("exp"),
                special = request.getParameter("special"),
                street = request.getParameter("street"),
                city = request.getParameter("city"),
                country = request.getParameter("country"),
                zip = request.getParameter("zip"),
                state = request.getParameter("state"),
                contactNumber = request.getParameter("contactNum"),
                dateOfBirth = request.getParameter("dateOfBirth"),
                username = request.getParameter("username");

        if (firstName.isEmpty()) {
            message.add("First name is required");
        }
        if (email.isEmpty()) {
            message.add("Email is required");
        }
        if (exp.isEmpty()) {
            message.add("Experience is required");
        }
        if (special.isEmpty()) {
            message.add("Speciality is required");
        }

        if (username.isEmpty()) {
            message.add("Username is required");
        }

        if (message.isEmpty()) {
            String authority = "ROLE_FACULTY";
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            try {
                date = formatter.parse(dateOfBirth);
            } catch (Exception ex) {
            }
            String password = Util.generatePassword();

            FacultyEntity facultyEntity = new FacultyEntity(special, Integer.parseInt(exp), username, password, firstName, email, authority, date, Long.parseLong(contactNumber), street, city, state, country, Long.parseLong(zip));
            boolean saved = facultyService.add(facultyEntity);

            if (saved) {
                message.add("Registration successful saved");
            } else {
                message.add("Registration unsuccessful");
            }
        }
        return message.toString();
    }

    @RequestMapping(value = "/waiver/viewWaiversByFA", method = RequestMethod.GET)
    public ModelAndView viewWaivers(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Object object = auth.getPrincipal();
        String password = ((UserDetails) object).getPassword();
        System.out.println("Faculty :" + name + "  Password:" + password);
        UserEntity user = userService.findUser("faculty", "faculty");
        FacultyEntity faculty = facultyService.getFaculty(user.getId());
        List<WaiverEntity> waivers = waiverService.getWaiversByFaId(user.getId());
        ModelAndView view = new ModelAndView("/waiver/viewWaiversByFA");
        view.addObject("waivers", waivers);
        view.addObject("faculty", faculty.getId());
        // view.addObject("pageTitle", "Waivers");
        //String message = "Waiver Request:";
        //view.addFlashAttribute("message", message);
        return view;
    }

    @RequestMapping(value = "/waiver/viewWaiversByFA/{id}", method = RequestMethod.GET)
    public ModelAndView viewWaiversByFA(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        // FacultyEntity faculty = facultyService.getFaculty(Long.valueOf(id));
        List<WaiverEntity> waivers = waiverService.getWaiversByFaId(Long.valueOf(id));
        ModelAndView view = new ModelAndView("/waiver/viewWaiversByFA");
        view.addObject("waivers", waivers);
        view.addObject("faculty", Long.valueOf(id));
        // view.addObject("pageTitle", "Waivers");
        String message = "Waiver Request:";
        redirectAttributes.addFlashAttribute("message", message);
        return view;
    }

    @RequestMapping(value = {"/waiver/waiverDetails/{id}","/waiverDetails/{id}"}, method = RequestMethod.GET)
    public ModelAndView waiverDetails(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        //FacultyEntity faculty = facultyService.getFaculty(Long.valueOf(id));
        WaiverEntity waiver = waiverService.getWaiver(Long.valueOf(id));
        ModelAndView view = new ModelAndView("/waiver/waiverDetails");
        view.addObject("waiver", waiver);
        // view.addObject("pageTitle", "Waivers");
        String message = "Waiver Request:";
        redirectAttributes.addFlashAttribute("message", message);
        return view;
    }

    @RequestMapping(value = {"/waiver/respondOnWaiver/{id}","/respondOnWaiver/{id}"}, method = RequestMethod.POST)
    public RedirectView respondOnWaiver(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        //if "name" value equals approve redirect to viewWaivers by updating status
        //else show reject popup with comments and redirect after submitting and updating to DB to viewWaivers
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            Object object = auth.getPrincipal();

            String password = ((UserDetails) object).getPassword();
            System.out.println("Faculty :" + name + "  Password:" + password);
        UserEntity faculty = userService.findUser("faculty", "faculty");
        //UserEntity user = userService.findUser("devika", "devika");
        System.out.println("Faculty Found:" + faculty);
        RedirectView view = new RedirectView();
        String message = "Successfully updated Waiver status.";
        System.out.println("Waiver Id:" + Long.valueOf(id) + "  name:" + request.getParameter("response"));
        if (faculty == null) {
            message = "Failed to Update Waiver, Faculty is not valid.";
            return view;
        }
        // WaiverEntity waiver = waiverService.getWaiver(Long.valueOf(id));
        String response = request.getParameter("response");
        boolean status = false;
        if (response.contains("Approve")) {
            status = waiverService.approveWaiverRequest(Long.valueOf(id), request.getParameter("comments"));
        } else if (response.contains("Reject")) {
            status = waiverService.rejectWaiverRequest(Long.valueOf(id), request.getParameter("comments"));
        }
        if (status == false) {
            message = "!!! Failed updating the waiver.";
        }
        view.setUrl(request.getContextPath() + "/waiver/viewWaiversByFA/" + faculty.getId());
        redirectAttributes.addFlashAttribute("message", message);
        return view;//"redirect:/";       
        //redirectAttributes.addFlashAttribute("message", "Profile not found.");
        //return new ModelAndView("/user/editProfile", "Profile", "not found");
        //return "redirect:/";
    }

}
