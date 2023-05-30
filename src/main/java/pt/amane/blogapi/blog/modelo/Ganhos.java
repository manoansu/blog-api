package pt.amane.blogapi.blog.modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class Ganhos {

    private BigDecimal valorPagosPorPalavras;
    private int quantidadePalavras;
    private BigDecimal totalGanho;

    public Ganhos(BigDecimal valorPagosPorPalavras, int quantidadePalavras, BigDecimal totalGanho) {
        this.valorPagosPorPalavras = valorPagosPorPalavras;
        this.quantidadePalavras = quantidadePalavras;
        this.totalGanho = totalGanho;
    }

    public BigDecimal getValorPagosPorPalavras() {
        return valorPagosPorPalavras;
    }

    public void setValorPagosPorPalavras(BigDecimal valorPagosPorPalavras) {
        this.valorPagosPorPalavras = valorPagosPorPalavras;
    }

    public int getQuantidadePalavras() {
        return quantidadePalavras;
    }

    public void setQuantidadePalavras(int quantidadePalavras) {
        this.quantidadePalavras = quantidadePalavras;
    }

    public BigDecimal getTotalGanho() {
        return totalGanho;
    }

    public void setTotalGanho(BigDecimal totalGanho) {
        this.totalGanho = totalGanho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ganhos ganhos)) return false;
        return quantidadePalavras == ganhos.quantidadePalavras && valorPagosPorPalavras.equals(ganhos.valorPagosPorPalavras) && totalGanho.equals(ganhos.totalGanho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorPagosPorPalavras, quantidadePalavras, totalGanho);
    }
}
