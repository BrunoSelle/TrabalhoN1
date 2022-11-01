package com.example.trabalhon1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO{

    public static void inserir(Context contexto, Produto produto) {
        Banco conn = new Banco(contexto);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", produto.nome);
        valores.put("numero", produto.quantidade);
        valores.put("urgente", produto.urgente);

        db.insert(Banco.TBL_PRODUTOS, null, valores);
    }

    // Editar jogador já cadastrado no DB
    public static void editar(Context contexto, Produto produto){
        Banco conn = new Banco(contexto);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", produto.nome);
        valores.put("numero", produto.quantidade);
        valores.put("urgente", produto.urgente);
        db.update(Banco.TBL_PRODUTOS, valores, " id = " + produto.id, null);
    }

    // Excluir jogador do DB
    public static void excluir (Context contexto,int idProduto){
        Banco conn = new Banco(contexto);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.delete(Banco.TBL_PRODUTOS, " id = " + idProduto, null);
    }


    // Exibir lista de produtos
    public static List<Produto> getProdutos (Context contexto){
        List<Produto> lista = new ArrayList<>();
        Banco conn = new Banco(contexto);
        SQLiteDatabase db = conn.getReadableDatabase();
        String sql = "SELECT id, nome, numero, urgente FROM " + Banco.TBL_PRODUTOS + " ORDER BY urgente DESC, nome";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Produto p = new Produto();
                p.id = cursor.getInt(0);
                p.nome = cursor.getString(1);
                p.quantidade = cursor.getInt(2);
                lista.add(p);
            } while (cursor.moveToNext());
        }
        return lista;

    }
// Buscar um produto por ID

    public static Produto getProdutoById(Context contexto, int idProduto) {
        Banco conn = new Banco (contexto);
        SQLiteDatabase db = conn.getReadableDatabase();
        String sql = "SELECT id, nome, numero, urgente FROM " + Banco.TBL_PRODUTOS + " WHERE id = " + idProduto;
        Cursor cursor = db.rawQuery(sql, null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Produto p = new Produto();
            p.id = cursor.getInt(0);
            p.nome = cursor.getString(1);
            p.quantidade = cursor.getInt(2);
            return p;
        } else {
            return null;
        }

    }

}