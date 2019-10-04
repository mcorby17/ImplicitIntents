package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open up a web browser for the passed URL
        mWebEditText = findViewById(R.id.website_editText);
        mLocationEditText = findViewById(R.id.location_editText);
        mShareEditText = findViewById(R.id.share_text_editText);
    }

    public void openWebsite(View view) {

        String webUrl = mWebEditText.getText().toString();
        Uri webpage = Uri.parse(webUrl);

        // Note this is an IMPLICIT intent. This means that
        // an action is specified (ACTION_VIEW) and the
        // android OS is expected to find activities from
        // other applications that can complete the action.
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to give the intent to and start that activity
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else
        {
            Log.d("ImplicitIntent", "Couldn't resolve the webpage URI intent");
        }
    }

    public void openLocation(View view) {

        String location = mLocationEditText.getText().toString();
        Uri locUri = Uri.parse("geo:0,0?q=" + location);

        Intent intent = new Intent(Intent.ACTION_VIEW, locUri);

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else
        {
            Log.d("ImplicitIntent", "Couldn't resolve the location URI intent");
        }
    }

    public void shareText(View view) {

        String text = mShareEditText.getText().toString();
        String mimetype = "text/plain";

        ShareCompat.IntentBuilder.from(this)
                .setType(mimetype)
                .setChooserTitle(getString(R.string.share_text_text))
                .setText(text)
                .startChooser();

    }

    public void openCamera(View view) {

        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (picIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(picIntent);
        }
        else
        {
            Log.d("PicIntent", "Couldn't resolve camera intent");
        }

    }
}
