package br.com.gilmario;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import java.io.IOException;

/**
 *
 * @author gilmario
 */
public class ClienteActivity extends Activity implements View.OnTouchListener {

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
        setContentView(R.layout.cliente);
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
        Button btn01 = (Button) findViewById(R.id.btn01);
        btn01.setOnTouchListener(this);
        Button btn02 = (Button) findViewById(R.id.btn02);
        btn02.setOnTouchListener(this);
        Button btn03 = (Button) findViewById(R.id.btn03);
        btn03.setOnTouchListener(this);
        Button btn04 = (Button) findViewById(R.id.btn04);
        btn04.setOnTouchListener(this);
        Button btn05 = (Button) findViewById(R.id.btn05);
        btn05.setOnTouchListener(this);
        Button btn06 = (Button) findViewById(R.id.btn06);
        btn06.setOnTouchListener(this);
        Button btn07 = (Button) findViewById(R.id.btn07);
        btn07.setOnTouchListener(this);
        Button btn08 = (Button) findViewById(R.id.btn08);
        btn08.setOnTouchListener(this);
        Button btn09 = (Button) findViewById(R.id.btn09);
        btn09.setOnTouchListener(this);
        Button btn10 = (Button) findViewById(R.id.btn10);
        btn10.setOnTouchListener(this);
        Button btn11 = (Button) findViewById(R.id.btn11);
        btn11.setOnTouchListener(this);
    }

    public void mover(View view) throws IOException, Exception {
        Button b = (Button) view;
        switch (b.getId()) {
            case R.id.btn01:
                p.sendTexto(producer.mensSubirEsquerda());
                break;
            case R.id.btn02:
                p.sendTexto(producer.mensSubir());
                break;
            case R.id.btn03:
                p.sendTexto(producer.mensSubirDireita());
                break;
            case R.id.btn04:
                p.sendTexto(producer.mensEsquerda());
                break;
            case R.id.btn05:
                p.sendTexto(producer.mensClickEsquerda());
                break;
            case R.id.btn06:
                p.sendTexto(producer.mensDireita());
                break;
            case R.id.btn07:
                p.sendTexto(producer.mensDescerEsquerda());
                break;
            case R.id.btn08:
                p.sendTexto(producer.mensDescer());
                break;
            case R.id.btn09:
                p.sendTexto(producer.mensDescerDireita());
                break;
            case R.id.btn10:
                p.sendTexto(producer.mensClickDireita());
                break;
            case R.id.btn11:
                p.sendTexto(producer.mensCentralizar());
                break;
        }
    }

    private void stop() throws Exception {
        try {
            p.sendTexto(producer.mensStop());
        } catch (IOException ex) {
            Toast.makeText(this, "erro ao se conectar\r\n" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            finish();
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                try {
                    mover(v);
                } catch (Exception ex) {
                    Toast.makeText(this, "erro ao se conectar" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                    finish();
                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                try {
                    stop();
                } catch (Exception ex) {
                    Toast.makeText(this, "erro ao se conectar" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                    finish();
                }
            }
            break;
            default:;
        }
        return true;
    }

    private void atualizaVelocidade() {
        producer.setVelocidade(Integer.toString(bar.getProgress()));
    }

}
