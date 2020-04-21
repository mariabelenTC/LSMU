package marbel.mov.urjc.imagerv02.PlayColorea;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import marbel.mov.urjc.imagerv02.Dibujar;
import marbel.mov.urjc.imagerv02.MainActivity;

public class Botonera  {

    private MainActivity main;
    private int fila, columna,totalBotones;

    private MyBoton[][] botonera;




    public Botonera(int fil, int column) {

        this.fila = fil;
        this.columna = column;
        this.totalBotones=fila*columna;
        this.botonera = new MyBoton[fil][column];
    }

    public Botonera(Dibujar dibujar) {
        super();
    }


    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public int getTotalBotones() {
        return totalBotones;
    }
    public void setTotalBotones(int totalBotones) {
        this.totalBotones = totalBotones;
    }
    public MyBoton[][] getBotonera() {
        return botonera;
    }
    public void setBotonera(MyBoton[][] botonera) {
        this.botonera = botonera;
    }


}

