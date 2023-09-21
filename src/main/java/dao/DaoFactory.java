package dao;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.JdbiDaoFactory;
import dao.ProductCollectionsDAO;
import dao.ProductDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author callumsullivan
 */
public class DaoFactory {

    private static boolean usingDatabase = true;

    public static ProductDAO getProductDAO() {
        if (usingDatabase) {
            return JdbiDaoFactory.getProductDAO();
        } else {
            return new ProductCollectionsDAO();
        }
    }

    public static CustomerDAO getCustomerDAO() {
        if (usingDatabase) {
            return JdbiDaoFactory.getCustomerDAO();
        } else {
            return new CustomerCollectionsDAO();
        }
    }

    public static SaleDAO getSaleDAO() {
            return JdbiDaoFactory.getSaleDAO();
    }

}
