package it.mmo.ncb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	Activity that;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		that = this;
	}

	public void selectCar(View v) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		final List<ResolveInfo> pkgAppsList = this.getPackageManager().queryIntentActivities(mainIntent, 0);
		ArrayList<CharSequence> names = new ArrayList<CharSequence>();
		for (ResolveInfo ri : pkgAppsList) {
			names.add(ri.loadLabel(getPackageManager()));
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		CharSequence cs [] = new CharSequence[names.size()];
		names.toArray(cs);
		builder.setTitle("Choose the App").setItems( cs, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	Log.d("WHICH", ""+which);
            	ResolveInfo selected = pkgAppsList.get(which);
            	
            	String p = selected.activityInfo.applicationInfo.packageName;
            	SharedPreferences sp = that.getSharedPreferences("ncb", Activity.MODE_PRIVATE);
            	sp.edit().putString("package", p).commit();
            	
            }
		});
		builder.create().show();
	}

	public void writeOnTag(View v) {
			
	}
}
