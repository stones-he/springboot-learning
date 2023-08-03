import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Student {
    private int id;
    private String name;
    private String className;
    private float age;
    private float englishScore;
    private float mathScore;
    private float chineseScore;
    private float physicsScore;
    private float chemistryScore;

}
