package storeservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import storeservice.components.SimpleEmailComponent;
import storeservice.encryption.Encryption;
import storeservice.model.Client;
import storeservice.model.Deal;
import storeservice.model.Interest;
import storeservice.model.Product;
import storeservice.service.ClientServiceImpl;
import storeservice.service.DealServiceImpl;
import storeservice.service.InterestServiceImpl;
import storeservice.service.ProductServiceImpl;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;


@Controller
public class ClientController {

    private int idPerson;

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


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("client",new Client());
        return "/client/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("client")Client client,Model model){
        Client client1 = clientService.findByClient(client);
        try {
            if (client1 != null) {
                if (client1.getActivateCode() != null) {
                    return "redirect:/client/login";
                } else {
                    return "/client/nonelogin";
                }
            } else {
                clientService.add(client);
                simpleEmailComponent.sendSimpleEmail(client.getEmail());
                return "redirect:/registration";
            }
        }catch (Exception index){
            System.out.println(index);
            clientService.add(client);
            simpleEmailComponent.sendSimpleEmail(client.getEmail());
            return "redirect:/registration";
        }
    }

    @GetMapping("/client/login")
    public String clientLogin(Model model){
        model.addAttribute("client",new Client());

        return "client/login";
    }

    @PostMapping("/client/login")
    public String clientLoginPost(@ModelAttribute("client")Client client){
        try {
            Client client1 = clientService.findByClient(client);
            if (client1!=null){
                String code = encryption.encoding(client1.getEmail());
                client1.setActivateCode(code);
                clientService.update(client1);
                idPerson = client1.getId();
                return "redirect:/set_cookie_client/"+client1.getId();
            }else {
                return "redirect:/client/login";
            }
        }catch (NullPointerException | UnsupportedEncodingException exception){
            System.out.println(exception);
            return "redirect:/client/login";
        }
    }

    @GetMapping("/client/allproducts")
    public String clientProducts(Model model){
        model.addAttribute("product",productService.findAll());
        return "/client/products";
    }

    @GetMapping("/client/oneproduct/{id}")
    public String clientOneProduct(@PathVariable int id,Model model){
        model.addAttribute("product",productService.findById(id));
        idProduct = id;
        try{
            if(interestService.findByPerProd(idPerson,idProduct)==null&&dealService.findByPerProd(idPerson,idProduct)==null){
                Interest interest = new Interest();
                interest.setDateOfActivity(new Date(new java.util.Date().getDate()));
                interest.setTimeOfActivity(new Time(new java.util.Date().getTime()));
                interest.setStatus((byte) 1);
                interest.setIdClient(idPerson);
                interest.setIdProduct(idProduct);
                interestService.add(interest);
            }
        }catch (NullPointerException e){
            System.out.println(e);
        }
        return "/client/oneproduct";
    }
    @PostMapping("/client/oneproduct/buy")
    public String clientBuyProduct(@ModelAttribute("product")Product product){
        try {
            if (dealService.findByPerProd(idPerson, idProduct) == null) {
                Deal deal = new Deal();
                deal.setStage((byte) 1);
                deal.setIdClient(idPerson);
                deal.setDateOfActivity(new Date(new java.util.Date().getDate()));
                deal.setTimeOfActivity(new Time(new java.util.Date().getTime()));
                deal.setIdProduct(idProduct);
                dealService.add(deal);
                interestService.delete(interestService.findByPerProd(idPerson,idProduct).getId());
            }
        }catch (NullPointerException e){
            System.out.println(e);
        }
        return "redirect:/client/oneproduct/"+idProduct;
    }
//    @Autowired
//    public ProductServiceImpl productService;


//    @GetMapping("/storeservice")
//    public String coffeeteria(Model model){
//        model.addAttribute("object",new Product());
//        model.addAttribute("list",productService.findAll());
//        return "/storeservice";
//    }
//    @GetMapping("/storeservice/{id}")
//    public String coffeeteriaup(@PathVariable("id")int id, Model model){
//        model.addAttribute("object",productService.findById(id));
//        model.addAttribute("list",productService.findAll());
//        return "/storeservice";
//    }
//    @GetMapping("/storeservice/delete/{id}")
//    public String coffeeteriadelete(@PathVariable("id")int id, Model model){
//        //productService.deleteProduct(id);
//        return "redirect:/coffeeteria";
//    }
//    @PostMapping("/storeservice")
//    public String addcoffeeteria(@ModelAttribute("object")Product product){
//        if(product.getId()==0){
//            //productService.addProduct(product);
//        }else {
//            productService.updateProduct(product);
//        }
//        return "redirect:/coffeeteria";
//    }
//    @GetMapping("/buy")
//    public String buy(@RequestParam("idproduct") int id,
//                      @RequestParam("quantity") int quan , Model model){
//        model.addAttribute("costobj",productService.findById(id).getPrice()*quan);
//        return "/buy";
//    }
//    @GetMapping("/listproduct")
//    public String listproduct(Model model){
//        model.addAttribute("list",productService.findAll());
//        return "/listproduct";
//    }
}
