package pl.wsb.fitnesstracker.health_metrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "health_metric")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "heartRate", nullable = false)
    private Float heartRate;

//    public User(
//            final String firstName,
//            final String lastName,
//            final LocalDate birthdate,
//            final String email) {
//
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthdate = birthdate;
//        this.email = email;
//    }

}
