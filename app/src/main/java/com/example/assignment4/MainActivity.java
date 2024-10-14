package com.example.assignment4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProducts;

    private String[] productNames = {"Cappuccino Coffee", "Latte Coffee", "Americano Coffee", "Espresso Coffee", "Mocha Coffee"};
    private double[] productPrices = {250, 300, 200, 150, 180};
    private int[] productQuantities = {0, 0, 0, 0, 0};
    private int[] productImages = {R.drawable.cappuccino, R.drawable.latte, R.drawable.americano, R.drawable.espresso, R.drawable.mocha};

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProducts = findViewById(R.id.list_view_products);

        listViewProducts.setAdapter(new ProductAdapter());

        button = findViewById(R.id.button_add);
    }

    private class ProductAdapter extends android.widget.BaseAdapter {

        @Override
        public int getCount() {
            return productNames.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_listview, parent, false);
            }


            ImageView imageViewProduct = convertView.findViewById(R.id.image_view_product);
            TextView textViewProductName = convertView.findViewById(R.id.text_view_product_name);
            TextView textViewProductPrice = convertView.findViewById(R.id.text_view_product_price);
            TextView textViewQuantity = convertView.findViewById(R.id.quantity);
            TextView textViewTotalPrice = convertView.findViewById(R.id.text_view_product_total); // For total price
            Button buttonIncrement = convertView.findViewById(R.id.increment);
            Button buttonDecrement = convertView.findViewById(R.id.decrement);


            imageViewProduct.setImageResource(productImages[position]);
            textViewProductName.setText(productNames[position]);
            textViewProductPrice.setText(String.format("$%.2f", productPrices[position]));
            textViewQuantity.setText(String.valueOf(productQuantities[position]));


            double totalPrice = productQuantities[position] * productPrices[position];
            textViewTotalPrice.setText(String.format("Total: $%.2f", totalPrice));


            buttonIncrement.setOnClickListener(v -> {
                productQuantities[position]++;
                textViewQuantity.setText(String.valueOf(productQuantities[position]));

                double newTotalPrice = productQuantities[position] * productPrices[position];
                textViewTotalPrice.setText(String.format("Total: $%.2f", newTotalPrice));
            });


            buttonDecrement.setOnClickListener(v -> {
                if (productQuantities[position] > 0) {
                    productQuantities[position]--;
                    textViewQuantity.setText(String.valueOf(productQuantities[position]));


                    double newTotalPrice = productQuantities[position] * productPrices[position];
                    textViewTotalPrice.setText(String.format("Total: $%.2f", newTotalPrice));
                } else {
                    Toast.makeText(MainActivity.this, "Quantity can't be less than 0", Toast.LENGTH_SHORT).show();
                }
            });

            button.setOnClickListener(v-> {
                Toast.makeText(MainActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
            });

            return convertView;
        }
    }
}
