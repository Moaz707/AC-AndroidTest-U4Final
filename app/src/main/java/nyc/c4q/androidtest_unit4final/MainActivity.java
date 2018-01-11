package nyc.c4q.androidtest_unit4final;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ColorAdapter adapter;
    protected HashMap<String, String> colorDict;
    protected List<String> colorsList;
    private RecyclerView recyclerView;
    private String TAG = MainActivity.class.getSimpleName();
    private String black, blue, red, purple, indigo, orange, brown, green;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorDict = new HashMap<>();
        colorsList = new ArrayList<>();

        String url = "https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String jsonData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(jsonData);

                    for (int i = 0; i < jsonObject.length(); i++) {
                        String colorName = jsonObject.names().get(i).toString();
                        colorDict.put(colorName, jsonObject.get(colorName).toString());
                        colorsList.add(colorName);
                    }
                    Log.d(TAG, "onResponse: " + colorDict.size());
                    Log.d(TAG, "onResponse:........................ ");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        Log.d(TAG, "onCreate: SizeHas" + colorDict.size());
        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new ColorAdapter(colorsList, colorDict, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dealy();
    }

    private void dealy() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        }, 5000);
    }

    // TODO: Add options menu with the item "Info" which is always visible
    // TODO: When "Info" menu item is clicked, display the fragment InfoFragment
    // TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
    // Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml

    //*


    public void makeRequestWithOkHttp() {


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "choosed: " + item.toString(), Toast.LENGTH_SHORT).show();
        LinearLayout fragment_container;
        fragment_container = (LinearLayout) findViewById(R.id.fragment_container);

        if (fragment_container.getVisibility() == View.VISIBLE) {
            fragment_container.setVisibility(View.GONE);
        } else {
            fragment_container.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }


}
