package com.takuminish.player;

import com.takuminish.othello.*;
import com.takuminish.othello.stone.Stone;
import com.takuminish.othello.stone.StoneFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Player(人間: CLI)
 */
public class ManualCLIPlayer implements Player {
    /** 自分の石の種類 */
    private StoneType myStoneType;

    /**
     * コンストラクタ
     * @param stoneType 自分の石の種類
     */
    public ManualCLIPlayer(StoneType stoneType) {
        this.myStoneType = stoneType;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public StoneType myStoneType() {
        return this.myStoneType;
    }

    /**
     * CLIからの入力により盤面に配置する石を決定する
     * <p>入力形式</p>
     * <pre>x y</pre>
     * @return 盤面に配置する石
     */
    @Override
    public Stone playHand() {
        final Scanner scanner = new Scanner(System.in);

        // 正常な値が入力できるまで、再度入力させる
        while(true) {
            try {
                // Playerからの入力を受け付ける
                // 不正な値の場合は、再度入力
                final int x = Integer.parseInt(scanner.next());
                final int y = Integer.parseInt(scanner.next());
                final CellPosition position = this.selectCellPosition(x, y);

                scanner.close();

                return StoneFactory.createStone4PlayHand(position, this.myStoneType);

            } catch(IllegalArgumentException e)  {
                e.printStackTrace();
                System.out.println("もう一度入力してください。");
            } catch(InputMismatchException e) {
                e.printStackTrace();
                System.out.println("もう一度入力してください。");
            }
         }
    }

    /**
     * 石を配置する位置のインスタンスを返す
     * @param x
     * @param y
     * @return CellPosition 石を配置する位置
     * @throws IllegalArgumentException
     */
    private CellPosition selectCellPosition(final int x, final int y) throws IllegalArgumentException{
        return new CellPosition(new CellPositionValue(x), new CellPositionValue(y));
    }
}
