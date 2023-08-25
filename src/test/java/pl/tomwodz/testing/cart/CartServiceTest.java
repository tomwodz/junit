package pl.tomwodz.testing.cart;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pl.tomwodz.testing.order.Order;
import pl.tomwodz.testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPreper(cart);
        then(cartHandler).should().sendToPreper(cart);

        verify(cartHandler, times(1)).sendToPreper(cart);
        verify(cartHandler, atLeastOnce()).sendToPreper(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPreper(cart);

        assertThat(resultCart.getOrders(), Matchers.hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),
                equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSendToPrepare() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPreper(cart);
        then(cartHandler).should(never()).sendToPreper(cart);
        assertThat(resultCart.getOrders(), Matchers.hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),
                equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldNotSendToPrepareWithArgumentMatchers() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPreper(any(Cart.class));
        then(cartHandler).should(never()).sendToPreper(any(Cart.class));
        assertThat(resultCart.getOrders(), Matchers.hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),
                equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandlerCartShouldReturnMultipleValues() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(true, false, false, true);

        //then
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0)))
                .willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPreper(cart);
        assertThat(resultCart.getOrders(), Matchers.hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),
                equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCartShouldThrowException() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart))
                .willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));

    }


}