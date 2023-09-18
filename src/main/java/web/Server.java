package web;

import dao.CustomerDAO;
import dao.DaoFactory;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {
    
    CustomerDAO dao = DaoFactory.getCustomerDAO();

	public Server() {
		

		install(new GsonModule());

		mount(new StaticAssetModule());
                
                mount(new CustomerModule(dao));
                
                
                
	}

	public static void main(String[] args) {
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}
