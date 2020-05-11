package id.ac.polinema.miniprojectretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.miniprojectretrofit.api.ApiClient;
import id.ac.polinema.miniprojectretrofit.api.ApiInterface;
import id.ac.polinema.miniprojectretrofit.helper.Session;
import id.ac.polinema.miniprojectretrofit.model.Guru;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        session = new Session(getApplicationContext());

        final RecyclerView guruView = findViewById(R.id.rv_guru);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        final List guru = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Guru>> call = apiInterface.getGuru();

        call.enqueue(new Callback<List<Guru>>() {
            @Override
            public void onResponse(Call<List<Guru>> call, Response<List<Guru>> response) {
                List<Guru> guruItems = response.body();

                for (Guru item : guruItems) {
                    guru.add(new Guru(item.getId_guru(), item.getNama(), item.getAlamat(), item.getJenis_kelamin(),
                            item.getNo_telp(), item.getFoto(), item.getUsername(), item.getPassword()));
                }

                itemAdapter.add(guru);
                guruView.setAdapter(fastAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                guruView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Guru>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }

    public void handleAdd(View view) {
        Intent intent = new Intent(AdminActivity.this, AddGuru.class);
        startActivity(intent);
    }

    public void handleLogoutAdmin(View view) {
        Intent intent = new Intent(AdminActivity.this, LoginAdmin.class);
        startActivity(intent);
    }
}
