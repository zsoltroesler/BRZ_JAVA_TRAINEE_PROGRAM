package at.gv.brz.roeslerz.inventur;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import at.gv.brz.roeslerz.inventur.adapters.ProduktAdapter;
import at.gv.brz.roeslerz.inventur.models.Produkt;
import at.gv.brz.roeslerz.inventur.utils.APIUtils;
import at.gv.brz.roeslerz.inventur.utils.ProduktApiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String DETAILS_PRODUKT_KEY = "Details product key";

    private ArrayList<Produkt> produktListe = new ArrayList<>();
    private ProduktAdapter produktAdapter;
    private ProduktApiService produktApiService;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView((R.id.fab))
    FloatingActionButton fab;
    @BindView(R.id.pb)
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pb.setVisibility(View.VISIBLE);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        produktApiService = APIUtils.getProduktService();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProduktIntent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(addProduktIntent);
            }
        });

        getProdukte();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //int id = (int) viewHolder.itemView.getTag();
                int id = viewHolder.getAdapterPosition();
                Log.i("TEST: ", "onSwiped() called" + id);
                Produkt produkt = produktListe.get(id);
                Log.i(LOG_TAG, "TEST: deleted produkt: " + produkt);
                deleteProdukt(produkt);

                getProdukte();
            }
        }).attachToRecyclerView(recyclerView);
    }

    /**
     * Retrofit GET call
     */
    public void getProdukte() {
        Call<ArrayList<Produkt>> call = produktApiService.getProdukt();

        // .enqueue () method makes the call async
        call.enqueue(new Callback<ArrayList<Produkt>>() {
            @Override
            public void onResponse(Call<ArrayList<Produkt>> call, Response<ArrayList<Produkt>> response) {
                Log.i("RETROFIT", "onResponse() called");
                if (response.isSuccessful()) {
                    pb.setVisibility(View.GONE);
                    produktListe = response.body();
                    produktAdapter = new ProduktAdapter(produktListe, new ProduktAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Produkt produkt) {
                            Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                            detailsIntent.putExtra(DETAILS_PRODUKT_KEY, produkt);
                            startActivity(detailsIntent);
                        }
                    });
                    recyclerView.setAdapter(produktAdapter);
                    produktAdapter.setProduktList(produktListe);
                    produktAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Produkt>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fehler ist aufgetreten", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Retrofit DELETE call
     */
    public void deleteProdukt(Produkt produkt) {
        Call<Produkt> call = produktApiService.deleteProdukt(produkt);
        call.enqueue(new Callback<Produkt>() {

            @Override
            public void onResponse(Call<Produkt> call, Response<Produkt> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Produkt erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produkt> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            getProdukte();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


