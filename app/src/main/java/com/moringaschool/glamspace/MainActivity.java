package com.moringaschool.glamspace;

import static com.moringaschool.glamspace.Constants.UNSPLASH_API_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.moringaschool.glamspace.models.ImageSearch;
import com.moringaschool.glamspace.network.UnsplashClient;
import com.moringaschool.glamspace.network.UnsplashPhotosApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchImages(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        inflater.inflate(R.menu.menu_menu, menu);
        return true;
    }

    private void fetchImages(String query) {
        UnsplashPhotosApi client = UnsplashClient.getClient();
        Call<ImageSearch> call = client.getImages(UNSPLASH_API_KEY,query,"1","30");
        call.enqueue(new Callback<ImageSearch>() {
            @Override
            public void onResponse(Call<ImageSearch> call, Response<ImageSearch> response) {

            }

            @Override
            public void onFailure(Call<ImageSearch> call, Throwable t) {

            }
        });
    }
}