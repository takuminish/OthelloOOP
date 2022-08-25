package com.takuminish.player;

import com.takuminish.othello.*;
import com.takuminish.othello.stone.Stone;
import com.takuminish.othello.stone.StoneFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Player(人間: CLI)
 */
public class ManualCLIPlayer implements Player {
    /** 自分の石の種類 */
    private StoneType myStoneType;

    /** 自分の石の数 */
    private long myStoneNum;

    /**
     * コンストラクタ
     * @param stoneType 自分の石の種類
     */
    public ManualCLIPlayer(StoneType stoneType) {
        this.myStoneType = stoneType;
        this.myStoneNum = 0;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public StoneType myStoneType() {
        return this.myStoneType;
    }

    @Override
    public long myStoneNum() {
        return this.myStoneNum;
    }

    public void setMyStone(final long stoneNum) {
        if(stoneNum < 0) {
            throw new IllegalArgumentException("石の数は負数にはできません。");
        }

        this.myStoneNum = stoneNum;
    }

    /**
     * CLIからの入力により盤面に配置する石を決定する
     * <p>入力形式</p>
     * <pre>x y</pre>
     * @param playPossibleCellPositionList　石が配置可能な位置一覧
     * @return 盤面に配置する石
     */
    @Override
    public Stone playHand(final List<CellPosition> playPossibleCellPositionList) {
        final Scanner scanner = new Scanner(System.in);

        // 正常な値が入力できるまで、再度入力させる
        while(true) {
            try {
                // Playerからの入力を受け付ける
                // 不正な値の場合は、再度入力
                final int x = Integer.parseInt(scanner.next());
                final int y = Integer.parseInt(scanner.next());
                final CellPosition position = this.selectCellPosition(x, y);

                // 石が配置可能な位置出ない場合は、再度入力
                if(!playPossibleCellPositionList.contains(position)) {
                    throw new IllegalArgumentException("配置可能なセルではありません。");
                }

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
