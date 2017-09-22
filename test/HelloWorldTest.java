import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {
    @Test
    public void shouldPrintHelloWorld() throws Exception {
        assertEquals("Hello, world", new HelloWorld().print());
    }
}

