package com.example.proyecto;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText correo,contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo=findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);
        mAuth=FirebaseAuth.getInstance();

    }


    public void iniciar(View view) {
        String email =correo.getText().toString();
        String password = contraseña.getText().toString();
        Log.d("valor",email);
        if (email.length()!=0 && password.length()!=0)
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"usuario o password erroneos",Toast.LENGTH_LONG).show();
                    }else
                        {
                            Toast.makeText(MainActivity.this,"Se inicio correctamente",Toast.LENGTH_LONG).show();
                        }


                }
            });
        }
        else{
            Toast.makeText(MainActivity.this,"Los campos estan nulos",Toast.LENGTH_LONG).show();
        }


    }

    public void registrar(View view) {

        String email = correo.getText().toString();
        String password = contraseña.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d( "Respuesta", "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
}
