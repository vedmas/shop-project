package ru.tokarev.shop.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pictures")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Picture implements Serializable {

    private static final long serialVersionUID = 1871253056427042613L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "content_type", nullable = false)
    private String contentType;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name="picture_data_id")
    private PictureData pictureData;

    @ManyToMany(mappedBy = "pictures")
    private List<Products> products;

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
