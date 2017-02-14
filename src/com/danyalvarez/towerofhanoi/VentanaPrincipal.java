package com.danyalvarez.towerofhanoi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Clase VentanaPrincipal
 * @author Daniel Alvarez
 */
public class VentanaPrincipal extends JFrame implements ActionListener, ChangeListener {

    private JLabel labelNroDiscos;
    private JLabel labelInformacion;
    private JSpinner spinnerNroDiscos;
    private JButton botonIniciar;
    private Dibujo dibujo;

    public VentanaPrincipal() {
        super("Tower of Hanoi | alvarez.tech");
        configurarVentana();
        inicializarComponentes();
        this.setVisible(true);
    }

    private void configurarVentana() {
        this.setSize(680, 400);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel panelInferior = new JPanel();

        labelNroDiscos = new JLabel("Number of discs");
        panelInferior.add(labelNroDiscos);

        spinnerNroDiscos = new JSpinner(new SpinnerNumberModel(8, 1, 8, 1));
        spinnerNroDiscos.addChangeListener(this);
        panelInferior.add(spinnerNroDiscos);

        botonIniciar = new JButton("Start");
        botonIniciar.addActionListener(this);
        panelInferior.add(botonIniciar);

        labelInformacion = new JLabel("RESOLUTION COMPLETED!");
        labelInformacion.setForeground(Color.red);
        labelInformacion.setVisible(false);
        panelInferior.add(labelInformacion);

        add(panelInferior, BorderLayout.SOUTH);
        dibujo = new Dibujo(8, this);
        add(dibujo, BorderLayout.CENTER);


    }

    public void actionPerformed(ActionEvent e) {
        if (botonIniciar.getText().equals("Pause")) {
            dibujo.pausarAnimacion();
            botonIniciar.setText("Continue");
        } else {
            if (botonIniciar.getText().equals("Start again")) {
                dibujo = new Dibujo(Integer.parseInt(spinnerNroDiscos.getValue().toString()), this);
                add(dibujo, BorderLayout.CENTER);
                botonIniciar.setText("Start");
                labelInformacion.setVisible(false);
                this.setVisible(true);
            } else {
                dibujo.iniciarAnimacion();
                botonIniciar.setText("Pause");
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        dibujo.pausarAnimacion();
        botonIniciar.setText("Start");
        labelInformacion.setVisible(false);
        dibujo = new Dibujo(Integer.parseInt(spinnerNroDiscos.getValue().toString()), this);
        add(dibujo, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resolucionCompletada() {
        botonIniciar.setText("Start again");
        labelInformacion.setVisible(true);
    }
}
