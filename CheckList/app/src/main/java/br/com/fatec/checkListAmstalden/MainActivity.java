package br.com.fatec.checkListAmstalden;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Integer login = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lerDados();
        if(login == 0)
        {
            Intent tela = new Intent(this, LoginActivity.class);
            startActivityForResult(tela, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            recreate();
        }
    }

    public void lerDados()
    {
        SharedPreferences dados = getSharedPreferences("fatec", MODE_PRIVATE);
        String mail = dados.getString("mail", "");
        login = dados.getInt("login", 0);
    }

    public void jogoVelha(View v)
    {
        Intent tela = new Intent(this, JogoVelhaActivity.class);
        startActivity(tela);
    }

    public void cadastrarCheckList(View v)
    {
        Intent tela = new Intent(this, CadastrarChecklist.class);
        startActivity(tela);
    }

    public void consultarCheckList(View v)
    {
        Intent tela = new Intent(this, ConsultarTarefas.class);
        startActivity(tela);
    }

    public void logout(View v)
    {
        SharedPreferences.Editor dados = getSharedPreferences("fatec", MODE_PRIVATE).edit();
        dados.putInt("login", 0);
        dados.apply();

        recreate();
    }
}
