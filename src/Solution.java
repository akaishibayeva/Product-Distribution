import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

    class Result {

        /*
         * Complete the 'maxScore' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY a
         *  2. INTEGER m
         */

        public static int maxScore(List<Integer> a, int m) {
            // Write your code here
            Collections.sort(a);
            int segments = (int) Math.floor(a.size()/m);
            int productCounter = 0;
            int productSum = 0;
            int currentSegment = 1;
            List<Integer> segmentScore = new ArrayList<Integer>();

            for (int i = 0; i < a.size(); i++) {
                if (productCounter == m && currentSegment != segments) {
                    segmentScore.add(productSum*currentSegment);
                    currentSegment++;
                    productSum = 0;
                    productCounter = 0;
                }
                productSum+=a.get(i);
                productCounter++;
            }
            segmentScore.add(productSum*currentSegment); // last segment values added b/c && currentSegment != segments
            //only adds up to the last-1;

            int maxScore = 0;
            for (int i = 0; i < segmentScore.size(); i++) {
                maxScore+=segmentScore.get(i);
            }
            return maxScore;
        }

    }

    public class Solution {
        public static void main(String... args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Otput.txt"));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int ans = Result.maxScore(a, m);

            bufferedWriter.write(String.valueOf(ans));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }


