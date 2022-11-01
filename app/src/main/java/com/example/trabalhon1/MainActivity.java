package com.example.trabalhon1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;
    private ArrayAdapter adapter;
    private List<Produto> produtos;
    private EditText etProduto, etQnt;
    private Button btnSalvar;
    private CheckBox urgente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvProdutos = findViewById(R.id.lvProdutos);
        etProduto = findViewById(R.id.etProduto);
        etQnt = findViewById(R.id.etQnt);
        urgente = findViewById(R.id.cbUrgente);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    @Override
    protected void onStart () {
        super.onStart();
        carregarProdutos();
    }

    private void carregarProdutos() {
        produtos = ProdutoDAO.getProdutos(this);
        if (produtos.size() == 0) {
            Produto fake = new Produto(0, "Nenhum produto cadastrado", 0, false);
            produtos.add(fake);
            lvProdutos.setEnabled(false);
        } else {
            lvProdutos.setEnabled(true);
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos);
        lvProdutos.setAdapter(adapter);
    }

    private void salvar() {
        String nome = etProduto.getText().toString();
        if (nome.isEmpty()) {
            Toast.makeText(this, "Necessario o preencimento do campo Produto", Toast.LENGTH_SHORT).show();
        } else {
            Produto p = new Produto();
            p.nome = nome;
            String sQuantidade = etQnt.getText().toString();
            if (sQuantidade.isEmpty()) {
                p.quantidade = 0;
            } else {
                p.quantidade = Integer.valueOf(sQuantidade);
            }
            ProdutoDAO.inserir(this, p);
            limparTela();
        }
    }

    private void limparTela() {
        etProduto.setText("");
        etQnt.setText("");
    }
}