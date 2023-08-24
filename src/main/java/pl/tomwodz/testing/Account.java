package pl.tomwodz.testing;

public class Account {

    public boolean active;
    private Address defaultDeliveryAddress;

    public Account() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }

    public boolean isActive(){
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
