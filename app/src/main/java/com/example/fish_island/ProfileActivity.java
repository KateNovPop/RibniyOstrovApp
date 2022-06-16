package com.example.fish_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ImageButton backButton;
    ImageButton signOut;

    CardView confidentialityButton;
    CardView historyButton;

    ImageView editButton;

    TextView profileName;
    TextView profileEmail;
    TextView profileAdress;
    TextView profilePhone;

    ConstraintLayout Root;

    Intent intent;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    DatabaseReference users_data;

    List<String> user_token_list = new ArrayList<>();
    String user_token ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user_token_list.addAll(AuthToken.Token);

        for(int i = 0;i<user_token_list.size();i++){
            user_token = user_token_list.get(i);
        }


        Root = (ConstraintLayout) findViewById(R.id.RootElement);

        profileName = (TextView) findViewById(R.id.ProfileName);
        profileEmail = (TextView) findViewById(R.id.ProfileEmail);
        profilePhone = (TextView) findViewById(R.id.ProfilePhone);
        profileAdress = (TextView) findViewById(R.id.ProfileAdress);

        auth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        users_data = db.getReference("UsersData");

        backButton = (ImageButton) findViewById(R.id.back2Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confidentialityButton = (CardView) findViewById(R.id.ConfidentialityButton);
        confidentialityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent("fishIsland.intent.action.plug");
                startActivity(intent);
            }
        });

        historyButton = (CardView) findViewById(R.id.historyProfileButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent("fishIsland.intent.action.orderHistory");
                startActivity(intent);
            }
        });

        signOut = (ImageButton) findViewById(R.id.signOutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthToken.Token.clear();
                intent = new Intent("fishisland.intent.action.auth");
                startActivity(intent);
            }
        });

        editButton = (ImageView) findViewById(R.id.EditButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showEditWindow();}
        });

    getDataFromDB();

    }
        private void getDataFromDB(){
            ValueEventListener vListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                        User user = dataSnapshot.getValue(User.class);
                        if (user.getEmail().equals(user_token)){
                            profileName.setText(user.getName());
                            profileEmail.setText(user.getEmail());
                            profilePhone.setText(user.getPhone());
                            profileAdress.setText(user.getAdress());

                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            users_data.addValueEventListener(vListener);
        }

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

                    ValueEventListener vListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                                User user = dataSnapshot.getValue(User.class);
                                if (user.getEmail().equals(user_token)){
                                   user.setName(name.getText().toString());
                                   user.setPhone(phone.getText().toString());
                                   user.setAdress(adress.getText().toString());
                                   user.setEmail(user_token);
                                   String KEY = dataSnapshot.getKey();

                                    users_data.child(KEY).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Snackbar.make(Root,"Данные изменены",Snackbar.LENGTH_LONG).show();
                                        }
                                    });

                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    users_data.addValueEventListener(vListener);
                }

        });

        regDialog.show();
    }
}