package com.example.shoppingproject.services;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.ClientType;
import com.example.shoppingproject.enums.ProductType;
import com.example.shoppingproject.exceptions.ErrMsg;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.UserRepository;
import com.example.shoppingproject.repository.CompanyRepository;
import com.example.shoppingproject.repository.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CompanyService implements CompanyServiceInterface  {

    private int id = -1;
    private CompanyRepository companyRepository;
    private ProductRepository productRepository;

    private UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public int getId() {
        return id;
    }

    @Override
    public Company login(String userName, String password) throws SystemException {
        if (userRepository.findClientTypeByUserNameAndPassword(userName, password) == ClientType.COMPANY){
            Company company = companyRepository.findByUserNameAndPassword(userName, password);
        this.id = company.getId();
        return company;
        }
        else throw new SystemException(ErrMsg.COMPANY_LOGIN_FAILURE);
    }

    @Override
    public Company getDetails(int id) throws SystemException {
        return companyRepository.findById(id).orElseThrow(()->new SystemException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public List<Product> getCompanyProducts() {
        return productRepository.findByCompanyId();
    }

    @Override
    public Product addProduct(Product product) throws SystemException {
        List<Product> products = productRepository.findByCompanyId();
        if (!products.stream().anyMatch(product1 -> product1.getProductName().equalsIgnoreCase(product.getProductName()))){
            product.setCompany(companyRepository.findById(id).orElseThrow(()->new SystemException(ErrMsg.ID_NOT_FOUND)));
            return productRepository.save(product);}
        throw new SystemException(ErrMsg.ACTION_FAILED);
    }

    @Override
    public Product getOneProduct(int prodId) throws SystemException {
        return productRepository.findByIdAndComapnyId(prodId, id)
                .orElseThrow(()->new SystemException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public Product getOneProductByName(String prodName) throws SystemException {
        return productRepository
                .findByProductNameAndComapnyId(prodName, id)
                .orElseThrow(()->new SystemException(ErrMsg.ACTION_FAILED));
    }

    @Override
    public boolean deleteProduct(int prodId) {
        return false;
    }

    @Override
    public Product updateProduct(Product product) throws SystemException {
        if (!productRepository.existsById(product.getId()))
                throw new SystemException(ErrMsg.ID_NOT_FOUND);
            return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsBetweenPublishedDates(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Product> getProductsBetweenPrices(double minPrice, double maxPrice) {
        return null;
    }

    @Override
    public List<Product> getTopRatingProducts(int numOfProducts) {
        return null;
    }

    @Override
    public List<Product> getMinRatingProducts(int numOfProducts) {
        return null;
    }

    @Override
    public List<Product> getProductsByType(ProductType productType) {
        return null;
    }

    @Override
    public List<Product> getTopSalesProducts(int numOfProducts) {
        return null;
    }






}
