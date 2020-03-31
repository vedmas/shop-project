package ru.tokarev.shop.controller.repr;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ProducerRepr implements Serializable {

    private static final long serialVersionUID = -1571893755608898141L;
    private Long id;

    @NonNull
    private String nameProducer;

    @EqualsAndHashCode.Exclude
    @NonNull
    private String region;

    @EqualsAndHashCode.Exclude
    @NonNull
    private String country;

}
