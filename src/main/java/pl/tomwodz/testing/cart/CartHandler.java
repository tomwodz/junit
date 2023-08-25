package pl.tomwodz.testing.cart;

public interface CartHandler {

    boolean canHandleCart(Cart cart);

    void sendToPreper(Cart cart);
}
