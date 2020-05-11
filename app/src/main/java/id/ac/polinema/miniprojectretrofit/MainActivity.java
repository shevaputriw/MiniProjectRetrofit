package id.ac.polinema.miniprojectretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.ac.polinema.miniprojectretrofit.api.ApiClient;
import id.ac.polinema.miniprojectretrofit.api.ApiInterface;
import id.ac.polinema.miniprojectretrofit.helper.Session;
import id.ac.polinema.miniprojectretrofit.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button button;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(getApplicationContext());
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.btnLoginTeacher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherLogin(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void handleBack(View view) {
        Intent intent = new Intent(MainActivity.this, LoginAdmin.class);
        startActivity(intent);
    }

    private void teacherLogin(String username, String password) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.loginGuru(new User(username, password));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONArray json = new JSONArray(response.body().string());
                        String username = json.getJSONObject(0).getString("username");
                        String password = json.getJSONObject(0).getString("password");
                        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                        session.setLoggedInStatus(true);
                        session.setUsername(username);
                        session.setPassword(password);
                        session.setLoginTime(currentTime);
                        session.setDate(currentDate);

                        Intent intent = new Intent(getApplicationContext(), GuruActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password Anda Salah", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
