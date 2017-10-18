package khamitov.tests.task.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import khamitov.tests.task.Payload;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
class PayloadParserTest {
    @Test
    void pars() throws IOException {
        String csv = "123,Jack,Daniels,true\n45,Johnny,Walker,false\n6,John,Jameson,true";

        PayloadParser parser = new PayloadParser();
        MappingIterator<Payload> it = parser.pars(csv);

        List<Payload> data = it.readAll();

        assertEquals(3, data.size(), "Size of data list should be 3");

        Payload payload;

        payload = data.get(0);
        assertEquals(123, payload.getId());
        assertEquals("Jack", payload.getFirstName());
        assertEquals("Daniels", payload.getLastName());
        assertTrue(payload.isStatus());

        payload = data.get(1);
        assertEquals(45, payload.getId());
        assertEquals("Johnny", payload.getFirstName());
        assertEquals("Walker", payload.getLastName());
        assertFalse(payload.isStatus());

        payload = data.get(2);
        assertEquals(6, payload.getId());
        assertEquals("John", payload.getFirstName());
        assertEquals("Jameson", payload.getLastName());
        assertTrue(payload.isStatus());
    }

}