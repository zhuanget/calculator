package org.example;

/**
 * 模拟计算器的计算及undo。redo，以及一些异常case
 */
public class Test {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("=========== start ==========");
        calculator.printCurrentResult();
        System.out.println(calculator.getResult());
        calculator.add("100", "20.2");
        System.out.println("=========== test add ==========");
        calculator.printCurrentResult();
        calculator.subtract("50", "23");
        System.out.println("=========== test subtract ==========");
        calculator.printCurrentResult();
        calculator.multiply("10", "2.9");
        System.out.println("=========== test multiply ==========");
        calculator.printCurrentResult();
        calculator.divide("100", "20.0");
        System.out.println("=========== test divide ==========");
        calculator.printCurrentResult();
        System.out.println("=========== test input format error ==========");
        calculator.add("1.0.1", "2");
        calculator.undo();
        System.out.println("=========== test undo ==========");
        calculator.printCurrentResult();
        System.out.println("=========== test undo record ==========");
        System.out.println(calculator.getResult());
        calculator.undo();
        System.out.println("=========== test undo ==========");
        calculator.printCurrentResult();
        System.out.println("=========== test undo record ==========");
        System.out.println(calculator.getResult());
        calculator.redo();
        System.out.println("=========== test redo ==========");
        calculator.printCurrentResult();
        calculator.redo();
        System.out.println("=========== test redo ==========");
        calculator.printCurrentResult();
        System.out.println("=========== test redo error ==========");
        calculator.redo();
        System.out.println("=========== test undo ==========");
        calculator.undo();
        calculator.printCurrentResult();
        calculator.add("90", "2000");
        System.out.println("=========== test add ==========");
        calculator.printCurrentResult();
        System.out.println(calculator.getResult());
        System.out.println("=========== test divide error ==========");
        calculator.divide("100", "0.0");
        calculator.printCurrentResult();
        System.out.println("=========== test undo error ==========");
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.printCurrentResult();
    }
}