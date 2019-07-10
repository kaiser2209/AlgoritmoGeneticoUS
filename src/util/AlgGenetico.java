/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dados.Bairro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author usuario.local
 */
public class AlgGenetico {
    private Bairro[] bairros = new Bairro[56];
    private int tamanhoPopulacao;
    private ArrayList<UnidadeSaude> populacao = new ArrayList<>();
    private int geracaoAtual = 0;
    private int totalGeracoes;
    private float taxaCruzamento;
    private float taxaMutacao;
    
    public AlgGenetico(int tamanhoPopulacao, int totalGeracoes, float taxaCruzamento,
            float taxaMutacao) {
        //Chama o método que armazena os dados referente aos bairros no vetor
        criarBairros();
        //Armazena o tamanho da população que será utilizada
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.totalGeracoes = totalGeracoes;
        this.taxaCruzamento = taxaCruzamento;
        this.taxaMutacao = taxaMutacao;
    }
    
    private void criarBairros() {
        bairros[0] = new Bairro(0, 0, 7);
        bairros[1] = new Bairro(0, 1, 5);
        bairros[2] = new Bairro(0, 2, 2);
        bairros[3] = new Bairro(0, 3, 5);
        bairros[4] = new Bairro(0, 4, 5);
        bairros[5] = new Bairro(0, 5, 5);
        bairros[6] = new Bairro(0, 6, 4);
        bairros[7] = new Bairro(0, 7, 5);
        bairros[8] = new Bairro(1, 0, 1);
        bairros[9] = new Bairro(1, 1, 4);
        bairros[10] = new Bairro(1, 2, 2);
        bairros[11] = new Bairro(1, 3, 8);
        bairros[12] = new Bairro(1, 4, 9);
        bairros[13] = new Bairro(1, 5, 1);
        bairros[14] = new Bairro(1, 6, 1);
        bairros[15] = new Bairro(1, 7, 8);
        bairros[16] = new Bairro(2, 0, 3);
        bairros[17] = new Bairro(2, 1, 5);
        bairros[18] = new Bairro(2, 2, 5);
        bairros[19] = new Bairro(2, 3, 1);
        bairros[20] = new Bairro(2, 4, 7);
        bairros[21] = new Bairro(2, 5, 2);
        bairros[22] = new Bairro(2, 6, 3);
        bairros[23] = new Bairro(2, 7, 9);
        bairros[24] = new Bairro(3, 0, 1);
        bairros[25] = new Bairro(3, 1, 8);
        bairros[26] = new Bairro(3, 2, 5);
        bairros[27] = new Bairro(3, 3, 6);
        bairros[28] = new Bairro(3, 4, 7);
        bairros[29] = new Bairro(3, 5, 5);
        bairros[30] = new Bairro(3, 6, 4);
        bairros[31] = new Bairro(3, 7, 2);
        bairros[32] = new Bairro(4, 0, 5);
        bairros[33] = new Bairro(4, 1, 8);
        bairros[34] = new Bairro(4, 2, 6);
        bairros[35] = new Bairro(4, 3, 5);
        bairros[36] = new Bairro(4, 4, 5);
        bairros[37] = new Bairro(4, 5, 4);
        bairros[38] = new Bairro(4, 6, 2);
        bairros[39] = new Bairro(4, 7, 9);
        bairros[40] = new Bairro(5, 0, 1);
        bairros[41] = new Bairro(5, 1, 6);
        bairros[42] = new Bairro(5, 2, 9);
        bairros[43] = new Bairro(5, 3, 3);
        bairros[44] = new Bairro(5, 4, 5);
        bairros[45] = new Bairro(5, 5, 5);
        bairros[46] = new Bairro(5, 6, 6);
        bairros[47] = new Bairro(5, 7, 4);
        bairros[48] = new Bairro(6, 0, 6);
        bairros[49] = new Bairro(6, 1, 3);
        bairros[50] = new Bairro(6, 2, 9);
        bairros[51] = new Bairro(6, 3, 9);
        bairros[52] = new Bairro(6, 4, 3);
        bairros[53] = new Bairro(6, 5, 8);
        bairros[54] = new Bairro(6, 6, 5);
        bairros[55] = new Bairro(6, 7, 8);
    }
    
    public void executa() {
        geraPopulacao();
        Collections.sort(populacao);
        populacao.forEach((us) -> {
            System.out.println(us.toString());
        });
        do {
            UnidadeSaude melhorIndividuo = new UnidadeSaude(populacao.get(0).getPosX(),
                populacao.get(0).getPosY(), populacao.get(0).getAptidao());
            
            ArrayList<UnidadeSaude> novaPopulacao = realizaCruzamento(7, 6);
            
            realizaMutacao(novaPopulacao);
            
            novaPopulacao.add(melhorIndividuo);
            Collections.sort(novaPopulacao);
            
            populacao = novaPopulacao;
            
            System.out.print("Geração " + geracaoAtual);
            System.out.print(" - Melhor Indivíduo: ");
            System.out.println(populacao.get(0).toString());
            
            geracaoAtual++;
        } while (geracaoAtual < totalGeracoes);
    }
    
