package sg.edu.rp.c346.id20027025.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextToDo;
    Button add;
    Button clear;
    Button delete;
    ListView lvToDo;
    Spinner spinner;

    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextToDo = findViewById(R.id.editTextToDo);
        add = findViewById(R.id.add);
        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        lvToDo = findViewById(R.id.lvToDo);
        spinner = findViewById(R.id.spinner);

        delete.setEnabled(false);

        alToDo = new ArrayList<String>();
        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        editTextToDo.setHint("Type in a new task here");
                        break;
                    case 1:
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        editTextToDo.setHint("Type in the index of the task to be removed");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextToDo.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter something",Toast.LENGTH_LONG).show();
                }else{
                    alToDo.add(editTextToDo.getText().toString());
                    editTextToDo.setText("");
                }
                aaToDo.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alToDo.size()==0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                }else if(editTextToDo.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(editTextToDo.getText().toString())>=alToDo.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                }else{
                    alToDo.remove(Integer.parseInt(editTextToDo.getText().toString()));
                    editTextToDo.setText("");
                }
                aaToDo.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
                editTextToDo.setText("");
            }
        });

    }
}