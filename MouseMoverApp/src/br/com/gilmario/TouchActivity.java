package br.com.gilmario;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import static br.com.gilmario.ClienteActivity.HOST_SERVIDOR;
import static br.com.gilmario.ClienteActivity.PORTA_SERVIDOR;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilmario
 */
public class TouchActivity extends Activity implements View.OnTouchListener {

    private Thread t;
    private Processo p;
    public static final String HOST_SERVIDOR = "servidor";
    public static final String PORTA_SERVIDOR = "porta";
    private DefaulMensagemProducer producer;
    private SeekBar bar;
    Float x;
    Float y;

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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.x = event.getX();
            this.x = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            System.out.println(event.getX());
            System.out.println(event.getY());
            try {
                p.sendTexto(producer.moveTo(event.getX(), event.getY()));
            } catch (Exception ex) {
                Logger.getLogger(TouchActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            System.out.println(event.getX());
            System.out.println(event.getY());
        }
        return true;
    }

    private void atualizaVelocidade() {
        producer.setVelocidade(Integer.toString(bar.getProgress()));
    }

}
