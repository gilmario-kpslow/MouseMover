package br.com.gilmario;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilmario
 */
public class TouchActivity extends Activity implements View.OnTouchListener, View.OnClickListener {

    private Thread t;
    private Processo p;
    public static final String HOST_SERVIDOR = "servidor";
    public static final String PORTA_SERVIDOR = "porta";
    private DefaulMensagemProducer producer;
    private SeekBar bar;
    Float x = 0F;
    Float y = 0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch);
        View v = findViewById(R.id.touchLayout);
        v.setOnTouchListener(this);
        bar = (SeekBar) findViewById(R.id.velocidade);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                atualizaVelocidade();
            }

        });
        producer = new DefaulMensagemProducer(Integer.toString(bar.getProgress()));
        String servidor = getIntent().getStringExtra(HOST_SERVIDOR);
        int porta = getIntent().getIntExtra(PORTA_SERVIDOR, 50000);
        p = new Processo(servidor, porta);
        t = new Thread(p);
        t.start();
    }

    public boolean onTouch(View v, MotionEvent event) {
        try {
            float j = event.getPointerCount();
            if (j > 1) {
                p.sendTexto(producer.mensClickEsquerda());
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                this.x = event.getX();
                this.y = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                p.sendTexto(producer.mensStop());
                this.x = 0F;
                this.y = 0F;
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                p.sendTexto(producer.moveTo(((event.getX() - this.x) * Integer.parseInt(producer.getVelocidade()) / 100), (event.getY() - this.y) * Integer.parseInt(producer.getVelocidade()) / 100));

            }
        } catch (Exception ex) {
            Logger.getLogger(TouchActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void atualizaVelocidade() {
        producer.setVelocidade(Integer.toString(bar.getProgress()));
    }

    public void onClick(View v) {
        try {
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            p.sendTexto(producer.mensClickEsquerda());
        } catch (Exception ex) {
            Logger.getLogger(TouchActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
