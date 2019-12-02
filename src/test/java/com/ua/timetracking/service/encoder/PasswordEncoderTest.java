package com.ua.timetracking.service.encoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordEncoderTest {
    private final PasswordEncoder encoder = new PasswordEncoder();

    private String actual;
    private String expected;

    public PasswordEncoderTest(String actual, String expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> passwordEncodingTable() {
        return Arrays.asList(new Object[][] {
                { "Vfkmdbyf1997", "49A327FE4C9AB67CA9A235B70845EEC".toLowerCase() },
                { "Besthash1199", "8A117E6A55D38E6E95DDA06DF188B7F1".toLowerCase() },
                { "Test123", "68EACB97D86FC4621FA2BE17CABD8C".toLowerCase() },
                { "Abrakadabra325698", "A38C2E83745E4D71BF7523FD8CF24EE1".toLowerCase() }
        });
    }

    @Test
    public void shouldEncodePassword() {
        String expected = this.expected;
        String actual = encoder.encode(this.actual).get();

        assertEquals(expected, actual);
    }
}
