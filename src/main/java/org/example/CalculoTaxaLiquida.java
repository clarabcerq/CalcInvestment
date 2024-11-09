package org.example;

public class CalculoTaxaLiquida {
    public static void main(String[] args) {
        CalculoTaxaLiquida c = new CalculoTaxaLiquida();
        c.capIn = 1000;
        c.taxa_anual = 0.14;
        c.anos = 2;
        c.aliquota = 0.15;
        double montante = c.montante(c.capIn, c.taxa_anual, c.anos);
        double lb = c.lucroBruto();
        double ir = c.impostoRenda(c.aliquota);
        double mLiq = c.mLiquido();
        double taxaLiquida = c.taxaLiquidaAnual();
        System.out.println("O seu montante final bruto é R$" + String.format("%.2f", montante));
        System.out.println("Seu lucro bruto é de R$" + String.format("%.2f", lb));
        System.out.println("O imposto de renda que deverá ser pago é R$" + String.format("%.2f", ir));
        System.out.println("Seu montante líquido é R$" + String.format("%.2f", mLiq));
        System.out.println("A sua taxa líquida anual, ou seja, quanto seu dinheiro renderá por ano é de " + String.format("%.2f", taxaLiquida) + "%");
    }

    private double m;
    private double capIn;
    private double taxa_anual;
    private int anos;
    private double aliquota;

    //1- Calcule o montante final bruto usando juros compostos
    public double montante(double capIn, double taxa_anual, int anos) {
        double resultadoPotencia = Math.pow(1 + taxa_anual, anos);
        double m = capIn * resultadoPotencia;
        return m;
    }

    //2- Calcule o lucro bruto subtraindo o capital inicial do montante final
    public double lucroBruto(){
        double m = montante(capIn, taxa_anual, anos);
        double lucroBruto = m - capIn;
        return lucroBruto;
    }

    //3- Calcule o IR. Como o investimento é de 2 anos, a alíquota de IR será de 15%.
    public double impostoRenda(double aliquota) {
        double m = montante(capIn, taxa_anual, anos);
        double lucroBruto = m - capIn;
        double ir = lucroBruto * aliquota;
        return ir;
    }

    //4- Encontre o montante líquido após o IR
    public double mLiquido() {
        double m = montante(capIn, taxa_anual, anos);
        double ir = impostoRenda(aliquota);
        double mLiq = m - ir;
        return mLiq;
    }

    //5- Calcule a taxa líquida anual
    public double taxaLiquidaAnual() {
        double mLiq = mLiquido();
        double resultadoPotencia = Math.pow(mLiq / capIn, 1.0 / anos);
        double taxaLiquida = (resultadoPotencia - 1) * 100;
        return taxaLiquida;
    }
}
