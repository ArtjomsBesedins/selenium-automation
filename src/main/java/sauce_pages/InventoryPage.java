package sauce_pages;

import lombok.Getter;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage {

    private final WebDriver driver = LocalDriverManager.getInstance();

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(how = How.CLASS_NAME, className = "inventory_list")
    private List<WebElement> inventoryItems;
}
