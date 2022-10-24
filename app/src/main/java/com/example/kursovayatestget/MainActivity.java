package com.example.kursovayatestget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.kursovayatestget.models.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button SignIn,Register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignIn = findViewById(R.id.SignIn);
        Register = findViewById(R.id.Register);
        root = findViewById(R.id.root_element);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterWindow();
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInWindow();
            }
        });
    }
    private void showSignInWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите данные для входа");
        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in = inflater.inflate(R.layout.sign_in, null);
        dialog.setView(sign_in);

        EditText email = sign_in.findViewById(R.id.email);
        EditText password = sign_in.findViewById(R.id.password);
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(root, "Введите почту", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().length() < 5) {
                    Snackbar.make(root, "Пароль", Snackbar.LENGTH_LONG).show();
                    return;
                }
                // Регистрация пользователя
                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, StartActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Ошибка " + e.getMessage(), Snackbar.LENGTH_SHORT ).show();
                    }
                });

            }
        });
        dialog.show();
    }
    private void showRegisterWindow() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Зарегистироваться");
            dialog.setMessage("Введите все данные для регистрации");
            LayoutInflater inflater = LayoutInflater.from(this);
            View registerWindow = inflater.inflate(R.layout.register_widget, null);
            dialog.setView(registerWindow);

            EditText email = registerWindow.findViewById(R.id.email);
            EditText password = registerWindow.findViewById(R.id.password);
            EditText name = registerWindow.findViewById(R.id.name);
            EditText phone = registerWindow.findViewById(R.id.phone);
            dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                }
            });
            dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (TextUtils.isEmpty(email.getText().toString())) {
                        Snackbar.make(root, "Введите почту", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    if (password.getText().toString().length() < 5) {
                        Snackbar.make(root, "Пароль", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(name.getText().toString())) {
                        Snackbar.make(root, "Имя", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(phone.getText().toString())) {
                        Snackbar.make(root, "Введите почту", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    // Регистрация пользователя
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Users user = new Users();gi
                                    user.setEmail(email.getText().toString());
                                    user.setName(name.getText().toString());
                                    user.setPassword(password.getText().toString());
                                    user.setPhone(phone.getText().toString());

                                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_SHORT);
                                                }
                                            });

                                }
                            });
                }
            });
            dialog.show();
        }
}
