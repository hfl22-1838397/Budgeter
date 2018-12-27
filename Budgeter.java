import java.util.*;

// Phil Lin
// 10/26/2018
// CSE142
// TA: Oliver Eriksen
// Assignment #4
//
//This program can calculate your net income and expense of a month
//and give you a valuation based on the information you enter.

public class Budgeter {

    public static final int DAYS_OF_A_MONTH = 30; //user can change the number of days

    public static void main(String[] args){
        Scanner console = new Scanner(System.in);

        intro();

        double netIncome = calculateIncome(console);
        double netExpense = calculateExpense(console);

        report(netIncome, netExpense);
    }

    public static void intro(){
        System.out.println("This program asks for your monthly income and\n" +
                "expenses, then tells you your net monthly income."); //intro sentence

        System.out.println(); //gap
    }

    //return a round-up net income of the user
    public static double calculateIncome(Scanner console){
        System.out.print("How many categories of income? "); //prompt
        int categoriesNum = console.nextInt();
        double netIncome = 0;

        for(int i = 1; i <= categoriesNum; i++){
            System.out.print("    Next income amount? $");
            double input = console.nextDouble();
            netIncome = netIncome + input;
        }

        System.out.println(); //gap
        return (double) Math.round(netIncome*100)/100;
    }

    //return a round-up net spending of the user
    public static double calculateExpense(Scanner console){
        System.out.print("Enter 1) monthly or 2) daily expenses? "); //prompt
        int answer = console.nextInt();
        System.out.print("How many categories of expense? ");
        int categoryNum = console.nextInt();

        double netExpense = 0;
        double netDaily = 0;

        for (int i = 1; i <= categoryNum; i++) {
            System.out.print("    Next expense amount? $");
            double input = console.nextDouble();

            //monthly expense -- net income is the sum of all inputs
            if (answer == 1) {
                netExpense = netExpense + input;

            //daily expense -- net income is the month constant of multiple of the input
            } else if (answer == 2) {
                netDaily = netDaily + input;
                netExpense = DAYS_OF_A_MONTH * netDaily;
            }
        }
        System.out.println(); //gap
        return (double) Math.round(netExpense*100)/100;
    }

    //To give evaluation on the user's spending and saving habit.
    public static void report(double netIncome, double netExpense) {

        //To round the number to have only two decimal places
        double moneyDiff = (double)Math.round((netIncome - netExpense)*100)/100;

        //To calculate the average income and expense
        double averageIncome = (double) Math.round(netIncome / DAYS_OF_A_MONTH * 100) / 100;
        double averageExpense = (double) Math.round(netExpense / DAYS_OF_A_MONTH * 100) / 100;

        System.out.printf("Total income = $%.2f ($%.2f/day)\n", netIncome, averageIncome);
        System.out.printf("Total expenses = $%.2f ($%.2f/day)", netExpense, averageExpense);
        System.out.println(); //need two println to make the format look right
        System.out.println();

        if(moneyDiff >= 0) {
            System.out.printf("You earned $%.2f more than you spent this month.\n", moneyDiff);
            //big saver - save more than $250 than the total spent
            if (moneyDiff >= 250) {
                System.out.println("You're a big saver.");
                System.out.println("You have a really good saving habit.");
            }
            //if moneyDiff is zero  the user is considered
            //to have earned $0 more than they spent (as opposed
            //to spending $0 more than they earned), and is considered
            //as a spender.
            else if(moneyDiff == 0){
                System.out.println("You're a spender.");
                System.out.println("Control yourself");
            }
            //saver - save more than 0 but less than 250 than the total spent
            else{
                System.out.println("You're a saver.");
                System.out.println("Good job!");
            }
        }
        if(moneyDiff < 0){
            //spender - spend not more than 0 but less than 250 than the total earned
            System.out.printf("You spent $%.2f more than you earned this month.\n",
                    Math.abs(moneyDiff));//eliminate the negative sign
            if(moneyDiff<=-250){
                System.out.println("You're a big spender.");
                System.out.println("Don't buy too much!");
            }
            //big spender - total spent is 250 or more larger than the total earned
            else{
                System.out.println("You're a spender.");
                System.out.println("Control yourself!");
            }
        }
    }
}
