package com.takuminish.othello.stone;

import com.takuminish.othello.CellPosition;
import com.takuminish.othello.StoneType;

public class EmptyStone implements Stone {

    /** 石の位置 */
    private final CellPosition position;

    /** 石の種類 */
    private final StoneType type;

    /**
     * コンストラクタ
     * @param position 石の位置
     * @param type 石の種類
     */
    EmptyStone(final CellPosition position, final  StoneType type) {
        this.position = position;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoneType stoneType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellPosition stonePosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void outputCLIStone() {
        System.out.print("-");
    }
}
