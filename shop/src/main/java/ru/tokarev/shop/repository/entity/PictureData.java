package ru.tokarev.shop.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pictures_data")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PictureData implements Serializable {

    private static final long serialVersionUID = -5989288982878549974L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @EqualsAndHashCode.Exclude
    @Column(name = "data", nullable = false, columnDefinition="bytea")
    private byte[] data;
}
