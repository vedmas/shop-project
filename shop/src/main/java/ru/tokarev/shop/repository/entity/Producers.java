package ru.tokarev.shop.repository.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Producers implements Serializable {

    private static final long serialVersionUID = 4447812409953257881L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nameProducer;

    @EqualsAndHashCode.Exclude
    private String region;

    @EqualsAndHashCode.Exclude
    @NonNull
    private String country;

    public Producers(String nameProducer, String region, String country) {
        this.nameProducer = nameProducer;
        this.region = region;
        this.country = country;
    }




}
