package vttp2022.ssf.day18shoppingcart.models;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    
    private Integer cartId;
    private String userName;
    private List<String> cartList = new LinkedList<>();

    public Integer getCartId() {
        return cartId;
    }
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<String> getCartList() {
        return cartList;
    }
    public void setCartList(List<String> cartList) {
        this.cartList = cartList;
    }

    
}
