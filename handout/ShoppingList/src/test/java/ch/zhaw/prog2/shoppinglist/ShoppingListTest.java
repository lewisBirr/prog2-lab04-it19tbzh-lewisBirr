package ch.zhaw.prog2.shoppinglist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {

	private ShoppingList shoppingList = null;

	@Mock
	private PriceService priceService;

    private final Product milk = new Product("MilkId", "Milk", 3);
    private static final double milkPrice = 2.0;

    private final Product salad = new Product("SaladId", "Salad", 2);
    private final double saladPrice = 5.0;

    private final Product bread = new Product("BreadId", "Bread", 5);
    private static final double breadPrice = 1.0;



    @BeforeEach
    void setUp() throws Exception {

        shoppingList = new ShoppingList();

        MockitoAnnotations.initMocks(this);

        priceService = mock(PriceService.class);
        when(priceService.getPrice(milk)).thenReturn(milkPrice);
        when(priceService.getPrice(salad)).thenReturn(saladPrice);
        when(priceService.getPrice(bread)).thenReturn(breadPrice);

    }

    @Test
    void testGetProducts() {

        addMilkAndSaladToShoppingList();
        List<Product> list = shoppingList.getProducts();
        assertEquals(2,list.size());
    }

    @Test
    void testAddProduct() {

        addMilkAndSaladToShoppingList();
        shoppingList.addProduct(bread);
        List<Product> list = shoppingList.getProducts();
        assertEquals(3, list.size());
    }

    @Test
    void testGetTotalCosts() {

        shoppingList.setPriceService(priceService);
        addMilkAndSaladToShoppingList();
        shoppingList.addProduct(bread);
        assertEquals(shoppingList.getTotalCosts(),21 );

    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}
