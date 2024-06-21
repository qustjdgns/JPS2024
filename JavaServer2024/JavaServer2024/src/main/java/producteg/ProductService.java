package producteg;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ProductService {
	Map<String, Product> products = new HashMap<>();
	
	public ProductService()
	{
		super();
		Product p = new Product("101", "딥러닝", 2000, "2024.04.29", "신구출판사");
		products.put("101", p);
		Product p2 = new Product("102", "텐서플로", 3000, "2024.04.29", "신구출판사");
		products.put("102", p2);
		Product p3 = new Product("103", "빅데이터", 4000, "2024.04.29", "신구출판사");
		products.put("103", p3);	
	}
	
	public List<Product> findAll()
	{
		return new ArrayList<>(products.values());
		
	}
	
	public Product find(String id)
	{
		return products.get(id);
		
	}
}
