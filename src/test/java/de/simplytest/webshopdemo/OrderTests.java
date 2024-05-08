package de.simplytest.webshopdemo;

import de.simplytest.webshopdemo.base.BaseTests;
import de.simplytest.webshopdemo.model.domain.products.Album;
import de.simplytest.webshopdemo.model.page.CartPage;
import de.simplytest.webshopdemo.model.page.HomePage;
import de.simplytest.webshopdemo.model.page.base.Page;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Dimension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTests extends BaseTests {

    @ParameterizedTest
    @MethodSource("provideScreenResolutions")
    void testAddingAlbumToCart(Dimension resolution) {
        driver.manage().window().setSize(resolution);
        driver.get(Page.baseUrl + HomePage.url);

        HomePage homePage = new HomePage(driver);
        homePage = homePage.getHomePageAccordingToWidth();

        assertThat(homePage.headline()).isEqualTo("Shop");
        assertThat(homePage.cartIsEmpty()).isTrue();

        CartPage cartPage = homePage.addProductToCart(Album.sku).viewCart();
        assertThat(cartPage.setQuantityOfProduct(Album.name, 2)).isEqualTo("Cart updated.");

        assertThat(cartPage.getTotalPrice()).isEqualTo("30,00 €");
    }

    public Stream<Arguments> provideScreenResolutions() {
            return Stream.of(
                    Arguments.of(desktop),
                    Arguments.of(mobile));
    }
}
