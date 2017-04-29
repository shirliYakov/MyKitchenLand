package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/*
private LinearLayout mLayout;
private EditText mEditText;
private Button mButton;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mLayout = (LinearLayout) findViewById(R.id.linearLayout);
    mEditText = (EditText) findViewById(R.id.editText);
    mButton = (Button) findViewById(R.id.button);
    mButton.setOnClickListener(onClick());
    TextView textView = new TextView(this);
    textView.setText("New text");
}

private OnClickListener onClick() {
    return new OnClickListener() {

        @Override
        public void onClick(View v) {
            mLayout.addView(createNewTextView(mEditText.getText().toString()));
        }
    };
}

private TextView createNewTextView(String text) {
    final LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    final TextView textView = new TextView(this);
    textView.setLayoutParams(lparams);
    textView.setText("New text: " + text);
    return textView;
}
*/


public class AddRecActivity extends AppCompatActivity {

    DBHandler db;
    EditText re_name;
    EditText re_instructions;
    EditText re_ingredient;
    EditText re_amount;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
        db = new DBHandler(this);

        re_name = (EditText) findViewById(R.id.list_name);
        re_instructions = (EditText) findViewById(R.id.input_instructions);
        re_ingredient = (EditText) findViewById(R.id.input_ingre);
        re_amount = (EditText) findViewById(R.id.input_amount);

        //db.addRecipe(new Recipes("RECIP1"));
        //db.addRecipe(new Recipes("RECIP2"));
        //db.addRecipe(new Recipes("RECIP3"));
/*
        TextView t1 = (TextView) findViewById(R.id.textView1);
        t1.isClickable();*/

    }


    public void btn_cancel_On_Click(View v){

        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }


    public void btn_add_re_On_Click(View v){

        Recipes recipe = new Recipes(re_name.getText().toString(), re_instructions.getText().toString());
        db.addRecipe(recipe);
        Ingredient ingredient = new Ingredient(re_amount.getText().toString(), re_ingredient.getText().toString());
        db.addIngredient(ingredient);

        Toast.makeText(AddRecActivity.this, "נוסף למתכונים שלי", Toast.LENGTH_LONG).show();

        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

}





