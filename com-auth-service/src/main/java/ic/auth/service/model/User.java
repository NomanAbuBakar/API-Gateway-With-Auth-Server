package ic.auth.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("users")
public class User {
    @Id
    private Long id;
    @Field("name")
    private String name;
    @Field("email")
    private String email;
    @Field("password")
    private String password;
//    @Field("created_at")
//    private LocalDateTime createdAt;
//    @Field("updated_at")
//    private LocalDateTime updatedAt;
}
