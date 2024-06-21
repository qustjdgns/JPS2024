package producteg;

public class Product {
	private String id;
	private String name;
	private int price;
	private String date;	
	private String marker;
	
	public Product(String id, String name, int price, String date, String marker) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
		this.marker = marker;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}
	
	
}

