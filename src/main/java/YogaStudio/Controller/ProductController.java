/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FileEntity;
import YogaStudio.domain.ProductEntity;
import YogaStudio.domain.ProductOrderEntity;
import YogaStudio.domain.UserEntity;
import YogaStudio.service.FileService;
import YogaStudio.service.ProductService;
import YogaStudio.service.UserService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ProductController {   
    
    @Autowired
    private ProductService productService; 
   
    @Autowired
    private UserService userService;
    
   @Autowired
    private FileService fileService;
   
    private Object message;
    
      
    @RequestMapping(value = {"/products","/user/products"}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        List<ProductEntity> products = productService.getAll();
        ModelAndView view = new ModelAndView("/product/products");
        view.addObject("products", products);
        view.addObject("pageTitle", "Products");
        return  view;
    }
    
    @RequestMapping(value = {"/user/product/add","/product/add"}, method = RequestMethod.GET)
    public String addEditProduct(HttpServletRequest request) {
        return "product/addProduct";
    }
    
    @RequestMapping(value = {"/product/save","/user/product/save"}, method = RequestMethod.POST)
    public RedirectView saveProduct(HttpServletRequest request,final RedirectAttributes redirectAttributes) {
         RedirectView view = new RedirectView();
         String  message =  addUpdateProduct(request,view);    
         redirectAttributes.addFlashAttribute("message", message);
         return view;//"redirect:/";
    }
    
    @RequestMapping(value = {"/product/save/{id}","/user/product/save/{id}"}, method = RequestMethod.POST)
    public RedirectView editProduct(HttpServletRequest request,@PathVariable Long id, final RedirectAttributes redirectAttributes) {
         RedirectView view = new RedirectView();                
         String  message =  addUpdateProduct(request,view,id);    
         redirectAttributes.addFlashAttribute("message", message);
         return view;//"redirect:/";
    }
    
    @RequestMapping(value = {"/product/remove/{id}","/user/product/remove/{id}"}, method = RequestMethod.GET)
    public RedirectView removeProduct(HttpServletRequest request,@PathVariable Long id, final RedirectAttributes redirectAttributes) {
         RedirectView view = new RedirectView();                
         productService.delete(id);   
         view.setUrl(request.getContextPath()+"/products");
         redirectAttributes.addFlashAttribute("Remove successfull", message);
         return view;//"redirect:/";
    }
    
    @RequestMapping(value = {"/products/{id}","/user/products/{id}"}, method = RequestMethod.GET)
    public String get(HttpServletRequest request,@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.get(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                 return "product/productDetail_Admin";
            }
        }
          
       return "product/productDetail";
    }
   
    @RequestMapping(value = {"/product/edit_product/{id}","/user/product/edit_product/{id}"}, method = RequestMethod.GET)
    public String getEditProduct(HttpServletRequest request,@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.get(id));
        return "product/edit_product";
    }

    @RequestMapping(value = "/products/delete", method = RequestMethod.POST)
    public String delete(Long userId) {
        productService.delete(userId);
        return "redirect:/products";
    }

    //private method to add add and update users
    private String addUpdateProduct(HttpServletRequest request, RedirectView view){
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("descritpion"),
                     price = request.getParameter("price"),
                     numberOfProducts = request.getParameter("numberOfProducts");
                     
                 if(name.isEmpty())
                     message.add("Product name is required");
                 if(price.isEmpty())
                     message.add("Price is required");
                 if(numberOfProducts.isEmpty())
                     message.add("Number of products is required");
                   
                 if(message.isEmpty()){
                     boolean saved = productService.add(name,
                                                       description,
                                                       Integer.parseInt(numberOfProducts),
                                                       Double.parseDouble(price));
                    
                     if(saved){
                       message.add("Successfully saved");
                       view.setUrl(request.getContextPath()+"/products");
                       }
                     else{
                       message.add("Product unsuccessful");
                       view.setUrl(request.getContextPath()+"/products");
                     }
                   }
                 else{
                     view.setUrl(request.getContextPath()+"/products");
                 }
             return message.toString();
    }   
    
    private String addUpdateProduct(HttpServletRequest request, RedirectView view, Long id){
              List message = new ArrayList();
              String name = request.getParameter("name"),
                     description = request.getParameter("description"),
                     price = request.getParameter("price"),
                     numberOfProducts = request.getParameter("numberOfProducts");
                     
                 if(name.isEmpty())
                     message.add("Product name is required");
                 if(price.isEmpty())
                     message.add("Price is required");
                 if(numberOfProducts.isEmpty())
                     message.add("Number of products is required");
                   
                 if(message.isEmpty()){
                     boolean saved = productService.add(id,name,
                                                       description,
                                                       Integer.parseInt(numberOfProducts),
                                                       Double.parseDouble(price));
                    
                     if(saved){
                       message.add("Successfully saved");
                       view.setUrl(request.getContextPath()+"/products");
                       }
                     else{
                       message.add("Product unsuccessful");
                       view.setUrl("add");
                     }
                   }
                 else{
                     view.setUrl("add");
                 }
             return message.toString();
    } 
   //customer product cart
   @RequestMapping(value = "/products/cart", method = RequestMethod.GET)
    public String cart(HttpServletRequest request) {
       // model.addAttribute("product", productService.get(id));
        return "product/customerCart";
    }
 
   @RequestMapping(value = "/products/checkout", method = RequestMethod.POST,headers ="Content-Type=application/json")
    public  @ResponseBody HashMap  checkout(@RequestBody final  String request) {
        //TODO check if the customer has a credit card 
        //check the balance in the credit card
          HashMap response = new HashMap();
        try {
             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
             String username = auth.getName();          
             Object object = auth.getPrincipal();   
             UserDetails userDetails = (UserDetails)object; //
             String password= userDetails.getPassword(); //
             //TODO user by username and password
             UserEntity user = userService.findCustomerBy("username",username);
             if(user !=null)
             {
                 //user is active
                    List <ProductOrderEntity> list = new ArrayList<ProductOrderEntity>(); 

                    JsonFactory f = new JsonFactory();
                    JsonParser jp = f.createJsonParser(request);
                    jp.nextToken();

                    ObjectMapper mapper = new ObjectMapper();
                   // and then each time, advance to opening START_OBJECT
                    while (jp.nextToken() == JsonToken.START_OBJECT) {
                       // jp.
                     list.add(mapper.readValue(jp, ProductOrderEntity.class));
                   }

                   boolean saved = productService.addOrders(list, user);
                   response.put("success",saved);
             }
             else{
                response.put("success",false);
                response.put("message","User does not exist");
             }
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            response.put("success", false);
            response.put("message","Sorry ,checkout unsuccessul");
        }
        
        return response;
    }
 
    //seach products
     @RequestMapping(value = "/products/query", method = RequestMethod.GET)
     public ModelAndView search(HttpServletRequest request) {
        String name = request.getParameter("product");
        List<ProductEntity> products = null;
        if(name.isEmpty()){
            products = productService.getAll();
        }
        else{
            products = productService.findBy("name", name);
        }
        ModelAndView view = new ModelAndView("/product/main_product_list");
        view.addObject("products", products);
        return  view;
     }
     
    //show product image
     @RequestMapping(value = "/products/image/{id}", method = RequestMethod.GET)
     public void productImage(@PathVariable Long id,
            HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
            try {
             // you can use FileInputStream to convert any type of file to
             // InputStream
             FileEntity file = fileService.get(id);
             InputStream inputStream = new FileInputStream(file.getPath());

             // IOUtils jar used to convert Input Stream to byte array easily
             byte[] bytes = IOUtils.toByteArray(inputStream);

             response.setContentType("image/jpeg");
             OutputStream outputStream = response.getOutputStream();
             outputStream.write(bytes);
             outputStream.close();
            } catch (Exception e) {
             // TODO: handle exception
             e.printStackTrace();
            }
   }
     
}
