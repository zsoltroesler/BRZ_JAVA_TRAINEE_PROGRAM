package at.gv.brz.roeslerz.inventur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.gv.brz.roeslerz.inventur.models.Produkt;
import at.gv.brz.roeslerz.inventur.utils.APIUtils;
import at.gv.brz.roeslerz.inventur.utils.ProduktApiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static at.gv.brz.roeslerz.inventur.MainActivity.DETAILS_PRODUKT_KEY;

public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailsActivity.class.getSimpleName();

    private Produkt produkt = null;
    private ProduktApiService produktApiService;


    @BindView(R.id.editText_pid)
    EditText et_pid;
    @BindView(R.id.editText_lid)
    EditText et_lid;
    @BindView(R.id.editText_id)
    EditText et_produktId;
    @BindView(R.id.editText_produkt)
    EditText et_produkt;
    @BindView(R.id.editText_preis)
    EditText et_preis;
    @BindView(R.id.editText_anzahl)
    EditText et_anzahl;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        produktApiService = APIUtils.getProduktService();

        final Bundle data = getIntent().getExtras();
        if (data != null) {
            produkt = data.getParcelable(DETAILS_PRODUKT_KEY);
            et_pid.setText(String.valueOf(produkt.getPid()));
            et_lid.setText(String.valueOf(produkt.getLid()));
            et_produktId.setText(produkt.getProduktId());
            et_produkt.setText(produkt.getProduktName());
            et_preis.setText(String.valueOf(produkt.getProduktPreis()));
            et_anzahl.setText(String.valueOf(produkt.getProduktAnzahl()));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = Integer.parseInt(et_pid.getText().toString());
                int lid = Integer.parseInt(et_lid.getText().toString());
                String produktId = et_produktId.getText().toString();
                String produktName = et_produkt.getText().toString();
                int preis = Integer.parseInt(et_preis.getText().toString());
                int anzahl = Integer.parseInt(et_anzahl.getText().toString());

                produkt = new Produkt(pid, lid, produktId, produktName, preis, anzahl);
                Log.i(LOG_TAG, "Produkt: " + produkt);

                if (data == null){
                    insertProdukt(produkt);
                } else {
                    updateProdukt(produkt);
                }

                Toast.makeText(DetailsActivity.this, "Produkt wurde gespeichert.", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    /**
     * Retrofit POST call
     */
    public void insertProdukt(Produkt produkt) {
        Call<Produkt> call = produktApiService.insertProdukt(produkt);
        call.enqueue(new Callback<Produkt>() {

            @Override
            public void onResponse(Call<Produkt> call, Response<Produkt> response) {
                if (response.isSuccessful()) {
                }
            }

            @Override
            public void onFailure(Call<Produkt> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    /**
     * Retrofit PUT call
     */
    public void updateProdukt(Produkt produkt) {
        Call<Produkt> call = produktApiService.updateProdukt(produkt);
        call.enqueue(new Callback<Produkt>() {

            @Override
            public void onResponse(Call<Produkt> call, Response<Produkt> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailsActivity.this, "Produkt erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produkt> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
