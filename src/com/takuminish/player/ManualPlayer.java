package com.takuminish.player;

import com.takuminish.othello.CellPosition;
import com.takuminish.othello.CellPositionValue;
import com.takuminish.othello.Stone;
import com.takuminish.othello.StoneType;

import java.util.Scanner;

public class ManualPlayer implements Player {

    /**
     * CLIからの入力により盤面に配置する石を決定する
     * <p>入力形式</p>
     * <pre>x y</pre>
     * @return 盤面に配置する石
     */
    @Override
    public Stone playHand() {
        final Scanner scanner = new Scanner(System.in);

        final CellPositionValue x = new CellPositionValue(scanner.nextInt());
        final CellPositionValue y = new CellPositionValue(scanner.nextInt());

        final CellPosition position = new CellPosition(x, y);

        return new Stone(position, StoneType.White);
    }
}
