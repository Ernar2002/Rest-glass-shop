package kz.yernar.rest_glass_shop.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse<T> {
    private boolean error;
    private String message;
    private T t;
}
