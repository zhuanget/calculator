package org.example;

import org.example.enums.LogType;
import org.example.enums.OperatorEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class Calculator {
    // 存放计算结果
    private Result result;
    // 执行计算的第一个数
    private BigDecimal num1;
    // 执行计算的第二个数
    private BigDecimal num2;
    // 存放undo操作的栈
    private final Stack<Result> undoStack;
    // 存放redo操作的栈
    private Stack<Result> redoStack;

    public Calculator() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * 计算结束后续操作，保存undo记录和重置redo记录
     */
    private void afterCalculate(Result result) {
        undoStack.push(result);
        if (!redoStack.empty()) {
            redoStack = new Stack<>();
        }
    }

    /**
     * 检查并设置参与计算的两个数数值
     * @param strNum1 参与计算的第一个数
     * @param strNum2 参与计算的第二个数
     * @return 输入是否有误
     */
    private boolean checkAndSetCalculatedNums(String strNum1, String strNum2) {
        try {
            num1 = new BigDecimal(strNum1);
            num2 = new BigDecimal(strNum2);
        } catch (NumberFormatException ex) {
            log(ex.toString(), LogType.ERROR.getLogType());
            return false;
        }
        return true;
    }

    public void add(String strNum1, String strNum2) {
        if (checkAndSetCalculatedNums(strNum1, strNum2)) {
            BigDecimal addResult = num1.add(num2);
            result = new Result(OperatorEnum.ADD, num1, num2, addResult);
            afterCalculate(result);
        }
    }

    public void subtract(String strNum1, String strNum2) {
        if (checkAndSetCalculatedNums(strNum1, strNum2)) {
            BigDecimal subResult = num1.subtract(num2);
            result = new Result(OperatorEnum.SUBTRACT, num1, num2, subResult);
            afterCalculate(result);
        }
    }

    public void multiply(String strNum1, String strNum2) {
        if (checkAndSetCalculatedNums(strNum1, strNum2)) {
            BigDecimal multiplyResult = num1.multiply(num2);
            result = new Result(OperatorEnum.MULTIPLY, num1, num2, multiplyResult);
            afterCalculate(result);
        }
    }

    public void divide(String strNum1, String strNum2) {
        if (checkAndSetCalculatedNums(strNum1, strNum2)) {
            // 校验除数是否为0
            if (num2.doubleValue() == 0) {
                log("divider should not be zero", LogType.ERROR.getLogType());
                return;
            }
            BigDecimal divideResult = num1.divide(num2, 6, RoundingMode.HALF_UP);
            result = new Result(OperatorEnum.DIVIDE, num1, num2, divideResult);
            afterCalculate(result);
        }
    }

    public void undo() {
        if (undoStack.empty()) {
            log("cannot undo, because undoStack is empty.", LogType.WARNING.getLogType());
            return;
        }
        Result lastResult = undoStack.pop();
        redoStack.push(lastResult);
        result = undoStack.empty() ? null : undoStack.peek();
    }

    public void redo() {
        if (redoStack.empty()) {
            log("cannot redo, because redoStack is empty.", LogType.WARNING.getLogType());
            return;
        }
        Result redoResult = redoStack.pop();
        undoStack.push(redoResult);
        result = redoResult;
    }

    /**
     * 用于获取当前结果
     * @return 当前结果
     */
    public Result getResult() {
        return result;
    }

    /**
     * 模拟打印当前结果
     */
    public void printCurrentResult() {
        System.out.println("current result: " + (result == null ? null : result.getResult()));
    }

    /**
     * 简单日志输出函数
     * @param log 日志内容
     * @param logType 日志级别，见enums.LogType
     */
    private void log(String log, String logType) {
        System.out.println("Calculator " + logType + " log: " + log);
    }
}
