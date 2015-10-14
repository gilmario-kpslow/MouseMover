package br.com.gilmario;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<Endereco> enderecos;
    private EditText nome;
    private EditText servidor;
    private EditText porta;
    private ListView lista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        atualizarDados();
        lista = (ListView) findViewById(R.id.enderecos);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, enderecos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
        servidor = (EditText) findViewById(R.id.servidor);
        porta = (EditText) findViewById(R.id.porta);
        nome = (EditText) findViewById(R.id.nome);

    }

    public void touch(View v) {
        startActivity(new Intent(this, TouchActivity.class));
    }

    private void atualizarDados() {
        enderecos = new ArrayList<Endereco>();
        SharedPreferences preference = getSharedPreferences("ENDERECOS", 0);
        String base;
        Integer i = 0;
        do {
            base = preference.getString(i.toString(), "");
            if (!base.equals("")) {
                Endereco e = new Endereco(base);
                enderecos.add(e);
            }
            i++;
        } while (!base.equals(""));

    }

    public void salvar(View view) {
        Endereco e = new Endereco(nome.getText().toString(), servidor.getText().toString(), porta.getText().toString());
        enderecos.add(e);
        SharedPreferences preference = getSharedPreferences("ENDERECOS", 0);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString(Integer.toString(enderecos.size() - 1), e.getValue());
        edit.commit();
        ((ArrayAdapter) lista.getAdapter()).notifyDataSetChanged();
    }

    public void conectar(View view) {
        String s = servidor.getText().toString();
        int p = Integer.parseInt(porta.getText().toString());
        Intent i = new Intent(this, ClienteActivity.class);
        i.putExtra(ClienteActivity.HOST_SERVIDOR, s);
        i.putExtra(ClienteActivity.PORTA_SERVIDOR, p);
        startActivity(i);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Endereco e = (Endereco) parent.getAdapter().getItem(position);
        servidor.setText(e.getIp());
        porta.setText(e.getPorta().toString());
        nome.setText(e.getNome());
    }

}
