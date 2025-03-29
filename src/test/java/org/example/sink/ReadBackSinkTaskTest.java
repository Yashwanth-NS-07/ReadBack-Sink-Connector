package org.example.sink;

import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;

public class ReadBackSinkTaskTest {
    @Test
    public void testPrase() {
        Map<String, String> props = new Hashtable<>();
        props.put("source.connection.url", "jdbc:;;;;;");
        props.put("source.connection.user", "appadmin");
        props.put("source.connection.password", "password");
        props = new ReadBackSinkTask().parse(props);

        for(Map.Entry<String, String> entry: props.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
