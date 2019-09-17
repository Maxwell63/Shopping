package lifestyle.solutions.com.shopping;

/**
 * Created by HLNKA on 2017/09/11.
 */

public class Product {
    private final String title;
    private final String description;
    private final double price;
    private final int imageResource;
    private boolean isFavorite = false;
    private final String imageUrl;
    private final String category;
    private final int stockAvailable;

    public Product(String title, String description, String category, double price, int stockAvailable,int imageResource, String imageUrl) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageResource = imageResource;
        this.imageUrl = imageUrl;
        this.stockAvailable = stockAvailable;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }


    public int getImageResource() {
        return imageResource;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
