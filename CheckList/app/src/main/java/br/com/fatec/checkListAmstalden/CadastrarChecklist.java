package br.com.fatec.checkListAmstalden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.fatec.checkListAmstalden.ModelChecklist.CheckList;

public class CadastrarChecklist extends AppCompatActivity {

    EditText edtTitulo, edtData;
    RadioGroup radioGroup;
    CheckBox cbStatus;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_checklist);

        inicializaFireBase();
        edtTitulo = (EditText)findViewById(R.id.txtTituloTarefa);
        edtData = (EditText)findViewById(R.id.txtDataExecuta);
        radioGroup = (RadioGroup) findViewById(R.id.rgbPrioridadeTarefa);
        cbStatus = (CheckBox)findViewById(R.id.cbFeito);
    }
    public void voltar(View v)
    {
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }

    private void inicializaFireBase(){
        FirebaseApp.initializeApp(CadastrarChecklist.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void cadastrarTarefa(View v){
        CheckList tarefa = new CheckList();
        try {
            tarefa.setId(UUID.randomUUID().toString());
            tarefa.setTitulo(edtTitulo.getText().toString());
            tarefa.setDataAbertura((edtData.getText().toString()));
            tarefa.setPrioridade(String.valueOf(getCheckBox()));

            int statuscbFeito = cbStatus.isChecked() ? 1 : 0;
            tarefa.setStatus(String.valueOf(statuscbFeito));

            databaseReference.child("Tarefas").child(tarefa.getId()).setValue(tarefa);
            exibeMensagem("Tarefa cadastrada com SUCESSO!", 1);
            limparCampos();
        } catch (Exception e) {
            exibeMensagem("Erro ao cadastrar tarefa!", 1);
        }

    }

    public int getCheckBox(){
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.rbPrioriMedia:
                return 1;
            case R.id.rbPrioriAlta:
                return 2;
            default:
                return 0;
        }
    }

    public void limparCampos()
    {
        edtTitulo.setText("");
        edtData.setText("");
    }

    public void exibeMensagem(String mensagem, int tempo){
        Context contexto = getApplicationContext();
        String texto = mensagem;
        int duracao = tempo == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contexto, texto,duracao);
        toast.show();
    }






}
