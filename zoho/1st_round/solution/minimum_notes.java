import java.util.Scanner;

public class minimum_notes {
    public static void main(String[] args) {
        int [] notes = {2000, 500, 200, 100, 50, 20, 10, 5, 1};
        int [] notes_count = new int[notes.length];

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int amount = scan.nextInt();
        System.out.println("entered number is " + amount);
        scan.close();

        for (int index = 0; index < notes.length; index++) {
            if (amount > notes[index]) {
                notes_count[index] = amount / notes[index];
                amount = amount % notes[index];
            }
        }

        for (int index = 0; index < notes.length; index++) {
            if (notes_count[index] != 0) {
                System.out.println(notes[index] + ":" + notes_count[index]);
            }
        }
    }
}
