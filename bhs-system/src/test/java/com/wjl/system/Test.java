package com.wjl.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> outputNum = new ArrayList<>();
        int n = scanner.nextInt();
        while (true) {
            if (n == 0 ) {
                break;
            }
            if(n< 0 || n>10000){
                System.out.println("请输入0到10000之间到整数, 输入0退出");
                continue;
            }
            outputNum.add(solve(n));
            n = scanner.nextInt();
        }
        outputNum.forEach(System.out::println);
    }

    private static int solve(int n) {
        int f = 0;
        if (n == 1) {
            f = 2;
        }
        if (n >= 2) {
            f = solve(n - 1) + n;
        }

        return f;
    }
}
