package com.example.hojadeservicio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class RegistroF1 extends AppCompatActivity {

    String[] items = {"Facultad de Informatica","Facultad de Derecho","Facultad de Psicologia","Facultad de Ciencias Naturales"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    Button btnNext1, bfecha,bhora;
    EditText efecha,ehora;
    private int dia, mes, ano, hora, minutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_f1);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = String.valueOf(parent.getItemIdAtPosition(position));
                Toast.makeText(getApplicationContext(),"Facultad"+item,Toast.LENGTH_SHORT).show();
            }
        });



        bfecha= (Button) findViewById (R.id.bFecha) ;
        bhora= (Button) findViewById (R.id.bHora);
        efecha=(EditText) findViewById (R.id.eFecha);
        ehora=(EditText) findViewById (R.id.eHora);
        bfecha.setOnClickListener (this::onClick);
        bhora.setOnClickListener (this::onClick);
        Calendar calendar = Calendar.getInstance();
        // Formatea la fecha y hora actuales en un formato específico
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDateTime = dateFormat.format(calendar.getTime());
        // Encuentra el TextView y establece el texto
        TextView textView = findViewById(R.id.fecha_soli);
        textView.setText(currentDateTime);

        btnNext1 = findViewById(R.id.btnNext1);

        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistroF2.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View v){
        if (v == bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==bhora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR);
            minutos=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":"+minute);
                }
            }
            ,hora,minutos,false);
            timePickerDialog.show();
        }
    }
}