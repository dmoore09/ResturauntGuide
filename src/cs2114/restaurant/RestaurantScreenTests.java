package cs2114.restaurant;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//-------------------------------------------------------------------------
/**
*  test restaurant screen methods
*
*  @author  dmoore09
*  @version 2012.04.16
*/
public class RestaurantScreenTests
    extends student.AndroidTestCase<RestaurantScreen>
{
    //~ Fields ................................................................

    // TODO Add fields that will contain references to the widgets that you
    // have in your layout.


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    private EditText searchField;
    private TextView numRatings;
    private Button viewMap;
    private Button next;
    private Button previous;

    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public RestaurantScreenTests()
    {
        super(RestaurantScreen.class);

    }

    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        enterText(searchField, "Blacksburg, VA");
    }

    /**
     * enter BlacksBurg, VA as a search
     */
    public void testSearch()
    {

        assertEquals("5",
            numRatings.getText());

    }

    /**
     * test the next button
     */
    public void testNext()
    {
        click(next);
        assertEquals("10",
            numRatings.getText());
    }

    /**
     * test the previous button
     */
    public void testPrevious()
    {
        click(previous);
        assertEquals("17",
            numRatings.getText());
    }

    /**
     * test view map button
     */
    public void testViewMap()
    {
        prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(viewMap);
    }

    /**
     * test business search failed
     */
    public void testBusinessSearchFailed()
    {
        enterText(searchField, "sadfs");
        click(next);

        assertEquals("10",
            numRatings.getText());
    }






    // TODO Add your test methods here.
}
