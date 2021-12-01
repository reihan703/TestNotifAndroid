package com.rei.notificationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView out;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out = findViewById(R.id.output);

        timePicker = findViewById(R.id.timeP);
        timePicker.setIs24HourView(false);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                Toast.makeText(getApplicationContext(),i+" "+i1,Toast.LENGTH_LONG).show();
                out.setText("Time is: "+ i + " : " + i1);
            }
        });
    }

    public void simple(View view){
        Toast.makeText(getApplicationContext(),"Simple toast", Toast.LENGTH_SHORT).show();
    }

    public void extended(View view){
        Toast toast = Toast.makeText(getApplicationContext(),"Extended Toast", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.LEFT,0,0);
        toast.show();
    }

    public void customToast(View view){
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.my_toast, findViewById(R.id.myToast));
        TextView textView = layout.findViewById(R.id.toastTextView);
        ImageView imageView = layout.findViewById(R.id.imageView);
        textView.setText("CustomToast");
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void alert(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Konfirmasi");
        alertDialog
                .setMessage("Hapus?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"yes was chosen",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        alertDialog.show();
    }

    public void listDialog(View view){
        final String items[]=new String[]{"red","blue","green"};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Choose");
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.show();
    }

    public void tanggal(View view){
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        out.setText(new StringBuilder()
                .append(month+1)
                .append("-")
                .append(day)
                .append("-")
                .append(year));

        showDialog(DATE_PICKER_ID);
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            day = i2;

            out.setText(new StringBuilder().append(month+1)
                    .append("-")
                    .append(day)
                    .append("-")
                    .append(year));
        }
    };


    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_PICKER_ID: return new DatePickerDialog(this,pickerListener,year,month,day);
        }
        return null;
    }

    public void customDialog(View view){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_custom);

        TextView textView = dialog.findViewById(R.id.textCustom);
        textView.setText("Custom Dialog");
        ImageView imageView = dialog.findViewById(R.id.iconCustom);
        imageView.setImageResource(R.mipmap.ic_launcher);

        Button button = dialog.findViewById(R.id.buttonOke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}