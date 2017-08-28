package simple.mvc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@Scope(value = "session")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CartBean {

    private List<OrderBean> items = new ArrayList<OrderBean>();

    public List<OrderBean> getItems() {
        return this.items;
    }

    public void clear() {
        this.items = new ArrayList<OrderBean>();
    }
}
