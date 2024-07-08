package com.example.andriodassignments;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class Login_activity_unit_test {

    private LoginActivity loginActivity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    @Before
    public void setUp() {
        loginActivity = Mockito.spy(LoginActivity.class);
        context = Mockito.mock(Context.class);
        sharedPreferences = Mockito.mock(SharedPreferences.class);
        editor = Mockito.mock(SharedPreferences.Editor.class);

        Mockito.when(context.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)).thenReturn(sharedPreferences);
        Mockito.when(sharedPreferences.edit()).thenReturn(editor);
        Mockito.when(editor.putString(Mockito.anyString(), Mockito.anyString())).thenReturn(editor);

        loginActivity.sharedPreferences = sharedPreferences;
        loginActivity.emailedittext = Mockito.mock(EditText.class);
        loginActivity.passwordedittext = Mockito.mock(EditText.class);
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(loginActivity.isValidEmail("test@example.com"));
        assertFalse(loginActivity.isValidEmail("invalid-email"));
    }

    @Test
    public void testSaveEmail() {
        String testEmail = "test@example.com";
        loginActivity.saveEmailAndProceed(testEmail);
        Mockito.verify(editor).putString("DefaultEmail", testEmail);
        Mockito.verify(editor).apply();
    }

    @Test
    public void testValidateAndProceedWithInvalidEmail() {
        Mockito.when(loginActivity.emailedittext.getText()).thenReturn(Mockito.mock(android.text.Editable.class));
        Mockito.when(loginActivity.emailedittext.getText().toString()).thenReturn("invalid-email");
        Mockito.when(loginActivity.passwordedittext.getText()).thenReturn(Mockito.mock(android.text.Editable.class));
        Mockito.when(loginActivity.passwordedittext.getText().toString()).thenReturn("password");

        loginActivity.validateAndProceed();

        Mockito.verify(loginActivity.emailedittext).setError("Invalid email");
    }

    @Test
    public void testValidateAndProceedWithEmptyPassword() {
        Mockito.when(loginActivity.emailedittext.getText()).thenReturn(Mockito.mock(android.text.Editable.class));
        Mockito.when(loginActivity.emailedittext.getText().toString()).thenReturn("test@example.com");
        Mockito.when(loginActivity.passwordedittext.getText()).thenReturn(Mockito.mock(android.text.Editable.class));
        Mockito.when(loginActivity.passwordedittext.getText().toString()).thenReturn("");

        loginActivity.validateAndProceed();

        Mockito.verify(loginActivity.passwordedittext).setError("Password cannot be empty");
    }
}
