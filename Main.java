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
        // loop over each month
        for (int m = 0; m < MONTHS; m++) {
            // loop over each day
            for (int d = 0; d < DAYS; d++) {
                // loop over each commodity
                for (int c = 0; c < COMMS; c++) {
                    // ex formula for profit: (month + 1)(day + 1)(commodity + 1)
                    // this just generates numbers we can test with
                    profits[m][d][c] = (m + 1) * (d + 1) * (c + 1);
                }
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS) return "INVALID_MONTH"; // check month validity

        int bestIndex =0; // index of the best commodity
        int bestProfit = Integer.MIN_VALUE; // find the highest total profit

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
            return -99999; // invalid day

        // Finds commodity index
        int commIndex = -1;
        for (int i = 0; i < COMMS; i++){
            if (commodities[i].equals(commodity)) {
                commIndex = i;
                break;
            }
        }
        if (commIndex == -1)
            return -99999; // invalid commodity

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
                sum += profits[month][d][c];// sum profits for the day
            }
            if (sum > bestTotal) {
                bestTotal = sum;
                bestDay = d + 1; // +1 bc days are 1-indexed
            }
        }
        return bestDay;
    public static String bestMonthForCommodity(String commodity) {
            int cIndex = -1;
            for (int i = 0; i < COMMS; i++)
                if (commodities[i].equals(commodity)) {
                    cIndex = i;
                    break;
                }
            if (cIndex == -1) return "INVALID_COMMODITY";
            int bestMonth = 0;
            int bestProfit = Integer.MIN_VALUE;//finds the highrst total profit
            for (int m = 0; m < MONTHS; m++) {
                int sum = 0;
                for (int d = 0; d < DAYS; d++)
                    sum += profits[m][d][cIndex];//sums up daily profit for these
                if (sum > bestProfit) {
                    bestProfit = sum;
                    bestMonth = m;
                }
            }
            return months[bestMonth]; // returns the month name
        }
    public static int consecutiveLossDays(String comm) {//longest streak for -profit for a comm
        int cIndex = -1;
        for (int i = 0; i < COMMS; i++)
            if (commodities[i].equals(commodity)) {
                cIndex = i;
                break;
            }
        if (cIndex == -1)
            return -1;

        int longest = 0, current = 0;//tracks the longest and current streak
        // Loop through all months and days
        for (int m = 0; m < MONTHS; m++)
            for (int d = 0; d < DAYS; d++) {
                if (profits[m][d][cIndex] < 0) { // negative profit
                    current++;
                    if (current > longest) longest = current;//update longest streak
                } else {
                    current = 0; // reset streak
                }
            }
        return longest;
    }
    public static int daysAboveThreshold(String comm, int threshold) {//profit above threshold for comms
        int cIndex = -1;
        for (int i = 0; i < COMMS; i++)
            if (commodities[i].equals(commodity)) {
                cIndex = i;
                break;
            }
        if (cIndex == -1)
            return -1;//invalid comm
        int count = 0;
        for (int m = 0; m < MONTHS; m++)
            for (int d = 0; d < DAYS; d++)
                if (profits[m][d][cIndex] > threshold)
                    count++;//for counting days that are in this category
        return count;
    }
    public static int biggestDailySwing(int month) {//biggest daily change
        if (month < 0 || month >= MONTHS)//invalid month
            return -99999;
        int max = 0;
        for (int d = 0; d < DAYS - 1; d++) { // compare day d and d+1
            int sum1 = 0, sum2 = 0;
            for (int c = 0; c < COMMS; c++) {
                sum1 += profits[month][d][c];
                sum2 += profits[month][d + 1][c];
            }
            int diff = sum2 - sum1;//get the difference
            if (diff < 0)//for absoluta value
                diff = -diff;
            if (diff > max) max = diff;//tracks biggest swing
        }
        return max;
    }
    public static String compareTwoCommodities(String c1, String c2) {
        int i1 = -1, i2 = -1;
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(c1)) i1 = i;
            if (commodities[i].equals(c2)) i2 = i;
        }
        if (i1 == -1 || i2 == -1)
            return "INVALID_COMMODITY";
        int sum1 = 0, sum2 = 0;
        for (int m = 0; m < MONTHS; m++)
            for (int d = 0; d < DAYS; d++) {
                sum1 += profits[m][d][i1];
                sum2 += profits[m][d][i2];
            }
        if (sum1 == sum2) return "Equal";
        if (sum1 > sum2) return c1 + " is better by " + (sum1 - sum2);//for the first
        return c2 + " is better by " + (sum2 - sum1);//for the second
    }
    public static String bestWeekOfMonth(int month) {
        if (month < 0 || month >= MONTHS) return "INVALID_MONTH";
        int bestWeek = 1, bestSum = Integer.MIN_VALUE;
        // 4 weeks per month, 7 days per week
        for (int w = 0; w < 4; w++) {
            int start = w * 7, sum = 0;//starting day of the week and sum holder
            for (int d = start; d < start + 7; d++)
                for (int c = 0; c < COMMS; c++)
                    sum += profits[month][d][c];
            if (sum > bestSum) {
                bestSum = sum;
                bestWeek = w + 1;//+1 bc week indexes start from 1
            }
        }
        return "Week " + bestWeek;
    }
    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}