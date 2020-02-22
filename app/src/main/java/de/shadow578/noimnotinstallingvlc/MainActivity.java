package de.shadow578.noimnotinstallingvlc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.videolan.vlc.R;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("NoVLC", "NoVLC Main Activity initialized!");

        //redirect call intent
        if (redirectIntent(getIntent()))
        {
            //redirect ok, close asap
            Log.d("NoVLC", "Redirect intent OK.");
        }
        else
        {
            //redirect failed, show error
            Log.e("NoVLC", "Redirect intent failed!");
            Toast.makeText(getApplicationContext(), getText(R.string.err_redirect_fail), Toast.LENGTH_LONG).show();
        }

        //exit activity
        finish();
    }

    /**
     * Redirects the intent to the default handler
     *
     * @param orgIntent the intent to redirect
     * @return was the intent redirected ok?
     */
    private boolean redirectIntent(Intent orgIntent)
    {
        Log.d("NoVLC", "Starting intent redirect...");
        dumpIntent(orgIntent, "Original Intent");

        //do NOT redirect ACTION_MAIN intents
        if (orgIntent.getAction() == null || orgIntent.getAction().equals(Intent.ACTION_MAIN))
        {
            //is ACTION_MAIN, abort
            Log.d("NoVLC", "Abort redirect on ACTION_MAIN...");
            return false;
        }

        //do NOT redirect if already redirected
        if (orgIntent.getBooleanExtra("IS_NOVLC_REDIRECT", false))
        {
            //is already redirected, abort
            Log.d("NoVLC", "Abort redirect on IS_NOVLC_REDIRECT set...");
            return false;
        }

        //copy original intent
        Intent redirIntent = new Intent();

        //set action, data and type
        redirIntent.setAction(orgIntent.getAction());
        redirIntent.setDataAndType(orgIntent.getData(), orgIntent.getType());

        //clone categories
        if (orgIntent.getCategories() != null)
        {
            for (String cat : orgIntent.getCategories())
            {
                redirIntent.addCategory(cat);
            }
        }

        //clone extras
        if (orgIntent.getExtras() != null)
        {
            redirIntent.putExtras(orgIntent.getExtras());
        }

        //add REDIRECT flag
        redirIntent.putExtra("IS_NOVLC_REDIRECT", true);

        //check if intent can be handled
        PackageManager packageManager = getPackageManager();
        if (redirIntent.resolveActivity(packageManager) != null)
        {
            //can be handled, launch intent
            startActivity(redirIntent);
            return true;
        }
        else
        {
            //cannot handle, abort
            return false;
        }
    }

    /**
     * Dump the intent data to Log.d
     *
     * @param intent the intent to dump
     * @param desc   the description of the intent that is dumped
     */
    private void dumpIntent(Intent intent, @SuppressWarnings("SameParameterValue") String desc)
    {
        Log.d("NoVLC", "========================================");
        Log.d("NoVLC", "Dumping Intent " + desc);
        Log.d("NoVLC", String.format("%s of type %s", intent.toString(), intent.getType()));
        Uri data = intent.getData();
        Log.d("NoVLC", String.format("Data: %s (%s)", (data == null) ? "null" : data.toString(), intent.getDataString()));

        //dump extras
        Log.d("NoVLC", "Extras: ");
        Bundle extras = intent.getExtras();
        if (extras != null)
        {
            for (String key : extras.keySet())
            {
                Log.d("NoVLC", String.format("   %s = %s", key, extras.get(key)));
            }
        }
        else
        {
            Log.d("NoVLC", "Intent has no extras.");
        }

        Log.d("NoVLC", "========================================");
    }
}
