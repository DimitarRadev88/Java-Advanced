package com.workshop.customDataStructures;

import com.workshop.customDataStructures.smartArray.CustomList;
import com.workshop.customDataStructures.smartArray.SmartArray;
import com.workshop.customDataStructures.smartStack.CustomStack;
import com.workshop.customDataStructures.smartStack.MyStack;

public class Main {
    public static void main(String[] args) {
        CustomList list = new SmartArray();

        for (int i = 1; i < 11; i++) {
            list.add(i);
        }
        for (int i = list.size() - 1; i > 5; i--) {
            list.remove(i);
        }

        System.out.println(list.contains(2));
        System.out.println(list.contains(5));
        System.out.println(list.contains(0));

        System.out.println(list.get(2));

        list.add(2, 10);

        System.out.println(list.get(2));

        list.forEach(System.out::println);

        CustomStack stack = new MyStack();

        stack.push(3);
        stack.push(2);
        stack.push(1);

        stack.forEach(System.out::println);

        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
//        System.out.println(stack.pop());

    }
}
