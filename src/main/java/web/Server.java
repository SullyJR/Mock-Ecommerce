package web;

import dao.CustomerDAO;
import dao.DaoFactory;
import dao.ProductDAO;
import dao.SaleDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;
import java.util.Set;

public class Server extends Jooby {
    
    CustomerDAO customerDao = DaoFactory.getCustomerDAO();
    ProductDAO productDao = DaoFactory.getProductDAO();
    SaleDAO saleDao = DaoFactory.getSaleDAO();
    
	public Server() {
		
                mount(new StaticAssetModule());
		
                install(new GsonModule());
                
                install(new BasicAccessAuth(customerDao, Set.of("/api/.*"), Set.of("/api/register")));
		
                mount(new ProductModule(productDao));
                
                mount(new CustomerModule(customerDao));
                              
                mount (new SaleModule(saleDao));
                
                
                
	}

	public static void main(String[] args) {
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}
