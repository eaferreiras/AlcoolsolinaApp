package com.eaferreiras.alcoolsolinaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText editAlcoolPrice;
    private EditText editGasolinaPrice;
    private TextView editResult;
    private View myCoordinator;
    private ImageView imgViewLogo;
    private ImageView imgViewLogoE;
    private ImageView imgViewLogoG;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAlcoolPrice = findViewById(R.id.editAlcoolPrice);
        editGasolinaPrice = findViewById(R.id.editGasolinaPrice);
        editResult = findViewById(R.id.editResult);
        imgViewLogo = findViewById(R.id.imageViewLogo);
        myCoordinator = findViewById(R.id.myCoordinator);
        imgViewLogoE = findViewById(R.id.imageViewLogoE);
        imgViewLogoG = findViewById(R.id.imageViewLogoG);

    }

    public boolean validateData (Double dAlcoolPrice, Double dGasolinaPrice) {
        if (dAlcoolPrice == null || dGasolinaPrice == null) {
            return false;
        }
        return true;
    }

    public void calculatePrice (View view) {
        Double dAlcoolPrice = null;
        Double dGasolinaPrice  = null;
        imgViewLogo.setVisibility(View.VISIBLE);
        imgViewLogoE.setVisibility(View.INVISIBLE);
        imgViewLogoG.setVisibility(View.INVISIBLE);

        try {
            dAlcoolPrice = Double.parseDouble(editAlcoolPrice.getText().toString());
            dGasolinaPrice = Double.parseDouble(editGasolinaPrice.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        } finally {
            if (this.validateData(dAlcoolPrice, dGasolinaPrice)) {
        /*Para cálculo tem a seguinte regra: (alcoolPrice / gasolinaPrice)
            Se resultado  >= 0.7  gasolina é mais vantajosa
            Senão, alcool é mais vantajoso.
         */
                imgViewLogo.setVisibility(View.INVISIBLE);
                if ((dAlcoolPrice / dGasolinaPrice) >= 0.7) {
                    editResult.setText("Abastaceça com GASOLINA!");
                    editResult.setBackgroundColor(Color.YELLOW);
                    imgViewLogoG.setVisibility(View.VISIBLE);
                } else {
                    editResult.setText("Abastaceça com ÁLCOOL!");
                    editResult.setBackgroundColor(Color.GREEN);
                    imgViewLogoE.setVisibility(View.VISIBLE);
                }

            } else {
                if (dAlcoolPrice == null) {
                    editResult.setText("Digite o preço do Alcool!");
                } else if (dGasolinaPrice == null) {
                    editResult.setText("Digite o preço da Gasolina!");
                }
                editResult.setBackgroundColor(Color.RED);
            }

            editResult.setTextColor(Color.WHITE);
            editResult.setTextSize(36);
            editResult.setVisibility(View.VISIBLE);
            Snackbar mySnackbar = Snackbar.make(myCoordinator, editResult.getText(), Snackbar.LENGTH_LONG);
            mySnackbar.show();
        }


    }

}
