package Classes;

public class Bandeja { //inicia a classe Bandeja
    private boolean ativa;
    private int tipoNumerario;
    private int quantidadeNumerario;
    
    public Bandeja(boolean ativa, int tipoNumerario, int quantidadeNumerario) { //cria o construtor Bandeja
        this.ativa = ativa;
        this.tipoNumerario = tipoNumerario;
        this.quantidadeNumerario = quantidadeNumerario;
    } //fim do construtor Bandeja

    public boolean isAtiva() { //retorna se a bandeja esta ativa ou não
        return ativa;
    }

    public void setAtiva(boolean ativa) { //recebe a ativação
        this.ativa = ativa;
    }

    public int getTipoNumerario() { //retorna o tipo de numerário
        return tipoNumerario;
    }

    public void setTipoNumerario(int tipoNumerario) { //recebe o tipo de numerário
        this.tipoNumerario = tipoNumerario;
    }

    public int getQuantidadeNumerario() { //retorna a quantidade de numerário
        return quantidadeNumerario;
    }

    public void setQuantidadeNumerario(int quantidadeNumerario) { //recebe a quantidade de numerário
        this.quantidadeNumerario = quantidadeNumerario;
    }
    
    public int calculaSaldoBandeja() { //calcula o saldo da bandeja
        return quantidadeNumerario * tipoNumerario;
    }
    
    public int getNumerariosNecessarios(double valor) { //retorna os numerários necessários
        return (int) valor / this.tipoNumerario;
    }
} //fim da classe bandeja
