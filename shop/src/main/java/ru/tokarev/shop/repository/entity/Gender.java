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
public class Gender implements Serializable {

    private static final long serialVersionUID = -5532190602666172318L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String nameGender;
}
