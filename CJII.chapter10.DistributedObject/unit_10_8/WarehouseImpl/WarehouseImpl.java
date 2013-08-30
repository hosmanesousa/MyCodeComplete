package unit_10_8.WarehouseImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unit_10_5.Warehouse.Warehouse;
import unit_10_6.Product.Product;

public class WarehouseImpl extends UnicastRemoteObject implements Warehouse 
{
	public WarehouseImpl(Warehouse backup)throws RemoteException
	{
		products = new HashMap<String,Product>();
		this.backup = backup;
	}
	
	public void add(String keyword,Product product)
	{
		product.setLocation(this);
		products.put(keyword, product);
	}
	
	public double getPrice(String description) throws RemoteException
	{
		for(Product p:products.values())
			if(p.getDescription().equals(description))
				return p.getPrice();
		if(backup == null)
			return 0;
		else
			return backup.getPrice(description);
	}

	
	public Product getProduct(List<String> keywords)throws RemoteException
	{
		for(String keyword:keywords)
		{
			Product p = products.get(keyword);
			if(p != null)
				return p;
		}
		
		if(backup != null)
			return backup.getProduct(keywords);
		else if(products.values().size() > 0)
			return products.values().iterator().next();
		else
			return null;
	}
	
	private Map<String,Product> products;
	private Warehouse backup;
}