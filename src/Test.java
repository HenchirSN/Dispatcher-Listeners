import java.awt.*;

public class Test {

    public static void main(String[] args) {
        // Creating 3 EventListeners
        EventListener<Integer> el1 = event -> System.out.println("Listener 1 is accepting the event " + event);
        EventListener<Integer> el2 = event -> System.out.println("Listener 2 is accepting the event " + event);
        EventListener<Integer> el3 = event -> System.out.println("Listener 3 is accepting the event " + event);

        EventDispatcher<Integer> server1 = new EventDispatcher<>(); // Integer dispatcher
        EventDispatcher<String> server2 = new EventDispatcher<>();   // String dispatcher
        Converter<Integer, String> c = i -> "String " + i;

        server1.addNextEventDispatcher(server2, c);

        server1.addListener(event -> System.out.println("Listener on server1 received event: " + event), Filters.ALWAYS_TRUE);
        server2.addListener(event -> System.out.println("Listener on server2 received event: " + event), Filters.ALWAYS_TRUE);

        // Send an Integer to server1
        server1.send(123);

        // The event will be converted to a String and passed to server2
        // Both listeners should receive the converted event
    }
}
