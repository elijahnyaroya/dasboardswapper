package com.crypsol.dashboard_swapper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crypsol.dashboard_swapper.CA.dashboardCA;
import com.crypsol.dashboard_swapper.DMC.dashboardDMC;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class dashboard extends AppCompatActivity implements dashboardCA.OnItemClickListener {
    private dashboardCA instance;
    private ArrayList<dashboardDMC> dmc;
    RequestQueue mRequestQueue;
    RecyclerView mRecyclerView;
     View main;
    genericScreenShot screenShot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        main = findViewById(R.id.main);
        /*View view = getWindow().getDecorView().getRootView(); //Getting activity view
        screenShot = new genericScreenShot(view,dashboard.this);*/

        sessionmanager session = new sessionmanager(dashboard.this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewCycling);
        mRequestQueue = Volley.newRequestQueue(this);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutSetter(2);
        } else {
            layoutSetter(4);
        }

        dmc = new ArrayList<>();
        pullShowWheels();

    }

    public void layoutSetter(int tileNo){
        String layoutView = sessionmanager.getLayoutView();

        switch (layoutView){
            case "listview_dashboard_view":
                mRecyclerView.setLayoutManager(new LinearLayoutManager(dashboard.this));
                mRecyclerView.setHasFixedSize(true);
                break;
            case "tiled_dashboard_view":
                mRecyclerView.setLayoutManager(new GridLayoutManager(dashboard.this, tileNo));
                mRecyclerView.setHasFixedSize(true);
                break;
            case "staggeredgridiew":
                 StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(tileNo, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;

            case "filmstrip":
                LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(dashboard.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(horizontalLayoutManagaer);
                break;
        }

        FabSpeedDial fabSpeedDial = (FabSpeedDial)findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.tiledlayout:
                        sessionmanager.setLayoutView("tiled_dashboard_view");
                        Intent intent1 = new Intent(dashboard.this,dashboard.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.linearlayout:
                        sessionmanager.setLayoutView("listview_dashboard_view");
                        Intent intent2 = new Intent(dashboard.this,dashboard.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.StaggeredGridLayoutManager:
                        sessionmanager.setLayoutView("staggeredgridiew");
                        Intent intent3 = new Intent(dashboard.this,dashboard.class);
                        startActivity(intent3);
                        finish();
                        break;
                     case R.id.filmstrip:
                        sessionmanager.setLayoutView("filmstrip");
                        Intent intent4 = new Intent(dashboard.this,dashboard.class);
                        startActivity(intent4);
                        finish();
                        break;

                }

                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

    public void pullShowWheels() {
        String url = "https://choop.one/ChoopApp/pullshowwheel.php";

        StringRequest request = new StringRequest(Request.Method.POST,  url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response Data "+response);
                        try {
                            JSONArray responses = new JSONArray(response);

                            for (int i = 0; i < responses.length(); i++) {
                                JSONObject hit = responses.getJSONObject(i);
                                String AppID = hit.getString("AppID");
                                String DashBoardID = hit.getString("DashBoardID");
                                String imagePath = hit.getString("imagePath");
                                String image = hit.getString("image");
                                // The language picked below is a result of a previous choice. This language is being used to select the ShowID slides.
                                String Language = hit.getString("Language");
                                String showwheel_title = hit.getString("showwheel_title");
                                String ShowID = hit.getString("ShowID");
                                String payable = hit.getString("payable");
                                String Price = hit.getString("costperTile");
                                String PriceCurrency = hit.getString("PriceCurrency");

                                /////// New fields..
                                String paid = hit.getString("paid");
                                String CommissionPercent = hit.getString("commissionPercent");
                                String DisplayOrder = hit.getString("displayOrder");
                                String tileAvailability = hit.getString("tileAvailability");
                                String expirydate = hit.getString("expirydate");
                                String QueryResponseUpTo63 = hit.getString("QuizResponseUpTo63");
                                String CreatedUnixDate = hit.getString("createdUnixDate");
                                String NoOfQuizzes = hit.getString("noOfQuizzes");
                                String payBillAccount = hit.getString("payBillAccount");
                                String ownerPhone = hit.getString("ownerPhone");
                                String DeletePhoneNumber = hit.getString("DeletePhone");

                                dmc.add(new dashboardDMC(
                                        DeletePhoneNumber,
                                        AppID,
                                        DashBoardID,
                                        ownerPhone,
                                        imagePath,
                                        image,
                                        Language,
                                        showwheel_title,
                                        ShowID,
                                        payable,
                                        Price,
                                        PriceCurrency,
                                        paid,
                                        CommissionPercent,
                                        DisplayOrder,
                                        tileAvailability,
                                        expirydate,
                                        QueryResponseUpTo63,
                                        CreatedUnixDate,
                                        NoOfQuizzes,
                                        payBillAccount));
                                instance = new dashboardCA(dashboard.this, dmc);
                                mRecyclerView.setAdapter(instance);
                                instance.setOnItemClickListener(dashboard.this);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(dashboard.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("myphone", "+254721729081");
                params.put("appName", getResources().getString(R.string.app_name));
                params.put("language", "ENG");
                params.put("imeiNumber", "25b7aec6b6db9ebca6428ebf5beb78");


                return params;
            }
        };

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
      dashboardDMC object = dmc.get(position);
        AlertDialog.Builder mB = new AlertDialog.Builder(dashboard.this);

        //View displayView = AlertDialog.class.cast(mB).getWindow().getDecorView().getRootView();


        screenShot = new genericScreenShot(main,dashboard.this);

        screenShot.captureScreenShotTrigger();
        mB.setMessage("Get View Data")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        screenShot.captureScreenShotTrigger();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        screenShot.captureScreenShotTrigger();
                    }
                })
                .create()
                .show();
    }

}