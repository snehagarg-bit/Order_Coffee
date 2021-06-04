package com.example.ordercoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public int calculatePrice(boolean hasWhippedCream,boolean hasChocolateCream ){
        int basePrice=20;
        if( hasWhippedCream ){
           basePrice=basePrice+5;
        }
        if(hasChocolateCream){
            basePrice=basePrice+10;
        }
        return basePrice*quantity;

    }
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream= whippedCreamCheckbox.isChecked();

        CheckBox  ChocolateCreamCheckbox=(CheckBox)findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChocolateCream= ChocolateCreamCheckbox.isChecked();

        EditText nameField=(EditText)findViewById(R.id.name_field);
        String Name=nameField.getText().toString();
        int price=calculatePrice( hasWhippedCream,hasChocolateCream);
        String priceMessage=createOrderSummary( Name,price,hasWhippedCream,hasChocolateCream );


        Intent intent = new Intent(this, MainActivity2.class);
        //Intent intent = new Intent(Intent.ACTION_SENDTO);
       // intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_SUBJECT, "OrderCoffee for:"+Name );
       // intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }
    public String createOrderSummary( String Name,int price,boolean hasWhippedCream,boolean hasChocolateCream){
        String orderMessage="Name : "+Name;
        orderMessage+="\nQuantity : "+ quantity;
        orderMessage+="\nAdd whipped cream : "+ hasWhippedCream;
        orderMessage+="\nAdd chocolate cream : "+ hasChocolateCream;
        orderMessage+="\nTotal : "+price;
        orderMessage+="\nThank You!";
        return  orderMessage;
    }
    public void increment(View view) {
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement(View view) {
        quantity=quantity-1;
        display(quantity);
    }
   /**
     * This method displays the given quantity value on the screen.
     */
    public void display(int number) {
        TextView quantity  = (TextView) findViewById(R.id.quantity_text_view);
        quantity.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}