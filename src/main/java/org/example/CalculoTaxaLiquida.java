package org.example;

import java.util.Scanner;

public class CalculoTaxaLiquida {

    private double capIn;
    private double taxa_anual;
    private int periodo;
    String resposta = "s";

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CalculoTaxaLiquida c = new CalculoTaxaLiquida();
        c.executar();
    }

    public void executar() {
        while (resposta.equalsIgnoreCase("s")) {
            lerEntradas();
            periodo = validarPeriodo(periodo);

            double montante = montante(capIn, periodo);
            double lb = lucroBruto();
            double ir = impostoRenda();
            double mLiq = mLiquido();
            double taxaLiquida = taxaLiquidaMensal();

            System.out.println("O seu montante final bruto é R$" + String.format("%.2f", montante));
            System.out.println("Seu lucro bruto é de R$" + String.format("%.2f", lb));
            System.out.println("O imposto de renda que deverá ser pago é R$" + String.format("%.2f", ir));
            System.out.println("Seu montante líquido é R$" + String.format("%.2f", mLiq));
            System.out.println("A sua taxa líquida mensal, ou seja, quanto seu dinheiro renderá por mês é de " + String.format("%.2f", taxaLiquida) + "%");

            System.out.println("Deseja realizar outra simulação? (s/n): ");
            resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("n")) {
                System.out.println("Programa encerrado.");
                sc.close();
                break;
            }
        }
    }

    public void lerEntradas() {
        System.out.println("Informe o capital inicial: ");
        capIn = sc.nextDouble();

        System.out.println("Informe a taxa anual: ");
        taxa_anual = sc.nextDouble();

        System.out.println("Informe o número de meses: ");
        periodo = sc.nextInt();
        sc.nextLine();
    }

    public int validarPeriodo(int periodo) {
        while (periodo <= 0) {
            System.out.println("O período deve ser maior que zero. Informe novamente: ");
            periodo = sc.nextInt();
        }
        return periodo;
    }

    public double conversaoTaxaAnualEmMensal(double taxa_anual) {
        double taxaM = Math.pow(1 + taxa_anual / 100, (double) 1 / 12) - 1;
        return taxaM;
    }

    public double montante(double capIn, int periodo) {
        double resultadoPotencia = Math.pow(1 + conversaoTaxaAnualEmMensal(taxa_anual), periodo);
        double m = capIn * resultadoPotencia;
        return m;
    }

    public double lucroBruto(){
        double m = montante(capIn, periodo);
        double lucroBruto = m - capIn;
        return lucroBruto;
    }

    public double calculoAliquota(int periodo) {
        double aliquota;
        if (periodo >= 1 && periodo <= 6) {
            aliquota = 22.5;
        }else if (periodo > 6 && periodo <= 12) {
            aliquota = 20;
        }else if (periodo > 12 && periodo <= 24) {
            aliquota = 17.5;
        }else{
            aliquota = 15;
        }
        return aliquota;
    }

    public double impostoRenda() {
        double aliquota = calculoAliquota(periodo);
        double aliq = aliquota / 100;
        double m = montante(capIn, periodo);
        double lucroBruto = m - capIn;
        double ir = lucroBruto * aliq;
        return ir;
    }

    public double mLiquido() {
        double m = montante(capIn, periodo);
        double ir = impostoRenda();
        double mLiq = m - ir;
        return mLiq;
    }

    public double taxaLiquidaMensal() {
        double mLiq = mLiquido();
        double resultadoPotencia = Math.pow(mLiq / capIn, 1.0 / periodo);
        double taxaLiquida = (resultadoPotencia - 1) * 100;
        return taxaLiquida;
    }

    public String pergunta() {
        System.out.println("Deseja realizar outra simulação? (s/n): ");
        return sc.nextLine();
    }



}
