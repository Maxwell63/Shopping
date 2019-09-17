package lifestyle.solutions.com.shopping;

import android.app.SearchManager;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class HomeActivity extends AppCompatActivity{

    //options menu

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "https://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://www.jscotthypnosis.com/wp-content/plugins/doptg/uploads/nLhYh9WnDKO33xHs3k9XKZbGfLfBRHkhx8zz5r4Asp5Kkyyapheek4m7CjfzdC2Ex.jpg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PRODUCT = "product";
    static final String TAG_INDIVIDUAL_PRODUCT = "individual product";
    private static final String TAG_CART ="cart";
    private static final String TAG_ORDER_HISTORY = "order history";
    private static final String TAG_DELIVERY = "delivery";
    private static final String TAG_HELP = "help";
    static final String TAG_CHECKOUT = "checkout";
    static final String TAG_SEARCH = "search";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_SIGN_IN = "sign in";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private SearchView searchView;
    private TextView tvNumCartItems;
    public static int mCartItemCount = 0; //get amount of items


    void updateCart(final boolean itemAdded){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (itemAdded)
                    mCartItemCount++;
                else {
                    if (mCartItemCount != 0)
                        mCartItemCount--;
                }
                setupBadge();
                Toast.makeText(HomeActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_search:
                //Toast.makeText(HomeActivity.this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cart:
                /**
                 * Load cart fragment
                 */

                navItemIndex = 4;
                CURRENT_TAG = TAG_CART;
                loadHomeFragment();

                break;
            default:
                break;
        }

        //noinspection SimplifiableIfStatement
        /**if (id == R.id.action_sign_out) {
            signOut();
            return true;
        }*/

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main_options, menu);
        //getMenuInflater().inflate(R.menu.app_bar_options, menu);


        /**
         * invalidateOptionsMenu() changes menu options
         */

        getMenuInflater().inflate(R.menu.app_bar_options, menu);


        final MenuItem cartItem = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(cartItem, R.layout.cart_menu_item);
        View actionView = MenuItemCompat.getActionView(cartItem);
        tvNumCartItems = (TextView) actionView.findViewById(R.id.actionbar_notifcation_textview);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(cartItem);
            }
        });



        MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    //some operation
                    /**
                     * return to current fragment
                     */
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some operation
                }
            });

            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint("Search products");
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            // use this method for search process

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // use this method when query submitted
                    /**
                     * show search fragment results
                     */
                    Toast.makeText(HomeActivity.this, query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // use this method for auto complete search process
                    /**
                     * show search fragment results
                     */
                    return false;
                }
            });

            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
         }

       //return super.onCreateOptionsMenu(menu);

        return true;
    }

    private void setupBadge() {
        if (tvNumCartItems != null) {
            if (mCartItemCount == 0) {
                if (tvNumCartItems .getVisibility() != View.GONE) {
                    tvNumCartItems .setVisibility(View.GONE);
                }
            } else {
                tvNumCartItems .setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (tvNumCartItems .getVisibility() != View.VISIBLE) {
                    tvNumCartItems .setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void signOut(){
        /**FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();

// this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MapsActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };*/
    }

    private void loadNavHeader() {
        // name, website
        txtName.setText("Katekani Hlungwani");
        txtWebsite.setText("XG 12 GH GP");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        //navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                return new HomeFragment();
            case 1:
                // products
                return new ProductsFragment();//ProductPageFragment();
            case 2:
             // help
                //return new HelpFragment();
                break;
             case 3:
             // settings
                break;
             case 4:
                 //cart
                 return new CartFragment();
            case 5:
                // order
                return new OrderHistoryFragment();
            case 6:
                // delivery
                return new DeliveryFragment();
            case 7:
                // sign up/sign in
                break;

            default:
                return new HomeFragment();
        }
        return new HomeFragment();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        if (navItemIndex > 3) {
            navigationView.getMenu().getItem(3).getSubMenu().getItem(navItemIndex-3).setChecked(true);
            return;
        }
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.nav_products:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PRODUCT;
                        break;

                    case R.id.nav_help:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_HELP;
                        break;

                    case R.id.nav_user_settings:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTINGS;
                        startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                        drawer.closeDrawers();
                        return true;

                    /**case R.id.nav_cart:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_CART;
                        break;*/

                    case R.id.nav_order_history:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_ORDER_HISTORY;
                        break;

                    /**case R.id.nav_delivery:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_DELIVERY;
                        break;*/

                    case R.id.nav_signin:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_SIGN_IN;
                        //if not signed in then sign in or sign out
                        if (true)//(!signedIn)
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        else
                            signOut();
                        drawer.closeDrawers();
                        return true;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        /**if (!searchView.isIconified()) {
            searchView.setIconified(true);
            findViewById(R.id.default_title).setVisibility(View.VISIBLE);
        }*/

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

}
