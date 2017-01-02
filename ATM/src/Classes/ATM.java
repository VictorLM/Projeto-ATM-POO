package Classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ATM { //inicia a classe ATM
    private Bandeja b1, b2, b3, b4; //cria as bandejas
    private double saldoATM;
    private List<String> log; //cria uma lista para o log
    private ContaCorrente contaCorrente;
    
    private final String SAQUE = "Saque";
    private final String DEPOSITO = "Depósito";
    private final String SALDO_ATM = "Saldo do ATM";
    private final String SALDO_CC = "Saldo da CC";

    //cria o construtor ATM
    public ATM(Bandeja b1, Bandeja b2, Bandeja b3, Bandeja b4,
               ContaCorrente contaCorrente) {
        this.saldoATM = 0;
        this.log = new ArrayList<>();
        
        this.b1 = b1;
        this.b1.setAtiva(true);
        
        this.b2 = b2;
        this.b2.setAtiva(true);
        
        this.b3 = b3;
        this.b3.setAtiva(true);
        
        this.b4 = b4;
        this.b4.setAtiva(true);
        
        this.contaCorrente = contaCorrente;
        
        atualizaSaldoATM();
    } //fim do construtor ATM
    
    public void setLog(String tipo, double valor) { //recebe as variáveis do log
        Date data = new Date();
        SimpleDateFormat simpleDateFormat = 
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String msg = "[" + simpleDateFormat.format(data) + "] " +
            tipo + ": R$" + String.format("%.2f", valor);
        
        this.log.add(msg);
    }
    
    public void addBandeja(int posicao) { //adiciona uma bandeja na posição
        setStatusBandeja(posicao, true);
    }
    
    public void removeBandeja(int posicao) { //remove uma bandeja da posição
        setStatusBandeja(posicao, false);
    }
    
    //verifica se a bandeja esta ativa ou não
    private void setStatusBandeja(int posicao, boolean isAtivo) {
        switch(posicao) {
            case 1:
                b1.setAtiva(isAtivo);
            break;
            
            case 2:
                b2.setAtiva(isAtivo);
            break;
            
            case 3:
                b3.setAtiva(isAtivo);
            break;
            
            case 4:
                b4.setAtiva(isAtivo);
            break;
            
            default:
                
            break;
        }
    } //fim do método setStatusBandeja
    
    private void atualizaSaldoATM() { //atualiza o saldo do ATM
        this.saldoATM=0;
        if (this.b1.isAtiva()) {
            this.saldoATM += b1.calculaSaldoBandeja();
        } else {
            this.saldoATM -= b1.calculaSaldoBandeja();
        }
        
        if (this.b2.isAtiva()) {
            this.saldoATM += b2.calculaSaldoBandeja();
        } else {
            this.saldoATM -= b2.calculaSaldoBandeja();
        }
        
        if (this.b3.isAtiva()) {
            this.saldoATM += b3.calculaSaldoBandeja();
        } else {
            this.saldoATM -= b3.calculaSaldoBandeja();
        }
        
        if (this.b4.isAtiva()) {
            this.saldoATM += b4.calculaSaldoBandeja();
        } else {
            this.saldoATM -= b4.calculaSaldoBandeja();
        }
    } //fim do método atualizaSaldoATM
    
    //atualiza o saldo da conta corrente
    public String atualizaSaldoContaCorrente() {
        return "[SALDO] R$" + this.contaCorrente.getSaldo() + 
            "\n[LIMITE] R$" + this.contaCorrente.getLimiteContratado();
    }
    
    public double consultaSaldoATM() { //consulta o saldo do ATM
        return this.saldoATM;
    }
    
    //faz a retirada de um valor da conta corrente
    public void saque(double valor) {
        if (valor < b1.getTipoNumerario()) {
            System.out.println("\nNão é possível sacar valores menores que " + String.format("R$%.2f", (double) b1.getTipoNumerario()));
            return;
        }        
    	//Checa o saldo do ATM
        if (valor > this.saldoATM) {
            System.out.println("ATM sem saldo");
            return;
        }        
        //verifica se o saldo da conta é zero e se o limite é falso
        if ((this.contaCorrente.getSaldo() == 0 && this.contaCorrente.isLimite() && valor > this.contaCorrente.getLimiteContratado()) ||
                (this.contaCorrente.getSaldo() == 0 && !this.contaCorrente.isLimite()) ||
                (this.contaCorrente.getSaldo() > 0 && this.contaCorrente.isLimite() && valor > (this.contaCorrente.getSaldo() + this.contaCorrente.getLimiteContratado()))) {
            System.out.println("\nNão tem saldo suficiente para saque");
        } else {
            //cria uma string para a selecao do numerário
            double valores = selecaoNumerario(valor);

            //verifica se o saldo da conta é maior que o valor recebido
            if (this.contaCorrente.getSaldo() >= valores) {
                this.contaCorrente.saque(valores); //retira o valor da conta
            //se o saldo da conta for menor que valor e o limite for true e
            //o limite contratado for maior que o valor recebido
            } else if (this.contaCorrente.getSaldo() < valores && this.contaCorrente.isLimite() && (this.contaCorrente.getLimiteContratado() + this.contaCorrente.getSaldo()) >= valores) {
                //se o saldo da conta for maior que zero(0)
                if (this.contaCorrente.getSaldo() > 0) {
                    valores -= this.contaCorrente.getSaldo();
                    this.contaCorrente.setSaldo(0);
                    this.contaCorrente.setLimiteContratado(this.contaCorrente.getLimiteContratado() - valores);
                } else {
                    this.contaCorrente.setLimiteContratado(this.contaCorrente.getLimiteContratado() - valores);
                } //fim do if
            } //fim do if
        }
    } //fim do método saque
    
    public void deposito(double valor) { //faz o deposito de um valor
        this.contaCorrente.deposito(valor);
        this.setLog(DEPOSITO, valor);
    }
    //cria a string para a seleção de numerários
    public double selecaoNumerario(double valor) {
        String valores = "";
        double valorOriginal = valor;
        //bandeja 4
        //verifica se o valor é maior ou igual ao tipo de numerário
        //se a quantida de numerário é maior que zero(0) e
        //se a bandeja está ativa
        if (valor >= this.b4.getTipoNumerario() &&
            this.b4.getQuantidadeNumerario() > 0 && this.b4.isAtiva()) {
            int b4Necessarios = b4.getNumerariosNecessarios(valor);
            
            //verifica se nos numerários necessários é maior que a
            //quantidade de numerários
            if (b4Necessarios > b4.getQuantidadeNumerario()) {
                b4Necessarios = b4.getQuantidadeNumerario();
                b4.setQuantidadeNumerario(0);
                b4.setAtiva(false);
            } else {
                b4.setQuantidadeNumerario(b4.getQuantidadeNumerario() -
                                          b4Necessarios);
            }
            
            valores += b4Necessarios + "x R$" + String.format("%.2f", 
                (double) b4.getTipoNumerario());
            
            valor = valor - (b4Necessarios * b4.getTipoNumerario());
        }
        
        //bandeja 3
        //verifica se o valor é maior ou igual ao tipo de numerário
        //se a quantida de numerário é maior que zero(0) e
        //se a bandeja está ativa
        if (valor >= this.b3.getTipoNumerario() &&
            this.b3.getQuantidadeNumerario() > 0 && this.b3.isAtiva()) {
            int b3Necessarios = b3.getNumerariosNecessarios(valor);
            
            //verifica se nos numerários necessários é maior que a
            //quantidade de numerários
            if (b3Necessarios > b3.getQuantidadeNumerario()) {
                b3Necessarios = b3.getQuantidadeNumerario();
                b3.setQuantidadeNumerario(0);
                b3.setAtiva(false);
            } else {
                b3.setQuantidadeNumerario(b3.getQuantidadeNumerario() -
                                          b3Necessarios);
            }

            valores += "\n" + b3Necessarios + "x R$" + String.format("%.2f", 
                (double) b3.getTipoNumerario());

            valor = valor - (b3Necessarios * b3.getTipoNumerario());
        }
        
        //bandeja 2
        //verifica se o valor é maior ou igual ao tipo de numerário
        //se a quantida de numerário é maior que zero(0) e
        //se a bandeja está ativa
        if (valor >= this.b2.getTipoNumerario() &&
            this.b2.getQuantidadeNumerario() > 0 && this.b2.isAtiva()) {
            int b2Necessarios = b2.getNumerariosNecessarios(valor);
            
            //verifica se nos numerários necessários é maior que a
            //quantidade de numerários
            if (b2Necessarios > b2.getQuantidadeNumerario()) {
                b2Necessarios = b2.getQuantidadeNumerario();
                b2.setQuantidadeNumerario(0);
                b2.setAtiva(false);
            } else {
                b2.setQuantidadeNumerario(b2.getQuantidadeNumerario() -
                                          b2Necessarios);
            }

            valores += "\n" + b2Necessarios + "x R$" + String.format("%.2f", 
                (double) b2.getTipoNumerario());

            valor = valor - (b2Necessarios * b2.getTipoNumerario());
        }
        
        //bandeja 1
        //verifica se o valor é maior ou igual ao tipo de numerário
        //se a quantida de numerário é maior que zero(0) e
        //se a bandeja está ativa
        if (valor >= this.b1.getTipoNumerario() &&
            this.b1.getQuantidadeNumerario() > 0 && this.b1.isAtiva()) {
            int b1Necessarios = b1.getNumerariosNecessarios(valor);
            
            //verifica se nos numerários necessários é maior que a
            //quantidade de numerários
            if (b1Necessarios > b1.getQuantidadeNumerario()) {
                b1Necessarios = b1.getQuantidadeNumerario();
                b1.setQuantidadeNumerario(0);
                b1.setAtiva(false);
            } else {
                b1.setQuantidadeNumerario(b1.getQuantidadeNumerario() -
                                          b1Necessarios);
            }

            valores += "\n" + b1Necessarios + "x R$" + String.format("%.2f", 
                (double) b1.getTipoNumerario());
            
            valor = valor - (b1Necessarios * b1.getTipoNumerario());
        }
        double valorARetornar;
        
        if (valor == valorOriginal) {
            valorARetornar = 0;
            this.setLog(SAQUE, 0);
        } else if (valor > 0) {
            valores += "\nNão é possivel retirar R$" + valor;
            this.setLog(SAQUE, (valorOriginal - valor));
            valorARetornar = valorOriginal - valor;
        } else {
            this.setLog(SAQUE, valorOriginal);
            valorARetornar = valorOriginal;
        }
        
        atualizaSaldoATM();
        
        System.out.println(valores); //mostra o resultado da variável
        return valorARetornar;
    } //fim do método selecaoNumerario
    
    //cria a string para exibir o log
    public String exibeExtratoLog() {
        String mensagem = "";
        for(String msg : this.log) {
            mensagem += msg + "\n";
        }
        
        return mensagem;
    }
} //fim da classe ATM