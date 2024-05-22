package exercise;

import java.util.*;

public class testgForP07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        ArrayList<String> list = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();

        while (!Objects.equals(input, "e")){
            list.add(input);

            input = sc.nextLine();
        }
        boolean isRight = true;


        for (String s : list) {
            map.putIfAbsent(s, 1);
        }

        if (list.size() != map.size()){
            isRight = false;
        }
        System.out.println(isRight);
    }
}
