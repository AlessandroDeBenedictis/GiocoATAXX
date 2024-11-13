package it.uniba.app.gioco;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitTest {

    private static class MockExitHandler extends ExitHandler {
        private boolean exitCalled = false;

        @Override
        public void exitGame() {
            exitCalled = true;
        }

        public boolean isExitCalled() {
            return exitCalled;
        }
    }

    @Test
    void testExitGame() {
        MockExitHandler mockHandler = new MockExitHandler();
        ExitHandler.setInstance(mockHandler);

        Exit exit = new Exit();
        exit.exitGame();

        assertTrue(mockHandler.isExitCalled(), "System exit was not called");
    }
}
