public class mathUtils {

    /**
     * add to numbers
     * @param firstNum the first number
     * @param secondNum the second number
     * @return the sum
     */
    public static double sum(double firstNum, double secondNum){
        return firstNum + secondNum;
    }

    /**
     * subtract secondNum form firstNum
     * @param firstNum the firstNum
     * @param secondNum the secondNum
     * @return the result
     */
    public static double subtract(double firstNum, double secondNum){
        return firstNum - secondNum;
    }

    /**
     * reverse the sign of a number (e.g. 5 -> -5, -6 -> 6)
     * @param num the number
     * @return the result
     */
    public static double reverseSign(double num){
        return -num;
    }

    /**
     * multiply two number
     * @param firstNum the first number
     * @param secondNum the second number
     * @return the result
     */
    public static double multiply(double firstNum, double secondNum){
        return firstNum * secondNum;
    }

    /**
     * return the multiplicative inverse of a number
     * @param num the number
     * @return the multiplicative inverse
     */
    public static double multiplicativeInverse(double num){
        return 1.0 / num;
    }

    /**
     * divide firstNum by secondNum
     * @param firstNum the first number
     * @param secondNum the second number
     * @return the result
     */
    public static double divide(double firstNum, double secondNum){
        return firstNum / secondNum;
    }

    /**
     * return the square of a number
     * @param num the number
     * @return the result
     */
    public static double square(double num){
        return num * num;
    }

    /**
     * raise base to exponent
     * @param base the base
     * @param exponent the exponent
     * @return the result
     */
    public static double pow(double base, double exponent){
        return Math.pow(base, exponent);
    }

    /**
     * return the square root of a number
     * @param num the number
     * @return the result
     */
    public static double sqrt(double num){
        return Math.sqrt(num);
    }

    /**
     * return the numRoot-th root of a number
     * @param num the number
     * @param numRoot the numRoot-th root
     * @return the result
     */
    public static double root(double num, double numRoot){
        return Math.pow(num, 1.0 / numRoot);
    }

    /**
     * return the number in percentage (85 -> 0.85)
     * @param num the number
     * @return the result
     */
    public static double percentage(double num){
        return num / 100;
    }

}
