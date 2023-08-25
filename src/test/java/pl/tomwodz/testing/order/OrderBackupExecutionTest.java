package pl.tomwodz.testing.order;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderBackupExecutionTest {

    @Test //Time
    void callingBackupWithCreatingAFileFirstThrowException() throws IOException
    {
        //given
        OrderBackup orderBackup = new OrderBackup();

        //then
        assertThrows(IOException.class, () -> orderBackup.backupOrder(new Order()));
    }
}
