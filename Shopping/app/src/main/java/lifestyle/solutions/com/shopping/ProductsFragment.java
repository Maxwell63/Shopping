package lifestyle.solutions.com.shopping;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    Handler mHandler;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);


        mHandler = new Handler();
        GridView gridView = (GridView)rootView.findViewById(R.id.gridview);
        final ProductsAdapter productsAdapter = new ProductsAdapter(getContext(), products);
        gridView.setAdapter(productsAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Product product = products[position];
                //product.toggleFavorite();
                //productsAdapter.notifyDataSetChanged();

                //start product page fragment

                Runnable mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        IndividualProductFragment fragment = new IndividualProductFragment();
                        Bundle args = new Bundle();
                        args.putInt("id", 0);
                        args.putString("title", "Title");
                        args.putString("description", "An Item");
                        args.putInt("stock", 10);
                        args.putDouble("price", 10.00);
                        fragment.setArguments(args);

                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame, fragment, HomeActivity.TAG_INDIVIDUAL_PRODUCT);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }

            }
        });

        return rootView;

    }

    private final static Product[] products = {

        new Product("Title", "Description", "Category",10.00, 10,R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg"),
        new Product("Title", "Description", "Category",10.00, 10, R.mipmap.pawn,
                "http://d3onj95025fh63.cloudfront.net/image/image/data/1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg")
    };

}
/**


*/