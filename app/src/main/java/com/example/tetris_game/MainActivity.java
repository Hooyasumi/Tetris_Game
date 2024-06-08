package com.example.tetris_game;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tetris_game.presenter.GamePresenter;
import com.example.tetris_game.presenter.GameTurn;
import com.example.tetris_game.views.GameFrame;
import com.example.tetris_game.models.TetrisGameModel;
import com.example.tetris_game.views.GameViewImpl;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameFrame gameFrame = findViewById(R.id.game_container);
        TextView gameScoreText = findViewById(R.id.game_score);
        TextView gameStatusText = findViewById(R.id.game_status);
        Button gameCtlBtn = findViewById(R.id.game_ctl_btn);

        GamePresenter gamePresenter = new GamePresenter();
        TetrisGameModel tetrisGameModel = new TetrisGameModel();
        gamePresenter.setGameModel(tetrisGameModel);
        GameViewImpl gameViewImpl = new GameViewImpl(gameFrame, gameScoreText, gameStatusText, gameCtlBtn);
        gamePresenter.setGameView(gameViewImpl);

        Button upBtn = findViewById(R.id.up_btn);
        Button downBtn = findViewById(R.id.down_btn);
        Button leftBtn = findViewById(R.id.left_btn);
        Button rightBtn = findViewById(R.id.right_btn);
        Button rotateBtn = findViewById(R.id.rotate_btn);

        upBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.UP));
        downBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.DOWN));
        leftBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.LEFT));
        rightBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.RIGHT));
        rotateBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.ROTATE));

        gameCtlBtn.setOnClickListener(v -> gamePresenter.changeStatus());

        gamePresenter.init();
    }
}