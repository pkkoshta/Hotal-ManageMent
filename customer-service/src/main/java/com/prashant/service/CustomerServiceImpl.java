package com.prashant.service;

import com.prashant.dto.CustomerDTO;
import com.prashant.dto.CartRequest;
import com.prashant.dto.Food;
import com.prashant.model.Customer;
import com.prashant.model.OrderedProduct;
import com.prashant.model.ProductCustomer;
import com.prashant.mapper.CustomerMapper;
import com.prashant.mapper.FoodMapper;
import com.prashant.model.User;
import com.prashant.repository.CustomerDao;
import com.prashant.repository.CustomerProductRepository;
import com.prashant.repository.OrderProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomerProductRepository productRepo;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private RestTemplate restTemplate;
     Set<ProductCustomer> customerProductList = new CopyOnWriteArraySet<>();



    @Override
    public CustomerDTO orderComplete(int customerId){
        CustomerDTO customerDTO = null;
        Set<ProductCustomer> productCustomer = null;
        Optional<Customer> optionalCustomer = customerDao.findById(customerId);
        Customer customer1 = optionalCustomer.get();
        List<OrderedProduct> byCustomerId = orderProductRepository.findByCustomerId(customerId);
        Set<OrderedProduct> orderedProducts = byCustomerId.stream().collect(Collectors.toSet());
        customer1.setTotalPrice(getPrice(orderedProducts));
        customer1.setOrderStatus("readyTOPayment");
        customerDao.save(customer1);
        customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer1.getCustomerId());
        customerDTO.setAge(customer1.getAge());
        customerDTO.setProducts(orderedProducts);
        customerDTO.setEmail(customer1.getEmail());
        customerDTO.setAddress(customer1.getAddress());
        customerDTO.setPhone(customer1.getPhone());
        customerDTO.setFirstName(customer1.getFirstName());
        customerDTO.setLastName(customer1.getLastName());
        customerDTO.setTotalPrice(getPrice(orderedProducts));
        customerProductList.clear();
        return customerDTO;
    }
    @Override
    public CustomerDTO removeProduct(CartRequest cartRequest) {
        Customer customer = null;
        Customer updatedCustomer = null;
        CustomerDTO customerDTO = null;
        double totalPrice = 0.0;
        Set<ProductCustomer> productCustomer = null;

        Optional<Customer> optionalCustomer = customerDao.findById(cartRequest.getCustomerId());
        Customer customer1 = optionalCustomer.get();
        orderProductRepository.deleteByCustomerIdAndProductId(cartRequest.getCustomerId(), cartRequest.getProductId());
        List<OrderedProduct> orderedProducts = orderProductRepository.findByCustomerId(cartRequest.getCustomerId());
        Set<OrderedProduct> productSet = orderedProducts.stream().collect(Collectors.toSet());


        customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer1.getCustomerId());
            customerDTO.setAge(customer1.getAge());
            customerDTO.setProducts(productSet);
            customerDTO.setEmail(customer1.getEmail());
            customerDTO.setAddress(customer1.getAddress());
            customerDTO.setPhone(customer1.getPhone());
            customerDTO.setFirstName(customer1.getFirstName());
            customerDTO.setLastName(customer1.getLastName());
            customerDTO.setTotalPrice(getPrice(productSet));
            return customerDTO;

        }



    @Override
    public List<CustomerDTO> customers() {
        List<ProductCustomer> productCustomerList = null;
        List<Customer> customerList = customerDao.findAll();
        for (Customer customer: customerList){
            productCustomerList = productRepo.findByCustomerId(customer.getCustomerId());
        }
        return CustomerMapper.dtos(customerDao.findAll());
    }

    @Override
    public CustomerDTO checkCustomerOrderById(int customerId) {
        List<ProductCustomer> productCustomerList = null;
        Set<ProductCustomer> productCustomerSet = null;
        CustomerDTO customerDTO = null;
        Optional<Customer> optionalCustomer = customerDao.findById(customerId);
        Customer customer1 = optionalCustomer.get();
        List<OrderedProduct> orderedProducts = orderProductRepository.findByCustomerId(customerId);
        Set<OrderedProduct> orderedProductSet = orderedProducts.stream().collect(Collectors.toSet());
        customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer1.getCustomerId());
        customerDTO.setAge(customer1.getAge());
        customerDTO.setProducts(orderedProductSet);
        customerDTO.setEmail(customer1.getEmail());
        customerDTO.setAddress(customer1.getAddress());
        customerDTO.setPhone(customer1.getPhone());
        customerDTO.setFirstName(customer1.getFirstName());
        customerDTO.setLastName(customer1.getLastName());
        customerDTO.setTotalPrice(getPrice(orderedProductSet));
        return customerDTO;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = null;
        if (dto !=null) {
            User user = new User();
            user.setUsername(dto.getFirstName());
            user.setPassword(dto.getPassword());
            userDetailsService.createUser(user);
            customer = CustomerMapper.dtoTOCustomer(dto);
             customer = customerDao.save(customer);
             return CustomerMapper.customerToDTO(customer);

        }
        return null;
    }

    @Override
    public CustomerDTO updateCustomer(CartRequest dto) {
        double totalprice=0.0;
        Customer updatedCustomer = null;
        CustomerDTO customerDTO = null;
        Set<ProductCustomer> productCustomerSet = null;
        Optional<Customer> customer = customerDao.findById(dto.getCustomerId());
        Customer customer1 = customer.get();
        Food food = restTemplate.getForObject("http://localhost:9091/product/"+dto.getProductId(), Food.class);
        logger.warn("food recived in customer {}", food);
        OrderedProduct orderProduct = (OrderedProduct) FoodMapper.foodToOrderProduct(food);
        orderProduct.setQuantity(dto.getQuantity());
        orderProduct.setPid(food.getId());
       // orderProduct.setId(food.getId());
        orderProduct.setCustomerId(dto.getCustomerId());
        orderProduct.setPrice(food.getPrice() * dto.getQuantity());
        logger.warn("recived food mapped to orderProduct {}", orderProduct);

        orderProduct = orderProductRepository.save(orderProduct);
        List<OrderedProduct> byCustomerId = orderProductRepository.findByCustomerId(dto.getCustomerId());
        Set<OrderedProduct> orderedProducts = byCustomerId.stream().collect(Collectors.toSet());
        logger.warn("recived food get after save updated customer {}", updatedCustomer);
        customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(orderProduct.getCustomerId());
            customerDTO.setAge(customer1.getAge());
            customerDTO.setProducts(orderedProducts);
            customerDTO.setEmail(customer1.getEmail());
            customerDTO.setAddress(customer1.getAddress());
            customerDTO.setPhone(customer1.getPhone());
            customerDTO.setFirstName(customer1.getFirstName());
            customerDTO.setLastName(customer1.getLastName());
            customerDTO.setTotalPrice(getPrice(orderedProducts));
        logger.warn("recived food get after save customerDTO {}", customerDTO);
         return customerDTO;
        }




    @Override
    public void deleteCustomer(int cid) {
      customerDao.deleteById(cid);
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        CustomerDTO customerDTO = null;
        Customer customer = null;
        List<OrderedProduct> byCustomerId = null;
        Optional<Customer> customerOptional = customerDao.findById(id);
        if (customerOptional.isPresent()){
            customer = customerOptional.get();

            customerDTO = CustomerMapper.customerToDTO(customer);
            byCustomerId = orderProductRepository.findByCustomerId(id);
            Set<OrderedProduct> orderedProducts = byCustomerId.stream().collect(Collectors.toSet());

            customerDTO.setProducts(orderedProducts);
            customerDTO.setTotalPrice(getPrice(orderedProducts));
        }
        return customerDTO;
    }

    private double getPrice(Set<OrderedProduct> orderedProducts){
        double totalprice = 0.0;
        for (OrderedProduct p : orderedProducts){
            totalprice += p.getPrice();
        }
        return totalprice;
    }
    @Override
    public String updateAndCreateFoodId(int custId, int foodId) {
        return "Duplicate food id not allowed..";
    }
}
