/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author usuario.local
 */
public class Bairro {
    private int posX;
    private int posY;
    private int freqChamada;
    
    public Bairro(int posX, int posY, int freqChamada) {
        this.posX = posX;
        this.posY = posY;
        this.freqChamada = freqChamada;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getFreqChamada() {
        return freqChamada;
    }

    public void setFreqChamada(int freqChamada) {
        this.freqChamada = freqChamada;
    }
    
    
}
