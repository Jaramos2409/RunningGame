package cs3340.project.runninggame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import cs3340.project.runninggame.GameScreen;
import cs3340.project.runninggame.RunningGame;

/**
 * The Android Main that is only used for launching the game on the Android
 * platform.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/24/2015
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RunningGame(), config);
	}
}
