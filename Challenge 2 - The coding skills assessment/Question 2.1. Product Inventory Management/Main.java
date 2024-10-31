import java.util.ArrayList;

class Product{
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }
}

class ProductInventoryManagement{
    private ArrayList<Product> productList = new ArrayList<>();

    public ProductInventoryManagement(ArrayList<Product> productList){
        for(Product p: productList){
            this.productList.add(p);
        }
    }

    public double calcTotalInventoryValue(){
        double result = 0;
        for(Product p : this.productList){
            result += (p.getPrice() * p.getQuantity()); 
        }
        
        return Math.round(result * 100.0) / 100.0;
    }

    public String findTheMostExpensiveProduct(){
        if(this.productList.isEmpty()){
            return "";
        }
        Product max = productList.get(0);

        for(Product p : this.productList){
            if(max.getPrice() < p.getPrice()){
                max = p;
            }
        }

        return max.getName();

    }

    public boolean isInStock(String name){
        for(Product p : this.productList){
            if(p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void printProductList() {
        for (Product p : productList) {
            System.out.println("Product Name: " + p.getName() + ", Price: " + p.getPrice() + ", Quantity: " + p.getQuantity());
        }
    }

    public void sortWithOptions(String option, String sortBy){
        boolean ascending = option.equalsIgnoreCase("ascending");

        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = 0; j < productList.size() - i - 1; j++) {
                boolean condition = false;

                if (sortBy.equalsIgnoreCase("price")) {
                    condition = ascending 
                        ? productList.get(j).getPrice() > productList.get(j + 1).getPrice()
                        : productList.get(j).getPrice() < productList.get(j + 1).getPrice();
                } else if (sortBy.equalsIgnoreCase("quantity")) {
                    condition = ascending 
                        ? productList.get(j).getQuantity() > productList.get(j + 1).getQuantity()
                        : productList.get(j).getQuantity() < productList.get(j + 1).getQuantity();
                }

                // Swap elements if the condition is met
                if (condition) {
                    Product temp = productList.get(j);
                    productList.set(j, productList.get(j + 1));
                    productList.set(j + 1, temp);
                }
            }
        }
    }
}

public class Main{
   public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99, 5));
        products.add(new Product("Smartphone B", 499.99, 10));
        products.add(new Product("Tablet", 299.99, 0));
        products.add(new Product("Smartwatch", 199.99, 3));

        ProductInventoryManagement inventory = new ProductInventoryManagement(products);

        System.out.println("Calculate the total inventory value: ");
        System.out.println(inventory.calcTotalInventoryValue());

        System.out.println("\nFind the most expensive product: ");
        System.out.println(inventory.findTheMostExpensiveProduct());

        System.out.println("\nCheck if a product named Headphone is in stock: ");
        System.out.println(inventory.isInStock("Headphone"));

        System.out.println("\nBefore sorting:");
        inventory.printProductList();

        inventory.sortWithOptions("ascending", "price");
        System.out.println("\nAfter sorting by price ascending:");
        inventory.printProductList();

        inventory.sortWithOptions("descending", "quantity");
        System.out.println("\nAfter sorting by quantity descending:");
        inventory.printProductList();
   } 
}