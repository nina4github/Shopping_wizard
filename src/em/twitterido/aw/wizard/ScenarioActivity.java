package em.twitterido.aw.wizard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ScenarioActivity extends Activity {
	public final String TAG = "Scenario Activity";
	String actor = null;
	protected static final String uri = "http://idea.itu.dk:8080/";
	protected static final String service = "@idea.itu.dk:3000";
	private String activity = "shopping";
	private String place = "place01";
	protected String TEST_IMG_URL = "http://idea.itu.dk:3000/uploads/images/scaled_full_fb79b5fef393d17fc2c5.jpg";
	protected String what = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		Log.d(TAG, "we have extras from SensorEvent");

		String userScenario = extras.getString("actor");

		setContentView(R.layout.scenario);

		initializeButtons(userScenario);
	}

	protected void initializeButtons(String scenario) {

		LinearLayout ll = (LinearLayout) findViewById(R.id.scenarioLayout);

		actor = scenario;

		Button b1 = new Button(this);
		b1.setText(R.string.scenarioButton_activity_start);
		Button b2 = new Button(this);
		b2.setText(R.string.scenarioButton_activity_stop);
		Button b3 = new Button(this);
		b3.setText(R.string.scenarioButton_activity_data);

		Button b4 = new Button(this);
		b4.setText(R.string.scenarioButton_activity_spark);

		Button b5 = new Button(this);
		b5.setText(R.string.scenarioButton_location_enter);
		Button b6 = new Button(this);
		b6.setText(R.string.scenarioButton_location_leave);

		Spinner spinner = new Spinner(this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.usernames, android.R.layout.simple_spinner_item);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// set what has been selected
				what = arg0.getItemAtPosition(arg2).toString();
				Log.d(TAG, "what has been set to: "+what);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		if (actor.contains("rollator")) {
			ll.addView(b1);
			ll.addView(b2);
			ll.addView(b3);
			b3.setPadding(0, 0, 0, 20);
		} else if (actor.contains("user")) {
			ll.addView(b4);
			b4.setPadding(0, 0, 0, 20);
		} else if (actor.contains("place01") || actor.contains("fakta")) {
			ll.addView(spinner);
			spinner.setPadding(0, 0, 0, 20);
			ll.addView(b5);
			ll.addView(b6);
			
		}

		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String params = "?user=" + actor + service;
				String postActivity = uri + "activities/" + activity + ".json"
						+ params;

				String text = "start" + " #" + activity;
				postRequest(text, postActivity);
			}
		});

		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String params = "?user=" + actor + service;
				String postActivity = uri + "activities/" + activity + ".json"
						+ params;
				String text = "stop" + " #" + activity;
				postRequest(text, postActivity);
			}
		});
		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String params = "?user=" + actor + service;
				String postActivity = uri + "activities/" + activity + ".json"
						+ params;
				String text = "data:500:m" + " #" + activity;
				postRequest(text, postActivity);
			}
		});

		b4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String params = "?user=" + actor + service;
				String postActivity = uri + "activities/" + activity + ".json"
						+ params;
				String text = "spark:photo:" + TEST_IMG_URL + " #" + activity;
				postRequest(text, postActivity);
			}
		});

		// location buttons
		b5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (what != null) {
					String params = "?user=" + actor + service; // here the
																// actor is
					// the place
					String postActivity = uri + "activities/" + activity
							+ ".json" + params;
					String text = what + " enter " + " #" + activity;

					postRequest(text, postActivity);
				} else {
					Toast.makeText(
							v.getContext(),
							"Please select WHAT is entering or leaving "
									+ actor + " before selecting the action!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		b6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (what != null) {
					String params = "?user=" + actor + service; // here the
					// actor is the
					// place
					String postActivity = uri + "activities/" + activity
							+ ".json" + params;
					String text = what + " leave " + " #" + activity;
					// String text = actor + "is leaving" + " #" + activity;
					postRequest(text, postActivity);
				} else {
					Toast.makeText(
							v.getContext(),
							"Please select WHAT is entering or leaving "
									+ actor + " before selecting the action!",
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	private void postRequest(String text, String postActivity) {
		// TODO Auto-generated method stub

		MakePostRequest request = new MakePostRequest(ScenarioActivity.this);
		Log.d(TAG, "making request " + text);
		Toast.makeText(this, "making post request", Toast.LENGTH_LONG);
		request.execute(postActivity, text);

	}

}
