package tests.entities;

import entities.Financing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTests {

    @Test
    public void deveCriarObjetoComDadosCorretosQuandoForemValidos() {

        Financing financing = new Financing(100000.0, 2000.0, 80);

        Assertions.assertEquals(100000.0, financing.getTotalAmount());
        Assertions.assertEquals(2000.0, financing.getIncome());
        Assertions.assertEquals(80, financing.getMonths());
    }

    @Test
    public void deveLancarIllegalArgumentExceptionQuandoDadosForemInvalidos() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(100000.0, 2000.0, 20);
        });
    }

    @Test
    public void atualizarValorTotalQuandoDadosForemValidos() {

        Financing financing = new Financing(100000.0, 2000.0, 80);
        financing.setTotalAmount(90000.0);
        Assertions.assertEquals(90000.0, financing.getTotalAmount());
    }

    @Test
    public void deveLancarIllegalArgumentExceptionQuandoSetAmountTotalInvalido() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(100000.0, 2000.0, 20);
            financing.setTotalAmount(200000.0);
        });
    }

    @Test
    public void deveAtualizarValoresDoSetIncomeQuandoDadosForemValidos() {

        Financing financing = new Financing(100000.0, 2000.0, 80);
        financing.setIncome(3000.0);
        Assertions.assertEquals(3000.0, financing.getIncome());
    }

    @Test
    public void deveLancarIllegalArgumentExceptionQuandoSetIncomeInvalido() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(100000.0, 2000.0, 80);
            financing.setIncome(100.0);
        });
    }

    @Test
    public void deveAtualizarValoresDoSetMonthsQuandoDadosForemValidos() {

        Financing financing = new Financing(100000.0, 2000.0, 80);
        financing.setMonths(81);
        Assertions.assertEquals(81, financing.getMonths());
    }

    @Test
    public void deveLancarIllegalArgumentExceptionQuandoSetMonthsInvalido() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(100000.0, 2000.0, 80);
            financing.setMonths(70);
        });
    }

    @Test
    public void deveCalcularCorretamenteValorDaEntrada() {
        Financing financing = new Financing(100000.0, 2000.0, 80);
        Assertions.assertEquals(20000.0, financing.entry());
    }

    @Test
    public void deveCalcularCorretamenteValorDaPrestacao() {
        Financing financing = new Financing(100000.0, 2000.0, 80);
        Assertions.assertEquals(1000.0, financing.quota());
    }

}
