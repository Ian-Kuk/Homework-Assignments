import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public interface EncryptionAndDecryption {
    void getKeyFileInfo() throws IOException; //methods that can be used by encryption and decryption classes
    void getLetterPlacement();
}