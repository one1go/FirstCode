package com.miao.www.litepaldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        Button addData = (Button) findViewById(R.id.add_data);
        Button updateData = (Button) findViewById(R.id.update_data);
        Button deleteData = (Button) findViewById(R.id.delete_data);
        Button queryData = (Button) findViewById(R.id.query_data);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //只要调用一次数据库操作，数据库就创建完成了
                LitePal.getDatabase();
            }
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("The First Code");
                book.setAuthor("Guolin");
                book.setPages(570);
                book.setPrice(79.00);
                book.setPress("Unknow");
                book.save();
            }
        });
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Book book = new Book();
                book.setName("The Second Code");
                book.setAuthor("Guolin2");
                book.setPages(580);
                book.setPrice(89.00);
                book.setPress("Unknow");
                book.save();
                book.setPrice(99.00);
                book.save();*/
                /*Book book = new Book();
                book.setPrice(20.17);
                book.setPress("New Year");
                book.updateAll("name = ? and author = ?","The First Code","Guolin");*/
                Book book = new Book();
                book.setToDefault("pages");
                book.updateAll();
            }
        });
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class,"price < ?","21");
            }
        });
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "onClick: " + book.getName() + " " + book.getAuthor() + " " + book.getPages() + " " + book.getPrice() + " " + book.getPress());
                }
            }
        });
    }
}