    private void realizaMutacao(ArrayList<UnidadeSaude> populacao) {
        Random rnd = new Random();
        float r;
        for (UnidadeSaude us : populacao) {
            r = rnd.nextFloat();
            if (r < taxaMutacao) {
                float novoX = rnd.nextFloat() * 7;
                us.setPosX(novoX);
            }
            r = rnd.nextFloat();
            if (r < taxaMutacao) {
                float novoY = rnd.nextFloat() * 6;
                us.setPosY(novoY);
            }
            us.setAptidao(calculaObjetivo(us.getPosX(), us.getPosY()));
        }
    }
    
    private ArrayList<UnidadeSaude> realizaCruzamento(float limiteX, float limiteY) {
        ArrayList<UnidadeSaude> pais = new ArrayList<>();
        ArrayList<UnidadeSaude> novaPopulacao = new ArrayList<>();
        ArrayList<UnidadeSaude> selecao;
        while (pais.size() < (2 * tamanhoPopulacao) - 2) {
            Random r = new Random();
            selecao = new ArrayList<>();
            for (int z = 0; z < 3; z++) {
                int selecionado = r.nextInt(populacao.size());
                selecao.add(populacao.get(selecionado));
            }
            Collections.sort(selecao);
            float k = r.nextFloat();
            
            if (k < 0.6f) {
                pais.add(selecao.get(0));
            } else {
                pais.add(selecao.get(2));
            }
        }
        System.out.println("Lista de Pais: ");
        for (UnidadeSaude us : pais) {
            System.out.println(us.toString());
        }
        for (int i = 0; i < pais.size(); i += 2) {
            Random rnd = new Random();
            float r = rnd.nextFloat();
            
            if (r < taxaCruzamento) {
                float beta = (rnd.nextFloat() * 1.5f) - 0.25f;
                double xFilho, yFilho;
                xFilho = pais.get(i).getPosX() + beta * (pais.get(i + 1).getPosX() - pais.get(i).getPosX());
                yFilho = pais.get(i).getPosY() + beta * (pais.get(i + 1).getPosY() - pais.get(i).getPosY());
                
                while (xFilho < 0) {
                    xFilho += 1;
                }
                while (xFilho > limiteX) {
                    xFilho -= 1;
                }
                while (yFilho < 0) {
                    yFilho += 1;
                }
                while (yFilho > limiteY) {
                    yFilho -= 1;
                }
                novaPopulacao.add(new UnidadeSaude(xFilho, yFilho, calculaObjetivo(xFilho, yFilho)));
            } else {
                int p = rnd.nextInt(2);
                novaPopulacao.add(new UnidadeSaude(pais.get(i + p).getPosX(), 
                    pais.get(i + p).getPosY(), pais.get(i + p).getAptidao()));
            }
        }
        
        System.out.println("Filhos:");
        for (UnidadeSaude us : novaPopulacao) {
            System.out.println(us.toString());
        }
        
        System.out.println();
        
        return novaPopulacao;
    }
    
    private void geraPopulacao() {
        for (int i = 0; i < tamanhoPopulacao; i++) {
            Random r = new Random();
            double x = r.nextDouble() * 7;
            double y = r.nextDouble() * 6;
            populacao.add(new UnidadeSaude(x, y, calculaObjetivo(x, y)));
        }
    }
    
    private double calculaObjetivo(double usX, double usY) {
        double resultado = 0;
        
        for (Bairro bairro : bairros) {
            double rX = Math.pow(bairro.getPosX() - usX, 2);
            double rY = Math.pow((bairro.getPosY() - usY) * 2, 2);
            resultado += bairro.getFreqChamada() * Math.sqrt(rX + rY);
        }
        
        return resultado;
    }
    
    public class UnidadeSaude implements Comparable<UnidadeSaude> {
        private double posX;
        private double posY;
        private double aptidao;
        
        public UnidadeSaude(double posX, double posY, double aptidao) {
            this.posX = posX;
            this.posY = posY;
            this.aptidao = aptidao;
        }

        public double getPosX() {
            return posX;
        }

        public void setPosX(double posX) {
            this.posX = posX;
        }

        public double getPosY() {
            return posY;
        }

        public void setPosY(double posY) {
            this.posY = posY;
        }

        public double getAptidao() {
            return aptidao;
        }

        public void setAptidao(double aptidao) {
            this.aptidao = aptidao;
        }

        @Override
        public int compareTo(UnidadeSaude o) {
            if (this.getAptidao() > o.getAptidao()) {
                return 1;
            }
            if (this.getAptidao() < o.getAptidao()) {
                return -1;
            }
            return 0;
        }
        
        @Override
        public String toString() {
            return "X: " + getPosX() + " - Y: " + getPosY() + " - Aptidão: " + getAptidao();
        }
    }
}
