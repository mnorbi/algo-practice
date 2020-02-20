import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            int differentBooks_B = scanner.nextInt();
            int numberOfLibraries_L = scanner.nextInt();
            int numberOfDays_D = scanner.nextInt();
            Map<Integer, Integer> bookScores = new HashMap<>();
            for (int i = 0; i < differentBooks_B; ++i) {
                bookScores.put(i, scanner.nextInt());
            }
            Library[] libraries = new Library[numberOfLibraries_L];
            for (int i = 0; i < libraries.length; ++i) {
                libraries[i] = new Library();
                libraries[i].bookScores = bookScores;
                libraries[i].libraryId = i;
                libraries[i].numberOfBooks_N = scanner.nextInt();
                libraries[i].numberOfDaysForSignup_T = scanner.nextInt();
                libraries[i].numberOfBooksShippedPerDay_M = scanner.nextInt();
                libraries[i].bookIds = new Integer[libraries[i].numberOfBooks_N];
                for (int j = 0; j < libraries[i].bookIds.length; ++j) {
                    libraries[i].bookIds[j] = scanner.nextInt();
                }
                Arrays.sort(libraries[i].bookIds, comparingInt(bookScores::get));
            }
            Arrays.sort(libraries, comparingDouble(Library::rank));

            int passedDays = 0;
            StringBuilder sb = new StringBuilder();
            int libraryCount = 0;
            for (Library library : libraries) {
                passedDays += library.numberOfDaysForSignup_T;
                if (passedDays > numberOfDays_D) {
                    break;
                }
                ++libraryCount;
                library.shipmentStartDay = passedDays;
                library.bookCursor = library.numberOfBooks_N;
            }
            Map<Integer, List<Integer>> libraryToBookIds = new LinkedHashMap<>();
            BitSet usedBookIds = new BitSet();
            for (int day = 0; day < numberOfDays_D; ++day) {
                for (int i = 0; i < libraryCount && libraries[i].shipmentStartDay <= day; ++i) {
                    int shippedBookCount = 0;
                    for (; libraries[i].bookCursor > 0
                            && shippedBookCount < libraries[i].numberOfBooksShippedPerDay_M; ) {
                        int bookId = libraries[i].bookIds[--libraries[i].bookCursor];
                        if (usedBookIds.get(bookId)) {
                            continue;
                        }
                        usedBookIds.set(bookId);
                        ++shippedBookCount;
                        libraryToBookIds.computeIfAbsent(libraries[i].libraryId, v -> new ArrayList<>()).add(bookId);
                    }
                }
            }
            sb.append("" + libraryToBookIds.size()).append('\n');
            libraryToBookIds.forEach((libraryId,books) -> {
                sb.append(""+libraryId);
                sb.append(""+" ");
                sb.append(""+books.size());
                sb.append('\n');
                for (int j = 0; j < books.size(); ++j) {
                    sb.append(""+books.get(j));
                    if (j < books.size()-1) {
                        sb.append(""+" ");
                    }
                }
                sb.append('\n');
            });
            System.out.println(sb.toString());
        }
    }

    static class Library {

        int bookCursor;
        int shipmentStartDay;
        int libraryId;
        int numberOfBooks_N;
        int numberOfDaysForSignup_T;
        int numberOfBooksShippedPerDay_M;
        Integer[] bookIds;
        Map<Integer, Integer> bookScores;

        double rank() {
            double rank = 1.0;
            rank *= (double) numberOfBooks_N;
            rank *= (((double) bookScores.get(bookIds[(numberOfBooks_N - 1) / 2]) + (double) bookScores.get(bookIds[numberOfBooks_N / 2])) / 2);
            rank *= (double) numberOfBooksShippedPerDay_M;
            rank /= ((double) numberOfDaysForSignup_T);
            return -rank;
        }
    }
}

