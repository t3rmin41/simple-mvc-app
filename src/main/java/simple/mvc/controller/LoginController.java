package simple.mvc.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.service.UserService;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login/success", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, String> successfulUserLogin(HttpSession session) {
      DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
      Map<String, String> requestMap = new HashMap<String, String>();
      String requestedURL = "";
      if (null == savedRequest) {
        requestedURL = "/logged";
      } else {
        requestedURL = savedRequest.getRequestURL();
      }
      requestMap.put("requestedURL", requestedURL);
      return requestMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }
    
    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String successfullLoginPage(Principal principal) {
        return "logged";
    }
    
}
