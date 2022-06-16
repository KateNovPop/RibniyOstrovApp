package com.example.fish_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fish_island.model.AuthToken;
import com.example.fish_island.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    TextView RegCheck;
    ImageButton AuthButton;
    ConstraintLayout Root;
    TextView Test;

    EditText Email;
    EditText Pass;



    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    DatabaseReference users_data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        RegCheck = findViewById(R.id.RegCheck);
        AuthButton = findViewById(R.id.AuthButton);
        Root = findViewById(R.id.rootel);
        Email = findViewById(R.id.EmailInput);
        Pass = findViewById(R.id.PasswordInput);
        Test = findViewById(R.id.test);

        auth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        users_data = db.getReference("UsersData");

        RegCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
        AuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUser();
            }
        });


    }

    public void AuthUser() {

        if(TextUtils.isEmpty(Email.getText().toString())){
            Snackbar.make(Root,"Введите почту",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(Pass.getText().toString().length() < 6){
            Snackbar.make(Root,"Введите пароль длиннее 6 символов",Snackbar.LENGTH_LONG).show();
            return;
        }
        auth.signInWithEmailAndPassword(Email.getText().toString(),Pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String token = auth.getCurrentUser().getEmail();
                AuthToken.Token.clear();
                AuthToken.Token.add(token);
                Intent intent = new Intent("fishIsland.intent.action.profile");
                intent.putExtra("USER_KEY",token);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(Root,"Ошибка авторизации",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void showRegisterWindow() {
        AlertDialog.Builder regDialog = new AlertDialog.Builder(this);
        regDialog.setTitle("Зарегистрироваться");
        regDialog.setMessage("Введите данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View reg_window = inflater.inflate(R.layout.register_window,null);
        regDialog.setView(reg_window);

        EditText email = reg_window.findViewById(R.id.EmailField);
        EditText pass = reg_window.findViewById(R.id.PasswordField);
        EditText name = reg_window.findViewById(R.id.NameField);
        EditText phone = reg_window.findViewById(R.id.PhoneField);
        EditText adress = reg_window.findViewById(R.id.AdressField);

        regDialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        regDialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(Root,"Введите почту",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(Root,"Введите имя",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(Root,"Введите телефон",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(pass.getText().toString().length() < 6){
                    Snackbar.make(Root,"Введите пароль длиннее 6 символов",Snackbar.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setName(name.getText().toString());
                        user.setPhone(phone.getText().toString());
                        user.setAdress(adress.getText().toString());

                        users_data.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(Root,"Пользоатель добавлен!",Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                });

            }
        });

        regDialog.show();
    }
}
