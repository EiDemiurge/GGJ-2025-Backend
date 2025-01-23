package e2e;

import ggj.engine.EngineLauncher;
import org.junit.jupiter.api.BeforeAll;

public class E2eTest {
    @BeforeAll
    public static void setup() {
        EngineLauncher.launch();
    }
}
