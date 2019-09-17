package lifestyle.solutions.com.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by 63 on 2017/09/11.
 */

public class ProductsAdapter extends BaseAdapter {
    private final Context mContext;
    private final Product[] products;

    public ProductsAdapter(Context context, Product[] products) {
        this.mContext = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Product product = products[position];

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_product, null);

            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final TextView titleTextView = (TextView)convertView.findViewById(R.id.textview_title);
            final TextView descriptionTextView = (TextView)convertView.findViewById(R.id.textview_description);
            final TextView priceTextView = (TextView)convertView.findViewById(R.id.textview_price);
            final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

            final ViewHolder viewHolder = new ViewHolder(titleTextView, descriptionTextView,
                    priceTextView, imageViewCoverArt, imageViewFavorite);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
//    viewHolder.imageViewCoverArt.setImageResource(book.getImageResource());
        viewHolder.titleTextView.setText(product.getTitle());
        viewHolder.descriptionTextView.setText(product.getDescription());
        viewHolder.priceTextView.setText(Double.toString(product.getPrice()));
        viewHolder.imageViewFavorite.setImageResource(product.getIsFavorite() ? R.mipmap.star_enabled : R.mipmap.star_disabled);

        Picasso.with(mContext).load(product.getImageUrl()).into(viewHolder.imageViewCoverArt);

        return convertView;
    }

    private class ViewHolder {
        private final TextView titleTextView;
        private final TextView priceTextView;
        private final TextView descriptionTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewFavorite;

        public ViewHolder(TextView titleTextView, TextView descriptionTextView, TextView priceTextView, ImageView imageViewCoverArt, ImageView imageViewFavorite) {
            this.titleTextView = titleTextView;
            this.descriptionTextView = descriptionTextView;
            this.priceTextView = priceTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewFavorite = imageViewFavorite;
        }
    }
}
