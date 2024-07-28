//Moath Khalid Alkhelifi | 441015594
//Faisal Saleh Alkholaifi | 441014098

package com.example.ebook_assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    EditText etName, etBookCategory, etBookTitle;
    TextView textView, textView5, textView4;
    Button showButton, insertButton;
    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText)findViewById(R.id.TextNameField);
        etBookCategory = (EditText)findViewById(R.id.TextCategoryField);
        etBookTitle = (EditText)findViewById(R.id.TextTitleField);
        textView = (TextView)findViewById(R.id.textView);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView4 = (TextView)findViewById(R.id.textView4);
        showButton = (Button)findViewById(R.id.showBtn);
        insertButton = (Button)findViewById(R.id.insertBtn);
        database=new DataBase(this);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show btn below ->
                Cursor res=database.getData();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "NO Data Found ", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("BookTitle :"+res.getString(1)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Books Available:");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insertion btn below ->
                String bookName=etName.getText().toString();
                String bookTitle=etBookTitle.getText().toString();
                String  bookCategory=etBookCategory.getText().toString();
                Boolean check=database.insert(bookName,bookTitle,bookCategory);

                if(check==true)
                    Toast.makeText(MainActivity.this, "New information has been entered", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "The information has not been entered", Toast.LENGTH_SHORT).show();

            }

            });
    }
}

