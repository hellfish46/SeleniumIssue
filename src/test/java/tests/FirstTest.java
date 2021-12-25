package tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class FirstTest extends BaseTest {

    @Test
    public void x(){
        Selenide.open("https://www.google.com.ua/");
        sleep(60000);
    }
}
