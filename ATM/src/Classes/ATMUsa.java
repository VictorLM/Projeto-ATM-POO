package Classes;

import java.util.Scanner;

public class ATMUsa { //inicia a classe ATMUsa
    static int charFalse = 0;
    public static void main(String [] args0){ //cria o método main
        System.out.println("\n\nIniciando ATM ...\n\n\n");
        Bandeja b1 = new Bandeja(false, 10, 50); //inicia a bandeja 1
        Bandeja b2 = new Bandeja(false, 20, 50); //inicia a bandeja 2
        Bandeja b3 = new Bandeja(false, 50, 50); //inicia a bandeja 3
        Bandeja b4 = new Bandeja(false, 100, 50); //inicia a bandeja 4
        //instacia a classe ContaCorrente
        ContaCorrente conta = new ContaCorrente("", "", 0.00);
        //ContaCorrente conta = new ContaCorrente("Joao da Silva", "12345", 1000.00);
        //instancia a classe ATM
        ATM atm = new ATM(b1, b2, b3, b4, conta);
        atm.addBandeja(1); //adiciona a bandeja 1
        atm.addBandeja(2); //adiciona a bandeja 2
        atm.addBandeja(3); //adiciona a bandeja 3
        atm.addBandeja(4); //adiciona a bandeja 4
        // --------------------------------------------------------------------
        Scanner cc = new Scanner(System.in); 
        String nome = "", numConta = "", limiteContratado = "", saldoConta = "";
        String saca = "", deposita = "";
        do {
            System.out.print("Digite o nome do cliente: ");
            nome = cc.next();
            letra(nome);
        } while(charFalse == 0);
        conta.setNome(nome);
        System.out.println("");
        charFalse = 0;
        do {
            System.out.print("Entre com o número da conta: ");
            numConta = cc.next();
            digito(numConta);
        } while(charFalse == 0);
        conta.setConta(numConta);
        String limite;
        charFalse = 0;
        boolean isOpValid;
        // verifica se tem limite, se é numérico e se é maior que zero
        do {
            System.out.println("");
            System.out.print("A conta tem limite(S/N)? ");
            limite = cc.next();
            switch(limite.toUpperCase()) {
                case "S":
                    conta.setLimite(true);
                    do {
                        System.out.print("\nDigite o valor contratado: ");
                        limiteContratado = cc.next();
                        if (!isReal(limiteContratado))
                            System.out.println("Valor do limite não é válido!!");
                    } while (charFalse == 0);
                    double limiteContratados = Double.parseDouble(limiteContratado);
                    if (limiteContratados > 0) {
                        conta.setLimiteContratado(limiteContratados);
                        isOpValid = false;
                    } else {
                        System.out.print("Não pode contratar limite menor ou igual a zero.");
                        isOpValid = true;
                    }
                    break;
                case "N":
                    System.out.println("Nao contratatou limite.");
                    conta.setLimite(false);
                    conta.setLimiteContratado(0.00);
                    isOpValid = false;
                    break;
                default:
                    System.out.print("Opção inválida, escolha S ou N.");
                    isOpValid = true;
                    break;
            }
        } while(isOpValid);
        isOpValid = false;
        charFalse = 0;
        // verifica se o saldo da conta é numérico e se é maior que zero
        do {
            do {
                System.out.print("\nEntre com o saldo da conta: ");
                saldoConta = cc.next();
                if (!isReal(saldoConta))
                    System.out.println("Saldo da conta não é válido!!");
            } while (charFalse == 0);
            double saldoContas = Double.parseDouble(saldoConta);
            if (saldoContas > 0) {
                conta.setSaldo(saldoContas);
                System.out.println("");
                isOpValid = false;
            } else {
                System.out.println("Saldo da Conta não poder ser menor ou igual a zero");
                isOpValid = true;
            }
        } while(isOpValid);
        
/*        // --------------------------------------------------------------------
       conta.setLimite(true); //inicia o limite da conta como verdadeiro
        //inicia o valor do limite contratado
        conta.setLimiteContratado(2000.00);
       */
        Scanner opcao = new Scanner(System.in);
        int op;
        
        do {
            menu();
            System.out.print("Entre com a opção: ");
            op = opcao.nextInt();
            switch(op) {
                case 6:
                    System.out.println("\n\n\nSistema finalizado!");
                    System.exit(0);
                    break;
                case 1:
                    charFalse = 0;
                    do {
                        System.out.print("Valor a sacar: ");
                        saca = opcao.next();
                        if (!isReal(saca))
                            System.out.println("Valor a sacar não é válido");
                    } while (charFalse == 0);
                    Double sacas = Double.parseDouble(saca);
                    atm.saque(sacas);
                    break;
                case 2:
                    charFalse = 0;
                    do {
                        System.out.print("Valor a depositar: ");
                        deposita = opcao.next();
                        if (!isReal(deposita))
                            System.out.println("Valor a depositar não é válido!!");
                    } while(charFalse == 0);
                    double depositas = Double.parseDouble(deposita);
                    atm.deposito(depositas);
                    break;
                case 3:
                    System.out.println("Saldo da conta: R$ " + conta.getSaldo());
                    System.out.println("Saldo Limite: R$ " + conta.getLimiteContratado());
                    break;
                case 4: 
                    System.out.println("Extrato do Log\n" + atm.exibeExtratoLog());
                    break;
                case 5:
                    System.out.println("b1: " + b1.getQuantidadeNumerario());
                    System.out.println("b2: " + b2.getQuantidadeNumerario());
                    System.out.println("b3: " + b3.getQuantidadeNumerario());
                    System.out.println("b4: " + b4.getQuantidadeNumerario());
                    System.out.println("Saldo do ATM: R$ " + atm.consultaSaldoATM());
                    break;
                default:
                    System.out.println("Opção inválida!");
            } //fim do switch
        } while (op != 6);
    } //fim do método main
    
