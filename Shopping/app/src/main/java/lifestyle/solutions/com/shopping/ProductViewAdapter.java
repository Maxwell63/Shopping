package lifestyle.solutions.com.shopping;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HLNKA on 2017/09/11.
 */

public class ProductViewAdapter extends PagerAdapter {

    private Activity _activity;
    //private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;
    private ArrayList<Integer> _productImages;


    // constructor
    public ProductViewAdapter(Activity activity,
                              ArrayList<Integer> productImages){
                                  //ArrayList<String> imagePaths) {
        this._activity = activity;
        this._productImages = productImages;
    }

    @Override
    public int getCount() {
        return this._productImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        //TextView tvTitle, tvDescription, tvPrice, tvStock;
        //Button btnClose;
        //FloatingActionButton fabCart;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.full_product_image, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgProduct);
        /**tvTitle = (TextView) viewLayout.findViewById(R.id.tvTitle);
        tvDescription = (TextView) viewLayout.findViewById(R.id.tvDescription);
        tvStock = (TextView) viewLayout.findViewById(R.id.tvStockAvail);
        tvPrice = (TextView) viewLayout.findViewById(R.id.tvPrice);
        fabCart = (FloatingActionButton) viewLayout.findViewById(R.id.btnCart);*/

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        //Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        //imgDisplay.setImageBitmap(bitmap);

        //Picasso.with(mContext).load(product.getImageUrl()).into(viewHolder.imageViewCoverArt);
        imgDisplay.setImageResource(_productImages.get(position));
        /**tvTitle.setText(_product.getTitle());
        tvPrice.setText("Remaining Stock: " + Integer.toString(_product.getStockAvailable()));
        tvStock.setText("R" + Double.toString(_product.getPrice()));
        tvDescription.setText(_product.getDescription());
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start cart fragment
            }
        });

        // close button click event
        /**btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });*/

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
