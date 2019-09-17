package lifestyle.solutions.com.shopping;

/**
 * Created by 63 on 2017/09/20.
 */

public class CartItem {
    String mTitle;
    double mPrice;
    String mURL;
    String mDescription;
    int mMaxQuantity;
    int mQuantityChosen;

    public CartItem(String mTitle, String mURL, String mDescription, double mPrice, int mMaxQuantity, int mQuantityChosen){
        this.mTitle = mTitle;
        this.mURL = mURL;
        this.mDescription = mDescription;
        this.mPrice = mPrice;
        this.mMaxQuantity = mMaxQuantity;
        this.mQuantityChosen = mQuantityChosen;
    }
}
