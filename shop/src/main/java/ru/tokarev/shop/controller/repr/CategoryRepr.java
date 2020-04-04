package ru.tokarev.shop.controller.repr;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CategoryRepr implements Serializable {

    private static final long serialVersionUID = -1583878940958610954L;

    private Long id;

    @NonNull
    private String nameCategory;
}
