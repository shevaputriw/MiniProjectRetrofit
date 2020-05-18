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

import id.ac.polinema.miniprojectretrofit.api.ApiClient;
import id.ac.polinema.miniprojectretrofit.api.ApiInterface;
import id.ac.polinema.miniprojectretrofit.helper.Session;
import id.ac.polinema.miniprojectretrofit.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAdmin extends AppCompatActivity {
    private EditText username, password;
    private Button button;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        session = new Session(getApplicationContext());
        username = findViewById(R.id.usernameAdmin);
        password = findViewById(R.id.passwordAdmin);
        button = findViewById(R.id.btnLoginAdmin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent intent = new Intent(LoginAdmin.this, AdminActivity1.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginAdmin.this, "Username atau Password Anda Salah", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void handleLoginasTeacher(View view) {
        Intent intent = new Intent(LoginAdmin.this, MainActivity.class);
        startActivity(intent);
    }
}
