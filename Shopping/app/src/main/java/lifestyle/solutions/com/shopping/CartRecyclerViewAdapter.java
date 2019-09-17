package lifestyle.solutions.com.shopping;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HLNKA on 2017/09/20.
 */

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.DataObjectHolder> {

    private static String LOG_TAG = "CartRecyclerViewAdapter";
    private ArrayList<CartItem> mDataset;
    private static MyClickListener myClickListener;

    private Fragment mFragment;
    private Context mContext;
    private Integer[] items;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView name, price, desc;
        ImageView imgProduct;
        Spinner spinner;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgItem);
            name = (TextView) itemView.findViewById(R.id.tvItemName);
            price = (TextView) itemView.findViewById(R.id.tvPriceTotal);
            desc = (TextView) itemView.findViewById(R.id.tvDesc);
            spinner = (Spinner) itemView.findViewById(R.id.spinnerItemCount);

            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public CartRecyclerViewAdapter(ArrayList<CartItem> myDataset, Fragment myFragment, Context myContext) {
        mDataset = myDataset;
        mFragment = myFragment;
        mContext = myContext;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_card_view, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    //public void onBindViewHolder(DataObjectHolder holder, int position) {
    public void onBindViewHolder(final DataObjectHolder holder, int position) {

        int max = mDataset.get(position).mMaxQuantity;
        items = new Integer[max];
        for (int i = 0; i < max; i++){
            int value = i;
            items[i] = value+1;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(mContext,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(0);
        holder.spinner.setAdapter(adapter);

        // Loading profile image
        Glide.with(mFragment).load(mDataset.get(position).mURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgProduct);

        holder.name.setText(mDataset.get(position).mTitle);
        holder.desc.setText(mDataset.get(position).mDescription);
        holder.price.setText("ZAR" + Double.toString(mDataset.get(position).mPrice * mDataset.get(position).mQuantityChosen));

        //holder.spinner.setSelection(items[mDataset.get(position).mQuantityChosen-1]);
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) holder.spinner;
                final int qty = spinner.getSelectedItemPosition();
                //Toast.makeText(mContext, Integer.toString(qty), Toast.LENGTH_SHORT).show();

                //holder.price.setText("ZAR" + mDataset.get(position).mPrice * items[qty]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner*/

    }


    public void addItem(CartItem dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
