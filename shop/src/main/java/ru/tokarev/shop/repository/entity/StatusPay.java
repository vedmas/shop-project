package ru.tokarev.shop.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "status_payment")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class StatusPay implements Serializable {

    private static final long serialVersionUID = 7388989823911628671L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nameStatus;
}
