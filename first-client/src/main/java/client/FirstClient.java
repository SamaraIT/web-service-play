package client;

import client.generated.FirstService;
import client.generated.FirstServiceService;

import java.util.List;

public class FirstClient {
    public static void main(String[] args) {
        // set-up
        FirstServiceService service = new FirstServiceService();
        FirstService port = service.getFirstServicePort();
        // sample calls
        System.out.println(port.next1());
        System.out.println();
        List<Integer> nums = port.nextN(4);
        for (Integer num : nums) System.out.println(num);
    }
}
