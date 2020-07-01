package br.com.fatec.checkListAmstalden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.checkListAmstalden.ModelChecklist.CheckList;

public class ConsultarTarefas extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView lstTarefas_dados;
    private List<CheckList> listTarefas = new ArrayList<CheckList>();
    private ArrayAdapter<CheckList> arrayAdapTerChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_tarefas);

        inicializaFireBase();
        lstTarefas_dados = (ListView)findViewById(R.id.lstTarefas);
        eventoDatabase();
    }

    private void eventoDatabase(){
        databaseReference.child("Tarefas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listTarefas.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    CheckList tarefa = objSnapshot.getValue(CheckList.class);
                    listTarefas.add(tarefa);
                }
                arrayAdapTerChecklist = new ArrayAdapter<>(ConsultarTarefas.this,
                        android.R.layout.simple_list_item_1, listTarefas);
                lstTarefas_dados.setAdapter(arrayAdapTerChecklist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializaFireBase(){
        FirebaseApp.initializeApp(ConsultarTarefas.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    public void limparCampos()
    {
        //edtTitulo.setText("");
    }

    public void exibeMensagem(String mensagem, int tempo){
        Context contexto = getApplicationContext();
        String texto = mensagem;
        int duracao = tempo == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contexto, texto,duracao);
        toast.show();
    }
}
