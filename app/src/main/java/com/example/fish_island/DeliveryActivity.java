package com.example.fish_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fish_island.model.AuthToken;
import com.example.fish_island.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    ConstraintLayout Root;

    TextView deliveryName;
    TextView deliveryAdress;
    TextView deliveryPhone;

    FirebaseDatabase db;
    DatabaseReference users_data;

    List<String> user_token_list_d = new ArrayList<>();
    String user_token_d ;

    String Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        db= FirebaseDatabase.getInstance();
        users_data = db.getReference("UsersData");

        user_token_list_d.addAll(AuthToken.Token);

        for(int i = 0;i<user_token_list_d.size();i++){
            user_token_d = user_token_list_d.get(i);
        }

        Root = (ConstraintLayout) findViewById(R.id.rootElement);

        deliveryName = (TextView) findViewById(R.id.deliveryName);
        deliveryPhone = (TextView) findViewById(R.id.deliveryPhone);
        deliveryAdress = (TextView) findViewById(R.id.deliveryAdress);

        //Итого
        TextView totalView = (TextView) findViewById(R.id.totalView);
        Total = getIntent().getStringExtra("total");
        totalView.setText(Total);

        //Назад
        ImageButton backButton = (ImageButton) findViewById(R.id.back5Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        //Оплата
        ImageView NextStep = (ImageView) findViewById(R.id.NextStepPaymentButton);
        NextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("fishIsland.intent.action.payment");
                intent.putExtra("Name",deliveryName.getText().toString());
                intent.putExtra("Adress",deliveryAdress.getText().toString());
                intent.putExtra("Phone",deliveryPhone.getText().toString());
                intent.putExtra("total",Total);
                startActivity(intent);
            }
        });

        //вызов функции редактирования данных
        TextView edit = (TextView) findViewById(R.id.editView);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showEditWindow(); }
        });

        getDataDeliveryFromDB();
    }

    //подставление из бд
    private void getDataDeliveryFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    if (user.getEmail().equals(user_token_d)){
                        deliveryName.setText(user.getName());
                        deliveryPhone.setText(user.getPhone());
                        deliveryAdress.setText(user.getAdress());
                    }

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        users_data.addValueEventListener(vListener);
    }

    //Редактирование
    private void showEditWindow() {
        AlertDialog.Builder regDialog = new AlertDialog.Builder(this);
        regDialog.setTitle("Изменить");

        LayoutInflater inflater = LayoutInflater.from(this);
        View edit_window = inflater.inflate(R.layout.edit_window,null);
        regDialog.setView(edit_window);

        EditText adress = edit_window.findViewById(R.id.AdressField);
        EditText name = edit_window.findViewById(R.id.NameField);
        EditText phone = edit_window.findViewById(R.id.PhoneField);

        regDialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        regDialog.setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(Root,"Введите имя",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(Root,"Введите телефон",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(adress.getText().toString())){
                    Snackbar.make(Root,"Введите адрес",Snackbar.LENGTH_LONG).show();
                    return;
                }

                deliveryName.setText(name.getText().toString());
                deliveryPhone.setText(phone.getText().toString());
                deliveryAdress.setText(adress.getText().toString());
            }

        });

        regDialog.show();
    }
}