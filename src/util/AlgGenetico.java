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
import ui.main.MainController;

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
    private MainController controller;
    private int melhorGeracao;
    
    public AlgGenetico(int tamanhoPopulacao, int totalGeracoes, float taxaCruzamento,
            float taxaMutacao, MainController controller) {
        //Chama o método que armazena os dados referente aos bairros no vetor
        criarBairros();
        //Armazena o tamanho da população que será utilizada
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.totalGeracoes = totalGeracoes;
        this.taxaCruzamento = taxaCruzamento;
        this.taxaMutacao = taxaMutacao;
        this.controller = controller;
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
        //Gera a população aleatoriamente
        geraPopulacao();
        //Ordena a população baseado na melhor Aptidão
        Collections.sort(populacao);
        //populacao.forEach((us) -> {
        //    System.out.println(us.toString());
        //});
        double melhorAptidao = 0;
        do {
            //Salva o melhor indíviduo para ser usado na próxima geração
            UnidadeSaude melhorIndividuo = new UnidadeSaude(populacao.get(0).getPosX(),
                populacao.get(0).getPosY(), populacao.get(0).getAptidao());
            //Realiza o cruzamento dos indivíduos
            ArrayList<UnidadeSaude> novaPopulacao = realizaCruzamento(7, 6);
            //Realiza a mutação da nova população (filhos)
            realizaMutacao(novaPopulacao);
            //Adiciona o melhor indivíduo a nova população
            novaPopulacao.add(melhorIndividuo);
            //Ordena a população baseado na melhor Aptidão
            Collections.sort(novaPopulacao);
            //Substitui a população anterior pela nova
            populacao = novaPopulacao;
            //Estrutura condicional que verifica e armazena a geração que teve o melhor indivíduo
            if (geracaoAtual > 0) {
                if (populacao.get(0).getAptidao() < melhorAptidao) {
                    melhorAptidao = populacao.get(0).getAptidao();
                    melhorGeracao = geracaoAtual;
                }
            } else {
                melhorAptidao = populacao.get(0).getAptidao();
            }
            geracaoAtual++;
        } while (geracaoAtual < totalGeracoes);
        //Envia os dados da execução do algoritmo para a tela da aplicação
        controller.setResultados(populacao.get(0).posX, populacao.get(0).posY,
                populacao.get(0).getAptidao(), melhorGeracao);
        
    }
    
    private void realizaMutacao(ArrayList<UnidadeSaude> populacao) {
        Random rnd = new Random();
        float r;
        for (UnidadeSaude us : populacao) {
            //Número aleatório que testa se vai acontecer a mutação no valor x
            r = rnd.nextFloat();
            if (r < taxaMutacao) {
                float novoX = rnd.nextFloat() * 7;
                us.setPosX(novoX);
            }
            //Número aleatório que testa se vai aconter a mutação no valor y
            r = rnd.nextFloat();
            if (r < taxaMutacao) {
                float novoY = rnd.nextFloat() * 6;
                us.setPosY(novoY);
            }
            //Calcula a nova aptidão após realizada a mutação
            us.setAptidao(calculaObjetivo(us.getPosX(), us.getPosY()));
        }
    }
    
    private ArrayList<UnidadeSaude> realizaCruzamento(float limiteX, float limiteY) {
        //Lista que vai armazenar a lista dos pais selecionados
        ArrayList<UnidadeSaude> pais = new ArrayList<>();
        //Lista que vai armazenar a nova população originada do cruzamento
        ArrayList<UnidadeSaude> novaPopulacao = new ArrayList<>();
        //Lista que vai conter os 3 indíviduos escolhidos pelo método de torneio
        ArrayList<UnidadeSaude> selecao;
        //Escolhe os indíviduos para cruzamento (2 * populacao) - 2
        while (pais.size() < (2 * tamanhoPopulacao) - 2) {
            Random r = new Random();
            selecao = new ArrayList<>();
            for (int z = 0; z < 3; z++) {
                //Gera um número aleatório para o método de seleção torneio
                int selecionado = r.nextInt(populacao.size());
                //Armazena o indivíduo localizado na posição escolhida aleatoriamente
                selecao.add(populacao.get(selecionado));
            }
            //Ordena os 3 selecionados de acordo com a melhor aptidão
            Collections.sort(selecao);
            //Número aleatório gerado para determinar se o indivíduo escolhido será o melhor ou o pior dos 3
            float k = r.nextFloat();
            /*Estrutura que avalia se será escolhido o melhor ou o pior indivíduo
             *para o cruzamento. 60% de chances do melhor ser escolhido e 40% de chances
             *do pior ser escolhido*/
            if (k < 0.6f) {
                pais.add(selecao.get(0));
            } else {
                pais.add(selecao.get(2));
            }
        }

        for (int i = 0; i < pais.size(); i += 2) {
            Random rnd = new Random();
            //Número aleatório usado para testar se vai aconter o cruzamento
            float r = rnd.nextFloat();
            
            if (r < taxaCruzamento) {
                //Geração aleatório do valor beta (-0.25 à 1.25)
                float beta = (rnd.nextFloat() * 1.5f) - 0.25f;
                double xFilho, yFilho;
                //Cálculo dos valor x e y do filho baseado no número beta
                xFilho = pais.get(i).getPosX() + beta * (pais.get(i + 1).getPosX() - pais.get(i).getPosX());
                yFilho = pais.get(i).getPosY() + beta * (pais.get(i + 1).getPosY() - pais.get(i).getPosY());
                /*Corrige os valores de x e y do filho se caso estiver fora
                **dos valores permitidos 0 <= x <= 7, 0 <= y <= 6
                */
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
                //Adiciona o novo filho a nova população
                novaPopulacao.add(new UnidadeSaude(xFilho, yFilho, calculaObjetivo(xFilho, yFilho)));
            } else {
                /*Caso não haja cruzamento, é sorteado aleatoriamente qual dos pais
                **vai para a próxima geração.
                */
                int p = rnd.nextInt(2);
                //Adiciona o pai selecionado a nova população
                novaPopulacao.add(new UnidadeSaude(pais.get(i + p).getPosX(), 
                    pais.get(i + p).getPosY(), pais.get(i + p).getAptidao()));
            }
        }
        
        return novaPopulacao;
    }
    
    private void geraPopulacao() {
        //Gera a população aleatoriamente de acordo com o tamanho máximo definido
        for (int i = 0; i < tamanhoPopulacao; i++) {
            Random r = new Random();
            //Gera um número aleatório que varia de 0 até 7 (double)
            double x = r.nextDouble() * 7;
            //Gera um número aleatório que varia de 0 até 6 (double)
            double y = r.nextDouble() * 6;
            //Cria um novo indivíduo e armazena na lista população
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
