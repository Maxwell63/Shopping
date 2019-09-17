package lifestyle.solutions.com.shopping;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import static lifestyle.solutions.com.shopping.HomeActivity.CURRENT_TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CartRecyclerViewAdapter mAdapter;
    private Button btnCheckout;
    ArrayList<CartItem> cart;

    //Todo add total price
    //private Handler mHandler;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewRoot = inflater.inflate(R.layout.fragment_cart, container, false);

        btnCheckout = (Button) viewRoot.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        // update the main content by replacing fragments
                        CheckoutFragment fragment = new CheckoutFragment();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                        fragmentTransaction.commitAllowingStateLoss();
            }
        });
        
        mRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        cart = getItems();
        mAdapter = new CartRecyclerViewAdapter(cart, this, getContext());
        mRecyclerView.setAdapter(mAdapter);
        initSwipe();
        cartEmpty();

        return viewRoot;
    }


    void cartEmpty(){
        if (HomeActivity.mCartItemCount == 0){
            //todo
            //display image with cart empty
            //btnCheckout.setVisibility(View.INVISIBLE);
            //go to home fragment
        }
    }

    ArrayList<CartItem> getItems(){
        ArrayList<CartItem> items = new ArrayList<CartItem>();

        for (int i = 0; i < HomeActivity.mCartItemCount; i++) {
            items.add(new CartItem("Title", "http://d3onj95025fh63.cloudfront.net/image/image/data/" +
                    "1Kanger/five-6/kanger-five-6-222w-vape-kit-2-600x600.jpg", "This is an item",
                    10.00, 5, 2));
        }
        //Todo notifyDataset changed
        return items;
    }

    private View view;
    private boolean add = false;
    private Paint p = new Paint();


    public void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback
                //(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                (0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Log.e("Position", Integer.toString(position));

                if (direction == ItemTouchHelper.LEFT){
                    mAdapter.deleteItem(position);
                    cartEmpty();
                    //Todo notify db to delete the cart item
                    ((HomeActivity)getContext()).updateCart(false);
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){

                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.trash);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

}
