// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};

    static int[][][] profits = new int[MONTHS][DAYS][COMMS];
    //======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        // Loop over each month
        for (int m = 0; m < MONTHS; m++) {
            // Loop over each day
            for (int d = 0; d < DAYS; d++) {
                // Loop over each commodity
                for (int c = 0; c < COMMS; c++) {
                    // Example formula for profit: (month + 1)(day + 1)(commodity + 1)
                    // This just generates numbers we can test with
                    profits[m][d][c] = (m + 1) * (d + 1) * (c + 1);
                }
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS) return "INVALID_MONTH"; // check month validity

        int bestIndex =0; // index of the best commodity
        int bestProfit = Integer.MIN_VALUE; // highest total profit found

        // Loop through all commodities
        for (int c = 0; c < COMMS; c++) {
            int sum = 0; // total profit of this commodity for the month
            for (int d = 0; d < DAYS; d++)
                sum += profits[month][d][c]; // sum profits for each day
            if (sum > bestProfit) { // if this commodity is better
                bestProfit = sum;
                bestIndex = c;
            }
        }
        // Return the commodity name + total profit
        return commodities[bestIndex] + " " + bestProfit;
    }
    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS)
            return -99999; // invalid input return
        int sum = 0;
        for (int c = 0; c < COMMS; c++)
            sum += profits[month][day - 1][c]; // day - 1 because index numbers start from 0
        return sum;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {

        if (from < 1 || to < 1 || from > to || to > DAYS)
            return -99999; // invalid day range

        // Finds commodity index
        int commIndex = -1;
        for (int i = 0; i < COMMS; i++){
            if (commodities[i].equals(commodity)) {
                commIndex = i;
                break;
            }
        }
        if (commIndex == -1) return -99999; // invalid commodity

        int total = 0;
        // Loop through all months and days in range
        for (int m = 0; m < MONTHS; m++) {
            for (int d = from - 1; d <= to - 1; d++)
                total += profits[m][d][commIndex];
        }
        return total;
    }
    public static int bestDayOfMonth(int month) {
        if (month < 0 || month >= MONTHS) return -1; // invalid month

        int bestDay = 1;
        int bestTotal = Integer.MIN_VALUE;

        for (int d = 0; d < DAYS; d++) {
            int sum = 0;
            for (int c = 0; c < COMMS; c++) {
                sum += profits[month][d][c];
            }// sum profits for the day
            if (sum > bestTotal) {
                bestTotal = sum;
                bestDay = d + 1; // +1 because days are 1-indexed
            }
        }
        return bestDay;
    }

    public static String bestMonthForCommodity(String comm) {
            int cIndex = -1;
            for (int i = 0; i < COMMS; i++)
                if (commodities[i].equals(commodity)) {
                    cIndex = i;
                    break;
                }
            if (cIndex == -1) return "INVALID_COMMODITY";

            int bestMonth = 0;
            int bestProfit = Integer.MIN_VALUE;

            for (int m = 0; m < MONTHS; m++) {
                int sum = 0;
                for (int d = 0; d < DAYS; d++)
                    sum += profits[m][d][cIndex];
                if (sum > bestProfit) {
                    bestProfit = sum;
                    bestMonth = m;
                }
            }
            return months[bestMonth]; // return the month name
        }
    public static int consecutiveLossDays(String comm) {
        int cIndex = -1;
        for (int i = 0; i < COMMS; i++)
            if (commodities[i].equals(commodity)) {
                cIndex = i;
                break;
            }
        if (cIndex == -1) return -1;

        int longest = 0, current = 0;
        // Loop through all months and days
        for (int m = 0; m < MONTHS; m++)
            for (int d = 0; d < DAYS; d++) {
                if (profits[m][d][cIndex] < 0) { // negative profit
                    current++;
                    if (current > longest) longest = current;
                } else {
                    current = 0; // reset streak
                }
            }
        return longest;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}