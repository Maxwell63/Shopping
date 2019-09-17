package lifestyle.solutions.com.shopping;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


/**
 * Todo ADD DETAILS OF ITEM
 */
public class IndividualProductFragment extends Fragment {

    private ViewPager viewPager;

//hide action bar
    /**
     * ActionBar actionBar = getActionBar();
     actionBar.hide();
     */

    //chnage action bar values
    /**
     * setHasOptionsMenu(true)
     */
    public IndividualProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View rootView = inflater.inflate(R.layout.fragment_individual_product, container, false);
        View rootView = inflater.inflate(R.layout.fragment_individual_product, container, false);

                // get intent data
        Bundle extras = getArguments();

        // Selected image id
        int position = extras.getInt("id");
        int avail = extras.getInt("stock");
        String title = extras.getString("title");
        String description = extras.getString("description");
        Double price = extras.getDouble("price");

        ///viewPager = (ViewPager) rootView.findViewById(R.id.pager);

        //utils = new Utils(getApplicationContext());

        //Intent i = getIntent();
        //int position = i.getIntExtra("position", 0);

        //ProductViewAdapter adapter = new ProductViewAdapter(getActivity(), images);

        //viewPager.setAdapter(adapter);

        // displaying selected image first
        //viewPager.setCurrentItem(position);

        /**ArrayList<Integer> images =new ArrayList<Integer>();
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);**/

        Product product = new Product(title, description, "", price, avail, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg");

        ImageView imgProduct = (ImageView) rootView.findViewById(R.id.imgProduct);
        TextView tvProductTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        TextView tvProductPrice = (TextView) rootView.findViewById(R.id.tvPrice);
        TextView tvProductStock = (TextView) rootView.findViewById(R.id.tvStockAvail);
        TextView tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.btnCart);


        // Loading profile image
        Glide.with(this).load(product.getImageUrl())
                .crossFade()
                //.thumbnail(0.5f)
                //.bitmapTransform(new CircleTransform(getContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProduct);
        tvProductTitle.setText(title);
        tvProductPrice.setText("Remaining Stock: " + Integer.toString(avail));
        tvProductStock.setText("R" + Double.toString(price));
        tvDescription.setText(description);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to cart

                ((HomeActivity)getContext()).updateCart(true);

                //todo if item added then go back to previous products fragment
            }
        });


        return rootView;
    }

}
