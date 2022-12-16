package com.example.shoppingproject.aop;

import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.ErrMsg;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.ProductRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private ProductRepository productRepository;




    @After("@annotation(Log)")
    public void DeleteLog(JoinPoint jp) throws SystemException {
        LocalDateTime dateTime = LocalDateTime.now();

        MethodSignature signature = (MethodSignature) jp.getSignature();

        String action = signature.getMethod().getName();

        if (action.equals("deleteProduct")){
         int prodId = (int) Arrays.stream(jp.getArgs()).findFirst().get();
         Product p = productRepository.findById(prodId).orElseThrow(()-> new SystemException(ErrMsg.ACTION_FAILED));
        System.out.println( "action:  " + action + " ; at: "  + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")) +"" +
                " , name: " + p.getProductName() + ", company name: " + p.getCompany().getCompanyName());}
    }
}
