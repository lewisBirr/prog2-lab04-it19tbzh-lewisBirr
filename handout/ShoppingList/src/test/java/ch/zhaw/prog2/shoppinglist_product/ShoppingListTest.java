package ch.zhaw.prog2.shoppinglist_product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testen der Methode getTotalCosts aus der Klasse Shopping
 *
 * @author bles
 */
class ShoppingListTest {
    private ShoppingList shoppingList = null;

    @Mock
    private ProductMigros milkMock;


    // Ansatz: Die Produkte selbst mocken (für testGetTotalCostsWithProductMock())
    // PriceService wird nicht mehr verwendet


    private static final double milkMockPrice = 2.0;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shoppingList = new ShoppingList();

        milkMock = spy(new ProductMigros("MilkId", "Milk", 3));
        when(milkMock.getPrice()).thenReturn(milkMockPrice);
    }

    @Test
    void testGetTotalCostsWithProductMock() {
        shoppingList.addProduct(milkMock);
        assertEquals(shoppingList.getTotalCosts(),6);

        verify(milkMock, times(1)).getPrice();
    }

}
