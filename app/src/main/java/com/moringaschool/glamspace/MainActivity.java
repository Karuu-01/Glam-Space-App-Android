package com.moringaschool.glamspace;

import static com.moringaschool.glamspace.Constants.UNSPLASH_API_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.glamspace.adapters.ImagesAdapter;
import com.moringaschool.glamspace.models.ImageSearch;
import com.moringaschool.glamspace.models.Result;
import com.moringaschool.glamspace.network.UnsplashClient;
import com.moringaschool.glamspace.network.UnsplashPhotosApi;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private ImagesAdapter adapter;
@SuppressLint("NonConstantResourceId")
@BindView(R.id.recyclerView1) RecyclerView recyclerView;
@BindView(R.id.tvpleaseWait) TextView pleaseWaitText;
@BindView(R.id.progress_circular) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        hideRecyclerView();//hide recycler
        UnsplashPhotosApi client = UnsplashClient.getClient();
        Call<ImageSearch> call = client.getImages(UNSPLASH_API_KEY,query,"1","30");
        showProgressBar();//show progressbar
        call.enqueue(new Callback<ImageSearch>() {
            @Override
            public void onResponse(Call<ImageSearch> call, Response<ImageSearch> response) {

            if(response.isSuccessful()){

                assert response.body() != null;
                List<Result> results = response.body().getResults();
                adapter = new ImagesAdapter(MainActivity.this, results);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layoutManager= new GridLayoutManager(MainActivity.this,2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                hideProgressBar();//hide progressbar
                showRecycler();//show recyclerview

            }
            }

            @Override
            public void onFailure(Call<ImageSearch> call, Throwable t) {

            }
        });
    }
    public void showProgressBar(){//show progressbar
        progressBar.setVisibility(View.VISIBLE);
        pleaseWaitText.setVisibility(View.VISIBLE);
    }
    public void hideProgressBar(){ // hide progressbar
        progressBar.setVisibility(View.GONE);
        pleaseWaitText.setVisibility(View.GONE);
    }
    public void hideRecyclerView(){// hide recyclerview
        recyclerView.setVisibility(View.GONE);
    }
    public void showRecycler(){ // show recyclerview
        recyclerView.setVisibility(View.VISIBLE);
    }

}