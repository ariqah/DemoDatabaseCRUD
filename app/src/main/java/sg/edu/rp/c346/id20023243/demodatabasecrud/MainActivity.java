package sg.edu.rp.c346.id20023243.demodatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvContent;
    EditText etContent;
    Button btnAdd, btnEdit, btnRetrieve;
    ListView lv;
    ArrayAdapter<Note> aa;
    ArrayList<Note> al;

    @Override
    protected void onResume() {
        super.onResume();
        btnRetrieve.performClick(); //so will execute btnRetrieve method
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tvContent);
        etContent = findViewById(R.id.etContent);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Note>();
        aa = new ArrayAdapter<Note>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etContent.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(data); // because insertMethod returns long, which is  num of items inserted

                if (inserted_id != -1){ //if id returned is -1, means insert fail
                    al.clear();
                    al.addAll(dbh.getAllNotes()); //add notes arrayList from DBHelper
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note target = al.get(0); //hardcore to get only first object in arrayList

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("data", target);
                //note implemented serializable that's why can pass data to another class
                startActivity(intent);
            }
        });



        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear(); //clear all contents
                //al.addAll(dbh.getAllNotes()); //add back updated notes arrayList from DBHelper
                String filterText = etContent.getText().toString().trim();
                if(filterText.length() == 0) { //if nothing
                    al.addAll(dbh.getAllNotes());
                }
                else{
                    al.addAll(dbh.getAllNotes(filterText));
                }

                aa.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });



    }
}