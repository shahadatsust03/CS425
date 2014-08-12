/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.Controller;

import YogaStudio.domain.ClassEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.EnrollmentEntity;
import YogaStudio.domain.PaymentEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.SectionEntity;
import YogaStudio.domain.SemesterEntity;
import YogaStudio.domain.UserEntity;
import YogaStudio.service.ClassService;
import YogaStudio.service.CustomerService;
import YogaStudio.service.EnrollmentService;
import YogaStudio.service.PaymentService;
import YogaStudio.service.ProductService;
import YogaStudio.service.SectionService;
import YogaStudio.service.SemesterService;
import YogaStudio.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class EnrollmentController {

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/enrollments", "/user/enrollments"}, method = RequestMethod.GET)
    public ModelAndView getSemesters(HttpServletRequest request) {
        List<SemesterEntity> semesters = semesterService.getSemesterList();
        ModelAndView view = new ModelAndView("/enrollments/semester");
        view.addObject("semesters", semesters);
        view.addObject("pageTitle", "Semesters");
        return view;
    }

    @RequestMapping(value = {"/enrollmentsemesters/{id}", "/user/enrollmentsemesters/{id}"}, method = RequestMethod.GET)
    public ModelAndView getSections(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        //List<SectionEntity> sections = sectionService.getAllSections();
        List<SectionEntity> sections = sectionService.getAllSections(id);
        ModelAndView view = new ModelAndView("enrollments/section");
        view.addObject("sections", sections);
        return view;

    }

    @RequestMapping(value = {"/enrollmentsections/{id}", "/user/enrollmentsections/{id}"}, method = RequestMethod.GET)
    public ModelAndView getSectionDetail(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        SectionEntity sectionEntity = sectionService.getSection(id);
        ModelAndView view = new ModelAndView("enrollments/sectionDetail");
        view.addObject("section", sectionEntity);
        return view;

    }

    @RequestMapping(value = {"/processenrollment/{sectionid}", "/user/processenrollment/{id}"}, method = RequestMethod.GET)
    public ModelAndView processEnrollment(@PathVariable Long sectionid, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
           // Object object = auth.getPrincipal();
            //UserDetails userDetails = (UserDetails) object; //
            //String password = userDetails.getPassword(); // String password = ((UserDetails)object).getPa

            //System.out.println("User :" + name+"  Password:"+password);
            //UserEntity user = userService.findUser(name, password);
            UserEntity user = userService.findUser(name);
            CustomerEntity customerEntity = customerService.getCustomer(user.getId());
            //UserEntity user = userService.findUser("samuel", "samuel");
            //System.out.println("User Found:" + user);

            if (user != null) {
                //process enrollment
                Long customerId = user.getId();
                SectionEntity sectionEntity = sectionService.getSection(sectionid);
                boolean spaceAvailable = sectionService.checkSpace(sectionEntity);

                //Check if customer has already enrolled
                if (sectionService.isEnrolled(customerId, sectionid) == true) {

                    //if some one has unenrolled
                    if (sectionEntity.getTotalEnrollment() < sectionEntity.getClassLimit()) {
                        //if the current customer is at front of waiting list
                        if (customerEntity.getId() == enrollmentService.getCustoemrAtFrontOfWaitingList(sectionEntity.getId()).getId()) {
                            //enroll him/her

                            EnrollmentEntity alreadyExistingEnrollment = enrollmentService.getEnrollment(customerEntity.getId(), sectionEntity.getId());
                            boolean prerequisteMet = enrollmentService.checkPrerequisites(customerId, sectionid);

                            if (prerequisteMet == true) {

                                //validate credit card
                                if (user.getCreditCard() != null)//credit card is valid
                                {
                                    ClassEntity classEntity = sectionEntity.getClassEntity();

                                    PaymentEntity paymentEntity = new PaymentEntity();

                                    paymentEntity.setAmount(classEntity.getFee());
                                    paymentEntity.setPaymentDate(new Date());
                                    paymentEntity.setCreditcard(user.getCreditCard());
                                    paymentEntity.setClasses(classEntity);

                                    classEntity.addPayments(paymentEntity);

                                    paymentService.savePayment(paymentEntity);
                                    classService.saveClass(classEntity);

                                    //EnrollmentEntity enrollmentEntity = new EnrollmentEntity(Byte.parseByte("0"), customerEntity, sectionEntity, classEntity);
                                    alreadyExistingEnrollment.setStatus(Byte.valueOf("0"));
                                    enrollmentService.saveEnrollment(alreadyExistingEnrollment);

                                    ModelAndView view = new ModelAndView("enrollments/results");
                                    view.addObject("message", "Enrollment successfully completed");
                                    return view;

                                } else {//credit card not valid

                                    ModelAndView view = new ModelAndView("enrollments/errorresults");
                                    view.addObject("message", "Invalid credit card. Enrollment unsuccessful!!");
                                    return view;
                                }

                            } else {//prerequisite not met  ** waiverneedconfirmation page
                                //Prerequisite not met, Do you want a waiver? 
                                // if yes to waiver, redirect to waiver form //*for now redirect to enrollment page
                                //if no to waiver, redirect to enrollments page, *
                                ModelAndView view = new ModelAndView("enrollments/waiverneedconfirmation");
                                view.addObject("class", sectionEntity.getClassEntity());
                                //view.addObject("customerId", user.getId());
                                return view;

                            }

                        ///////////////////////////
                        } else {
                            ModelAndView view = new ModelAndView("enrollments/errorresults");
                            String message = "You have already enrolled(beeen waitlisted) for " + sectionEntity.getSectionName();
                            view.addObject("message", message);
                            return view;
                        }

                    } else {
                        ModelAndView view = new ModelAndView("enrollments/errorresults");
                        String message = "You have already enrolled(beeen waitlisted) for " + sectionEntity.getSectionName();
                        view.addObject("message", message);
                        return view;
                    }
                }

                if (spaceAvailable == true) {

                    boolean prerequisteMet = enrollmentService.checkPrerequisites(customerId, sectionid);

                    if (prerequisteMet == true) {

                        //validate credit card
                        if (user.getCreditCard() != null)//credit card is valid
                        {
                            ClassEntity classEntity = sectionEntity.getClassEntity();

                            PaymentEntity paymentEntity = new PaymentEntity();

                            paymentEntity.setAmount(classEntity.getFee());
                            paymentEntity.setPaymentDate(new Date());
                            paymentEntity.setCreditcard(user.getCreditCard());
                            paymentEntity.setClasses(classEntity);

                            classEntity.addPayments(paymentEntity);

                            paymentService.savePayment(paymentEntity);
                            classService.saveClass(classEntity);

                            EnrollmentEntity enrollmentEntity = new EnrollmentEntity(Byte.parseByte("0"), customerEntity, sectionEntity, classEntity);

                            enrollmentService.saveEnrollment(enrollmentEntity);

                            ModelAndView view = new ModelAndView("enrollments/results");
                            view.addObject("message", "Enrollment successfully completed");
                            return view;

                        } else {//credit card not valid

                            ModelAndView view = new ModelAndView("enrollments/errorresults");
                            view.addObject("message", "Invalid credit card. Enrollment unsuccessful!!");
                            return view;
                        }

                    } else {//prerequisite not met  ** waiverneedconfirmation page
                        //Prerequisite not met, Do you want a waiver? 
                        // if yes to waiver, redirect to waiver form //*for now redirect to enrollment page
                        //if no to waiver, redirect to enrollments page, *
                        ModelAndView view = new ModelAndView("enrollments/waiverneedconfirmation");
                        view.addObject("class", sectionEntity.getClassEntity());
                        //view.addObject("customerId", user.getId());
                        return view;

                    }
                } else {//space not available  **waitinglistneedconfirmation page
                    //Space is not available. Do you want to be added to waiting list
                    //If yes to waiving list, add to waiving list and redirect to enrollment page *
                    //If no to waiving list, redirect to enrollment  page *

                    ModelAndView view = new ModelAndView("enrollments/waitinglistneedconfirmation");
                    view.addObject("section", sectionEntity);
                    return view;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            ModelAndView view = new ModelAndView("enrollments/errorresults");
            view.addObject("message", "Exception error!!");
            return view;
        }

        ModelAndView view = new ModelAndView("enrollments/errorresults");
        view.addObject("message", "Unkown error!!");
        return view;

    }

    @RequestMapping(value = {"/addtowaitinglist/{id}", "/user/addtowaitinglist/{id}"}, method = RequestMethod.GET)
    public ModelAndView addToWaitingList(@PathVariable Long id, Model model) {

        SectionEntity sectionEntity = sectionService.getSection(id);
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            //System.out.println("User :" + name+"  Password:"+password);
            UserEntity user = userService.findUser(name);
            CustomerEntity customerEntity = customerService.getCustomer(user.getId());
            if (user != null) {

                //Check if customer has already enrolled
                if (sectionService.isEnrolled(customerEntity.getId(), id) == true) {
                    ModelAndView view = new ModelAndView("enrollments/errorresults");
                    String message = "You have already enrolled(beeen waitlisted) for " + sectionEntity.getSectionName();
                    view.addObject("message", message);
                    return view;
                }

                EnrollmentEntity enrollmentEntity = new EnrollmentEntity(Byte.parseByte("1"), customerEntity, sectionEntity, sectionEntity.getClassEntity());

                enrollmentService.saveEnrollment(enrollmentEntity);

                ModelAndView view = new ModelAndView("enrollments/results");
                view.addObject("message", "You are successfully added to waiting list");
                return view;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ModelAndView view = new ModelAndView("enrollments/results");
            view.addObject("message", "Exception error!!");
            return view;
        }

        ModelAndView view = new ModelAndView("enrollments/errorresults");
        view.addObject("message", "Unkown error!!");
        return view;

    }

    @RequestMapping(value = {"/unenrollments", "/user/unenrollments"}, method = RequestMethod.GET)
    public ModelAndView getEnrolledSections(HttpServletRequest request) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            UserEntity user = userService.findUser(name);

            CustomerEntity customerEntity = customerService.getCustomer(user.getId());
            List<SectionEntity> sections = enrollmentService.getEnrolledSections(customerEntity.getId());

            ModelAndView view = new ModelAndView("unenrollments/enrolledsections");
            view.addObject("sections", sections);
            return view;

        } catch (Exception ex) {
            ex.printStackTrace();
            ModelAndView view = new ModelAndView("unenrollments/errorresults");
            view.addObject("message", "Exception error!!");
            return view;
        }
    }

    @RequestMapping(value = {"/unenrollmentsection/{id}", "/user/unenrollmentsection/{id}"}, method = RequestMethod.GET)
    public ModelAndView getEnrolledSectionDetail(@PathVariable Long id, Model model) {
        //model.addAttribute("classes", classService.getClass(id));
        //return "classes/classDetail";
        SectionEntity sectionEntity = sectionService.getSection(id);
        ModelAndView view = new ModelAndView("unenrollments/sectiondetail");
        view.addObject("section", sectionEntity);
        return view;

    }

    @RequestMapping(value = {"/processunenrollment/{sectionid}", "/user/processunenrollment/{id}"}, method = RequestMethod.GET)
    public ModelAndView processUnenrollment(@PathVariable Long sectionid, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            UserEntity user = userService.findUser(name);
            CustomerEntity customerEntity = customerService.getCustomer(user.getId());
            SectionEntity sectionEntity = sectionService.getSection(sectionid); //???
            EnrollmentEntity enrollmentEntity = enrollmentService.getEnrollment(customerEntity.getId(), sectionid);
            enrollmentService.removeEnrollment(enrollmentEntity);
            if (enrollmentService.waitingListIsEmpty(sectionid) == false) {
                CustomerEntity customerToNotify = enrollmentService.getCustoemrAtFrontOfWaitingList(sectionid);
                //EmailController emailController = new EmailController();
                //emailController.sendEmail(customerToNotify.getEmail(), "Please", "Space is now availabele for the section : " + sectionEntity.getSectionName() + " Please enroll as soon as possible");
            }
            ModelAndView view = new ModelAndView("unenrollments/results");
            view.addObject("message", "You have successfully unenrolled from " + sectionEntity.getSectionName());
            return view;

        } catch (Exception ex) {
            ex.printStackTrace();
            ModelAndView view = new ModelAndView("unenrollments/errorresults");
            view.addObject("message", "Exception error!!");
            return view;
        }

    }
    /*@RequestMapping(value = "/viewEnrollments", method = RequestMethod.GET)
    public ModelAndView viewEnrollments(HttpServletRequest request) {        
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            Object object = auth.getPrincipal();
            String password = ((UserDetails) object).getPassword();
            System.out.println("Customer :" + name + "  Password:" + password);
            UserEntity customer = userService.findUser("devika", "devika");
            CustomerEntity cust = customerService.get(customer.getId());
            List<EnrollmentEntity> enrollments = enrollmentService.getEnrollments(cust.getId());        
            ModelAndView view = new ModelAndView("/enrollments/viewEnrollments");
            view.addObject("enrollments", enrollments);
            view.addObject("pageTitle", "Enrollments");
            String message = "Enrollments List:";//updateProfile(request, view, id);
            //redirectAttributes.addFlashAttribute("message", message);
            return view;
    }*/
    
     /*@RequestMapping(value = "/viewEnrollments/{id}", method = RequestMethod.GET)
    public ModelAndView viewEnrollments(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        System.out.println("Id:" + Long.valueOf(id));
        CustomerEntity cust = customerService.get(Long.valueOf(id));
        List<EnrollmentEntity> enrollments = enrollmentService.getEnrollments(cust.getId());               
        ModelAndView view = new ModelAndView("/enrollments/viewEnrollments");
        view.addObject("enrollments", enrollments);
        view.addObject("pageTitle", "Enrollments");
        String message = "Enrollments List::";//updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
//         if (user != null) {                
//                return new ModelAndView("/user/requestWaiver", "user", user);
//            }
//        return new ModelAndView("/user/requestWaiver", "user", "not found");  
        return view;
    }
    */
     @RequestMapping(value = "/viewEnrollments", method = RequestMethod.GET)
    public ModelAndView viewEnrollments(HttpServletRequest request) {        
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            Object object = auth.getPrincipal();
            //String password = ((UserDetails) object).getPassword();
           // System.out.println("Customer :" + name + "  Password:" + password);
            UserEntity customer = userService.findUser(name);
            CustomerEntity cust = customerService.get(customer.getId());
            List<EnrollmentEntity> enrollments = enrollmentService.getEnrollments(cust.getId());        
            ModelAndView view = new ModelAndView("/enrollments/viewEnrollments");
            view.addObject("enrollments", enrollments);
            view.addObject("pageTitle", "Enrollments");
            //String message = "Enrollments List:";//updateProfile(request, view, id);
            //redirectAttributes.addFlashAttribute("message", message);
            return view;
    }
    
     @RequestMapping(value = "/viewEnrollments/{id}", method = RequestMethod.GET)
    public ModelAndView viewEnrollments(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        System.out.println("Id:" + Long.valueOf(id));
        CustomerEntity cust = customerService.get(Long.valueOf(id));
        List<EnrollmentEntity> enrollments = enrollmentService.getEnrollments(cust.getId());               
        ModelAndView view = new ModelAndView("/enrollments/viewEnrollments");
        view.addObject("enrollments", enrollments);
        view.addObject("pageTitle", "Enrollments");
        String message = "Enrollments List::";//updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
//         if (user != null) {                
//                return new ModelAndView("/user/requestWaiver", "user", user);
//            }
//        return new ModelAndView("/user/requestWaiver", "user", "not found");  
        return view;
    }
     @RequestMapping(value = "/viewEnrollments", method = RequestMethod.GET)
    public ModelAndView viewEnrollments(HttpServletRequest request) {        
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            Object object = auth.getPrincipal();
            String password = ((UserDetails) object).getPassword();
            System.out.println("Customer :" + name + "  Password:" + password);
            UserEntity customer = userService.findUser("devika", "devika");
        CustomerEntity cust = customerService.get(customer.getId());
        List<EnrollmentEntity> enrollments = enrollmentService.getEnrollments(cust.getId());        
        ModelAndView view = new ModelAndView("/enrollments/viewEnrollments");
        view.addObject("enrollments", enrollments);
        view.addObject("pageTitle", "Enrollments");
        String message = "Enrollments List:";//updateProfile(request, view, id);
        //redirectAttributes.addFlashAttribute("message", message);
        return view;
    }
    
     @RequestMapping(value = "/viewEnrollments/{id}", method = RequestMethod.GET)
    public ModelAndView viewEnrollments(HttpServletRequest request, @PathVariable int id, final RedirectAttributes redirectAttributes) {
        System.out.println("Id:" + Long.valueOf(id));
        CustomerEntity cust = customerService.get(Long.valueOf(id));
        List<EnrollmentEntity> enrollments = enrollmentService.getEnrollments(cust.getId());               
        ModelAndView view = new ModelAndView("/enrollments/viewEnrollments");
        view.addObject("enrollments", enrollments);
        view.addObject("pageTitle", "Enrollments");
        String message = "Enrollments List::";//updateProfile(request, view, id);
        redirectAttributes.addFlashAttribute("message", message);
//         if (user != null) {                
//                return new ModelAndView("/user/requestWaiver", "user", user);
//            }
//        return new ModelAndView("/user/requestWaiver", "user", "not found");  
        return view;
    }

}
