/*
Since you have not learned File class yet, I have written the main method for you
to test your written code. Please don't forget to change the file path
"/Users/luqifei/IdeaProjects/untitled/Paint/test1.txt" to test 4 test txt
files. The 4 test txt files are already saved in assignment 7 zip. The deadline
for this assignment is 15 Mach 2017.
*/

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.io.*;
import java.util.*;

public class LyricAnalyzer {

    public static void add(HashMap<String, ArrayList<Integer>> map, String lyricWord, int wordPosition) {

        // Complete this method.
        if (map.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            if (map.containsKey(lyricWord)) {
                map.get(lyricWord).add(wordPosition);
            } else {
                ArrayList<Integer> n = new ArrayList<Integer>();
                n.add(wordPosition);
                map.put(lyricWord, n);
            }
        }

    }

    public static void displayWords(HashMap<String, ArrayList<Integer>> map) {

        // Complete this method.
        if (map.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            List<String> l = new ArrayList<String>();

            for (String Key : map.keySet()) {
                l.add(Key);
            }
            Collections.sort(l);
            System.out.println();
            for (String lstring : l) {
                System.out.print(lstring + ": ");
                for (int i = 0; i < map.get(lstring).size(); i++) {
                    if (i == map.get(lstring).size() - 1) {
                        System.out.print(map.get(lstring).get(i));
                    } else {
                        System.out.print(map.get(lstring).get(i) + ", ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void displayLyrics(HashMap<String, ArrayList<Integer>> map) {

        // Complete this method.
        if (map.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            int num = 0;

            for (String Key : map.keySet()) {
                num += map.get(Key).size();
            }

            String[] l = new String[num + 1];

            l[0] = "";
            for (String Key : map.keySet()) {
                for (int order : map.get(Key)) {
                    if (order > 0) {
                        l[order] = Key + " ";
                    } else {
                        l[order * (-1)] = Key + System.getProperty("line.separator");
                    }
                }
            }

            for (String s : l) {
                System.out.print(s);
            }
        }

    }

    public static int count(HashMap<String, ArrayList<Integer>> map) {

        // Complete this method.
        if (map.isEmpty()) {
            return 0;
        } else {
            int c = 0;
            for (String Key : map.keySet()) {
                c++;
            }

            return c;
        }

    }

    public static String mostFrequentWord(HashMap<String, ArrayList<Integer>> map) {

        // Complete this method.
        if (map.isEmpty()) {
            return null;
        } else {
            int c = 0;
            String t = "";

            for (String Key : map.keySet()) {
                if (c < map.get(Key).size()) {
                    c = map.get(Key).size();
                    t = Key;
                } else if (c == map.get(Key).size()) {
                    if (t.compareTo(Key) > 0) {
                        t = Key;
                    }
                }
            }
            return t;
        }
    }


    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        int position = 1;  //word position index start from 1


        String pathname = "/Users/zhengkevin/Desktop/assignment7/test1.txt";
        File f = new File(pathname);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String line = "";
        while (true) {
            line = br.readLine();
            if (line == null || line.length() == 0) {
                break;
            }
            String[] arr = line.trim().split(" ");
            for (int i = 0; i < arr.length; i++) {
                if (i != arr.length - 1) {
                    add(map, arr[i].toUpperCase(), position++);
                } else {
                    add(map, arr[i].toUpperCase(), (-1) * position);
                    position++;
                }
            }
        }
        br.close();

        displayLyrics(map);
        displayWords(map);
        System.out.println();
        System.out.println("The number of unique words in the lyric is: " + count(map));
        System.out.println();
        System.out.println("Most frequent word: " + mostFrequentWord(map));
    }

}

