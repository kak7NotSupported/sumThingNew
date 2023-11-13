package me.kak7;

import lombok.SneakyThrows;

import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Введите арифметическое выражение\nПример: 2 + 3 или V - III");
            String input = scanner.nextLine();
            if (input.isEmpty()) break;
            System.out.println("Результат: " + calc(input) + "\n");
        }
        scanner.close();
    }

    @SneakyThrows
    public static String calc(String input) {
        String equation = input.replace(" ", "");
        Character operator = null;
        String operand1String = null;
        String operand2String = null;
        boolean isNumbersRoman;
        String equationPattern = "(-?\\d+|[IVXLCDM]+)([+\\-*/])(-?\\d+|[IVXLCDM]+)";

        Pattern pattern = Pattern.compile(equationPattern);
        Matcher matcher = pattern.matcher(equation);

        if (matcher.find()) {
            operand1String = matcher.group(1);
            operator = matcher.group(2).charAt(0);
            operand2String = matcher.group(3);
        }

        if (!Pattern.matches(equationPattern, equation)) {
            throw new Exception("Unknown format");
        }

        isNumbersRoman = Pattern.matches("[IVXLCDM]+", operand1String) &&
                Pattern.matches("[IVXLCDM]+", operand2String);

        int operand1 = isNumbersRoman ? fromRoman(operand1String) : Integer.parseInt(operand1String);
        int operand2 = isNumbersRoman ? fromRoman(operand2String) : Integer.parseInt(operand2String);

        int result = OPERATIONS.get(operator).apply(operand1, operand2);
        if (isNumbersRoman && result < 1) throw new Exception("Roman negative numbers don't exist");

        // Ограничения по числам
        if (operand1 < -10 || operand1 > 10 || operand2 < -10 || operand2 > 10) {
            throw new Exception("Numbers must be in the range from -10 to 10");
        }

        return isNumbersRoman ?
                toRoman(result) :
                String.valueOf(result);
    }


    public static String toRoman(int number) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < romanValues.length; i++) {
            while (number >= romanValues[i]) {
                number -= romanValues[i];
                roman.append(romanNumerals[i]);
            }
        }
        return roman.toString();
    }

    public static int fromRoman(String roman) {
        Map<Character, Integer> romanNumerals = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );

        int result = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(roman.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

    private static final Map<Character, BiFunction<Integer, Integer, Integer>> OPERATIONS
            = Map.of(
            '+', (a, b) -> a + b,
            '-', (a, b) -> a - b,
            '*', (a, b) -> a * b,
            '/', (a, b) -> a / b
    );

}