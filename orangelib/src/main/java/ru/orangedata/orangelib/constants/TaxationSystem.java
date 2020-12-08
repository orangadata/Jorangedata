package ru.orangedata.orangelib.constants;

/**
 * Created by Alexey Padyukov on 19.06.2017.
 */

//Число от 0 до 5
public class TaxationSystem {

    //Общая, ОСН
    public static final int OSN = 0;
    //Упрощенная доход, УСН доход
    public static final int USN_INCOME = 1;
    //Упрощенная доход минус расход, УСН доход - расход
    public static final int USN_INCOME_COSTS = 2;
    //Единый налог на вмененный доход, ЕНВД
    public static final int ENVD = 3;
    //Единый сельскохозяйственный налог, ЕСН
    public static final int ESN = 4;
    //Патентная система налогообложения, Патент
    public static final int PATENT = 5;

}
