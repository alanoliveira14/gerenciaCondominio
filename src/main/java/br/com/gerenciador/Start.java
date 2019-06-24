package br.com.gerenciador;

import br.com.gerenciador.dao.*;
import br.com.gerenciador.entity.*;

import javax.swing.*;

public class Start {

    public static void main(String[] args) {

        String identificacao = JOptionPane.showInputDialog(null, "informe o cpf/cnpj");

        if (identificacao.equals("1040")){



            Boolean repete = true;

            while (repete){
                Integer captura = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma opção:\n" +
                        "1 - Cadastrar locatario\n" +
                        "2 - Cadastrar Locador\n" +
                        "3 - Alugar\n" +
                        "4 - Fechar cobrança\n" +
                        "5 - Criar Aviso\n" +
                        "6 - Inserir nova conta\n" +
                        "7 - Cobrar Vaga de Estacionamento\n" +
                        "8 - Listar\n"+
                        "9 - Novo Imovel\n"+
                        "0 - Sair"));

                if (captura == 1){
                    Locatario locatario = new Locatario();

                    locatario.setNomeLocatario(JOptionPane.showInputDialog(null, "Digite o nome do Locatario"));
                    locatario.setCpfCnpj(JOptionPane.showInputDialog(null, "Digite o CPF/CNPJ do locatario"));
                    locatario.setEmail(JOptionPane.showInputDialog(null, "Digite o email"));
                    locatario.setTelefone(JOptionPane.showInputDialog(null, "Digite o telefone de contato"));

                    new LocatarioDAO().insetLocatario(locatario);


                }else if (captura == 2){

                    Locador locador = new Locador();

                    locador.setNomeLocador(JOptionPane.showInputDialog(null, "Digite o Nome do Locador"));
                    locador.setCpfCnpj(JOptionPane.showInputDialog(null, "Digite o CPF/CNPJ do locador"));
                    locador.setEmail(JOptionPane.showInputDialog(null, "Digite o email"));
                    locador.setTelefone(JOptionPane.showInputDialog(null, "Digite o telefone de contato"));
                    locador.setRepresentante(JOptionPane.showInputDialog(null, "Informe um representante para contato"));

                    new LocadorDAO().insertLocador(locador);

                }else if (captura == 9){
                    Imovel imovel = new Imovel();

                    imovel.setEndereco(JOptionPane.showInputDialog(null, "Digite o Endereço do Imovel a Alugar"));
                    imovel.setDescricaoImovel(JOptionPane.showInputDialog(null, "Digite uma Descrição para o Imovel"));
                    imovel.setValorAluguel(Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o Valor do Aluguel")));

                    new ImovelDAO().insertImovel(imovel);

                }else if (captura == 3){
                    Aluguel aluguel = new Aluguel();
                    String imoveis = new ImovelDAO().imoveisDisponiveis();
                    if(imoveis.equals("")){
                        JOptionPane.showMessageDialog(null, "Sem imoveis para alugar");

                    }else{
                        JOptionPane.showMessageDialog(null,imoveis);

                        aluguel.setIdImovel(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o numero do Imovel escolhido")));
                        aluguel.setCpfCnpjLocador(JOptionPane.showInputDialog(null, "Identifique o Locador"));
                        aluguel.setCpfCnpjLocatario(JOptionPane.showInputDialog(null, "Identifique o Locatario"));
                        aluguel.setValidadeContrato(JOptionPane.showInputDialog(null, "Diga a data de final de contrato"));

                        new AluguelDAO().insertAluguel(aluguel);
                    }

                }else if(captura == 6){

                    ContasComunitarias conta = new ContasComunitarias();

                    JOptionPane.showMessageDialog(null, new ContaComumDAO().tipoContas());

                    conta.setIdNomeConta(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a identificação da conta")));
                    conta.setValorConta(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor da conta")));
                    conta.setMes(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero do mes")));
                    conta.setAno(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ano")));

                    new ContaComumDAO().insertConta(conta);

                }else if(captura == 4){
                    Integer mesFechamento = 0;
                    Integer anoFechamento = 0;

                    mesFechamento = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o mes do fechamento"));
                    anoFechamento = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano do fechamento"));

                    Double valorContasComuns = new ContaComumDAO().getValoresDeContasComuns(mesFechamento, anoFechamento);

                    JOptionPane.showMessageDialog(null, "O valor das contas comunitarias, dividas por aluguel é de: R$ " + valorContasComuns);

                    String cpfCnpjLocatario = JOptionPane.showInputDialog(null, "Identifique o Locatario que deseja realizar o fechamento de cobrança");

                    JOptionPane.showMessageDialog(null, new ContaComumDAO().fazFechamento(cpfCnpjLocatario, valorContasComuns));

                }else if(captura == 5){

                    Integer tipoAviso = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual aviso deseja criar?\n 1 - Manutenção do Elevador \n 2 - Direcionado" ));

                    if(tipoAviso == 1){
                        String data = JOptionPane.showInputDialog(null, "Informe a data da manutenção");
                        String motivo = JOptionPane.showInputDialog(null, "Informe o motivo");

                        Aviso aviso = new Aviso();

                        aviso.setDestinatario(0);
                        aviso.setMensagem("Manutenção do elevador dia: " + data + " Por motivos de: " + motivo);

                        new AvisoDAO().criaAvisoGenerico(aviso);

                    }else{

                        Aviso aviso = new Aviso();

                        aviso.setIdentificacaoDest(JOptionPane.showInputDialog(null, "Identifique o Locatario"));
                        aviso.setMensagem(JOptionPane.showInputDialog(null, "Digite a mensagem do Aviso"));

                        new AvisoDAO().criaAvisoDirecionado(aviso);

                    }

                }else if(captura == 7){
                    JOptionPane.showMessageDialog(null, "Vagas de Estacionamento são acrescidas no valor do aluguel, R$ 100,00 por vaga");

                    String cpfCnpj = JOptionPane.showInputDialog(null, "Identifique o Locatario que possui a vaga");

                    new ContaComumDAO().insertDeVaga(cpfCnpj);

                }else if(captura == 8){

                    Integer hue = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o que deseja listar:\n 1 - Locadores\n2 - Locatarios"));

                    if(hue == 1){
                        JOptionPane.showMessageDialog(null, new LocadorDAO().getLocadores());
                    }else{
                        JOptionPane.showMessageDialog(null, new LocatarioDAO().getLocatarios());
                    }

                }

                else if (captura == 0){
                    JOptionPane.showMessageDialog(null, "Até mais!");
                    repete = false;
                    break;
                }

            }

        }else{
            JOptionPane.showMessageDialog(null, new AvisoDAO().mostraAvisos(identificacao));
        }


    }


}
