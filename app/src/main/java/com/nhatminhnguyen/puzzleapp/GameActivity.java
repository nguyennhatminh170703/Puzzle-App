package com.nhatminhnguyen.puzzleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.nhatminhnguyen.puzzleapp.Class.CustomAdapter;
import com.nhatminhnguyen.puzzleapp.Class.Database;
import com.nhatminhnguyen.puzzleapp.Class.GestureDetectGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    private static GestureDetectGridView mGridView;

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    private static String[] tileList;
    private static String animalNameToPlay;

    static Map<String, Integer[]> dict_img = new HashMap<>();
    static Map<String, CharSequence> dict_info_img = new HashMap<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Hide NavBar
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        loadData();

        animalNameToPlay = getIntent().getStringExtra("imageToPlay");

        TextView textAnimal = findViewById(R.id.textAnimal);
        textAnimal.setText(animalNameToPlay);

        ImageView hintButton = findViewById(R.id.hintBtn);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHintDialog();
            }
        });

        ImageButton backBtn = findViewById(R.id.btnBackGame);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        createGame();
    }

    public void loadData() {
        dict_img.put(Database.babyPandaName, Database.babypandaImageList);
        dict_img.put(Database.grayBearName, Database.graybearImageList);
        dict_img.put(Database.pandaName, Database.pandaImageList);
        dict_img.put(Database.polarBearName, Database.polarbearImageList);
        dict_img.put(Database.redPandaName, Database.redpandaImageList);
        dict_img.put(Database.tibetanBearName, Database.tibetanbearImageList);
        dict_img.put(Database.ursidaeName, Database.tibetanbearImageList);


        dict_info_img.put(Database.babyPandaName, getText(R.string.info_babypanda));
        dict_info_img.put(Database.grayBearName, getText(R.string.info_graybear));
        dict_info_img.put(Database.pandaName, getText(R.string.info_panda));
        dict_info_img.put(Database.polarBearName, getText(R.string.info_polarbear));
        dict_info_img.put(Database.redPandaName, getText(R.string.info_redpanda));
        dict_info_img.put(Database.tibetanBearName, getText(R.string.info_tibetanbear));
        dict_info_img.put(Database.ursidaeName, getText(R.string.info_ursidae));

    }

    void initPuzzle() {
        mGridView = findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            // identify for each button puzzle
            tileList[i] = String.valueOf(i);
        }
    }

    void scramblePuzzle() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i >= 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // get width and height of gridView
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();
                // calculate width and height of each button
                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
            }
        });
    }

    private void createGame() {
        initPuzzle();
        scramblePuzzle();
        setDimensions();
    }


    static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;
        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0")) {
                // set image for button
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[0]);
            } else if (tileList[i].equals("1")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[1]);
            } else if (tileList[i].equals("2")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[2]);
            } else if (tileList[i].equals("3")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[3]);
            } else if (tileList[i].equals("4")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[4]);
            } else if (tileList[i].equals("5")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[5]);
            } else if (tileList[i].equals("6")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[6]);
            } else if (tileList[i].equals("7")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[7]);
            } else if (tileList[i].equals("8")) {
                button.setBackgroundResource(dict_img.get(animalNameToPlay)[8]);
            }
            buttons.add(button);
        }
        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }


    private static void swapPuzzle(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);


        if (isSolved()) {
            Toast.makeText(context, "You're the winner", Toast.LENGTH_SHORT).show();
            openResultDialog(context);
        }
    }

    public static void makePuzzleMove(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swapPuzzle(context, position, 1);
            else if (direction.equals(down)) swapPuzzle(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swapPuzzle(context, position, -1);
            else if (direction.equals(right)) swapPuzzle(context, position, 1);
            else if (direction.equals(down)) swapPuzzle(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swapPuzzle(context, position, -1);
            else if (direction.equals(down)) swapPuzzle(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swapPuzzle(context, position, -COLUMNS);
            else if (direction.equals(right)) swapPuzzle(context, position, 1);
            else if (direction.equals(down)) swapPuzzle(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swapPuzzle(context, position, -COLUMNS);
            else if (direction.equals(left)) swapPuzzle(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swapPuzzle(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swapPuzzle(context, position, -COLUMNS);
            else if (direction.equals(right)) swapPuzzle(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swapPuzzle(context, position, -COLUMNS);
            else if (direction.equals(left)) swapPuzzle(context, position, -1);
            else if (direction.equals(right)) swapPuzzle(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swapPuzzle(context, position, -COLUMNS);
            else if (direction.equals(left)) swapPuzzle(context, position, -1);
            else if (direction.equals(right)) swapPuzzle(context, position, 1);
            else swapPuzzle(context, position, COLUMNS);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
        return solved;
    }


    private void openHintDialog() {
        Dialog dialog = new Dialog(GameActivity.this);
        dialog.setContentView(R.layout.dialog_hint_image);
        ImageView imageHint = dialog.findViewById(R.id.hintImage);
        // choose origin image to show hint
        imageHint.setImageResource(dict_img.get(animalNameToPlay)[9]);
        dialog.show();
    }

    private static void openResultDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_win);
        TextView infoAnimal = dialog.findViewById(R.id.info);
        ImageView imageAnimal = dialog.findViewById(R.id.imageWinnning);
        imageAnimal.setImageResource(dict_img.get(animalNameToPlay)[9]);
        infoAnimal.setMovementMethod(new ScrollingMovementMethod());
        Button submitBtn = dialog.findViewById(R.id.menuBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuBtnIntent = new Intent(context, MainActivity.class);
                context.startActivity(menuBtnIntent);
            }
        });
        infoAnimal.setText(dict_info_img.get(animalNameToPlay));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window windowAlDl = dialog.getWindow();

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        windowAlDl.setAttributes(layoutParams);
        dialog.show();
    }

}