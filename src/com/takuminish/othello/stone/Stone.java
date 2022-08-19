package com.takuminish.othello.stone;

import com.takuminish.othello.CellPosition;
import com.takuminish.othello.StoneType;

public interface Stone {

    /** 石の種類 */
    public StoneType stoneType();

    /** 石の位置 */
    public CellPosition stonePosition();

    /**
     * 石のコンソール出力
     */
    public void outputCLIStone();
}
