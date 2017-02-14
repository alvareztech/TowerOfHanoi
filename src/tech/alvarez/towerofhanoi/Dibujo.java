package tech.alvarez.towerofhanoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Clase Dibujo
 * @author Daniel Alvarez
 */
public class Dibujo extends JPanel implements ActionListener {

    private int nroFichas;
    private int[] topes;
    private int movimientoActual;
    private Image[] fichas;
    private int x, y;
    private int ficha;
    private int nm;
    private Movimiento[] movimientos;
    private Posicion[] posiciones;
    private Timer timer;
    private boolean movimientoCompletado;
    private int paso;
    private static final int VELOCIDAD = 1;
    private static final int LIMITE_FICHAS = 8;
    private static final int LIMITE_TORRES = 3;
    private MainFrame nucleo;

    public Dibujo(int nroFichas, MainFrame nucleo) {
        this.nroFichas = nroFichas;
        this.nucleo = nucleo;
        configurarPanel();
        inicializarComponentes();
        inicializarComponentesDeAnimacion();
        timer = new Timer(VELOCIDAD, this);
    }

    private void configurarPanel() {
        setDoubleBuffered(true);
        setBackground(Color.black);
    }

    private void inicializarComponentes() {
        fichas = new Image[LIMITE_FICHAS + 1];
        for (int i = 1; i <= LIMITE_FICHAS; i++) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/tech/alvarez/towerofhanoi/images/" + i + ".png"));
            fichas[i] = ii.getImage();
        }
    }

    private void inicializarComponentesDeAnimacion() {
        nm = 0;
        topes = new int[LIMITE_TORRES + 1];
        topes[1] = nroFichas;
        topes[2] = 0;
        topes[3] = 0;
        ficha = 1;
        movimientos = new Movimiento[(int) Math.pow(2, nroFichas)];
        algoritmoHanoi(nroFichas, 1, 2, 3); // llena el vector de movimientos
        posiciones = new Posicion[9];
        for (int i = 1; i <= nroFichas; i++) {
            int w = nroFichas - i + 1;
            posiciones[i] = new Posicion(posicionXFicha(i, 1), posicionYFicha(w));
        }
        x = posiciones[1].getX();
        y = posiciones[1].getY();
        movimientoActual = 1;
        movimientoCompletado = false;
        paso = 1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = nroFichas; i >= 1; i--) {
            g2.drawImage(fichas[i], posiciones[i].getX(), posiciones[i].getY(), this);
        }
        g2.drawString("Torre 1", 115, 320);
        g2.drawString("Torre 2", 315, 320);
        g2.drawString("Torre 3", 515, 320);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void algoritmoHanoi(int n, int origen, int temporal, int destino) {
        if (n == 0) {
            return;
        }
        algoritmoHanoi(n - 1, origen, destino, temporal);
        nm++;
        movimientos[nm] = new Movimiento(n, origen, destino);
        algoritmoHanoi(n - 1, temporal, origen, destino);
    }

    public void actionPerformed(ActionEvent e) {
        switch (paso) {
            case 1: // mover hacia arriba
                if (y > 30) { // 30 es el maximo a elevar la ficha
                    y--;
                    posiciones[ficha].setY(y);
                } else {
                    if (movimientos[movimientoActual].getTorreOrigen() < movimientos[movimientoActual].getTorreDestino()) {
                        paso = 2; // mover a la derecha
                    } else {
                        paso = 3; // mover a la izquierda
                    }
                }
                break;
            case 2: // mover hacia derecha
                if (x < posicionXFicha(ficha, movimientos[movimientoActual].getTorreDestino())) { // recorre hasta la torre destino
                    x++;
                    posiciones[ficha].setX(x);
                } else {
                    paso = 4;
                }
                break;
            case 3: // mover hacia izquierda
                if (x > posicionXFicha(ficha, movimientos[movimientoActual].getTorreDestino())) { // recorre hasta la torre destino
                    x--;
                    posiciones[ficha].setX(x);
                } else {
                    paso = 4;
                }
                break;
            case 4: // mover hacia abajo
                int nivel = topes[movimientos[movimientoActual].getTorreDestino()] + 1;
                if (y < posicionYFicha(nivel)) {
                    y++;
                    posiciones[ficha].setY(y);
                } else {
                    movimientoCompletado = true;
                }
                break;
        }
        if (movimientoCompletado) {
            paso = 1;
            topes[movimientos[movimientoActual].getTorreDestino()]++;
            topes[movimientos[movimientoActual].getTorreOrigen()]--;
            movimientoActual++;
            if (movimientoActual == (int) Math.pow(2, nroFichas)) {
                timer.stop();
                nucleo.resolucionCompletada();
            } else {
                movimientoCompletado = false;
                ficha = movimientos[movimientoActual].getFicha();
                x = posiciones[ficha].getX();
                y = posiciones[ficha].getY();
            }
        }
        repaint();
    }

    public static int posicionXFicha(int ficha, int torre) {
        int k = (torre - 1) * 200;
        switch (ficha) {
            case 1:
                return 110 + k;
            case 2:
                return 100 + k;
            case 3:
                return 90 + k;
            case 4:
                return 80 + k;
            case 5:
                return 70 + k;
            case 6:
                return 60 + k;
            case 7:
                return 50 + k;
            case 8:
                return 40 + k;
        }
        return 0;
    }

    public static int posicionYFicha(int nivel) {
        switch (nivel) {
            case 1:
                return 260;
            case 2:
                return 233;
            case 3:
                return 206;
            case 4:
                return 179;
            case 5:
                return 152;
            case 6:
                return 125;
            case 7:
                return 98;
            case 8:
                return 71;
        }
        return 0;
    }

    public void iniciarAnimacion() {
        timer.restart();
    }

    public void pausarAnimacion() {
        timer.stop();
    }
}
