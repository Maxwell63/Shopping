package lifestyle.solutions.com.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ListAdapter mAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("My Account");

        lv = (ListView) findViewById(R.id.listView);

        mAdapter = new ListAdapter(this);

        fillListView();

        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SettingsActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void fillListView(){
        mAdapter.addSectionHeaderItem("Profile");
        mAdapter.addItem("User Profile");
        mAdapter.addItem("Delivery Address");
        mAdapter.addItem("Saved Cards");

        mAdapter.addSectionHeaderItem("About");
        mAdapter.addItem("Help");
        mAdapter.addItem("FAQ");
        mAdapter.addItem("Returns Policy");
        mAdapter.addItem("Delivery Policy");
        mAdapter.addItem("Terms & Conditions");
        mAdapter.addItem("Contact Us");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}