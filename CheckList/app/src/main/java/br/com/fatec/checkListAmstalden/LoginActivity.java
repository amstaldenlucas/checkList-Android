package br.com.fatec.checkListAmstalden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void sair(View v)
    {
        EditText mail = (EditText) findViewById(R.id.edtUsuario);
        SharedPreferences.Editor dados = getSharedPreferences("fatec", MODE_PRIVATE).edit();
        dados.putString("mail", mail.getText().toString() );
        dados.putInt("login", 1);
        dados.apply();

        Intent retorno = new Intent();
        setResult(RESULT_OK, retorno);
        finish();
    }
}
