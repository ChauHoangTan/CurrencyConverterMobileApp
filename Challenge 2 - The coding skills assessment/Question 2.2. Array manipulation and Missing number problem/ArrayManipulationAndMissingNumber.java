


class ArrayManipulationAndMissionNumber{

    public static void main(String[] args) {
        int[] input = {3, 7, 1, 2, 6, 4};
        int n = 6;
        
        // two ways to solve
        System.out.println(solve1(input, n));
    }

    private static int findItemIndexByValue(int[] input, int n, int value){
        for(int i = 0; i < n; i++){
            if(input[i] == value){
                return i;
            }
        }
        return -1;
    }
    
    public static int solve1(int[] input, int n){
        int[] dp = new int[n + 1];

        for(int i = 0; i < n; i++){
            dp[i] = 0;
        }

        for(int i = 0; i < n; i++){
            dp[input[i] - 1] = 1;
        }

        return findItemIndexByValue(dp, n, 0) + 1;
        
    }

    public static int solve2(int[] input, int n){
        // calculate sum from 1 to n + 1
        int totalSum = (n+1) * (n+2) / 2;

        // calculate sum in array input

        int sumArray = 0;
        for(int i = 0; i < n; i++){
            sumArray += input[i];
        }

        return totalSum - sumArray;
    }
}