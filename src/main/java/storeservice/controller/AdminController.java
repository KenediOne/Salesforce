package storeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storeservice.components.SimpleEmailComponent;
import storeservice.encryption.Encryption;
import storeservice.model.*;
import storeservice.service.*;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;

@Controller
public class AdminController {

    private int idProduct;

    @Autowired
    public Encryption encryption;

    @Autowired
    public ClientServiceImpl clientService;

    @Autowired
    public SimpleEmailComponent simpleEmailComponent;

    @Autowired
    public ProductServiceImpl productService;

    @Autowired
    public InterestServiceImpl interestService;

    @Autowired
    public DealServiceImpl dealService;

    @Autowired
    public AdminServiceImpl adminService;

    @GetMapping("/admin/login")
    public String admintLogin(Model model){
        model.addAttribute("admin",new Admin());

        return "/admin/loginadmin";
    }
    @PostMapping("/admin/logins")
    public String admintLoginPost(@ModelAttribute("admin") Admin admins){
        try {
            Admin admin = adminService.findByAdmin(admins);
            System.out.println(admin.getEmail());
            if (admin!=null){
                String code = encryption.encoding(admin.getEmail());
                admin.setActivateCode(code);
                admin.setDateOfAuthorization(new Date(new java.util.Date().getDate()));
                admin.setTimeOfAuthorization(new Time(new java.util.Date().getTime()));
                adminService.update(admin);
                return "redirect:/set_cookie_admin/"+admin.getId();
            }else {
                return "redirect:/admin/login";
            }
        }catch (NullPointerException | UnsupportedEncodingException exception){
            System.out.println(exception);
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/admin/allproducts")
    public String adminProducts(Model model){
        model.addAttribute("product",productService.findAll());
        return "/admin/products";
    }

    @GetMapping("/admin/allinterest")
    public String adminInterestsCl(Model model){
        model.addAttribute("interest",interestService.findAll());
        model.addAttribute("client",clientService);
        model.addAttribute("product",productService);
        return "/admin/interests";
    }
    @GetMapping("/admin/interest/edit")
    public String adminInterestEdit(@RequestParam("idproduct")int id,
                                    @RequestParam("status")byte status){
        Interest interest = (Interest) interestService.findById(id);
        interest.setDateOfActivity(new Date(new java.util.Date().getDate()));
        interest.setTimeOfActivity(new Time(new java.util.Date().getTime()));
        interest.setStatus(status);
        interestService.update(interest);
        return "redirect:/admin/interest";
    }

    @GetMapping("/admin/alldeal")
    public String adminDealsCl(Model model){
        model.addAttribute("deal",dealService.findAll());
        model.addAttribute("client",clientService);
        model.addAttribute("product",productService);
        return "/admin/deals";
    }

    @GetMapping("/admin/deal/edit")
    public String adminDealEdit(@RequestParam("idproduct")int id,
                                    @RequestParam("stage")byte status){
        Deal deal = (Deal) dealService.findById(id);
        deal.setDateOfActivity(new Date(new java.util.Date().getDate()));
        deal.setTimeOfActivity(new Time(new java.util.Date().getTime()));
        deal.setStage(status);
        dealService.update(deal);
        return "redirect:/admin/deal";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String adminDeleteProduct(@PathVariable int id, Model model){
        interestService.deleteOfProduct(id);
        productService.delete(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/add")
    public String adminAddProduct(Model model){
        model.addAttribute("product",new Product());
        return "/admin/oneproduct";
    }
    @GetMapping("/admin/product/edit/{id}")
    public String adminEditProduct(@PathVariable int id, Model model){
        model.addAttribute("product",productService.findById(id));
        idProduct = id;
        return "/admin/oneproduct";
    }
    @PostMapping("/admin/product/")
    public String adminEditOrAddProduct(@ModelAttribute("product")Product product ){
        product.setId(idProduct);
                if(product.getId()>0){
            productService.update(product);
        }else {
            productService.add(product);
        }
        return "redirect:/admin/products";
    }
}
