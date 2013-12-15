package cs2114.restaurant;

import android.widget.Button;
import sofia.content.ContentViewer;
import realtimeweb.yelp.DetailedLocation;
import android.net.Uri;
import android.widget.RatingBar;
import sofia.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import realtimeweb.yelp.BusinessQuery;
import realtimeweb.yelp.SearchResponse;
import realtimeweb.yelp.exceptions.BusinessSearchException;
import realtimeweb.yelp.BusinessSearchListener;
import realtimeweb.yelp.BusinessSearch;
import realtimeweb.yelp.Business;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * Restaurant Screen class. Gets business objects and updates the view
 *
 * @author dmoore09
 * @version 2012.04.14
 */
public class RestaurantScreen
    extends Screen
    implements BusinessSearchListener
{
    // ~ Fields ................................................................
    private CircularLinkedList<Business> bList;
    private BusinessSearch               search;

    // fields for widgets
    private TextView                     resturauntName;
    private EditText                     searchField;
    private ImageView                    imageView;
    private RatingBar                    ratingBar;
    private TextView                     numRatings;
    private Button                       viewMap;
    private Button                       next;
    private Button                       previous;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * initialize fields in class
     */
    public void initialize()
    {
        bList = new CircularLinkedList<Business>();
        search = BusinessSearch.getInstance();
    }


    /**
     * when a user searches for a business a business search will begin
     */
    public void searchFieldEditingDone()
    {

        search.searchBusinesses(new BusinessQuery(searchField.getText()
            .toString()), new BusinessSearchGUIAdapter(this));
    }


    /**
     * Update the GUI with new data
     *
     * @param response
     *            returned from search
     */
    public void businessSearchCompleted(SearchResponse response)
    {
        List<Business> notC = response.getBusinesses();

        for (int i = 0; i < notC.size(); i++)
        {
            bList.add(notC.get(i));
        }

        // update the widgets with new data
        Business currentBus = bList.getCurrent();
        updateData(currentBus);

        viewMap.setEnabled(true);
        next.setEnabled(true);
        previous.setEnabled(true);

    }


    /**
     * Happens when search fails, disables gui buttons
     *
     * @param exception
     *            thrown when search fails
     */
    public void businessSearchFailed(BusinessSearchException exception)
    {
        viewMap.setEnabled(false);
        next.setEnabled(false);
        previous.setEnabled(false);

    }


    /**
     * go to the next business in the list and update view
     */
    public void nextClicked()
    {
        bList.next();
        Business currentBus = bList.getCurrent();
        updateData(currentBus);
    }


    /**
     * go to the previous business in the list and update view
     */
    public void previousClicked()
    {
        bList.previous();
        Business currentBus = bList.getCurrent();
        updateData(currentBus);
    }


    /**
     * update the data in the screen
     *
     * @param currentBus
     *            current business
     */
    public void updateData(Business currentBus)
    {
        resturauntName.setText(currentBus.getName());
        ratingBar.setRating(currentBus.getRating());
        String num = "" + currentBus.getReviewCount();
        numRatings.setText(num);
        String uriString = currentBus.getImageUrl();
        if (uriString != null)
        {
            Uri uri = Uri.parse(uriString);
            imageView.setImageURI(uri);
        }

    }


    /**
     * bring up the current restaurant in google maps
     */
    public void viewMapClicked()
    {
        // get current business and latitude and longitude
        Business currentBus = bList.getCurrent();
        DetailedLocation loc = currentBus.getLocation();
        double lon = loc.getLongitude();
        double lat = loc.getLatitude();

        String uriString = "http://maps.google.com/maps?q=" + lat + "," + lon;
        Uri uri = Uri.parse(uriString);
        new ContentViewer(uri).start(this);

    }

    // TODO Write your other methods...
}
