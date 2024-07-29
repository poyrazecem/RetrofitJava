package com.example.retrofitjava.view;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitjava.R;
import com.example.retrofitjava.databinding.FragmentLoginBinding;
import com.example.retrofitjava.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String registeremail = binding.editTextEmailRegister.getText().toString();
                String registerpassword = binding.editTextPasswordRegister.getText().toString();
                createUser(registeremail, registerpassword);
            }
        });


        binding.textViewLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new LoginFragment(), false);
            }
        });


        return view;
    }

    private void createUser(String registeremail, String registerpassword) {
        mAuth.createUserWithEmailAndPassword(registeremail, registerpassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Create your account successfully");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void saveUserToFirestore(FirebaseUser user, String email) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("uid", user.getUid());
        userMap.put("email", email);
        userMap.put("createdAt", System.currentTimeMillis());

        db.collection("users")
                .document(user.getUid())
                .set(userMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User saved to Firestore");
                        } else {
                            Log.w(TAG, "Error saving user to Firestore", task.getException());
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

            loadFragment(new LoginFragment(), false);
            Toast.makeText(getActivity(), "Üyelik işleminiz başarıyla tamamlanmıştır.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Please register", Toast.LENGTH_SHORT).show();

        }
    }

    public void loadFragment(Fragment fragment, boolean isAppInitiazlized) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitiazlized) {
            fragmentTransaction.add(R.id.main2, fragment);
        } else {
            fragmentTransaction.replace(R.id.main2, fragment);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
