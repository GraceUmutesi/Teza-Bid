package com.example.teza_bid;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class Drawer extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        NavigationView nvDrawer =(NavigationView) findViewById(R.id.nav);
        mToggle.syncState();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setDrawerContent(nvDrawer);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem){
        Fragment myFrament = null;
        Class fragClass;
        switch (menuItem.getItemId()){
            case R.id.nav_Buy:
                fragClass = Categories.class;
                break;

            default:
                fragClass = Categories.class;

        }
        try{
            myFrament = (Fragment) fragClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.catContent,myFrament);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }
    private void setDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }

}
