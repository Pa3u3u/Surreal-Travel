package cz.muni.pa165.surrealtravel.controller;

import cz.muni.pa165.surrealtravel.dto.CustomerDTO;
import cz.muni.pa165.surrealtravel.service.CustomerService;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Stub of a customer controller.
 * @author Jan Klimeš [374259]
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    
    final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private MessageSource messageSource;
       
    @RequestMapping(method = RequestMethod.GET)
    public String listCustomers(ModelMap model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/list";
    }
    
    @PostConstruct
    public void init() {
        CustomerDTO c1 = new CustomerDTO();
        c1.setName("Honza");
        c1.setAddress("Olomouc");
        
        CustomerDTO c2 = new CustomerDTO();
        c2.setName("Eva");
        c2.setAddress("Olomouc");
        
        CustomerDTO c3 = new CustomerDTO();
        c3.setName("Jonáš");
        c3.setAddress("Olomouc");
        
        customerService.addCustomer(c1);
        customerService.addCustomer(c2);
        customerService.addCustomer(c3);
    }
    
}
