package pl.wsb.fitnesstracker.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    void testParticipantCounts() {
        List<Object[]> results = eventRepository.findEventNamesWithParticipantCount();
        results.forEach(row ->
                System.out.println("Event: " + row[0] + ", Count: " + row[1])
        );
    }
}