    static void menu() {
        System.out.println("\n\nMenu Principal\n");
        System.out.println("1 - Saque");
        System.out.println("2 - Depósito");
        System.out.println("3 - Saldo C.C.");
        System.out.println("4 - Log");
        System.out.println("5 - Saldo ATM");
        System.out.println("6 - Sair");
    }
    
    static void letra(String nome) {
        // se o primeiro caracter nao for letra, invalida nome
        if (Character.isLetter(nome.charAt(0))) {
            for (int i=0;i <nome.length() ;i++) {
                if (Character.isLetterOrDigit(nome.charAt(i))) {
                        
                } else {
                    charFalse=1;
                }
            }
            charFalse = 1;
        } else {
            System.out.println("Nome com Caracter inválido");
            charFalse = 0;
        }
    }
    
    static void digito(String conta) {
        int letra = 0;
        // verifica se o primeiro digito da conta é um numero
        if (Character.isDigit(conta.charAt(0))) {
            for (int i=0;i <conta.length() ;i++) {
                if (Character.isDigit(conta.charAt(i))) {

                } else {
                    letra = 1;
                }
            }
        } else {
            letra = 1;
        }
        // verificado os digitos, se houver letra invalida a conta
        if (letra == 1) {
            System.out.println("Existe caracter inválido.");
            charFalse = 0;
        } else {
            charFalse = 1;
        }
    }
    
    public static boolean isReal(String s){
        boolean isNumber = false;
        boolean isReal = false;
        for(int x=0; x<s.length(); x++){
            if((s.charAt(x) >= '0' && s.charAt(x) <= '9') || s.charAt(x) == '.'){ //verifico se é um numero e/ou se contem um ponto '.'
                isNumber = true;
                if(s.charAt(x) == '.' && x>0 && x<s.length()-1){ //aqui eu verifico se o ponto nao esta no começo ou no fim da string.
                    if(!isReal) //se o numero ja tiver sido validado como real, ele ja tem um ponto, se tiver outro nao é um numero valido
                        isReal = true;
                    else
                        return false;
                }else{
                    if(s.charAt(x) == '.') //se for um segundo, ou superior, ponto da string e estiver nas estremidades finaliza.
                        return false;
                }
            }else{
                return false;
            }
        }
        if(isNumber && isReal) { //se os dois forem verdade entao é a string é um numero este numero é real.
            charFalse = 1;
            return true;
        } else {
            charFalse = 0;
            return false;
        }
    }
}
