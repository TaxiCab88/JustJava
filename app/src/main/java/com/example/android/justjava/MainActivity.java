package com.example.android.justjava; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p/>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    double coffee = 5;
    double chocolate = 0.5;
    double cream = 0.75;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //this method is called when the "+" button is tapped

    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    //this method is called when the "-" button is tapped

    public void decrement(View view) {
        if (quantity <= 0) {
            displayQuantity(quantity);
            Toast.makeText(MainActivity.this, "Cannot go below 0!", Toast.LENGTH_SHORT).show();
        } else {
            quantity--;
            displayQuantity(quantity);
        }
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        //Finds out the users name for the order
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        String name = nameInput.getText().toString();

        //Verification of checked/unchecked boxes for chocolate
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();



        //Verification of checked/unchecked boxes for chocolate
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();



        //calculating the total price
        double price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }


        //displays the order summary
        displayMessage(priceMessage);

    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }


    /*Calculates the price of the order

    @checks for cream and chocolate being added
    @calculates total
    @returning the price
     */


    private double calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        if (addWhippedCream) {
            coffee = coffee + cream;
        }
        if (addChocolate) {
            coffee = coffee + chocolate;
        }


        return (quantity * coffee);

    }

    /*This shows the whole order text summary
    @param name of the customer
    @param price of the order
    @String is the text summary
    @param addWhippedCream to determine whether or not whipped cream is added
     */
    private String createOrderSummary(String addName, double price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + addName;
        priceMessage += "\nAdd whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd chocolate?" + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

}