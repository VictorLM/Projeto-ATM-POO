package Classes;

public class ContaCorrente { //inicia a classe ContaCorrente
    private String nome, conta; //nome e número da conta
    private double saldo, limiteContratado; //saldo e limite contratado da conta
    private boolean limite; //limite da conta (true ou false)
    
    public ContaCorrente(String nome, String conta, double saldo) { //construtor ContaCorrente, inicia atributos
        this.nome = nome;
        this.conta = conta;
        this.saldo = saldo;
    } //fim do constutor ContaCorrente
    
    public String getNome() { //retorna o nome da conta
        return nome;
    }

    public void setNome(String nome) { //recebe o nome da conta
        this.nome = nome;
    }

    public String getConta() { //retorna o número da conta
        return conta;
    }

    public void setConta(String conta) { //recebe o número da conta
        this.conta = conta;
    }

    public double getSaldo() { //retorna o saldo da conta
        return saldo;
    }

    public void setSaldo(double saldo) { //recebe o saldo da conta
        this.saldo = saldo;
    }

    public double getLimiteContratado() { //retorna o limite contratado
        return limiteContratado;
    }

    public void setLimiteContratado(double limiteContratado) { //recebe o limite contratado
        this.limiteContratado = limiteContratado;
    }

    public boolean isLimite() { //retorna se o limite é false ou true
        return limite;
    }

    public void setLimite(boolean limite) { //recebe o limite
        this.limite = limite;
    }
    
    void saque(double valor) { //faz a retirada de um valor da conta
        this.setSaldo(this.getSaldo() - valor);
    }
    
    void deposito(double valor) { //faz o deposito de um valor da conta
        this.setSaldo(this.getSaldo() + valor);
    }
    
    void defineLimiteEspecial(boolean limite, double valor) { //define se a conta tem limite especial
        this.setLimite(limite);
        this.setLimiteContratado(valor);
    }
} //fim da classe ContaCorrente
