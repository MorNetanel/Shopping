package com.example.shoppingproject.loginManager;

import com.example.shoppingproject.enums.ClientType;
import com.example.shoppingproject.exceptions.ErrMsg;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.UserRepository;
import com.example.shoppingproject.services.ClientService;
import com.example.shoppingproject.services.CompanyService;
import com.example.shoppingproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {

    @Autowired
    private ApplicationContext applicationContext;

    public ClientService login(String userName, String password) throws SystemException {

        ClientType clientType;
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        clientType = userRepository.findClientTypeByUserNameAndPassword(userName, password);
        if (clientType == null)
            throw new SystemException(ErrMsg.COMPANY_LOGIN_FAILURE);


        switch (clientType){
            case COMPANY :
                CompanyService companyService = applicationContext.getBean(CompanyService.class);
                if (companyService.login(userName, password)==-1)
                    throw new SystemException(ErrMsg.ACTION_FAILED);
                return companyService;
            case CUSTOMER:
                CustomerService customerService = applicationContext.getBean(CustomerService.class);
                if (customerService.login(userName, password) ==-1)
                    throw new SystemException(ErrMsg.ACTION_FAILED);
                return customerService;
        }
        return null;
    }





}
