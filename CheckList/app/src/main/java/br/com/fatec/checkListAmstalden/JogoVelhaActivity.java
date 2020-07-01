package br.com.fatec.checkListAmstalden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JogoVelhaActivity extends AppCompatActivity {
    String jogador = "X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_velha);
    }

    public void jogada(View v)
    {
        Button botao = (Button) findViewById(v.getId());

        if(botao.getText().toString().contains("X"))
        if(botao.getText().toString() == "")
        {
            if(jogador == "X")
            {
                botao.setText("X");
                jogador = "O";
            }
            else
            {
                botao.setText("O");
                jogador = "X";
            }

        }
    }
    public void voltar(View v)
    {
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }
}
