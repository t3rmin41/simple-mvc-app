package simple.mvc.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CartBean {

    private List<OrderBean> items = new ArrayList<OrderBean>();

    public List<OrderBean> getItems() {
        return this.items;
    }

    public void clear() {
        this.items = new ArrayList<OrderBean>();
    }
}
