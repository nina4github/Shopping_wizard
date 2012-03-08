package em.twitterido.aw.wizard;

import em.twitterido.aw.wizard.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PersonalEMWizardActivity extends Activity {

	public String TAG = "PersonalEM Wizard Activity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actors);
		initializeButtons();
	}

	private void initializeButtons() {
		

		int[] buttons = { R.id.buttonT1, R.id.buttonT2, R.id.buttonT3,
				R.id.buttonT4, R.id.buttonT5, R.id.buttonP1, R.id.buttonP2,
				R.id.buttonP3, R.id.buttonP4, R.id.buttonP5, R.id.buttonPl1 }; 
		
		Button[] scenarioButtons = new Button[buttons.length];
		for (int j = 0; j < scenarioButtons.length; j++) {
			scenarioButtons[j] = (Button) findViewById(buttons[j]);

		}

		for (Button button : scenarioButtons) {
			button.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					Intent home = new Intent(PersonalEMWizardActivity.this,
							ScenarioActivity.class);
					home.putExtra("actor", ((Button) v).getText());
					Log.d(TAG, "opening scenario for actor +"
							+ ((Button) v).getText());

					startActivity(home);
				}
			});
		}

	}

}