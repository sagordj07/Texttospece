package com.example.sagor.texttospece;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    int result;
    Button button,button2;
    EditText editText;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editTextid) ;
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);

        textToSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                   result= textToSpeech.setLanguage(Locale.US);
                }else {
                    Toast.makeText(getApplicationContext(),"Not Supported in your Device",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void desomething(View v)
    {
        switch(v.getId()){

            case R.id.button:
           if(result==textToSpeech.LANG_NOT_SUPPORTED || result==textToSpeech.LANG_MISSING_DATA){

               Toast.makeText(getApplicationContext(),"Not Supported in your Device",Toast.LENGTH_SHORT).show();

           }else {

               text= editText.getText().toString();

               textToSpeech.speak(text,textToSpeech.QUEUE_FLUSH,null);
           }

           break;
            case R.id.button2:
                if(textToSpeech!=null){
                    textToSpeech.stop();
                }

            break;
    }

    }

    public void OnDestroy()
    {
        super.onDestroy();
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
