import java.util.Arrays;
import java.util.Scanner;

class Bala {
    private static String name;
    public String number;
    
    Bala() {
        System.out.println("Bala class");
    }

    public void print() {
        //name = "Hero";
        System.out.println(name + " " + number);
    }
}

public class pattern_printing {

    static void print_pattern(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void print_pattern_1(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 1; col <= size; col++) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    static void print_pattern_2(int size) {
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    static void print_pattern_3(int size) {
        for (int row = 1; row <= size; row++) {
            int c = 1;
            for (int col = 1; col <= size; col++) {
                if ((size - row) >= col) {
                    System.out.print(" ");
                } else {
                    System.out.print(c);
                    c++;
                }
            }
            System.out.println();
        }
    }

    static void print_pattern_4(int size) {
        for (int row = 1; row <= size; row++) {
            int c = 1;
            for (int col = 1; col <= size * 2 - 1; col++) {
                if (col <= size - row || col >= size + row) {
                    System.out.print(" ");
                } else {
                    System.out.print(c);
                    c++;
                }
            }
            System.out.println();
        }
    }

    static void print_pattern_5(int size) {
        boolean flag = true;
        for (int row = 1; row <= size; row++) {
            int c = 1;
            flag = true;
            for (int col = 1; col <= size * 2 - 1; col++) {
                if (col <= size - row || col >= size + row) {
                    System.out.print(" ");
                } else {
                    if (flag) {
                        System.out.print(c);
                        flag = false;
                        c++;
                    } else {
                        System.out.print(" ");
                        flag = true;
                    }
                }
            }
            System.out.println();
        }
    }

    static void print_pattern_6(int size) {
        boolean flag = true;
        String str = "BALASHAN";
        size = str.length();
        for (int row = 1; row <= size; row++) {
            int c = 0;
            flag = true;
            for (int col = 1; col <= size * 2 - 1; col++) {
                if (col <= size - row || col >= size + row) {
                    System.out.print(" ");
                } else {
                    if (flag) {
                        System.out.print(str.charAt(c));
                        flag = false;
                        c++;
                    } else {
                        System.out.print(" ");
                        flag = true;
                    }
                }
            }
            System.out.println();
        }
    }

    static void print_pattern_7(int size) {

        for (int row = 1; row < size * 2; row++) {
            int c = 1;
            int s = (size > row) ? size - row: row - size;
            int row1 = (row < size) ? row : size * 2 - row;
            for (int col = 1; col < size * 2; col++) {
                if (col <= s || col >= size + row1) {
                    System.out.print(" ");
                } else {
                    System.out.print(c);
                    c++;
                }
            }
            System.out.println();
        }
      
    }

    static void print_pattern_8(int size) {

        for (int row = 1; row <= size; row++) {
            int c = row;
            boolean flag = true;
            
            for (int col = 1; col < size * 2; col++) {
                if (col <= size - row || col >= size + row) {
                    System.out.print("  ");
                } else {
                    System.out.print(c + " ");
                    if (c == 1) {
                        flag = false;
                    }
                    if (flag) {
                        c--;
                    } else {
                        c++;
                    }
                    
                }
            }
            System.out.println();
        }
    }

    static void print_pattern_9(int n) {
        for (int row = 1; row <= n; row++) {

            for (int space = 0; space < n-row; space++) {
                System.out.print("  ");
            }

            for (int col = row; col >= 1; col--) {
                System.out.print(col + " ");
            }
            for (int col = 2; col <= row; col++) {
                System.out.print(col + " ");
            }

            System.out.println();
        }
    }

    static void print_pattern_10(int n) {
        int originalN = n;
        n = 2 * n;
        for (int row = 0; row <= n; row++) {
            for (int col = 0; col <= n; col++) {
                int atEveryIndex = originalN - Math.min(Math.min(row, col), Math.min(n - row, n - col));
                System.out.print(atEveryIndex + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number:");
        int size = scan.nextInt();
        System.out.println();
        print_pattern_8(size);
    }
}
