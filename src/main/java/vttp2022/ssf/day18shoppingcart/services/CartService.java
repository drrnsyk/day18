package vttp2022.ssf.day18shoppingcart.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.ssf.day18shoppingcart.models.Cart;
import vttp2022.ssf.day18shoppingcart.models.Item;
import vttp2022.ssf.day18shoppingcart.repositories.CartRepository;

@Service
// @Scope("singleton")
public class CartService {

    @Autowired 
    private CartRepository cartRepo;
    
    public void saveRepo (Cart cart) {
        cartRepo.save(cart);
    }

    public Cart getRepo(String userName) {
        Optional<Cart> opt = cartRepo.get(userName);
        return opt.get();
    }

    public Item generateItem(String itemName, Integer quantity) {
        Item item = new Item();
        item.setName(itemName);
        item.setQuantity(quantity);
        return item;
    }
    
    public List<Item> addItemToList(Item item) {
        List<Item> cartList = new LinkedList<>();
        cartList.add(item);
        return cartList;
    }

    public Cart generateCart(String userName, List<Item> cartList) {
        Cart cart = new Cart();
        cart.setName(userName);
        cart.setContents(cartList);
        return cart;
    }



}
