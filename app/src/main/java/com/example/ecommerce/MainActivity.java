package com.example.ecommerce;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static ImageView menu, back, cart;
    public static DrawerLayout drawerLayout;
    public static TextView title, userNameTxt, userMobileNumberTxt, searchProduct, developedCompany, cartCount;
    public static LinearLayout toolbarContainer;
    public static String userId, currency = "Rs.", userName, userNumber, userPassword;
    public static NavigationView navigationView;
    public static BottomNavigationView bottomNavigationView;
    public static ProgressBar progressBar;
    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarContainer = findViewById(R.id.toolbar_container);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        initViews();

        loadFragment(new Home(), false);

        navigationView = findViewById(R.id.navigationView);
        View header = navigationView.getHeaderView(0);
        userNameTxt = header.findViewById(R.id.userName);
        userMobileNumberTxt = header.findViewById(R.id.userMobileNumber);

       /* BadgeDrawable badgeDrawable = bottomNavigationView.getBadge(R.id.cart);
        if (badgeDrawable != null) {
            bottomNavigationView.getOrCreateBadge(R.id.cart).setNumber(10);
        } else {
            bottomNavigationView.removeBadge(R.id.cart);
        }*/

        userNameTxt.setText(userName);
        userMobileNumberTxt.setText(userNumber);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!MainActivity.drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    MainActivity.drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MyCart(),true);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        loadFragment(new Home(), false);
                        MainActivity.drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.cart:
                        loadFragment(new MyCart(), true);
                        MainActivity.drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.order:
                        loadFragment(new MyOrder(), true);
                        MainActivity.drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.profile:
                        loadFragment(new Profile(), true);
                        MainActivity.drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.share:


                    case R.id.rateus:
                        // perform click on Rate Category
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                        break;

                    case R.id.callus:
                        call();
                        break;


                }

                return false;
            }
        });

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home:
//                        loadFragment(new Home(), false);
//                        break;
//                    case R.id.cart:
//                       // loadFragment(new MyCart(), true);
//                        break;
//                    case R.id.history:
//                       // loadFragment(new OrderHistory(), true);
//                        break;
//                    case R.id.profile:
//                      //  loadFragment(new Setting(), true);
//                        break;
//                }
//                return true;
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCurrentFragmentAndMoveBack();
            }
        });

    }

    @SuppressLint("CutPasteId")
    private void initViews() {

        drawerLayout = findViewById(R.id.drawer_layout);
        title = findViewById(R.id.title);
        searchProduct = findViewById(R.id.searchProduct);
        menu = findViewById(R.id.menu);
      //progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.back);
        cart = findViewById(R.id.cart);
        cartCount = findViewById(R.id.cartCount);
       // bottomNavigationView = findViewById(R.id.bottomNavigationView);
        developedCompany = findViewById(R.id.developedCompany);
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
       // intent.setData(Uri.parse("tel:" + getResources().getString(R.string.contactNo)));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // double press to exit
        if (menu.getVisibility() == View.VISIBLE) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
        } else {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(MainActivity.this, "Press back one more to exit", Toast.LENGTH_SHORT).show();
       // Toasty.normal(MainActivity.this, "Press back once more to exit", Toasty.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    public void lockUnlockDrawer(int lockMode) {
        drawerLayout.setDrawerLockMode(lockMode);
        if (lockMode == DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            menu.setVisibility(View.GONE);
            searchProduct.setVisibility(View.GONE);
            cart.setVisibility(View.VISIBLE);
            cartCount.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
        } else {
            menu.setVisibility(View.VISIBLE);
            searchProduct.setVisibility(View.VISIBLE);
            cart.setVisibility(View.VISIBLE);
            cartCount.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
        }
    }

    public void removeCurrentFragmentAndMoveBack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    public void loadFragment(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        if (bool) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}