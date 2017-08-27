package simple.mvc.bean;

import java.util.ArrayList;
import java.util.List;

public class CartBean {

    private List<OrderBean> items = new ArrayList<OrderBean>();

    public List<OrderBean> getItems() {
        return items;
    }

}
