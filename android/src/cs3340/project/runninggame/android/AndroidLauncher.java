package cs3340.project.runninggame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.android.Database.DBAdapter;
import cs3340.project.runninggame.android.Database.AndroidTimeDatabase;

/**
 * The Android Main that is only used for launching the game on the Android
 * platform. Also contains the creation of the app's single database instance.
 * @author Jesus Ramos
 * @version 0.3
 * @since 11/24/2015
 */
public class AndroidLauncher extends AndroidApplication {
    /**
     * Creates an AndroidApplication instance and launches the game.
     * @param savedInstanceState .
     */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new RunningGame(new AndroidTimeDatabase(new DBAdapter(this))), config);
	}
}
