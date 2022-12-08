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
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyService extends ClientService implements CompanyServiceInterface  {

    private int id = 1;
    private CompanyRepository companyRepository;
    private  ProductRepository productRepository;

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
    public int login(String userName, String password) throws SystemException {
        if (userRepository.findClientTypeByUserNameAndPassword(userName, password) == ClientType.COMPANY){
            Company company = companyRepository.findByUserNameAndPassword(userName, password);
        this.id = company.getId();
        return id;
        }
        else throw new SystemException(ErrMsg.COMPANY_LOGIN_FAILURE);
    }

    @Override
    public Company getDetails() throws SystemException {
        return companyRepository.findById(id).orElseThrow(()->new SystemException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public List<Product> getCompanyProducts() {
        return productRepository.findByCompanyId(id);
    }

    @Transactional
    @Override
    public Product addProduct(Product product) throws SystemException {
        if (!isProductNameExistsForCompanyProducts(product.getProductName())){
            addCompanyIdToProduct(product);
            addPublishedDateToProduct(product);
            return productRepository.save(product);}
        throw new SystemException(ErrMsg.ACTION_FAILED);
    }

    @Override
    public Product getOneProduct(int prodId) throws SystemException {
        return productRepository.findByIdAndCompanyId(prodId, id)
                .orElseThrow(()->new SystemException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public Product getOneProductByName(String prodName) throws SystemException {

        return productRepository
                .findByProductName(prodName).filter(product -> product.getCompany().getId() == id)
                .orElseThrow(()->new SystemException(ErrMsg.ACTION_FAILED));
    }

    @Transactional
    @Override
    public boolean deleteProduct(int id) {
        if (isProductBelongToCompany(id, this.id)){
            productRepository.deleteProductFromCustomersProductsHistory(id);
            productRepository.deleteProductFromProducts(id);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct( Product product) throws SystemException {
            Product productToUpdate = productRepository.findByIdAndCompanyId(product.getId(), id).orElseThrow(()-> new SystemException(ErrMsg.ID_NOT_FOUND));
            if (isProductUpdatable(product, productToUpdate)){
                Company companyToInsertBeforeUpdate = companyRepository.findById(id).orElseThrow(()-> new SystemException(ErrMsg.ID_NOT_FOUND));
            product.setCompany(companyToInsertBeforeUpdate);
            return productRepository.save(product);}
            else
            throw new SystemException(ErrMsg.ACTION_FAILED);
    }

    @Override
    public List<Product> getProductsBetweenPublishedDates(Date startDate, Date endDate) {
        return productRepository.findProductsBetweenPublishedDates(startDate, endDate)
                .stream().filter(product -> product.getCompany().getId() == id).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsBetweenPrices(double minPrice, double maxPrice) throws SystemException {
        if (minPrice > 0 && minPrice < maxPrice)
        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream().filter(product -> product.getCompany().getId() == id).collect(Collectors.toList());
        else throw new SystemException(ErrMsg.ACTION_FAILED);
    }

    @Override
    public List<Product> getTopRatingProducts(int numOfProducts) {
        return productRepository.findTopRatingProductsByCompanyId(id, numOfProducts);
    }

    @Override
    public List<Product> getMinRatingProducts(int numOfProducts) {
        return productRepository.findLowRatingProductsByCompanyId(id, numOfProducts);
    }

    @Override
    public List<Product> getProductsByType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    @Override
    public List<Product> getTopSalesProducts(int numOfProducts) {
        return productRepository.findTopSaleProductsByCompanyId(id, numOfProducts);
    }



    //util methods

    public  boolean isProductNameExistsForCompanyProducts(String productName){
        List<Product> products = productRepository.findByCompanyId(id);
        return products.stream().anyMatch(product1 -> product1.getProductName().equalsIgnoreCase(productName));
    }

    public void addCompanyIdToProduct(Product product) throws SystemException {
        product.setCompany(companyRepository.findById(id).orElseThrow(() -> new SystemException(ErrMsg.ID_NOT_FOUND)));
    }

    public Product addPublishedDateToProduct(Product product){
        product.setPublishedDate(Date.valueOf(LocalDate.now()));
        return product;
    }

    public boolean isProductBelongToCompany(int productId, int id){
       return productRepository.findByIdAndCompanyId(productId, id).isPresent();
    }

    public boolean isProductUpdatable(Product prodAfterChanges, Product prodBeforeChanges){
        return  (prodAfterChanges.getPrice()>0
                && prodAfterChanges.getSales() == prodBeforeChanges.getSales()
                &&prodBeforeChanges.getProductName().equals(prodAfterChanges.getProductName())
        && prodBeforeChanges.getPublishedDate().getYear()==prodAfterChanges.getPublishedDate().getYear()
        &&prodBeforeChanges.getPublishedDate().getMonth()==prodAfterChanges.getPublishedDate().getMonth()
                &&prodBeforeChanges.getPublishedDate().getDay()==prodAfterChanges.getPublishedDate().getDay()
        &&prodAfterChanges.getExpiredDate().after(Date.valueOf(LocalDate.now())));

    }






}
