package pl.tomwodz.testing;

public class Account {

    public boolean active;

    public Account() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }

    public boolean isActive(){
        return this.active;
    }
}
