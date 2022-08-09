package vttp2022.ssf.day18shoppingcart.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.day18shoppingcart.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartSvc;
    
    @GetMapping
    public String getCartName (@RequestParam String name, Model model) {
        model.addAttribute("userName", name);
        return "cart";
    }

    @PostMapping
    public String getCartItems (@RequestBody MultiValueMap<String, String> form, Model model) {
        
        // get userName and populate in cart.html with hidden field
        String userName = form.getFirst("userName");
        model.addAttribute("userName", userName);

        // instantiate a linkedlist to store the items
        List<String> cartList = new LinkedList<>();

        // convert from string to linkedlist so that item can be added
        String cartStr = form.getFirst("cartStr");
        cartList = cartSvc.deserializer(cartStr);

        // get item from form and populate in the linkedlist
        String item = form.getFirst("item");
        cartList.add(item);
        // cartList = cartSvc.addToCart(item);

        // convert the linkedlist to string to be added back to the form as hidden field
        cartStr = cartSvc.serializer(cartList);

        model.addAttribute("cartList", cartList);
        model.addAttribute("cartStr", cartStr);

        // for (int i = 0; i < cartList.size(); i++) {
        //     System.out.println(cartList.get(i));
        // }
        //System.out.println(cartList.size());
      
        return "cart";
    }

}
