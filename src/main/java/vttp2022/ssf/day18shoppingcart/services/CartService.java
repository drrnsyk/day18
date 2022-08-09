package vttp2022.ssf.day18shoppingcart.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
// @Scope("singleton")
public class CartService {

    // cannot do this, because its a global linkedlist
    // List<String> cartList = new LinkedList<>();
    
    // public List<String> addToCart(String item) {
        
    //     cartList.add(item);
    //     return cartList;

    // }

    public List<String> deserializer(String cartStr) {

        List<String> cartList = new LinkedList<>();
        String[] cartArray = cartStr.split(",");

        for (int i = 0; i < cartArray.length; i++) {
            cartList.add(cartArray[i]);
        }

        return cartList;

    }

    public String serializer(List<String> cartList) {

        String cartStr = String.join(",", cartList);
        // System.out.println(cartStr);
        return cartStr;

    }

}
