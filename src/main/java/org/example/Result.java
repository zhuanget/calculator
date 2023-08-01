package org.example;

import org.example.enums.OperatorEnum;

import java.math.BigDecimal;

public class Result {

    // 操作符 +-*/
    private final OperatorEnum operator;

    private final BigDecimal num1;

    private final BigDecimal num2;

    private final BigDecimal result;

    public Result(OperatorEnum operator, BigDecimal num1, BigDecimal num2, BigDecimal result) {
        this.operator = operator;
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    @Override
    public String toString() {
        return num1.toString() + " " + operator.getOperator() + " "
                + num2.toString() + " = " + result.toString();
    }
}
