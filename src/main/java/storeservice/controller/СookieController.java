package storeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import storeservice.model.Admin;
import storeservice.model.Client;
import storeservice.service.AdminServiceImpl;
import storeservice.service.ClientServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class СookieController {

    @Autowired
    public AdminServiceImpl adminService;

    @Autowired
    public ClientServiceImpl clientService;


    @GetMapping("/set_cookie_admin/{id}")
    public String setCookieAdmin(HttpServletResponse httpServletResponse,@PathVariable String id){
        Admin admin = (Admin) adminService.findById(Integer.parseInt(id));
        Cookie cookie = new Cookie("code",admin.getActivateCode());
        cookie.setMaxAge(72000);
        httpServletResponse.addCookie(cookie);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products")
    public String readCookieAdminProd(@CookieValue(value = "code",required = false)String code){

        try {
            if (adminService.findByCode(code)!= null) {
                return "redirect:/admin/allproducts";
            } else {
                return "redirect:/admin/login";
            }
        }catch (NullPointerException exception){
            System.out.println(exception);
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/admin/interest")
    public String readCookieAdminIntr(@CookieValue(value = "code",required = false)String code){

        try {
            if (adminService.findByCode(code)!= null) {
                return "redirect:/admin/allinterest";
            } else {
                return "redirect:/admin/login";
            }
        }catch (NullPointerException exception){
            System.out.println(exception);
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/admin/deal")
    public String readCookieAdminDeal(@CookieValue(value = "code",required = false)String code){

        try {
            if (adminService.findByCode(code)!= null) {
                return "redirect:/admin/alldeal";
            } else {
                return "redirect:/admin/login";
            }
        }catch (NullPointerException exception){
            System.out.println(exception);
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/set_cookie_client/{id}")
    public String setCookieClient(HttpServletResponse httpServletResponse, @PathVariable("id") String id){
        Client client = (Client) clientService.findById(Integer.parseInt(id));
        Cookie cookie = new Cookie("code",client.getActivateCode());
        cookie.setMaxAge(72000);
        httpServletResponse.addCookie(cookie);
        return "redirect:/client/products";
    }
    @GetMapping("/client/products")
    public String readCookieClient(@CookieValue(value = "code",required = false)String code){
        try {
            if (clientService.findByCode(code)!= null) {
                return "redirect:/client/allproducts";
            } else {
                return "redirect:/client/login";
            }
        }catch (NullPointerException exception){
            System.out.println(exception);
            return "redirect:/client/login";
        }

    }
    @GetMapping("/client/product/{id}")
    public String readCookieClientProduct(@CookieValue(value = "code",required = false)String code,@PathVariable int id){
        try {
            if (clientService.findByCode(code)!= null) {
                return "redirect:/client/oneproduct/"+id;
            } else {
                return "redirect:/client/login";
            }
        }catch (NullPointerException exception){
            System.out.println(exception);
            return "redirect:/client/login";
        }

    }

}
