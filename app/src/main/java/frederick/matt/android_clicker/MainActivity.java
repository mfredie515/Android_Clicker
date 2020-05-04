package frederick.matt.android_clicker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private int speedMultiplier = 1;
    private int pointMultiplier = 1;
    private int points = 0;
    private Timer timer = new Timer(false);
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button speed = (Button) findViewById(R.id.btnSpeed);
        Button point = (Button) findViewById(R.id.btnPoint);
        speed.setClickable(false);
        point.setClickable(false);
    }

    private void drawPoints() {
        TextView score = (TextView) findViewById(R.id.txtScore);
        score.setText("Score: " + points);
    }

    private void addPoints() {
        points += pointMultiplier * 2;

        drawPoints();
    }

    public void btnClick_Clicked(View view) {
        addPoints();
    }

    public void btnAuto_Clicked(View view) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= speedMultiplier; i++)
                            addPoints();
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 1000); //1000ms delay

        view.setClickable(false);
        Button speed = (Button) findViewById(R.id.btnSpeed);
        Button point = (Button) findViewById(R.id.btnPoint);

        speed.setClickable(true);
        point.setClickable(true);
    }

    public void btnSpeed_Clicked(View view) {
        if (points > (speedMultiplier ^ 2)) {
            speedMultiplier += 1;
            points -= speedMultiplier ^ 2;
        }

        drawPoints();
    }

    public void btnPoint_Clicked(View view) {
        if (points > (pointMultiplier ^ 2)) {
            pointMultiplier += 1;
            points -= pointMultiplier ^ 2;
        }

        drawPoints();
    }
}
