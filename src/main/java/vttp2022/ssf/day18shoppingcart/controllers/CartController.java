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

import vttp2022.ssf.day18shoppingcart.models.Cart;
import vttp2022.ssf.day18shoppingcart.models.Item;
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
        List<Item> itemList = new LinkedList<>();
        // List<Cart> cartList = new LinkedList<>();

        
        // get item name from form
        String itemName = form.getFirst("item");
        // get item quantity from form
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        // generate item and add it to a list of item
        itemList = cartSvc.addItemToList(cartSvc.generateItem(itemName, quantity));
        // generate the cart containing the list of item
        Cart cart = cartSvc.generateCart(userName.toLowerCase(), itemList);
        // save the cart to redis in a json string format in redis list
        cartSvc.saveRepo(cart);


        // get json string from the redis by passing in key userName
        // convert json string to json object
        // convert json object to model cart
        // get list of cart to populate in view
        cart = cartSvc.getRepo(userName.toLowerCase());
        itemList = cart.getContents();
        // System.out.println(cart.getContents().get(0).getName());
        // cartList.add(cart);
        
        model.addAttribute("itemList", itemList);
        // model.addAttribute("cartStr", cartStr);

        // for (int i = 0; i < cartList.size(); i++) {
        //     System.out.println(cartList.get(i).getName());
        //     System.out.println(cartList.get(i).getQuantity());
        // }
        // System.out.println(cartList.size());
      
        return "cart";
    }

}
